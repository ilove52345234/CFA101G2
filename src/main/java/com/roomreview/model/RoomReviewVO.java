package com.roomreview.model;

import java.sql.Timestamp;

public class RoomReviewVO implements java.io.Serializable {
    private Integer roomReviewId;
    private Integer roomCategoryId;
    private Integer memId;
    private String commentContent;
    private Timestamp commentDate;
    private String satisfaction;

    @Override
    public String toString() {
        return "RoomReviewVO{" +
                "roomReviewId=" + roomReviewId +
                ", roomCategoryId=" + roomCategoryId +
                ", memId=" + memId +
                ", commentContent='" + commentContent + '\'' +
                ", commentDate=" + commentDate +
                ", satisfaction='" + satisfaction + '\'' +
                '}';
    }

    public Integer getRoomReviewId() {
        return roomReviewId;
    }

    public void setRoomReviewId(Integer roomReviewId) {
        this.roomReviewId = roomReviewId;
    }

    public Integer getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(Integer roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public RoomReviewVO() {
    }

    public RoomReviewVO(Integer roomReviewId, Integer roomCategoryId, Integer memId, String commentContent, Timestamp commentDate, String satisfaction) {
        this.roomReviewId = roomReviewId;
        this.roomCategoryId = roomCategoryId;
        this.memId = memId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.satisfaction = satisfaction;
    }
}
