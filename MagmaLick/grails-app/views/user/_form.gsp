<%@ page import="scavengr.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" maxlength="40" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" maxlength="250" value="${userInstance?.email}"/>
</div>

