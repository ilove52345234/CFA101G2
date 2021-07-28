package com.rmorderlist.model;

import com.rmorder.model.RmoVO;
import com.utils.PageBean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RmolService {
	
	private RmolDAO_interface dao;
	
	public RmolService() {
		dao = new RmolJDBCDAO();
	}

	public RmolVO addRmolVO(Integer roomOrderId, Integer roomCategoryId, Integer roomPromotionId, Integer roomId, String memName, Integer memNumber, Timestamp checkInDate, Timestamp CheckOutDate, Integer roomTotalPrice) {
		// 新增
		RmolVO rmolVO = new RmolVO();
		
		rmolVO.setRoomOrderId(roomOrderId);
		rmolVO.setRoomCategoryId(roomCategoryId);
		rmolVO.setRoomPromotionId(roomPromotionId);
		rmolVO.setRoomId(roomId);
		rmolVO.setMemName(memName);
		rmolVO.setMemNumber(memNumber);
		rmolVO.setCheckInDate(checkInDate);
		rmolVO.setCheckOutDate(CheckOutDate);
		rmolVO.setRoomTotalPrice(roomTotalPrice);
		dao.insert(rmolVO);
		
		return rmolVO;
		
	}

	public RmolVO updateRmolVO(Integer orderListId, Integer roomOrderId, Integer roomCategoryId, Integer roomPromotionId, Integer roomId, String memName, Integer memNumber, Timestamp checkInDate, Timestamp CheckOutDate, Integer roomTotalPrice) {
		RmolVO rmolVO = new RmolVO();
		rmolVO.setOrderListId(orderListId);
		rmolVO.setRoomOrderId(roomOrderId);
		rmolVO.setRoomCategoryId(roomCategoryId);
		rmolVO.setRoomPromotionId(roomPromotionId);
		rmolVO.setRoomId(roomId);
		rmolVO.setMemName(memName);
		rmolVO.setMemNumber(memNumber);
		rmolVO.setCheckInDate(checkInDate);
		rmolVO.setCheckOutDate(CheckOutDate);
		rmolVO.setRoomTotalPrice(roomTotalPrice);

		dao.update(rmolVO);
		return rmolVO;
	}



	public void updateRmolVO(RmolVO rmolVO){
		dao.update(rmolVO);
	}

	public void deleteRt(Integer orderListId) {
		dao.delete(orderListId);
	}

	public RmolVO getOneRt(Integer orderListId) {
		return dao.findByPrimaryKey(orderListId);
	}

	public List<RmolVO> getAll(){
		return dao.getAll();
	}

	public PageBean<RmolVO> findRmolByPage(String _currentPage, String _rows, Map<String, String> condition) {
		//1.創建一個空的PageBean物件
		PageBean<RmolVO> RmolVOPageBean = new PageBean<RmolVO>();

		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);

		//2.設置參數
		RmolVOPageBean.setCurrentPage(currentPage);
		RmolVOPageBean.setRows(rows);

		//3.調用dao查詢總記錄數
		int totalCount= dao.findTotalCount(condition);

		RmolVOPageBean.setTotalCount(totalCount);

		//4.調用dao查詢List數據集合

		//計算開始的索引值
		int start = (currentPage - 1) * rows;

		List<RmolVO> byPage = dao.findByPage(start, rows, condition);

		for (RmolVO rmolVO : byPage) {
			System.out.println(rmolVO);
		}

		RmolVOPageBean.setList(byPage);

		//5.計算總頁碼

		int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

		RmolVOPageBean.setTotalPage(totalPage);

		return RmolVOPageBean;
	}

	public List<RmolVO> getAllByROOM_ORDER_ID(Integer RoomOrderId){
		return dao.getAllByRoomOrderId(RoomOrderId);
	}

}	

