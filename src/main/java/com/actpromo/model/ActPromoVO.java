package com.actpromo.model;

public class ActPromoVO implements java.io.Serializable {
	
	
	private Integer actPromotionId;
	private String	actPromotionName;
	private Double	actPromotionDiscount;
	
	
	
	
	
	public Integer getActPromotionId() {
		return actPromotionId;
	}
	public void setActPromotionId(Integer actPromotionId) {
		this.actPromotionId = actPromotionId;
	}
	public String getActPromotionName() {
		return actPromotionName;
	}
	public void setActPromotionName(String actPromotionName) {
		this.actPromotionName = actPromotionName;
	}
	public Double getActPromotionDiscount() {
		return actPromotionDiscount;
	}
	public void setActPromotionDiscount(Double actPromotionDiscount) {
		this.actPromotionDiscount = actPromotionDiscount;
	}
	
	
		@Override
		public String toString() {
			return "ActPromoVO [actPromotionId=" + actPromotionId + ", actPromotionName=" + actPromotionName
					+ ", actPromotionDiscount=" + actPromotionDiscount + "]";
		}
}
