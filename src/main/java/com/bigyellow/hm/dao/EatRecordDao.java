
package com.bigyellow.hm.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bigyellow.hm.entity.EatRecord;


/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date Jan 26, 2015
 */
public interface EatRecordDao extends BaseDao<Serializable, EatRecord>{

	EatRecord getTodayRecords(String openID);
	EatRecord getRecordsByDate(String openID , Date date);
	
	long countEatMen();
	
	/* this api is used for upgrade older weixin subscribe user to weixin service */
	List<EatRecord> getRecordsWithUidNotNull();
	
	/* this api is used for upgrade older weixin subscribe user to weixin service */
	void resolveOpenIDForOldSubscribeUser(String openID, String nickName);
	
	/* this api is used for first time check */
	boolean isFirstLoginUser(String openID);
	
}
