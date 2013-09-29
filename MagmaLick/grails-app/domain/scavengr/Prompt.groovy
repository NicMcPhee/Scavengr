package scavengr


class Prompt {
    String title
    String description
    Hunt myHunt
    Date dateCreated
    boolean deleted

    static transients = ['deleted']

    static belongsTo = [myHunt:Hunt]
    static hasMany = [myPhotos:Photo]

    static constraints = {
        title blank:false, maxSize:23
        description blank:true, maxSize:250
    }

    static mapping = {
    }

    String toString() {
        return "$title"
    }
    
    boolean equals(p){
        if(p.getClass() != Prompt){
            return false
        }
        if(p.id != this.id){
            return false
        }
        return true
    }
    
    def sessionSuccess(hunter) {
        for(photo in myPhotos) {
            if (photo.myHunter != null && photo.myHunter == hunter) {
                return true
            }
        }
        return false
    }
}
