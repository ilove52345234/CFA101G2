package com.rmtypepic.model;

import com.utils.JDBCUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 7 * 1024 * 1024, maxRequestSize = 5 * 7 * 1024 * 1024)



public class UploadPicture {
	
//	private static final String driver = "com.mysql.cj.jdbc.Driver";
//	private static final String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	private static final String userid = "CFA101G2";
//	private static final String passwd = "A123456";
//
	private static final String UPDATE = "UPDATE ROOM_TYPE_PIC set ROOM_PHOTO=?" + "WHERE ROOM_PHOTO_ID = ?";
	JDBCUtils jdbcUtils = new JDBCUtils();

	
	public  void updatePicture(Integer ROOM_PHOTO_ID,String path){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(2, ROOM_PHOTO_ID);
			byte[] pic = getPictureByteArray(path);
			pstmt.setBytes(1, pic);
			pstmt.addBatch();
			
			pstmt.executeBatch();
			
			System.out.println("ROOM_PHOTO_ID:"+ROOM_PHOTO_ID+",path:"+path);
			

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}  
		baos.close();
		fis.close();

		return baos.toByteArray();  
 	}
		
	}

