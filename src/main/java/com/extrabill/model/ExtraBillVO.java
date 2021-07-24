package com.extrabill.model;

import java.sql.Timestamp;

public class ExtraBillVO implements java.io.Serializable{
    private Integer extraBillId;
    private Integer roomId;
    private String informationPhone;
    private Integer extraPrice;
    private String serviceItem;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;

    public ExtraBillVO() {
    }




    public Integer getExtraBillId() {
        return extraBillId;
    }

    public void setExtraBillId(Integer extraBillId) {
        this.extraBillId = extraBillId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }




    public Integer getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Integer extraPrice) {
        this.extraPrice = extraPrice;
    }

    public String getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(String serviceItem) {
        this.serviceItem = serviceItem;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "ExtraBillVO{" +
                "extraBillId=" + extraBillId +
                ", roomId=" + roomId +
                ", informationPhone='" + informationPhone + '\'' +
                ", extraPrice=" + extraPrice +
                ", serviceItem='" + serviceItem + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

    public String getInformationPhone() {
        return informationPhone;
    }

    public void setInformationPhone(String informationPhone) {
        this.informationPhone = informationPhone;
    }

    public ExtraBillVO(Integer extraBillId, Integer roomId, String informationPhone, Integer extraPrice, String serviceItem, Timestamp checkInDate, Timestamp checkOutDate) {
        this.extraBillId = extraBillId;
        this.roomId = roomId;
        this.informationPhone = informationPhone;
        this.extraPrice = extraPrice;
        this.serviceItem = serviceItem;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
