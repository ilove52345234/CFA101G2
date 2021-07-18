package com.rmpromotion.model;

import java.sql.Timestamp;

public class RmpVO implements java.io.Serializable{
	private Integer roomPromotionId;
	private Integer roomCategoryId;
	private Integer promotionPrice;
	private Timestamp promotionStartDate;
	private Timestamp promotionEndDate;
	private String promotionText;
	public RmpVO() {
	}
	public Integer getRoomPromotionId() {
		return roomPromotionId;
	}
	public void setRoomPromotionId(Integer roomPromotionId) {
		this.roomPromotionId = roomPromotionId;
	}
	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public Integer getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(Integer promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public Timestamp getPromotionStartDate() {
		return promotionStartDate;
	}
	public void setPromotionStartDate(Timestamp promotionStartDate) {
		this.promotionStartDate = promotionStartDate;
	}
	public Timestamp getPromotionEndDate() {
		return promotionEndDate;
	}
	public void setPromotionEndDate(Timestamp promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}
	public String getPromotionText() {
		return promotionText;
	}
	public void setPromotionText(String promotionText) {
		this.promotionText = promotionText;
	}


}
