package com.shop.model;

import java.util.List;
import com.shoppic.model.ShopPicDAO_interface;
import com.shoppic.model.ShopPicJDBCDAO;
import com.shoppic.model.ShopPicVO;

public class ShopService {
	
	private ShopDAO_interface dao;
	private ShopPicDAO_interface picDao;
	

	public ShopService() {
		dao = new ShopJDBCDAO();
		picDao = new ShopPicJDBCDAO();

	}
	
	// 新增
	public ShopVO addShop(Integer itemCategoryId, String itemDescribtion,
			Integer itemFee, String itemName, Integer itemQuantity, Byte itemStatus, ShopPicVO shopPicVO) {
		
		ShopVO shopVO = new ShopVO();
		// 新增商品資料
		shopVO.setItemCategoryId(itemCategoryId);
		shopVO.setItemDescribtion(itemDescribtion);
		shopVO.setItemFee(itemFee);
		shopVO.setItemName(itemName);
		shopVO.setItemQuantity(itemQuantity);
		shopVO.setItemStatus(itemStatus);
		shopVO = dao.insert(shopVO);		
		
		// 新增商品圖片
		shopPicVO.setItemId(shopVO.getItemId()); 
		picDao.insert(shopPicVO);
		
		return shopVO;
		
	}


	public ShopVO addShop2(Integer itemCategoryId, String itemDescribtion, Integer itemFee,
						  String itemName, Integer itemQuantity, Byte itemStatus, Integer commentNumber, Integer commentTotalScore) {
		ShopVO shopVO = new ShopVO();

		shopVO.setItemCategoryId(itemCategoryId);
		shopVO.setItemDescribtion(itemDescribtion);
		shopVO.setItemFee(itemFee);
		shopVO.setItemName(itemName);
		shopVO.setItemQuantity(itemQuantity);
		shopVO.setItemStatus(itemStatus);
		shopVO.setCommentNumber(commentNumber);
		shopVO.setCommentTotalScore(commentTotalScore);
		dao.insert(shopVO);

		return shopVO;
	}



	// 修改
	public ShopVO updateShop(Integer itemId, Integer itemCategoryId, String itemDescribtion,
			Integer itemFee, String itemName, Integer itemQuantity, Byte itemStatus, Integer commentNumber, Integer commentTotalScore,ShopPicVO shopPicVO) {

		ShopVO shopVO = new ShopVO();
		
		// 修改商品資料
		shopVO.setItemId(itemId);
		shopVO.setItemCategoryId(itemCategoryId);
		shopVO.setItemDescribtion(itemDescribtion);
		shopVO.setItemFee(itemFee);
		shopVO.setItemName(itemName);
		shopVO.setItemQuantity(itemQuantity);
		shopVO.setItemStatus(itemStatus);
		shopVO.setCommentNumber(commentNumber);
		shopVO.setCommentTotalScore(commentTotalScore);
		dao.update(shopVO);
		
		
		if(shopPicVO.getItemPhoto() != null) {
			// 修改商品圖片
			shopPicVO.setItemId(itemId); 
			picDao.update(shopPicVO);
		}
		
		return shopVO;
		
	}

	// 刪除
	public void deleteShop(Integer itemId) {
		dao.delete(itemId);
	}
	
	// 單筆查詢
	public ShopVO getOneShop(Integer itemId) {
		return dao.findByPK(itemId);
	}
	
	// 全部查詢
	public List<ShopVO> getAll() {
		return dao.getAll();
	}


	public ShopVO updateShop2(Integer itemId, Integer itemCategoryId, String itemDescribtion, Integer itemFee,
							 String itemName, Integer itemQuantity, Byte itemStatus, Integer commentNumber, Integer commentTotalScore) {
		ShopVO shopVO = new ShopVO();

		shopVO.setItemId(itemId);
		shopVO.setItemCategoryId(itemCategoryId);
		shopVO.setItemDescribtion(itemDescribtion);
		shopVO.setItemFee(itemFee);
		shopVO.setItemName(itemName);
		shopVO.setItemQuantity(itemQuantity);
		shopVO.setItemStatus(itemStatus);
		shopVO.setCommentNumber(commentNumber);
		shopVO.setCommentTotalScore(commentTotalScore);
		dao.update(shopVO);

		return shopVO;

	}


	// 關鍵字查詢
	public List<ShopVO> getSearch(String itemName){
		return dao.search(itemName);
	}
	public ShopVO getOneShop2(String itemName) {
		return dao.findByitemName(itemName);
	}//會員利用名稱查詢商品
}	