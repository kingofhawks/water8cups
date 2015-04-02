package com.bigyellow.hm.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bigyellow.hm.entity.EatRecord;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date Jan 26, 2015
 */
@Repository(value = "EatRecordDaoImpl")
@Transactional(readOnly = true)
public class EatRecordDaoImpl extends BaseJpaDaoSupport<Integer, EatRecord>
		implements EatRecordDao {

	public static final Logger logger = LoggerFactory
			.getLogger(DrinkRecordDaoImpl.class);
	
	public EatRecord getTodayRecords(String openID) {
		Date currentTime = Calendar.getInstance().getTime();
		return getRecordsByDate(openID, currentTime);
		
	}

	public EatRecord getRecordsByDate(String openID, Date date) {
		logger.info("date : " + date.toString());
		EatRecord result = null;
		Query query = this.getEm()
				.createNamedQuery("EatRecord.findTodayRecords")
				.setParameter("openID", openID)
				.setParameter("time", date, TemporalType.DATE);
		List list = query.getResultList();
		if ( list != null && list.size() > 0) {
			result = (EatRecord) query.getSingleResult();
		}
		return result;

	}

	@Override
	public long countEatMen() {
		long result = 0;
		Query query = this.getEm().createQuery(
				"SELECT count(r) FROM EatRecord r GROUP BY uid,openID");
		// .setParameter("time", date, TemporalType.DATE);
		List<Long> list = (List<Long>) query.getResultList();
		if (list != null && !list.isEmpty()) {
			result = list.size();
		}
		logger.info("count eat men:" + result);
		return result;
	}

	@Override
	public List<EatRecord> getRecordsWithUidNotNull() {
		List<EatRecord> result = new ArrayList<EatRecord>();
		Query query = this.getEm()
				.createNamedQuery("EatRecord.findAllRecordsUidNotNull");
		result = query.getResultList();
		return result;
	}

	@Override
	public void resolveOpenIDForOldSubscribeUser(String openID, String nickName) {
		logger.info("resolveOpenIDForOldSubscribeUser:" + openID + "-" + nickName);
		Query query = this
				.getEm()
				.createQuery(
						"FROM EatRecord r where r.openID is null and r.nickName = :nickname")
				.setParameter("nickname", nickName);
		List<EatRecord> result = query.getResultList();

		if (result != null) {
			logger.info("size :" + result.size());
			for (EatRecord record : result) {
				record.setOpenID(openID);
				this.getEm().merge(record);
				this.getEm().flush();
			}
		}

	}

	@Override
	public boolean isFirstLoginUser(String openID) {
		long result = 0;
		Query query = this
				.getEm()
				.createQuery(
						"SELECT count(r) FROM EatRecord r where r.openID = :openID")
				.setParameter("openID", openID);
		List<Long> list = (List<Long>) query.getResultList();
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		logger.info("isFirstLoginUser:" + (result == 0));
		return (result == 0);
	}
}
