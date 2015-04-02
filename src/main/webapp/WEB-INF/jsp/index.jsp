<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--HTML5 doctype-->
<html>
<head>
<title>Eggplant Health Manager</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes" />
<link rel="stylesheet" type="text/css" href="/healthmanager/resources/css/icons.css" />
<link rel="stylesheet" type="text/css" href="/healthmanager/resources/css/afui.custom.css" />
<script type="text/javascript" charset="utf-8"
	src="/healthmanager/resources/js/appframework.ui.min.js"></script>
<script type="text/javascript">
	
	 
	$.ui.ready(function() {
		$.ui.toggleHeaderMenu();
		$.feat.nativeTouchScroll = false; //Disable native scrolling globally
		$.ui.removeFooterMenu();
		
		var testUid = "test1234";
		
		$('#drinkBtn').on('click', function(e) {
			var opts = {
				type : "POST",
				success : function(data) {
					$("#currentDay").html(data.obj.time);
					$("#total").html(data.obj.total);
				},
				url : "/healthmanager/rest/drinkrecord/add/" + testUid,
				data : null,
				contentType : "application/json",
				dataType : "json"
			}
			$.ajax(opts);
		});
		
		$('#historyBtn').on('click', function(e) {
			
			$('#drinkBtn').hide();
			var opts = {
				type : "GET",
				success : function(data) {
					$("#searchResultBox").empty();
					var result = "";
					$.each(data,function(id, element){
						result += "<li>" + element.time + " : " + element.total  + "杯" + "</li>";
					});
					$("#searchResultBox").html(result);
				},
				url : "/healthmanager/rest/drinkrecord/history/" + testUid,
				data : null,
				contentType : "application/json",
				dataType : "json"
			}
			$.ajax(opts);
		});
		
		$('#todayBtn').on('click', function(e) {
			
			$('#drinkBtn').show();
			var opts = {
				type : "GET",
				success : function(data) {
					$("#searchResultBox").empty();
					var result = "";
					result += '<li id="uid">' + data.uid + '</li>';
					result += '<li id="currentDay">' + data.time + '</li>';
					result += '<li id="total">' + data.total + '</li>';
					$("#searchResultBox").html(result);
				},
				url : "/healthmanager/rest/drinkrecord/today/" + testUid,
				data : null,
				contentType : "application/json",
				dataType : "json"
			}
			$.ajax(opts);
		});
		

	});
</script>

<style>
#header {
	display: none;
}

#navbar {
	display: none;
}
</style>
</head>

<body>
	<div id="afui">
		<div id="content">
			<div class="panel" js-scrolling="true">
				<div class="formGroupHead">
					<div class="button-grouped flex">
				    	<a class="button icon home" id="todayBtn">今日 </a>
				    	<a class="button red" id="historyBtn">以往 </a>
				  	</div>
				</div>
				<a class="button" style="width:100%" id="drinkBtn">喝一杯</a>
				<ul class="list inset" id="searchResultBox">
					<li id="uid">${record.uid}</li>
					<li id="currentDay">${record.time}</li>
					<li id="total">${record.total}</li>
				</ul>
				
				<div class="formGroupHead"></div>
				
			</div>
		</div>
	</div>
</body>
</html>