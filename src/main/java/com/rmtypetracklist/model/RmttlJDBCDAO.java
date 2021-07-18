package com.rmtypetracklist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//會員還沒有資料

public class RmttlJDBCDAO implements RmttlDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmttlVO.getRoomCategoryId());
			pstmt.setInt(2, rmttlVO.getMemId());


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
	public void update(RmttlVO rmttlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmttlVO.getRoomCategoryId());
			pstmt.setInt(2, rmttlVO.getMemId());
			pstmt.setInt(3, rmttlVO.getRoomCategoryId());
			pstmt.setInt(4, rmttlVO.getMemId());

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
	public void delete(Integer roomCategoryId, Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomCategoryId);
			pstmt.setInt(2, memId);

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
	public RmttlVO findByPrimaryKey(Integer roomCategoryId, Integer memId) {
		RmttlVO rmttlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomCategoryId);
			pstmt.setInt(2, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmttlVO = new RmttlVO();
				rmttlVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmttlVO.setMemId(rs.getInt("MEM_ID"));
				
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rmttlVO1 = new RmttlVO();
				rmttlVO1.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmttlVO1.setMemId(rs.getInt("MEM_ID"));
				
				list.add(rmttlVO1);
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
		RmttlJDBCDAO dao = new RmttlJDBCDAO();
		// 新增
		RmttlVO rmttlVO1 = new RmttlVO();
		rmttlVO1.setRoomCategoryId(2);
		rmttlVO1.setMemId(2);
		dao.insert(rmttlVO1);

		// 修改
		RmttlVO rmttlVO2 = new RmttlVO();
		rmttlVO2.setRoomCategoryId(1);
		rmttlVO2.setMemId(1);

		dao.update(rmttlVO2);

		// 刪除
	//	dao.delete(7001,1);
		
//		Integer roomNo=1;

		// 查詢
		RmttlVO rmttlVO3 = dao.findByPrimaryKey(1,1);
      	if(rmttlVO3==null) {
			throw new RuntimeException("PK:not found") ;
		}
		
		System.out.println(rmttlVO3.getRoomCategoryId() + ",");
		System.out.println(rmttlVO3.getMemId() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<RmttlVO> list = dao.getAll();
		for (RmttlVO aRmttl : list) {
			System.out.println(aRmttl.getRoomCategoryId() + ",");
			System.out.println(aRmttl.getMemId() + ",");
			System.out.println("---------------------");

		}
	}
		
	}


