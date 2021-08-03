package com.actphoto.model;

import java.sql.Connection;
import java.util.List;


public interface ActPhotoDAOInterface {
	public void insert(ActPhotoVO actPhotoVO);
	public void update(ActPhotoVO actPhotoVO);
//	public void delete (Integer actPhotoId);
	public ActPhotoVO findByPrimaryKey(Integer actPhotoId);
	public List<ActPhotoVO> getAll();
	public List<ActPhotoVO> getAllByActCategoryId(Integer actCategoryId);
	public void insert2 (ActPhotoVO actPhotoVO , Connection con);
	public ActPhotoVO getOneByActCategoryId(Integer actCategoryId);

}
