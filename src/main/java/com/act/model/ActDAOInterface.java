package com.act.model;

import java.util.List;

public interface ActDAOInterface {

	public void insert(ActVO actVO);
	public void update(ActVO actVO);
	public ActVO findByPrimaryKey(Integer actId);
	public List<ActVO> getAll();
}
