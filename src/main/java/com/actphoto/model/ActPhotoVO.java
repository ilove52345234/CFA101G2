package com.actphoto.model;

import java.sql.Blob;

public class ActPhotoVO implements java.io.Serializable {
	
	private Integer actPhotoId;
	private Integer actCategoryId; 
	private Blob actPhotoFile;
	
	
	public Integer getActPhotoId() {
		return actPhotoId;
	}
	public void setActPhotoId(Integer actPhotoId) {
		this.actPhotoId = actPhotoId;
	}
	public Integer getActCategoryId() {
		return actCategoryId;
	}
	public void setActCategoryId(Integer actCategoryId) {
		this.actCategoryId = actCategoryId;
	}
	public Blob getActPhotoFile() {
		return actPhotoFile;
	}
	public void setActPhotoFile(Blob blob) {
		this.actPhotoFile = blob;
	}

	
	
	
	
}
