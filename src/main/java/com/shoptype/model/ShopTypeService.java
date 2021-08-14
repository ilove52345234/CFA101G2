package com.shoptype.model;

import java.util.*;

import com.shop.model.ShopVO;

public class ShopTypeService {
	
	private ShopTypeDAO_interface dao;
	public ShopTypeService() {
		dao = new ShopTypeJDBCDAO();

	}
	
	// 新增
	public ShopTypeVO addShopType(Integer itemCategoryId, String itemCategoryName, String itemCategoryDesc) {
		
		ShopTypeVO shopTypeVO = new ShopTypeVO();
		shopTypeVO.setItemCategoryId(itemCategoryId);
		shopTypeVO.setItemCategoryName(itemCategoryName);
		shopTypeVO.setItemCategoryDesc(itemCategoryDesc);
		
		dao.insert(shopTypeVO);
		return shopTypeVO;
	}
	
	// 修改
	public ShopTypeVO updateShopType(Integer itemCategoryId, String itemCategoryName, String itemCategoryDesc) {

		ShopTypeVO shopTypeVO = new ShopTypeVO();	
		System.out.println("12  " + shopTypeVO.toString());
		shopTypeVO.setItemCategoryId(itemCategoryId);
		shopTypeVO.setItemCategoryName(itemCategoryName);
		shopTypeVO.setItemCategoryDesc(itemCategoryDesc);
			
		dao.update(shopTypeVO);
		return shopTypeVO;
	}
		
	// 刪除 (還無法使用)
	public void deleteShopType(Integer itemCategoryId) {
		dao.delete(itemCategoryId);
	}
		
	// 單筆查詢
	public ShopTypeVO getOneShopType(Integer itemCategoryId) {
		return dao.findByPK(itemCategoryId);
	}
		
	// 查詢所有的類別
	public List<ShopTypeVO> getAll() {
		return dao.getAll();
	}
	
	// 查詢相同類別的全部商品
	public Set<ShopVO> getShopsByItemCategoryId(Integer itemCategoryId) {
		return dao.getShopsByItemCategoryId(itemCategoryId);
	}
	
}
