
<%@ page import="scavengr.Hunt"%>
<%@ page import="scavengr.Prompt"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('#admin-login').typeahead({
				source:${userLoginList}
			}
		);
		$('#user').typeahead({
				source:${userLoginList}
			}
		);
		
		$('#new-prompt').submit(function() {
			if ($('#title').val() != '') {
				$('#new-prompt').submit();
			} else {
				$('#title').focus();
				return false;
			}
		});

		$('#new-participant').submit(function() {
			if ($('#email').val() != '') {
				$('#new-participant').submit();
			} else {
				$('#email').focus();
				return false;
			}
		});

		$('#new-admin').submit(function() {
			if ($('#admin-login').val() != '') {
				$('#new-admin').submit();
			} else {
				$('#admin-login').focus();
				return false;
			}
		});
	});

	
</script>


<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'hunt.label', default: 'Hunt')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">



			<div class="span9">
				<g:if test="${session.hunter == null}">
					<g:if test="${isCreatorOrAdmin}">
						<g:form>
							<g:hiddenField name="id" value="${huntInstance?.id}" />
							<div class="pull-right">

								<g:link class="btn" action="edit" id="${huntInstance?.id}">
									<i class="icon-pencil"></i>
								Edit Hunt
							</g:link>
								<g:if test="${userInstance == huntInstance.myCreator}">
									<br />
									<button class="btn btn-danger" type="submit"
										name="_action_delete" style="margin-top: 8px;">
										<i class="icon-trash icon-white"></i> Delete Hunt
									</button>
								</g:if>
							</div>
						</g:form>
					</g:if>
				</g:if>
				<g:else>
					<div class="pull-right">
						<h3>
							Hunter: ${session.hunter}
						</h3>
						<g:form class="form-horizontal" name="normalMode" controller="hunt" action="normalMode">
							<input name="password" placeholder="Administrator Password" type="password" />
							<g:hiddenField name="key" value="${huntInstance?.key }" />
							<button type="submit" class="btn btn-primary">
								<i class="icon-unlock"></i>Unlock
							</button>
						</g:form>
					</div>
				</g:else>
				<div class="page-header">
					<h1>
						<g:fieldValue bean="${huntInstance}" field="title" />
					</h1>

					<h4>
						<g:fieldValue bean="${huntInstance}" field="description" />
					</h4>


				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">
						${flash.message}
					</bootstrap:alert>
				</g:if>


				<g:each in="${promptPhotoList}" var="promptPhotoContainer">
					<div class="row-fluid prompt-row">
						<div class="span4">
							<g:if test="${promptPhotoContainer[1] && (session.hunter == null || promptPhotoContainer[0].sessionSuccess(session.hunter))}">
								<g:link controller="prompt" action="show" mapping="prompt"
									params="[key:huntInstance?.key]"
									id="${promptPhotoContainer[0].id}"
									class="btn btn-prompt btn-success">
									<g:fieldValue bean="${promptPhotoContainer[0]}" field="title" />
									<i class="icon-ok"></i>
								</g:link>
							</g:if>
							<g:else>
								<g:link controller="prompt" action="show" mapping="prompt"
									params="[key:huntInstance?.key]"
									id="${promptPhotoContainer[0].id}"
									class="btn btn-prompt btn-primary">
									<g:fieldValue bean="${promptPhotoContainer[0]}" field="title" />
								</g:link>
							</g:else>
						</div>
						<div class="span8 small-photo">
							<g:each in="${promptPhotoContainer}" var="photoInstance"
								status="i">
								<g:if test="${ i > 1 }">
									<g:if test="${photoInstance?.myUser == userInstance && (session.hunter == null || photoInstance?.myHunter == session.hunter)}">
										<div class="span2" style="position: relative;">
											<i class="icon-white icon-user user-photo"></i> <a
												class="thumbnail"
												href="${createLink(controller:'photo', action:'show', id: photoInstance?.id)}">

												<bi:img size="small" bean="${photoInstance}" />

											</a>
										</div>
									</g:if>
									<g:else>
										<div class="span2 ">
											<div>
												<a class="thumbnail"
													href="${createLink(controller:'photo', action:'show', id: photoInstance?.id)}">
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
								<g:formatDate date="${huntInstance?.startDate}" type="datetime"
									dateStyle="LONG" />
							</dd>

						</g:if>

						<g:if test="${huntInstance?.endDate}">

							<dt>
								<g:message code="hunt.endDate.label" default="End Date" />
							</dt>

							<dd>
								<g:formatDate date="${huntInstance?.endDate}" type="datetime"
									dateStyle="LONG" />
							</dd>

						</g:if>

						<g:if test="${session.hunter == null}">
							<dt>
								<g:message code="hunt.isPrivate.label" default="Privacy" />
							</dt>


							<g:if test="${huntInstance?.isPrivate}">
								<dd>
									<i class="icon-lock"> </i> Private
								</dd>
							</g:if>
							<g:else>
								<dd>
									<i class="icon-unlock"> </i> Public
								</dd>
							</g:else>


							<g:if test="${huntInstance?.key}">

								<dt>
									<g:message code="hunt.key.label" default="Key" />
								</dt>

								<dd>
									<g:fieldValue bean="${huntInstance}" field="key" id="huntKey" />
								</dd>

							</g:if>



							<g:if test="${huntInstance?.myCreator}">

								<dt>Hunt Creator</dt>

								<dd>
									<g:link controller="user" action="show"
										params="[login:huntInstance.myCreator.login]">
										<i class="icon-user"></i>
										${huntInstance?.myCreator?.encodeAsHTML()}
									</g:link>
								</dd>

							</g:if>

							<g:if test="${huntInstance?.myAdmins}">
								<dt>Hunt Admins</dt>
								<g:each in="${huntInstance?.myAdmins}" var="admin">
									<dd>
										<g:link controller="user" action="show"
											params="[login:admin.login]">
											<i class="icon-user"> </i>
											${admin?.login}
										</g:link>
										<g:if test="${userInstance == huntInstance?.myCreator}">
											<g:link class="pull-right" style="text-decoration:none;"
												action="removeAdmin"
												params="['login':admin?.login, 'myHunt.id':huntInstance?.id]">
												<i class="icon-remove"></i>
											</g:link>
										</g:if>
									</dd>
								</g:each>
							</g:if>
							<g:if test="${huntInstance?.myUsers}">
								<g:if test="${isParticipating || isCreatorOrAdmin}">
									<dt>Hunt Participants</dt>

									<g:each in="${huntInstance?.myUsers}" var="user">
										<dd>
											<g:link controller="user" action="show"
												params="[login:user.login]">
												<i class="icon-user"></i>
												${user?.login}
											</g:link>
											<g:if test="${isCreatorOrAdmin}">
												<g:link class="pull-right" style="text-decoration:none;"
													action="removeUser"
													params="['login':user?.login, 'myHunt.id':huntInstance?.id]">
													<i class="icon-ban-circle"></i>
												</g:link>
											</g:if>
										</dd>
									</g:each>
								</g:if>
							</g:if>
							<g:if test="${huntInstance?.emails}">
								<g:if test="${isCreatorOrAdmin}">
									<dt>
										<g:message code="hunt.emails.label"
											default="Invited Participants" />
									</dt>
									<g:each in="${huntInstance?.emails}" var="participant">
										<dd>
											${participant}
										</dd>
									</g:each>
								</g:if>
							</g:if>
							<g:if test="${isCreatorOrAdmin}">
								<li class="divider"></li>
								<li class="nav-header">Admin Options</li>

								<dt>Child Lock</dt>
								<p>Enter a child's name so that all photos uploaded are
									associated with them, and they cannot leave this hunt.</p>
								<g:form name="hunterMode" controller="hunt" action="hunterMode">
									<g:textField name="hunter" placeholder="Name" />
									<g:hiddenField name="key" value="${huntInstance?.key }" />
									<button type="submit" class="btn btn-primary">
										<i class="icon-lock"></i>Lock In
									</button>
								</g:form>

								<dt>Add new Prompts</dt>
								<g:form name="new-prompt" controller="prompt" action="create">

									<g:textField name="title" placeholder="Title" />
									<br />
									<g:textArea name="description" placeholder="Description" />
									<g:hiddenField name="myHunt.id" value="${huntInstance.id}" />
									<button id="new-prompt-submit" type="submit"
										class="btn btn-primary">
										<i class="icon-plus icon-white"></i>Add Prompt
									</button>
								</g:form>

								<dt>Add new Participants</dt>
								<g:form name="new-participant" controller="hunt" action="invite">
									<input type="text" id="user" data-provide="typeahead"
										name="user" autocomplete="off"
										placeholder="Email Address or Username" />
									<g:hiddenField name="id" value="${huntInstance.id}" />
									<button id="new-participant-submit" type="submit"
										class="btn btn-primary">
										<i class="icon-envelope icon-white"></i>Invite
									</button>
								</g:form>

								<dt>Add new Administrators</dt>
								<g:form name="new-admin" controller="hunt" action="inviteAdmin">
									<input type="text" data-provide="typeahead" autocomplete="off"
										id="admin-login" name="login" placeholder="Username" />
									<g:hiddenField name="id" value="${huntInstance.id}" />
									<button id="new-admin-submit" type="submit"
										class="btn btn-primary">
										<i class="icon-plus icon-white"></i>Add
									</button>
								</g:form>
								<g:form>
									<g:hiddenField name="id" value="${huntInstance?.id}" />
									<div class="control-group">
										<button class="btn btn-primary" name="_action_downloadPhotos">
											<i class="icon-download icon-white"></i> Download Photos
										</button>
									</div>
									<g:if test="${huntInstance.endDate > currentDate}">
										<div class="control-group">
											<button type="submit" class="btn btn-warning"
												name="_action_closeHunt">
												<i class="icon-stop"></i> Close Hunt
											</button>
										</div>
									</g:if>
								</g:form>
							</g:if>
						</g:if>
					</ul>



				</div>
			</div>


		</div>
	</div>
</body>
</html>
