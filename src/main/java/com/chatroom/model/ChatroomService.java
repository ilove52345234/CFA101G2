package com.chatroom.model;

import java.util.List;

public class ChatroomService {
    private ChatroomDAO chatroomDAO;

    public ChatroomService() {
        chatroomDAO = new ChatroomDAOImpl();
    }

    public ChatroomVO addChatroom( String chatName,Integer chatMax,Integer chatStatus){
        ChatroomVO chatroomVO = new ChatroomVO();

        chatroomVO.setChatName(chatName);
        chatroomVO.setChatMax(chatMax);
        chatroomVO.setChatStatus(chatStatus);

        chatroomDAO.insert(chatroomVO);

        return chatroomVO;
    }



    public ChatroomVO updateChatroom( Integer chatPlaceId,String chatName,Integer chatMax,Integer chatStatus){
        ChatroomVO chatroomVO = new ChatroomVO();


        chatroomVO.setChatPlaceId(chatPlaceId);
        chatroomVO.setChatName(chatName);
        chatroomVO.setChatMax(chatMax);
        chatroomVO.setChatStatus(chatStatus);

        chatroomDAO.update(chatroomVO);

        return chatroomVO;
    }






    public void deleteChatroom(Integer chatPlaceId) {
        chatroomDAO.delete(chatPlaceId);
    }


    public List<ChatroomVO> getAll() {
        return chatroomDAO.getAll();
    }


    public ChatroomVO getOneChatroom(Integer chatPlaceId) {
        return chatroomDAO.findByPrimaryKey(chatPlaceId);
    }

}
