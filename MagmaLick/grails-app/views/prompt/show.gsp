
<%@ page import="scavengr.Prompt"%>
<!doctype html>
<html>
<head>
<%--<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap.file-input.js')}"></script>--%>
<script type="text/javascript">



</script>

<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'prompt.label', default: 'Prompt')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="container">
	<div class="row-fluid">

		<div class="span3">
			<div class="well">
			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>
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
							<input style="width:90%" required type="file" name="myFile"/>
							<label for="title">Title</label>
							<input type="text" id="title" name="title"/>
							<label for="description">Description</label>
							<input type="text" id="description" name="description"/>
							<g:hiddenField name="myUser.id" value="${userInstance?.id}" />
							<g:hiddenField name="myPrompt.id" id="myPrompt" value="${promptInstance.id}"/>
						</div>
							
							<div class="control-group">
								<g:link controller="hunt" action="show" params="[key:promptInstance?.myHunt?.key]" class="btn">
									<i class="icon-arrow-left icon-black"></i>
									<g:message code="default.button.back.label" default="Back to Hunt" />
								</g:link>
								
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.upload.label" default="Upload" />
								</button>
							</div>
						</fieldset>
					</g:form>
					
				</fieldset>
				</g:else>
			</div>
			
			<g:if test="${isCreatorOrAdmin && session.hunter == null}">
			<div class="well">
				<h2>Admin Options</h2>
				<g:form>
					<g:hiddenField name="id" value="${promptInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${promptInstance?.id}"  mapping="editPrompt" params="[key:promptInstance?.myHunt?.key, id:promptInstance?.id]">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit"/>
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>
				</div>
			</g:if>
		</div>

		<div class="span9">

			<div class="page-header">
				<h1>
					${promptInstance?.title}
				</h1>
				<g:if test="${promptInstance?.description}">
					<h5>
						<g:fieldValue bean="${promptInstance}" field="description" />
					</h5>
				</g:if>
			</div>

			

			<div class="row-fluid" id="prompt-photos">
				<ul class="thumbnails">
					<g:each in="${photoInstanceList}" var="photoInstance">
						<li class="span3">
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
	</div>
</body>
</html>
