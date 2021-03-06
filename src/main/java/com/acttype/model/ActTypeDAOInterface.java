package com.acttype.model;

import java.util.*;

import com.act.model.ActVO;
import com.actphoto.model.ActPhotoVO;

public interface ActTypeDAOInterface {
		public void insert(ActTypeVO actTypeVO);
		public void update(ActTypeVO actTypeVO);
		public void delete (Integer actCategoryId);
		public ActTypeVO findByPrimaryKey(Integer actCategoryId);
		public List<ActTypeVO> getAll();
		public void insertWithActPhotos(ActTypeVO actTypeVO, List<ActPhotoVO> list);
	public Set<ActVO> getActByActCategoryId(Integer actCategoryId);
	public void inserComment(ActTypeVO actTypeVO);
		public void updateComment(ActTypeVO actTypeVO);
}
