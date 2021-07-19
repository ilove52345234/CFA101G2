package com.rschedule.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RsVO implements java.io.Serializable{
	private Integer roomScheduleId; //流水編號
	private Integer roomCategoryId;	//房型ID
	private Date roomScheduleDate;	//日期
	private Integer roomAmount; //房間原始數量
	private Integer roomRsvBooked; //未離店房間數量
	private Integer roomCheckOut;	//房間當天入住數量
	private Integer roomCheckIn; 	//房間退房數量


	public RsVO() {
	}

	public RsVO(Integer roomScheduleId, Integer roomCategoryId, Date roomScheduleDate, Integer roomAmount, Integer roomRsvBooked, Integer roomCheckOut, Integer roomCheckIn) {
		this.roomScheduleId = roomScheduleId;
		this.roomCategoryId = roomCategoryId;
		this.roomScheduleDate = roomScheduleDate;
		this.roomAmount = roomAmount;
		this.roomRsvBooked = roomRsvBooked;
		this.roomCheckOut = roomCheckOut;
		this.roomCheckIn = roomCheckIn;
	}

	@Override
	public String toString() {
		return "RsVO{" +
				"roomScheduleId=" + roomScheduleId +
				", roomCategoryId=" + roomCategoryId +
				", roomScheduleDate=" + roomScheduleDate +
				", roomAmount=" + roomAmount +
				", roomRsvBooked=" + roomRsvBooked +
				", roomCheckOut=" + roomCheckOut +
				", roomCheckIn=" + roomCheckIn +
				'}';
	}

	public Integer getRoomScheduleId() {
		return roomScheduleId;
	}

	public void setRoomScheduleId(Integer roomScheduleId) {
		this.roomScheduleId = roomScheduleId;
	}

	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	public Date getRoomScheduleDate() {
		return roomScheduleDate;
	}

	public void setRoomScheduleDate(Date roomScheduleDate) {
		this.roomScheduleDate = roomScheduleDate;
	}

	public Integer getRoomAmount() {
		return roomAmount;
	}

	public void setRoomAmount(Integer roomAmount) {
		this.roomAmount = roomAmount;
	}

	public Integer getRoomRsvBooked() {
		return roomRsvBooked;
	}

	public void setRoomRsvBooked(Integer roomRsvBooked) {
		this.roomRsvBooked = roomRsvBooked;
	}

	public Integer getRoomCheckOut() {
		return roomCheckOut;
	}

	public void setRoomCheckOut(Integer roomCheckOut) {
		this.roomCheckOut = roomCheckOut;
	}

	public Integer getRoomCheckIn() {
		return roomCheckIn;
	}

	public void setRoomCheckIn(Integer roomCheckIn) {
		this.roomCheckIn = roomCheckIn;
	}
}
