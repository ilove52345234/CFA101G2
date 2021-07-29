package com.acttype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*目前用JDBC連線MySQL, JNDI暫時不用*/

public class ActTypeDAO implements ActTypeDAOInterface {
	
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
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";
	
	
	//SQL need to be overwritten;
	private static final String INSERT_STMT = 
			"INSERT INTO ACT_TYPE ( ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_MAX_PART,ACT_MIN_PART,ACT_FEE,ACT_NUMBER,ACT_TOTAL_SCORE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ACT_CATEGORY_ID, ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_MAX_PART,ACT_MIN_PART,ACT_FEE,ACT_NUMBER,ACT_TOTAL_SCORE FROM ACT_TYPE order by ACT_CATEGORY_ID";
	private static final String GET_ONE_STMT = 
		"SELECT ACT_CATEGORY_ID, ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_MAX_PART,ACT_MIN_PART,ACT_FEE,ACT_NUMBER,ACT_TOTAL_SCORE FROM ACT_TYPE where ACT_CATEGORY_ID = ?";
	private static final String GET_ONE_STMT2 = 
			"SELECT ACT_CATEGORY_ID, ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_FEE FROM ACT_TYPE where ACT_CATEGORY_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ACT_TYPE where ACT_CATEGORY_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACT_TYPE set  ACT_CATEGORY_NAME=?, ACT_CATEGORY_DESC=?, ACT_MAX_PART=?, ACT_MIN_PART=?, ACT_FEE=?, ACT_NUMBER=?, ACT_TOTAL_SCORE=? where ACT_CATEGORY_ID=?";

	
	
	
	@Override
	public void insert(ActTypeVO actTypeVO) {
		

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, actTypeVO.getActCategoryName());
			pstmt.setString(2, actTypeVO.getActCategoryDesc());
			pstmt.setInt(3, actTypeVO.getActMaxPart());
			pstmt.setInt(4, actTypeVO.getActMinPart());
			pstmt.setInt(5, actTypeVO.getActFee());
			pstmt.setInt(6, actTypeVO.getActNumber());
			pstmt.setInt(7, actTypeVO.getActTotalScore());
			
			pstmt.executeUpdate();
			
			
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void update(ActTypeVO actTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,actTypeVO.getActCategoryName());
			pstmt.setString(2, actTypeVO.getActCategoryDesc());
			pstmt.setInt(3, actTypeVO.getActMaxPart());
			pstmt.setInt(4, actTypeVO.getActMinPart());
			pstmt.setInt(5, actTypeVO.getActFee());
			pstmt.setInt(6, actTypeVO.getActNumber());
			pstmt.setInt(7, actTypeVO.getActTotalScore());
			pstmt.setInt(8, actTypeVO.getActCategoryId());
			
pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void delete(Integer actCategoryId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actCategoryId);

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public ActTypeVO findByPrimaryKey1(Integer actCategoryId) {
		
		ActTypeVO actTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setInt(1, actCategoryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				/* Column Names:
				   ACT_CATEGORY_ID  
				   ACT_CATEGORY_NAME 
				   ACT_CATEGORY_DESC  
				   ACT_MAX_PART  
				   ACT_MIN_PART  
				   ACT_FEE  
				   ACT_NUMBER  
				   ACT_TOTAL_SCORE  	
				*/	
				
				actTypeVO = new ActTypeVO();
				actTypeVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actTypeVO.setActCategoryName(rs.getString("ACT_CATEGORY_NAME"));
				actTypeVO.setActCategoryDesc(rs.getString("ACT_CATEGORY_DESC"));
				actTypeVO.setActFee(rs.getInt("ACT_FEE"));		
			}

			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
		return actTypeVO;
	}
	@Override
	public ActTypeVO findByPrimaryKey(Integer actCategoryId) {
		
		ActTypeVO actTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, actCategoryId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				/* Column Names:
				   ACT_CATEGORY_ID  
				   ACT_CATEGORY_NAME 
				   ACT_CATEGORY_DESC  
				   ACT_MAX_PART  
				   ACT_MIN_PART  
				   ACT_FEE  
				   ACT_NUMBER  
				   ACT_TOTAL_SCORE  	
				 */	
				
				actTypeVO = new ActTypeVO();
				actTypeVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actTypeVO.setActCategoryName(rs.getString("ACT_CATEGORY_NAME"));
				actTypeVO.setActCategoryDesc(rs.getString("ACT_CATEGORY_DESC"));
				actTypeVO.setActMaxPart(rs.getInt("ACT_MAX_PART"));
				actTypeVO.setActMinPart(rs.getInt("ACT_MIN_PART"));
				actTypeVO.setActFee(rs.getInt("ACT_FEE"));
				actTypeVO.setActNumber(rs.getInt("ACT_NUMBER"));
				actTypeVO.setActTotalScore(rs.getInt("ACT_TOTAL_SCORE"));
				
			}
			
			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
		return actTypeVO;
	}

	@Override
	public List<ActTypeVO> getAll() {
		
		List<ActTypeVO> list = new ArrayList<ActTypeVO>();
		ActTypeVO actTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
									
				actTypeVO = new ActTypeVO();
				actTypeVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actTypeVO.setActCategoryName(rs.getString("ACT_CATEGORY_NAME"));
				actTypeVO.setActCategoryDesc(rs.getString("ACT_CATEGORY_DESC"));
				actTypeVO.setActMaxPart(rs.getInt("ACT_MAX_PART"));
				actTypeVO.setActMinPart(rs.getInt("ACT_MIN_PART"));
				actTypeVO.setActFee(rs.getInt("ACT_FEE"));
				actTypeVO.setActNumber(rs.getInt("ACT_NUMBER"));
				actTypeVO.setActTotalScore(rs.getInt("ACT_TOTAL_SCORE"));
				
				list.add(actTypeVO);		
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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

}
