package com.rmpromotion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class RmpJDBCDAO implements RmpDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "A123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmpVO.getRoomCategoryId());
			pstmt.setInt(2, rmpVO.getPromotionPrice());
			pstmt.setTimestamp(3, rmpVO.getPromotionStartDate());
			pstmt.setTimestamp(4, rmpVO.getPromotionEndDate());
			pstmt.setString(5, rmpVO.getPromotionText());

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
	public void update(RmpVO rmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmpVO.getRoomCategoryId());
			pstmt.setInt(2, rmpVO.getPromotionPrice());
			pstmt.setTimestamp(3, rmpVO.getPromotionStartDate());
			pstmt.setTimestamp(4, rmpVO.getPromotionEndDate());
			pstmt.setString(5, rmpVO.getPromotionText());
			pstmt.setInt(6, rmpVO.getRoomPromotionId());

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
	public void delete(Integer roomPromotionId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomPromotionId);

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
	public RmpVO findByPrimaryKey(Integer roomPromotionId) {
		
		RmpVO rmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				
		RmpJDBCDAO dao = new RmpJDBCDAO();
		// 新增
		RmpVO rmpVO1 = new RmpVO();
		rmpVO1.setRoomCategoryId(1);
		rmpVO1.setPromotionPrice(1);
		rmpVO1.setPromotionStartDate(ts);
		rmpVO1.setPromotionEndDate(ts);
		rmpVO1.setPromotionText("abc");
		dao.insert(rmpVO1);

		// 修改
		RmpVO rmpVO2 = new RmpVO();
		rmpVO2.setRoomPromotionId(1);
		rmpVO2.setRoomCategoryId(1);
		rmpVO2.setPromotionPrice(1);
		rmpVO2.setPromotionStartDate(ts);
		rmpVO2.setPromotionEndDate(ts);
		rmpVO2.setPromotionText("abc");

		dao.update(rmpVO2);

		// 刪除
		dao.delete(7002);
		
//		Integer roomNo=1;

		// 查詢
		RmpVO rmpVO3 = dao.findByPrimaryKey(1);
      	if(rmpVO3==null) {
			throw new RuntimeException("PK:not found") ;
		}
		
		System.out.println(rmpVO3.getRoomPromotionId() + ",");
		System.out.println(rmpVO3.getRoomCategoryId() + ",");
		System.out.println(rmpVO3.getPromotionPrice() + ",");
		System.out.println(rmpVO3.getPromotionStartDate() + ",");
		System.out.println(rmpVO3.getPromotionEndDate() + ",");
		System.out.println(rmpVO3.getPromotionText() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<RmpVO> list = dao.getAll();
		for (RmpVO aRmp : list) {
			System.out.println(aRmp.getRoomPromotionId() + ",");
			System.out.println(aRmp.getRoomCategoryId() + ",");
			System.out.println(aRmp.getPromotionPrice() + ",");
			System.out.println(aRmp.getPromotionStartDate() + ",");
			System.out.println(aRmp.getPromotionEndDate() + ",");
			System.out.println(aRmp.getPromotionText() + ",");
			System.out.println("---------------------");

		}
	}
	      
	}


