package com.rschedule.model;

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

public class RsDAO implements RsDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ROOM_SCHEDULE (ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_SCHEDULE_ID, ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED FROM ROOM_SCHEDULE order by ROOM_SCHEDULE_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_SCHEDULE_ID, ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED FROM ROOM_SCHEDULE where ROOM_SCHEDULE_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_SCHEDULE where ROOM_SCHEDULE_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_SCHEDULE set ROOM_CATEGORY_ID=?, ROOM_SCHEDULE_DATE=?, ROOM_AMOUNT=?, ROOM_RSV_BOOKED=? where ROOM_SCHEDULE_ID = ?";


	@Override
	public void insert(RsVO rsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rsVO.getRoomCategoryId());
			pstmt.setTimestamp(2, rsVO.getRoomScheduleDate());
			pstmt.setInt(3, rsVO.getRoomAmount());
			pstmt.setInt(4, rsVO.getRoomRsvBooked());

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
	public void update(RsVO rsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rsVO.getRoomCategoryId());
			pstmt.setTimestamp(2, rsVO.getRoomScheduleDate());
			pstmt.setInt(3, rsVO.getRoomAmount());
			pstmt.setInt(4, rsVO.getRoomRsvBooked());
			pstmt.setInt(5, rsVO.getRoomScheduleId());

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
	public void delete(Integer roomScheduleId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomScheduleId);

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
	public RsVO findByPrimaryKey(Integer roomScheduleId) {
		RsVO rsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomScheduleId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rsVO = new RsVO();
				rsVO.setRoomScheduleId(rs.getInt("ROOM_SCHEDULE_ID"));
				rsVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rsVO.setRoomScheduleDate(rs.getTimestamp("ROOM_SCHEDULE_DATE"));
				rsVO.setRoomAmount(rs.getInt("ROOM_AMOUNT"));
				rsVO.setRoomRsvBooked(rs.getInt("ROOM_RSV_BOOKED"));

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
		return rsVO;
	}

	@Override
	public List<RsVO> getAll() {
		List<RsVO> list = new ArrayList<RsVO>();
		RsVO rsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rsVO = new RsVO();
				rsVO.setRoomScheduleId(rs.getInt("ROOM_SCHEDULE_ID"));
				rsVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rsVO.setRoomScheduleDate(rs.getTimestamp("ROOM_SCHEDULE_DATE"));
				rsVO.setRoomAmount(rs.getInt("ROOM_AMOUNT"));
				rsVO.setRoomRsvBooked(rs.getInt("ROOM_RSV_BOOKED"));

				list.add(rsVO);
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
