<!DOCTYPE html>
<!--HTML5 doctype-->
<html>
<head>
</head>
<body>
<script>
(function(){
	var appId = "wx95fc895bebd3743b";
	var pageUrl = "http://qiezilife.com/healthmanager/web/eathome/";
	var redirectUrl = encodeURIComponent(pageUrl);
	var requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	window.location.href = requestUrl;
}()); 
</script>
</body>
