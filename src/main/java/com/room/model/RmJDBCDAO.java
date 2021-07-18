package com.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RmJDBCDAO implements RmDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
"INSERT INTO ROOM (ROOM_CATEGORY_ID, ROOM_CHECK_STATUS, ROOM_SALE_STATUS, ROOM_INFORMATION) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
"SELECT ROOM_ID, ROOM_CATEGORY_ID, ROOM_CHECK_STATUS, ROOM_SALE_STATUS, ROOM_INFORMATION FROM ROOM order by ROOM_ID";
	private static final String GET_ONE_STMT = 
"SELECT ROOM_ID, ROOM_CATEGORY_ID, ROOM_CHECK_STATUS, ROOM_SALE_STATUS, ROOM_INFORMATION FROM ROOM where ROOM_ID = ?";
	private static final String DELETE = 
"DELETE FROM ROOM where ROOM_ID = ?";
	private static final String UPDATE =
"UPDATE ROOM set ROOM_CATEGORY_ID=?, ROOM_CHECK_STATUS=?, ROOM_SALE_STATUS=?, ROOM_INFORMATION=? where ROOM_ID = ?";
	
	@Override
	public void insert(RmVO rmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmVO.getRoomCategoryId());
			pstmt.setByte(2, rmVO.getRoomCheckStatus());
			pstmt.setByte(3, rmVO.getRoomSaleStatus());
			pstmt.setString(4, rmVO.getRoomInformation());
	
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void update(RmVO rmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, rmVO.getRoomCategoryId());
			pstmt.setByte(2, rmVO.getRoomCheckStatus());
			pstmt.setByte(3, rmVO.getRoomSaleStatus());
			pstmt.setString(4, rmVO.getRoomInformation());
			pstmt.setInt(5, rmVO.getRoomId());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void delete(Integer roomId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomId);

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public RmVO findByPrimaryKey(Integer roomId) {
		RmVO rmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmVO = new RmVO();
				rmVO.setRoomId(rs.getInt("ROOM_ID"));
				rmVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmVO.setRoomCheckStatus(rs.getByte("ROOM_CHECK_STATUS"));
				rmVO.setRoomSaleStatus(rs.getByte("ROOM_SALE_STATUS"));
				rmVO.setRoomInformation(rs.getString("ROOM_INFORMATION"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
		return rmVO;
	}
	@Override
	public List<RmVO> getAll() {
		List<RmVO> list = new ArrayList<RmVO>();
		RmVO rmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rmVO = new RmVO();
				rmVO.setRoomId(rs.getInt("ROOM_ID"));
				rmVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmVO.setRoomCheckStatus(rs.getByte("ROOM_CHECK_STATUS"));
				rmVO.setRoomSaleStatus(rs.getByte("ROOM_SALE_STATUS"));
				rmVO.setRoomInformation(rs.getString("ROOM_INFORMATION"));

				list.add(rmVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
		RmJDBCDAO dao = new RmJDBCDAO();
		
		// 新增
		RmVO rmVO1 = new RmVO();
		rmVO1.setRoomCategoryId(7001);
		rmVO1.setRoomCheckStatus(new Integer(1).byteValue());
		rmVO1.setRoomSaleStatus(new Integer(1).byteValue());
		rmVO1.setRoomInformation("fssff");
		dao.insert(rmVO1);
		
		// 修改
		RmVO rmVO2 = new RmVO();
		rmVO2.setRoomId(1);
		rmVO2.setRoomCategoryId(7001);
		rmVO2.setRoomCheckStatus(new Integer(1).byteValue());
		rmVO2.setRoomSaleStatus(new Integer(1).byteValue());
		rmVO2.setRoomInformation("www");

		dao.update(rmVO2);
		
		// 刪除
		dao.delete(7007);
		
		
		// 查詢
		RmVO rmVO3 = dao.findByPrimaryKey(1);;
		System.out.println(rmVO3.getRoomId()+",");
		System.out.println(rmVO3.getRoomCategoryId()+",");
		System.out.println(rmVO3.getRoomCheckStatus()+",");
		System.out.println(rmVO3.getRoomSaleStatus()+",");
		System.out.println(rmVO3.getRoomInformation()+",");
		System.out.println("---------------------");
		
		// 查詢all
		List<RmVO> list = dao.getAll();
		for(RmVO aRm:list) {
			System.out.println(aRm.getRoomId()+",");
			System.out.println(aRm.getRoomCategoryId()+",");
			System.out.println(aRm.getRoomCheckStatus()+",");
			System.out.println(aRm.getRoomSaleStatus()+",");
			System.out.println(aRm.getRoomInformation()+",");
			System.out.println("---------------------");
			
		}
	}


	


}
