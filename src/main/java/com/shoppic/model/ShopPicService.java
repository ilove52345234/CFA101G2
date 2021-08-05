package com.shoppic.model;

import java.util.Base64;
import java.util.List;

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

	public void deleteShopPic(Integer itemPhotoId) {
		dao.delete(itemPhotoId);
	}

	public String getOneShopPic(Integer pdid) {
		byte[] img =  dao.getOnePic(pdid);//photodataId
		System.out.println(pdid);
		System.out.println(img);
		if(img != null) {
			String data = Base64.getEncoder().encodeToString(img);

			System.out.println(data);
			return data;
		} else {
			return "";
		}
	}

	public List<ShopPicVO> getAll() {
		return dao.getAll();

	}

	// 鬼哥教的 將ShopPicVO中的itemPhoto陣列化作字串才能輸出
	public String out(byte[] itemPhoto) {
		String data;
		data = Base64.getEncoder().encodeToString(itemPhoto);
		return data;
	}



}
