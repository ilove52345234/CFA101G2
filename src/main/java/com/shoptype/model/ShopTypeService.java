package com.shoptype.model;

import java.util.*;

import com.shop.model.ShopVO;

public class ShopTypeService {
	
	private ShopTypeDAO_interface dao;
	public ShopTypeService() {
		dao = new ShopTypeJDBCDAO();

	}
	
	// �s�W
	public ShopTypeVO addShopType(Integer itemCategoryId, String itemCategoryName, String itemCategoryDesc) {
		
		ShopTypeVO shopTypeVO = new ShopTypeVO();
		shopTypeVO.setItemCategoryId(itemCategoryId);
		shopTypeVO.setItemCategoryName(itemCategoryName);
		shopTypeVO.setItemCategoryDesc(itemCategoryDesc);
		
		dao.insert(shopTypeVO);
		return shopTypeVO;
	}
	
	// �ק�
	public ShopTypeVO updateShopType(Integer itemCategoryId, String itemCategoryName, String itemCategoryDesc) {

		ShopTypeVO shopTypeVO = new ShopTypeVO();	
		System.out.println("12  " + shopTypeVO.toString());
		shopTypeVO.setItemCategoryId(itemCategoryId);
		shopTypeVO.setItemCategoryName(itemCategoryName);
		shopTypeVO.setItemCategoryDesc(itemCategoryDesc);
			
		dao.update(shopTypeVO);
		return shopTypeVO;
	}
		
	// �R�� (�ٵL�k�ϥ�)
	public void deleteShopType(Integer itemCategoryId) {
		dao.delete(itemCategoryId);
	}
		
	// �浧�d��
	public ShopTypeVO getOneShopType(Integer itemCategoryId) {
		return dao.findByPK(itemCategoryId);
	}
		
	// �d�ߩҦ������O
	public List<ShopTypeVO> getAll() {
		return dao.getAll();
	}
	
	// �d�߬ۦP���O�������ӫ~
	public Set<ShopVO> getShopsByItemCategoryId(Integer itemCategoryId) {
		return dao.getShopsByItemCategoryId(itemCategoryId);
	}
	
}
