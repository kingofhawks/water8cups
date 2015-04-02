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

import com.bigyellow.hm.common.DateUtil;
import com.bigyellow.hm.entity.DrinkRecord;

@Repository(value = "DrinkRecordDaoImpl")
@Transactional(readOnly = true)
public class DrinkRecordDaoImpl extends BaseJpaDaoSupport<Integer, DrinkRecord>
		implements DrinkRecordDao {

	public static final Logger logger = LoggerFactory
			.getLogger(DrinkRecordDaoImpl.class);

	public List<DrinkRecord> getTodayRecords(String openID) {
		Date currentTime = Calendar.getInstance().getTime();
		return getRecordsByDate(openID, currentTime);
	}

	public List<DrinkRecord> getRecordsByDate(String openID, Date date) {
		// Date currentDate = DateUtil.parse(DateUtil.format(currentTime,
		// DateUtil.patterns[0]));
		List<DrinkRecord> result = new ArrayList<DrinkRecord>();
		Query query = this.getEm()
				.createNamedQuery("DrinkRecord.findTodayRecords")
				.setParameter("openID", openID)
				.setParameter("time", date, TemporalType.DATE);
		result = query.getResultList();
		logger.info("getRecordsByDate:" + result.size());
		// for (DrinkRecord rec : result) {
		// logger.info(rec.toString());
		// }
		return result;
	}

	public DrinkRecord getTodayRecordByCup(String openID, Integer cupNumber) {
		Date currentTime = Calendar.getInstance().getTime();
		Date currentDate = DateUtil.parse(DateUtil.format(currentTime,
				DateUtil.patterns[0]));
		DrinkRecord result = null;
		Query query = this.getEm()
				.createNamedQuery("DrinkRecord.findTodayRecordByCup")
				.setParameter("openID", openID)
				.setParameter("time", currentDate, TemporalType.DATE)
				.setParameter("cupNumber", cupNumber);
		;
		List list = query.getResultList();
		if (list != null && !list.isEmpty()) {
			result = (DrinkRecord) query.getSingleResult();
		}
		// if(result != null){
		// logger.info(result.toString());
		// }
		return result;
	}

	public List<DrinkRecord> getHistoryList(String openID , Date date) {
//		Date date = Calendar.getInstance().getTime();
		Date currentDate = DateUtil.parse(DateUtil.format(date,
				DateUtil.patterns[0]));
		List<DrinkRecord> result = (List<DrinkRecord>) this.getEm()
				.createNamedQuery("DrinkRecord.findHistoryRecord")
				.setParameter("openID", openID)
				.setParameter("time", currentDate, TemporalType.DATE)
				.getResultList();
		logger.info("openID:" + openID + "," + DateUtil.format(date,DateUtil.patterns[0]));
		logger.info("getHistoryList:" + result.size());
		return result;
	}

	public void clearRecords(String openID) {
		List<DrinkRecord> result = new ArrayList<DrinkRecord>();
		result = this.getAllRecords(openID);
		logger.info("clear record: " + result.size());
		for (DrinkRecord rec : result) {
			this.getEm().remove(rec);
		}
		this.getEm().flush();
	}

	public List<DrinkRecord> getAllRecords(String openID) {
		List<DrinkRecord> result = new ArrayList<DrinkRecord>();
		Query query = this.getEm()
				.createNamedQuery("DrinkRecord.findAllRecords")
				.setParameter("openID", openID);
		result = query.getResultList();
		return result;
	}

	@Override
	public long countDrinkMen() {
		long result = 0;
		Query query = this
				.getEm()
				.createQuery(
						"SELECT count(r) FROM DrinkRecord r GROUP BY uid,openID");
//				.setParameter("time", date, TemporalType.DATE);
		List<Long> list = (List<Long>) query.getResultList();
		if (list != null && !list.isEmpty()) {
			result = list.size();
		}
		logger.info("countDrinkMen:" + result);
		return result;
	}

	@Override
	public List<DrinkRecord> getRecordsWithUidNotNull() {
		List<DrinkRecord> result = new ArrayList<DrinkRecord>();
		Query query = this.getEm()
				.createNamedQuery("DrinkRecord.findAllRecordsUidNotNull");
		result = query.getResultList();
		return result;
	}

	@Override
	public void resolveOpenIDForOldSubscribeUser(String openID, String nickName) {
		logger.info("resolveOpenIDForOldSubscribeUser:" + openID + "-" + nickName);
		Query query = this
				.getEm()
				.createQuery(
						"FROM DrinkRecord r where r.openID is null and r.nickName = :nickname")
				.setParameter("nickname", nickName);
		List<DrinkRecord> result = query.getResultList();

		if (result != null) {
			logger.info("size :" + result.size());
			for (DrinkRecord record : result) {
				record.setOpenID(openID);
				this.getEm().merge(record);
				this.getEm().flush();
			}
		}

	}

}
