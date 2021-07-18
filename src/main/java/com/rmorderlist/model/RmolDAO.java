package com.rmorderlist.model;

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

public class RmolDAO implements RmolDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderListId);

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
	public RmolVO findByPrimaryKey(Integer orderListId) {
		RmolVO rmolVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
