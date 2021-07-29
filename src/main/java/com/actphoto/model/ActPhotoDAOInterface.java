package com.actphoto.model;

import java.util.List;


public interface ActPhotoDAOInterface {
	public void insert(ActPhotoVO actPhotoVO);
	public void update(ActPhotoVO actPhotoVO);
	public ActPhotoVO findByPrimaryKey(Integer actCategoryId);
	public List<ActPhotoVO> getAll();
}
