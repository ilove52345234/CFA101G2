package com.recentnews.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecentNewsVO implements Serializable{
	private Integer newsId;
	private String newsContent;
	private Timestamp newsTime;
	private Integer newsStatus;
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Timestamp getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(Timestamp newsTime) {
		this.newsTime = newsTime;
	}
	public Integer getNewsStatus() {
		return newsStatus;
	}
	public void setNewsStatus(Integer newsStatus) {
		this.newsStatus = newsStatus;
	}
}
