package com.rmorder.model;

import java.util.List;



public interface RmoDAO_interface {
	public void insert(RmoVO rmoVO);
	public void update(RmoVO rmoVO);
	public void delete(Integer roomOrderId);
	public  RmoVO findByPrimaryKey(Integer roomOrderId);
	public List<RmoVO> getAll();


}
