package com.shoppic.model;

import java.util.Arrays;

public class ShopPicVO implements java.io.Serializable {

	private static final long serialVersionUID = 4734440834244742407L;
	private Integer itemPhotoId;
	private Integer itemId;
	private byte[] itemPhoto;

	public Integer getItemPhotoId() {
		return itemPhotoId;
	}

	public void setItemPhotoId(Integer itemPhotoId) {
		this.itemPhotoId = itemPhotoId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public byte[] getItemPhoto() {
		return itemPhoto;
	}

	public void setItemPhoto(byte[] itemPhoto) {
		this.itemPhoto = itemPhoto;
	}

	@Override
	public String toString() {
		return "ShopPicVO [itemPhotoId=" + itemPhotoId + ", itemId=" + itemId + ", itemPhoto="
				+ Arrays.toString(itemPhoto) + "]";
	}

}
