package com.ron.model;

import java.sql.Timestamp;

public class RonVO implements java.io.Serializable {
    private Integer roomOrderNotifyId;
    private Integer roomOrderId;
    private Integer memId;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private String notifyText;
    private Timestamp notifyDate;
    private Integer notifyStatus;


    public Integer getRoomOrderNotifyId() {
        return roomOrderNotifyId;
    }

    public void setRoomOrderNotifyId(Integer roomOrderNotifyId) {
        this.roomOrderNotifyId = roomOrderNotifyId;
    }

    public Integer getRoomOrderId() {
        return roomOrderId;
    }

    public void setRoomOrderId(Integer roomOrderId) {
        this.roomOrderId = roomOrderId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
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

    public String getNotifyText() {
        return notifyText;
    }

    public void setNotifyText(String notifyText) {
        this.notifyText = notifyText;
    }

    public Timestamp getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(Timestamp notifyDate) {
        this.notifyDate = notifyDate;
    }

    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    @Override
    public String toString() {
        return "RonVO{" +
                "roomOrderNotifyId=" + roomOrderNotifyId +
                ", roomOrderId=" + roomOrderId +
                ", memId=" + memId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", notifyText='" + notifyText + '\'' +
                ", notifyDate=" + notifyDate +
                ", notifyStatus=" + notifyStatus +
                '}';
    }

    public RonVO(Integer roomOrderNotifyId, Integer roomOrderId, Integer memId, Timestamp checkInDate, Timestamp checkOutDate, String notifyText, Timestamp notifyDate, Integer notifyStatus) {
        this.roomOrderNotifyId = roomOrderNotifyId;
        this.roomOrderId = roomOrderId;
        this.memId = memId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.notifyText = notifyText;
        this.notifyDate = notifyDate;
        this.notifyStatus = notifyStatus;
    }

    public RonVO() {
    }
}
