package scavengr

class Notification {
    String subject
    String message
    String action
    String link
    User sender
    User recipient
    Date dateCreated
    boolean isRead = false
    
    static belongsTo = [recipient:User]
    static constraints = {
        subject maxSize:25
        message blank:true, nullable: true
        action blank:true, nullable: true
        link blank:true, nullable: true
    }
    String toString(){
        return subject
    }
    boolean equals(n){
        if(n.getClass() != Notification){
            return false
        }
        if(n.id != this.id){
            return false
        }
        return true
    }
}
