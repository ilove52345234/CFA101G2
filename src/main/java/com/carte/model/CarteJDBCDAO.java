package com.carte.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarteJDBCDAO implements CarteJDBCDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/GROUP2?serverTimezone=Asia/Taipei"; //35.221.136.103
	String userid = "root"; //CFA101G2
	String passwd = "sao124154"; //123456
	
		private static final String INSERT_STMT = 
			"INSERT INTO CARTE (MEM_ID, USER_NAME, USER_PIC, USER_STATUS, USER_UPDATE) values(?,?,?,?,?)";
		private static final String GET_ONE_STMT = 
			"SELECT  USER_ID, MEM_ID, USER_NAME, USER_PIC, USER_STATUS, USER_UPDATE FROM CARTE where MEM_ID = ?";
		private static final String UPDATE = 
			"UPDATE CARTE set USER_NAME=?, USER_PIC=?, USER_UPDATE=? where MEM_ID = ?";//  USER_STATUS=? 
		

		@Override //新增
		public void insert(CarteVO carteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, carteVO.getMemId());
				pstmt.setString(2, carteVO.getUserName());
				pstmt.setBlob(3, carteVO.getUserPic());
				pstmt.setInt(4, carteVO.getUserStatus());
				pstmt.setTimestamp(5, carteVO.getUserUpdate());
				
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());

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
		
		@Override //查單一
		public CarteVO findByPrimaryKey(Integer userId) {

			CarteVO carteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, userId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					carteVO = new CarteVO();
					carteVO.setUserId(rs.getInt("User_ID"));
					carteVO.setMemId(rs.getInt("MEM_ID"));
					carteVO.setUserName(rs.getString("USER_NAME"));
					carteVO.setUserPic(rs.getBlob("USER_PIC"));
					carteVO.setUserStatus(rs.getInt("USER_STATUS"));
					carteVO.setUserUpdate(rs.getTimestamp("USER_UPDATE"));
				}

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
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
			return carteVO;
		}
		
		@Override //更新
		public void update(CarteVO carteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, carteVO.getUserName());
				pstmt.setBlob(2, carteVO.getUserPic());
//				pstmt.setInt(2, carteVO.getUserStatus());
				pstmt.setTimestamp(3, carteVO.getUserUpdate());
				pstmt.setInt(4, carteVO.getMemId());
				
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());

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

}
