<%@ page
	import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><g:layoutTitle default="${meta(name: 'app.name')}" /></title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport" content="initial-scale = 1.0">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

<r:require modules="scaffolding" />

<!-- Le fav and touch icons -->
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.gif')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'favicon.gif')}">
<%--<link rel="apple-touch-icon" sizes="72x72" href="${resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">--%>

<r:layoutResources />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'font-awesome.min.css')}"
	type="text/css">


<g:layoutHead />

</head>

<body>

	<nav class="navbar navbar-fixed-top ">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<g:if test="${session.hunter == null}">
					<a class="brand" href="${createLink(uri: '/')}">Scavengr</a>
				</g:if>
				<g:else>
					<a class="brand">Scavengr</a>
				</g:else>


				<div class="nav-collapse">
					<g:if test="${session.hunter == null}">
						<ul class="nav">


							<%--							<g:if env="development">--%>
							<%--							<li<%= request.forwardURI == "${createLink(uri: '/photo/list')}" ? ' class="active"' : '' %>><g:link controller="photo">List of Photos</g:link></li>--%>
							<%--							<li<%= request.forwardURI == "${createLink(uri: '/prompt/list')}" ? ' class="active"' : '' %>><g:link controller="prompt">List of Prompts</g:link></li>--%>
							<%--							<li<%= request.forwardURI == "${createLink(uri: '/user/list')}" ? ' class="active"' : '' %>><g:link controller="user">List of Users</g:link></li>--%>
							<%--							</g:if>--%>
							<auth:ifLoggedIn>
								<li
									<%= request.forwardURI == "${createLink(uri: '/hunt/list')}" ? ' class="active"' : '' %>><g:link
										controller="hunt">Public Hunts</g:link></li>
								<li><g:link controller="hunt" action="create"
										id="navbarCreateButton">Create A Hunt</g:link></li>
							</auth:ifLoggedIn>
						</ul>
						<ul class="nav pull-right">
							<auth:ifLoggedIn>
								<li><g:render template="/shared/notifications" /> <g:if
										test="${messages}">
										<a id="mail"> <span id="message-indicator"
											class="badge badge-success"> <i class="icon-envelope"></i>
												<span id="messages"> ${numMessages}
											</span>
										</span>
										</a>
									</g:if> <g:else>
										<a> <span class="badge"> <i class="icon-envelope"></i>
												0
										</span>
										</a>
									</g:else></li>
								<li><g:link controller="user" action="myProfile">Hello, <auth:user
											id="username" />
									</g:link></li>
								<li><auth:logoutLink
										success="[controller:'index', action:'index']"
										error="[controller:'index', action:'index']">Log out</auth:logoutLink></li>
							</auth:ifLoggedIn>
							<auth:ifNotLoggedIn>
								<li><g:link fragment="loginModal" data-toggle="modal">Log In</g:link></li>
								<li><g:link fragment="resetModal" data-toggle="modal">Forgot Password?</g:link></li>
							</auth:ifNotLoggedIn>
						</ul>
					</g:if>

				</div>
			</div>
		</div>
	</nav>

	<g:layoutBody />
	<r:layoutResources />
	<g:render template="/shared/modals" />

	<hr>
	<div class="container">
		<footer>
			<p>&copy; UMM 2013</p>
		</footer>
	</div>

	<%--		<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-modalmanager.js')}"></script>--%>
	<%--		<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-modal.js')}"></script>--%>

</body>
</html>