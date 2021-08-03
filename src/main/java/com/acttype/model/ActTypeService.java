package com.acttype.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.actphoto.model.*;


public class ActTypeService {

	
	
	private ActTypeDAOInterface dao;
	private ActPhotoDAOInterface photodao;

	
	public ActTypeService() {
		dao = new ActTypeDAO();
		photodao = new ActPhotoDAO();
	}
	

	
	
	public void insertWithActPhotos(ActTypeVO actTypeVO, List<ActPhotoVO> photoList) {
		
			this.dao.insertWithActPhotos(actTypeVO, photoList);
	
		}
	
	
	public ActTypeVO addActTypeCommentCount(Integer actNumber, Integer actTotalScore) {
		
		ActTypeVO actTypeVO = new ActTypeVO();
		
		
		actTypeVO.setActNumber(actNumber);
		actTypeVO.setActTotalScore(actTotalScore);
		
		dao.inserComment(actTypeVO);
	
		
		return actTypeVO;
	}

	public ActTypeVO updateActTypeCommentCount(Integer actCategoryId, Integer actNumber, Integer actTotalScore) {
		
		ActTypeVO actTypeVO = new ActTypeVO();
		
		actTypeVO.setActCategoryId(actCategoryId);
		actTypeVO.setActNumber(actNumber);
		actTypeVO.setActTotalScore(actTotalScore);
	
		
		dao.updateComment(actTypeVO);
	
		
		return actTypeVO;
	}
	
	
	
	
	public ActTypeVO addActType(String actCategoryName, String actCategoryDesc,
			Integer actMaxPart,Integer actMinPart, Integer actFee) {
		
		ActTypeVO actTypeVO = new ActTypeVO();
		
		actTypeVO.setActCategoryName(actCategoryName);
		actTypeVO.setActCategoryDesc(actCategoryDesc);
		actTypeVO.setActMaxPart(actMaxPart);
		actTypeVO.setActMinPart(actMinPart);
		actTypeVO.setActFee(actFee);
//		actTypeVO.setActNumber(actNumber);
//		actTypeVO.setActTotalScore(actTotalScore);
		
		dao.insert(actTypeVO);
	
		
		return actTypeVO;
	}
	
	
	public ActTypeVO updateActType(Integer actCategoryId, String actCategoryName, String actCategoryDesc,
			Integer actMaxPart,Integer actMinPart, Integer actFee) {
		//Integer actNumber, Integer actTotalScore
		ActTypeVO actTypeVO = new ActTypeVO();
		
		actTypeVO.setActCategoryId(actCategoryId);
		actTypeVO.setActCategoryName(actCategoryName);
		actTypeVO.setActCategoryDesc(actCategoryDesc);
		actTypeVO.setActMaxPart(actMaxPart);
		actTypeVO.setActMinPart(actMinPart);
		actTypeVO.setActFee(actFee);
//		actTypeVO.setActNumber(0);
//		actTypeVO.setActTotalScore(0);
	
		
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


