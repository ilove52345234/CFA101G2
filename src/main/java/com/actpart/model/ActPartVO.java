package com.actpart.model;

import java.sql.Timestamp;

public class ActPartVO implements java.io.Serializable {
	
	
	
	private Integer actId;   //pk fk
	private Integer memId;	 //pk fk
	private Timestamp actApplyDate;	//NN
	private Integer feeConfirm; // 0 or 1 
	private Integer actStar;	// 1 ~5
	private Timestamp actCommentDate;// NN
	private String actCommentText;
	
	
	

	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Timestamp getActApplyDate() {
		return actApplyDate;
	}
	public void setActApplyDate(Timestamp actApplyDate) {
		this.actApplyDate = actApplyDate;
	}
	public Integer getFeeConfirm() {
		return feeConfirm;
	}
	public void setFeeConfirm(Integer feeConfirm) {
		this.feeConfirm = feeConfirm;
	}
	public Integer getActStar() {
		return actStar;
	}
	public void setActStar(Integer actStar) {
		this.actStar = actStar;
	}
	public Timestamp getActCommentDate() {
		return actCommentDate;
	}
	public void setActCommentDate(Timestamp actCommentDate) {
		this.actCommentDate = actCommentDate;
	}
	public String getActCommentText() {
		return actCommentText;
	}
	public void setActCommentText(String actCommentText) {
		this.actCommentText = actCommentText;
	}

	
	@Override
	public String toString() {
		return "ActPartVO [actId=" + actId + ", memId=" + memId + ", actApplyDate=" + actApplyDate + ", feeConfirm="
				+ feeConfirm + ", actStar=" + actStar + ", actCommentDate=" + actCommentDate + ", actCommentText="
				+ actCommentText + "]";
	}


}
