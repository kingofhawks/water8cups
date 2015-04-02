package com.bigyellow.hm.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigyellow.hm.common.Constants;
import com.bigyellow.hm.common.WeixinUtil;
import com.bigyellow.hm.dao.DrinkRecordDao;
import com.bigyellow.hm.dao.EatRecordDao;
import com.bigyellow.hm.dao.GenericDaoException;
import com.bigyellow.hm.entity.DrinkRecord;
import com.bigyellow.hm.entity.EatRecord;

@Service
public class InitService {

	public static final Logger logger = LoggerFactory
			.getLogger(InitService.class);

	@Autowired
	private DrinkRecordDao dao;

	@Autowired
	private EatRecordDao eatRecordDao;

	@PostConstruct
	public void init() {
		logger.info("================init===============");
		upgradeDrinkRecord();
		upgradeEatRecord();
	}

	private void upgradeDrinkRecord() {
		List<DrinkRecord> result = this.dao.getRecordsWithUidNotNull();
		if (result != null && !result.isEmpty()) {
			logger.info("record not null size : " + result.size());

			Set<String> emptyUidSet = new HashSet<String>();
			Map<String, String> uidMaps = new HashMap<String, String>();
			for (DrinkRecord rec : result) {
				emptyUidSet.add(rec.getUid());
			}

			logger.info("uid  size : " + emptyUidSet.size());
			for (String uid : emptyUidSet) {
				String nickname = WeixinUtil.getNickNameFromSubscribe(uid);
				try {
					if (nickname != null && !"".equals(nickname)) {
						uidMaps.put(uid, URLEncoder.encode(nickname, "utf-8"));
					} else {
						uidMaps.put(uid, Constants.UNSUBSCRIBE_USER);
					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			for (DrinkRecord rec : result) {
				String nickname = uidMaps.get(rec.getUid());
				try {
					rec.setNickName(new String(nickname.getBytes(), "utf-8"));
					dao.saveOrUpdate(rec);
				} catch (GenericDaoException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}

	private void upgradeEatRecord() {
		List<EatRecord> result = this.eatRecordDao.getRecordsWithUidNotNull();
		if (result != null && !result.isEmpty()) {
			logger.info("eat record wit uid not null size : " + result.size());

			Set<String> emptyUidSet = new HashSet<String>();
			Map<String, String> uidMaps = new HashMap<String, String>();
			for (EatRecord rec : result) {
				emptyUidSet.add(rec.getUid());
			}

			logger.info("eat record uid  size : " + emptyUidSet.size());
			for (String uid : emptyUidSet) {
				String nickname = WeixinUtil.getNickNameFromSubscribe(uid);
				try {
					if (nickname != null && !"".equals(nickname)) {
						uidMaps.put(uid, URLEncoder.encode(nickname, "utf-8"));
					} else {
						uidMaps.put(uid, Constants.UNSUBSCRIBE_USER);
					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			for (EatRecord rec : result) {
				String nickname = uidMaps.get(rec.getUid());
				try {
					rec.setNickName(new String(nickname.getBytes(), "utf-8"));
					eatRecordDao.saveOrUpdate(rec);
				} catch (GenericDaoException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
}
