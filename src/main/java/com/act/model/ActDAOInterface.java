package com.act.model;

import java.util.List;
import java.util.Map;

public interface ActDAOInterface {

	public void insert(ActVO actVO);
	public void update(ActVO actVO);
	public void delete (Integer actId);
	public ActVO findByPrimaryKey(Integer actId);
	public List<ActVO> getAll();
	public List<ActVO> complexSearch(Map<String, String[]> condition);
	public void updateStatus(ActVO actVO);


	
	
}
