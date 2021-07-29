package com.actphoto.model;

public class ActPhotoVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 615872326557166786L;
	private Integer actPhotoId;
	private Integer actCategoryId; 
	private byte[] actPhotoFile;
	
	
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

	public byte[] getActPhotoFile(){  
		return actPhotoFile;  
		}  
	
	public void setActPhotoFile(byte[] actPhotoFile){  
		this.actPhotoFile = actPhotoFile;
		}  
	
}
