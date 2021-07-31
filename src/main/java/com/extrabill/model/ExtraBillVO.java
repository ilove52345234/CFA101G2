package com.extrabill.model;

import java.sql.Timestamp;

public class ExtraBillVO implements java.io.Serializable{
    private Integer extraBillId;
    private Integer roomId;
    private String informationPhone;
    private Integer extraPrice;
    private Timestamp expectedCheckOutDate;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;

    public ExtraBillVO() {
    }


    @Override
    public String toString() {
        return "ExtraBillVO{" +
                "extraBillId=" + extraBillId +
                ", roomId=" + roomId +
                ", informationPhone='" + informationPhone + '\'' +
                ", extraPrice=" + extraPrice +
                ", expectedCheckOutDate=" + expectedCheckOutDate +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
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

    public Timestamp getExpectedCheckOutDate() {
        return expectedCheckOutDate;
    }

    public void setExpectedCheckOutDate(Timestamp expectedCheckOutDate) {
        this.expectedCheckOutDate = expectedCheckOutDate;
    }

    public ExtraBillVO(Integer extraBillId, Integer roomId, String informationPhone, Integer extraPrice, Timestamp expectedCheckOutDate, Timestamp checkInDate, Timestamp checkOutDate) {
        this.extraBillId = extraBillId;
        this.roomId = roomId;
        this.informationPhone = informationPhone;
        this.extraPrice = extraPrice;
        this.expectedCheckOutDate = expectedCheckOutDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Integer getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Integer extraPrice) {
        this.extraPrice = extraPrice;
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


    public String getInformationPhone() {
        return informationPhone;
    }

    public void setInformationPhone(String informationPhone) {
        this.informationPhone = informationPhone;
    }

}
