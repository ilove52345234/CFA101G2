package com.acttype.model;

import java.util.List;

public class ActTypeService {

	
	
	private ActTypeDAOInterface dao;
	
	public ActTypeService() {
		dao = new ActTypeDAO();
	}
	
	
	public ActTypeVO addActType(String actCategoryName, String actCategoryDesc,
			Integer actMaxPart,Integer actMinPart, Integer actFee, Integer actNumber, Integer actTotalScore) {
		
		ActTypeVO actTypeVO = new ActTypeVO();
		
		actTypeVO.setActCategoryName(actCategoryName);
		actTypeVO.setActCategoryDesc(actCategoryDesc);
		actTypeVO.setActMaxPart(actMaxPart);
		actTypeVO.setActMinPart(actMinPart);
		actTypeVO.setActFee(actFee);
		actTypeVO.setActNumber(actNumber);
		actTypeVO.setActTotalScore(actTotalScore);
		
		dao.insert(actTypeVO);
	
		
		return actTypeVO;
	}
	
	
	public ActTypeVO updateActType(Integer actCategoryId, String actCategoryName, String actCategoryDesc,
			Integer actMaxPart,Integer actMinPart, Integer actFee, Integer actNumber, Integer actTotalScore) {
		
		ActTypeVO actTypeVO = new ActTypeVO();
		
		actTypeVO.setActCategoryId(actCategoryId);
		actTypeVO.setActCategoryName(actCategoryName);
		actTypeVO.setActCategoryDesc(actCategoryDesc);
		actTypeVO.setActMaxPart(actMaxPart);
		actTypeVO.setActMinPart(actMinPart);
		actTypeVO.setActFee(actFee);
		actTypeVO.setActNumber(actNumber);
		actTypeVO.setActTotalScore(actTotalScore);
		
dao.update(actTypeVO);
	
		
		return actTypeVO;
	}
	
	public void deleteActType(Integer actCategoryId) {
		dao.delete(actCategoryId);
	}
	
	public ActTypeVO getOneById(Integer actCategoryId) {
		return dao.findByPrimaryKey(actCategoryId);
	}
	
	public List<ActTypeVO> getAll() {
		return dao.getAll();
		
	}
	
	
}


