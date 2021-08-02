package com.shoporder.model;

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
	
}
