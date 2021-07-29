package com.act.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class BLOB {
		private static final String SQL = "INSERT INTO ACT_PHOTO(ACT_PHOTO_ID, ACT_CATEGORY_ID, ACT_PHOTO_FILE) VALUES (?, ?, ?)";

		public static void main(String[] args) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei", "CFA101G2", "123456");
				pstmt = con.prepareStatement(SQL);

	      //☆1. setBytes
				pstmt.setInt(1, 11);
				pstmt.setInt(2, 11);
				byte[] pic = getPictureByteArray("WebContent/images/攀岩.jpg");
				pstmt.setBytes(3, pic);
				pstmt.executeUpdate();			

				
				
				System.out.println("新增成功");

			} catch (ClassNotFoundException ce) {
				System.out.println(ce);
			} catch (SQLException se) {
				System.out.println(se);
			} catch (IOException ie) {
				System.out.println(ie);
			} finally {
				 
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						System.out.println(se);
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						System.out.println(se);
					}
				}
			}
		}

		// 使用byte[]方式
		public static byte[] getPictureByteArray(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[fis.available()];
	    /*  取得容量的大小
	        ＊File.length()：檔案的大小
	        ＊FileInputStream.available：資料流源頭的大小
	    */
			fis.read(buffer);
			fis.close();
			return buffer;
	  }

}
