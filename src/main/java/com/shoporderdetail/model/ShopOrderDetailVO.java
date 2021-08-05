package com.shoporderdetail.model;

import com.shop.model.ShopVO;
import com.shoporder.model.ShopOrderVO;

public class ShopOrderDetailVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 2693467988477592396L;
	private Integer itemOrderId;
	private Integer itemId;
	private Integer itemQuantity;
	private Integer itemPromotionId;
	private Integer itemAmounts;
	private Integer itemFinalAmount;
	private Integer orderQuantity;

	@Override
	public String toString() {
		return "ShopOrderDetailVO{" +
				"itemOrderId=" + itemOrderId +
				", itemId=" + itemId +
				", itemQuantity=" + itemQuantity +
				", itemPromotionId=" + itemPromotionId +
				", itemAmounts=" + itemAmounts +
				", itemFinalAmount=" + itemFinalAmount +
				", orderQuantity=" + orderQuantity +
				", shopOrderVO=" + shopOrderVO +
				", shopVO=" + shopVO +
				'}';
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

	// JOIN
	private ShopOrderVO shopOrderVO;
	// JOIN
	private ShopVO shopVO;
	
	public Integer getItemOrderId() {
		return itemOrderId;
	}
	public void setItemOrderId(Integer itemOrderId) {
		this.itemOrderId = itemOrderId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public Integer getItemPromotionId() {
		return itemPromotionId;
	}
	public void setItemPromotionId(Integer itemPromotionId) {
		this.itemPromotionId = itemPromotionId;
	}
	public Integer getItemAmounts() {
		return itemAmounts;
	}
	public void setItemAmounts(Integer itemAmounts) {
		this.itemAmounts = itemAmounts;
	}
	public Integer getItemFinalAmount() {
		return itemFinalAmount;
	}
	public void setItemFinalAmount(Integer itemFinalAmount) {
		this.itemFinalAmount = itemFinalAmount;
	}
	public ShopOrderVO getShopOrderVO() {
		return shopOrderVO;
	}
	public void setShopOrderVO(ShopOrderVO shopOrderVO) {
		this.shopOrderVO = shopOrderVO;
	}
	public ShopVO getShopVO() {
		return shopVO;
	}
	public void setShopVO(ShopVO shopVO) {
		this.shopVO = shopVO;
	}

}
