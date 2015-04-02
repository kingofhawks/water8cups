var shareContent = "茄子健康-8杯水";
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	    // 发送给好友
	    WeixinJSBridge.on('menu:share:appmessage', function (argv) {
	        WeixinJSBridge.invoke('sendAppMessage', {
	            "appid": "gh_ca14469eba07",
	            "img_url": "https://mmbiz.qlogo.cn/mmbiz/sEAlex1QSeSaLeDajbFAAYtmlD76XLc5QDBU0Inr29ehAA61u8HRheOCymjLQYL76SVcN63QgCqolc7gSOlj9A/0",
	            "img_width": "60",
	            "img_height": "60",
	            "link": location.href,
	            "desc":  shareContent,
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
	            "desc":  shareContent,
	            "title": shareContent 
	        }, function (res) {
	            _report('timeline', res.err_msg);
	        });
	    });
	}, false);

function load (uid, date) {	
		var paddingWidth = ($(document).width() - 85 * 3) / 4 + "px";
		var itemIndex = 0;
		var hasHistory = true;
		var history = new Array();
		var todayFlag = true;

		$("#focusLink").css("padding-right", paddingWidth);
		getHistory();
		todayAnimation();	

		function getHistory() {
			var opts = {
				type : "GET",
				success : function(data) {
					if(data != null && data.length > 0) {
						$.each(
							data,
							function(id, element) {
								if(element != null) {
									var item = new Array();
									var string = element.date.indexOf('-') > -1 && element.date.split('-');
									item[0] = string[1] + "." + string[2];
									item[1] = element.level == 3 || element.level == 2 || element.level == 1;
									item[2] = element.days;							
									item[3] = element.level;

									history.push(item);	
									if(history != null && (history.length == 1 || history.length == 2)) {
										setShareContent();
									}
								}			
						});

						init();
					}
					else {
						hasHistory = false;
					}
				},
				url : "../rest/drinkrecord/history/" + uid + "/" + Math.floor(itemIndex / 15) + "?date=" + date,
				data : null,
				contentType : "application/json",
				dataType : "json"
			}
			$.ajax(opts);
		}

		function init() {			
			var count = history.length < (itemIndex + 15) ? history.length : (itemIndex + 15);
			hasHistory = history.length >= (itemIndex + 15);
			for(var index = itemIndex; index < count; index++) {
				$("#date" + index).css("padding-left", paddingWidth);
				$("#planet" + index).css("padding-left", paddingWidth);
				$("#text" + index).css("padding-left", paddingWidth);
				$("#star" + index).css("padding-right", paddingWidth);

				$("#date" + index).html(history[index][0]);
				$("#days" + index).html(history[index][2]);

				if(history[index][1]) {
					var planet;
					var imgNumber = history[index][2] % 8 == 0 ? 8 : (history[index][2] % 8);
					if(index == 0 || history[index - 1][1]) {
						planet = "<img class=\"line\" src=\"resources/images/history_planet_line_" + imgNumber + ".png\" />";				
					}
					else {
						planet = "<img src=\"resources/images/history_planet_" + imgNumber + ".png\" />";
					}
					
					$("#planet" + index).html(planet);

					var star = "<img src=\"resources/images/history_star.png\" />";
					for(var j = 0; j < history[index][3]; j++) {
						$("#star" + index).append(star);
					}
				}
				else {
					var unfinished = "<img class=\"unfinished\" src=\"resources/images/history_unfinished.png\" />";
					$("#star" + index).html(unfinished);

					$("#item" + index).css("color", "#fff");
					$("#item" + index).css("opacity", "0.3");
				}

				$("#item" + index).css("display", "block");
			}
			itemIndex += 15;

			if(!hasHistory || itemIndex == 45) {
				$("#more").css("display", "block");
			}
			else {
				getHistory();			
			}
		}

		function todayAnimation() {
			if(todayFlag) {
								todayFlag = false;
								$('#todayOuter').addClass('today_outer');
								$('#todayOuter').removeClass('today_outer_off');
								$('#todayInner').addClass('today_inner');
								$('#todayInner').removeClass('today_inner_off');
			}else {
				todayFlag = true;
				$('#todayOuter').addClass('today_outer_off');
				$('#todayOuter').removeClass('today_outer');
				$('#todayInner').addClass('today_inner_off');
				$('#todayInner').removeClass('today_inner');
			}
			setTimeout(function(){todayAnimation();},1000 );
		}

		var shareContents = [
			"【茄子健康-八杯水】连续打卡n天喽~距21天习惯养成还差(21-n)天！加油！", // 0:n < 21
			"【茄子健康-八杯水】21天习惯养成中~这是第n天，后面的(21-n)天也要加油！",
			"【茄子健康-八杯水】21天习惯养成第n天，(21-n)天 to go! fighting!",
			"【茄子健康-八杯水】21天顺利达成！感觉棒棒哒！", // 3:n = 21
			"【茄子健康-八杯水】21天就这样过来了~整个人都变好了！",
			"【茄子健康-八杯水】21天很轻松~觉得自己水灵灵的！",
			"【茄子健康-八杯水】21天习惯养成 mission complete！",
			"【茄子健康-八杯水】超越21天，好习惯继续坚持！", // 7:n > 21
			"【茄子健康-八杯水】不止步于21天，我要继续保持！",
			"【茄子健康-八杯水】第n天了~好习惯继续保持~",
			"【茄子健康-八杯水】21天习惯养成第1天，一定可以坚持下来的！", // 10:n = 1
			"【茄子健康-八杯水】第1天...21天习惯养成~大家来监督~",
			"【茄子健康-八杯水】哎呀~不小心断掉了...不过好习惯养成了就丢不掉啦！今天就捡回来~", // 12:n = 0, m >= 21
			"【茄子健康-八杯水】啧啧，一不小心断掉了，今天就把好习惯捡回来~",
			"【茄子健康-八杯水】好可惜，上次只差(21-m)天就达成习惯养成了~这次绝对能坚持下来！", // 14:n = 0, 21 > m >= 13
			"【茄子健康-八杯水】上次只差(21-m)天就习惯养成了，这次一定可以！",
			"【茄子健康-八杯水】上次的浅尝辄止不行啊...这回21天习惯养成一定能坚持下来！", // 16:n = 0, 13 > m > 0
			"【茄子健康-八杯水】坚持一个小小的习惯就那么难么~我不信，这次一定可以坚持下来！", // 17:n = 0, m = 0
			"【茄子健康-八杯水】21天全新的开始~这次一定可以习惯达成！"
		];

		function setShareContent() {
			var n = history[0][2];
			var random;
			if(n != 0 && history.length == 1) {  // n				
				if(n == 1) { // 10 -11
					random = Math.round(Math.random() * 1) + 10;
					shareContent = shareContents[random];
				}
				else if(n > 1 && n < 21) { // 0 - 2					
					random = Math.round(Math.random() * 2);
					var content = shareContents[random].replace("(21-n)", 21 - n).replace("n", n);
					shareContent = content;
				}
				else if(n == 21) { // 3 - 6
					random = Math.round(Math.random() * 3) + 3;
					shareContent = shareContents[random];
				}
				else if(n > 21) { // 7 - 9
					random = Math.round(Math.random() * 2) + 7;
					var content = shareContents[random].replace("n", n);
					shareContent = content;
				}
			}

			if(n == 0 && history.length == 2) {  // m
				var m = history[1][2];
				if(m >= 21) { // 12 - 13
					random = Math.round(Math.random() * 1) + 12;
					shareContent = shareContents[random];
				}
				else if(m >= 13) { // 14 - 15
					random = Math.round(Math.random() * 1) + 14;
					var content = shareContents[random].replace("(21-m)", 21 - m);
					shareContent = content;
				}
				else if(m > 0) { // 16
					shareContent = shareContents[16];
				}
				else if(m == 0) { // 17 - 18
					random = Math.round(Math.random() * 1) + 17;
					shareContent = shareContents[random];
				}
			}
		}
}

function resetTodayUrl(uid, date, name) {			
	$("#todayBtn").attr("href", "today?user=" + uid + "&date=" + date + "&name=" + name);
}	