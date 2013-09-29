
<%@ page import="scavengr.Hunt"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-datetimepicker.min.js')}" ></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrapSwitch.js')}"></script>
<link href="${resource(dir: 'css', file: 'bootstrap-datetimepicker.min.css')}" rel="stylesheet" />
<link href="${resource(dir: 'css', file: 'bootstrapSwitch.css') }" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(function() {

		$('.datepicker').datetimepicker({
			language: 'en',
			pick12HourFormat: true,
			pickSeconds: false
		});

		for (e in ${emails}) {
			addEmail(${emails}[e]);
		}

		for (p in ${prompts}){
			var title = ${prompts}[p].title;
			var description = ${prompts}[p].description;
			addPrompt(title, description);

		}
	});

	var promptCount = 0;
	function addPrompt(title, description) {
		if (typeof title == 'undefined') {
        	title = $('#promptTitle').val();
        }
        if (typeof description == 'undefined'){
        	description = $('#promptDescription').val();
        }
		if (title != ''){
	        var htmlId = 'prompt' + promptCount;
	        var templateHtml = '<div name="' + htmlId + '" id="' + htmlId + '" hidden="true" class="control-group">\n';
	        templateHtml += '<input type="text" name="myPrompts[' + promptCount + '].title" id="myPrompts[' + promptCount + '].title" value="' + title + '" />\n';
	        templateHtml += '<textarea name="myPrompts[' + promptCount + '].description" id="myPrompts[' + promptCount + '].description" > ' + description + ' </textarea> \n';
	        templateHtml += '<input type="hidden" name="myPrompts[' + promptCount + '].deleted" id="myPrompts[' + promptCount + '].deleted" value="false"/>';
	        templateHtml += '<button onclick="deletePrompt(this);" type="button" id="remove' + promptCount + '" class="remove-prompt-button btn btn-secondary" value="Remove"><i class="icon-remove icon-black"></i></button></div>\n';
	        $('#prompt-list').append(templateHtml);
	        $('#prompt-div').show();
	        $('#'+htmlId).slideDown();
	        var title = $('#promptTitle').val('');
	        var description = $('#promptDescription').val('');
	        promptCount++;
		}
		$('#promptTitle').focus();
    }
    
	var deletedPrompts = 0;
	function deletePrompt(button){
		var id = $(button).attr('id');
		var promptNumber = id.substr(id.length - 1);
		var divId = 'prompt' + promptNumber;
		var deletedId = 'myPrompts[' + promptNumber + '].deleted';
		$(document.getElementById(deletedId)).val('true');
		$(document.getElementById(divId)).slideUp(function() {
			deletedPrompts++;
			if (deletedPrompts == promptCount) {
				$('#prompt-div').hide();	
			}
		});
	}
	
    var emailCount = 0;
    function addEmail(email) {
        if (typeof email == 'undefined') {
        	email = $('#new-email').val();
        }
    	
    	if (email != ''){
	        var htmlId = 'email' + emailCount;
			var templateHtml = '<div class="control-group" hidden="true" name="' + htmlId + '" id="' + htmlId + '">\n';
			templateHtml += '<input type="text" id="emails[' + emailCount + ']" name="emails[' + emailCount + ']" value="' + email + '" />\n';
			templateHtml += '<button onclick="deleteEmail(this);" type="button" id="remove' + emailCount + '" class="remove-email-button btn btn-secondary" value="Remove">\n';
			templateHtml += '<i class="icon-remove icon-black"></i>\n</button>\n</div>\n';
			$('#email-list').append(templateHtml);
			$('#email-div').show();
			$('#'+htmlId).slideDown();
			$('#new-email').val('');
			emailCount++;
    	}
    	$('#new-email').focus();
    }

    var deletedEmails = 0;
    function deleteEmail(button) {
    	var id = $(button).attr('id');
		var emailNumber = id.substr(id.length - 1);
		var divId = 'email' + emailNumber;
		var textId = 'emails[' + emailNumber + ']';
		$(document.getElementById(textId)).val('');
		$(document.getElementById(divId)).slideUp(function() {
			deletedEmails++;
			if (deletedEmails == emailCount) {
				$('#email-div').hide();
			}
		});
    }

    $(document).ready(function() {
        $('.switch').tooltip();
    });
</script>

<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'hunt.label', default: 'Hunt')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>


<body>
	<div class="container">
	
	<fieldset>
		<g:form class="form-horizontal" action="create">


			<div class="row-fluid" >
				<div class="span12">
					<div class="page-header row-fluid">
						<div class="span10">
							<div id="hunt-create-label">
								<h1>
									<g:message code="default.create.label" args="[entityName]" />
								</h1>
							</div>
						</div>
						
						<div id="span1 hunt-create-button">
							<button id="createHuntButton" type="submit" class="btn btn-large btn-success" >
								<g:message code="default.button.create.label" default="Create" />
							</button>
						</div>
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
				</div>
			</div>


			<div class="row-fluid">

				<div class="span4 well">

					<h3>Info</h3>
					<fieldset>
						<label for="title">Title</label>
						<input type="text" name="title" id="title" value="${huntInstance?.title}" required/>
						<label for="description">Description</label>
						<g:textArea name="description" id="description" value="${huntInstance?.description}" />
						<label for="startDate">Start Date</label>
						<div class="input-prepend datepicker">
							<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
							<input data-format="MM/dd/yyyy HH:mm PP" type="text" id="startDate" name="start" placeholder="MM/dd/yyyy HH:mm" value="${params?.start}" required/>
						</div>
						<label for="endDate">End Date</label>
						<div class="input-prepend datepicker">
							<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"></i></span>
							<input data-format="MM/dd/yyyy HH:mm PP" type="text" id="endDate" name="end" placeholder="MM/dd/yyyy HH:mm" value="${params?.end}" required/>
						</div>
						
						<label for="isPrivate">Privacy</label>
						
						
						<div class="switch" data-on-label="Private" data-off-label="Public" data-toggle="tooltip" title="Click to toggle privacy." data-animation="true" data-placement="right" id="privacy">
							<g:checkBox name="isPrivate" value="true" id="toggle" />
						</div>

						
					</fieldset>


				</div>

				<div class="span4">



					<div id="prompt-info" class="well">
						<h3>Add Prompts</h3>
						<fieldset>
							<div class="control-group">
								<g:textField id="promptTitle" name="new-prompt-title" placeholder="Title" />
							</div>
							<div class="control-group">
								<g:textArea id="promptDescription" name="new-prompt-description"
									placeholder="Description"></g:textArea>

								<button onclick="addPrompt()" id="addPromptButton" value="Add Prompt" type="button"
									class="btn btn-primary">Add Prompt</button>
							</div>
						</fieldset>
					</div>

					<div id="prompt-div" class="well" hidden="true">
						<fieldset id="prompt-list">
						
						</fieldset>
					</div>
					
					<div>
					
					
					</div>
					

				</div>

				<div class="span4">

					<div id="user-info" class="well">
						<h3>Invite Participants</h3>

						<fieldset>
							<div class="control-group">
								<input type=email id="new-email" name="new-email"
									placeholder="Participant Email Address" />
								<button onclick="addEmail()" id="add-email-button" value="Add Participant"
									type="button" class="btn btn-primary">Invite</button>
							</div>
						</fieldset>

					</div>

					<div class="well" hidden="true" id="email-div">
					
						<fieldset id="email-list">
							
						</fieldset>
					
					</div>



				</div>

			</div>
		</g:form>
	</fieldset>
	
</div>
</body>
</html>
