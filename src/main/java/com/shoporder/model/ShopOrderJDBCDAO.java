package com.shoporder.model;

import com.shoporderdetail.model.ShopOrderDetailJDBCDAO;
import com.shoporderdetail.model.ShopOrderDetailVO;
import com.utils.JDBCUtils;

import java.sql.*;
import java.util.*;

public class ShopOrderJDBCDAO implements ShopOrderDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";

	JDBCUtils jdbcUtils = new JDBCUtils();



	private static final String list_ALL_BY_MEM_ID_STMT =
			"SELECT * FROM SHOP_ORDER WHERE MEM_ID=? AND SHIPPING_STATUS!=3 AND SHIPPING_STATUS!=2 ORDER BY ITEM_ORDER_ID DESC";




	private static final String INSERT_STMT2 =
			"INSERT INTO SHOP_ORDER (MEM_ID, ITEM_ORDER_DATE, ITEM_AMOUNTS, PAYMENT_METHOD, SHIPPING_METHOD, SHIPPING_STATUS,ORDER_NAME,ORDER_MOBILE,ORDER_ADDRESS) VALUE(?, NOW(), ?, ?, ?, ?, ?, ?, ?)";



	private static final String ADD_STOCK =
			"UPDATE SHOP SET ITEM_QUANTITY = ITEM_QUANTITY + ? where ITEM_ID = ?";



	private static final String UPDATE_STATUS_STMT =
			"UPDATE SHOP_ORDER SET SHIPPING_STATUS = ? WHERE ITEM_ORDER_ID = ?";

	private static final String SHOP_ORDER_DETAIL_STMT=
			"SELECT d.ITEM_ID, d.ORDER_QUANTITY ,d.ITEM_AMOUNTS from SHOP_ORDER_DETAIL d\r\n" +
					"JOIN SHOP s on d.ITEM_ID = s.ITEM_ID \r\n" +
					"WHERE d.ITEM_ORDER_ID = ?";

	private static final String REDUCE_STOCK =
			"UPDATE SHOP SET ITEM_QUANTITY = ITEM_QUANTITY - ? where ITEM_ID = ?";

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

			// ????????????SQL?????????
		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
			// ??????JDBC?????????
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

			// ????????????SQL?????????
		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
			// ??????JDBC?????????
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

			System.out.println("????????????: " + updateCount);

			// ????????????SQL?????????
		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
			// ??????JDBC?????????
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
				shopOrderVO.setOrderName(rs.getString("ORDER_NAME"));
				shopOrderVO.setOrderAddress(rs.getString("ORDER_MOBILE"));
				shopOrderVO.setOrderMobile(rs.getString("ORDER_ADDRESS"));
			}

			// ????????????SQL??????
		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
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
				shopOrderVO.setOrderName(rs.getString("ORDER_NAME"));
				shopOrderVO.setOrderAddress(rs.getString("ORDER_MOBILE"));
				shopOrderVO.setOrderMobile(rs.getString("ORDER_ADDRESS"));

				list.add(shopOrderVO);
			}

			// ????????????SQL??????
		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
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
				shopOrderVO.setOrderName(rs.getString("ORDER_NAME"));
				shopOrderVO.setOrderAddress(rs.getString("ORDER_MOBILE"));
				shopOrderVO.setOrderMobile(rs.getString("ORDER_ADDRESS"));



				list.add(shopOrderVO);
			}

			// ????????????SQL??????
		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
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
	public void reduceStock(Integer itemId, Integer orderQuantity, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(REDUCE_STOCK);
			pstmt.setInt(1, itemId);
			pstmt.setInt(2, orderQuantity);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back-???-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public Integer insertWithShopOrderDetail(ShopOrderVO shopOrderVO, List<ShopOrderDetailVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer newShopOrderId = null;
		try {
			con = jdbcUtils.getConnection();

			con.setAutoCommit(false);

			// ???????????????????????????????????????????????????
			// ??????????????????????????????
			String cols[] = { "1" };
//			System.out.println("??????dao");
			pstmt = con.prepareStatement(INSERT_STMT2, cols);
			// ??????Statement.RETURN_GENERATED_KEYS??????AI??????
//			System.out.println("??????dao1");
			System.out.println(shopOrderVO.getMemId());
			pstmt.setInt(1, shopOrderVO.getMemId());
//			System.out.println("??????dao2");
			pstmt.setInt(2, shopOrderVO.getItemAmounts());
//			System.out.println("??????dao3");
			pstmt.setByte(3, shopOrderVO.getPaymentMethod());
//			System.out.println("??????dao4");
			pstmt.setByte(4, shopOrderVO.getShippingMethod());
//			System.out.println("??????dao5");
			pstmt.setByte(5, shopOrderVO.getShippingStatus());
//			System.out.println("??????dao6");
			pstmt.setString(6, shopOrderVO.getOrderName());
//			System.out.println("??????dao7");
			pstmt.setString(7, shopOrderVO.getOrderMobile());
//			System.out.println("??????dao8");
			pstmt.setString(8, shopOrderVO.getOrderAddress());
//			System.out.println("??????dao9");
			System.out.println(shopOrderVO.getOrderAddress());
//			System.out.println("??????dao10");
			pstmt.executeUpdate();
//			System.out.println("dao??????");

			ResultSet rs = pstmt.getGeneratedKeys();
			// ??????????????????????????????????????????????????????

			if (rs.next()) {
				newShopOrderId = rs.getInt(1);// rs??????PK????????????PK Index?????????
				System.out.println("????????????" + newShopOrderId + "???????????????");
			} else { // ???????????????????????? ?????????????????????
				System.out.println("?????????????????????");
			}
			rs.close();
			// ????????? ????????????????????????
			ShopOrderDetailJDBCDAO dao = new ShopOrderDetailJDBCDAO();
			for (ShopOrderDetailVO shopOrderDetailVO : list) {
				shopOrderDetailVO.setItemOrderId(new Integer(newShopOrderId));
				dao.insert2(shopOrderDetailVO, con);
				// ????????????
				Integer itemId = shopOrderDetailVO.getItemId();
				Integer orderQuantity = shopOrderDetailVO.getOrderQuantity();
				reduceStock(itemId, orderQuantity, con);
			}
			// commit ????????????excuteUpdate??????
			con.commit();
			con.setAutoCommit(true);

		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3. ???????????????exception????????????catch?????????
					System.err.print("Transaction is being rolled back from dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return newShopOrderId;
	}

	@Override
	public void addStock(Integer itemId, Integer orderQuantity, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(ADD_STOCK);
			pstmt.setInt(1, itemId);
			pstmt.setInt(2, orderQuantity);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back-???-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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


	@Override
	public List<ShopOrderVO> listAllByMemId(Integer memId) {
		List<ShopOrderVO> list = new ArrayList<ShopOrderVO>();
		ShopOrderVO shopOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(memId);
		try {
			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(list_ALL_BY_MEM_ID_STMT);
			pstmt.setInt(1,memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopOrderVO = new ShopOrderVO();
				shopOrderVO.setItemOrderId(rs.getInt(1));
				shopOrderVO.setMemId(rs.getInt(2));
				shopOrderVO.setItemOrderDate2(rs.getTimestamp(3));
				shopOrderVO.setItemAmounts(rs.getInt(4));
				shopOrderVO.setPaymentMethod(rs.getByte(5));
				shopOrderVO.setShippingMethod(rs.getByte(6));
				shopOrderVO.setShippingStatus(rs.getByte(7));
				shopOrderVO.setOrderName(rs.getString(8));
				shopOrderVO.setOrderMobile(rs.getString(9));
				shopOrderVO.setOrderAddress(rs.getString(10));
				list.add(shopOrderVO);
			}


		} catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
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
	public void confirmShopOrder(Integer shippingStatus, Integer itemOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			pstmt.setInt(1, shippingStatus);
			pstmt.setInt(2,itemOrderId);
			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
			// ??????JDBC?????????
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
	public void cancelShopOrder(Integer shippingStatus, Integer itemOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			pstmt.setInt(1, shippingStatus);
			pstmt.setInt(2,itemOrderId);
			pstmt.executeUpdate();

			//????????????
			List<ShopOrderDetailVO> list = getShopOrderDetailByItemOrderId(itemOrderId);
			for (ShopOrderDetailVO shopOrderDetailVO:list) {
				Integer itemId = shopOrderDetailVO.getItemId();
				Integer orderQuantity = shopOrderDetailVO.getOrderQuantity();
				addStock(itemId, orderQuantity, con);
			}

		}  catch (SQLException se) {
			throw new RuntimeException("?????????????????????" + se.getMessage());
			// ??????JDBC?????????
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
	}


	@Override
	public List<ShopOrderDetailVO> getShopOrderDetailByItemOrderId(Integer itemOrderId) {

		List<ShopOrderDetailVO>list = new ArrayList<ShopOrderDetailVO>();
		ShopOrderDetailVO shopOrderDetailVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {

			con = jdbcUtils.getConnection();


			pstmt = con.prepareStatement(SHOP_ORDER_DETAIL_STMT);
			pstmt.setInt(1, itemOrderId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				shopOrderDetailVO = new ShopOrderDetailVO();
				shopOrderDetailVO.setItemId(rs.getInt(1));
				shopOrderDetailVO.setOrderQuantity(rs.getInt(2));
				shopOrderDetailVO.setItemAmounts(rs.getInt(3));
				list.add(shopOrderDetailVO);
			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

}
