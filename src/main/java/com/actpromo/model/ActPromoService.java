package com.actpromo.model;

import java.util.List;

import com.acttype.model.ActTypeVO;

public class ActPromoService {

	
	private ActPromoDAOInterface dao;
	
	
	public ActPromoService() {
		dao = new ActPromoDAO();
	}
	
	public ActPromoVO addActPromo(String actPromotionName, Double actPromotionDiscount) {
		
		
		
		ActPromoVO actPromoVO = new ActPromoVO();
		actPromoVO.setActPromotionName(actPromotionName);
		actPromoVO.setActPromotionDiscount(actPromotionDiscount);
		
		dao.insert(actPromoVO);
		
		
		
		return actPromoVO;
		
	}
	
	public ActPromoVO updateActPromo(Integer actPromotionId, String actPromotionName, Double actPromotionDiscount) {
		
		ActPromoVO actPromoVO = new ActPromoVO();
		
		actPromoVO.setActPromotionId(actPromotionId);
		actPromoVO.setActPromotionName(actPromotionName);
		actPromoVO.setActPromotionDiscount(actPromotionDiscount);
		
		dao.update(actPromoVO);
		
		
		
		return actPromoVO;
		
	}
	
	public void deleteActPromo(Integer actPromotionId) {
		dao.delete(actPromotionId);
	}
	
	public ActPromoVO getOneById(Integer actPromotionId) {
		return dao.findByPrimaryKey(actPromotionId);
	}
	
	public List<ActPromoVO> getAll() {
		return dao.getAll();
		
	}
	


	
	
}
