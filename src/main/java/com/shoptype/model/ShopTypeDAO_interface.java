package com.shoptype.model;

import java.util.*;

import com.shop.model.ShopVO;

public interface ShopTypeDAO_interface {

	public void insert(ShopTypeVO shoptypeVO);

	public void update(ShopTypeVO shoptypeVO);

	public void delete(Integer itemCategoryId);
	
	// ノ Shop_Type  PK Item_Category_Idт
	public ShopTypeVO findByPK(Integer itemCategoryId);
	
	// 场摸琩高р Item_Category_Id 秈 <ShopType> 硂栋
	public List<ShopTypeVO> getAll();	
	
	// 琩高摸场坝珇
	public Set<ShopVO> getShopsByItemCategoryId(Integer itemCategoryId);
	
}
