package scavengr
//import scavengr.Notification
//import grails.converters.JSON

class NotificationController {
    def authenticationService
    
    def dismiss(){
        def notificationInstance = Notification.get(params.id)
        notificationInstance.isRead = true
        render null
    }
    
    def dismissAll(){
        def userInstance = User.findByLogin(auth.user())
        def messages = Notification.findAll("from Notification as n where n.recipient=:user and n.isRead=:f",
            [user:userInstance,f:false],[sort:'dateCreated', order:'desc', max:10])
        messages.each{ msg ->
            msg.isRead = true
        }
        render null
    }
}
