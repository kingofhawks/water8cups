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
	<link rel="SHORTCUT ICON" href="${pageContext.servletContext.contextPath}/resources/images/logo.ico"/>
	<title>茄子健康-8杯水</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes" />

	<link rel="stylesheet" type="text/css"
		href="${pageContext.servletContext.contextPath}/resources/css/animate.min.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.servletContext.contextPath}/resources/css/history.css" />
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.servletContext.contextPath}/resources/js/appframework.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.servletContext.contextPath}/resources/js/history.js"></script>
	<script type="text/javascript" charset="utf-8">
		$(document)
			.ready(
					function() {
						var uid = '${uid}';	
						var date = '${date}';
						var name = '${name}';	
						load(uid, date);		

						var readonly = '${readonly}';						
						if(readonly == "true") {							
							resetTodayUrl(uid, date, name);	
						}			
					});
	</script>
</head>

<body>
	<div class="background"></div>
	<div id="header">
		<div id="drinkMen">
			<c:if  test="${name!=null && name!=''}">
				<span>${name}</span>
				<span>的打卡记录</span>
				<span class="separator">|</span>
			</c:if>
			<span>${drinkMen}人正在打卡</span>
		</div>
	</div>
	<div id="history">
		<c:forEach var="i" begin="0" end="44" step="1">
			<div id="item${i}" class="item">
				<span id="date${i}"></span>
				<span id="planet${i}" class="planet"></span>
				<span id="text${i}">第</span>
				<span id="days${i}" class="days"></span>
				<span>/21天</span>
				<span id="star${i}" class="star"></span>
			</div>
		</c:forEach>
		<div id="more">茄子君记不住更多啦~~~</div>
	</div>

	<a id="todayBtn" href="home?openID=${uid}&name=${name}">		
		<span id="todayOuter"></span>
		<span id="todayInner"></span>
		<span id="todayText">今日</span>
	</a>
	<div id="footer">
		<a id="focusLink" href="http://mp.weixin.qq.com/s?__biz=MzAxMzExODQ3Ng==&mid=201149825&idx=1&sn=8b5ce7ca9a94849ef43f6f7c11ccf4bf#rd">
			<span class="plus">+</span>
			<span>添加茄子健康，一起打卡</span>
		</a>
	</div>
</body>
</html>