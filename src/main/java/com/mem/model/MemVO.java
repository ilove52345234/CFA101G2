package com.mem.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class MemVO implements Serializable {
	private Integer memId;
	private String memAccount;
	private String memName;
	private String memPassword;
	private String memAddress;
	private String memPhone;
	private String memUid;
	private String memEmail;
	private String memSex;
	private Date memDob;
	private Integer memStatus;
	private Timestamp memUpdate;
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemUid() {
		return memUid;
	}
	public void setMemUid(String memUid) {
		this.memUid = memUid;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemSex() {
		return memSex;
	}
	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}
	public Date getMemDob() {
		return memDob;
	}
	public void setMemDob(Date memDob) {
		this.memDob = memDob;
	}
	public int getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public Timestamp getMemUpdate() {
		return memUpdate;
	}
	public void setMemUpdate(Timestamp memUpdate) {
		this.memUpdate = memUpdate;
	}
}
