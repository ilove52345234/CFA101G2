package com.roomorder.model;

import java.sql.Timestamp;
import java.util.List;

public class RoomOrderService {
        private RoomOrderDAO roomOrderDAO;


    public RoomOrderVO addRoomOrder(Integer memId, Timestamp orderDate, Integer roomOrderStatus, Integer totalPrice){
        RoomOrderVO roomOrderVO = new RoomOrderVO();
        roomOrderVO.setMemId(memId);
        roomOrderVO.setOrderDate(orderDate);
        roomOrderVO.setRoomOrderStatus(roomOrderStatus);
        roomOrderVO.setTotalPrice(totalPrice);

        roomOrderDAO.insert(roomOrderVO);

        return roomOrderVO;

    }




    public RoomOrderVO updateRoomOrder(Integer roomOrderId, Integer memId, Timestamp orderDate, Integer roomOrderStatus, Integer totalPrice){


        RoomOrderVO roomOrderVO = new RoomOrderVO();
        roomOrderVO.setRoomOrderId(roomOrderId);
        roomOrderVO.setMemId(memId);
        roomOrderVO.setOrderDate(orderDate);
        roomOrderVO.setRoomOrderStatus(roomOrderStatus);
        roomOrderVO.setTotalPrice(totalPrice);

        roomOrderDAO.update(roomOrderVO);

        return roomOrderVO;


    }

    public RoomOrderService() {
        roomOrderDAO = new RoomOrderDAOImpl();
    }




    public void deleteRoomOrder(Integer roomOrderId) {
        roomOrderDAO.delete(roomOrderId);
    }


    public List<RoomOrderVO> getAll() {
        return roomOrderDAO.getAll();
    }


    public RoomOrderVO getOneRoomOrder(Integer roomOrderId) {
        return roomOrderDAO.findByPrimaryKey(roomOrderId);
    }
}
