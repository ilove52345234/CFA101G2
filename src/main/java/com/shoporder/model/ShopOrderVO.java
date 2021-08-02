package com.shoporder.model;

public class ShopOrderVO implements java.io.Serializable {

	private static final long serialVersionUID = 8786041608847931301L;
	private Integer itemOrderId;
	private Integer memId;
	private String itemOrderDate;
	private Integer itemAmounts;
	private Byte paymentMethod;
	private Byte shippingMethod;
	private Byte shippingStatus;
	private String startDate;
	private String endDate;
	
	public Integer getItemOrderId() {
		return itemOrderId;
	}
	public void setItemOrderId(Integer itemOrderId) {
		this.itemOrderId = itemOrderId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getItemOrderDate() {
		return itemOrderDate;
	}
	public void setItemOrderDate(String itemOrderDate) {
		this.itemOrderDate = itemOrderDate;
	}
	public Integer getItemAmounts() {
		return itemAmounts;
	}
	public void setItemAmounts(Integer itemAmounts) {
		this.itemAmounts = itemAmounts;
	}
	public Byte getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Byte paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Byte getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(Byte shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public Byte getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(Byte shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "ShopOrderVO [itemOrderId=" + itemOrderId + ", memId=" + memId + ", itemOrderDate=" + itemOrderDate
				+ ", itemAmounts=" + itemAmounts + ", paymentMethod=" + paymentMethod + ", shippingMethod="
				+ shippingMethod + ", shippingStatus=" + shippingStatus + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}
