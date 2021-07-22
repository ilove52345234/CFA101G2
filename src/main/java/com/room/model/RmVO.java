package com.room.model;

public class RmVO implements java.io.Serializable{
	private Integer roomId;
	private Integer roomCategoryId;
	private Byte roomCheckStatus; //0:未使用, 1:待入住, 2:已入住, 3:待退房
	private Byte roomSaleStatus; //0:可用 , 1:不可用
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