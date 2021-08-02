package com.shoppic.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopPicJDBCDAO implements ShopPicDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";

	JDBCUtils jdbcUtils = new JDBCUtils();


	private static final String INSERT_STMT = "INSERT INTO SHOP_PIC (ITEM_ID,ITEM_PHOTO) VALUE (?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOP_PIC ORDER BY ITEM_PHOTO_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOP_PIC WHERE ITEM_PHOTO_ID = ?";
	private static final String DELETE = "DELETE FROM SHOP_PIC WHERE ITEM_PHOTO_ID = ?";
	private static final String UPDATE = "UPDATE SHOP_PIC SET ITEM_PHOTO=? WHERE ITEM_ID = ?";

	@Override
	public void insert(ShopPicVO shopPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shopPicVO.getItemId());
			pstmt.setBytes(2, shopPicVO.getItemPhoto());

			int rowCount = pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + "筆圖片");
			//更新資料庫,傳回更新成功的筆數

			// 處理任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
			// 關閉JDBC的資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);// System.err API預設方法
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
	public void update(ShopPicVO shopPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setBytes(1, shopPicVO.getItemPhoto());
			pstmt.setInt(2, shopPicVO.getItemId());
			pstmt.executeUpdate();
			System.out.println("修改商品圖片成功!!");


			// 處理任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
			// 關閉JDBC的資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);// System.err API預設方法
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
	public void delete(Integer itemPhotoId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, itemPhotoId);

			pstmt.executeUpdate();


			// 處理任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
			// 關閉JDBC的資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);// System.err API預設方法
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
	public ShopPicVO findByPK(Integer itemPhotoId) {
		ShopPicVO shopPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, itemPhotoId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopPicVO = new ShopPicVO();
				shopPicVO.setItemPhotoId(rs.getInt("ITEM_PHOTO_ID"));
				shopPicVO.setItemId(rs.getInt("ITEM_ID"));
//				shopPicVO.setItemPhoto(rs.getbyte[]("ITEM_PHOTO"));
			}
			// 處理任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
			// 關閉JDBC的資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);// System.err API預設方法
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

		return shopPicVO;
	}

	@Override
	public List<ShopPicVO> getAll() {
		List<ShopPicVO> list = new ArrayList<ShopPicVO>();
		ShopPicVO shopPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopPicVO = new ShopPicVO();
				shopPicVO.setItemPhotoId(rs.getInt("ITEM_PHOTO_ID"));
				shopPicVO.setItemId(rs.getInt("ITEM_ID"));
//				shopPicVO.setItemPhoto(rs.getByte("ITEM_PHOTO"));
				list.add(shopPicVO);
			}

			// 處理任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
			// 關閉JDBC的資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);// System.err API預設方法
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
