package com.act.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActVO implements java.io.Serializable {
	
	private Integer actId;
	private Integer actCategoryId;
	private Integer actPromotionId;
	private String actDescription;
//	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Timestamp actStart;
//	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Timestamp actEnd;
	private String actStatus;
	private Integer actFee;
	private Integer applicants;
//	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Timestamp partStart;
//	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Timestamp partEnd;
	private Integer actMaxPart;
	private Integer actMinPart;
	
		
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getActCategoryId() {
		return actCategoryId;
	}
	public void setActCategoryId(Integer actCategoryId) {
		this.actCategoryId = actCategoryId;
	}
	public Integer getActPromotionId() {
		return actPromotionId;
	}
	public void setActPromotionId(Integer actPromotionId) {
		this.actPromotionId = actPromotionId;
	}
	public String getActDescription() {
		return actDescription;
	}
	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}
	public Timestamp getActStart() {
		return actStart;
	}
	public void setActStart(Timestamp actStart) {
		this.actStart = actStart;
	}
	public Timestamp getActEnd() {
		return actEnd;
	}
	public void setActEnd(Timestamp actEnd) {
		this.actEnd = actEnd;
	}
	public String getActStatus() {
		return actStatus;
	}
	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getApplicants() {
		return applicants;
	}
	public void setApplicants(Integer applicants) {
		this.applicants = applicants;
	}
	public Timestamp getPartStart() {
		return partStart;
	}
	public void setPartStart(Timestamp partStart) {
		this.partStart = partStart;
	}
	public Timestamp getPartEnd() {
		return partEnd;
	}
	public void setPartEnd(Timestamp partEnd) {
		this.partEnd = partEnd;
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
	
	
	@Override
	public String toString() {
		return "ActVO [actId=" + actId + ", actCategoryId=" + actCategoryId + ", actPromotionId=" + actPromotionId
				+ ", actDescription=" + actDescription + ", actStart=" + actStart + ", actEnd=" + actEnd
				+ ", actStatus=" + actStatus + ", actFee=" + actFee + ", applicants=" + applicants + ", partStart="
				+ partStart + ", partEnd=" + partEnd + ", actMaxPart=" + actMaxPart + ", actMinPart=" + actMinPart
				+ "]";
	}
	
	
	
	
}
