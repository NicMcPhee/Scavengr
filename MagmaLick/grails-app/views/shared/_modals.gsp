<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-modalmanager.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-modal.js')}"></script>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-modal.css')}">
<script type="text/javascript">
$(document).ready(function(){
	$('#signupModal').on('shown', function() {
		$('#signupLogin').focus();
	});

	$('#resetModal').on('shown', function() {
		$('#resetLogin').focus();
	});

	$('#changeModal').on('shown', function() {
		$('#changePassword').focus();
	});

	$('#loginModal').on('shown', function() {
		$('#loginLogin').focus();
	});
	
});
function submitReset(){
	$('#resetForm').submit();
}
function submitSignup(){
	$('#signupForm').submit();
}
function submitLogin(){
	$('#loginForm').submit();
}

function submitChange(){
	$('#changeForm').submit();
}
function submitEmail(){
	$('#emailForm').submit();
}
function hideLogin(){
	$('#loginModal').modal('hide');
}

</script>
<auth:ifNotLoggedIn>
<div id="loginModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h2 id="myModalLabel">Log In</h2>
	</div>
	
	<div class="modal-body">
		<auth:form id="loginForm" authAction="login" success="[controller:'user', action:'myProfile']" error="[controller:'index', action:'index']">
			<label for="login">Username</label>
			<g:textField id="loginLogin" name="login" value="${flash.loginForm?.login?.encodeAsHTML()}"/>
			<label for="password">Password</label>
			<input type="password" name="password" />
			<input type="submit" style="visibility: hidden;" />
		
		</auth:form>
	
	</div>
	
	<div class="modal-footer">
		
		<g:link fragment="signupModal" data-toggle="modal" class="btn" onclick="hideLogin();">Create Account</g:link>
		<button type="button" id="submitLogin" onclick="submitLogin()" class="btn btn-primary">
			<i class="icon-ok icon-white"></i>
			<g:message code="default.button.login.label" default="Log In" />
		</button>
	</div>
	
</div>

<div id="signupModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h2 id="myModalLabel">Sign Up</h2>
	</div>
	
		<div class="modal-body">
			<g:if test="${flash.signupFormErrors}">
				<bootstrap:alert class="alert-error">
						<g:renderErrors bean="${flash.signupFormErrors}" as="list" field="login" />

						<g:renderErrors bean="${flash.signupFormErrors}" as="list" field="email" />

						<g:renderErrors bean="${flash.signupFormErrors}" as="list" field="password" />

						<g:renderErrors bean="${flash.signupFormErrors}" as="list" field="passwordConfirm" />
				</bootstrap:alert>
			</g:if>
			
			<div class="row-fluid">
				<div class="span6">
					<auth:form id="signupForm" authAction="signup" success="[controller:'user', action:'myProfile']" error="[controller:'index', action:'index']">
				
					<label for="login">Username</label>
					<input type="text" id="signupLogin" name="login" value="${flash.signupForm?.login?.encodeAsHTML()}" required/>
	
					<label for="email">Email Address</label>
					<g:textField name="email" id="emailSignUp" value="${flash.signupForm?.email?.encodeAsHTML()}" required="true" />
	
					<label for="password">Password</label> 
					<input type="password" id="passwordSignup" name="password" required /> 
					<label for="passwordConfirm">Confirm Password</label> 
					<input type="password" id="confirmSignup" name="passwordConfirm" required />
	
					<g:hiddenField name="immediate" value="true" />
					<input type="submit" style="visibility: hidden;" />
					</auth:form>
				</div>
				
				<div class="span6 well">
					<h3>Welcome!</h3>
					<p>By signing up, you are taking the first step on your way to becoming a great photographer! 
					With your new account, you can create photo scavenger hunts, participate in public hunts, and fill your profile with pictures! 
					</p>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" id="submitSignup" onclick="submitSignup();" class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<g:message code="default.button.create.label" default="Create Account" />
			</button>
		</div>
	
</div>



<div id="resetModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h2 id="myModalLabel">Reset Password</h2>
	</div>
	
	<div class="modal-body">
		<p>Enter your username and email address below and an email will be set to you containing your new password.</p>
		<g:form name="resetForm" controller="user" action="resetPassword">
		<label for="login">Username</label>
		<input required type="text" id="resetLogin" name="login"/>
		<label for="email">Email Address</label>
		<input required type="text" id="resetEmail" name="email"/>
		<input type="submit" style="visibility: hidden;" />
		</g:form>
		
	</div>
	<div class="modal-footer">
		<button type="button" id="submitReset" onclick="submitReset();" class="btn btn-primary">
			<i class="icon-ok icon-white"></i>
			<g:message code="default.button.reset.label" default="Reset Password" />
		</button>
	</div>
</div>

</auth:ifNotLoggedIn>

<auth:ifLoggedIn>

<div id="changeModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" id="closeButton" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h2 id="myModalLabel">Change Password</h2>
	</div>
	
	<div class="modal-body">
		<g:form controller="user" action="changePassword" name="changeForm">
		<p>Enter your current password and your new password twice.</p>
			<label for="password">Your Current Password</label>
			<input required type="password" id="changePassword" name="password"/>
			<label for="newPassword">Your New Password</label>
			<input required type="password" id="newPassword" name="newPassword"/>
			<label for="confirmPassword">Confirm Your New Password</label>
			<input required type="password" id="confirmPassword" name="confirmPassword"/>
			<input type="submit" style="visibility: hidden;" />
		</g:form>
			
	</div>
	<div class="modal-footer">
		<button type="button" id="submitChange" onclick="submitChange();" class="btn btn-primary">
			<i class="icon-ok icon-white"></i>
			<g:message code="default.button.change.label" default="Change Password" />
		</button>
	</div>
</div>

<div id="emailModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h2 id="myModalLabel">Change Email Address</h2>
	</div>
	
	<div class="modal-body">
		<g:form controller="user" action="changeEmail" name="emailForm">
		<p>Your current address: ${myEmail}</p>
		<p>Enter your new email address twice.</p>
			<label for="newEmail">Your New Email</label>
			<input required type="email" id="newEmail" name="email"/>
			<label for="confirmEmail">Confirm Your New Email</label>
			<input required type="email" id="confirmEmail" name="confirmEmail"/>
			<input type="submit" style="visibility: hidden;" />
		</g:form>
			
	</div>
	<div class="modal-footer">
		<button type="button" id="submitEmail" onclick="submitEmail();" class="btn btn-primary">
			<i class="icon-ok icon-white"></i>
			<g:message code="default.button.email.label" default="Change Email" />
		</button>
	</div>
</div>




</auth:ifLoggedIn>