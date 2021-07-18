package com.rmtypepic.model;

import java.sql.Timestamp;

public class RtpVO implements java.io.Serializable{
	private Integer roomPhotoId;
	private Integer roomCategoryId;
	private byte[] roomPhoto;
	public RtpVO(){
		
	}

	public Integer getRoomPhotoId() {
		return roomPhotoId;
	}
	public void setRoomPhotoId(Integer roomPhotoId) {
		this.roomPhotoId = roomPhotoId;
	}
	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public byte[] getRoomPhoto() {
		return roomPhoto;
	}
	public void setRoomPhoto(byte[] roomPhoto) {
		this.roomPhoto = roomPhoto;
	}
	}
	