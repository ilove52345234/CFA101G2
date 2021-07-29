package com.actphoto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActPhotoDAO implements ActPhotoDAOInterface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "CFA101G2";
//	String passwd = "123456";
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cfa101g2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";

	// SQL need to be overwritten;
	private static final String INSERT_STMT = "INSERT INTO ACT_TYPE ( ACT_CATEGORY_ID, ACT_PHOTO_FILE) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT ACT_PHOTO_ID, ACT_CATEGORY_ID,ACT_PHOTO_FILE FROM ACT_PHOTO order by ACT_PHOTO_ID";
	private static final String GET_ONE_STMT = "SELECT ACT_PHOTO_ID, ACT_CATEGORY_ID,ACT_PHOTO_FILE FROM ACT_PHOTO WHERE ACT_CATEGORY_ID = ?";
	private static final String UPDATE = "UPDATE ACT_TYPE set  ACT_CATEGORY_ID=?, ACT_PHOTO_FILE=? where ACT_PHOTO_ID=?";

	@Override
	public void insert(ActPhotoVO actPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, actPhotoVO.getActCategoryId());
			pstmt.setBytes(2, actPhotoVO.getActPhotoFile());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while inserting. " + se.getMessage());

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

	@Override
	public void update(ActPhotoVO actPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, actPhotoVO.getActCategoryId());
			pstmt.setBytes(2, actPhotoVO.getActPhotoFile());
			pstmt.setInt(3, actPhotoVO.getActPhotoId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while updating." + se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public ActPhotoVO findByPrimaryKey(Integer actCategoryId) {

		ActPhotoVO actPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, actCategoryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				actPhotoVO = new ActPhotoVO();
				actPhotoVO.setActPhotoId(rs.getInt("ACT_Photo_ID"));
				actPhotoVO.setActPhotoFile(rs.getBytes("ACT_PHOTO_FILE"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getOneById. " + se.getMessage());
			// Clean up JDBC resources
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
		return actPhotoVO;
	}

	@Override
	public List<ActPhotoVO> getAll() {

		List<ActPhotoVO> list = new ArrayList<ActPhotoVO>();
		ActPhotoVO actPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actPhotoVO = new ActPhotoVO();
				actPhotoVO.setActPhotoId(rs.getInt("ACT_PHOTO_ID"));
				actPhotoVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actPhotoVO.setActPhotoFile(rs.getBytes("ACT_PHOTO_FILE"));
				list.add(actPhotoVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getAll " + se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}

}
