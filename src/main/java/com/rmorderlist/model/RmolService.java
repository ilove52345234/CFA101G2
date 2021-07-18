package com.rmorderlist.model;

import java.sql.Timestamp;
import java.util.List;

public class RmolService {
	
	private RmolDAO_interface dao;
	
	public RmolService() {
		dao = new RmolDAO();
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
	public void deleteRt(Integer orderListId) {
		dao.delete(orderListId);
	}
	
	public RmolVO getOneRt(Integer orderListId) {
		return dao.findByPrimaryKey(orderListId);
	}
	
	public List<RmolVO> getAll(){
		return dao.getAll();
	}
}	

