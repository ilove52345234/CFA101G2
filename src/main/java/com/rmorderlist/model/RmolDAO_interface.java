package com.rmorderlist.model;

import java.util.List;


public interface RmolDAO_interface {
	public void insert(RmolVO rmolVO);
	public void update(RmolVO rmolVO);
	public void delete(Integer orderListId);
	public  RmolVO findByPrimaryKey(Integer orderListId);
	public List<RmolVO> getAll();

}
