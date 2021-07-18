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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rsVO.getRoomCategoryId());
			pstmt.setTimestamp(2, rsVO.getRoomScheduleDate());
			pstmt.setInt(3, rsVO.getRoomAmount());
			pstmt.setInt(4, rsVO.getRoomRsvBooked());

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
			pstmt.setTimestamp(2, rsVO.getRoomScheduleDate());
			pstmt.setInt(3, rsVO.getRoomAmount());
			pstmt.setInt(4, rsVO.getRoomRsvBooked());
			pstmt.setInt(5, rsVO.getRoomScheduleId());

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
				rsVO.setRoomScheduleDate(rs.getTimestamp("ROOM_SCHEDULE_DATE"));
				rsVO.setRoomAmount(rs.getInt("ROOM_AMOUNT"));
				rsVO.setRoomRsvBooked(rs.getInt("ROOM_RSV_BOOKED"));

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
				rsVO.setRoomScheduleDate(rs.getTimestamp("ROOM_SCHEDULE_DATE"));
				rsVO.setRoomAmount(rs.getInt("ROOM_AMOUNT"));
				rsVO.setRoomRsvBooked(rs.getInt("ROOM_RSV_BOOKED"));

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

	public static void main(String[] args) {

		//現在時間
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println("現在時間:"+ts);

//		//自定義時間
//		String tsStr = "2011-05-09 11:49:45";
//		try {
//			ts = Timestamp.valueOf(tsStr);
//			System.out.println("自定義時間:"+ts);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//=======================================

		RsJDBCDAO dao = new RsJDBCDAO();
		// 新增
		RsVO rsVO1 = new RsVO();
		rsVO1.setRoomCategoryId(1);
		rsVO1.setRoomScheduleDate(ts);
		rsVO1.setRoomAmount(1);
		rsVO1.setRoomRsvBooked(1);
		dao.insert(rsVO1);

		// 修改
		RsVO rsVO2 = new RsVO();
		rsVO2.setRoomScheduleId(1);
		rsVO2.setRoomCategoryId(1);
		rsVO2.setRoomScheduleDate(ts);
		rsVO2.setRoomAmount(1);
		rsVO2.setRoomRsvBooked(1);

		dao.update(rsVO2);

		// 刪除
		dao.delete(7002);
		
//		Integer roomNo=1;

		// 查詢
		RsVO rsVO3 = dao.findByPrimaryKey(1);
      	if(rsVO3==null) {
			throw new RuntimeException("PK:not found") ;
		}
		
		System.out.println(rsVO3.getRoomScheduleId() + ",");
		System.out.println(rsVO3.getRoomCategoryId() + ",");
		System.out.println(rsVO3.getRoomScheduleDate() + ",");
		System.out.println(rsVO3.getRoomAmount() + ",");
		System.out.println(rsVO3.getRoomRsvBooked() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<RsVO> list = dao.getAll();
		for (RsVO aRs : list) {
			System.out.println(aRs.getRoomScheduleId() + ",");
			System.out.println(aRs.getRoomCategoryId() + ",");
			System.out.println(aRs.getRoomScheduleDate() + ",");
			System.out.println(aRs.getRoomAmount() + ",");
			System.out.println(aRs.getRoomRsvBooked() + ",");
			System.out.println("---------------------");

		}
	}


}
