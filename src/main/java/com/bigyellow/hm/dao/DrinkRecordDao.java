package com.bigyellow.hm.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bigyellow.hm.entity.DrinkRecord;

public interface DrinkRecordDao extends BaseDao<Serializable, DrinkRecord>{
	List<DrinkRecord> getTodayRecords(String openID);
	List<DrinkRecord> getRecordsByDate(String openID , Date date);
	DrinkRecord getTodayRecordByCup(String openID , Integer cupNumber);
	List<DrinkRecord> getHistoryList(String openID , Date date);
	
	long countDrinkMen();
	
	List<DrinkRecord> getAllRecords(String openID);
	void clearRecords(String openID);
	
	/* this api is used for upgrade older weixin subscribe user to weixin service */
	List<DrinkRecord> getRecordsWithUidNotNull();
	
	/* this api is used for upgrade older weixin subscribe user to weixin service */
	void resolveOpenIDForOldSubscribeUser(String openID, String nickName);
	
}
