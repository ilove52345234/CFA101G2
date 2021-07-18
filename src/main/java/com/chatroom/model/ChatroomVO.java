package com.chatroom.model;

public class ChatroomVO implements java.io.Serializable {
    private Integer chatPlaceId;
    private String chatName;
    private Integer chatMax;
    private Integer chatStatus;

    public ChatroomVO() {
    }

    public ChatroomVO(Integer chatPlaceId, String chatName, Integer chatMax, Integer chatStatus) {
        this.chatPlaceId = chatPlaceId;
        this.chatName = chatName;
        this.chatMax = chatMax;
        this.chatStatus = chatStatus;
    }

    @Override
    public String toString() {
        return "ChatroomVO{" +
                "chatPlaceId=" + chatPlaceId +
                ", chatName='" + chatName + '\'' +
                ", chatMax=" + chatMax +
                ", chatStatus=" + chatStatus +
                '}';
    }

    public Integer getChatPlaceId() {
        return chatPlaceId;
    }

    public void setChatPlaceId(Integer chatPlaceId) {
        this.chatPlaceId = chatPlaceId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Integer getChatMax() {
        return chatMax;
    }

    public void setChatMax(Integer chatMax) {
        this.chatMax = chatMax;
    }

    public Integer getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
    }
}
