package com.shop.model;

import com.shoppic.model.ShopPicVO;

import java.util.Objects;

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
	private Integer orderQuantity; // 用來暫存購物車的購買商品數量

	@Override
	public String toString() {
		return "ShopVO{" +
				"itemId=" + itemId +
				", itemCategoryId=" + itemCategoryId +
				", itemDescribtion='" + itemDescribtion + '\'' +
				", itemFee=" + itemFee +
				", itemName='" + itemName + '\'' +
				", itemQuantity=" + itemQuantity +
				", itemStatus=" + itemStatus +
				", commentNumber=" + commentNumber +
				", commentTotalScore=" + commentTotalScore +
				", shopPicVO=" + shopPicVO +
				", shopPicSrc='" + shopPicSrc + '\'' +
				", orderQuantity=" + orderQuantity +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopVO other = (ShopVO) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

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

}