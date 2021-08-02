package com.shoporder.model;

import com.utils.JDBCUtils;

import java.sql.*;
import java.util.*;

public class ShopOrderJDBCDAO implements ShopOrderDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";

	JDBCUtils jdbcUtils = new JDBCUtils();


	private static final String GET_ALL_STMT = "SELECT * FROM SHOP_ORDER";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOP_ORDER WHERE ITEM_ORDER_ID = ?";
	private static final String INSERT_STMT = "INSERT INTO SHOP_ORDER (MEM_ID, ITEM_ORDER_DATE, ITEM_AMOUNTS, PAYMENT_METHOD, SHIPPING_METHOD, SHIPPING_STATUS) VALUE(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE SHOP_ORDER SET MEM_ID = ?, ITEM_ORDER_DATE = ?, ITEM_AMOUNTS = ?, PAYMENT_METHOD = ?, SHIPPING_METHOD = ?, SHIPPING_STATUS = ? WHERE ITEM_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM SHOP_ORDER WHERE ITEM_ORDER_ID = ?";
	private static final String GET_INTERVAL_ORDER = "SELECT * FROM SHOP_ORDER WHERE ITEM_ORDER_DATE>= ? AND ITEM_ORDER_DATE <= ?; ";

	@Override
	public void insert(ShopOrderVO shopOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, shopOrderVO.getMemId());
			pstmt.setString(2, shopOrderVO.getItemOrderDate());
			pstmt.setInt(3, shopOrderVO.getItemAmounts());
			pstmt.setByte(4, shopOrderVO.getPaymentMethod());
			pstmt.setByte(5, shopOrderVO.getShippingMethod());
			pstmt.setByte(6, shopOrderVO.getShippingStatus());

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
	public void update(ShopOrderVO shopOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shopOrderVO.getMemId());
			pstmt.setString(2, shopOrderVO.getItemOrderDate());
			pstmt.setInt(3, shopOrderVO.getItemAmounts());
			pstmt.setByte(4, shopOrderVO.getPaymentMethod());
			pstmt.setByte(5, shopOrderVO.getShippingMethod());
			pstmt.setByte(6, shopOrderVO.getShippingStatus());
			pstmt.setInt(7, shopOrderVO.getItemOrderId());

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
	public void delete(Integer itemOrderId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, itemOrderId);

			updateCount = pstmt.executeUpdate();

			System.out.println("更動比數: " + updateCount);

			// 處理任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤" + se.getMessage());
			// 關閉JDBC的資源
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
	public ShopOrderVO findByPK(Integer itemOrderId) {

		ShopOrderVO shopOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itemOrderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopOrderVO = new ShopOrderVO();
				shopOrderVO.setItemOrderId(rs.getInt("ITEM_ORDER_ID"));
				shopOrderVO.setMemId(rs.getInt("MEM_ID"));
				shopOrderVO.setItemOrderDate(rs.getString("ITEM_ORDER_DATE"));
				shopOrderVO.setItemAmounts(rs.getInt("ITEM_AMOUNTS"));
				shopOrderVO.setPaymentMethod(rs.getByte("PAYMENT_METHOD"));
				shopOrderVO.setShippingMethod(rs.getByte("SHIPPING_METHOD"));
				shopOrderVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));

			}

			// 處理任何SQL錯誤
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

		return shopOrderVO;
	}

	@Override
	public List<ShopOrderVO> getAll() {
		List<ShopOrderVO> list = new ArrayList<ShopOrderVO>();
		ShopOrderVO shopOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopOrderVO = new ShopOrderVO();
				shopOrderVO.setItemOrderId(rs.getInt("ITEM_ORDER_ID"));
				shopOrderVO.setMemId(rs.getInt("MEM_ID"));
				shopOrderVO.setItemOrderDate(rs.getString("ITEM_ORDER_DATE"));
				shopOrderVO.setItemAmounts(rs.getInt("ITEM_AMOUNTS"));
				shopOrderVO.setPaymentMethod(rs.getByte("PAYMENT_METHOD"));
				shopOrderVO.setShippingMethod(rs.getByte("SHIPPING_METHOD"));
				shopOrderVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));
				list.add(shopOrderVO);
			}

			// 處理任何SQL錯誤
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
	public List<ShopOrderVO> getIntervalOrder(ShopOrderVO shopOrderVO) {
		List<ShopOrderVO> list = new ArrayList<ShopOrderVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(GET_INTERVAL_ORDER);

			pstmt.setString(1, shopOrderVO.getStartDate());
			pstmt.setString(2, shopOrderVO.getEndDate());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopOrderVO = new ShopOrderVO();
				shopOrderVO.setItemOrderId(rs.getInt("ITEM_ORDER_ID"));
				shopOrderVO.setMemId(rs.getInt("MEM_ID"));
				shopOrderVO.setItemOrderDate(rs.getString("ITEM_ORDER_DATE"));
				shopOrderVO.setItemAmounts(rs.getInt("ITEM_AMOUNTS"));
				shopOrderVO.setPaymentMethod(rs.getByte("PAYMENT_METHOD"));
				shopOrderVO.setShippingMethod(rs.getByte("SHIPPING_METHOD"));
				shopOrderVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));
				list.add(shopOrderVO);
			}

			// 處理任何SQL錯誤
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

	public static void main(String[] args) {

		ShopOrderJDBCDAO dao = new ShopOrderJDBCDAO();

		// 新增
//		ShopOrderVO shopOrderVO1 = new ShopOrderVO();
//		shopOrderVO1.setMemId(6);
//		shopOrderVO1.setItemOrderDate("2021-08-01");
//		shopOrderVO1.setItemAmounts(10000);
//		shopOrderVO1.setPaymentMethod((byte) 0);
//		shopOrderVO1.setShippingMethod((byte) 0);
//		shopOrderVO1.setShippingStatus((byte) 0);
//		dao.insert(shopOrderVO1);

		// 修改
//		ShopOrderVO shopOrderVO2 = new ShopOrderVO();	
//		shopOrderVO2.setMemId(5);
//		shopOrderVO2.setItemOrderDate("2021-07-02 12:20:00");
//		shopOrderVO2.setItemAmounts(1000);
//		shopOrderVO2.setPaymentMethod((byte)1);
//		shopOrderVO2.setShippingMethod((byte)1);
//		shopOrderVO2.setShippingStatus((byte)0);
//		shopOrderVO2.setItemOrderId(1);		
//		dao.update(shopOrderVO2);

		// 刪除
//		dao.delete(3);

		// 單筆查詢
//		ShopOrderVO shopOrderVO3 = dao.findByPK(1);
//		System.out.println("訂單編號 : " + shopOrderVO3.getItemOrderId());
//		System.out.println("會員編號 : " + shopOrderVO3.getMemId());
//		System.out.println("訂單成立時間 : " + shopOrderVO3.getItemOrderDate());
//		System.out.println("訂單總金額 : "+ shopOrderVO3.getItemAmounts()+ " NTD ");
//		System.out.println("付款方式 : " + shopOrderVO3.getPaymentMethod());
//		System.out.println("運送方式 : " + shopOrderVO3.getShippingMethod());
//		System.out.println("訂單狀態 : " + shopOrderVO3.getShippingStatus());

		// 全部查詢
//		List<ShopOrderVO> list = dao.getAll();
//		for (ShopOrderVO sShop : list) {
//			System.out.println("訂單編號 : " + sShop.getItemOrderId());
//			System.out.println("會員編號 : " + sShop.getMemId());
//			System.out.println("訂單成立時間 : " + sShop.getItemOrderDate());
//			System.out.println("訂單總金額 : "+ sShop.getItemAmounts());
//			System.out.println("付款方式 : " + sShop.getPaymentMethod());
//			System.out.println("運送方式 : " + sShop.getShippingMethod());
//			System.out.println("訂單狀態 : " + sShop.getShippingStatus());
//			System.out.println("=============================");
//		}
	}
}
