package com.shoporderdetail.model;

import java.util.List;

public interface ShopOrderDetailDAO_interface {
	
	public void insert(ShopOrderDetailVO shoporderdetailVO);
	
	public void update(ShopOrderDetailVO shoporderdetailVO);
	
	public void delete(Integer itemOrderId);
	
	// 用 Shop_Order_Detail 的 PK Item_Order_Id 去找
	public ShopOrderDetailVO findByPK (Integer itemOrderId);
	
	// 把 Item_Order_Id 放進去 <ShopOrderDetailVO> 這個集合
	public List<ShopOrderDetailVO> getAll();
	
	public List<ShopOrderDetailVO> shopJoinDetail(Integer itemId);
	
}
