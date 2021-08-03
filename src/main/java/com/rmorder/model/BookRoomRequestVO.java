package com.rmorder.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class BookRoomRequestVO implements java.io.Serializable {

	private Integer roomCategoryId;
	private Timestamp checkInDate;
	private Timestamp checkOutDate;
	private Integer memNumber;
	private Integer roomNumber;
	private Object memberData;
	
	private String roomName; //只有購物車在用


	public BookRoomRequestVO() {
		super();
	}

	public BookRoomRequestVO(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
    //command+shift+G 可以查看該建構式方法
	public BookRoomRequestVO(Integer roomCategoryId, Timestamp checkInDate, Timestamp checkOutDate, Integer memNumber,
			Integer roomNumber, Object memberData) {
		this.roomCategoryId = roomCategoryId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.memNumber = memNumber;
		this.roomNumber = roomNumber;
		this.memberData = memberData;
	}

	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	public Timestamp getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Timestamp checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Timestamp getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Timestamp checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getMemNumber() {
		return memNumber;
	}

	public void setMemNumber(Integer memNumber) {
		this.memNumber = memNumber;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Object getMemberData() {
		return memberData;
	}

	public void setMemberData(Object memberData) {
		this.memberData = memberData;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "BookRoomRequestVO [roomCategoryId=" + roomCategoryId + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", memNumber=" + memNumber + ", roomNumber=" + roomNumber
				+ ", memberData=" + memberData + ", roomName=" + roomName + "]";
	}



}
