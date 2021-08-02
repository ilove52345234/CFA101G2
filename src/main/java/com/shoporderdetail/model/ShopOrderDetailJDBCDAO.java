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


	private static final String INSERT_STMT = "INSERT INTO SHOP_ORDER_DETAIL(ITEM_ORDER_ID, ITEM_ID, ITEM_QUANTITY, ITEM_PROMOTION_ID, ITEM_AMOUNTS, ITEM_FINAL_AMOUNT) VALUE(?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOP_ORDER_DETAIL ORDER BY ITEM_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOP_ORDER_DETAIL WHERE ITEM_ORDER_ID = ?";
	private static final String DELETE = "DElETE FROM SHOP_ORDER_DETAIL WHERE ITEM_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE SHOP_ORDER_DETAIL SET ITEM_ID = ? ,ITEM_QUANTITY = ? ,ITEM_PROMOTION_ID = ? ,ITEM_AMOUNTS = ? ,ITEM_FINAL_AMOUNT =? WHERE ITEM_ORDER_ID = ?";
	private static final String SHOP_JOIN_DETAIL = "SELECT SHOP_ORDER_DETAIL.ITEM_ORDER_ID, SHOP.ITEM_NAME, SHOP.ITEM_FEE, SHOP.ITEM_QUANTITY FROM SHOP_ORDER_DETAIL INNER JOIN SHOP ON SHOP_ORDER_DETAIL.ITEM_ID = SHOP.ITEM_ID WHERE SHOP.ITEM_ID = ?";
	
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
			System.out.println("新增 " + rowCount + "筆資料");
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
				shopOrderDetailVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
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
				shopOrderDetailVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
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
				shopVO.setItemName(rs.getString("SHOP.ITEM_NAME"));
				shopVO.setItemQuantity(rs.getInt("SHOP.ITEM_QUANTITY"));
				shopVO.setItemFee(rs.getInt("SHOP.ITEM_FEE"));
				System.out.println("shopVO" + shopVO);
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

	public static void main(String[] args) {

		ShopOrderDetailJDBCDAO dao = new ShopOrderDetailJDBCDAO();

		// 新增
//		ShopOrderDetailVO shopOrderDetailVO1 = new ShopOrderDetailVO();
//		shopOrderDetailVO1.setItemOrderId(5);
//		shopOrderDetailVO1.setItemId(15);
//		shopOrderDetailVO1.setItemQuantity(1);
//		shopOrderDetailVO1.setItemPromotionId(0);
//		shopOrderDetailVO1.setItemAmounts(10000);
//		shopOrderDetailVO1.setItemFinalAmount(10000);
//		dao.insert(shopOrderDetailVO1);

		// 修改
//		ShopOrderDetailVO shopOrderDetailVO2 = new ShopOrderDetailVO();
//		shopOrderDetailVO2.setItemId(2);
//		shopOrderDetailVO2.setItemQuantity(1);
//		shopOrderDetailVO2.setItemPromotionId(1001);
//		shopOrderDetailVO2.setItemAmounts(5000);
//		shopOrderDetailVO2.setItemFinalAmounts(5000);
//		shopOrderDetailVO2.setItemOrderId(1);
//		dao.update(shopOrderDetailVO2);

		// 刪除
//		dao.delete(2);

		// 單一查詢
//		ShopOrderDetailVO shopOrderDetailVO3 = dao.findByPK(2);
//		System.out.println("訂單編號： " + shopOrderDetailVO3.getItemOrderId());
//		System.out.println("商品編號： " + shopOrderDetailVO3.getItemId());
//		System.out.println("商品數量： " + shopOrderDetailVO3.getItemQuantity());
//		System.out.println("優惠方案編號： " + shopOrderDetailVO3.getItemPromotionId());
//		System.out.println("商品金額： " + shopOrderDetailVO3.getItemAmounts());
//		System.out.println("商品原價： " + shopOrderDetailVO3.getItemFinalAmount());
		
		// 全部查詢
//		List<ShopOrderDetailVO> list = dao.getAll();
//		for(ShopOrderDetailVO sShop : list) {
//			System.out.println("訂單編號： " + sShop.getItemOrderId());
//			System.out.println("商品編號： " + sShop.getItemId());
//			System.out.println("商品數量： " + sShop.getItemQuantity());
//			System.out.println("商品金額： " + sShop.getItemAmounts());
//			System.out.println("=====================");
//		}
		
		List<ShopOrderDetailVO> list = dao.shopJoinDetail(55);
		for(ShopOrderDetailVO sShop : list) {
			System.out.println("訂單編號： " + sShop.getItemOrderId());
			System.out.println("商品編號： " + sShop.getShopVO().getItemName());
			System.out.println("商品數量： " + sShop.getShopVO().getItemQuantity());
			System.out.println("商品金額： " + sShop.getShopVO().getItemFee());
			System.out.println("=====================");
		}
	}
}
