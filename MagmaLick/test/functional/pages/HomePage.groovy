package pages

import geb.Page  


class HomePage extends Page {
        def input = 'input'
        def a = 'a'
        static uri = ' '
        
        static at = {
            title.endsWith('Scavengr')
        }
        
        static content = {
            //Join a Hunt
            findHuntButton() {$(input, value:'Go!')}
            viewPublicHunts(to: ListOfHuntsPage) {$(a, text: 'View Public Hunts')}
            enterKeyBox() {$(input, name:'key')}
            
            
            createAHuntButton() {$('div', id: 'create')}
			
/////////////User show page buttons////////////////////////////////////////////////////////////
			userCreateAHuntButton(){$(a, text:'Create Hunt')}
			
//			changeEmail{$()(a, id:'changeEmail')}
//			newEmail(){$(a, id:'newEmail')}
//			confirmEmail(){$(a, id:'confirmEmail')}
//			confirmEmailButton(){$('button', id:'submitEmail')}
			
			downloadPhotos(){$(a, text:'Download My Photos')}
			
			currentPasswordBox(){$('input', id:'changePassword')}
			newPasswordBox(){$('input', id:'newPassword')}
			confirmNewPasswordBox(){$('input', id:'confirmPassword')}
			confirmPasswordButton(){$('button', id:'submitChange')}
			changePassword(){$(a, text:'Change Password')}
            
            //Fields for Log in
            loginUserNameBox() {$(input, id: 'loginUsername')}
            loginPasswordBox() {$(input, id: 'loginPassword')}
            loginButton() {$(input, id:'loginButton')}
            createAccountButton() {$(a, text: 'Create Account')}
            
            //Fields for Popup Sign up
            userNameBox() {$(input, id: 'signupLogin')}
            emailBox() {$(input, id:'emailSignUp')}
            passwordBox() {$(input, id:'passwordSignup')}
            confirmPasswordBox() {$(input, id:'confirmSignup')}
            
            createButton() {$('button', id:'submitSignup')}
            closeButton() {$('button', text:'×')}
			
		
            
            //Buttons that appear in user session
            //this is the button at the top of the page when you are logged in
            navbarCreateButton(to: CreateAHuntPage) {$(a, text: 'Create A Hunt')}
            
            scavengrButton(to: HomePage) {$(a , text: 'Scavengr')}
            helloButton(to: UserShowPage) {$(a, text: startsWith('Hello,')) }
            logoutButton() {$(a, text: 'Log out')}
        }
}
