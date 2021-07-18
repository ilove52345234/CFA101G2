package com.rmorder.model;

import java.sql.Timestamp;
import java.util.List;

public class RmoService {
	
	private RmoDAO_interface dao;
	
	public RmoService() {
		dao = new RmoDAO();
	}
	
	public RmoVO addRmoVO(Integer memId, Timestamp orderDate, Integer roomOrderStatus,  Integer totalPrice) {
		
		RmoVO rmoVO = new RmoVO();
		rmoVO.setMemId(memId);
		rmoVO.setOrderDate(orderDate);
		rmoVO.setRoomOrderStatus(roomOrderStatus);
		rmoVO.setTotalPrice(totalPrice);
		dao.insert(rmoVO);
		return rmoVO;
		
	}
	public RmoVO updateRmoVO(Integer roomOrderId, Integer memId, Timestamp orderDate, Integer roomOrderStatus,  Integer totalPrice) {
		RmoVO rmoVO = new RmoVO();
		rmoVO.setRoomOrderId(roomOrderId);
		rmoVO.setMemId(memId);
		rmoVO.setOrderDate(orderDate);
		rmoVO.setRoomOrderStatus(roomOrderStatus);
		rmoVO.setTotalPrice(totalPrice);

		dao.update(rmoVO);
		return rmoVO;

		
	}
	
	public void deleteRmo(Integer roomOrderId) {
		dao.delete(roomOrderId);
	}
	
	public RmoVO getOneRmo(Integer roomOrderId) {
		return dao.findByPrimaryKey(roomOrderId);
	}
	
	public List<RmoVO> getAll(){
		return dao.getAll();
	}
	
	
}
