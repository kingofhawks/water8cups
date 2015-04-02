
package com.bigyellow.hm.rs;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.bigyellow.hm.common.Constants;
import com.bigyellow.hm.common.ValidateResult;
import com.bigyellow.hm.dao.EatRecordDao;
import com.bigyellow.hm.dao.GenericDaoException;
import com.bigyellow.hm.entity.EatRecord;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date Jan 26, 2015
 */

@Path("/eatrecord/")
public class EatRecordResource {

	public static final Logger logger = LoggerFactory
			.getLogger(EatRecordResource.class);

	@Autowired(required =  true)
	private EatRecordDao dao;
	
	@Autowired(required = true)
	private MessageSource messageSource;

	public EatRecordResource() {

	}

	@Path("/add/{openID}/{eatFlag}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ValidateResult add(@PathParam("openID") String openID,
			@PathParam("eatFlag") Integer eatFlag,
			@DefaultValue("true") @QueryParam("eatValue") boolean eatValue ) {
		logger.debug("eatRecordAdd for openID: " + openID + ", eat number : "
				+ eatFlag);
		ValidateResult result = new ValidateResult();
		try {
			EatRecord existed = this.dao.getTodayRecords(openID);
			if (existed == null) {
				existed = new EatRecord();
				existed.setOpenID(openID);
				Date current = Calendar.getInstance().getTime();
				existed.setTime(current);
				existed.setRecordTime(current);
			}
			resolveRecordWithNum(eatFlag ,eatValue,  existed);
			logger.info("save to database : " + existed.toString());
			this.dao.saveOrUpdate(existed);
			result.setObj(existed);
		} catch (GenericDaoException e) {
			logger.error(e.getErrorMsg() + " : " + e.getMessage());
			result.resolveErrorMsg(e.getErrorMsg());
		}

		return result;
	}

	

	@Path("/today/{openID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EatRecord getToday(@PathParam("openID") String openID,
			@QueryParam(value = "date") long date) {
		logger.info("get today for openID: " + openID  + ", " + date);
		Date currentDate = null;
		if(date == 0) {
			currentDate = new Date();
		} else {
			currentDate = new Date(date);
		}
		logger.info("date  " + currentDate );
		EatRecord result = this.dao.getRecordsByDate(openID,currentDate);
		if(result == null) {
			result = new EatRecord();
		}
		return result;
	}
	
	@Path("/totalEat")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public long getEatMenNumber() {
		long result = this.dao.countEatMen();
		logger.debug("get total eat men: " + result);
		return result;
	}
	
	private void resolveRecordWithNum(int eatFlag, boolean eatValue, EatRecord existed) {
		if (eatFlag == Constants.EAT_MILK) {
			existed.setMilk(eatValue);
		} else if (eatFlag == Constants.EAT_BEANPRODUCT) {
			existed.setBeanProduct(eatValue);
		} else if (eatFlag == Constants.EAT_EGG) {
			existed.setEgg(eatValue);
		} else if (eatFlag == Constants.EAT_VEGETABLE) {
			existed.setVegetable(eatValue);
		} else if (eatFlag == Constants.EAT_FRUIT) {
			existed.setFruit(eatValue);
		}
	}

}
