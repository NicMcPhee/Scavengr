package scavengr

import org.apache.commons.collections.FactoryUtils
import org.apache.commons.collections.ListUtils

class Hunt {
    String title
    String description
    boolean isPrivate = true

    Date dateCreated
    Date startDate
    Date endDate
    List emails = ListUtils.lazyList(new ArrayList<String>(), FactoryUtils.instantiateFactory(String))
    String key = generateKey()
    User myCreator
    static belongsTo = User
    static hasMany = [myPrompts:Prompt, emails:String, myUsers:User, myAdmins:User, bannedUsers:User]


    static mapping = {
        myPrompts cascade: 'all-delete-orphan'
        key column:'`key'
    }

    static constraints = {
        title blank:false, maxSize:23
        description blank:true, maxSize:250
        startDate nullable:false
        endDate validator: { endDate, Hunt ->
            return endDate.after(Hunt.startDate)
        }
        key blank:false, nullable:false, unique:true, size:10..10
        emails validator: { emails ->
            emails.every {
                email:true
                //FIX WHEN EMAILS WORKS UPDATE: EMAILS WORK BUT THIS IS REDUNDANT?
            }
        }
    }

    String toString(){
        return "$title"
    }

    private String generateKey() {
        String key = ''
        String[] alphabet = ['a','b','c','d','e','f','g','h','i','j','k','l','m',
			'n','o','p','q','r','s','t','u','v','w','x','y','z']
        String[] numbers = ['0','1','2','3','4','5','6','7','8','9']
        Random random = new Random()
        for (int i = 0; i < 10; i++) {

            if (random.nextInt(2) == 0) {
                key += numbers[random.nextInt(10)]
            } else {
                key += alphabet[random.nextInt(26)]
            }
        }
        return key
    }
    
    
    public String genTest(){
        generateKey()
    }
    boolean equals(hunt){
        if(hunt.getClass() != Hunt){
            return false
        }
        if(hunt.id != this.id){
            return false
        }
        return true
    }
}
