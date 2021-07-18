package com.ron.model;

import com.emp.model.EmpVO;

import java.sql.Timestamp;
import java.util.List;

public class RonService {

    private RonDAO ronDAO;

    public RonService() {
        ronDAO = new RonDAOImpl();
    }


    public RonVO addRon(Integer roomOrderId, Integer memId, Timestamp checkInDate, Timestamp checkOutDate, String notifyText, Timestamp notifyDate, Integer notifyStatus){
        RonVO ronVO = new RonVO();

        ronVO.setRoomOrderId(roomOrderId);
        ronVO.setMemId(memId);
        ronVO.setCheckInDate(checkInDate);
        ronVO.setCheckOutDate(checkOutDate);
        ronVO.setNotifyText(notifyText);
        ronVO.setNotifyDate(notifyDate);
        ronVO.setNotifyStatus(notifyStatus);
        ronDAO.insert(ronVO);


        return ronVO;
    };




    public RonVO updateRon(Integer roomOrderNotifyId,Integer roomOrderId, Integer memId, Timestamp checkInDate, Timestamp checkOutDate, String notifyText, Timestamp notifyDate, Integer notifyStatus){
        RonVO ronVO = new RonVO();


        ronVO.setRoomOrderId(roomOrderId);
        ronVO.setMemId(memId);
        ronVO.setCheckInDate(checkInDate);
        ronVO.setCheckOutDate(checkOutDate);
        ronVO.setNotifyText(notifyText);
        ronVO.setNotifyDate(notifyDate);
        ronVO.setNotifyStatus(notifyStatus);
        ronVO.setRoomOrderNotifyId(roomOrderNotifyId);

        ronDAO.update(ronVO);


        return ronVO;
    }

    public void deleteRon(Integer roomOrderNotifyId) {
        ronDAO.delete(roomOrderNotifyId);
    }



    public List<RonVO> getAll() {
        return ronDAO.getAll();
    }


    public RonVO getOneRon(Integer roomOrderNotifyId) {
        return ronDAO.findByPrimaryKey(roomOrderNotifyId);
    }



}
