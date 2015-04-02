var shareContent = "健康饮食打卡，从今天开始~";
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
	            "title": "茄子健康-每日饮食"
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

function load(uid, date, readonly, qiezisaidseat) {
	var paddingWidth = ($(document).width() - 85 * 3) / 4 + "px";
	var eatItem = new Array();  
	var itemName = ["milk", "bean", "egg", "vegetable", "fruit"]; //API : 1,2,3,4,5

	$("#focusLink").css("padding-right", paddingWidth);
	getQieziSaidsEat();
	getTodayRecord();

	function getTimeForHour(hourTime) {
		var today = new Date();
		var hour = parseInt(hourTime.split(":")[0]);
		var min = parseInt(hourTime.split(":")[1]);
		today.setHours(hour,min,0,0)
		return today.getTime();
	}

	function getQieziSaidsEat() {
		var result;
		var currentTime = new Date().getTime();
		if( currentTime >= getTimeForHour( "00:00") && currentTime < getTimeForHour("10:00")) {
			result = qiezisaidseat[0];
		} else if(currentTime >= getTimeForHour("10:00") && currentTime <= getTimeForHour("14:00")) {
			result = qiezisaidseat[1];
		} else if(currentTime > getTimeForHour("14:00") && currentTime <= getTimeForHour("17:30")) {
			result = qiezisaidseat[2];
		} else if(currentTime > getTimeForHour("17:30") && currentTime < getTimeForHour("20:30")) {
			result = qiezisaidseat[3];
		} else if(currentTime >= getTimeForHour("20:30") && currentTime <= getTimeForHour("24:00")) {
			result = qiezisaidseat[4];
		} 

		$("#headerText").html(result);
	}

	function setTodayRecord(itemIndex) {
		var opts = {
			type : "POST",
			success : function(data) { },
			url : "../rest/eatrecord/add/" + uid + "/" + itemIndex,
			data : null,
			contentType : "application/json",
			dataType : "json"
		}
		$.ajax(opts);
	}	

	function getTodayRecord() {
		var opts = {
			type : "GET",
			success : function(data) {
				if(data != null && data.id != null) {
					eatItem[0] = data.milk;
					eatItem[1] = data.beanProduct;
					eatItem[2] = data.egg;
					eatItem[3] = data.vegetable;
					eatItem[4] = data.fruit;
				}
				else {
					eatItem = [false, false, false, false, false];
				}

				init();

				!readonly && bindEvents();
				readonly && authorize();
			},
			url : "../rest/eatrecord/today/" + uid + "/?date=" + date,
			data : null,
			contentType : "application/json",
			dataType : "json"
		}
		$.ajax(opts);
	}

	function init() {		
		var width = $(document).width();
		var height = $(document).height() - $("#header").get(0).offsetHeight - $("#footer").get(0).offsetHeight;
		$("#eatHome").css("margin-top", $("#header").css("height"));
		$("#eatHome").css("height", height + "px");

		var widthParam;
		if($(document).height() > 500) {
			widthParam = 1;
		}
		else if($(document).height() > 450) {
			widthParam = 0.9;
		}
		else {
			widthParam = 0.8;
		}

		for(var index = 0; index < 5; index++) {
			var itemStatus = eatItem[index] ? "_color" : "_grey";
			var item = "<img id=\"" + itemName[index] + "Img\" src=\"resources/images/eat/" + itemName[index] + itemStatus + ".png\" />";

			$("#" + itemName[index]).html(item);
			if(index == 2 || index == 1) {
				$("#" + itemName[index] + "Img").css("width", width * widthParam / 2 + "px");
				$("#" + itemName[index]).css("width", widthParam / 2 * 100 + "%");
			}
			else {
				$("#" + itemName[index] + "Img").css("width", width * widthParam + "px");
				$("#" + itemName[index]).css("width", widthParam * 100 + "%");
			}

			$("#" + itemName[index]).css("left", (100 - widthParam * 100) / 2 + "%");


			if(index == 0 || index == 2) {
				$("#" + itemName[index]).css("top", $("#header").get(0).offsetHeight + "px");
			}
			else if(index == 1 || index == 4) {
				$("#" + itemName[index]).css("top", $("#header").get(0).offsetHeight * 5 / 7 + height / 3 + "px");
			}
			else {
				$("#" + itemName[index]).css("top", $("#header").get(0).offsetHeight * 4 / 5 + height * 2 / 3 + "px");
			}
		}
	}	

	function bindEvents() {		
		for(var index = 0; index < 5; index++) {
			(function(index){
				$("#" + itemName[index] + "Img").on("click", function(e) {
					if(!eatItem[index]) {
						eatItem[index] = true;
						$("#" + itemName[index] + "Img").attr("src", "resources/images/eat/" + itemName[index] + "_color.png");

						setTodayRecord(index + 1);
					}
				});
			})(index);
		}
	}	

	function authorize() {		
		for(var index = 0; index < 5; index++) {
			(function(index){
				$("#" + itemName[index] + "Img").on("click", function(e) {
					var appId = "wx95fc895bebd3743b";
					var pageUrl = "http://qiezilife.com/healthmanager/web/eathome/";
					var redirectUrl = encodeURIComponent(pageUrl);
					var requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
					window.open(requestUrl);
				});
			})(index);
		}
	}								
}