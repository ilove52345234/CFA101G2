package com.shoptype.model;

public class ShopTypeVO implements java.io.Serializable {

	private static final long serialVersionUID = -1029690686510673745L;
	private Integer itemCategoryId;
	private String itemCategoryName;
	private String itemCategoryDesc;
	
	public Integer getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}
	public String getItemCategoryDesc() {
		return itemCategoryDesc;
	}
	public void setItemCategoryDesc(String itemCategoryDesc) {
		this.itemCategoryDesc = itemCategoryDesc;
	}

}
