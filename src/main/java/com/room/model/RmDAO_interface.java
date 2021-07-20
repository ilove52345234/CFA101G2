package com.room.model;

import com.emp.model.EmpVO;

import java.util.List;
import java.util.Map;


public interface RmDAO_interface {
	 public void insert(RmVO rmVO);
	 public void update(RmVO rmVO);
	 public void delete(Integer rmVO);
	 public RmVO findByPrimaryKey(Integer roomId);
	 public List<RmVO> getAll();
	public int findTotalCount(Map<String, String> condition);
	public List<RmVO> findByPage(int start, int rows, Map<String, String> condition);
}
