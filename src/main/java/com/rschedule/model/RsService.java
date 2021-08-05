package com.rschedule.model;

import com.rmorderlist.model.RmolVO;
import com.room.model.RmService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RsService {

    private RsDAO_interface dao;
    RmService rmService = new RmService();

    public RsService() {
        dao = new RsJDBCDAO();
    }

    public RsVO addRsVO(Integer roomCategoryId, Date roomScheduleDate, Integer roomAmount, Integer roomRsvBooked, Integer roomCheckOut, Integer roomCheckIn) {
        RsVO rsVO = new RsVO();
        rsVO.setRoomCategoryId(roomCategoryId);
        rsVO.setRoomScheduleDate(roomScheduleDate);
        rsVO.setRoomAmount(roomAmount);
        rsVO.setRoomRsvBooked(roomRsvBooked);
        rsVO.setRoomCheckOut(roomCheckOut);
        rsVO.setRoomCheckIn(roomCheckIn);
        dao.insert(rsVO);
        return rsVO;
    }

    public RsVO updateRsVO(Integer roomScheduleId, Integer roomCategoryId, Date roomScheduleDate, Integer roomAmount, Integer roomRsvBooked, Integer roomCheckOut, Integer roomCheckIn) {
        RsVO rsVO = new RsVO();
        rsVO.setRoomScheduleId(roomScheduleId);
        rsVO.setRoomCategoryId(roomCategoryId);
        rsVO.setRoomScheduleDate(roomScheduleDate);
        rsVO.setRoomAmount(roomAmount);
        rsVO.setRoomRsvBooked(roomRsvBooked);
        rsVO.setRoomCheckOut(roomCheckOut);
        rsVO.setRoomCheckIn(roomCheckIn);

        dao.update(rsVO);
        return rsVO;
    }


    public RsVO updateRsVO(RsVO rsVO) {
        dao.update(rsVO);
        return rsVO;
    }

    public void deleteRs(Integer roomScheduleId) {
        dao.delete(roomScheduleId);
    }

    public RsVO getOneRt(Integer roomScheduleId) {
        return dao.findByPrimaryKey(roomScheduleId);
    }

    public List<RsVO> getAll() {
        return dao.getAll();
    }

    public void updateByRmOrderList(RmolVO rmolVO) {
        List<RsVO> interval = dao.getInterval(rmolVO);
        int size = interval.size();


        for (int i = 0; i < size; i++) {
            RsVO rsVO = interval.get(i);
            if (i == 0) {
                rsVO.setRoomCheckIn(rsVO.getRoomCheckIn() + 1);
                System.out.println("入住的:" + rsVO);
            } else if (i == (interval.size() - 1)) {
                rsVO.setRoomCheckOut(rsVO.getRoomCheckOut() + 1);
                System.out.println("退房的:" + rsVO);
            } else {
                rsVO.setRoomRsvBooked(rsVO.getRoomRsvBooked() + 1);
                System.out.println("期間的:" + rsVO);

            }
            dao.update(rsVO);
        }
    }


//    public List<RsVO> getAllValidAmount() {
//        List<RsVO> list = new ArrayList<RsVO>();
//        List<RsVO> all = dao.getAll();
//        for (RsVO rsVO : all) {
//            Integer roomCheckOut = rsVO.getRoomCheckOut();
//            Integer roomCheckIn = rsVO.getRoomCheckIn();
//            Integer roomRsvBooked = rsVO.getRoomRsvBooked();
//            Integer roomAmount = rsVO.getRoomAmount();
//            //總數量  - (入住數量 + 未離店數量 ) + 當天退房數量
//            int i = roomAmount - (roomCheckIn + roomRsvBooked) + roomCheckOut;
//            if (i >= roomAmount) {
//                //如果超出最大數量
//                rsVO.setRoomValidAmount(roomAmount);
//            } else {
//                //設定有效數量
//                rsVO.setRoomValidAmount(i);
//            }
//            list.add(rsVO);
//        }
//        return list;
//    }

    public List<RsVO> getAllByRoomCategoryId( Integer RoomCategoryId) {
        List<RsVO> list = new ArrayList<RsVO>();

        List<RsVO> all = dao.getAllByRoomCategoryId(RoomCategoryId);
        int rm = rmService.getOneRmByRoomCategoryId(RoomCategoryId);

        for (RsVO rsVO : all) {

            Integer roomCheckOut = rsVO.getRoomCheckOut();
            Integer roomCheckIn = rsVO.getRoomCheckIn();
            Integer roomRsvBooked = rsVO.getRoomRsvBooked();
//            Integer roomAmount = rsVO.getRoomAmount();
            Integer roomAmount = rm;
            //總數量  - (入住數量 + 未離店數量 ) + 當天退房數量
            int i = roomAmount - (roomCheckIn + roomRsvBooked) + roomCheckOut;
            if (i >= roomAmount) {
                //如果超出最大數量
                rsVO.setRoomValidAmount(roomAmount);
            } else {
                //設定有效數量
                rsVO.setRoomValidAmount(i);
            }
            list.add(rsVO);
        }
        return list;
    }


    public List<RsVO> getOneValidAmount(Integer RoomCategoryId,Date CheckInDate,Date CheckOutDate) {
        List<RsVO> list = new ArrayList<RsVO>();
        List<RsVO> oneInterval = dao.getOneInterval(RoomCategoryId, CheckInDate, CheckOutDate);
        int rm = rmService.getOneRmByRoomCategoryId(RoomCategoryId);
        for (RsVO rsVO : oneInterval) {
            Integer roomCheckOut = rsVO.getRoomCheckOut();
            Integer roomCheckIn = rsVO.getRoomCheckIn();
            Integer roomRsvBooked = rsVO.getRoomRsvBooked();
//            Integer roomAmount = rsVO.getRoomAmount();
            Integer roomAmount = rm;
            //總數量  - (入住數量 + 未離店數量 ) + 當天退房數量
            int i = roomAmount - (roomCheckIn + roomRsvBooked) + roomCheckOut;
            if (i >= roomAmount) {
                //如果超出最大數量
                rsVO.setRoomValidAmount(roomAmount);
            } else {
                //設定有效數量
                rsVO.setRoomValidAmount(i);
            }
            list.add(rsVO);
        }
        return list;
    }
}
