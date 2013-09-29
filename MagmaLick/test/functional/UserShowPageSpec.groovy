import geb.spock.GebReportingSpec  
import spock.lang.*
import pages.*



class UserShowPageSpec extends GebReportingSpec {
    
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
	
	private changePasswordToANewOne(currentPassword, newPassword){
		changePassword.click()
		currentPasswordBox.value(currentPassword) //!= newPasswordBox.value(newPassword)
		newPasswordBox.value(newPassword) //== confirmPasswordBox.value(newPassword)
		confirmNewPasswordBox.value(newPassword)
		confirmPasswordButton.click()
	}
	
	private switchEmail(Email){
		changeEmail.click()
		newEmail.value(Email)
		confirmEmail(Email)
		confirmEmailButton.click()
	}
	
//	def 'when you log in you will be at the user show page'() {
//		when:
//		createUser('Walter', 'Walter@example.com', 'password')
//		loginAs('Walter', 'password')
//		
//		then:
//		at UserShowPage
//	}
//	
//
//    def 'redirects to create a hunt page when create hunt is clicked'() {
//        when:
//        createUser('Walter', 'Walter@example.com', 'password')
//		loginAs('Walter', 'password')
//        userCreateAHuntButton.click()
//        
//        then:
//        at CreateAHuntPage
//    }
//	
//	
//	def 'after changing password you are on the user show page'() {
//		when:
//		createUser('Walter', 'Walter@example.com', 'password')
//		loginAs('Walter', 'password')
//		changePasswordToANewOne('password', 'NewPassword')
//		
//		then:
//		at UserShowPage
//	}
	
//	def 'after changing your Email you are on the user show page'() {
//		when:
//		loginAs('Walter','password')
//		switchEmail('newEmail@example.aaa')
//		
//		then:
//		at UserShowPage
//	}

}