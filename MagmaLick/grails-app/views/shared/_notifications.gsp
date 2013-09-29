<script type="text/javascript">
	$(document).ready(function(){
		
		$('.notification-window').css('top', $(window).scrollTop()+50);
		$('#mail').click(function(){
			if($('#messages').html() > 0){
				$('#mail').popover({
					trigger: 'manual',
					placement: 'bottom',
					title: 'Notifications' + $('#dismissAll').html(),
					content: function(){
						return $('#notifications').html()
					},
					html:true,
					template: '<div class="popover notification-window"><div class="arrow"></div><div class="popover-inner "><h3 class="popover-title"></h3><div class="popover-content" style="padding:0;"><p></p></div></div></div>'
				}).data('popover').tip().css('z-index', 1030);
				//$('.notification-window').css('position', 'fixed')
				$('#mail').popover('toggle');
				
			}
		});
	});
	var removed = 0
	function hideMsg(id){
		$('#notification'+id).remove();
		$('.popover-content p > #notification'+id).slideUp(function(){
			$('#messages').html(${numMessages} - ++removed);
			if(${numMessages} - removed == 0){
				$('#mail').popover('destroy');
				$('#message-indicator').removeClass("badge-success");
			}
		});
	}
	function hideAll(){
		$('.popover-content').slideUp(function(){
			$('#mail').popover('destroy');
			$('#message-indicator').removeClass("badge-success");
			$('#messages').html(0);
		});
	}
</script>
<div id="dismissAll" hidden="true">
<g:remoteLink controller="notification" action="dismissAll" onSuccess="hideAll();"><span class="pull-right label label-warning">Dismiss All</span></g:remoteLink>
</div>
<div id="notifications" hidden="true">
	<g:each in="${messages}" var="msg">
		<div class="notification" id="notification${msg.id}" >
			<div class="pull-right" style="margin-left:10px;">
				<small class="pull-right">
				<g:formatDate date="${msg?.dateCreated}" format="MM/dd hh:mm a"/>
				</small>
				<br />
				
				<g:remoteLink controller="notification" action="dismiss" id="${msg.id}" onSuccess="hideMsg(${msg.id});"><span class="label label-info pull-right">Dismiss</span></g:remoteLink>
				<g:if test="${msg?.link}">
				<br />
				<a href="${msg?.link}" class="pull-right" style="margin-bottom:3px;"><span class="label label-success">${msg?.action}</span></a>
				</g:if>
			</div>
			<div>
				<label><b>${msg?.subject}</b></label>
				<small>${msg?.message}</small>
			</div>
			<hr style="margin-right:-14px;margin-left:-14px;margin-top:0;margin-bottom:0;"/>
		</div>
		
	</g:each>
</div>