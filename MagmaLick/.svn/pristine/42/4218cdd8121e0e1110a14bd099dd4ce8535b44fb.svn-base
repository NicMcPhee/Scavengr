<%@ page import="scavengr.Prompt" %>



<div class="fieldcontain ${hasErrors(bean: promptInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="prompt.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${promptInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: promptInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="prompt.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${promptInstance?.description}"/>
</div>

