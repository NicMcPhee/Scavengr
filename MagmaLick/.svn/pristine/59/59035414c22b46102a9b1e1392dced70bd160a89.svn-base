
<%@ page import="scavengr.Hunt"%>
<%@ page import="scavengr.Prompt"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-datetimepicker.min.js')}" ></script>
<link href="${resource(dir: 'css', file: 'bootstrap-datetimepicker.min.css')}" rel="stylesheet" />
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrapSwitch.js')}"></script>
<link href="${resource(dir: 'css', file: 'bootstrapSwitch.css') }" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".prompt").click(function() {
			document.location = "../../prompt/show/" + this.id

		});

		$('#new-prompt').submit(function() {
			if ($('#title').val() != '') {
				$('#new-prompt').submit();
			} else {
				$('#title').focus();
				return false;
			}
		});

		$('.datepicker').datetimepicker({
			language: 'en',
			pick12HourFormat: true,
			pickSeconds: false
		});
	});
</script>


<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'hunt.label', default: 'Hunt')}" />
<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<div class="container">
	<g:form action="edit" id="${huntInstance?.id}" >
		<g:hiddenField name="version" value="${huntInstance?.version}" />
		<div class="row-fluid">
	
	
	
			<div class="span9">
	
				<div class="page-header">
					<g:textField style="height:40px;width:50%;font-size:22pt;" placeholder="Title" name="title" value="${huntInstance?.title}" />
					<g:textArea placeholder="Description" style="width:100%;height:50px;font-size:14pt;" name="description" value="${huntInstance.description}" />
				</div>
	
				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">
						${flash.message}
					</bootstrap:alert>
				</g:if>
				<g:hasErrors bean="${huntInstance}">
						<bootstrap:alert class="alert-error">
							<ul>
								<g:eachError bean="${huntInstance}" var="error">
									<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
											error="${error}" /></li>
								</g:eachError>
							</ul>
						</bootstrap:alert>
					</g:hasErrors>
	
				<g:each in="${promptPhotoList}" var="promptPhotoContainer">
				<div class="row-fluid prompt-row" >
					<div class="span4">
						<g:if test="${promptPhotoContainer[1]}">
							<g:link controller="prompt" action="show" id="${promptPhotoContainer[0].id}" class="btn btn-prompt btn-success"><g:fieldValue bean="${promptPhotoContainer[0]}" field="title" /><i class="icon-ok"></i></g:link>
						</g:if>
						<g:else>
							<g:link controller="prompt" action="show" id="${promptPhotoContainer[0].id}" class="btn btn-prompt btn-primary"><g:fieldValue bean="${promptPhotoContainer[0]}" field="title" /></g:link>
						</g:else>
					</div>
					<div class="span8 small-photo">
						<g:each in="${promptPhotoContainer}" var="photoInstance" status="i">
							<g:if test="${ i > 1 }">
								<g:if test="${photoInstance?.myUser == userInstance}">
									<div class="span2" style="position:relative;">
										<i class="icon-white icon-user user-photo"></i>
									
										<a class="thumbnail" href="${createLink(controller:'photo', action:'show', id: photoInstance?.id)}">
										<bi:img size="small" bean="${photoInstance}" />
										</a>
									</div>
								</g:if>
								<g:else>
									<div class="span2 ">
										<div>
											<a class="thumbnail" href="${createLink(controller:'photo', action:'show', id: photoInstance?.id)}">
											<bi:img size="small" bean="${photoInstance}" />
											</a>
										</div>
									</div>
								</g:else>
							</g:if>
							
						</g:each>
						
					</div>
				</div>
			</g:each>
	
	
	
	
	
	
	
	
	
	
	
			</div>
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">Hunt Info</li>
	
	
						<g:if test="${huntInstance?.startDate}">
							
								<dt>
									<g:message code="hunt.startDate.label" default="Start Date" />
								</dt>
	
								<dd>
								
									<div class="input-prepend datepicker">
										<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
										<input style="width:160px;" data-format="MM/dd/yyyy HH:mm PP" type="text" id="startDate" name="start" placeholder="MM/dd/yyyy HH:mm:ss" value="${params?.start}" required/>
									</div>
						
								</dd>
							
						</g:if>
	
						<g:if test="${huntInstance?.endDate}">
							
								<dt>
									<g:message code="hunt.endDate.label" default="End Date" />
								</dt>
	
								<dd>
									<div class="input-prepend datepicker">
										<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"></i></span>
										<input style="width:160px;" data-format="MM/dd/yyyy HH:mm PP" type="text" id="endDate" name="end" placeholder="MM/dd/yyyy HH:mm:ss" value="${params?.end}" required/>
									</div>
								</dd>
							
						</g:if>
	
						<dt>
							<g:message code="hunt.isPrivate.label" default="Privacy" />
						</dt>

						<dd>
							<div class="switch" data-on-label="Private" data-off-label="Public" id="privacy">
								<g:checkBox name="isPrivate" value="${huntInstance?.isPrivate}" />
							</div>
						</dd>
	
						<g:if test="${huntInstance?.key}">
							
								<dt>
									<g:message code="hunt.key.label" default="Key" />
								</dt>
	
								<dd>
									<g:fieldValue bean="${huntInstance}" field="key" />
								</dd>
							
						</g:if>
	
						
	
						<g:if test="${huntInstance?.myCreator}">
							<dt>
								<g:message code="hunt.myCreator.label" default="My Creator" />
							</dt>
							<dd>
								<g:link controller="user" action="show" params="[login:huntInstance.myCreator.login]" >
									<i class="icon-user"></i>
									${huntInstance?.myCreator?.encodeAsHTML()}
								</g:link>
							</dd>
						</g:if>
						<g:if test="${huntInstance?.myAdmins}">
							<dt>
								Hunt Admins
							</dt>
							<g:each in="${huntInstance?.myAdmins}" var="admin">
							<dd>
								<g:link controller="user" action="show" params="[login:admin.login]">
									<i class="icon-user"></i>
									${admin.login}
								</g:link>
								</dd>
							</g:each>
						</g:if>					
						<g:if test="${huntInstance?.myUsers}">
							<dt>
								Hunt Participants
							</dt>
							
							<g:each in="${huntInstance?.myUsers}" var="user">
								<dd>
								<g:link controller="user" action="show" params="[login:user.login]" >
									<i class="icon-user"></i>
									${user?.login}
								</g:link>
								</dd>
							</g:each>
							
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