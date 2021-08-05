package com.room.model;

import com.emp.model.EmpVO;
import com.utils.PageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RmService {

    private RmDAO_interface dao;

    public RmService() {
        dao = new RmJDBCDAO();
    }


    public RmVO addRmVO2( Integer RoomCategoryId) {
        RmVO rmVO = new RmVO();
        Byte i = 0 ;
        rmVO.setRoomCategoryId(RoomCategoryId);
        rmVO.setRoomCheckStatus(i);
        rmVO.setRoomSaleStatus(i);
        dao.insert2(rmVO);
        return rmVO;
    }

    public RmVO addRmVO(Integer roomCategoryId, Byte roomCheckStatus, Byte roomSaleStatus, String roomInformation) {
        RmVO rmVO = new RmVO();
        rmVO.setRoomCategoryId(roomCategoryId);
        rmVO.setRoomCheckStatus(roomCheckStatus);
        rmVO.setRoomSaleStatus(roomSaleStatus);
        rmVO.setRoomInformation(roomInformation);
        dao.insert(rmVO);
        return rmVO;

    }

    public RmVO updateRmVO(Integer roomId, Integer roomCategoryId, Byte roomCheckStatus, Byte roomSaleStatus, String roomInformation) {
        RmVO rmVO = new RmVO();
        rmVO.setRoomId(roomId);
        rmVO.setRoomCategoryId(roomCategoryId);
        rmVO.setRoomCheckStatus(roomCheckStatus);
        rmVO.setRoomSaleStatus(roomSaleStatus);
        rmVO.setRoomInformation(roomInformation);
        dao.update(rmVO);
        return rmVO;
    }

    public RmVO updateRmVO(RmVO rmVO) {
        dao.update(rmVO);
        return rmVO;
    }

    public void deleteRm(Integer roomId) {
        dao.delete(roomId);
    }

    public RmVO getOneRm(Integer roomId) {
        return dao.findByPrimaryKey(roomId);
    }
    public Integer getOneRmByRoomCategoryId(Integer roomCategoryId) {
        Integer allBy = dao.getAllBy(roomCategoryId);
        return allBy;
    }

    public List<RmVO> getAll() {
        return dao.getAll();
    }


    public PageBean<RmVO> findrmByPage(String _currentPage, String _rows, Map<String, String> condition) {
        //1.創建一個空的PageBean物件
        PageBean<RmVO> rmVOPageBean = new PageBean<RmVO>();

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);


        //2.設置參數
        rmVOPageBean.setCurrentPage(currentPage);
        rmVOPageBean.setRows(rows);

        //3.調用dao查詢總記錄數
        int totalCount = dao.findTotalCount(condition);

        rmVOPageBean.setTotalCount(totalCount);

        //4.調用dao查詢List數據集合

        //計算開始的索引值
        int start = (currentPage - 1) * rows;

        List list = dao.findByPage(start, rows, condition);

        rmVOPageBean.setList(list);

        //5.計算總頁碼

        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        rmVOPageBean.setTotalPage(totalPage);


        return rmVOPageBean;
    }

    public PageBean<RmVO> getValidRoom(String _currentPage, String _rows, Map<String, String> condition) {
        //1.創建一個空的PageBean物件
        PageBean<RmVO> rmVOPageBean = new PageBean<RmVO>();


        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);


        //2.設置參數
        rmVOPageBean.setCurrentPage(currentPage);
        rmVOPageBean.setRows(rows);

        //3.調用dao查詢總記錄數
        int totalCount = dao.findTotalCount(condition);

        rmVOPageBean.setTotalCount(totalCount);

        //4.調用dao查詢List數據集合

        //計算開始的索引值
        int start = (currentPage - 1) * rows;

        List list = dao.findByPage(start, rows, condition);

        rmVOPageBean.setList(list);

        //5.計算總頁碼

        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        rmVOPageBean.setTotalPage(totalPage);

        return rmVOPageBean;
    }


}
