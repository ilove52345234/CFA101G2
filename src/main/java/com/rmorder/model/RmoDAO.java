package com.rmorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RmoDAO implements RmoDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmoVO.getMemId());
			pstmt.setTimestamp(2, rmoVO.getOrderDate());
			pstmt.setInt(3, rmoVO.getRoomOrderStatus());
			pstmt.setInt(4, rmoVO.getTotalPrice());

			pstmt.executeUpdate();

	

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmoVO.getRoomOrderId());
			pstmt.setInt(2, rmoVO.getMemId());
			pstmt.setTimestamp(3, rmoVO.getOrderDate());
			pstmt.setInt(4, rmoVO.getRoomOrderStatus());
			pstmt.setInt(5, rmoVO.getTotalPrice());

			pstmt.executeUpdate();


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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomOrderId);

			pstmt.executeUpdate();

		
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

}
