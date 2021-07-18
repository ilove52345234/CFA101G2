package com.rmorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class RmoJDBCDAO implements RmoDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ROOM_ORDER (MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE FROM ROOM_ORDER order by ROOM_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE FROM ROOM_ORDER where ROOM_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_ORDER where ROOM_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_ORDER set MEM_ID=?, ORDER_DATE=?, ROOM_ORDER_STATUS=?, TOTAL_PRICE=? where ROOM_ORDER_ID = ?";


	@Override
	public void insert(RmoVO rmoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmoVO.getMemId());
			pstmt.setTimestamp(2, rmoVO.getOrderDate());
			pstmt.setInt(3, rmoVO.getRoomOrderStatus());
			pstmt.setInt(4, rmoVO.getTotalPrice());

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
	public void update(RmoVO rmoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmoVO.getRoomOrderId());
			pstmt.setInt(2, rmoVO.getMemId());
			pstmt.setTimestamp(3, rmoVO.getOrderDate());
			pstmt.setInt(4, rmoVO.getRoomOrderStatus());
			pstmt.setInt(5, rmoVO.getTotalPrice());

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
	public void delete(Integer roomOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomOrderId);

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
	public RmoVO findByPrimaryKey(Integer roomOrderId) {
		RmoVO rmoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomOrderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmoVO = new RmoVO();
				rmoVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmoVO.setMemId(rs.getInt("MEM_ID"));
				rmoVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				rmoVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				rmoVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));

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
		return rmoVO;
	}

	@Override
	public List<RmoVO> getAll() {
		List<RmoVO> list = new ArrayList<RmoVO>();
		RmoVO rmoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmoVO = new RmoVO();
				rmoVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmoVO.setMemId(rs.getInt("MEM_ID"));
				rmoVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				rmoVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				rmoVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
				
				list.add(rmoVO);

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
				
		RmoJDBCDAO dao = new RmoJDBCDAO();
		// 新增
		RmoVO rmoVO1 = new RmoVO();
		rmoVO1.setMemId(1);
		rmoVO1.setOrderDate(ts);
		rmoVO1.setRoomOrderStatus(1);
		rmoVO1.setTotalPrice(2000);
		dao.insert(rmoVO1);

		// 修改
		RmoVO rmoVO2 = new RmoVO();
		rmoVO2.setRoomOrderId(1);
		rmoVO2.setMemId(1);
		rmoVO2.setOrderDate(ts);
		rmoVO2.setRoomOrderStatus(1);
		rmoVO2.setTotalPrice(1600);

		dao.update(rmoVO2);

		// 刪除
		dao.delete(3);
		
//		Integer roomNo=1;

		// 查詢
		RmoVO rmoVO3 = dao.findByPrimaryKey(1);
      	if(rmoVO3==null) {
			throw new RuntimeException("PK:not found") ;
		}
		
		System.out.println(rmoVO3.getRoomOrderId() + ",");
		System.out.println(rmoVO3.getMemId() + ",");
		System.out.println(rmoVO3.getOrderDate() + ",");
		System.out.println(rmoVO3.getRoomOrderStatus() + ",");
		System.out.println(rmoVO3.getTotalPrice() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<RmoVO> list = dao.getAll();
		for (RmoVO aRmo : list) {
			System.out.println(aRmo.getRoomOrderId() + ",");
			System.out.println(aRmo.getMemId() + ",");
			System.out.println(aRmo.getOrderDate() + ",");
			System.out.println(aRmo.getRoomOrderStatus() + ",");
			System.out.println(aRmo.getTotalPrice() + ",");
			System.out.println("---------------------");

		}
	}

}
