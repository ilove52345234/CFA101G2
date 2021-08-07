package com.shop.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopJDBCDAO implements ShopDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";
JDBCUtils jdbcUtils = new JDBCUtils();

	private static final String GET_ONE_STMT2 = "SELECT * FROM SHOP WHERE ITEM_NAME = ?";

	private static final String INSERT_STMT = "INSERT INTO SHOP(ITEM_CATEGORY_ID,ITEM_DESCRIBTION,ITEM_FEE,ITEM_NAME,ITEM_QUANTITY,ITEM_STATUS,COMMENT_NUMBER,COMMENT_TOTAL_SCORE) VALUE(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOP where Item_status!=1 ORDER BY ITEM_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOP WHERE ITEM_ID = ?";
	private static final String DELETE = "DELETE FROM SHOP WHERE ITEM_ID = ?";
	private static final String UPDATE = "UPDATE SHOP SET ITEM_CATEGORY_ID = ?,ITEM_DESCRIBTION = ?,ITEM_FEE = ?,ITEM_NAME = ?,ITEM_QUANTITY = ?,ITEM_STATUS = ?,COMMENT_NUMBER = ?,COMMENT_TOTAL_SCORE = ? WHERE ITEM_ID = ?";
	private static final String SEARCH = "SELECT * FROM SHOP WHERE ITEM_NAME LIKE ?";

	@Override
	public ShopVO insert(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, shopVO.getItemCategoryId());
			pstmt.setString(2, shopVO.getItemDescribtion());
			pstmt.setInt(3, shopVO.getItemFee());
			pstmt.setString(4, shopVO.getItemName());
			pstmt.setInt(5, shopVO.getItemQuantity());
			pstmt.setByte(6, shopVO.getItemStatus());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			int rowCount = pstmt.executeUpdate();
			// 更新資料庫,傳回更新成功的筆數
			System.out.println("新增 " + rowCount + "筆資料");

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					shopVO.setItemId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
			return shopVO;
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
	public void update(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, shopVO.getItemCategoryId());
			pstmt.setString(2, shopVO.getItemDescribtion());
			pstmt.setInt(3, shopVO.getItemFee());
			pstmt.setString(4, shopVO.getItemName());
			pstmt.setInt(5, shopVO.getItemQuantity());
			pstmt.setByte(6, shopVO.getItemStatus());
			pstmt.setInt(7, shopVO.getCommentNumber());
			pstmt.setInt(8, shopVO.getCommentTotalScore());
			pstmt.setInt(9, shopVO.getItemId());
			int rowCount = pstmt.executeUpdate();

			// 更新資料庫,傳回更新成功的筆數
			System.out.println("修改" + rowCount + "筆資料");

		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
	public void delete(Integer itemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, itemId);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
	public ShopVO findByPK(Integer itemId) {

		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, itemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopVO = new ShopVO();
				shopVO.setItemId(rs.getInt("ITEM_ID"));
				shopVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopVO.setItemDescribtion(rs.getString("ITEM_DESCRIBTION"));
				shopVO.setItemFee(rs.getInt("ITEM_FEE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
				shopVO.setItemStatus(rs.getByte("ITEM_STATUS"));
				shopVO.setCommentNumber(rs.getInt("COMMENT_NUMBER"));
				shopVO.setCommentTotalScore(rs.getInt("COMMENT_TOTAL_SCORE"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return shopVO;
	}

	@Override
	public List<ShopVO> getAll() {

		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				shopVO = new ShopVO();
				shopVO.setItemId(rs.getInt("ITEM_ID"));
				shopVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopVO.setItemDescribtion(rs.getString("ITEM_DESCRIBTION"));
				shopVO.setItemFee(rs.getInt("ITEM_FEE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
				shopVO.setItemStatus(rs.getByte("ITEM_STATUS"));
				shopVO.setCommentNumber(rs.getInt("COMMENT_NUMBER"));
				shopVO.setCommentTotalScore(rs.getInt("COMMENT_TOTAL_SCORE"));
				shopVO.setShopPicSrc(
						"images?queryImg=ITEM_PHOTO&tableName=SHOP_PIC&colName=ITEM_ID&queryId=" + shopVO.getItemId());
				list.add(shopVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return list;
	}

	@Override
	public List<ShopVO> search(String itemName) {

		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, "%" + itemName + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopVO = new ShopVO();
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemId(rs.getInt("ITEM_ID"));
				shopVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopVO.setItemDescribtion(rs.getString("ITEM_DESCRIBTION"));
				shopVO.setItemFee(rs.getInt("ITEM_FEE"));
				shopVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
				shopVO.setItemStatus(rs.getByte("ITEM_STATUS"));
				shopVO.setCommentNumber(rs.getInt("COMMENT_NUMBER"));
				shopVO.setCommentTotalScore(rs.getInt("COMMENT_TOTAL_SCORE"));
				list.add(shopVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~" + se.getMessage());
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return list;
	}

	@Override
	public ShopVO findByitemName(String itemName) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT2);
			pstmt.setString(1, itemName);
			rs = pstmt.executeQuery();// 查詢資料庫,傳回ResultSet

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setItemId(rs.getInt("ITEM_ID"));
				shopVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopVO.setItemDescribtion(rs.getString("ITEM_DESCRIBTION"));
				shopVO.setItemFee(rs.getInt("ITEM_FEE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
				shopVO.setItemStatus(rs.getByte("ITEM_STATUS"));
				shopVO.setCommentNumber(rs.getInt("COMMENT_NUMBER"));
				shopVO.setCommentTotalScore(rs.getInt("COMMENT_TOTAL_SCORE"));
			}
			// 處理任何驅動錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return shopVO;
	}

}