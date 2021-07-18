package com.extrabill.model;

import java.sql.Timestamp;

public class ExtraBillVO implements java.io.Serializable{
    private Integer extraBillId;
    private Integer orderListId;
    private Integer extraPrice;
    private Integer amount;
    private String serviceItem;
    private Timestamp consumptionDate;

    public ExtraBillVO() {
    }

    public ExtraBillVO(Integer extraBillId, Integer orderListId, Integer extraPrice, Integer amount, String serviceItem, Timestamp consumptionDate) {
        this.extraBillId = extraBillId;
        this.orderListId = orderListId;
        this.extraPrice = extraPrice;
        this.amount = amount;
        this.serviceItem = serviceItem;
        this.consumptionDate = consumptionDate;
    }

    @Override
    public String toString() {
        return "ExtraBillVO{" +
                "extraBillId=" + extraBillId +
                ", orderListId=" + orderListId +
                ", extraPrice=" + extraPrice +
                ", amount=" + amount +
                ", serviceItem='" + serviceItem + '\'' +
                ", consumptionDate=" + consumptionDate +
                '}';
    }

    public Integer getExtraBillId() {
        return extraBillId;
    }

    public void setExtraBillId(Integer extraBillId) {
        this.extraBillId = extraBillId;
    }

    public Integer getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public Integer getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Integer extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(String serviceItem) {
        this.serviceItem = serviceItem;
    }

    public Timestamp getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Timestamp consumptionDate) {
        this.consumptionDate = consumptionDate;
    }
}
