package com.rmorder.model;

import java.sql.Timestamp;

public class BookRmoVO implements java.io.Serializable{
	private String memName;
	private String memEmail;
	private String memPhone;
	private String roomName;
	private Integer roomOrderId;
	private Timestamp checkInDate;
	private Timestamp CheckOutDate;
	private Integer days;   //存入的資料是從dao裏查詢 預定訂單明細來的 指令為DATEDIFF(rol.CHECK_OUT_DATE, rol.CHECK_IN_DATE) as 住房天數
	private Integer memNumber;
	private Integer roomPrice;
	private Integer roomTotalPrice;//單一房間價格
	private Integer roomCount;
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getRoomOrderId() {
		return roomOrderId;
	}
	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}
	public Timestamp getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Timestamp checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Timestamp getCheckOutDate() {
		return CheckOutDate;
	}
	public void setCheckOutDate(Timestamp checkOutDate) {
		CheckOutDate = checkOutDate;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getMemNumber() {
		return memNumber;
	}
	public void setMemNumber(Integer memNumber) {
		this.memNumber = memNumber;
	}
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getRoomTotalPrice() {
		return roomTotalPrice;
	}
	public void setRoomTotalPrice(Integer roomTotalPrice) {
		this.roomTotalPrice = roomTotalPrice;
	}
	
	

	@Override
	public String toString() {
		return "BookRmoVO [memName=" + memName + ", memEmail=" + memEmail + ", memPhone=" + memPhone + ", roomName="
				+ roomName + ", roomOrderId=" + roomOrderId + ", checkInDate=" + checkInDate + ", CheckOutDate="
				+ CheckOutDate + ", days=" + days + ", memNumber=" + memNumber + ", roomPrice=" + roomPrice
				+ ", roomTotalPrice=" + roomTotalPrice + "]";
	}

	
	

}
