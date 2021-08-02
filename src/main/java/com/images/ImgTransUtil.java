package com.images;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImgTransUtil {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";


	JDBCUtils jdbcUtils = new JDBCUtils();


	public byte[] getBytesFromDB(String tableName, String colName, String queryImg, String queryId)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] buffer = null;

		String queryStmt = "";
		if (tableName.equals("SHOP_PIC")) {
			queryStmt = "SELECT ITEM_PHOTO FROM SHOP_PIC WHERE ITEM_ID = ?";
			queryImg = "ITEM_PHOTO";
		}

		try {
			conn = jdbcUtils.getConnection();
			pstmt = conn.prepareStatement(queryStmt);
			pstmt.setString(1, queryId);
			System.out.println("queryId= "+queryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				buffer = rs.getBytes(queryImg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can't find pictures by query statement...try again by typing something else:)");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return buffer;
	}
}
