package com.rmtypetracklist.model;

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

public class RmttlDAO implements RmttlDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ROOM_TYPE_TRACK_LIST (ROOM_CATEGORY_ID, MEM_ID) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_CATEGORY_ID, MEM_ID FROM ROOM_TYPE_TRACK_LIST order by ROOM_CATEGORY_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_CATEGORY_ID, MEM_ID FROM ROOM_TYPE_TRACK_LIST where ROOM_CATEGORY_ID = ? and MEM_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_TYPE_TRACK_LIST where ROOM_CATEGORY_ID = ? and MEM_ID= ?";
	private static final String UPDATE = "UPDATE ROOM_TYPE_TRACK_LIST set ROOM_CATEGORY_ID=?, MEM_ID=? where ROOM_CATEGORY_ID = ? and MEM_ID= ?";

	@Override
	public void insert(RmttlVO rmttlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmttlVO.getRoomCategoryId());
			pstmt.setInt(2, rmttlVO.getMemId());


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
	public void update(RmttlVO rmttlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmttlVO.getRoomCategoryId());
			pstmt.setInt(2, rmttlVO.getMemId());
			pstmt.setInt(3, rmttlVO.getRoomCategoryId());
			pstmt.setInt(4, rmttlVO.getMemId());

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
	public void delete(Integer roomCategoryId, Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomCategoryId);
			pstmt.setInt(2, memId);

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
	public RmttlVO findByPrimaryKey(Integer roomCategoryId, Integer memId) {
		RmttlVO rmttlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomCategoryId);
			pstmt.setInt(2, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmttlVO = new RmttlVO();
				rmttlVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmttlVO.setMemId(rs.getInt("MEM_ID"));
				
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
		return rmttlVO;
	}

	@Override
	public List<RmttlVO> getAll() {
		List<RmttlVO> list = new ArrayList<RmttlVO>();
		RmttlVO rmttlVO1 = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rmttlVO1 = new RmttlVO();
				rmttlVO1.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmttlVO1.setMemId(rs.getInt("MEM_ID"));
				
				list.add(rmttlVO1);
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
