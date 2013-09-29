package scavengr
import scavengr.Notification

class Filters {
    def authenticationService
    def filters = {
        allPages(controller:'*', action:'*'){
            after = { model ->
                try{
                    def user = authenticationService.userPrincipal
                    if(user != null && session.participant == null){
                        model?.messages = Notification.findAll(
                            'from Notification as n where n.recipient=:user and n.isRead=:f',
                            [user:user,f:false],[sort:'dateCreated', order:'desc'])
                        model?.numMessages = Notification.findAll(
                            'from Notification as n where n.recipient=:user and n.isRead=:f',
                            [user:user,f:false]).size()
                        }
                    model?.currentDate = new Date()
                }catch(Exception e){
                    log.error 'Error occured running Filters: ${e.message}', e
                }
            }
        }
    }
}
