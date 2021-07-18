package com.room.model;

import java.util.List;


public interface RmDAO_interface {
	 public void insert(RmVO rmVO);
	 public void update(RmVO rmVO);
	 public void delete(Integer rmVO);
	 public RmVO findByPrimaryKey(Integer roomId);
	 public List<RmVO> getAll();

}
