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
<!-- 
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/icons.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/afui.custom.css" /> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/animate.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/home.css?random=<%=System.currentTimeMillis() %>" />
<script type="text/javascript" charset="utf-8"
	src="${pageContext.servletContext.contextPath}/resources/js/appframework.min.js"></script>
<script type="text/javascript">
	var shareTitle = "茄子健康-8杯水";
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	    // 发送给好友
	    WeixinJSBridge.on('menu:share:appmessage', function (argv) {
	        WeixinJSBridge.invoke('sendAppMessage', {
	            "appid": "gh_ca14469eba07",
	            "img_url": "https://mmbiz.qlogo.cn/mmbiz/sEAlex1QSeSaLeDajbFAAYtmlD76XLc5QDBU0Inr29ehAA61u8HRheOCymjLQYL76SVcN63QgCqolc7gSOlj9A/0",
	            "img_width": "60",
	            "img_height": "60",
	            "link": location.href,
	            "desc":  shareTitle,
	            "title": "茄子健康-8杯水"
	        }, function (res) {
	            _report('send_msg', res.err_msg);
	        })
	    });
	
	    // 分享到朋友圈
	    WeixinJSBridge.on('menu:share:timeline', function (argv) {
	        WeixinJSBridge.invoke('shareTimeline', {
	        	"appid": "gh_ca14469eba07",
	            "img_url": "https://mmbiz.qlogo.cn/mmbiz/sEAlex1QSeSaLeDajbFAAYtmlD76XLc5QDBU0Inr29ehAA61u8HRheOCymjLQYL76SVcN63QgCqolc7gSOlj9A/0",
	            "img_width": "60",
	            "img_height": "60",
	            "link": location.href,
	            "desc":  shareTitle,
	            "title": shareTitle 
	        }, function (res) {
	            _report('timeline', res.err_msg);
	        });
	    });
	}, false);

	$(document)
			.ready(
					function() {
						//$.ui.toggleHeaderMenu();
						//$.feat.nativeTouchScroll = false; //Disable native scrolling globally
						//$.ui.removeFooterMenu();
						
						var uid = '${uid}';
						var date = '${date}';
						var records = {};
						var readonly = ${empty readonly};
						
						var recordsSize = ${fn:length(records)};
						var glowFlag = true;
						var historyFlag = true;
						var drinkTime = [ "7:00", "9:00", "10:30", "11:30",
											"13:30", "15:00", "17:00", "20:30" ];
						var shareDesc = ["茄子健康8杯水打卡~简单健康又有趣~大家都来试试吧！",
						                 "茄子健康8杯水打卡：搞定1杯啦~好的开始！继续加油！",
						                 "茄子健康8杯水打卡：2杯喽~2杯喽~心情棒棒哒~",
						                 "茄子健康8杯水打卡：搞定3杯喽！差不多就一半了~",
						                 "茄子健康8杯水打卡：4杯！半数达成！",
						                 "茄子健康8杯水打卡：完成5杯啦！胜利在望！",
						                 "茄子健康8杯水打卡：不知不觉6杯了~身心舒畅有木有~",
						                 "茄子健康8杯水打卡：第7杯！只差1杯喽！",
						                 "茄子健康8杯水打卡：第8杯！今日目标达成！明天再接再厉！"];
						
						var qiezisaids = [];
						<c:forEach var="item" items="${qiezisaids}" varStatus="status">
						qiezisaids[<c:out value="${status.index}"/>] = '${item}';
						</c:forEach>
						
						<c:forEach var="item" items="${records}" varStatus="status">
						records['${item.cupNumber}'] = '${item.starLevel}';
						</c:forEach>
						
						/** debug function : tobe remove for production */
						$('#resetBtn')
						.on(
								'click',
								function(e) {
									var opts = {
										type : "DELETE",
										success : function(data) {
											records = {};
											recordsSize = 0;
											initAllCups();
										},
										url : "${pageContext.servletContext.contextPath}/rest/drinkrecord/" + uid,
										data : null,
										contentType : "application/json",
										dataType : "json"
									}
									$.ajax(opts);
								});
						
						$('#drinkBtn')
								.on(
										'click',
										function(e) {
											var opts = {
												type : "POST",
												success : function(data) {
													$("#currentDay").html(
															data.obj.time);
													$("#total").html(
															data.obj.total);
												},
												url : "${pageContext.servletContext.contextPath}/rest/drinkrecord/add/"
														+ uid + +"?nickname=" + document.getElementById('weiXinHaoNickname').innerHTML ,
												data : null,
												contentType : "application/json",
												dataType : "json"
											}
											$.ajax(opts);
										});

						$("#historyBtn").on("click", function() {
							document.forms["status"].submit();
						});
/*
						$('#historyBtn')
								.on(
										'click',
										function(e) {

											$('#drinkBtn').hide();
											var opts = {
												type : "GET",
												success : function(data) {
													$("#searchResultBox")
															.empty();
													var result = "";
													$
															.each(
																	data,
																	function(
																			id,
																			element) {
																		result += "<li>"
																				+ element.time
																				+ " : "
																				+ element.total
																				+ "杯"
																				+ "</li>";
																	});
													$("#searchResultBox").html(
															result);
												},
												url : "${pageContext.servletContext.contextPath}/rest/drinkrecord/history/"
														+ uid,
												data : null,
												contentType : "application/json",
												dataType : "json"
											}
											$.ajax(opts);
										});

						$('#todayBtn')
								.on(
										'click',
										function(e) {

											$('#drinkBtn').show();
											var opts = {
												type : "GET",
												success : function(data) {
													$("#searchResultBox")
															.empty();
													var result = "";
													result += '<li id="uid">'
															+ data.uid
															+ '</li>';
													result += '<li id="currentDay">'
															+ data.time
															+ '</li>';
													result += '<li id="total">'
															+ data.total
															+ '</li>';
													$("#searchResultBox").html(
															result);
												},
												url : "${pageContext.servletContext.contextPath}/rest/drinkrecord/today/"
														+ uid,
												data : null,
												contentType : "application/json",
												dataType : "json"
											}
											$.ajax(opts);
										});
*/
						initAllCups();
						glowClickableCircle();
						initLayout();
						initHistoryBtn();
						initFloatingLayer();				

						function glowClickableCircle() {
							if(glowFlag) {
								glowFlag = false;
								$('#clickableCircleImg').addClass('glow');
								$('#clickableCircleImg').removeClass('glow_off');
							}else {
								glowFlag = true;
								$('#clickableCircleImg').addClass('glow_off');
								$('#clickableCircleImg').removeClass('glow');
							}
							setTimeout(function(){glowClickableCircle();},1000 );
						}

						function initHistoryBtn() {
							var opts = {
								type : "GET",
								success : function(data) {
									if(data != null && data.length > 0) {
										$("#historyBtn").css("display", "block");						
										historyAnimation();
									}	
								},
								url : "../rest/drinkrecord/history/" + uid + "/0" + "?date=" + date,
								data : null,
								contentType : "application/json",
								dataType : "json"
							}
							$.ajax(opts);
						}

						function historyAnimation() {
							if(historyFlag) {
								historyFlag = false;
								$('#historyOuter').addClass('history_outer');
								$('#historyOuter').removeClass('history_outer_off');
								$('#historyInner').addClass('history_inner');
								$('#historyInner').removeClass('history_inner_off');
							}else {
								historyFlag = true;
								$('#historyOuter').addClass('history_outer_off');
								$('#historyOuter').removeClass('history_outer');
								$('#historyInner').addClass('history_inner_off');
								$('#historyInner').removeClass('history_inner');
							}
							setTimeout(function(){historyAnimation();},1000 );
						}
						
						/**
						for (var i = 0; i < 8; i++) {
							
							if(records[i+1]) {
								drawProcess("cup" + (i + 1),"第"+ (i + 1) + "杯",true);
							} else {
								drawProcess("cup" + (i + 1), drinkTime[i]);
								$('#cup' + (i + 1)).bind('click', function(e) {
									var cupID = $(this).attr('id');
									var cupNumber = cupID.substring(3);
									var opts = {
											type : "POST",
											success : function(data) {
												$('#' + cupID).unbind('click');
												$('#' + cupID).empty();
												drawProcess(cupID,"第"+ cupNumber + "杯",true);
											},
											url : "${pageContext.servletContext.contextPath}/rest/drinkrecord/add/" + uid + "/" + cupNumber ,
											data : null,
											contentType : "application/json",
											dataType : "json"
										}
										$.ajax(opts);
								});
							}
						}*/
						
						function initAllCups() {
							drawHeaderText(recordsSize);
							for (var i = 0; i < 8; i++) {
								if(records[i+1]) {
									displayCup((i + 1), 1, parseInt(records[i+1]));
								} else {
									displayCup((i + 1), 0 , null, drinkTime[i]);
								}
							}
							
						}

						function drawHeaderText(recordsSize) {
							var cups = parseInt(recordsSize);
							var windowTitle = shareDesc[0];
							if( cups === 0) {
								windowTitle = shareDesc[0];
							} else if( cups > 0 && cups <= 8) {
								windowTitle = shareDesc[cups];
							} else {
								windowTitle = shareDesc[8];
							}
							shareTitle=windowTitle;
							$('#contentHeaderText').empty();
							var qiezisaid = getQieZiSaid(cups);
							if(qiezisaid.indexOf("8-n") >= 0) {
								qiezisaid = qiezisaid.replace(/8-n/,(8-recordsSize));
							}
							qiezisaid = qiezisaid.replace(/n/,recordsSize);
							if(qiezisaid == null) {
								qiezisaid = '茄子君说：已经喝了' + recordsSize + '杯水了！剩下的' + (8-cups)+ '杯也要加油！记得每杯200毫升左右噢~';
							}
							//var qiezisaid = '茄子君说：已经喝了' + recordsSize + '杯水了！剩下的' + (8-recordsSize)+ '杯也要加油！记得每杯200毫升左右噢~';
							$('#contentHeaderText').html(qiezisaid);
							
							//change water cups image
							$('#waterCup').attr('src','${pageContext.servletContext.contextPath}/resources/images/cup_' + cups + '.png');
							
						}
						
						function displayCup(cupNumber , cupState , starLevel , timeText) {
							var cupElementTd = $("#cup" + cupNumber);
							cupElementTd.empty();
							var cupStr,circleCenterClass='circleCenterStyle';

							if(cupState === 0) {
								var timeTextArray = timeText.split(":");
								if(cupNumber === (recordsSize+1)) {
									//clickable cup
									cupStr = '<div align="center" style="z-index: 103; " class="' + circleCenterClass + '">' +
											'<div style="vertical-align: middle; ">' +
												'<span class="cupTimeHour">'+timeTextArray[0]+'</span><span class="cupTimeMin">:'+timeTextArray[1]+'</span>' +
											'</div>' +
										'</div>' +
										'<div style="z-index: 101;" class="' + circleCenterClass + '">' +
											'<img id="clickableCircleImg" src="${pageContext.servletContext.contextPath}/resources/images/circle_on.png"' +
												'style="vertical-align:middle;position: absolute;z-index: 102; width: 85px; height: 85px;top:50%;margin-top:-42.5px;left:50%;margin-left:-42.5px;" />' +
										'</div>';
								} else {
									//unclickable cup
									cupStr = '<div align="center" style="z-index: 103;" class="' + circleCenterClass + '">' +
												 '<div style="vertical-align: middle; ">' +
													'<span class="cupTimeHour">'+timeTextArray[0]+'</span><span class="cupTimeMin">:'+timeTextArray[1]+'</span>' + 
												 '</div>' +
											'</div>' +
											'<div style="z-index: 101;" class="' + circleCenterClass + '">' +
												'<img id="unClickableCircleImg' + cupNumber + '" src="${pageContext.servletContext.contextPath}/resources/images/circle_off.png"' +
													'style="vertical-align:middle;position: absolute;z-index: 102; width: 85px; height: 85px;top:50%;margin-top:-42.5px;left:50%;margin-left:-42.5px;" />' +
											'</div>';
									
								}
								
							} else if(cupState === 1) {
								if(starLevel === 3) {
									cupStr = '<div id="cupNumText'+ cupNumber + '" align="center" style="z-index: 103;" class="' + circleCenterClass + '"> ' +
											' <p class="cupText">' + cupNumber + '</p> ' +
										'</div> ' +
										'<div id="cupImgArea'+ cupNumber + '" ' +
											'style="z-index: 101;"  class="' + circleCenterClass + '">' +
											'<img id="cupCircle'+ cupNumber + '" src="${pageContext.servletContext.contextPath}/resources/images/planet_' + cupNumber + '.png" class="circle" ' +
												' style="position: absolute; z-index: 102;top:50%;margin-top:-42.5px;left:50%;margin-left:-42.5px;" /> <img ' +
												'  class="cupStar" src="${pageContext.servletContext.contextPath}/resources/images/star_3.png" ' +
												' style="width: 119px; height: 88px; position: absolute; z-index: 104; top:50%;margin-top:-44px;left:50%;margin-left:-59.5px;" />' +
										'</div>' ;
								} else if(starLevel === 2) {
									cupStr = '<div id="cupNumText'+ cupNumber + '" align="center" style="z-index: 103;" class="' + circleCenterClass + '">' + 
									'<p class="cupText">' + cupNumber + '</p>' + 
								'</div>' + 
								'<div id="cupImgArea'+ cupNumber + '"  style="z-index: 101; " class="' + circleCenterClass + '">' + 
									'<img src="${pageContext.servletContext.contextPath}/resources/images/planet_' + cupNumber + '.png" class="circle" ' + 
										' style="position: absolute; z-index: 2;top:50%;margin-top:-42.5px" /> <img ' + 
										'  class="cupStar"  src="${pageContext.servletContext.contextPath}/resources/images/star_2.png" ' + 
										' style="width: 16px; height: 15px; position: absolute; z-index: 3;top:50%;margin-top:10px;left:50%;margin-left:-27.5px;" />' + 
									'<img src="${pageContext.servletContext.contextPath}/resources/images/star_2.png" ' + 
										'  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top:50%;margin-top:10px;left:50%;margin-left:-7.5px;" />' + 
									'<img src="${pageContext.servletContext.contextPath}/resources/images/star_1.png" ' + 
										'  class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top:50%;margin-top:10px;left:50%;margin-left:12.5px;" />' + 
								'</div>	' ;
								} else {
									cupStr = '<div id="cupNumText'+ cupNumber + '" align="center" style="z-index: 103; " class="' + circleCenterClass + '">' + 
									'<p class="cupText">' + cupNumber + '</p>' + 
								'</div>' + 
								'<div id="cupImgArea'+ cupNumber + '" style="z-index: 101;" class="' + circleCenterClass + '">' + 
									'<img id="cupCircle'+ cupNumber + '" src="${pageContext.servletContext.contextPath}/resources/images/planet_' + cupNumber + '.png" class="circle"' + 
										' style="position: absolute; z-index: 2;top:50%;margin-top:-42.5px" /> <img ' + 
										' src="${pageContext.servletContext.contextPath}/resources/images/star_2.png" ' + 
										' class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 3; top:50%;margin-top:10px;left:50%;margin-left:-27.5px;" />' + 
									'<img src="${pageContext.servletContext.contextPath}/resources/images/star_1.png"' + 
										' class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 4; top:50%;margin-top:10px;left:50%;margin-left:-7.5px;" />' + 
									'<img src="${pageContext.servletContext.contextPath}/resources/images/star_1.png"' + 
										' class="cupStar" style="width: 16px; height: 15px; position: absolute; z-index: 5; top:50%;margin-top:10px;left:50%;margin-left:12.5px;" />' + 
								'</div>	' ;
								}
								
									
							} 
							cupElementTd.append(cupStr);
							/**
							if(cupNumber === 4 || cupNumber ===5 ) {
								if(document.getElementById("cupImgArea" + cupNumber)) {
									document.getElementById("cupImgArea" + cupNumber).style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
									document.getElementById("cupImgArea"  + cupNumber).style.marginTop =  (118 - 85)/2+ "px";
								}
							} else {
								if(document.getElementById("cupImgArea" + cupNumber)) {
									document.getElementById("cupImgArea" + cupNumber).style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
									document.getElementById("cupImgArea" + cupNumber).style.marginTop =  (118 - 85)/2+ "px";
								}
							}
							if(document.getElementById("clickableCircleImg")) {
								document.getElementById("clickableCircleImg").style.marginLeft =  ($(document).width()/3 - 85)/2+ "px";
								document.getElementById("clickableCircleImg").style.marginTop =  (118 - 85)/2+ "px";
							}
							if(document.getElementById("unClickableCircleImg" + cupNumber)) {
								document.getElementById("unClickableCircleImg" + cupNumber).style.marginLeft =  ($(document).width() - 255)/4+ "px";
								document.getElementById("unClickableCircleImg" + cupNumber).style.marginTop =  (118 - 85)/2+ "px";
							}*/
							
							if(cupState === 0) {
								<c:if  test="${empty readonly}">
								if(cupNumber === (recordsSize + 1)) {
									$('#cup' + (cupNumber)).bind('click', function(e) {
										var cupID = $(this).attr('id');
										var innerCupNumber = cupID.substring(3);
										if(innerCupNumber == "1") {
											$("#floatingLayer").css("display", "none");
										}
										var opts = {
												type : "POST",
												success : function(data) {
													recordsSize = recordsSize + 1;
													$('#' + cupID).unbind('click');
													$('#' + cupID).empty();
													
													drawHeaderText(recordsSize);
													/** animate effect */
													/**
													$('#contentHeaderText').addClass('bounceOutUp');
													setTimeout(function(){
														$('#contentHeaderText').removeClass('bounceOutUp');
														$('#contentHeaderText').addClass('bounceInRight');
														},100);*/
													
													displayCup(parseInt(innerCupNumber), 1, data.obj.starLevel);
													$('#cupCircle'+innerCupNumber).addClass('animated bounceInRight');
													$('#cupNumText'+innerCupNumber).hide();
													$('#cupImgArea' + innerCupNumber).children('img.cupStar').hide();
													setTimeout(function(){
														$('#cupNumText'+innerCupNumber).show();
														$('#cupNumText'+innerCupNumber).addClass('animated bounceInRight');
														
														setTimeout(function(){
															$('#cupImgArea' + innerCupNumber).children('img.cupStar').show();
															$('#cupImgArea' + innerCupNumber).children('img.cupStar').addClass('animated bounceInRight');
															},200);
															setTimeout(function(){
																if(parseInt(innerCupNumber) <= 7) {
																	displayCup(parseInt(innerCupNumber) + 1 , 0 ,null, drinkTime[innerCupNumber]);
																}
															},1500);
														
														},200);
													
													
												},
												url : "${pageContext.servletContext.contextPath}/rest/drinkrecord/add/" + uid + "/" + innerCupNumber +"?nickname=" + document.getElementById('weiXinHaoNickname').innerHTML ,
												data : null,
												contentType : "application/json",
												dataType : "json"
											}
											$.ajax(opts);
									});
								}
								</c:if>
							}
						}

						function getTimeForHour(hourTime) {
							var today = new Date();
							var hour = parseInt(hourTime.split(":")[0]);
							var min = parseInt(hourTime.split(":")[1]);
							today.setHours(hour,min,0,0)
							return today.getTime();
						}

						function getQieZiSaid(cupNum) {
							var result;
							var refTime = new Date();
							var currentTime = new Date().getTime();
							if(cupNum === 0) {
								if( currentTime >= getTimeForHour( "0:0") && currentTime < getTimeForHour("5:0")) {
									result = qiezisaids[0];
								} else if(currentTime >= getTimeForHour("5:0") && currentTime <= getTimeForHour("7:30")) {
									result = qiezisaids[1];
								} else if(currentTime > getTimeForHour("7:30") && currentTime <= getTimeForHour("8:30")) {
									result = qiezisaids[2];
								} else if(currentTime > getTimeForHour("8:30") && currentTime < getTimeForHour("13:30")) {
									result = qiezisaids[3];
								} else if(currentTime >= getTimeForHour("13:30") && currentTime <= getTimeForHour("20:30")) {
									result = qiezisaids[4];
								} else if(currentTime > getTimeForHour("20:30")) {
									result = qiezisaids[5];
								}
							} else if(cupNum === 8) {
								if( currentTime >= getTimeForHour("0:0") && currentTime < getTimeForHour("5:0")) {
									result = qiezisaids[10];
								} else if( currentTime >= getTimeForHour("5:0") && currentTime < getTimeForHour("20:0")) {
									result = qiezisaids[11];
								} else if( currentTime >= getTimeForHour("20:0") && currentTime < getTimeForHour("23:59")) {
									var all3Star = $('#content').find('img[src$="/star_3.png"]').length;
									if(all3Star === 8) {
										result = qiezisaids[13];
									} else {
										result = qiezisaids[12];
									}
								} 
							} else {
								// 1 ~ 7 cups
								if( currentTime >= getTimeForHour("0:0") && currentTime < getTimeForHour("5:0")) {
									result = qiezisaids[6];
								} else if( currentTime >= getTimeForHour("5:0") && currentTime <= (getTimeForHour(drinkTime[cupNum-1]) - 45*60*1000)) {
									result = qiezisaids[7];
								} else if( currentTime >= (getTimeForHour(drinkTime[cupNum-1]) - 45*60*1000) && currentTime <= getTimeForHour(drinkTime[cupNum])) {
									result = qiezisaids[8];
								}  else if( currentTime >=  getTimeForHour(drinkTime[cupNum])) {
									result = qiezisaids[9];
								}
							}
							return result;
						}
						
						function initLayout() {		
							var paddingWidth = ($(document).width() - 85 * 3) / 4;
							var headerHeight = document.getElementById("contentHeader").offsetHeight;	
							var footerHeight = document.getElementById("footer").offsetHeight;	
							var contentHeight = $(document).height() - headerHeight - footerHeight; 	
							var paddingHeight = (contentHeight - 85 * 3) / 4;

							document.getElementById("contentHeader").style.width = $(document).width() - paddingWidth * 2 + "px";
							document.getElementById("contentHeader").style.padding = "0 " + paddingWidth + "px";	
							
							if(paddingHeight < 20) {
								paddingHeight /= 2;		
							}
							document.getElementById("content").style.bottom = footerHeight + "px";		
		
							for(var index = 0; index < 8; index++) {
								if(index != 3) {
									document.getElementById("cup" + (index + 1)).style.marginLeft =  paddingWidth + "px";
								}
								else {
									document.getElementById("cup" + (index + 1)).style.marginLeft =  ($(document).width() - paddingWidth - 85 * 2) / 2 + "px";
								}
								document.getElementById("cup" + (index + 1)).style.marginBottom = paddingHeight + "px";
							}
							
							document.getElementById("focusLink").style.marginRight =  paddingWidth + "px";	

							var left = paddingWidth + 85 + 15;
							var bottom = footerHeight + document.getElementById("content").offsetHeight - (40 + 85) / 2;
							$("#arrow").css("left", left + "px");
							$("#arrow").css("bottom", bottom + "px");	

							left = ($(document).width() - 192) / 2;
							bottom = ($(document).height() - 76) / 2;
							$("#floatingText").css("left", left + "px");
							$("#floatingText").css("top", bottom + "px");
							$("#leftMark").css("left", (left - 20) + "px");
							$("#leftMark").css("top", (bottom - 5) + "px");
							$("#rightMark").css("left", (left + 192) + "px");
							$("#rightMark").css("top", (bottom + 76 - 23) + "px");
						}

						function initFloatingLayer() {
							if(readonly && '${fn:length(records)}' == '0') {
								$("#floatingLayer").css("display", "block");
							}
						}
					});

	$(window)
		.resize(
			function() {
				var paddingWidth = ($(document).width() - 85 * 3) / 4;
				var headerHeight = document.getElementById("contentHeader").offsetHeight;	
				var footerHeight = document.getElementById("footer").offsetHeight;	
				var contentHeight = $(document).height() - headerHeight - footerHeight; 	
				var paddingHeight = (contentHeight - 85 * 3) / 4;

				document.getElementById("contentHeader").style.width = $(document).width() - paddingWidth * 2 + "px";
				document.getElementById("contentHeader").style.padding = "0 " + paddingWidth + "px";	
							
				if(paddingHeight < 20) {
					paddingHeight /= 2;		
				}
				document.getElementById("content").style.bottom = footerHeight + "px";		
		
				for(var index = 0; index < 8; index++) {
					if(index != 3) {
						document.getElementById("cup" + (index + 1)).style.marginLeft =  paddingWidth + "px";
					}
					else {
						document.getElementById("cup" + (index + 1)).style.marginLeft =  ($(document).width() - paddingWidth - 85 * 2) / 2 + "px";
					}
					document.getElementById("cup" + (index + 1)).style.marginBottom = paddingHeight + "px";
				}
							
				document.getElementById("focusLink").style.marginRight =  paddingWidth + "px";

				var left = paddingWidth + 85 + 15;
							var bottom = footerHeight + document.getElementById("content").offsetHeight - (40 + 85) / 2;
							$("#arrow").css("left", left + "px");
							$("#arrow").css("bottom", bottom + "px");	

							left = ($(document).width() - 192) / 2;
							bottom = ($(document).height() - 76) / 2;
							$("#floatingText").css("left", left + "px");
							$("#floatingText").css("top", bottom + "px");
							$("#leftMark").css("left", (left - 20) + "px");
							$("#leftMark").css("top", (bottom - 5) + "px");
							$("#rightMark").css("left", (left + 192) + "px");
							$("#rightMark").css("top", (bottom + 76 - 23) + "px");
	});
</script>

</head>

<body>
	<div class="main">
		<div id="contentHeader">
			<div id="drinkMen">
				<c:if  test="${name!=null && name!=''}">
					<span id="weiXinHaoNickname">${name}</span>
					<span>的打卡记录</span>
					<span class="separator">|</span>
				</c:if>
				<span>${drinkMen}人正在打卡</span>
			</div>
			<div id="drinkTitle">
				<img id="waterCup"
					src="${pageContext.servletContext.contextPath}/resources/images/cup_${fn:length(records)}.png" />
				<span id="contentHeaderText">茄子君说：已经喝了
					${fn:length(records)}杯水了！剩下的${8-fn:length(records)}杯也要加油！记得每杯200毫升左右噢~
				</span>
			</div>
		</div>

		<div id="content">
			<div>
				<div id="cup1" class="circleBox"></div>
				<div id="cup2" class="circleBox"></div>
				<div id="cup3" class="circleBox"></div>
			</div>
			<div>
				<div id="cup4" class="circleBox"></div>
				<div id="cup5" class="circleBox"></div>
			</div>
			<div>
				<div id="cup6" class="circleBox"></div>
				<div id="cup7" class="circleBox"></div>
				<div id="cup8" class="circleBox"></div>
			</div>
		</div>

		<div id="footer">
			<form method="post" action="history?user=${uid}&name=${name}&date=${date}" name="status">
				<input type="hidden" name="readonly" value="${readonly}"/>
			</form>
			<a id="historyBtn">
				<span id="historyOuter"></span>
				<span id="historyInner"></span>
				<span id="historyText">往日</span>
			</a>
			<!-- <a class="selected">今天</a> | --><!--历史记录 --><!-- | <a id="resetBtn">清除</a>-->
			<a id="focusLink"
				href="http://mp.weixin.qq.com/s?__biz=MzAxMzExODQ3Ng==&mid=201149825&idx=1&sn=8b5ce7ca9a94849ef43f6f7c11ccf4bf#rd">
				<span class="plus">+</span>
				<span>添加茄子健康，一起打卡</span>
			</a>	
		</div>

		<div id="floatingLayer">
			<img id="arrow" src="resources/images/arrow.png" />
			<div class="floatingText">
				<div id="leftMark">“</div>
				<div id="floatingText">
					<p>点击这里开始今天的打卡，</p>
					<p>这个是第一杯的建议时间！</p>
					<p>新的一天~</p>
					<p>好习惯继续保持哦！</p>
				</div>
				<div id="rightMark">”</div>
			</div>
		</div>
	</div>
</body>

</html>