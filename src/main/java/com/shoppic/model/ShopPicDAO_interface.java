package com.shoppic.model;

import java.util.List;

public interface ShopPicDAO_interface {
	
	public void insert(ShopPicVO shoppicVO);
	
	public void update(ShopPicVO shoppicVO);
	
	public void delete(Integer itemPhotoId);
	
	// 用 Shop_Pic 的 PK Item_Photo_Id 去找
	public ShopPicVO  findByPK (Integer itemPhotoId);
	
	// 把 Item_Photo_Id 放進去 <ShopPic> 這個集合
	public List<ShopPicVO> getAll();
	
}
