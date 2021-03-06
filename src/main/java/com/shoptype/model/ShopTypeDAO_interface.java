package com.shoptype.model;

import java.util.*;

import com.shop.model.ShopVO;

public interface ShopTypeDAO_interface {

	public void insert(ShopTypeVO shoptypeVO);

	public void update(ShopTypeVO shoptypeVO);

	public void delete(Integer itemCategoryId);
	
	// 用 Shop_Type 的 PK Item_Category_Id去找
	public ShopTypeVO findByPK(Integer itemCategoryId);
	
	// 全部類別查詢。把 Item_Category_Id 放進去 <ShopType> 這個集合
	public List<ShopTypeVO> getAll();	
	
	// 查詢相同類別的全部商品
	public Set<ShopVO> getShopsByItemCategoryId(Integer itemCategoryId);
	
}
