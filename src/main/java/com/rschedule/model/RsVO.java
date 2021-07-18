package com.rschedule.model;

import java.sql.Timestamp;

public class RsVO implements java.io.Serializable{
	private Integer roomScheduleId;
	private Integer roomCategoryId;
	private Timestamp roomScheduleDate;
	private Integer roomAmount;
	private Integer roomRsvBooked;
	public RsVO() {
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
	public Timestamp getRoomScheduleDate() {
		return roomScheduleDate;
	}
	public void setRoomScheduleDate(Timestamp roomScheduleDate) {
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

}
