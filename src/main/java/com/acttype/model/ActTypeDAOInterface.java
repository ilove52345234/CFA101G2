package com.acttype.model;

import java.util.*;

public interface ActTypeDAOInterface {
		public void insert(ActTypeVO actTypeVO);
		public void update(ActTypeVO actTypeVO);
		public void delete (Integer actCategoryId);
		public ActTypeVO findByPrimaryKey(Integer actCategoryId);
		public List<ActTypeVO> getAll();
		ActTypeVO findByPrimaryKey1(Integer actCategoryId);
}
