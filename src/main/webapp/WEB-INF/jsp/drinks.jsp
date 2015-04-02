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
		
		
		function drawProcess(id) {
			var el = document.getElementById(id); // get canvas

			var options = {
			    percent:  el.getAttribute('data-percent') || 25,
			    size: el.getAttribute('data-size') || 220,
			    lineWidth: el.getAttribute('data-line') || 15,
			    rotate: el.getAttribute('data-rotate') || 0
			}

			var canvas = document.createElement('canvas');
			var span = document.createElement('span');
			span.textContent = options.percent + '%';
			    
			if (typeof(G_vmlCanvasManager) !== 'undefined') {
			    G_vmlCanvasManager.initElement(canvas);
			}

			var ctx = canvas.getContext('2d');
			canvas.width = canvas.height = options.size;

			el.appendChild(span);
			el.appendChild(canvas);

			ctx.translate(options.size / 2, options.size / 2); // change center
			ctx.rotate((-1 / 2 + options.rotate / 180) * Math.PI); // rotate -90 deg

			//imd = ctx.getImageData(0, 0, 240, 240);
			var radius = (options.size - options.lineWidth) / 2;

			var drawCircle = function(color, lineWidth, percent) {
					percent = Math.min(Math.max(0, percent || 1), 1);
					ctx.beginPath();
					ctx.arc(0, 0, radius, 0, Math.PI * 2 * percent, false);
					ctx.strokeStyle = color;
			        ctx.lineCap = 'round'; // butt, round or square
					ctx.lineWidth = lineWidth;
					ctx.stroke();
			};
			drawCircle('#efefef', options.lineWidth, 100 / 100);
			drawCircle('#97A71D', options.lineWidth, options.percent / 100);
		}
		
		drawProcess("cup1");
		drawProcess("cup2");
		
	});
</script>

<style>
#header {
	display: none;
}

#navbar {
	display: none;
}

.circle {
    position:relative;
    margin:80px;
    width:220px; height:220px;
    float: left;
}
canvas {
    display: block;
    position:absolute;
    top:0;
    left:0;
}
span {
    color:#555;
    display:block;
    line-height:220px;
    text-align:center;
    width:220px;
    font-family:sans-serif;
    font-size:40px;
    font-weight:100;
    margin-left:5px;
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
					<div class="circle" id="cup1" data-percent="88"></div>
					<div class="circle" id="cup2" data-percent="2"></div>
				</ul>
				
				<div class="formGroupHead"></div>
				
			</div>
		</div>
	</div>
</body>
</html>