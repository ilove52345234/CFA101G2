package com.rmorderlist.model;

import java.sql.Timestamp;

public class RmolVO implements java.io.Serializable{
	private Integer orderListId;
	private Integer roomOrderId;
	private Integer roomCategoryId;
	private Integer roomPromotionId;
	private Integer roomId;
	private String memName;
	private Integer memNumber;
	private Timestamp checkInDate;
	private Timestamp CheckOutDate;
	private Integer roomTotalPrice;
	public RmolVO() {
	}
	public Integer getOrderListId() {
		return orderListId;
	}
	public void setOrderListId(Integer orderListId) {
		this.orderListId = orderListId;
	}
	public Integer getRoomOrderId() {
		return roomOrderId;
	}
	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}
	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public Integer getRoomPromotionId() {
		return roomPromotionId;
	}
	public void setRoomPromotionId(Integer roomPromotionId) {
		this.roomPromotionId = roomPromotionId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public Integer getMemNumber() {
		return memNumber;
	}
	public void setMemNumber(Integer memNumber) {
		this.memNumber = memNumber;
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
	public Integer getRoomTotalPrice() {
		return roomTotalPrice;
	}
	public void setRoomTotalPrice(Integer roomTotalPrice) {
		this.roomTotalPrice = roomTotalPrice;
	}
	

}
