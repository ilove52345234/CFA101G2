package com.rmpromotion.model;

import java.util.List;

public interface RmpDAO_interface {
	public void insert(RmpVO rmpVO);
	public void update(RmpVO rmpVO);
	public void delete(Integer roomPromotionId);
	public  RmpVO findByPrimaryKey(Integer roomPromotionId);
	public List<RmpVO> getAll();
}
