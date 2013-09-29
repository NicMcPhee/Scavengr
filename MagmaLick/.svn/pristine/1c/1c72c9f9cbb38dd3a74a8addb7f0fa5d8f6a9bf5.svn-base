
<%@ page import="scavengr.User"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'user.label', default: 'User')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
<script type="text/javascript">
	$(document).ready(function(){
		$('#myTab a:first').tab('show');
		// Javascript to enable link to tab
		var hash = document.location.hash;
		var prefix = "";
		if (hash) {
		    $('.nav-tabs a[href='+hash.replace(prefix,"")+']').tab('show');
		} 

		// Change hash for page-reload
		$('.nav-tabs a').on('shown', function (e) {
		    window.location.hash = e.target.hash.replace("#", "#" + prefix);
		});

		
	});
</script>
</head>
<body>
<div class="container">
	<div class="row-fluid">



		<div class="span9">

			<div class="page-header">
				<h1>
					<g:fieldValue bean="${userInstance}" field="login" />
				</h1>
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>
			<div class="row-fluid">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#myPhotos" data-toggle="tab">Photos</a></li>
					<li><a href="#favorites" data-toggle="tab">Favorites</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="myPhotos">
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
							<bootstrap:paginate fragment="myPhotos" mapping="user" params="[login: userInstance?.login]" total="${photoInstanceTotal}" />
						</div>
					</div>
					
					
					<div class="tab-pane" id="favorites">
						<ul class="thumbnails">
							<g:each in="${favoriteInstanceList}" var="favorite">
								<li class="span3">
									<a class="thumbnail" href="${createLink(controller:'photo', action:'show', id: favorite?.id)}">
										<bi:img size="medium" bean="${favorite}" />
									</a>
								</li>
							</g:each>
						</ul>
						<div class="pagination">
							<bootstrap:paginate fragment="favorites" mapping="user" params="[login: userInstance?.login]" total="${favoriteInstanceTotal}" />
						</div>
					</div>
				</div>
			
				
			</div>
		</div>

		<div class="span3">
			<div class="well">
				<ul class="nav nav-list">
					<g:if test="${isLoggedInUser}">
						<li class="nav-header">Actions</li>
						<li>
							<g:link id="createHunt" class="create" controller="hunt" action="create">
								<i class="icon-plus"></i>
								Create Hunt
							</g:link>
							<g:link id="changePassword" fragment="changeModal" data-toggle="modal">
								<i  class="icon-key"></i>
								Change Password
							</g:link>
							<g:link id="changeEmail" fragment="emailModal" data-toggle="modal">
								<i class="icon-envelope"></i>
								Change Email
							</g:link>
							<g:link id="downloadPhotos" action="downloadPhotos">
								<i class="icon-download"></i>
								Download My Photos
							</g:link>
						</li>
						<li class="divider"></li>
					</g:if>
					<g:if test="${userInstance?.myCreatedHunts}">
						<g:if test="${isLoggedInUser}">
							<li class="nav-header">My Created Hunts</li>
						</g:if>
						<g:else>
							<g:if test="${publicCreatedHuntInstanceList}">
								<li class="nav-header">Created Hunts</li>
							</g:if>
						</g:else>
						
						<g:each in="${publicCreatedHuntInstanceList}" var="huntInstance" >
							<li>
								<g:link class="list" action="show" controller="hunt" params="[key:huntInstance.key]">
									<i class="icon-search"></i>
									<g:fieldValue bean="${huntInstance}" field="title" />
								</g:link>
							</li>
						</g:each>
						<g:if test="${isLoggedInUser}">
							<g:each in="${privateCreatedHuntInstanceList}" var="huntInstance" >
								<li>
									<g:link class="list" action="show" controller="hunt" params="[key:huntInstance.key]">
										<i class="icon-search"></i>
										<g:fieldValue bean="${huntInstance}" field="title" />
									</g:link>
								</li>
							</g:each>
						</g:if>
					</g:if>
					<g:if test="${userInstance?.myAdministratedHunts}">
						<g:if test="${isLoggedInUser}">
							<li class="nav-header">Hunts I Administrate</li>
						</g:if>
						<g:else>
							<g:if test="${publicAdministratedHuntInstanceList}">
								<li class="nav-header">Admin of Hunts</li>
							</g:if>
						</g:else>
						<g:each in="${publicAdministratedHuntInstanceList}" var="huntInstance">
							<li><g:link class="list" action="show" controller="hunt" params="[key:huntInstance.key]">
								<i class="icon-search"></i>
									<g:fieldValue bean="${huntInstance}" field="title" />
							</g:link></li>
						</g:each>
						
						<g:if test="${isLoggedInUser}">
							<g:each in="${privateAdministratedHuntInstanceList}" var="huntInstance">
								<li><g:link class="list" action="show" controller="hunt" params="[key:huntInstance.key]">
									<i class="icon-search"></i>
										<g:fieldValue bean="${huntInstance}" field="title" />
								</g:link></li>
							</g:each>
						</g:if>
					</g:if>
					<g:if test="${userInstance?.myHunts}">
						<g:if test="${isLoggedInUser}">
							<li class="nav-header">My Hunt Participation</li>
						</g:if>
						<g:else>
							<g:if test="${publicHuntParticipationList}">
								<li class="nav-header">Hunt Participation</li>
							</g:if>
						</g:else>
						<g:each in="${publicHuntParticipationList}" var="huntInstance" >
							<li><g:link class="list" action="show" controller="hunt" params="[key:huntInstance.key]">
								<i class="icon-search"></i>
									<g:fieldValue bean="${huntInstance}" field="title" />
							</g:link></li>
						</g:each>
						<g:if test="${isLoggedInUser}">
							<g:each in="${privateHuntParticipationList}" var="huntInstance" >
								<li><g:link class="list" action="show" controller="hunt" params="[key:huntInstance.key]">
									<i class="icon-search"></i>
									<g:fieldValue bean="${huntInstance}" field="title" />
								</g:link></li>
							</g:each>
						</g:if>
					</g:if>
				</ul>
			</div>
		</div>
		</div>
	</div>
</body>
</html>
