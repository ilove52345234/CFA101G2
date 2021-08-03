package com.actphoto.model;


import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/*目前用JDBC連線MySQL, JNDI暫時不用*/
public class ActPhotoDAO implements ActPhotoDAOInterface {
	
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
//	
//  local MySQL server in Development
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://127.0.0.1:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String passwd = "123456";


	JDBCUtils jdbcUtils = new JDBCUtils();


	//SQL need to be overwritten;
	private static final String INSERT_STMT = 
		"INSERT INTO ACT_PHOTO ( ACT_CATEGORY_ID, ACT_PHOTO_FILE) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ACT_PHOTO_ID, ACT_CATEGORY_ID,ACT_PHOTO_FILE FROM ACT_PHOTO order by ACT_PHOTO_ID ";
	private static final String GET_ONE_STMT = 
		"SELECT ACT_PHOTO_ID, ACT_CATEGORY_ID,ACT_PHOTO_FILE FROM ACT_PHOTO where ACT_PHOTO_ID = ?";
//	private static final String DELETE = 
//		"DELETE FROM ACT_TYPE where ACT_PHOTO_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACT_PHOTO set  ACT_CATEGORY_ID=?, ACT_PHOTO_FILE=? where ACT_PHOTO_ID=?";

	private static final String GET_ALLBYACTTYPEID_STMT = 
			"SELECT ACT_PHOTO_ID, ACT_CATEGORY_ID,ACT_PHOTO_FILE FROM ACT_PHOTO where ACT_CATEGORY_ID =? order by ACT_PHOTO_ID ";
	
	private static final String GET_ONEBYTYPEID_STMT = 
			"SELECT ACT_PHOTO_ID, ACT_CATEGORY_ID,ACT_PHOTO_FILE FROM ACT_PHOTO where ACT_CATEGORY_ID =? limit 1 ";
/*		
ACT_PHOTO_ID
	int(11) AI PK
ACT_CATEGORY_ID
	int(11)
ACT_PHOTO_FILE
	blob
*/

	
	@Override
	public void insert2(ActPhotoVO actPhotoVO, Connection con) {
		PreparedStatement pstmt = null;
		

		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actPhotoVO.getActCategoryId());
			pstmt.setBlob(2, actPhotoVO.getActPhotoFile());
			
			Statement stmt=	con.createStatement();
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();
			
			
			// Handle any SQL errors
					} catch (SQLException se) {
						if (con != null) {
							try {
								// 3●設定於當有exception發生時之catch區塊內
								System.err.print("Transaction is being ");
								System.err.println("rolled back-由-actPhoto");
								con.rollback();
							} catch (SQLException excep) {
								throw new RuntimeException("rollback error occured. "
										+ excep.getMessage());
							}
						}
						throw new RuntimeException("A database error occured. "
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
					}

				}
	
	
	
	
	@Override
	public void insert(ActPhotoVO actPhotoVO) {


		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = jdbcUtils.getConnection();

//			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actPhotoVO.getActCategoryId());
			pstmt.setBlob(2, actPhotoVO.getActPhotoFile());
			
			
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
	public void update(ActPhotoVO actPhotoVO) {
		

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();

//		    con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, actPhotoVO.getActCategoryId());
			pstmt.setBlob(2, actPhotoVO.getActPhotoFile());
			pstmt.setInt(3, actPhotoVO.getActPhotoId());
			
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
	public ActPhotoVO findByPrimaryKey(Integer actPhotoId) {

		ActPhotoVO actPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = jdbcUtils.getConnection();

//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actPhotoId);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				
				/* Column Names:
				ACT_PHOTO_ID
				ACT_CATEGORY_ID
				ACT_PHOTO_FILE
				*/
				
				actPhotoVO = new ActPhotoVO();
				actPhotoVO.setActPhotoId(rs.getInt("ACT_PHOTO_ID"));
				actPhotoVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actPhotoVO.setActPhotoFile(rs.getBlob("ACT_PHOTO_FILE"));
				
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
		return actPhotoVO;
	}

	@Override
	public List<ActPhotoVO> getAll() {

		List<ActPhotoVO> list = new ArrayList<ActPhotoVO>();
		ActPhotoVO actPhotoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = jdbcUtils.getConnection();


//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				

				actPhotoVO = new ActPhotoVO();
				actPhotoVO.setActPhotoId(rs.getInt("ACT_PHOTO_ID"));
				actPhotoVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actPhotoVO.setActPhotoFile(rs.getBlob("ACT_PHOTO_FILE"));				
				list.add(actPhotoVO);
				
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

	
//	GET_ALLBYACTTYPEID_STMT
	@Override
	public List<ActPhotoVO> getAllByActCategoryId(Integer actCategoryId) {
		
		List<ActPhotoVO> listByActTypeId = new ArrayList<ActPhotoVO>();
		ActPhotoVO actPhotoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = jdbcUtils.getConnection();

//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLBYACTTYPEID_STMT);
			
			pstmt.setInt(1, actCategoryId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				

				actPhotoVO = new ActPhotoVO();
				actPhotoVO.setActPhotoId(rs.getInt("ACT_PHOTO_ID"));
				actPhotoVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actPhotoVO.setActPhotoFile(rs.getBlob("ACT_PHOTO_FILE"));				
				listByActTypeId.add(actPhotoVO);
				
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
		return listByActTypeId;
	}




	@Override
	public ActPhotoVO getOneByActCategoryId(Integer actCategoryId) {
		
		ActPhotoVO actPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = jdbcUtils.getConnection();

//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEBYTYPEID_STMT);

			pstmt.setInt(1, actCategoryId);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				
				/* Column Names:
				ACT_PHOTO_ID
				ACT_CATEGORY_ID
				ACT_PHOTO_FILE
				*/
				
				actPhotoVO = new ActPhotoVO();
				actPhotoVO.setActPhotoId(rs.getInt("ACT_PHOTO_ID"));
				actPhotoVO.setActCategoryId(rs.getInt("ACT_CATEGORY_ID"));
				actPhotoVO.setActPhotoFile(rs.getBlob("ACT_PHOTO_FILE"));
				
			}
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while getOneByTypeId. "
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
		return actPhotoVO;
	}
}

	
	