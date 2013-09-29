package pages

import geb.Page

class ListOfHuntsPage extends Page {

        static uri = 'hunt/list'
        
        static at = {
            title.endsWith('Hunt List')
        }
        
        static content = {
            //The navbar buttons
            scavengrButton(to: HomePage) {$('a' , id:'scavengrButton')}
            //the text is Stuff123 because that is the account name that we used in the test
            helloButton(to: UserShowPage) {$('a', text:'Hello, Stuff123') }
            logoutButton(to: HomePage) {$('a', text: 'Log out')}
            
        }
    
}
