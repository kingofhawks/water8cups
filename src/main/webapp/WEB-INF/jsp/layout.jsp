<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--HTML5 doctype-->
<html>
<head>
<title>茄子健康-8杯水</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- 
<link rel="stylesheet" type="text/css"
	href="/healthmanager/resources/css/icons.css" />
<link rel="stylesheet" type="text/css"
	href="/healthmanager/resources/css/afui.custom.css" /> -->
<link rel="stylesheet" type="text/css"
	href="/healthmanager/resources/css/cssreset-min.css" />	
<link rel="stylesheet" type="text/css"
	href="/healthmanager/resources/css/animate.min.css" />	
	
<link rel="stylesheet" type="text/css"
	href="/healthmanager/resources/css/layout.css" />
<script type="text/javascript" charset="utf-8"
	src="/healthmanager/resources/js/appframework.min.js"></script>
<script type="text/javascript">
console.log($(document).width() + "," + $(document).height());

	$(document).ready(function() {
		//$('#clickableCircleImg').css('margin-left', '100px');
		//$('#clickableCircleImg').css('margin-top', (118 - 85)/2+ "px");
		/**
		document.getElementById("clickableCircleImg").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("clickableCircleImg").style.marginTop =  (118 - 85)/2+ "px";
		
		document.getElementById("cupImgArea2").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea2").style.marginTop =  (118 - 85)/2+ "px";
		
		document.getElementById("cupImgArea3").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea3").style.marginTop =  (118 - 85)/2+ "px";
		
		document.getElementById("cupImgArea4").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea4").style.marginTop =  (118 - 85)/2+ "px";
		
		//document.getElementById("cupImgArea5").style.marginLeft =  ($(document).width()/2 - 85)/2+ "px";
		document.getElementById("cupImgArea5").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea5").style.marginTop =  (118 - 85)/2+ "px";
		
		document.getElementById("cupImgArea6").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea6").style.marginTop =  (118 - 85)/2+ "px";
		document.getElementById("cupImgArea7").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea7").style.marginTop =  (118 - 85)/2+ "px";
		document.getElementById("cupImgArea8").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
		document.getElementById("cupImgArea8").style.marginTop =  (118 - 85)/2+ "px";
		*/
		
	});
</script>

</head>

<body>
	<div class="main">
		<div id="contentHeader" style="margin:0 auto;">
			<div id="drinkMen">
			${drinkMen}人正在打卡
		</div>
		<div style="position:relative">
			<img id="waterCup" src="/healthmanager/resources/images/cup_${fn:length(records)}.png" style="position:absolute;top:50%;margin-top:-17px;height: 34px;width: 23px;" />
			<p id="contentHeaderText" style="vertical-align:middle; margin-left: 38px;">
			茄子君说：已经喝了
					${fn:length(records)}杯水了！剩下的${8-fn:length(records)}杯也要加油！记得每杯200毫升左右噢～
			</p>
					
		</div>
		</div>

		<div id="contentBody">
		
		<div id="floater"></div>
		<div id="content">
				
			<div style="height: 118px;">
				<div style="float: left; width: 33%; height: 118px;margin: 0px auto;">
				
					<div align="center" style="z-index: 103; " class="circleCenterStyle">
						<div style="vertical-align: middle;">
							<span class="cupTimeHour">8</span><span class="cupTimeMin">:30</span>
						</div>
					</div>
					<div style="z-index: 101;" class="circleCenterStyle">
						<img id="clickableCircleImg" src="/healthmanager/resources/images/circle_on.png" style="position: absolute;z-index: 102; width: 85px; height: 85px;" />
					</div>
				
				</div>
				<div style="float: left; width: 34%; height: 118px;">

						<div id="cupNumText2" align="center" style="z-index: 103; " class="circleCenterStyle" >
							<p class="cupText">2</p>
						</div>
						<div id="cupImgArea2" style="z-index: 101; " class="circleCenterStyle">
							<img id="cupCircle2"
								src="/healthmanager/resources/images/planet_1.png"
								class="circle" style="position: absolute; z-index: 2;" /> 
							<img
								class="cupStar" src="/healthmanager/resources/images/star_3.png"
								style="width: 119px; height: 88px; position: absolute; z-index: 3; top: -2px; left: -16px;" />
						</div>

					</div>
				<div style="float: left; width: 33%; height: 118px;">
				
					<div id="cupNumText3" align="center" style="z-index: 103; " class="circleCenterStyle">
						<p class="cupText">3</p>
					</div>
					<div id="cupImgArea3"  style="z-index: 101; " class="circleCenterStyle">
						<img id="cupCircle3" src="/healthmanager/resources/images/planet_3.png"  style="position: absolute; z-index: 2;" class="circle"/> 
						<img src="/healthmanager/resources/images/star_2.png"  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top: 52px; left: 13px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top: 52px; left: 35px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top: 52px; left: 57px;" />
					</div>
				
				</div>
			</div>
			<div style="height: 119px;">
				<div style="float: left; width: 16.67%;height: 118px;">
				
				</div>
				<div style="float: left; width: 33.33%;height: 118px;">
				
					<div id="cupNumText4" align="center" style="z-index: 103; "  class="circleCenterStyle2">
						<p class="cupText">4</p>
					</div>
					<div id="cupImgArea4"  style="z-index: 101; " class="circleCenterStyle2">
						<img id="cupCircle4" src="/healthmanager/resources/images/planet_4.png"  style="position: absolute; z-index: 2;" class="circle"/> 
						<img src="/healthmanager/resources/images/star_2.png"  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top: 52px; left: 13px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top: 52px; left: 35px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top: 52px; left: 57px;" />
					</div>
					
				</div>
				<div style="float: left; width: 33.33%; height: 118px;">
					<div id="cupNumText5" align="center" style="z-index: 103; " class="circleCenterStyle2">
						<p class="cupText">5</p>
					</div>
					<div id="cupImgArea5"  style="z-index: 101; " class="circleCenterStyle">
						<img id="cupCircle5" src="/healthmanager/resources/images/planet_5.png"  style="position: absolute; z-index: 2;" class="circle"/> 
						<img src="/healthmanager/resources/images/star_2.png"  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top: 52px; left: 13px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top: 52px; left: 35px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top: 52px; left: 57px;" />
					</div>
				</div>
				<div style="float: left; width: 16.67%;height: 118px;">
				
				</div>
			</div>
			<div style="height: 118px;">
				<div style="float: left; width: 33%;  height: 118px;">
					<div id="cupNumText6" align="center" style="z-index: 103; " class="circleCenterStyle">
						<p class="cupText">5</p>
					</div>
					<div id="cupImgArea6"  style="z-index: 101; " class="circleCenterStyle">
						<img id="cupCircle6" src="/healthmanager/resources/images/planet_6.png"  style="position: absolute; z-index: 2;" class="circle"/> 
						<img src="/healthmanager/resources/images/star_2.png"  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top: 52px; left: 13px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top: 52px; left: 35px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top: 52px; left: 57px;" />
					</div>
				</div>
				<div style="float: left; width: 34%;  height: 118px;">
					<div id="cupNumText7" align="center" style="z-index: 103; " class="circleCenterStyle">
						<p class="cupText">7</p>
					</div>
					<div id="cupImgArea7"  style="z-index: 101; " class="circleCenterStyle">
						<img id="cupCircle7" src="/healthmanager/resources/images/planet_7.png"  style="position: absolute; z-index: 2;" class="circle"/> 
						<img src="/healthmanager/resources/images/star_2.png"  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top: 52px; left: 13px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top: 52px; left: 35px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top: 52px; left: 57px;" />
					</div>
				</div>
				<div style="float: left; width: 33%; height: 118px;">
					<div id="cupNumText8" align="center" style="z-index: 103; " class="circleCenterStyle">
						<p class="cupText">8</p>
					</div>
					<div id="cupImgArea8"  style="z-index: 101; " class="circleCenterStyle">
						<img id="cupCircle8" src="/healthmanager/resources/images/planet_8.png"  style="position: absolute; z-index: 2;" class="circle"/> 
						<img src="/healthmanager/resources/images/star_2.png"  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top: 52px; left: 13px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top: 52px; left: 35px;" />
						<img src="/healthmanager/resources/images/star_1.png" class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top: 52px; left: 57px;" />
					</div>
				</div>
			</div>
			
			
		</div>
		
				
			</div>
	</div>
	<div class="footer">
		 <!-- <a class="selected">今天</a> | <a>往日</a> | <a id="resetBtn">清除</a> -->
		 <div style="text-align: right; margin-right: 20px;margin-bottom: 15px; font-size: 15px;font-weight: bold;color: #fff;"><a href="http://mp.weixin.qq.com/s?__biz=MzAxMzExODQ3Ng==&mid=201149825&idx=1&sn=8b5ce7ca9a94849ef43f6f7c11ccf4bf#rd">+关注茄子健康，一起打卡</a></div>
	</div>


</body>
</html>