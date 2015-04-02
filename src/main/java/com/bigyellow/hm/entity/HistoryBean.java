package com.bigyellow.hm.entity;

import java.io.Serializable;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date Dec 10, 2014
 */
public class HistoryBean implements Serializable {
	
	private String uid;
	private int level;
	private String date;
	private int days; //unremitting drinking days

	public HistoryBean(String uid, int level, String date) {
		this.uid = uid;
		this.level = level;
		this.date = date;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	
	
	
}
