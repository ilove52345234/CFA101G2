package com.actpart.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*目前用JDBC連線MySQL, JNDI暫時不用*/

public class ActPartDAO implements ActPartDAOInterface {
	

	/*JNDI got some problem, use JDBC first.*/
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

//  local MySQL server in Development
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://127.0.0.1:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String passwd = "123456";

	JDBCUtils jdbcUtils = new JDBCUtils();



	//SQL need to be overwritten;
	private static final String INSERT_STMT = 
			"INSERT INTO ACT_PARTICIPATE (ACT_ID, MEM_ID, ACT_APPLY_DATE, FEE_CONFIRM, ACT_STAR, ACT_COMMENT_DATE, ACT_COMMENT_TEXT) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ACT_ID, MEM_ID, ACT_APPLY_DATE,FEE_CONFIRM,ACT_STAR,ACT_COMMENT_DATE,ACT_COMMENT_TEXT FROM ACT_PARTICIPATE order by (ACT_ID=?) and (MEM_ID=?)";
	private static final String GET_ONE_STMT = 
		"SELECT ACT_ID, MEM_ID, ACT_APPLY_DATE,FEE_CONFIRM,ACT_STAR,ACT_COMMENT_DATE,ACT_COMMENT_TEXT FROM ACT_PARTICIPATE where (ACT_ID=?) and (MEM_ID=?)";
	private static final String DELETE = 
		"DELETE FROM ACT_PARTICIPATE where (ACT_ID=?) and (MEM_ID=?)";
	private static final String UPDATE = 
		"UPDATE ACT_PARTICIPATE set  ACT_APPLY_DATE=?, FEE_CONFIRM=?, ACT_STAR=?, ACT_COMMENT_DATE=?, ACT_COMMENT_TEXT=? where (ACT_ID=?) and (MEM_ID=?) ";
	
	
	private static final String GET_ALLBYACTID_STMT = 
			"SELECT ACT_ID, MEM_ID, ACT_APPLY_DATE,FEE_CONFIRM,ACT_STAR,ACT_COMMENT_DATE,ACT_COMMENT_TEXT FROM ACT_PARTICIPATE where ACT_ID=? order by MEM_ID";
	
//	ACT_ID
//	MEM_ID
	//above is pkfk
	
	
//	ACT_APPLY_DATE
//	FEE_CONFIRM
//	ACT_STAR
//	ACT_COMMENT_DATE
//	ACT_COMMENT_TEXT
//	

	

	@Override
	public void insert(ActPartVO actPartVO) {


		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();

//			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actPartVO.getActId());
			pstmt.setInt(2, actPartVO.getMemId());
			pstmt.setTimestamp(3, actPartVO.getActApplyDate());
			pstmt.setInt(4, actPartVO.getFeeConfirm());
			pstmt.setInt(5, actPartVO.getActStar());
			pstmt.setTimestamp(6, actPartVO.getActCommentDate());
			pstmt.setString(7, actPartVO.getActCommentText());
			
			pstmt.executeUpdate();
			
			
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while inserting. "
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

	@Override
	public void update(ActPartVO actPartVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {

			con = jdbcUtils.getConnection();

//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setTimestamp(1, actPartVO.getActApplyDate());
			pstmt.setInt(2, actPartVO.getFeeConfirm());
			pstmt.setInt(3, actPartVO.getActStar());
			pstmt.setTimestamp(4, actPartVO.getActCommentDate());
			pstmt.setString(5, actPartVO.getActCommentText());
			pstmt.setInt(6, actPartVO.getActId());
			pstmt.setInt(7, actPartVO.getMemId());
			
			pstmt.executeUpdate();			
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while updating."
					+ se.getMessage());
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
	public void delete(Integer actId, Integer memId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;		
		

		try {

			con = jdbcUtils.getConnection();

			//con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actId);
			pstmt.setInt(2, memId);

			pstmt.executeUpdate();
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while deleting. "
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


	@Override
	public ActPartVO findByPrimaryKey(Integer actId, Integer memId) {

		ActPartVO actPartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = jdbcUtils.getConnection();

//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actId);
			pstmt.setInt(2, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
//				Columns:
//					ACT_ID
//					MEM_ID
//					ACT_APPLY_DATE
//					FEE_CONFIRM
//					ACT_STAR
//					ACT_COMMENT_DATE
//					ACT_COMMENT_TEXT
				actPartVO = new ActPartVO();
				actPartVO.setActId(rs.getInt("ACT_ID"));
				actPartVO.setActId(rs.getInt("MEM_ID"));
				actPartVO.setActApplyDate(rs.getTimestamp("ACT_APPLY_DATE"));
				actPartVO.setFeeConfirm(rs.getInt("FEE_CONFIRM"));
				actPartVO.setActStar(rs.getInt("ACT_STAR"));
				actPartVO.setActCommentDate(rs.getTimestamp("ACT_COMMENT_DATE"));
				actPartVO.setActCommentText(rs.getString("ACT_COMMENT_TEXT"));
				
				
			}

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getOneById. "
					+ se.getMessage());
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
		return actPartVO;
	}

	@Override
	public List<ActPartVO> getAll() {
		

		List<ActPartVO> list = new ArrayList<ActPartVO>();
		ActPartVO actPartVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		
		try {

			con = jdbcUtils.getConnection();

//		con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
//			Columns:
//				ACT_ID
//				MEM_ID
//				ACT_APPLY_DATE
//				FEE_CONFIRM
//				ACT_STAR
//				ACT_COMMENT_DATE
//				ACT_COMMENT_TEXT
				actPartVO = new ActPartVO();
				actPartVO.setActId(rs.getInt("ACT_ID"));
				actPartVO.setActId(rs.getInt("MEM_ID"));
				actPartVO.setActApplyDate(rs.getTimestamp("ACT_APPLY_DATE"));
				actPartVO.setFeeConfirm(rs.getInt("FEE_CONFIRM"));
				actPartVO.setActStar(rs.getInt("ACT_STAR"));
				actPartVO.setActCommentDate(rs.getTimestamp("ACT_COMMENT_DATE"));
				actPartVO.setActCommentText(rs.getString("ACT_COMMENT_TEXT"));
				
				list.add(actPartVO);
			}

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getAll "
					+ se.getMessage());
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

	@Override
	public List<ActPartVO> getAllByActId(Integer actId) {
// "SELECT MEM_ID, ACT_APPLY_DATE,FEE_CONFIRM,ACT_STAR,ACT_COMMENT_DATE,ACT_COMMENT_TEXT FROM ACT_PARTICIPATE where ACT_ID=? order by MEM_ID=? ";
		
		List<ActPartVO> listByActId = new ArrayList<ActPartVO>();
		ActPartVO actPartVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		
		try {

			con = jdbcUtils.getConnection();


//		con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLBYACTID_STMT);
			
			pstmt.setInt(1, actId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
//			Columns:
//				ACT_ID
//				MEM_ID
//				ACT_APPLY_DATE
//				FEE_CONFIRM
//				ACT_STAR
//				ACT_COMMENT_DATE
//				ACT_COMMENT_TEXT
				
				actPartVO = new ActPartVO();
				actPartVO.setActId(rs.getInt("ACT_ID"));
				actPartVO.setMemId(rs.getInt("MEM_ID"));
				actPartVO.setActApplyDate(rs.getTimestamp("ACT_APPLY_DATE"));
				actPartVO.setFeeConfirm(rs.getInt("FEE_CONFIRM"));
				actPartVO.setActStar(rs.getInt("ACT_STAR"));
				actPartVO.setActCommentDate(rs.getTimestamp("ACT_COMMENT_DATE"));
				actPartVO.setActCommentText(rs.getString("ACT_COMMENT_TEXT"));
				
				listByActId.add(actPartVO);
			}

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getAllByActId "
					+ se.getMessage());
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
		return listByActId;
	}

}
