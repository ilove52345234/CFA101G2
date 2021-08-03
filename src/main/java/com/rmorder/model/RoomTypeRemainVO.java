package com.rmorder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class RoomTypeRemainVO implements java.io.Serializable {

	private Date liveDate; //住房日期
	private Integer roomCategoryId; //房型編號
	private String roomName; //房型名稱
	private Integer remainRoomNumber; //剩餘房間數
	

	public Date getLiveDate() {
		return liveDate;
	}
	public void setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
	}
	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public Integer getRemainRoomNumber() {
		return remainRoomNumber;
	}
	public void setRemainRoomNumber(Integer remainRoomNumber) {
		this.remainRoomNumber = remainRoomNumber;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	@Override    
	public String toString() {
		return "RoomTypeRemainVO [liveDate=" + liveDate + ", roomCategoryId=" + roomCategoryId + ", roomName="
				+ roomName + ", remainRoomNumber=" + remainRoomNumber + "]";
	}

	

}
