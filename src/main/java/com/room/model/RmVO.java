package com.room.model;

public class RmVO implements java.io.Serializable{
	private Integer roomId;
	private Integer roomCategoryId;
	private Byte roomCheckStatus;
	private Byte roomSaleStatus;
	private String roomInformation;

	@Override
	public String toString() {
		return "RmVO{" +
				"roomId=" + roomId +
				", roomCategoryId=" + roomCategoryId +
				", roomCheckStatus=" + roomCheckStatus +
				", roomSaleStatus=" + roomSaleStatus +
				", roomInformation='" + roomInformation + '\'' +
				'}';
	}

	public  RmVO() {
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public Byte getRoomCheckStatus() {
		return roomCheckStatus;
	}
	public void setRoomCheckStatus(Byte roomCheckStatus) {
		this.roomCheckStatus = roomCheckStatus;
	}
	public Byte getRoomSaleStatus() {
		return roomSaleStatus;
	}
	public void setRoomSaleStatus(Byte roomSaleStatus) {
		this.roomSaleStatus = roomSaleStatus;
	}
	public String getRoomInformation() {
		return roomInformation;
	}
	public void setRoomInformation(String roomInformation) {
		this.roomInformation = roomInformation;
	}


}