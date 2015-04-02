/**
 * 
 */
package com.bigyellow.hm.web;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigyellow.hm.common.CommonUtil;
import com.bigyellow.hm.common.WeixinUtil;
import com.bigyellow.hm.dao.DrinkRecordDao;
import com.bigyellow.hm.dao.EatRecordDao;
import com.bigyellow.hm.entity.EatRecord;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class EatOAuthController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	EatRecordDao dao;
	@Autowired
	DrinkRecordDao drinkDao;

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/eatauthhome", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "name", required = false) String name,
			RedirectAttributes model) {
		logger.debug("Received request to eat test homepage for " + code);

		String uid = WeixinUtil.getOpenIDFromCode(code);
		logger.info("eatauthhome openid:" + uid);
		EatRecord records = dao.getTodayRecords(uid);

		model.addFlashAttribute("records", records);
		model.addFlashAttribute("uid", uid);
		model.addFlashAttribute("qiezisaidseat", CommonUtil.getQiezisaidsEat());
		model.addFlashAttribute("eatMen",
				300 + drinkDao.countDrinkMen() + dao.countEatMen());

		try {
			if (name.length() > 9) {
				name = name.substring(0, 9) + "...";
			}
			model.addFlashAttribute("name", name);

			return "redirect:/web/eatauthtoday?user=" + uid + "&name="
					+ URLEncoder.encode(name, "utf-8") + "&date="
					+ (new Date()).getTime();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		// This will resolve to /WEB-INF/jsp/loginpage.jsp

		return "redirect:/web/eatauthtoday?user=" + uid + "&date="
				+ (new Date()).getTime();
	}

	@RequestMapping(value = "/eattestauthorize/{srcOpenID}", method = RequestMethod.GET)
	public String authorization(@PathVariable String srcOpenID,
			@RequestParam(value = "code", required = false) String code,
			Model model, HttpServletRequest request) {
		logger.info("-----------eatauthorize srcOpenID:" + srcOpenID);
		if (code == null) {
			code = request.getParameter("code");
		}
		logger.info("-----------eatauthorize code:" + code);
		String appid = "wx95fc895bebd3743b";
		String secret = "a2ed31c3b442a72b13f7802992d3678c";
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appid
				+ "&secret="
				+ secret
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject = WeixinUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				String accessToken = jsonObject.getString("access_token");
				String openId = jsonObject.getString("openid");

				requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
						+ accessToken + "&openid=" + openId;
				jsonObject = WeixinUtil.httpsRequest(requestUrl, "GET", null);

				if (null != jsonObject) {
					try {
						String uid = jsonObject.getString("openid");
						String name = jsonObject.getString("nickname");

						// String sex = jsonObject.getString("sex");
						// String country = jsonObject.getString("country");
						// String province = jsonObject.getString("province");
						// String city = jsonObject.getString("city");
						// String headimgurl =
						// jsonObject.getString("headimgurl");

						return "redirect:/web/eatauthhome?uid=" + uid
								+ "&name=" + name;
					} catch (Exception e) {
						logger.debug(e.getMessage());
					}
				}
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}

		return null;
	}

	@RequestMapping(value = "/eatauthtoday", method = RequestMethod.GET)
	public String toTodayPage(
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "date", required = false) String dateStr,
			Model model, HttpServletRequest request) {
		logger.debug("Received request to eat today page for " + user);

		if (dateStr == null) {
			dateStr = Long.toString((new Date()).getTime());
		}

		Map<String, ?> maps = model.asMap();
		String uid = (String) maps.get("uid");
		if (uid == null) {
			long dateTime = Long.parseLong(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(dateTime);
			EatRecord records = dao.getRecordsByDate(user, cal.getTime());
			model.addAttribute("records", records);
			model.addAttribute("uid", user);
			model.addAttribute("qiezisaidseat", CommonUtil.getQiezisaidsEat());
			model.addAttribute("readonly", true);
			model.addAttribute("eatMen",
					300 + drinkDao.countDrinkMen() + dao.countEatMen());

			try {
				if (name.length() > 9) {
					name = name.substring(0, 9) + "...";
				}
				model.addAttribute("name", name);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}

		model.addAttribute("date", dateStr);

		return "eathome";
	}

}