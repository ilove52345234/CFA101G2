package com.shoporderdetail.model;

import java.sql.*;
import java.util.*;

import com.shop.model.ShopVO;
import com.utils.JDBCUtils;

public class ShopOrderDetailJDBCDAO implements ShopOrderDetailDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";

	JDBCUtils jdbcUtils = new JDBCUtils();

	private static final String INSERT_STMT2 =
			"INSERT INTO SHOP_ORDER_DETAIL(ITEM_ORDER_ID,ITEM_ID,ORDER_QUANTITY,ITEM_PROMOTION_ID,ITEM_AMOUNTS,ITEM_FINAL_AMOUNT) "
					+ "VALUE(?,?,?,?,?,?)";
	private static final String INSERT_STMT = "INSERT INTO SHOP_ORDER_DETAIL(ITEM_ORDER_ID, ITEM_ID, ORDER_QUANTITY, ITEM_PROMOTION_ID, ITEM_AMOUNTS, ITEM_FINAL_AMOUNT) VALUE(?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOP_ORDER_DETAIL ORDER BY ITEM_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOP_ORDER_DETAIL WHERE ITEM_ORDER_ID = ?";
	private static final String DELETE = "DElETE FROM SHOP_ORDER_DETAIL WHERE ITEM_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE SHOP_ORDER_DETAIL SET ITEM_ID = ? ,ORDER_QUANTITY = ? ,ITEM_PROMOTION_ID = ? ,ITEM_AMOUNTS = ? ,ITEM_FINAL_AMOUNT =? WHERE ITEM_ORDER_ID = ?";
	private static final String SHOP_JOIN_DETAIL = "SELECT SHOP_ORDER_DETAIL.ITEM_ORDER_ID, SHOP_ORDER_DETAIL.ORDER_QUANTITY, SHOP_ORDER_DETAIL.ITEM_FINAL_AMOUNT, SHOP.ITEM_NAME, SHOP.ITEM_FEE FROM SHOP_ORDER_DETAIL INNER JOIN SHOP ON SHOP_ORDER_DETAIL.ITEM_ID = SHOP.ITEM_ID WHERE SHOP_ORDER_DETAIL.ITEM_ORDER_ID = ?";

	@Override
	public void insert(ShopOrderDetailVO shopOrderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, shopOrderDetailVO.getItemOrderId());
			pstmt.setInt(2, shopOrderDetailVO.getItemId());
			pstmt.setInt(3, shopOrderDetailVO.getItemQuantity());
			pstmt.setInt(4, 0);
			pstmt.setInt(5, shopOrderDetailVO.getItemAmounts());
			pstmt.setInt(6, shopOrderDetailVO.getItemFinalAmount());
			int rowCount = pstmt.executeUpdate();
//			System.out.println("新增 " + rowCount + "筆資料");
			//更新資料庫,傳回更新成功的筆數

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
	public void update(ShopOrderDetailVO shopOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shopOrderDetailVO.getItemId());
			pstmt.setInt(2, shopOrderDetailVO.getItemQuantity());
			pstmt.setInt(3, shopOrderDetailVO.getItemPromotionId());
			pstmt.setInt(4, shopOrderDetailVO.getItemAmounts());
			pstmt.setInt(5, shopOrderDetailVO.getItemFinalAmount());
			pstmt.setInt(6, shopOrderDetailVO.getItemOrderId());		
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
	public void delete(Integer itemOrderId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, itemOrderId);
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
	public ShopOrderDetailVO findByPK(Integer itemOrderId) {

		ShopOrderDetailVO shopOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();



			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itemOrderId);

			rs = pstmt.executeQuery();
			// ResultSet介面使用 executeQuery方法傳回查詢結果
			while (rs.next()) {
				shopOrderDetailVO = new ShopOrderDetailVO();
				shopOrderDetailVO.setItemOrderId(rs.getInt("ITEM_ORDER_ID"));
				shopOrderDetailVO.setItemId(rs.getInt("ITEM_ID"));
				shopOrderDetailVO.setItemQuantity(rs.getInt("ORDER_QUANTITY"));
				shopOrderDetailVO.setItemPromotionId(rs.getInt("ITEM_PROMOTION_ID"));
				shopOrderDetailVO.setItemAmounts(rs.getInt("ITEM_AMOUNTS"));
				shopOrderDetailVO.setItemFinalAmount(rs.getInt("ITEM_FINAL_AMOUNT"));

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
		return shopOrderDetailVO;
	}

	@Override
	public List<ShopOrderDetailVO> getAll() {

		List<ShopOrderDetailVO> list = new ArrayList<ShopOrderDetailVO>();
		ShopOrderDetailVO shopOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			// ResultSet介面使用 executeQuery方法傳回查詢結果
			while (rs.next()) {

				shopOrderDetailVO = new ShopOrderDetailVO();
				shopOrderDetailVO.setItemOrderId(rs.getInt("ITEM_ORDER_ID"));
				shopOrderDetailVO.setItemId(rs.getInt("ITEM_ID"));
				shopOrderDetailVO.setItemQuantity(rs.getInt("ORDER_QUANTITY"));
				shopOrderDetailVO.setItemPromotionId(rs.getInt("ITEM_PROMOTION_ID"));
				shopOrderDetailVO.setItemAmounts(rs.getInt("ITEM_AMOUNTS"));
				shopOrderDetailVO.setItemFinalAmount(rs.getInt("ITEM_FINAL_AMOUNT"));
				list.add(shopOrderDetailVO);
				
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
	public List<ShopOrderDetailVO> shopJoinDetail(Integer itemId) {

		List<ShopOrderDetailVO> listShopOrderDetailVO = new ArrayList<ShopOrderDetailVO>();
		ShopOrderDetailVO shopOrderDetailVO = null;
		ShopVO shopVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();



			pstmt = con.prepareStatement(SHOP_JOIN_DETAIL);

			pstmt.setInt(1, itemId);

			rs = pstmt.executeQuery();
			// ResultSet介面使用 executeQuery方法傳回查詢結果
			while (rs.next()) {
				shopOrderDetailVO = new ShopOrderDetailVO();
				shopVO = new ShopVO();
				shopOrderDetailVO.setItemOrderId(rs.getInt("SHOP_ORDER_DETAIL.ITEM_ORDER_ID"));
				shopOrderDetailVO.setOrderQuantity(rs.getInt("SHOP_ORDER_DETAIL.ORDER_QUANTITY"));
				shopOrderDetailVO.setItemFinalAmount(rs.getInt("SHOP_ORDER_DETAIL.ITEM_FINAL_AMOUNT"));
				shopVO.setItemName(rs.getString("SHOP.ITEM_NAME"));
				shopVO.setItemFee(rs.getInt("SHOP.ITEM_FEE"));
				shopVO.setItemId(itemId);
				shopOrderDetailVO.setShopVO(shopVO);
				listShopOrderDetailVO.add(shopOrderDetailVO);
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
		return listShopOrderDetailVO;
	}

	@Override
	public void insert2(ShopOrderDetailVO shopOrderDetailVO,Connection con) {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT2);

			pstmt.setInt(1, shopOrderDetailVO.getItemOrderId());
			pstmt.setInt(2, shopOrderDetailVO.getItemId());
			pstmt.setInt(3, shopOrderDetailVO.getOrderQuantity());
			pstmt.setInt(4, shopOrderDetailVO.getItemPromotionId());
			pstmt.setInt(5, shopOrderDetailVO.getItemAmounts());
			pstmt.setInt(6, shopOrderDetailVO.getItemFinalAmount());


			int rowCount = pstmt.executeUpdate();
//			System.out.println("新增 " + rowCount + "筆資料");
			//更新資料庫,傳回更新成功的筆數

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back-由-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+excep.getMessage());
				}
			}
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
		}
	}


}
