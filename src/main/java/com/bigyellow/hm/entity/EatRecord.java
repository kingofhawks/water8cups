package com.bigyellow.hm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date Jan 26, 2015
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "EatRecord.findAllRecords", query = "SELECT r FROM EatRecord r WHERE r.openID = :openID"),
		@NamedQuery(name = "EatRecord.findTodayRecords", query = "SELECT DISTINCT r FROM EatRecord r WHERE r.openID = :openID and r.time = :time"),
		@NamedQuery(name = "EatRecord.findAllRecordsUidNotNull", query = "SELECT DISTINCT r FROM EatRecord r WHERE r.uid is not null and r.nickName is null "),
		@NamedQuery(name = "EatRecord.findHistoryRecord", query = "SELECT DISTINCT r FROM EatRecord r WHERE r.openID = :openID and r.time < :time ORDER BY recordTime desc") })
public class EatRecord extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 1291964316824201900L;

	// openID for qiezijiankangguanjia Subscribe
	private String uid;

	// openID for qiezijun weixin service
	private String openID;
	
	private String nickName;

	@Temporal(TemporalType.DATE)
	private Date time;

	@Temporal(TemporalType.TIMESTAMP)
	private Date recordTime;

	private int starLevel;

	private boolean milk;
	private boolean beanProduct;
	private boolean egg;
	private boolean vegetable;
	private boolean fruit;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public boolean isMilk() {
		return milk;
	}

	public void setMilk(boolean milk) {
		this.milk = milk;
	}

	public boolean isBeanProduct() {
		return beanProduct;
	}

	public void setBeanProduct(boolean beanProduct) {
		this.beanProduct = beanProduct;
	}

	public boolean isEgg() {
		return egg;
	}

	public void setEgg(boolean egg) {
		this.egg = egg;
	}

	public boolean isVegetable() {
		return vegetable;
	}

	public void setVegetable(boolean vegetable) {
		this.vegetable = vegetable;
	}

	public boolean isFruit() {
		return fruit;
	}

	public void setFruit(boolean fruit) {
		this.fruit = fruit;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (beanProduct ? 1231 : 1237);
		result = prime * result + (egg ? 1231 : 1237);
		result = prime * result + (fruit ? 1231 : 1237);
		result = prime * result + (milk ? 1231 : 1237);
		result = prime * result + ((openID == null) ? 0 : openID.hashCode());
		result = prime * result
				+ ((recordTime == null) ? 0 : recordTime.hashCode());
		result = prime * result + starLevel;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + (vegetable ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EatRecord other = (EatRecord) obj;
		if (beanProduct != other.beanProduct)
			return false;
		if (egg != other.egg)
			return false;
		if (fruit != other.fruit)
			return false;
		if (milk != other.milk)
			return false;
		if (openID == null) {
			if (other.openID != null)
				return false;
		} else if (!openID.equals(other.openID))
			return false;
		if (recordTime == null) {
			if (other.recordTime != null)
				return false;
		} else if (!recordTime.equals(other.recordTime))
			return false;
		if (starLevel != other.starLevel)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (vegetable != other.vegetable)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EatRecord [uid=" + uid + ", openID=" + openID + ", time="
				+ time + ", recordTime=" + recordTime + ", starLevel="
				+ starLevel + ", milk=" + milk + ", beanProduct=" + beanProduct
				+ ", egg=" + egg + ", vegetable=" + vegetable + ", fruit="
				+ fruit + "]";
	}

}
