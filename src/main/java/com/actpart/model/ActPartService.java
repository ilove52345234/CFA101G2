package com.actpart.model;

import java.sql.Timestamp;
import java.util.List;

import com.acttype.model.ActTypeVO;

public class ActPartService {
	
	
	private ActPartDAOInterface dao;
	
	public ActPartService() {
		dao = new ActPartDAO();
	}
	
	
	public ActPartVO addActPart(Integer memId,
			 Timestamp actApplyDate, Integer actStar) {
		
		ActPartVO actPartVO = new ActPartVO();
		
		actPartVO.setMemId(memId);
		actPartVO.setActApplyDate(actApplyDate);
		actPartVO.setActStar(actStar);
		
		
		new ActPartDAO().insert(actPartVO);
		
		
		return actPartVO;
	}
	
	
	public ActPartVO updateActPart(Integer actId, Integer memId,
			 Timestamp actApplyDate,Integer feeConfirm, Integer actStar, Timestamp actCommentDate, String actCommentText) {
		
		

		ActPartVO actPartVO = new ActPartVO();
		
		actPartVO.setActId(actId);
		actPartVO.setMemId(memId);
		actPartVO.setActApplyDate(actApplyDate);
		actPartVO.setFeeConfirm(feeConfirm);
		actPartVO.setActStar(actStar);
		actPartVO.setActCommentDate(actCommentDate);
		actPartVO.setActCommentText(actCommentText);
		
		new ActPartDAO().update(actPartVO);
		
		return actPartVO;
	}
	
	public void deleteActPart(Integer actId, Integer memId ) {
		dao.delete(actId,memId);
	}
	
	public ActPartVO getOneById(Integer actId, Integer memId) {
		return (ActPartVO) dao.findByPrimaryKey(actId, memId);
	}
	
	public List<ActPartVO> getAll() {
		return (List<ActPartVO>) dao.getAll();
		
	}
	
	
		
}
