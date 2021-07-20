package com.rschedule.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.print.attribute.standard.DateTimeAtCompleted;



public class RsJDBCDAO implements RsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ROOM_SCHEDULE (ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED,ROOM_CHECK_OUT,ROOM_CHECK_IN) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_SCHEDULE_ID, ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED,ROOM_CHECK_OUT,ROOM_CHECK_IN FROM ROOM_SCHEDULE order by ROOM_SCHEDULE_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_SCHEDULE_ID, ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED,ROOM_CHECK_OUT,ROOM_CHECK_IN FROM ROOM_SCHEDULE where ROOM_SCHEDULE_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_SCHEDULE where ROOM_SCHEDULE_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_SCHEDULE set ROOM_CATEGORY_ID=?, ROOM_SCHEDULE_DATE=?, ROOM_AMOUNT=?, ROOM_RSV_BOOKED=? ROOM_CHECK_OUT =?,ROOM_CHECK_IN =? where ROOM_SCHEDULE_ID = ?";

	@Override
	public void insert(RsVO rsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rsVO.getRoomCategoryId());
			pstmt.setDate(2, rsVO.getRoomScheduleDate());
			pstmt.setInt(3, rsVO.getRoomAmount());
			pstmt.setInt(4, rsVO.getRoomRsvBooked());
			pstmt.setInt(5, rsVO.getRoomCheckOut());
			pstmt.setInt(6, rsVO.getRoomCheckOut());

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
	public void update(RsVO rsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rsVO.getRoomCategoryId());
			pstmt.setDate(2, rsVO.getRoomScheduleDate());
			pstmt.setInt(3, rsVO.getRoomAmount());
			pstmt.setInt(4, rsVO.getRoomRsvBooked());
			pstmt.setInt(5, rsVO.getRoomScheduleId());
			pstmt.setInt(6, rsVO.getRoomCheckOut());
			pstmt.setInt(7, rsVO.getRoomCheckIn());

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
	public void delete(Integer roomScheduleId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomScheduleId);

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
	public RsVO findByPrimaryKey(Integer roomScheduleId) {
		RsVO rsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomScheduleId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rsVO = new RsVO();
				rsVO.setRoomScheduleId(rs.getInt("ROOM_SCHEDULE_ID"));
				rsVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rsVO.setRoomScheduleDate(rs.getDate("ROOM_SCHEDULE_DATE"));
				rsVO.setRoomAmount(rs.getInt("ROOM_AMOUNT"));
				rsVO.setRoomRsvBooked(rs.getInt("ROOM_RSV_BOOKED"));
				rsVO.setRoomCheckOut(rs.getInt("ROOM_CHECK_OUT"));
				rsVO.setRoomCheckIn(rs.getInt("ROOM_CHECK_IN"));

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rsVO = new RsVO();
				rsVO.setRoomScheduleId(rs.getInt("ROOM_SCHEDULE_ID"));
				rsVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rsVO.setRoomScheduleDate(rs.getDate("ROOM_SCHEDULE_DATE"));
				rsVO.setRoomAmount(rs.getInt("ROOM_AMOUNT"));
				rsVO.setRoomRsvBooked(rs.getInt("ROOM_RSV_BOOKED"));
				rsVO.setRoomCheckOut(rs.getInt("ROOM_CHECK_OUT"));
				rsVO.setRoomCheckIn(rs.getInt("ROOM_CHECK_IN"));

				list.add(rsVO);
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



}