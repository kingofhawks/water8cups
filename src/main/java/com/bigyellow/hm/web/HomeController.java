/**
 * 
 */
package com.bigyellow.hm.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigyellow.hm.common.CommonUtil;
import com.bigyellow.hm.common.WeixinUtil;
import com.bigyellow.hm.dao.DrinkRecordDao;
import com.bigyellow.hm.dao.EatRecordDao;
import com.bigyellow.hm.dao.WeixinUserDao;
import com.bigyellow.hm.entity.DrinkRecord;
import com.bigyellow.hm.entity.WeixinUser;
import com.bigyellow.hm.entity.WeixinUserBean;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class HomeController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	DrinkRecordDao dao;
	@Autowired
	EatRecordDao eatDao;
	@Autowired
	WeixinUserDao weixinUserDao;

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "openID", required = false) String openID,
			@RequestParam(value = "name", required = false) String name,
			RedirectAttributes model) {
		logger.debug("Received request to home page for " + code);
		
		/* local test 
		String openID = code;
		String name = WeixinUtil.getNickNameFromSubscribe(openID);
		*/
		/* online */
		if(openID == null || "".equals(openID)) {
			WeixinUserBean userInfo =  WeixinUtil.getUserInfoFromServiceByOauth(code);
			openID = userInfo.getOpenID();
			name = userInfo.getNickname();
			logger.info("----openid : " + openID  + ",nickname:" + name);
			WeixinUser wxUser = null;
			if(name == null || name.length() == 0) {
				wxUser = weixinUserDao.findByOpenID(openID);
				logger.info("isFirstTimeUser : " + (wxUser == null));
				if(wxUser == null){
					return "oauth";
				} else {
					name = wxUser.getNickname();
				}
			} else {
				try {
					wxUser = weixinUserDao.findByOpenID(openID);
					if(wxUser == null){
						WeixinUser firstTimeWxUser = new WeixinUser(openID, name);
						weixinUserDao.saveOrUpdate(firstTimeWxUser);
					}
					dao.resolveOpenIDForOldSubscribeUser(openID,
							URLEncoder.encode(name, "utf-8"));
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		
		
		List<DrinkRecord> records = dao.getTodayRecords(openID);
		model.addFlashAttribute("records", records);
		model.addFlashAttribute("uid", openID);
		model.addFlashAttribute("qiezisaids", CommonUtil.getQiezisaids());
		model.addFlashAttribute("drinkMen", 300 + dao.countDrinkMen() + eatDao.countEatMen());		
		
		try {
			if(name.length() > 9) {
				name = name.substring(0, 9) + "...";
			}
			model.addFlashAttribute("name", name);

			return "redirect:/web/today?user=" + openID + "&name=" + URLEncoder.encode(name, "utf-8") + "&date="
				+ (new Date()).getTime();
		}
		catch (Exception e) {  
	        logger.debug(e.getMessage());
	    }

		// This will resolve to /WEB-INF/jsp/loginpage.jsp

		return "redirect:/web/today?user=" + openID + "&date="
				+ (new Date()).getTime();
	}
			
	@RequestMapping(value = "/today", method = RequestMethod.GET)
	public String toTodayPage(
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "date", required = false) String dateStr,
			Model model, HttpServletRequest request) {
		logger.debug("Received request to today page for " + user);

		if (dateStr == null) {
			dateStr = Long.toString((new Date()).getTime());
		}

		Map<String, ?> maps = model.asMap();
		String uid = (String) maps.get("uid");
 
		if (uid == null) {
			long dateTime = Long.parseLong(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(dateTime);
			List<DrinkRecord> records = dao.getRecordsByDate(user,
					cal.getTime());
			model.addAttribute("records", records);
			model.addAttribute("uid", user);
			model.addAttribute("qiezisaids", CommonUtil.getQiezisaids());
			model.addAttribute("readonly", true);
			model.addAttribute("drinkMen", 300 + dao.countDrinkMen() + eatDao.countEatMen());
			
			try {
				if(name.length() > 9) {
					name = name.substring(0, 9) + "...";
				}
				model.addAttribute("name", name);
			}
			catch (Exception e) {  
	            logger.debug(e.getMessage());
	      	}
		}

		model.addAttribute("date", dateStr);

		return "home";
	}

	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public String postToHistoryPage(
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "date", required = false) String dateStr,
			Model model, HttpServletRequest request) {
		logger.debug("Post request to history page for " + user);

		long dateTime = Long.parseLong(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateTime);
		String readonly = request.getParameter("readonly");

		model.addAttribute("uid", user);
		model.addAttribute("drinkMen", 300 + dao.countDrinkMen() + eatDao.countEatMen());
		model.addAttribute("date", dateStr);		
		model.addAttribute("readonly", readonly);

		try {
			if(name.length() > 9) {
				name = name.substring(0, 9) + "...";
			}
			model.addAttribute("name", name);
		}
		catch (Exception e) {  
            logger.debug(e.getMessage());
      	}

		return "history";
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String toHistoryPage(
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "date", required = false) String dateStr,
			Model model) {
		logger.debug("Received request to history page for " + user);
		
		if (dateStr == null) {
			dateStr = Long.toString((new Date()).getTime());
		}

		long dateTime = Long.parseLong(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateTime);

		model.addAttribute("uid", user);
		model.addAttribute("readonly", true);
		model.addAttribute("drinkMen", 300 + dao.countDrinkMen() + eatDao.countEatMen());
		model.addAttribute("date", dateStr);
		
		try {
			if(name.length() > 9) {
				name = name.substring(0, 9) + "...";
			}
			model.addAttribute("name", name);
		}
		catch (Exception e) {  
            logger.debug(e.getMessage());
      	}

		return "history";
	}
}