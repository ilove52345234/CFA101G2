package com.actphoto.model;

import java.sql.Blob;
import java.sql.Connection;
import java.util.List;

//import com.acttype.model.ActTypeVO;

public class ActPhotoService {

	
	private ActPhotoDAOInterface dao;
	
	public ActPhotoService() {
		
		dao = new ActPhotoDAO();
	}

	
	public void addActPhotos(Integer actCategoryId,List<Blob> photoList) {
		
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		for (Blob actPhotoFile: photoList) {
			
			 actPhotoVO.setActCategoryId(actCategoryId);
			 actPhotoVO.setActPhotoFile(actPhotoFile);
			 this.dao.update(actPhotoVO);
		}
	}
	
	
	
	public ActPhotoVO addActTypeAndPhoto(Connection conn ,Integer actCategoryId, Blob actPhotoFile) {
		
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		
		actPhotoVO.setActCategoryId(actCategoryId);
		actPhotoVO.setActPhotoFile(actPhotoFile);
		this.dao.insert2(actPhotoVO, conn);
		
		
		return actPhotoVO;
	}
	
	public ActPhotoVO updateActPhoto(Integer actPhotoId, Integer actCategoryId, Blob actPhotoFile) {
		
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		
		actPhotoVO.setActPhotoId(actPhotoId);
		actPhotoVO.setActCategoryId(actCategoryId);
		actPhotoVO.setActPhotoFile(actPhotoFile);
		
		dao.update(actPhotoVO);
		
		return actPhotoVO;
	}
	


	public ActPhotoVO getOneById(Integer actPhotoId) {
		return dao.findByPrimaryKey(actPhotoId);
	}
	
	public List<ActPhotoVO> getAll() {
		return dao.getAll();
		
	}
	
	public List<ActPhotoVO> getAllByActCategoryId(Integer actCategoryId ) {
		return dao.getAllByActCategoryId(actCategoryId);
		
	}
	
	public ActPhotoVO getOneByTypeId(Integer actCategoryId) {
		return dao.getOneByActCategoryId(actCategoryId);
	}

	
	

}
