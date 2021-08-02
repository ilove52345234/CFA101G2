package com.carte.model;

import java.util.List;


public interface CarteJDBCDAO_interface {
	public void insert(CarteVO carteVO);
	public void update(CarteVO carteVO);
	public CarteVO findByPrimaryKey(Integer carteVO);

}
