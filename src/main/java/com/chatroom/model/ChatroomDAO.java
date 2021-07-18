package com.chatroom.model;

import java.util.List;

public interface ChatroomDAO {
    public void insert(ChatroomVO chatroomVO);

    public void update(ChatroomVO chatroomVO);

    public void delete(Integer chatPlaceId);

    public ChatroomVO findByPrimaryKey(Integer chatPlaceId);

    public List<ChatroomVO> getAll();
}
