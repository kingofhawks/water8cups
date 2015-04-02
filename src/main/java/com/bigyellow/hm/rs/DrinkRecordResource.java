package com.bigyellow.hm.rs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bigyellow.hm.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.bigyellow.hm.common.CommonUtil;
import com.bigyellow.hm.common.Constants;
import com.bigyellow.hm.common.DateUtil;
import com.bigyellow.hm.common.ValidateResult;
import com.bigyellow.hm.dao.DrinkRecordDao;
import com.bigyellow.hm.dao.GenericDaoException;
import com.bigyellow.hm.entity.DrinkRecord;
import com.bigyellow.hm.entity.HistoryBean;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2013-10-22
 */

@Path("/drinkrecord/")
public class DrinkRecordResource {

	public static final Logger logger = LoggerFactory
			.getLogger(DrinkRecordResource.class);
	public static final int PAGE_SIZE = 15;

	@Autowired
	DrinkRecordDao dao;

	@Autowired(required = true)
	private MessageSource messageSource;

	public DrinkRecordResource() {

	}

	@Path("/add/{openID}/{cupNumber}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ValidateResult add(@PathParam("openID") String openID,
			@PathParam("cupNumber") Integer cupNumber,
			@QueryParam("nickname") String nickname) {
		logger.info("drinkRecordAdd for openID: " + openID + ", cup number : "
				+ cupNumber + ", nickname : " + nickname);
		ValidateResult result = new ValidateResult();
		try {
			DrinkRecord existed = this.dao.getTodayRecordByCup(openID, cupNumber);
			if (existed == null) {
				existed = new DrinkRecord();
				existed.setOpenID(openID);
				existed.setNickName(nickname);
				Date current = Calendar.getInstance().getTime();
				existed.setTime(current);
				existed.setRecordTime(current);
				existed.setCupNumber(cupNumber);
				existed.setStarLevel(CommonUtil.calculateStarLevel(current,
						Constants.drinkStandardTime[cupNumber - 1]));
				logger.info("save to database : " + existed.toString());
			}
			this.dao.saveOrUpdate(existed);
			result.setObj(existed);
		} catch (GenericDaoException e) {
			logger.error(e.getErrorMsg() + " : " + e.getMessage());
			result.resolveErrorMsg(e.getErrorMsg());
		}

		return result;
	}

	@Path("/history/{openID}/{page}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<HistoryBean> getHistory(@PathParam("openID") String openID,
			@PathParam("page") Integer page,
			@QueryParam(value = "date") long date) {
		logger.info("get history for openID: " + openID + ",page : " + page + ",date: " +  date);
		Date historyDate = null;
		if(date == 0) {
			historyDate = new Date();
		} else {
			historyDate = new Date(date);
		}
		List<DrinkRecord> drinkRecords = this.dao.getHistoryList(openID , historyDate);
		List<HistoryBean> result = resolveHistoryBean(openID, drinkRecords,page , historyDate );
		logger.info("get history record size : " + result.size());
		return result;
	}


	@Path("/today/{openID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<DrinkRecord> getToday(@PathParam("openID") String openID) {
		logger.debug("get today for openID: " + openID);
		List<DrinkRecord> result = this.dao.getTodayRecords(openID);
		return result;
	}

	@Path("/{openID}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ValidateResult clear(@PathParam("openID") String openID) {
		logger.info("clear for openID: " + openID);
		ValidateResult result = new ValidateResult();
		this.dao.clearRecords(openID);
		return result;
	}
	
	private List<HistoryBean> resolveHistoryBean(String openID,
			List<DrinkRecord> drinkRecords, Integer page , Date historyDate) {
		List<HistoryBean> rawResult = new ArrayList<HistoryBean>();
		List<HistoryBean> result = new ArrayList<HistoryBean>();
		Date startDate = null;
		if (drinkRecords != null && !drinkRecords.isEmpty()) {
			startDate = drinkRecords.get(drinkRecords.size() - 1).getTime();
		}
		if (startDate != null) {
			long diff = historyDate.getTime() - startDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);

			Map<String, List<DrinkRecord>> map = new HashMap<String, List<DrinkRecord>>();
			for (DrinkRecord rec : drinkRecords) {
				String key = DateUtil.format(rec.getTime(),
						DateUtil.patterns[0]);

				if (map.containsKey(key)) {
					List<DrinkRecord> existed = map.get(key);
					existed.add(rec);
				} else {
					List<DrinkRecord> newList = new ArrayList<DrinkRecord>();
					newList.add(rec);
					map.put(key, newList);
				}
			}

			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(startDate);
			int unremittingDays = 0;
			for (int i = 0; i < diffDays; i++) {
				String innerKey = DateUtil.format(cal.getTimeInMillis(),
						DateUtil.patterns[0]);
				if (map.containsKey(innerKey)) {
					HistoryBean bean = new HistoryBean(openID,
							generateLevel(map.get(innerKey)), innerKey);
					if (bean.getLevel() != 0) {
						bean.setDays(++unremittingDays);
					} else {
						unremittingDays = 0;
					}
					rawResult.add(bean);
				} else {
					rawResult.add(new HistoryBean(openID, 0, innerKey));
					unremittingDays = 0;
				}
				cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
			}
			
			Collections.reverse(rawResult);
			
			if(page == 0) {
				if(rawResult.size() > PAGE_SIZE) {
					result = rawResult.subList(0, PAGE_SIZE);
				} else {
					result = rawResult;
				}
			} else if(page == 1) {
				if(rawResult.size() > 2*PAGE_SIZE) {
					result = rawResult.subList(PAGE_SIZE, 2*PAGE_SIZE);
				} else if(rawResult.size() > PAGE_SIZE){
					result = rawResult.subList(PAGE_SIZE, rawResult.size());
				} 
			} else {
				if(rawResult.size() > 3*PAGE_SIZE) {
					result = rawResult.subList(2*PAGE_SIZE, 3*PAGE_SIZE);
				} else if(rawResult.size() > 2*PAGE_SIZE){
					result = rawResult.subList(2*PAGE_SIZE, rawResult.size());
				} 
			}
		}
		
		return result;
	}

	private int generateLevel(List<DrinkRecord> list) {
		int result = 0;
		int starNumber = 0;
		if(list != null && !list.isEmpty() && list.size() == 8) {
			for(DrinkRecord rec : list) {
				starNumber += rec.getStarLevel();
			}
		}
		if(starNumber >= 20) {
			result = 3;
		} else if(starNumber >= 16) {
			result = 2;
		} else if(starNumber > 0) {
			result = 1;
		} 
		return result;
	}


//    @Path("/users")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public List<AppUser> getAppUsers() {
//        logger.debug("getAppUsers now**** ");
//        List<AppUser> result = this.dao.getAllUsers();
//        return result;
//    }
//
//    @Path("/users/{fid}")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public AppUser getAppUserById(@PathParam("fid") String fid) {
//        logger.debug("getAppUser now**** "+fid);
//        AppUser result = this.dao.getUserById(Integer.valueOf(fid));
//        return result;
//    }



}
