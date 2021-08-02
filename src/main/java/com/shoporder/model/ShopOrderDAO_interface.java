package com.shoporder.model;

import java.util.List;

import com.shop.model.ShopVO;

public interface ShopOrderDAO_interface {
	
	public void insert(ShopOrderVO shopOrderVO);

	public void update(ShopOrderVO shopOrderVO);
	
	public void delete(Integer itemOrderId);
	
	// 用 ShopOrder 的 PK itemOrderId 去找
	public ShopOrderVO findByPK (Integer itemOrderId);
	
	// 把 itemOrderId 放進去 <ShopOrder> 這個集合		
	public List<ShopOrderVO> getAll();
	
	// 拿到指定範圍日期的訂單
	public List<ShopOrderVO> getIntervalOrder(ShopOrderVO shopOrderVO);

}
