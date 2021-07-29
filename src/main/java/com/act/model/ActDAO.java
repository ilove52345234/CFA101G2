package com.act.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActDAO implements ActDAOInterface {

	/* JNDI got some problem, use JDBC first. */
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2cfa101");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

//	JDBC ver.

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "CFA101G2";
//	String passwd = "123456";
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cfa101g2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";

	// SQL need to be overwritten;
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY (ACT_CATEGORY_ID,ACT_PROMOTION_ID,ACT_DESCRIPTION,ACT_START,ACT_END,ACT_STATUS,ACT_FEE, APPLICANTS, PART_START, PART_END, ACT_MAX_PART, ACT_ACT_MIN_PART) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ACT_ID, ACT_CATEGORY_ID,ACT_PROMOTION_ID,ACT_DESCRIPTION,ACT_START,ACT_END,ACT_STATUS,ACT_FEE, APPLICANTS, PART_START, PART_END, ACT_MAX_PART, ACT_ACT_MIN_PART   FROM ACTIVITY order by ACT_ID";
	private static final String GET_ONE_STMT = "SELECT ACT_ID, ACT_CATEGORY_ID,ACT_PROMOTION_ID,ACT_DESCRIPTION,ACT_START,ACT_END,ACT_STATUS,ACT_FEE, APPLICANTS, PART_START, PART_END, ACT_MAX_PART, ACT_ACT_MIN_PART FROM ACTIVITY where ACT_ID = ?";
	private static final String UPDATE = "UPDATE ACTIVITY set  ACT_CATEGORY_ID=?, ACT_PROMOTION_ID=?, ACT_DESCRIPTION=?, ACT_START=?, ACT_END=?, ACT_STATUS=?, ACT_FEE=?, APPLICANTS=?, PART_START=?, PART_END=?, ACT_MAX_PART=?, ACT_ACT_MIN_PART=?  where ACT_ID=?";

	@Override
	public void insert(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, actVO.getActCategoryId());
			pstmt.setInt(2, actVO.getActPromotionId());
			pstmt.setString(3, actVO.getActDescription());
			pstmt.setTimestamp(4, actVO.getActStart());
			pstmt.setTimestamp(5, actVO.getActEnd());
			pstmt.setString(6, actVO.getActStatus());
			pstmt.setInt(7, actVO.getActFee());
			pstmt.setInt(8, actVO.getApplicants());
			pstmt.setTimestamp(9, actVO.getPartStart());
			pstmt.setTimestamp(10, actVO.getPartEnd());
			pstmt.setInt(11, actVO.getActMaxPart());
			pstmt.setInt(12, actVO.getActMinPart());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while inserting. " + se.getMessage());

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
	public void update(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, actVO.getActId());
			pstmt.setInt(2, actVO.getActCategoryId());
			pstmt.setInt(3, actVO.getActPromotionId());
			pstmt.setString(4, actVO.getActDescription());
			pstmt.setTimestamp(5, actVO.getActStart());
			pstmt.setTimestamp(6, actVO.getActEnd());
			pstmt.setString(7, actVO.getActStatus());
			pstmt.setInt(8, actVO.getActFee());
			pstmt.setInt(9, actVO.getApplicants());
			pstmt.setTimestamp(10, actVO.getPartStart());
			pstmt.setTimestamp(11, actVO.getPartEnd());
			pstmt.setInt(12, actVO.getActMaxPart());
			pstmt.setInt(13, actVO.getActMinPart());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public ActVO findByPrimaryKey(Integer actId) {

		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				actVO = new ActVO();
				actVO.setActId(rs.getInt("ACT_ID"));
				actVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actVO.setActPromotionId(rs.getInt("ACT_PROMOTION_ID"));
				actVO.setActStart(rs.getTimestamp("ACT_START"));
				actVO.setActEnd(rs.getTimestamp("ACT_END"));
				actVO.setActStatus(rs.getString("ACT_STATUS"));
				actVO.setActFee(rs.getInt("ACT_FEE"));
				actVO.setApplicants(rs.getInt("APPLICANTS"));
				actVO.setPartStart(rs.getTimestamp("PART_START"));
				actVO.setPartEnd(rs.getTimestamp("PART_END"));
				actVO.setActMaxPart(rs.getInt("ACT_MAX_PART"));
				actVO.setActMinPart(rs.getInt("ACT_ACT_MIN_PART"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getOneById. " + se.getMessage());
			// Clean up JDBC resources
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
		return actVO;
	}

	@Override
	public List<ActVO> getAll() {

		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				actVO = new ActVO();
				actVO.setActId(rs.getInt("ACT_ID"));
				actVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actVO.setActPromotionId(rs.getInt("ACT_PROMOTION_ID"));
				actVO.setActDescription(rs.getString("ACT_DESCRIPTION"));
				actVO.setActStart(rs.getTimestamp("ACT_START"));
				actVO.setActEnd(rs.getTimestamp("ACT_END"));
				actVO.setActStatus(rs.getString("ACT_STATUS"));
				actVO.setActFee(rs.getInt("ACT_FEE"));
				actVO.setApplicants(rs.getInt("APPLICANTS"));
				actVO.setPartStart(rs.getTimestamp("PART_START"));
				actVO.setPartEnd(rs.getTimestamp("PART_END"));
				actVO.setActMaxPart(rs.getInt("ACT_MAX_PART"));
				actVO.setActMinPart(rs.getInt("ACT_ACT_MIN_PART"));

				list.add(actVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getAll " + se.getMessage());
			// Clean up JDBC resources
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
