package com.rmorderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class RmolJDBCDAO implements RmolDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ROOM_ORDER_LIST (ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_LIST_ID,ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE FROM ROOM_ORDER_LIST order by ORDER_LIST_ID";
	private static final String GET_ONE_STMT = "SELECT ORDER_LIST_ID,ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE FROM ROOM_ORDER_LIST where ORDER_LIST_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_ORDER_LIST where ORDER_LIST_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_ORDER_LIST set ROOM_ORDER_ID=?, ROOM_CATEGORY_ID=?, ROOM_PROMOTION_ID=?, ROOM_ID=?,MEM_NAME=?, MEM_NUMBER=?, CHECK_IN_DATE=?,CHECK_OUT_DATE=?,ROOM_TOTAL_PRICE=? where ORDER_LIST_ID = ?";


	@Override
	public void insert(RmolVO rmolVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmolVO.getRoomOrderId());
			pstmt.setInt(2, rmolVO.getRoomCategoryId());
			pstmt.setInt(3, rmolVO.getRoomPromotionId());
			pstmt.setInt(4, rmolVO.getRoomId());
			pstmt.setString(5, rmolVO.getMemName());
			pstmt.setInt(6, rmolVO.getMemNumber());
			pstmt.setTimestamp(7, rmolVO.getCheckInDate());
			pstmt.setTimestamp(8, rmolVO.getCheckOutDate());
			pstmt.setInt(9, rmolVO.getRoomTotalPrice());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
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
	
	}

	@Override
	public void update(RmolVO rmolVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmolVO.getRoomOrderId());
			pstmt.setInt(2, rmolVO.getRoomCategoryId());
			pstmt.setInt(3, rmolVO.getRoomPromotionId());
			pstmt.setInt(4, rmolVO.getRoomId());
			pstmt.setString(5, rmolVO.getMemName());
			pstmt.setInt(6, rmolVO.getMemNumber());
			pstmt.setTimestamp(7, rmolVO.getCheckInDate());
			pstmt.setTimestamp(8, rmolVO.getCheckOutDate());
			pstmt.setInt(9, rmolVO.getRoomTotalPrice());
			pstmt.setInt(10, rmolVO.getOrderListId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
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
		
	}

	@Override
	public void delete(Integer orderListId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderListId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		
	}

	@Override
	public RmolVO findByPrimaryKey(Integer orderListId) {
		RmolVO rmolVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderListId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmolVO = new RmolVO();
				rmolVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
				rmolVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmolVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmolVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
				rmolVO.setRoomId(rs.getInt("ROOM_ID"));
				rmolVO.setMemName(rs.getString("MEM_NAME"));
				rmolVO.setMemNumber(rs.getInt("MEM_NUMBER"));
				rmolVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
				rmolVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
				rmolVO.setRoomTotalPrice(rs.getInt("ROOM_TOTAL_PRICE"));
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return rmolVO;
	}

	@Override
	public List<RmolVO> getAll() {
		List<RmolVO> list = new ArrayList<RmolVO>();
		RmolVO rmolVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmolVO = new RmolVO();
				rmolVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
				rmolVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmolVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmolVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
				rmolVO.setRoomId(rs.getInt("ROOM_ID"));
				rmolVO.setMemName(rs.getString("MEM_NAME"));
				rmolVO.setMemNumber(rs.getInt("MEM_NUMBER"));
				rmolVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
				rmolVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
				rmolVO.setRoomTotalPrice(rs.getInt("ROOM_TOTAL_PRICE"));
				
				list.add(rmolVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
public static void main(String[] args) {
		
		//現在時間
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				System.out.println("現在時間:"+ts);
				
		RmolJDBCDAO dao = new RmolJDBCDAO();
		// 新增
		RmolVO rmolVO1 = new RmolVO();
		rmolVO1.setRoomOrderId(1);
		rmolVO1.setRoomCategoryId(7001);
		rmolVO1.setRoomPromotionId(1);
		rmolVO1.setRoomId(1);
		rmolVO1.setMemName("GB");
		rmolVO1.setMemNumber(2);
		rmolVO1.setCheckInDate(ts);
		rmolVO1.setCheckOutDate(ts);
		rmolVO1.setRoomTotalPrice(2000);
		dao.insert(rmolVO1);

		// 修改
		RmolVO rmolVO2 = new RmolVO();
		rmolVO2.setOrderListId(1);
		rmolVO2.setRoomOrderId(1);
		rmolVO2.setRoomCategoryId(7001);
		rmolVO2.setRoomPromotionId(1);
		rmolVO2.setRoomId(1);
		rmolVO2.setMemName("kk");
		rmolVO2.setMemNumber(2);
		rmolVO2.setCheckInDate(ts);
		rmolVO2.setCheckOutDate(ts);
		rmolVO2.setRoomTotalPrice(2000);

		dao.update(rmolVO2);

		// 刪除
		dao.delete(2);
		
//		Integer roomNo=1;

		// 查詢
		RmolVO rmolVO3 = dao.findByPrimaryKey(1);
      	if(rmolVO3==null) {
			throw new RuntimeException("PK:not found") ;
		}
		
		System.out.println(rmolVO3.getOrderListId() + ",");
		System.out.println(rmolVO3.getRoomOrderId() + ",");
		System.out.println(rmolVO3.getRoomCategoryId() + ",");
		System.out.println(rmolVO3.getRoomPromotionId() + ",");
		System.out.println(rmolVO3.getRoomId() + ",");
		System.out.println(rmolVO3.getMemName() + ",");
		System.out.println(rmolVO3.getMemNumber() + ",");
		System.out.println(rmolVO3.getCheckInDate() + ",");
		System.out.println(rmolVO3.getCheckOutDate() + ",");
		System.out.println(rmolVO3.getRoomTotalPrice() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<RmolVO> list = dao.getAll();
		for (RmolVO aRmol : list) {
			System.out.println(aRmol.getOrderListId() + ",");
			System.out.println(aRmol.getRoomOrderId() + ",");
			System.out.println(aRmol.getRoomCategoryId() + ",");
			System.out.println(aRmol.getRoomPromotionId() + ",");
			System.out.println(aRmol.getRoomId() + ",");
			System.out.println(aRmol.getMemName() + ",");
			System.out.println(aRmol.getMemNumber() + ",");
			System.out.println(aRmol.getCheckInDate() + ",");
			System.out.println(aRmol.getCheckOutDate() + ",");
			System.out.println(aRmol.getRoomTotalPrice() + ",");
			System.out.println("---------------------");

		}
	}

	

}
