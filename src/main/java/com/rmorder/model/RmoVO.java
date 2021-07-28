package com.rmorder.model;

import java.sql.Timestamp;

public class RmoVO implements java.io.Serializable{
	private Integer roomOrderId;
	private Integer memId;
	private Timestamp orderDate;
	private Integer roomOrderStatus; //0:未確認 , 1:已確認 , 2:已取消 ,3:已經完成
	private Integer totalPrice;
	
	public RmoVO() {
	}

	@Override
	public String toString() {
		return "RmoVO{" +
				"roomOrderId=" + roomOrderId +
				", memId=" + memId +
				", orderDate=" + orderDate +
				", roomOrderStatus=" + roomOrderStatus +
				", totalPrice=" + totalPrice +
				'}';
	}

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

	public Integer getRoomOrderStatus() {
		return roomOrderStatus;
	}

	public void setRoomOrderStatus(Integer roomOrderStatus) {
		this.roomOrderStatus = roomOrderStatus;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	

}
