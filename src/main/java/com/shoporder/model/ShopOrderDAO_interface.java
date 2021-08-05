package com.shoporder.model;

import java.sql.Connection;
import java.util.List;

import com.shop.model.ShopVO;
import com.shoporderdetail.model.ShopOrderDetailVO;

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
	public void reduceStock(Integer itemId, Integer orderQuantity, Connection con);
	public Integer insertWithShopOrderDetail(ShopOrderVO shopOrderVO, List<ShopOrderDetailVO> list)	;

	public List<ShopOrderVO> listAllByMemId(Integer memId);
	//進行透過購物車達成訂單生成並且減去庫存
	public void confirmShopOrder(Integer shippingStatus,Integer itemOrderId);
	//確認訂單
	//用訂單編號查訂單
	public List<ShopOrderDetailVO> getShopOrderDetailByItemOrderId(Integer itemOrderId);
	//用訂單編號找訂單明細
	//減去庫存
	public void addStock(Integer itemId,Integer orderQuantity,Connection con);
	//加回庫存
	public void cancelShopOrder(Integer shippingStatus,Integer itemOrderId);
}
