package com.rmpromotion.model;

import java.sql.Timestamp;
import java.util.List;

public class RmpService {
	
	private RmpDAO_interface dao;
	
	public RmpService() {
		dao =new RmpDAO();
	}
	
	public RmpVO addRmpVO(Integer roomCategoryId, Integer promotionPrice, Timestamp promotionStartDate, Timestamp promotionEndDate, String promotionText) {
		RmpVO rmpVO = new RmpVO();
		rmpVO.setRoomCategoryId(roomCategoryId);
		rmpVO.setPromotionPrice(promotionPrice);
		rmpVO.setPromotionStartDate(promotionStartDate);
		rmpVO.setPromotionEndDate(promotionEndDate);
		rmpVO.setPromotionText(promotionText);
		dao.insert(rmpVO);
		
		return rmpVO;
		
	}

	public RmpVO updateRmpVO(Integer roomPromotionId, Integer roomCategoryId, Integer promotionPrice, Timestamp promotionStartDate, Timestamp promotionEndDate, String promotionText) {
		RmpVO rmpVO = new RmpVO();
		rmpVO.setRoomPromotionId(roomPromotionId);
		rmpVO.setRoomCategoryId(roomCategoryId);
		rmpVO.setPromotionPrice(promotionPrice);
		rmpVO.setPromotionStartDate(promotionStartDate);
		rmpVO.setPromotionEndDate(promotionEndDate);
		rmpVO.setPromotionText(promotionText);

		dao.update(rmpVO);
		return rmpVO;
	}
	
	public void deletRmp(Integer roomPromotionId) {
		dao.delete(roomPromotionId);
	}
	
	public RmpVO getOneRt(Integer roomPromotionId) {
		return dao.findByPrimaryKey(roomPromotionId);
	}
	
	public List<RmpVO> getAll() {
		return dao.getAll();
		
	}
}
