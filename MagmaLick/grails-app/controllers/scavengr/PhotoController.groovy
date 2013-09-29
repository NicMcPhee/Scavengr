package scavengr


//import pl.burningice.plugins.image.test.FileUploadUtils
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.mapping.LinkGenerator



class PhotoController {
    LinkGenerator grailsLinkGenerator
    def imageUploadService
    def burningImageService
    Map codeDefaultPhoto = [code: 'photo.label', default: 'Photo']
    Map actionList = [action: 'list']
    Map flushTrue = [flush: true]

    static List getPost = ['GET', 'POST']
    def promptController = 'prompt'
    def showAction = 'show'
    def authenticationService
    static allowedMethods = [create: getPost, edit: getPost, delete: getPost[1]]


    def create() {
        switch (request.method) {
            case getPost[0]:
                [photoInstance: new Photo(params)]
                break
            case getPost[1]:
                def photoInstance = new Photo(params)
                def huntInstance = photoInstance.myPrompt.myHunt
                def userInstance = User.findByLogin(auth.user())
                if(!userInstance) {
                    flash.message = 'You must have an account to upload to a hunt. Please log in or create an account.'
                    redirect controller: promptController, action: showAction, id: params.myPrompt.id
                    return
                }
                def promptInstance = Prompt.get(params.myPrompt.id)
                if(promptInstance.myHunt.bannedUsers.contains(userInstance)){
                    flash.message = 'Upload failed: you have been banned from this hunt.'
                    redirect controller: promptController, action: showAction, id: params.myPrompt.id
                    return
                }
                photoInstance.myPrompt = promptInstance
                photoInstance.myUser = userInstance
                if (session.hunter != null) {
                    photoInstance.myHunter = session.hunter
                }
                def now = new Date()
                def closedHunt = huntInstance.endDate < now || huntInstance.startDate > now
                if(closedHunt){
                    flash.message = 'Upload failed: this hunt has closed.'
                    redirect controller: promptController, action: showAction, id: photoInstance.myPrompt.id
                    return
                }

                def image = request.getFile('myFile')
                def okcontents = ['image/png', 'image/jpeg', 'image/gif']
                if (!okcontents.contains(image.contentType)) {
                    flash.message = "Photo must be one of: ${okcontents}"
                    redirect controller: promptController, action: showAction, id: photoInstance.myPrompt.id
                    return
                }
                if(image.bytes.size() > 5000000){
                    flash.message = 'Photo too large!'
                    redirect controller: promptController, action: showAction, id: photoInstance.myPrompt.id
                    return
                }
                photoInstance.original = image.bytes
                photoInstance.fileType = image.contentType
                if (!photoInstance.save()) {
                    render controller: promptController, view: 'show',
                    model: [photoInstance: photoInstance, promptInstance: photoInstance.myPrompt]
                    return
                }
                imageUploadService.save(photoInstance)
                photoInstance.save(flushTrue)
                if (!huntInstance.myUsers.find {user -> user == userInstance}){
                    userInstance.addToMyHunts(huntInstance)
                    huntInstance.removeFromEmails(userInstance.login)
                    huntInstance.removeFromEmails(userInstance.email)
                    userInstance.save()
                }

                flash.message = message(code: 'scavengr.Photo.created.message',
                args: [message(codeDefaultPhoto), photoInstance.id])
                redirect action: showAction, controller: promptController, id: params.myPrompt.id
                break
        }
    }

    def toggleFavorite(){
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def photoInstance = Photo.get(params.id)
            def userInstance = User.findByLogin(auth.user())
            if(!userInstance){
                return
            }
            if(userInstance.favorites.contains(photoInstance)){
                userInstance.removeFromFavorites(photoInstance)
                render '<i class="icon icon-star"></i> Favorite'
            }else{
                userInstance.addToFavorites(photoInstance)
                render '<i class="icon icon-star-empty"></i> Unfavorite'
            }
        }
    }

    def isInHunt(hunt, user){
        if(hunt.myUsers.contains(user) || hunt.myAdmins.contains(user) || hunt.myCreator == user){
            return true
        }
        return false
    }

    def show() {
        def photoInstance = Photo.get(params.id)
        def showHunt = false
        def isMyPhoto = false
        def loggedInUser = User.findByLogin(auth.user())
        def key
        if (authenticationService.isLoggedIn(request) && loggedInUser == photoInstance.myUser) {
            isMyPhoto = true
        }
        def hunt = photoInstance?.myPrompt?.myHunt

        if (!hunt?.isPrivate || isInHunt(hunt, loggedInUser)) {
            showHunt = true
            key = photoInstance?.myPrompt?.myHunt?.key
        }
        if (!photoInstance) {
            flash.message = message(code: 'default.not.found.message',
            args: [message(codeDefaultPhoto), params.id])
            redirect actionList
            return
        }

        def photoIdList = authorizedIds(loggedInUser, photoInstance.myUser)
        def index = photoIdList.indexOf(params.long('id'))
        def prevId
        def nextId
        if (index > 0){
            prevId = photoIdList.get(index - 1)
        }
        if (index < photoIdList.size()-1){
            nextId = photoIdList.get(index + 1)
        }
        def isFavorite = loggedInUser?.favorites?.contains(photoInstance)
        photoInstance.views++

        [isFavorite:isFavorite, photoInstance: photoInstance, isMyPhoto: isMyPhoto,
            showHunt: showHunt, key: key, prevId: prevId, nextId: nextId]
    }

    def authorizedIds(loggedInUser, photoOwner){
        def myHuntString = 'myHunt'
        Photo.withCriteria{
            projections {
                property('id')
            }
            order('dateCreated', 'desc')
            myUser {
                eq('login', photoOwner?.login)
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
        }
    }
    def viewImage() {
        def photoInstance = Photo.get( params.id )
        response.outputStream << photoInstance.original
        response.outputStream.flush()
    }

    def cancel() {
        def photoInstance = Photo.get( params.id )
        redirect action: showAction, id: photoInstance.id
    }

    def edit() {
        switch (request.method) {
            case getPost[0]:
                def photoInstance = Photo.get(params.id)
                if (User.findByLogin(auth.user()) != photoInstance.myUser) {
                    redirect action: showAction, id: photoInstance.id
                    return
                }
                if (!photoInstance) {
                    flash.message = message(code: 'default.not.found.message',
                    args: [message(codeDefaultPhoto), params.id])
                    redirect actionList
                    return
                }

                [photoInstance: photoInstance]
                break
            case getPost[1]:
                def photoInstance = Photo.get(params.id)
                if (User.findByLogin(auth.user()) != photoInstance.myUser) {
                    redirect action: showAction, id: photoInstance.id
                    return
                }
                if (!photoInstance) {
                    flash.message = message(code: 'default.not.found.message',
                    args: [message(codeDefaultPhoto), params.id])
                    redirect actionList
                    return
                }


                if (params.version) {
                    def version = params.version.toLong()
                    if (photoInstance.version > version) {
                        photoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(codeDefaultPhoto)] as Object[],
                                'Another user has updated this Photo while you were editing')
                        render view: 'edit', model: [photoInstance: photoInstance]
                        return
                    }
                }
                photoInstance.properties = params
                if (!photoInstance.save(flushTrue)) {
                    render view: 'edit', model: [photoInstance: photoInstance]
                    return
                }

                flash.message = message(code: 'scavengr.Photo.updated.message',
                args: [message(codeDefaultPhoto), photoInstance.id])
                redirect action: showAction, id: photoInstance.id
                break
        }
    }

    def delete() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def photoInstance = Photo.get(params.id)
            if (User.findByLogin(auth.user()) != photoInstance.myUser) {
                redirect action: showAction, id: photoInstance.id
                return
            }
            if (!photoInstance) {
                flash.message = message(code: 'default.not.found.message',
                args: [message(codeDefaultPhoto), params.id])
                redirect actionList
                return
            }


            try {
                photoInstance.likedBy.each{ user ->
                    user.removeFromFavorites(photoInstance)
                }
                photoInstance.likedBy.clear()
                //photoInstance.myUser.removeFromFavorites()
                photoInstance.delete(flushTrue)
                flash.message = message(code: 'default.deleted.message',
                args: [message(codeDefaultPhoto), params.id])
                redirect controller: 'User', action: 'myProfile'
            }
            catch (DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message',
                args: [message(codeDefaultPhoto), params.id])
                redirect action: showAction, id: params.id
            }
        }
    }
}