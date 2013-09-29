
<%@ page import="scavengr.Hunt" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'hunt.label', default: 'Hunt')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="container">
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
					<ul class="nav nav-list">
					<li class="nav-header">Search for Hunt</li>
					<g:form class="form-horizontal" name="join" url="[controller:'hunt']" method="post">
						<div class="control-group">
							<g:textField style="width:60%;" name="key" placeholder="Enter Key" />
							<g:actionSubmit value="Go!" class="btn btn-primary" action="show" />
						</div>
					</g:form>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1>Public Hunts</h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="title" title="${message(code: 'hunt.title.label', default: 'Title')}" />
						
							<g:sortableColumn property="description" title="${message(code: 'hunt.description.label', default: 'Description')}" />
						
							<g:sortableColumn property="startDate" title="${message(code: 'hunt.startDate.label', default: 'Start Date')}" />
						
							<g:sortableColumn property="endDate" title="${message(code: 'hunt.endDate.label', default: 'End Date')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${huntInstanceList}" var="huntInstance">
						<tr>
						
							<td>
							<g:link action="show" params="[key:huntInstance.key]">
							${fieldValue(bean: huntInstance, field: "title")}
							</g:link>
							</td>
						
							<td>
							<g:link action="show" params="[key:huntInstance.key]">
							${fieldValue(bean: huntInstance, field: "description")}
							</g:link>
							</td>
						
							<td><g:formatDate date="${huntInstance.startDate}" type="date" dateStyle="LONG"/></td>
						
							<td><g:formatDate date="${huntInstance.endDate}" type="date" dateStyle="LONG"/></td>
						
							
						
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${huntInstanceTotal}" />
				</div>
			</div>

		</div>
		</div>
	</body>
</html>
