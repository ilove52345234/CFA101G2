package com.recentnews.model;

import java.sql.*;
import java.util.*;

public class RecentNewsJDBCDAO implements RecentNewsJDBCDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/GROUP2?serverTimezone=Asia/Taipei"; //35.221.136.103
	String userid = "root"; //CFA101G2
	String passwd = "sao124154"; //123456
	
	private static final String INSERT_STMT = 
		"INSERT INTO RECENT_NEWS (NEWS_CONTENT, NEWS_TIME, NEWS_STATUS) values(?,?,?)"; 
	private static final String GET_ALL_STMT = 
		"SELECT  NEWS_ID, NEWS_CONTENT, NEWS_TIME, NEWS_STATUS FROM RECENT_NEWS order by NEWS_ID DESC";
	private static final String GET_ONE_STMT = 
		"SELECT  NEWS_ID, NEWS_CONTENT, NEWS_TIME, NEWS_STATUS FROM RECENT_NEWS where NEWS_ID = ?";
	private static final String UPDATE = 
		"UPDATE RECENT_NEWS set NEWS_CONTENT=?, NEWS_STATUS=? where NEWS_ID = ?";
	private static final String DELETE = 
		"DELETE FROM RECENT_NEWS where NEWS_ID = ?";
	
	
	@Override //刪除
	public void delete(Integer newsId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, newsId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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

	@Override //新增
	public void insert(RecentNewsVO recentNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, recentNewsVO.getNewsContent());
			pstmt.setTimestamp(2, recentNewsVO.getNewsTime());
			pstmt.setInt(3, recentNewsVO.getNewsStatus());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	
	@Override //查全部
	public List<RecentNewsVO> getAll() {
		List<RecentNewsVO> list = new ArrayList<RecentNewsVO>();
		RecentNewsVO recentNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recentNewsVO = new RecentNewsVO();
				recentNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				recentNewsVO.setNewsContent(rs.getString("NEWS_CONTENT"));
				recentNewsVO.setNewsTime(rs.getTimestamp("NEWS_TIME"));
				recentNewsVO.setNewsStatus(rs.getInt("NEWS_STATUS"));

				list.add(recentNewsVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	
	@Override //查單一
	public RecentNewsVO findByPrimaryKey(Integer newsId) {

		RecentNewsVO recentNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, newsId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				recentNewsVO = new RecentNewsVO();
				recentNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				recentNewsVO.setNewsContent(rs.getString("NEWS_CONTENT"));
				recentNewsVO.setNewsTime(rs.getTimestamp("NEWS_TIME"));
				recentNewsVO.setNewsStatus(rs.getInt("NEWS_STATUS"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return recentNewsVO;
	}
	
	@Override //更新
	public void update(RecentNewsVO recentNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recentNewsVO.getNewsContent());
			pstmt.setInt(2, recentNewsVO.getNewsStatus());
			pstmt.setInt(3, recentNewsVO.getNewsId());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
}
