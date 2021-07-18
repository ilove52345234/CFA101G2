package com.rmpromotion.model;

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

public class RmpDAO implements RmpDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ROOM_PROMOTION (ROOM_CATEGORY_ID, PROMOTION_PRICE, PROMOTION_START_DATE, PROMOTION_END_DATE,PROMOTION_TEXT) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_PROMOTION_ID, ROOM_CATEGORY_ID, PROMOTION_PRICE, PROMOTION_START_DATE, PROMOTION_END_DATE, PROMOTION_TEXT FROM ROOM_PROMOTION order by ROOM_PROMOTION_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_PROMOTION_ID, ROOM_CATEGORY_ID, PROMOTION_PRICE, PROMOTION_START_DATE, PROMOTION_END_DATE, PROMOTION_TEXT FROM ROOM_PROMOTION where ROOM_PROMOTION_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_PROMOTION where ROOM_PROMOTION_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_PROMOTION set ROOM_CATEGORY_ID=?, PROMOTION_PRICE=?, PROMOTION_START_DATE=?, PROMOTION_END_DATE=?, PROMOTION_TEXT=? where ROOM_PROMOTION_ID = ?";
	
	@Override
	public void insert(RmpVO rmpVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmpVO.getRoomCategoryId());
			pstmt.setInt(2, rmpVO.getPromotionPrice());
			pstmt.setTimestamp(3, rmpVO.getPromotionStartDate());
			pstmt.setTimestamp(4, rmpVO.getPromotionEndDate());
			pstmt.setString(5, rmpVO.getPromotionText());

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
	public void update(RmpVO rmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmpVO.getRoomCategoryId());
			pstmt.setInt(2, rmpVO.getPromotionPrice());
			pstmt.setTimestamp(3, rmpVO.getPromotionStartDate());
			pstmt.setTimestamp(4, rmpVO.getPromotionEndDate());
			pstmt.setString(5, rmpVO.getPromotionText());
			pstmt.setInt(6, rmpVO.getRoomPromotionId());

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
	public void delete(Integer roomPromotionId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomPromotionId);

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
	public RmpVO findByPrimaryKey(Integer roomPromotionId) {
		
		RmpVO rmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomPromotionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmpVO = new RmpVO();
				rmpVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
				rmpVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmpVO.setPromotionPrice(rs.getInt("PROMOTION_PRICE"));
				rmpVO.setPromotionStartDate(rs.getTimestamp("PROMOTION_START_DATE"));
				rmpVO.setPromotionEndDate(rs.getTimestamp("PROMOTION_END_DATE"));
				rmpVO.setPromotionText(rs.getString("PROMOTION_TEXT"));

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
		return rmpVO;
	}

	@Override
	public List<RmpVO> getAll() {
		List<RmpVO> list = new ArrayList<RmpVO>();
		RmpVO rmpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmpVO = new RmpVO();
				rmpVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
				rmpVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmpVO.setPromotionPrice(rs.getInt("PROMOTION_PRICE"));
				rmpVO.setPromotionStartDate(rs.getTimestamp("PROMOTION_START_DATE"));
				rmpVO.setPromotionEndDate(rs.getTimestamp("PROMOTION_END_DATE"));
				rmpVO.setPromotionText(rs.getString("PROMOTION_TEXT"));
				
				list.add(rmpVO);

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