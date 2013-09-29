package scavengr
import com.grailsrocks.authentication.*
class User {
    def authenticationService
    
    String login
    String password
    String email
    int status = AuthenticationService.STATUS_NEW
    static hasMany = [myHunts:Hunt, myAdministratedHunts:Hunt, myCreatedHunts:Hunt, myPhotos:Photo, 
        favorites:Photo, bannedFrom:Hunt, recieved:Notification, sent:Notification] //, myParticipants:Participant]
    static mappedBy = [myHunts:'myUsers', myAdministratedHunts:'myAdmins', myCreatedHunts:'myCreator', 
        bannedFrom:'bannedUsers', recieved:'recipient', sent:'sender', myPhotos:'myUser', favorites:'likedBy']
    static mapping = {
        myPhotos cascade: 'all-delete-orphan'
    }
    
    static constraints = {
        login(minSize:3, maxSize:40, unique: true)
        password(nullable:false, minSize:6, maxSize:40, password: true) 
        email(minSize:6, maxSize:40, email:true, nullable: false, blank: false)
        status(inList:[
            AuthenticationService.STATUS_NEW, 
            AuthenticationService.STATUS_VALID, 
            AuthenticationService.STATUS_AWAITING_CONFIRMATION, 
            AuthenticationService.STATUS_CONFIRMATION_LAPSED
        ])
    }
	
	String toString(){
            return "$login"
	}
    boolean equals(user){
        if(user.getClass() != User){
            return false
        }
        if(user.id != this.id){
            return false
        }
        return true
    }
    
}
