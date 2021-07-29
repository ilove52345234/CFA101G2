package com.actpromo.model;

import java.util.List;


public interface ActPromoDAOInterface {
	public void insert(ActPromoVO actPromoVO);
	public void update(ActPromoVO actPromoVO);
	public void delete (Integer actPromotionId);
	public ActPromoVO findByPrimaryKey(Integer actPromotionId);
	public List<ActPromoVO> getAll();
	
}
