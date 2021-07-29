package com.acttype.model;

public class ActTypeVO implements java.io.Serializable {
	
	private Integer actCategoryId;
	private String actCategoryName;
	private String actCategoryDesc;
	private Integer actMaxPart;
	private Integer actMinPart;
	private Integer actFee;
	private Integer actNumber;
	private Integer actTotalScore;
	
	
	public Integer getActCategoryId() {
		return actCategoryId;
	}
	public void setActCategoryId(Integer actCategoryId) {
		this.actCategoryId = actCategoryId;
	}
	public String getActCategoryName() {
		return actCategoryName;
	}
	public void setActCategoryName(String actCategoryName) {
		this.actCategoryName = actCategoryName;
	}
	public String getActCategoryDesc() {
		return actCategoryDesc;
	}
	public void setActCategoryDesc(String actCategoryDesc) {
		this.actCategoryDesc = actCategoryDesc;
	}
	public Integer getActMaxPart() {
		return actMaxPart;
	}
	public void setActMaxPart(Integer actMaxPart) {
		this.actMaxPart = actMaxPart;
	}
	public Integer getActMinPart() {
		return actMinPart;
	}
	public void setActMinPart(Integer actMinPart) {
		this.actMinPart = actMinPart;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getActNumber() {
		return actNumber;
	}
	public void setActNumber(Integer actNumber) {
		this.actNumber = actNumber;
	}
	public Integer getActTotalScore() {
		return actTotalScore;
	}
	public void setActTotalScore(Integer actTotalScore) {
		this.actTotalScore = actTotalScore;
	}
	
	
	@Override
	public String toString() {
		return "ActTypeVO [actCategoryId=" + actCategoryId + ", actCategoryName=" + actCategoryName
				+ ", actCategoryDesc=" + actCategoryDesc + ", actMaxPart=" + actMaxPart + ", actMinPart=" + actMinPart
				+ ", actFee=" + actFee + ", actNumber=" + actNumber + ", actTotalScore=" + actTotalScore + "]";
	}
	
	
}
