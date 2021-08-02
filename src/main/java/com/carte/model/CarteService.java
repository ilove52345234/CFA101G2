package com.carte.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class CarteService {
	
	private CarteJDBCDAO_interface jdbcdao;
	
	public CarteService() {
		jdbcdao = new CarteJDBCDAO();
	}
	
	//新增
	public CarteVO addCarte(Integer mamId, String userName, Blob userPic, Integer userStatus, Timestamp userUpdate) {
		
		CarteVO carteVO = new CarteVO();
		carteVO.setMemId(mamId);
		carteVO.setUserName(userName);
		carteVO.setUserPic(userPic);
		carteVO.setUserStatus(userStatus);
		carteVO.setUserUpdate(userUpdate);
		jdbcdao.insert(carteVO);
		
		return carteVO;
	}
	
	//更新
	public CarteVO updateCarte(Integer memId, String userName, Blob userPic, Timestamp userUpdate) { //Integer userStatus,  

		CarteVO carteVO = new CarteVO();
		carteVO.setMemId(memId);
		carteVO.setUserName(userName);
		carteVO.setUserPic(userPic);
//		carteVO.setUserStatus(userStatus);
		carteVO.setUserUpdate(userUpdate);
		jdbcdao.update(carteVO);

		return carteVO;
	}
	
	public CarteVO getOneCarte(Integer userId) {
		return jdbcdao.findByPrimaryKey(userId);
	}
}
