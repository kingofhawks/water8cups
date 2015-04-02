package com.bigyellow.hm.dao;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bigyellow.hm.entity.WeixinUser;

@Repository(value = "WeixinUserDaoImpl")
@Transactional(readOnly = true)
public class WeixinUserDaoImpl extends
		BaseJpaDaoSupport<Long, WeixinUser> implements WeixinUserDao {
	
	public static final Logger logger = LoggerFactory
			.getLogger(WeixinUserDaoImpl.class);
	
	
	public WeixinUser findByOpenID(String openID) {

		Query query = this.getEm().createNamedQuery("WeixinUser.findByID")
				.setParameter("openID", openID);
		List list = query.getResultList();
		WeixinUser result = null;
		if (list != null && !list.isEmpty()) {
			result = (WeixinUser) list.get(0);
		}
		logger.info("get weixin user by id:" + openID + " , " + list.size());
		return result;

	}
}
