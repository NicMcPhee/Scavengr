package scavengr

import org.springframework.dao.DataIntegrityViolationException
//import scavengr.Hunt

class PromptController {

    Map codeDefaultPrompt = [code: 'prompt.label', default: 'Prompt']
    Map actionList = [action: 'list']
    Map flushTrue = [flush: true]
    def showAction = 'show'
    def huntCon = 'hunt'
    static List getPost = ['GET', 'POST']

    def authenticationService
    static allowedMethods = [create: getPost, edit: getPost, delete: getPost[1]]


    def create() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            switch (request.method) {
                case getPost[0]:
                    [promptInstance: new Prompt(params)]
                    break
                case getPost[1]:
                    def promptInstance = new Prompt(params)

                    if (!promptInstance.save(flushTrue)) {
                        flash.message = 'Prompt title / description too long!'
                        redirect controller:huntCon, action: showAction, params: [key: promptInstance.myHunt.key]
                        return
                    }

                    flash.message = message(code: 'default.created.message',
                    args: [message(codeDefaultPrompt), promptInstance.id])
                    redirect controller: huntCon, action: showAction, params: [key: promptInstance.myHunt.key]
                    break
            }
        }
    }

    def show() {
        def userInstance = User.findByLogin(auth.user())
        def promptInstance = Prompt.get(params.id)
        def now = new Date()
        def closedHunt = promptInstance.myHunt.endDate < now || promptInstance.myHunt.startDate > now
        def endDate = promptInstance.myHunt.endDate.dateTimeString
        params.max = Math.min(params.max ? params.int('max') : 8, 100)
        def photoInstanceList = Photo.findAllByMyPrompt(
                promptInstance, [sort:'dateCreated', order:'desc', max:params.max, offset:params.offset])


        [promptInstance: promptInstance,
            photoInstanceList: photoInstanceList, userInstance: userInstance,
            photoInstanceTotal: Photo.findAllByMyPrompt(promptInstance).size(),
            closedHunt:closedHunt, endDate:endDate,
            isCreatorOrAdmin:isAdminOrCreator(userInstance, promptInstance.myHunt)]
    }

    def cancel() {
        def promptInstance = Prompt.get(params.id)
        redirect action: showAction, id: promptInstance.id
    }

    def removePhoto(){
        def promptInstance = Prompt.get(params.id)
        if (session.hunter != null) {
            redirect action: 'show', id: promptInstance.id, params:params
        } else {
            def userInstance = User.findByLogin(auth.user())
            if (!isAdminOrCreator(userInstance, promptInstance.myHunt)) {
                redirect action: showAction, id: promptInstance.id
                return
            }
        }
    }

    def isAdminOrCreator(user, hunt){
        if(user != hunt.myCreator && !hunt.myAdmins.contains(user)){
            return false
        }
        return true
    }

    def edit() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            switch (request.method) {
                case getPost[0]:
                    def promptInstance = Prompt.get(params.id)
                    def userInstance = User.findByLogin(auth.user())

                    if (!isAdminOrCreator(userInstance, promptInstance.myHunt)) {
                        redirect action: showAction, id: promptInstance.id
                        return
                    }
                    if (!promptInstance) {
                        flash.message = message(code: 'default.not.found.message',
                        args: [message(codeDefaultPrompt), params.id])
                        redirect actionList
                        return
                    }
                    params.max = Math.min(params.max ? params.int('max') : 8, 100)
                    def photoInstanceList = Photo.findAllByMyPrompt(
                            promptInstance, [max:params.max, offset:params.offset])

                    [promptInstance: promptInstance, photoInstanceList: photoInstanceList,
                        photoInstanceTotal: Photo.findAllByMyPrompt(promptInstance).size()]
                    break
                case getPost[1]:
                    def promptInstance = Prompt.get(params.id)
                    def userInstance = User.findByLogin(auth.user())
                    if (!isAdminOrCreator(userInstance, promptInstance.myHunt)) {
                        redirect action: showAction, id: promptInstance.id
                        return
                    }
                    if (!promptInstance) {
                        flash.message = message(code: 'default.not.found.message',
                        args: [message(codeDefaultPrompt), params.id])
                        redirect actionList
                        return
                    }


                    if (params.version) {
                        def version = params.version.toLong()
                        if (promptInstance.version > version) {
                            promptInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                    [message(codeDefaultPrompt)] as Object[],
                                    'Another user has updated this Prompt while you were editing')
                            render view: 'edit', model: [promptInstance: promptInstance]
                            return
                        }
                    }

                    flash.message = message(code: 'default.updated.message',
                    args: [message(codeDefaultPrompt), promptInstance.id])
                    redirect action: showAction, id: promptInstance.id
                    break
            }
        }
    }

    def delete() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {
            def promptInstance = Prompt.get(params.id)
            if (!isAdminOrCreator(User.findByLogin(auth.user()) , promptInstance.myHunt)) {
                redirect action: showAction, id: promptInstance.id
                return
            }
            if (!promptInstance) {
                flash.message = message(code: 'default.not.found.message',
                args: [message(codeDefaultPrompt), params.id])
                redirect actionList
                return
            }
            def key = promptInstance.myHunt.key
            try {
                promptInstance.myPhotos.each { photo ->
                    photo.myPrompt = null
                }


                promptInstance.delete(flushTrue)
                flash.message = message(code: 'default.deleted.message',
                args: [message(codeDefaultPrompt), params.id])
                redirect controller: huntCon, action: showAction, params:[key:key]
            }
            catch (DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message',
                args: [message(codeDefaultPrompt), params.id])
                redirect action: showAction, id: params.id
            }
        }
    }
}
