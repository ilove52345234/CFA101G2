package com.shoporder.model;

import com.shoporderdetail.model.ShopOrderDetailVO;

import java.sql.Connection;
import java.util.List;

public class ShopOrderService {
	
	private ShopOrderDAO_interface dao ;
		
	public ShopOrderService() {		
		dao = new ShopOrderJDBCDAO();			
	}
	
	// 新增
	public ShopOrderVO addShopOrder(Integer itemOrderId, Integer memId, String itemOrderDate, 
		Integer itemAmounts, Byte paymentMethod, Byte shippingMethod, Byte shippingStatus) {
		
		ShopOrderVO shopOrderVO = new ShopOrderVO();
		
		shopOrderVO.setItemOrderId(itemOrderId);
		shopOrderVO.setMemId(memId);
		shopOrderVO.setItemOrderDate(itemOrderDate);
		shopOrderVO.setItemAmounts(itemAmounts);
		shopOrderVO.setPaymentMethod(paymentMethod);
		shopOrderVO.setShippingMethod(shippingMethod);
		shopOrderVO.setShippingStatus(shippingStatus);
			
		return shopOrderVO;
	}
	
	// 修改
	public ShopOrderVO updateShopOrder(Integer itemOrderId, Integer memId, String itemOrderDate, 
			Integer itemAmounts, Byte paymentMethod, Byte shippingMethod, Byte shippingStatus) {

		ShopOrderVO shopOrderVO = new ShopOrderVO();	
		System.out.println("12  " + shopOrderVO.toString());
		
		shopOrderVO.setItemOrderId(itemOrderId);
		shopOrderVO.setMemId(memId);
		shopOrderVO.setItemOrderDate(itemOrderDate);
		shopOrderVO.setItemAmounts(itemAmounts);
		shopOrderVO.setPaymentMethod(paymentMethod);
		shopOrderVO.setShippingMethod(shippingMethod);
		shopOrderVO.setShippingStatus(shippingStatus);
		
		dao.update(shopOrderVO);
		return shopOrderVO;
		
	}

	// 刪除
	public void deleteShopOrder(Integer itemOrderId) {
		dao.delete(itemOrderId);
	}
	
	// 單筆查詢
	public ShopOrderVO getOneShopOrder(Integer itemOrderId) {
		return dao.findByPK(itemOrderId);
	}
	
	// 全部查詢
	public List<ShopOrderVO> getAll() {
		return dao.getAll();
	}
	// 拿到指定範圍日期的訂單
	public List<ShopOrderVO> getIntervalOrder(String startDate,String endDate){
		ShopOrderVO shopOrderVO = new ShopOrderVO();	
		shopOrderVO.setStartDate(startDate);
		shopOrderVO.setEndDate(endDate);
		return dao.getIntervalOrder(shopOrderVO);
	}


	public Integer insertWithShopOrderDetail(ShopOrderVO shopOrderVO,List<ShopOrderDetailVO> list){
		return dao.insertWithShopOrderDetail(shopOrderVO,list);
	}
	//進行透過購物車達成訂單生成並且減去庫存
	public void confirmShopOrder(Integer shippingStatus,Integer itemOrderId) {
		dao.confirmShopOrder(shippingStatus, itemOrderId);
	}
	//確認訂單
	public ShopOrderVO findByPK(Integer itemOrderId ) {
		return dao.findByPK(itemOrderId);
	}

	//用訂單編號查訂單
	public List<ShopOrderDetailVO> getShopOrderDetailByItemOrderId(Integer itemOrderId){
		return dao.getShopOrderDetailByItemOrderId(itemOrderId);
	}
	//用訂單編號找訂單明細
	public void reduceStock(Integer itemId,Integer orderQuantity,Connection con) {
		dao.reduceStock(itemId, orderQuantity, con);
	}
	//減去庫存
	public void addStock(Integer itemId, Integer orderQuantity, Connection con) {
		dao.addStock(itemId, orderQuantity, con);
	}
	//加回庫存
	public void cancelShopOrder(Integer shippingStatus,Integer itemOrderId) {
		dao.cancelShopOrder(shippingStatus, itemOrderId);
	}
	//取消訂單

	public ShopOrderVO getOneOrder(Integer itemOrderId) {
		return dao.findByPK(itemOrderId);
	}

	public List<ShopOrderVO> listAllByMemId(Integer memId){
		return dao.listAllByMemId(memId);
	}


	
}
