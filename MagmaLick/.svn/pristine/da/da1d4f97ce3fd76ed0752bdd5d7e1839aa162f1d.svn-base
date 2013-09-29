
<%@ page import="scavengr.Photo"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'bootstrap-lightbox.less')}"
	type="text/css">
<script type="text/javascript"
	src="${resource(dir: 'js', file: 'bootstrap-lightbox.js')}"></script>

<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'photo.label', default: 'Photo')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<script type="text/javascript">
			$(document).ready(function() {
				$(document).keydown(function(e) {
					if (e.keyCode == 37) {
						if (typeof $('#previousPicture').attr('href') != 'undefined' ) {
							window.location = $('#previousPicture').attr('href');
						}
					}
					if (e.keyCode == 39){
						if (typeof $('#nextPicture').attr('href') != 'undefined' ) {
							window.location = $('#nextPicture').attr('href');
						}
					}
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
						${photoInstance?.title}
					</h1>
					<g:if test="${photoInstance?.description}">
						<h5>
							<g:fieldValue bean="${photoInstance}" field="description" />
						</h5>
					</g:if>
					<%--					<g:if test="${participantInstance?.name}">--%>
					<%--						<h5><g:fieldValue bean="${participantInstance}" field="name"/></h5>--%>
					<%--					</g:if>--%>
				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">
						${flash.message}
					</bootstrap:alert>
				</g:if>
				<div id="photo" style="text-align: center;">
					<a class="thumbnail" data-toggle="lightbox" href="#photoLightbox">
						<bi:img size="large" bean="${photoInstance}" />
					</a>
				</div>
				<div id="photoLightbox" class="lightbox hide fade" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class='lightbox-header'>
						<button type="button" class="close" data-dismiss="lightbox"
							aria-hidden="true">&times;</button>
					</div>
					<div class='lightbox-content'>
						<img
							src="${createLink(controller:'photo', action:'viewImage', id: photoInstance?.id, params:[size:original])}">
					</div>
					<div class='lightbox-footer'>
						<%--					        <button class="btn btn-small btn-primary">Click</button>--%>
					</div>
				</div>
				<ul class="pager">
					<g:if test="${prevId}">
						<li class="previous"><g:link action="show" id="${prevId}"
								elementId="previousPicture">
							&larr; Previous
							</g:link></li>
					</g:if>
					<g:else>
						<li class="previous disabled"><a id="previousPicture">
								&larr; Previous </a></li>
					</g:else>

					<g:if test="${session.hunter == null }">

						<auth:ifLoggedIn>

							<li id="favButton"><a id="favLink"
								onclick="${remoteFunction(action:'toggleFavorite',params:['id':photoInstance.id], update:'favLink')}">
									<g:if test="${!isFavorite}">
										<i id="favorited" class="icon icon-star"> </i>
								Favorite
							</g:if> <g:else>
										<i id="favorited" class="icon icon-star-empty"> </i>
								Unfavorite
							</g:else>
							</a></li>

						</auth:ifLoggedIn>
					</g:if>


					<g:if test="${nextId}">
						<li class="next"><g:link action="show" id="${nextId}"
								elementId="nextPicture">
							Next &rarr;
							</g:link></li>
					</g:if>
					<g:else>
						<li class="next disabled"><a id="nextPicture"> Next
								&rarr; </a></li>
					</g:else>
				</ul>
			</div>

			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">Photo Info</li>
						<dt>Photo Uploader</dt>
						<g:if test="${photoInstance?.myHunter != null }">
							<dd>
								<p>
									${photoInstance.myHunter }
								</p>
							</dd>
						</g:if>
						<g:else>
							<dd>
								<g:if test="${photoInstance?.myUser}">
									<g:link controller="user" action="show"
										params="[login: photoInstance.myUser]">
										${photoInstance?.myUser}
									</g:link>
								</g:if>
								<g:else>
									Anonymous
									${photoInstance?.myUser}
								</g:else>
							</dd>
						</g:else>
						<g:if test="${photoInstance?.dateCreated}">
							<dt>Upload Date</dt>
							<dd>
								<g:formatDate date="${photoInstance?.dateCreated}"
									type="datetime" style="LONG" timeStyle="SHORT" />
							</dd>
						</g:if>
						<dt>Views</dt>
						<dd>
							${photoInstance.views}
						</dd>
						<g:if test="${photoInstance?.myPrompt}">
							<g:if test="${!photoInstance?.myPrompt?.myHunt?.isPrivate}">
								<dt>Hunt</dt>
								<dd>
									<g:link controller="hunt" action="show"
										params="[key:photoInstance?.myPrompt?.myHunt?.key]">
										${photoInstance?.myPrompt.myHunt.title}
									</g:link>
								</dd>
								<dt>Prompt</dt>
								<dd>
									<g:link controller="prompt" action="show" mapping="prompt"
										params="[key:photoInstance?.myPrompt.myHunt?.key, id: photoInstance?.myPrompt.id]">
										${photoInstance?.myPrompt.title}
									</g:link>
								</dd>
								<g:if test="${photoInstance?.myPrompt?.description}">
									<dt>Prompt Description</dt>
									<dd>
										${photoInstance?.myPrompt?.description}
									</dd>
								</g:if>
							</g:if>
						</g:if>
					</ul>
					<g:if test="${isMyPhoto && session.hunter == null}">
						<g:form>
							<g:hiddenField name="id" value="${photoInstance?.id}" />
							<div class="form-actions">
								<g:link class="btn" action="edit" id="${photoInstance?.id}">
									<i class="icon-pencil"></i>
									<g:message code="default.button.edit.label" default="Edit" />
								</g:link>
								<button class="btn btn-danger" type="submit"
									name="_action_delete">
									<i class="icon-trash icon-white"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</button>
							</div>
						</g:form>
					</g:if>

				</div>
			</div>

		</div>
	</div>
</body>
</html>
