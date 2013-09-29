<%@ page import="scavengr.Hunt" %>



<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="hunt.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${huntInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="hunt.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${huntInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="hunt.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${huntInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="hunt.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${huntInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'key', 'error')} required">
	<label for="key">
		<g:message code="hunt.key.label" default="Key" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="key" required="" value="${huntInstance?.key}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'isPrivate', 'error')} ">
	<label for="isPrivate">
		<g:message code="hunt.isPrivate.label" default="Is Private" />
		
	</label>
	<g:checkBox name="isPrivate" value="${huntInstance?.isPrivate}" />
</div>

<div class="fieldcontain ${hasErrors(bean: huntInstance, field: 'myCreator', 'error')} required">
	<label for="myCreator">
		<g:message code="hunt.myCreator.label" default="My Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="myCreator" name="myCreator.id" from="${scavengr.User.list()}" optionKey="id" required="" value="${huntInstance?.myCreator?.id}" class="many-to-one"/>
</div>

