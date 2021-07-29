package com.actphoto.model;

import java.util.List;


public class ActPhotoService {

	private ActPhotoDAOInterface dao;
	
	public ActPhotoService() {
		dao = new ActPhotoDAO();
	}

	
	public ActPhotoVO addActPhoto(Integer actCategoryId, byte[] actPhotoFile) {
		
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		actPhotoVO.setActCategoryId(actCategoryId);
		actPhotoVO.setActPhotoFile(actPhotoFile);
		
		dao.insert(actPhotoVO);
		return actPhotoVO;
	}
	
	public ActPhotoVO updateActPhoto(Integer actPhotoId, Integer actCategoryId, byte[] actPhotoFile) {
		
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		actPhotoVO.setActCategoryId(actCategoryId);
		actPhotoVO.setActPhotoFile(actPhotoFile);
		dao.update(actPhotoVO);
		return actPhotoVO;
	}
	
	
	public ActPhotoVO getOneById(Integer actCategoryId) {
		return dao.findByPrimaryKey(actCategoryId);
	}
	
	public List<ActPhotoVO> getAll() {
		return dao.getAll();
		
	}
	
}
