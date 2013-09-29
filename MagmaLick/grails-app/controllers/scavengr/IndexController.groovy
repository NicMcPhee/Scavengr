package scavengr

class IndexController {
    def authenticationService
    def index() {
        if (session.hunter != null) {
            redirect action: 'show', controller: 'hunt', params: [key: session.key]
        } else {

            if(flash.loginFormErrors || flash.authenticationFailure){
                params.login = true
            }
            if(flash.signupFormErrors){
                params.signup = true
            }
            render view:'index'
        }
    }
}
