package com.acttype.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.actphoto.model.ActPhotoDAO;
import com.actphoto.model.ActPhotoService;
import com.actphoto.model.ActPhotoVO;
import com.utils.JDBCUtils;



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
	
//  local MySQL server in Development
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://127.0.0.1:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String passwd = "123456";

	JDBCUtils jdbcUtils = new JDBCUtils();



	//SQL need to be overwritten;
	private static final String INSERT_STMT = 
			"INSERT INTO ACT_TYPE ( ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_MAX_PART,ACT_MIN_PART,ACT_FEE,ACT_NUMBER,ACT_TOTAL_SCORE) VALUES (?, ?, ?, ?, ?, 0, 0)";
	private static final String GET_ALL_STMT = 
		"SELECT ACT_CATEGORY_ID, ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_MAX_PART,ACT_MIN_PART,ACT_FEE,ACT_NUMBER,ACT_TOTAL_SCORE FROM ACT_TYPE order by ACT_CATEGORY_ID";
	private static final String GET_ONE_STMT = 
		"SELECT ACT_CATEGORY_ID, ACT_CATEGORY_NAME,ACT_CATEGORY_DESC,ACT_MAX_PART,ACT_MIN_PART,ACT_FEE,ACT_NUMBER,ACT_TOTAL_SCORE FROM ACT_TYPE where ACT_CATEGORY_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ACT_TYPE where ACT_CATEGORY_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACT_TYPE set  ACT_CATEGORY_NAME=?, ACT_CATEGORY_DESC=?, ACT_MAX_PART=?, ACT_MIN_PART=?, ACT_FEE=? where ACT_CATEGORY_ID=? ";

	
//	以下兩個只在評論更新時存入DB
	//ACT_NUMBER=?, ACT_TOTAL_SCORE=? 
	private static final String INSERT_STMT_COMMENT = 
			"INSERT INTO ACT_TYPE (ACT_NUMBER, ACT_TOTAL_SCORE) values (?,?)";
			
	private static final String UPDATE_COMMENT = 
			"update ACT_TYPE set ACT_NUMBER=?, ACT_TOTAL_SCORE=? where ACT_CATEGORY_ID=? ";
	
			

	
	@Override
	public void insertWithActPhotos(ActTypeVO actTypeVO, List<ActPhotoVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = jdbcUtils.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增活動類別
			String cols[] = {"ACT_CATEGORY_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, actTypeVO.getActCategoryName());
			pstmt.setString(2, actTypeVO.getActCategoryDesc());
			pstmt.setInt(3, actTypeVO.getActMaxPart());
			pstmt.setInt(4, actTypeVO.getActMinPart());
			pstmt.setInt(5, actTypeVO.getActFee());
//			pstmt.setInt(6, actTypeVO.getActNumber());
//			pstmt.setInt(7, actTypeVO.getActTotalScore());
Statement stmt=	con.createStatement();
stmt.executeUpdate("set auto_increment_offset=1;");    //自增主鍵-初始值
stmt.executeUpdate("set auto_increment_increment=1;"); //自增主鍵-遞增
			pstmt.executeUpdate();
			
			//獲取對應的自增主鍵值
		String next_acttypeid = null;
			//
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				//next_acttypeid 為新增成功的活動類別編號（pk）
				next_acttypeid = rs.getString(1);
				System.out.println("自增主鍵值= " + next_acttypeid +"(剛新增成功的活動類別編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增活動圖片
//			ActPhotoDAO actPhotoDAO = new ActPhotoDAO();
			System.out.println("list.size()-A="+list.size());
			for (ActPhotoVO actPhotoVO  : list) {
				actPhotoVO.setActCategoryId(new Integer(next_acttypeid));
				Blob blob = actPhotoVO.getActPhotoFile();
				
				ActPhotoService actPhotoService = new ActPhotoService();
				actPhotoService.addActTypeAndPhoto(con, Integer.valueOf(next_acttypeid), blob);
			}

			// 2●設定於 多邊 （aka List<actPhotoVO>  pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增活動類別編號" + next_acttypeid + "時,共有活動照片" + list.size()
					+ "張同時被新增");
			
			// Handle any driver errors

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由ActType");
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
	public void inserComment(ActTypeVO actTypeVO) {
		

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = jdbcUtils.getConnection();

//			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT_COMMENT);

//			private static final String INSERT_STMT_COMMENT = 
//			"INSERT INTO ACT_TYPE (ACT_NUMBER, ACT_TOTAL_SCORE) values (?,?)";			
			
			pstmt.setInt(1, actTypeVO.getActNumber());
			pstmt.setInt(2, actTypeVO.getActTotalScore());
			
			pstmt.executeUpdate();
			
			
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while inserting comment count. "
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
	public void insert(ActTypeVO actTypeVO) {
		

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();
//			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, actTypeVO.getActCategoryName());
			pstmt.setString(2, actTypeVO.getActCategoryDesc());
			pstmt.setInt(3, actTypeVO.getActMaxPart());
			pstmt.setInt(4, actTypeVO.getActMinPart());
			pstmt.setInt(5, actTypeVO.getActFee());
//			pstmt.setInt(6, actTypeVO.getActNumber());
//			pstmt.setInt(7, actTypeVO.getActTotalScore());
			
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
	public void updateComment(ActTypeVO actTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_COMMENT);
			
//			private static final String UPDATE_COMMENT = 
//			"update ACT_TYPE set ACT_NUMBER=?, ACT_TOTAL_SCORE=? where ACT_CATEGORY_ID=? ";	
			
			pstmt.setInt(1, actTypeVO.getActNumber());
			pstmt.setInt(2, actTypeVO.getActTotalScore());
			pstmt.setInt(3, actTypeVO.getActCategoryId());

			
pstmt.executeUpdate();
			
			

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured while updating comment count."
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
	public void update(ActTypeVO actTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = jdbcUtils.getConnection();
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,actTypeVO.getActCategoryName());
			pstmt.setString(2, actTypeVO.getActCategoryDesc());
			pstmt.setInt(3, actTypeVO.getActMaxPart());
			pstmt.setInt(4, actTypeVO.getActMinPart());
			pstmt.setInt(5, actTypeVO.getActFee());
//	這兩個不會更新到		pstmt.setInt(6, actTypeVO.getActNumber());
//	這兩個不會更新到		pstmt.setInt(7, actTypeVO.getActTotalScore());
			pstmt.setInt(6, actTypeVO.getActCategoryId());
			
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
	public void delete(Integer actCategoryId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;		
		
		try {

			con = jdbcUtils.getConnection();
			//con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actCategoryId);

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
	public ActTypeVO findByPrimaryKey(Integer actCategoryId) {
		
		ActTypeVO actTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();
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

			con = jdbcUtils.getConnection();

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

