package com.act.model;

import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class ActService {

	private ActDAOInterface dao;
	
	public ActService() {
		dao = new ActDAO();
	}
	
	
	public ActVO addAct(Integer actCategoryId, Integer actPromotionId, String actDescription, Timestamp actStart, Timestamp actEnd, String actStatus,
			Integer actFee,  Timestamp partStart, Timestamp partEnd, Integer actMaxPart, Integer actMinPart) {
		//Integer applicants,
		ActVO actVO = new ActVO();
		actVO.setActCategoryId(actCategoryId);
		actVO.setActPromotionId(actPromotionId);
		actVO.setActDescription(actDescription);
		actVO.setActStart(actStart);
		actVO.setActEnd(actEnd);
		actVO.setActStatus(actStatus);
		actVO.setActFee(actFee);
//		actVO.setApplicants(applicants);
		actVO.setPartStart(partStart);
		actVO.setPartEnd(partEnd);
		actVO.setActMaxPart(actMaxPart);
		actVO.setActMinPart(actMinPart);
		
		dao.insert(actVO);
		return actVO;
	}
	
	public ActVO updateAct(Integer actId, String actDescription, String actStatus) {
		
		ActVO actVO = new ActVO();
		actVO.setActId(actId);
		actVO.setActDescription(actDescription);
		actVO.setActStatus(actStatus);

		dao.update(actVO);
		return actVO;
				
	}
	
	public void deleteAct(Integer actId) {
		dao.delete(actId);
	}
	
	public ActVO getOneById(Integer actId) {
		return dao.findByPrimaryKey(actId);
	}
	
	public List<ActVO> getAll() {
		return dao.getAll();
		
	}
	
	public List<ActVO> complexSearch(Map<String, String[]> map) {
		return dao.complexSearch(map);
		
	}
	
	
	//還沒做完，等畫面調整完再改
	public void startAct(Integer actId) {
		
//		ActVO actVO = this.dao.findByPrimaryKey(actId);
//		Integer actMaxPart = actVO.getActMaxPart();
//		Integer actMinPart = actVO.getActMinPart();
		
//		Integer applicants= actVO.getApplicants();
//		Timestamp T1 = actVO.getPartStart();
//		Timestamp T2 = actVO.getPartEnd();
//		if()
		
//		return dao.(actVO);
		
	}
	
	
}
