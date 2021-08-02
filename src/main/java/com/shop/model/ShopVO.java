package com.shop.model;

import com.shoppic.model.ShopPicVO;

public class ShopVO implements java.io.Serializable {

	private static final long serialVersionUID = 4007142691851599904L;
	private Integer itemId;
	private Integer itemCategoryId;
	private String itemDescribtion;
	private Integer itemFee;
	private String itemName;
	private Integer itemQuantity;
	private Byte itemStatus;
	private Integer commentNumber;
	private Integer commentTotalScore;
	// JOIN 商品圖片
	private ShopPicVO shopPicVO;
	private String shopPicSrc;
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public String getItemDescribtion() {
		return itemDescribtion;
	}

	public void setItemDescribtion(String itemDescribtion) {
		this.itemDescribtion = itemDescribtion;
	}

	public Integer getItemFee() {
		return itemFee;
	}

	public void setItemFee(Integer itemFee) {
		this.itemFee = itemFee;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Byte getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Byte itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Integer getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}

	public Integer getCommentTotalScore() {
		return commentTotalScore;
	}

	public void setCommentTotalScore(Integer commentTotalScore) {
		this.commentTotalScore = commentTotalScore;
	}

	public ShopPicVO getShopPicVO() {
		return shopPicVO;
	}

	public void setShopPicVO(ShopPicVO shopPicVO) {
		this.shopPicVO = shopPicVO;
	}

	
	
	public String getShopPicSrc() {
		return shopPicSrc;
	}

	public void setShopPicSrc(String shopPicSrc) {
		this.shopPicSrc = shopPicSrc;
	}

	@Override
	public String toString() {
		return "ShopVO [itemId=" + itemId + ", itemCategoryId=" + itemCategoryId + ", itemDescribtion="
				+ itemDescribtion + ", itemFee=" + itemFee + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity
				+ ", itemStatus=" + itemStatus + ", commentNumber=" + commentNumber + ", commentTotalScore="
				+ commentTotalScore + ", shopPicVO=" + shopPicVO + ", shopPicSrc=" + shopPicSrc + "]";
	}
	
}