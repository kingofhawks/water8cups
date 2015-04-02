package com.bigyellow.hm.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bigyellow.hm.entity.WeixinUserBean;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date Mar 8, 2015
 */
public class WeixinUtil {
	
	protected static Logger logger = Logger.getLogger(WeixinUtil.class);
	
	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			conn.setRequestMethod(requestMethod);

			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();

				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
		}

		return jsonObject;
	}

	public static String getOpenIDFromCode(String code) {
		String result = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ Constants.SERVICE_APP_ID
				+ "&secret="
				+ Constants.SERVICE_SECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject =  httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				// String accessToken = jsonObject.getString("access_token");
				result = jsonObject.getString("openid");
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return result;
	}

	public static String getNickNameFromSubscribe(String openID) {
		return getNickNameFromWeixin(Constants.SUBSCRIBE_APP_ID, 
				Constants.SUBSCRIBE_SECRET , openID);
	}
	
	public static String getNickNameFromService(String openID) {
		return getNickNameFromWeixin(Constants.SERVICE_APP_ID,
				Constants.SERVICE_SECRET, openID);
	}
	
	public static WeixinUserBean getUserInfoFromServiceByOauth(String code) {
		WeixinUserBean userInfo = new WeixinUserBean();
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ Constants.SERVICE_APP_ID
				+ "&secret="
				+ Constants.SERVICE_SECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject = WeixinUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				String accessToken = jsonObject.getString("access_token");
				String openID = jsonObject.getString("openid");

				userInfo.setOpenID(openID);

				requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
						+ accessToken + "&openid=" + openID;
				jsonObject = WeixinUtil.httpsRequest(requestUrl, "GET", null);

				if (null != jsonObject) {
					try {
						String uid = jsonObject.getString("openid");
						String name = jsonObject.getString("nickname");

						userInfo.setOpenID(uid);
						userInfo.setNickname(name);

						// String sex = jsonObject.getString("sex");
						// String country = jsonObject.getString("country");
						// String province = jsonObject.getString("province");
						// String city = jsonObject.getString("city");
						// String headimgurl =
						// jsonObject.getString("headimgurl");

					} catch (Exception e) {
						logger.debug(e.getMessage());
					}
				}
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return userInfo;
	}
	
	private static String getNickNameFromWeixin(String appid, String secret,
			String openID) {
		String result = null;
		String accessTokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appid + "&secret=" + secret;

		JSONObject accessTokenJSON = httpsRequest(accessTokenURL,
				"GET", null);
		if (accessTokenJSON != null) {
			try {
				String accessToken = accessTokenJSON.getString("access_token");
				String userInfoURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
						+ accessToken + "&openid=" + openID;

				JSONObject userInfoJSON = httpsRequest(userInfoURL,
						"GET", null);
				if (userInfoJSON != null) {
					result = userInfoJSON.getString("nickname");
				}

			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return result;
	}
}
