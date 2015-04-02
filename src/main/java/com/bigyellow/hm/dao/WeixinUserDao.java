package com.bigyellow.hm.dao;

import java.io.Serializable;

import com.bigyellow.hm.entity.WeixinUser;

public interface WeixinUserDao extends BaseDao<Serializable, WeixinUser> {
	WeixinUser findByOpenID(String openID);
}
