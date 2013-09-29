package scavengr
import pl.burningice.plugins.image.ast.FileImageContainer
//import pl.burningice.plugins.image.engines.scale.ScaleType

@FileImageContainer(field = 'myFile')
class Photo {
    byte[] original
    String fileType
    String title
    String description
    String myHunter
    User myUser
    Integer views = 0
    Prompt myPrompt
    Date dateCreated
    
    static belongsTo = [User, Prompt]
    static hasMany = [likedBy:User]
    static constraints = {
        title blank:true, maxSize:22
        description blank:true, maxSize:250
        myUser nullable:true
        original nullable:false, maxSize:5000000
        myPrompt nullable:true
        original sqlType: 'blob'
        myHunter nullable:true
    }

    String toString() {
        return "$title"
    }
    
    boolean equals(p){
        if(p.getClass() != Photo){
            return false
        }
        if(p.id != this.id){
            return false
        }
        return true
    }
    
}
