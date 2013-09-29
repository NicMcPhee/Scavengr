<%@ page import="scavengr.Prompt" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'prompt.label', default: 'Prompt')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		
	</head>
	<body>
	<div class="container">
	<g:form  action="edit" id="${promptInstance?.id}" >
		<g:hiddenField name="version" value="${promptInstance?.version}" />
		<div class="row-fluid">
		
		<div class="span3">
			<div class="well">
			<g:if test="${closedHunt}">
				<h3>This Hunt Has closed on ${endDate}</h3>
				<g:link controller="hunt" action="show" params="[key:promptInstance?.myHunt?.key]" class="btn">
					<i class="icon-arrow-left icon-black"></i>
					<g:message code="default.button.back.label" default="Back to Hunt" />
				</g:link>
			</g:if>
			<g:else>
				<h2>Upload</h2>
				<fieldset>
					<g:form class="form-horizontal" action="create" controller="photo" enctype="multipart/form-data">
						<fieldset>
						<div class="control-group">
							<input disabled style="width:90%" required type="file" name="myFile"/>
							<label for="title">Title</label>
							<input disabled type="text" id="title" name="title"/>
							<label for="description">Description</label>
							<input disabled type="text" id="description" name="description"/>
							<g:hiddenField name="myUser.id" value="${userInstance?.id}" />
							<g:hiddenField name="myPrompt.id" id="myPrompt" value="${promptInstance.id}"/>
						</div>
							
							<div class="control-group">
								<button class="btn" disabled type="button" >
									<i class="icon-arrow-left icon-black"></i>
									<g:message code="default.button.back.label" default="Back to Hunt" />
								</button>
								
								<button disabled type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.upload.label" default="Upload" />
								</button>
							</div>
						</fieldset>
					</g:form>
					
				</fieldset>
				</g:else>
			</div>
			
			<div class="well">
				<h2>Admin Options</h2>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">
						<i class="icon-ok icon-white"></i>
						<g:message code="default.button.update.label" default="Update" />
					</button>
					<button type="submit" class="btn btn-secondary" name="_action_cancel">
						<i class="icon-remove icon-black"></i>
						<g:message code="default.button.cancel.label" default="Cancel" />
					</button>
				</div>
			</div>
		</div>

		<div class="span9">

			<div class="page-header">
				<g:textField required="true" placeholder="Title" style="height:40px;width:50%;font-size:22pt;" name="title" value="${promptInstance.title}" />
				<g:textArea placeholder="Description" style="width:100%;height:50px;font-size:14pt;" name="description" value="${promptInstance.description}" />
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>
			<g:hasErrors bean="${promptInstance}">
					<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError var="error" bean="${promptInstance}">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
							</g:eachError>
						</ul>
					</bootstrap:alert>
				</g:hasErrors>

			<div class="row-fluid" id="prompt-photos">
				<ul class="thumbnails">
					<g:each in="${photoInstanceList}" var="photoInstance">
						<li class="span3" style="position:relative;">
							<g:link class="icon-remove remove-photo" action="removePhoto" params="[photo:photoInstance?.id, id:promptInstance?.id]"></g:link>
							<a class="thumbnail" href="${createLink(controller:'photo', action:'show', id: photoInstance?.id)}">
								<bi:img size="medium" bean="${photoInstance}" />
							</a>
						</li>
	
					</g:each>
				</ul>
				<div class="pagination">
					<bootstrap:paginate id="${promptInstance.id}" total="${photoInstanceTotal}" />
				</div>
			</div>

		</div>

	</div>
						
	</g:form>
	</div>
	</body>
</html>
