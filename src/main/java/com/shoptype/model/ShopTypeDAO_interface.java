package com.shoptype.model;

import java.util.*;

import com.shop.model.ShopVO;

public interface ShopTypeDAO_interface {

	public void insert(ShopTypeVO shoptypeVO);

	public void update(ShopTypeVO shoptypeVO);

	public void delete(Integer itemCategoryId);
	
	// �� Shop_Type �� PK Item_Category_Id�h��
	public ShopTypeVO findByPK(Integer itemCategoryId);
	
	// �������O�d�ߡC�� Item_Category_Id ��i�h <ShopType> �o�Ӷ��X
	public List<ShopTypeVO> getAll();	
	
	// �d�߬ۦP���O�������ӫ~
	public Set<ShopVO> getShopsByItemCategoryId(Integer itemCategoryId);
	
}
