package com.act.model;

import java.util.List;


import java.sql.Timestamp;

public class ActService {

	private ActDAOInterface dao;

	public ActService() {
		dao = new ActDAO();
	}

	public ActVO addAct(Integer actCategoryId, Integer actPromotionId, String actDescription, Timestamp actStart,
			Timestamp actEnd, String actStatus, Integer actFee, Integer applicants, Timestamp partStart,
			Timestamp partEnd, Integer actMaxPart, Integer actMinPart) {

		ActVO actVO = new ActVO();
		actVO.setActCategoryId(actCategoryId);
		actVO.setActPromotionId(actPromotionId);
		actVO.setActDescription(actDescription);
		actVO.setActStart(actStart);
		actVO.setActEnd(actEnd);
		actVO.setActStatus(actStatus);
		actVO.setActFee(actFee);
		actVO.setApplicants(applicants);
		actVO.setPartStart(partStart);
		actVO.setPartEnd(partEnd);
		actVO.setActMaxPart(actMaxPart);
		actVO.setActMinPart(actMinPart);

		dao.insert(actVO);
		return actVO;
	}

	public ActVO updateAct(Integer actId, Integer actCategoryId, Integer actPromotionId, String actDescription,
			Timestamp actStart, Timestamp actEnd, String actStatus, Integer actFee, Integer applicants,
			Timestamp partStart, Timestamp partEnd, Integer actMaxPart, Integer actMinPart) {

		ActVO actVO = new ActVO();
		actVO.setActId(actId);
		actVO.setActCategoryId(actCategoryId);
		actVO.setActPromotionId(actPromotionId);
		actVO.setActDescription(actDescription);
		actVO.setActStart(actStart);
		actVO.setActEnd(actEnd);
		actVO.setActStatus(actStatus);
		actVO.setActFee(actFee);
		actVO.setApplicants(applicants);
		actVO.setPartStart(partStart);
		actVO.setPartEnd(partEnd);
		actVO.setActMaxPart(actMaxPart);
		actVO.setActMinPart(actMinPart);

		dao.update(actVO);
		return actVO;

	}

	public ActVO getOneById(Integer actId) {
		return dao.findByPrimaryKey(actId);
	}

	public List<ActVO> getAll() {
		return dao.getAll();
	}

}
