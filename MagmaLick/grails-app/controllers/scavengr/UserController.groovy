package scavengr

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import org.apache.commons.validator.GenericValidator

class UserController {

    Map codeDefaultUser = [code: 'user.label', default: 'User']
    Map actionList = [action: 'list']
    Map flushTrue = [flush: true]
    def showAction = 'show'
    def indexString = 'index'
    def myHuntString = 'myHunt'
    static List getPost = ['GET', 'POST']
    def NotifierService
    def authenticationService
    static allowedMethods = [create: getPost, edit: getPost, delete: getPost[1]]

    def randomPassword(length){
        String pass = ''
        String[] alphabet = ['a','b','c','d','e','f','g','h','i','j','k','l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z']
        String[] numbers = ['0','1','2','3','4','5','6','7','8','9']
        Random random = new Random()
        for (int i = 0; i < length; i++) {

            if (random.nextInt(2) == 0) {
                pass += numbers[random.nextInt(10)]
            } else {
                pass += alphabet[random.nextInt(26)]
            }
        }
        return pass
    }

    def changeEmail(){
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def userInstance = User.findByLogin(auth.user())
            if(!userInstance){
                redirect controller:indexString, params:[login:true]
                return
            }
            if (params.email != params.confirmEmail){
                flash.message = 'The emails entered did not match.'
                redirect action: showAction, params: [login: auth.user()]
                return
            }
            if(!GenericValidator.isEmail(params.email)) {
                flash.message = 'Invalid email address: ' + params.email
                redirect action: showAction, params: [login: auth.user()]
                return
            }
            userInstance.email = params.email
            userInstance.save()
            flash.message = 'Email changed to ' + params.email
            redirect action: showAction, params: [login: auth.user()]
        }
    }

    def resetPassword() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def userInstance = User.findByEmailAndLogin(params.email, params.login)
            if(!userInstance){
                redirect controller:indexString, params:[login:true]
                return
            }
            if(!userInstance){
                flash.message = 'No user named ' + params.login + ' with email address ' + params.email
                redirect actionList
                return

            }
            def newPassword = randomPassword(8)
            userInstance.password = authenticationService.encodePassword(newPassword)
            if(!userInstance.save(flushTrue)){
                flash.message = 'Password reset failed'
                redirect actionList
                return
            }
            NotifierService.sendPasswordReset(userInstance.email, newPassword)
            flash.message = 'Password reset email sent to ' + userInstance.email
            redirect actionList
        }
    }

    def changePassword() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def userInstance = User.findByLogin(auth.user())
            if (authenticationService.encodePassword(params.password) != userInstance.password){
                flash.message = 'Password change failed: entered wrong password!'
                redirect action: showAction, params: [login: auth.user()]
                return
            }
            if (params.newPassword != params.confirmPassword){
                flash.message = 'Password change failed: passwords did not match!'
                redirect action: showAction, params: [login: auth.user()]
                return
            }
            if (params.newPassword.size() < 6){
                flash.message = 'Password change failed: password must be at least 6 characters long.'
                redirect action: showAction, params: [login: auth.user()]
                return
            }
            userInstance.password = authenticationService.encodePassword(params.newPassword)
            if(!userInstance.save(flushTrue)){
                flash.message = 'Password change failed: error saving new password.'
                redirect action: showAction, params: [login: auth.user()]
                return
            }
            flash.message = 'Password Changed!'
            redirect action: showAction, params: [login: auth.user()]
            return
        }
    }

    def downloadPhotos(){
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def userInstance = User.findByLogin(auth.user())
            if(!userInstance?.myPhotos){
                flash.message = 'No photos to download!'
                redirect action: showAction, params: [login: userInstance.login]
                return
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream()
            ZipOutputStream zipFile = new ZipOutputStream(baos)
            userInstance.myPhotos.each { photo ->
                zipFile.putNextEntry(new ZipEntry(
                        (photo.title ?: 'Untitled-' + photo.id) + '.' + photo.fileType.split('/')[1]))
                zipFile << photo.original
                zipFile.closeEntry()
            }

            zipFile.finish()
            response.setHeader('Content-disposition', "filename=\"${userInstance.login}-Scavengr.zip\"")
            response.contentType = 'application/zip'
            response.outputStream << baos.toByteArray()
            response.outputStream.flush()
        }

    }

    def index() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            redirect action:'list', params: params
        }
    }

    def list() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            [userInstanceList: User.list(params), userInstanceTotal: User.count()]
        }
    }

    def myProfile() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            if (authenticationService.isLoggedIn(request)) {
                redirect action: showAction, params: [login: auth.user()]
            } else {
                redirect action: indexString
            }
        }
    }

    def getAuthorizedPhotos(userInstance, loggedInUser, offset){
        Photo.withCriteria{
            order('dateCreated', 'desc')
            maxResults(8)
            firstResult(offset)
            myUser {
                eq('login', userInstance?.login)
            }
            myPrompt {
                or{
                    if (loggedInUser?.myCreatedHunts){ inList(myHuntString, loggedInUser?.myCreatedHunts) }
                    if (loggedInUser?.myAdministratedHunts){ inList(myHuntString, loggedInUser?.myAdministratedHunts) }
                    if (loggedInUser?.myHunts){ inList(myHuntString, loggedInUser?.myHunts) }
                    myHunt {
                        eq('isPrivate', false)
                    }
                }
            }
        }
    }

    def getAuthorizedFavorites(userInstance, loggedInUser, offset){
        Photo.withCriteria{
            likedBy {
                eq('login', userInstance?.login)
            }
            myPrompt {
                or {
                    if (loggedInUser?.myCreatedHunts){ inList(myHuntString, loggedInUser?.myCreatedHunts) }
                    if (loggedInUser?.myAdministratedHunts){ inList(myHuntString, loggedInUser?.myAdministratedHunts) }
                    if (loggedInUser?.myHunts){ inList(myHuntString, loggedInUser?.myHunts) }
                    myHunt {
                        eq('isPrivate', false)
                    }
                }
            }
            order('dateCreated', 'desc')
            maxResults(8)
            firstResult(offset)
        }
    }

    def show() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def userInstance = User.findByLogin(params.login)
            def loggedInUser = User.findByLogin(auth.user())
            def isLoggedInUser = userInstance == loggedInUser
            if (!userInstance) {
                flash.message = message(code: 'default.not.found.message',
                args: [message(codeDefaultUser), params.login])
                redirect actionList
                return
            }
            params.max = Math.min(params.max ? params.int('max') : 8, 100)
            def photoInstanceList = getAuthorizedPhotos(userInstance, loggedInUser, params.int('offset') ?: 0)
            def favoriteInstanceList = getAuthorizedFavorites(userInstance, loggedInUser, params.int('offset') ?: 0)
            def photoInstanceTotal = userInstance.myPhotos.findAll {photo ->
                def hunt = photo?.myPrompt?.myHunt
                hunt?.isPrivate == false || isLoggedInUser || (loggedInUser?.myCreatedHunts?.contains(hunt) ||
                        loggedInUser?.myAdministratedHunts?.contains(hunt) || loggedInUser?.myHunts?.contains(hunt))
            }.size()
            def favoriteInstanceTotal = userInstance.favorites.findAll {photo ->
                def hunt = photo?.myPrompt?.myHunt
                hunt?.isPrivate == false || isLoggedInUser || (loggedInUser?.myCreatedHunts?.contains(hunt) ||
                        loggedInUser?.myAdministratedHunts?.contains(hunt) || loggedInUser?.myHunts?.contains(hunt))
            }.size()
            def publicCreatedHuntInstanceList = userInstance.myCreatedHunts.findAll
            {hunt -> (hunt.isPrivate == false || (!isLoggedInUser && loggedInUser?.myCreatedHunts?.contains(hunt)) )}
            def privateCreatedHuntInstanceList = userInstance.myCreatedHunts.findAll
            {hunt -> hunt.isPrivate == true}
            def publicAdministratedHuntInstanceList = userInstance.myAdministratedHunts.findAll
            {hunt -> hunt.isPrivate == false || (!isLoggedInUser && loggedInUser?.myAdministratedHunts?.contains(hunt))}
            def privateAdministratedHuntInstanceList = userInstance.myAdministratedHunts.findAll
            {hunt -> hunt.isPrivate == true}
            def publicHuntParticipationList = userInstance.myHunts.findAll
            {hunt -> hunt.isPrivate == false || (!isLoggedInUser && loggedInUser?.myHunts?.contains(hunt))}
            def privateHuntParticipationList = userInstance.myHunts.findAll
            {hunt -> hunt.isPrivate == true}

            [userInstance: userInstance, photoInstanceList: photoInstanceList,
                publicCreatedHuntInstanceList: publicCreatedHuntInstanceList,
                privateCreatedHuntInstanceList: privateCreatedHuntInstanceList,
                publicAdministratedHuntInstanceList:publicAdministratedHuntInstanceList,
                privateAdministratedHuntInstanceList:privateAdministratedHuntInstanceList,
                publicHuntParticipationList: publicHuntParticipationList,
                privateHuntParticipationList: privateHuntParticipationList,
                isLoggedInUser: isLoggedInUser, photoInstanceTotal: photoInstanceTotal,
                favoriteInstanceList:favoriteInstanceList, favoriteInstanceTotal:favoriteInstanceTotal,
                myEmail:loggedInUser?.email]
        }
    }

    def cancel() {
        def userInstance = User.get(params.id)
        redirect action: showAction, params: [login: userInstance.login]
    }

}
