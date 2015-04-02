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
	<title>茄子健康-每日饮食</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes" />

	<link rel="stylesheet" type="text/css"
		href="${pageContext.servletContext.contextPath}/resources/css/eathome.css" />
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.servletContext.contextPath}/resources/js/appframework.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${pageContext.servletContext.contextPath}/resources/js/eathome.js"></script>
	<script type="text/javascript" charset="utf-8">
		$(document)
			.ready(
					function() {						
						var uid = '${uid}';	
						var date = '${date}';
						var readonly = '${readonly}' == "true";	
						var qiezisaidseat = [];
						<c:forEach var="item" items="${qiezisaidseat}" varStatus="status">
						qiezisaidseat[<c:out value="${status.index}"/>] = '${item}';
						</c:forEach>
						load(uid, date, readonly, qiezisaidseat);	
						
						//var readonly = '${readonly}';						
						//if(readonly == "true") {							
							//resetTodayUrl(uid, date);	
						//}			
					});
	</script>
</head>

<body>
	<div id="header">		
		<div id="eatMen">
			<c:if  test="${name!=null && name!=''}">
				<span>${name}</span>
				<span>的打卡记录</span>
				<span class="separator">|</span>
			</c:if>
			<span>${eatMen}人正在打卡</span>
		</div>
		<div id="headerText"></div>
	</div>

	<div id="eatHome">
		<span id="egg"></span>
		<span id="milk"></span>
		<span id="bean"></span>
		<span id="fruit"></span>
		<span id="vegetable"></span>
	</div>

	<div id="footer">
		<a id="focusLink" href="http://mp.weixin.qq.com/s?__biz=MzAxMzExODQ3Ng==&mid=201149825&idx=1&sn=8b5ce7ca9a94849ef43f6f7c11ccf4bf#rd">
			<span class="plus">+</span>
			<span>添加茄子健康，一起打卡</span>
		</a>
	</div>
</body>
</html>