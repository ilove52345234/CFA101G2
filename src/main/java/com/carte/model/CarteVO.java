package com.carte.model;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class CarteVO implements Serializable{
	private Integer userId;
	private Integer memId;
	private String userName;
	private Blob userPic;
	private Integer userStatus;
	private Timestamp userUpdate;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Blob getUserPic() {
		return userPic;
	}
	public void setUserPic(Blob userPic) {
		this.userPic = userPic;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public Timestamp getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(Timestamp userUpdate) {
		this.userUpdate = userUpdate;
	}
}
