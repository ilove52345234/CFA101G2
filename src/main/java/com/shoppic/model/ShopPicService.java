package com.shoppic.model;

public class ShopPicService {

	private ShopPicDAO_interface dao;

	public ShopPicService() {
		dao = new ShopPicJDBCDAO();
	}

	// 新增
	public ShopPicVO addShopPic(Integer itemPhotoId, Integer itemId, byte[] itemPhoto) {

		ShopPicVO shopPicVO = new ShopPicVO();

		shopPicVO.setItemId(itemId);
		shopPicVO.setItemPhoto(itemPhoto);

		dao.insert(shopPicVO);
		return shopPicVO;

	}

	// 修改
	public ShopPicVO updateShopPic(Integer itemPhotoId, Integer itemId, byte[] itemPhoto) {

		ShopPicVO shopPicVO = new ShopPicVO();
		System.out.println("12  " + shopPicVO.toString());

		shopPicVO.setItemPhotoId(itemPhotoId);
		shopPicVO.setItemId(itemId);
		shopPicVO.setItemPhoto(itemPhoto);

		dao.update(shopPicVO);
		return shopPicVO;

	}
}
