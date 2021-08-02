package com.shoporderdetail.model;

import java.util.List;
import com.shop.model.ShopVO;
import com.shoporder.model.ShopOrderVO;

public class ShopOrderDetailService {
	
	private ShopOrderDetailDAO_interface dao;

	public ShopOrderDetailService() {
		dao = new ShopOrderDetailJDBCDAO();
	}
	
	// 新增
//	public void addShopOrderDetail(Integer itemOrderId, Integer itemId, Integer itemQuantity, Integer itemPromotionId, 
//			Integer itemAmounts, Integer itemFinalAmount) {
//		
//		ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();
//		
//		
//		shopOrderDetailVO.setItemOrderId(itemOrderId);
//		shopOrderDetailVO.setItemId(itemId);
//		shopOrderDetailVO.setItemQuantity(itemQuantity);
//		shopOrderDetailVO.setItemPromotionId(itemPromotionId);
//		shopOrderDetailVO.setItemAmounts(itemAmounts);
//		shopOrderDetailVO.setItemFinalAmount(itemFinalAmount);
//		dao.insert(shopOrderDetailVO);	
//		
//	}
	
	// 修改
	public ShopOrderDetailVO updateShopOrderDetail(Integer itemOrderId, Integer itemId, Integer itemQuantity, 
			Integer itemPromotionId, Integer itemAmounts, Integer itemFinalAmount, ShopOrderVO shopOrderVO, ShopVO shopVO) {

		ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();		
		System.out.println("12  " + shopOrderDetailVO.toString());
		
		shopOrderDetailVO.setItemOrderId(itemOrderId);
		shopOrderDetailVO.setItemId(itemId);
		shopOrderDetailVO.setItemQuantity(itemQuantity);
		shopOrderDetailVO.setItemPromotionId(itemPromotionId);
		shopOrderDetailVO.setItemAmounts(itemAmounts);
		shopOrderDetailVO.setItemFinalAmount(itemFinalAmount);
		
		dao.update(shopOrderDetailVO);
		return shopOrderDetailVO;
		
	}
		
	// 刪除
	public void deleteShopOrderDetail(Integer itemOrderId) {
		dao.delete(itemOrderId);
	}
		
	// 單筆查詢
	public ShopOrderDetailVO getOneShopOrderDetail(Integer itemOrderId) {
		return dao.findByPK(itemOrderId);
	}
		
	// 全部查詢
	public List<ShopOrderDetailVO> getAll() {
		return dao.getAll();
	}
	
	public List<ShopOrderDetailVO> shopJoinDetail(Integer itemId) {
		return dao.shopJoinDetail(itemId);
	}
	
}
