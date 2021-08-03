package com.act.model;

import com.act.CompositeQuery.jdbcUtil_CompositeQuery_Act;
import com.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class ActDAO implements ActDAOInterface{

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
		"INSERT INTO ACTIVITY (ACT_CATEGORY_ID,ACT_PROMOTION_ID,ACT_DESCRIPTION,ACT_START,ACT_END,ACT_STATUS,ACT_FEE, PART_START, PART_END, ACT_MAX_PART, ACT_ACT_MIN_PART, APPLICANTS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
	private static final String GET_ALL_STMT = 
		"SELECT ACT_ID, ACT_CATEGORY_ID,ACT_PROMOTION_ID,ACT_DESCRIPTION,ACT_START,ACT_END,ACT_STATUS,ACT_FEE, APPLICANTS, PART_START, PART_END, ACT_MAX_PART, ACT_ACT_MIN_PART FROM ACTIVITY order by ACT_ID";
	private static final String GET_ONE_STMT = 
		"SELECT ACT_ID, ACT_CATEGORY_ID,ACT_PROMOTION_ID,ACT_DESCRIPTION,ACT_START,ACT_END,ACT_STATUS,ACT_FEE, APPLICANTS, PART_START, PART_END, ACT_MAX_PART, ACT_ACT_MIN_PART FROM ACTIVITY where ACT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ACTIVITY where ACT_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACTIVITY set  ACT_DESCRIPTION=?, ACT_STATUS=? where ACT_ID=?";
	private static final String UPDATE_STATUS = 
			"UPDATE ACTIVITY set ACT_STATUS=? where ACT_ID=?";
	
	
	
	@Override
	public void updateStatus(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;		
		
		
		try {
			con = jdbcUtils.getConnection();

	//		con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
//				"UPDATE ACTIVITY set ACT_STATUS=? where ACT_ID=?";

			pstmt.setString(1, actVO.getActStatus());
			pstmt.setInt(2,actVO.getActId());

			pstmt.executeUpdate();
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while update actStatus. "
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
	public void insert(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actVO.getActCategoryId());
			pstmt.setInt(2, actVO.getActPromotionId());
			pstmt.setString(3,actVO.getActDescription());
			pstmt.setTimestamp(4, actVO.getActStart());
			pstmt.setTimestamp(5, actVO.getActEnd());
			pstmt.setString(6, actVO.getActStatus());
			pstmt.setInt(7, actVO.getActFee());
			pstmt.setTimestamp(8, actVO.getPartStart());
			pstmt.setTimestamp(9,actVO.getPartEnd());
			pstmt.setInt(10, actVO.getActMaxPart());
			pstmt.setInt(11, actVO.getActMinPart());
//			pstmt.setInt(12, actVO.getApplicants());

			pstmt.executeUpdate();
			
			
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace(System.err);

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
	public void update(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;		
		
		
		try {
			con = jdbcUtils.getConnection();

			//con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
//				"UPDATE ACTIVITY set ACT_DESCRIPTION=?, ACT_STATUS=? where ACT_ID=? ";
	
			
			pstmt.setString(1, actVO.getActDescription());
			pstmt.setString(2, actVO.getActStatus());
			pstmt.setInt(3, actVO.getActId());

			pstmt.executeUpdate();
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while update act. "
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
	public void delete(Integer actId) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {
			con = jdbcUtils.getConnection();
			//con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, actId);
			
			pstmt.executeUpdate();
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while delete. "
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
	public ActVO findByPrimaryKey(Integer actId) {
		
		ActVO actVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			con = jdbcUtils.getConnection();

//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, actId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

			/*  Column Names:
			 *   
				private Integer ACT_ID;  
				private Integer ACT_CATEGORY_ID;
				private Integer ACT_PROMOTION_ID;
				private String ACT_DESCRIPTION;
				private Date ACT_START;
				private Date ACT_END;
				private String ACT_STATUS;
				private Integer ACT_FEE;
				private Integer APPLICANTS;
				private Date PART_START;
				private Date PART_END;
				private Integer ACT_MAX_PART;
				private Integer ACT_ACT_MIN_PART;
			*/				
				
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
			con = jdbcUtils.getConnection();
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
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
	public List<ActVO> complexSearch(Map<String, String[]> map) {
		
		
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			con = jdbcUtils.getConnection();
			//ACTIVITY_<== TableName ACTIVITY 後面一定要空一格，才能串接SQL，避免SQL連在一起(防呆）
			String finalSQL = "select * from ACTIVITY "
			          + jdbcUtil_CompositeQuery_Act.get_WhereCondition(map)
			          + "order by ACT_ID";
			
			pstmt = con.prepareStatement(finalSQL);
			
			//注意：確保finalSQL成功取得了pstmt
			// finalSQL拿去Workbench測試
System.out.println("finalSQL(by DAO) = "+finalSQL);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
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
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while complexQuery."
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
	}//end of if 
				
	

	
}
