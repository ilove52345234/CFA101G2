package com.rmtypetracklist.model;

import java.util.List;


public interface RmttlDAO_interface {
	public void insert(RmttlVO rmttlVO);
	public void update(RmttlVO rmttlVO);
	public void delete(Integer roomCategoryId,Integer memId);
	public  RmttlVO findByPrimaryKey(Integer roomCategoryId,Integer memId);
	public List<RmttlVO> getAll();
}
	


