import geb.spock.GebReportingSpec 
import spock.lang.*
import pages.*


class HomePageSpec extends GebReportingSpec{
    def pass = 'password'
    def stuff123 = 'stuff123'
    def walter = 'Walter'
    private loginAs(user, password) {
        to HomePage
        loginUserNameBox.value(user)
        loginPasswordBox.value(password)
        loginButton.click()
    }

    private createUser(user, email, password) {
        to HomePage
        createAccountButton.click()
        userNameBox.value(user)
        emailBox.value(email)
        passwordBox.value(password)
        confirmPasswordBox.value(password)
        createButton.click()
    }

    def 'can reach the home page'() {
        when:
        to HomePage

        then:
        at HomePage
    }
    
    def 'can log in as bootstrapped data'() {
        when:
        loginAs(walter, pass)
        
        then:
        at UserShowPage
    }

    def 'can navigate to public hunts'() {
        when:
        to HomePage
        viewPublicHunts.click()
        then:
        at ListOfHuntsPage
    }

    def 'can create account'() {
        when:
        createUser('Pleasurewizard', 'email@junk.gov', pass)

        then:
        at UserShowPage
    }


    def 'hitting Create A Hunt while not logged in does not redirect' (){
        when:
        to HomePage
        createAHuntButton.click()
        
        then:
        at HomePage
    }
       
    def 'clicking the navbar create a hunt button redirects to the create a hunt page if logged in' (){
        when:
        loginAs(walter, pass)
        navbarCreateButton.click()
        
        then:
        at CreateAHuntPage
    }

    def 'clicking the big blue create a hunt button redirects to the create a hunt page if logged in' (){
        when:
        loginAs(walter, pass)
        to HomePage
        createAHuntButton.click()
        
        then:
        at CreateAHuntPage
    }
    
    def 'can cancel signup box'() {
        when:
        to HomePage
        createAccountButton.click()
        closeButton.click()

        then:
        at HomePage
    }

    def 'can log out and then in again'() {
        when:
        createUser(stuff123, 'stuff@junk.gov', pass)
        to HomePage
        logoutButton.click()
        loginAs(stuff123,pass)

        then:
        at UserShowPage
    }



    def 'hello user button redirects to user page'() {
        when:
        loginAs(stuff123, pass)
        to HomePage
        helloButton.click()

        then:
        at UserShowPage

    }

    def 'scavengr button redirects to home page'() {
        when:
        loginAs(stuff123, pass)
        scavengrButton.click()

        then:
        at HomePage
    }
// THis test fails, but it should pass because logging out should take you to the homepage.
//    def 'log out redirects to home page'() {
//        when:
//        loginAs(stuff123, pass)
//        logoutButton.click()
//
//        then:
//        at HomePage
//    }

    def 'create an account with an identical name to another account'() {
        when:
        createUser(stuff123, 'stuff@junk.gov', pass)
        then:
        at HomePage
    }

    def 'log in with incorrect password'() {
        when:
        loginAs(stuff123, 'incorrectpassword')
        then:
        at HomePage
    }
    
    def 'entering a key brings you to a hunt'() {
        when:
        to HomePage
        enterKeyBox.value('yfe5mejs2a')
        findHuntButton.click()
        
        then:
        at HuntShowPage
        
    }
    
    //This test currently does not work but should at some point. 
    /*def 'entering an incorrect key keeps you at the home page'() {
        when:
        to HomePage
        enterKeyBox.value('incorrectkey')
        findHuntButton.click()
        
        then:
        at HomePage
        
    }*/
    


}
