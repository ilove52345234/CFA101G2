package com.rmorder.model;

import java.sql.Timestamp;

public class MemRoomOrderVO implements java.io.Serializable{
	
	private Integer roomOrderId;
	private Integer memId;
	private Timestamp orderDate;
	private Integer totalPrice;
	private Timestamp checkInDate;
	private String roomName;
	
	public Integer getRoomOrderId() {
		return roomOrderId;
	}
	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Timestamp getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Timestamp checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	@Override
	public String toString() {
		return "MemRoomOrderVO [roomOrderId=" + roomOrderId + ", memId=" + memId + ", orderDate=" + orderDate
				+ ", totalPrice=" + totalPrice + ", checkInDate=" + checkInDate + ", roomName=" + roomName + "]";
	}
	//加toString方法才可以印出System.out.println("memRoomOrderVO:" + memRoomOrderVOs); 不然就會變成亂碼
	
	


}
