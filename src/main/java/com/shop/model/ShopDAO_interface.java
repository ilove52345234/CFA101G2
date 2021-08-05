package com.shop.model;

import java.util.List;

public interface ShopDAO_interface {

	public ShopVO insert(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public void delete(Integer itemId);
	
	// 搜尋商品
	public List<ShopVO> search(String itemName);

	// 用 Shop 的 PK itemId 去找
	public ShopVO findByPK(Integer itemId);
	
	// 把 Item_Id 放進去 <Shop> 這個集合
	public List<ShopVO> getAll();

	public ShopVO findByitemName(String itemName);
	
}
