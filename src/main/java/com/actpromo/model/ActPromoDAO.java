package com.actpromo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acttype.model.ActTypeVO;
import com.utils.JDBCUtils;


/*目前用JDBC連線MySQL, JNDI暫時不用*/

public class ActPromoDAO implements ActPromoDAOInterface {


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
			"INSERT INTO ACT_PROMOTION (ACT_PROMOTION_NAME, ACT_PROMOTION_DISCOUNT) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ACT_PROMOTION_ID, ACT_PROMOTION_NAME,ACT_PROMOTION_DISCOUNT FROM ACT_PROMOTION order by ACT_PROMOTION_ID";
	private static final String GET_ONE_STMT = 
		"SELECT ACT_PROMOTION_ID, ACT_PROMOTION_NAME,ACT_PROMOTION_DISCOUNT FROM ACT_PROMOTION where ACT_PROMOTION_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ACT_PROMOTION where ACT_PROMOTION_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACT_PROMOTION set  ACT_PROMOTION_NAME=?, ACT_PROMOTION_DISCOUNT=? where ACT_PROMOTION_ID=?";

	
	/*
	 * column names:
	 * 
ACT_PROMOTION_ID
int(11) AI PK
ACT_PROMOTION_NAME
varchar(45)
ACT_PROMOTION_DISCOUNT
int(11)
	 */

	
	
	
	@Override
	public void insert(ActPromoVO actPromoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();

//		con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, actPromoVO.getActPromotionName());
			pstmt.setDouble(2, actPromoVO.getActPromotionDiscount());
			
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
	public void update(ActPromoVO actPromoVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = jdbcUtils.getConnection();

//		con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, actPromoVO.getActPromotionName());
			pstmt.setDouble(2, actPromoVO.getActPromotionDiscount());
			pstmt.setInt(3, actPromoVO.getActPromotionId());
			
			pstmt.executeUpdate();
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while updating. "
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
	public void delete(Integer actPromotionId) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {
			con = jdbcUtils.getConnection();

			//con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actPromotionId);

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
	public ActPromoVO findByPrimaryKey(Integer actPromotionId) {
		
		ActPromoVO actPromoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = jdbcUtils.getConnection();

//		con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actPromotionId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				/*
				ACT_PROMOTION_ID
				ACT_PROMOTION_NAME
				ACT_PROMOTION_DISCOUNT	
				*/
				
				actPromoVO = new ActPromoVO();
				actPromoVO.setActPromotionId(rs.getInt("ACT_PROMOTION_ID"));
				actPromoVO.setActPromotionName(rs.getString("ACT_PROMOTION_NAME"));
				actPromoVO.setActPromotionDiscount(rs.getDouble("ACT_PROMOTION_DISCOUNT"));
				
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
		return actPromoVO;
	}

	@Override
	public List<ActPromoVO> getAll() {

		List<ActPromoVO> list = new ArrayList<ActPromoVO>();
		ActPromoVO actPromoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = jdbcUtils.getConnection();

//		con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				actPromoVO = new ActPromoVO();
				actPromoVO.setActPromotionId(rs.getInt("ACT_PROMOTION_ID"));
				actPromoVO.setActPromotionName(rs.getString("ACT_PROMOTION_NAME"));
				actPromoVO.setActPromotionDiscount(rs.getDouble("ACT_PROMOTION_DISCOUNT"));
				
				list.add(actPromoVO);
			
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

}
