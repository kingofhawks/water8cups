package com.bigyellow.hm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
		@NamedQuery(name = "WeixinUser.findAll", query = "SELECT u FROM WeixinUser u"),
		@NamedQuery(name = "WeixinUser.findByID", query = "SELECT DISTINCT u FROM WeixinUser u WHERE u.openID = :openID") })
@XmlRootElement
public class WeixinUser extends BaseEntity {

	private String openID;
	private String nickname;

	public WeixinUser(){
	}
	
	public WeixinUser(String openID, String nickname) {
		super();
		this.openID = openID;
		this.nickname = nickname;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((openID == null) ? 0 : openID.hashCode());
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
		WeixinUser other = (WeixinUser) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (openID == null) {
			if (other.openID != null)
				return false;
		} else if (!openID.equals(other.openID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeixinUser [openID=" + openID + ", nickname=" + nickname + "]";
	}

}
