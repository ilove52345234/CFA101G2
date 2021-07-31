package com.extrabill.model;

import java.sql.Timestamp;
import java.util.List;

public class ExtraBillService {
    private ExtraBillDAO extraBillDAO;

    public ExtraBillService() {
       extraBillDAO = new ExtraBillDAOImpl();
    }

    public ExtraBillVO GetOne(Integer roomId){
        return  extraBillDAO.getOneByRoomId(roomId);
    }

    public ExtraBillVO CheckIn(Integer roomId, String informationPhone, Integer extraPrice, Timestamp expectedCheckOutDate, Timestamp checkInDate, Timestamp checkOutDate) {

        ExtraBillVO extraBillVO = new ExtraBillVO();
        extraBillVO.setRoomId(roomId);
        extraBillVO.setInformationPhone(informationPhone);
        extraBillVO.setExtraPrice(extraPrice);
        extraBillVO.setExpectedCheckOutDate(expectedCheckOutDate);
        extraBillVO.setCheckInDate(checkInDate);
        extraBillVO.setCheckOutDate(checkOutDate);

        extraBillDAO.insert(extraBillVO);

        return extraBillVO;

    }

    public ExtraBillVO CheckOut(ExtraBillVO extraBillVO){


        extraBillDAO.update(extraBillVO);
        return extraBillVO;
    }

    public void deleteExtraBill(Integer roomId) {
        extraBillDAO.deleteByRoomId(roomId);
    }

}
