
<%@ page import="scavengr.Photo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="container">
	<g:form action="edit" id="${photoInstance?.id}" >
		<div class="row-fluid">
			<div class="span9">
				
				<div class="page-header">
					
					<g:textField style="height:40px;width:50%;font-size:22pt;" placeholder="Title" name="title" value="${photoInstance?.title}" />
					<br />
					<g:textArea style="width:100%;height:50px;font-size:14pt;" placeholder="Description" name="description" value="${photoInstance?.description}"/>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:hasErrors bean="${photoInstance}">
					<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError var="error" bean="${photoInstance}">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}" /></li>
							</g:eachError>
						</ul>
					</bootstrap:alert>
				</g:hasErrors>
				
				<div id="photo" style="text-align:center;">
					<bi:img size="large" bean="${photoInstance}" />
				</div>
			</div>
				
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">Photo Info</li>
							<dt>
								Photo Uploader
							</dt>
							<dd>
								<g:if test="${photoInstance?.myUser}">
									<g:link controller="user" action="show" params="[login: photoInstance.myUser]" >
									${photoInstance?.myUser}
									</g:link>
								</g:if>
								<g:else>
									Anonymous
									${photoInstance?.myUser}
								</g:else>
							</dd>
							<g:if test="${photoInstance?.dateCreated}">
								<dt>Upload Date</dt>
								<dd>
								<g:formatDate date="${photoInstance?.dateCreated}" type="datetime" style="LONG" timeStyle="SHORT"/>
								</dd>
							</g:if>
						<g:if test="${photoInstance?.myPrompt}">
							<g:if test="${!photoInstance?.myPrompt?.myHunt?.isPrivate}" >
								<dt>
									Hunt
								</dt>	
								<dd>
									<g:link controller="hunt" action="show" params="[key:photoInstance?.myPrompt?.myHunt?.key]" >
										${photoInstance?.myPrompt.myHunt.title}
									</g:link>
								</dd>						
								<dt>
									Prompt
								</dt>
								<g:select name="myPrompt.id" from="${photoInstance?.myPrompt?.myHunt?.myPrompts}" value="${photoInstance?.myPrompt.id}" optionKey="id" />
									
								<g:if test="${photoInstance?.myPrompt?.description}">
									<dt>
									Prompt Description
									</dt>
									<dd>
									${photoInstance?.myPrompt?.description}
									</dd>
								</g:if>
							</g:if>
						</g:if>
					</ul>
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
			
		</div>
		</g:form>
		</div>
	</body>
</html>
