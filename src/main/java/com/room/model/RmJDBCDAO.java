package com.room.model;

import com.emp.model.EmpVO;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RmJDBCDAO implements RmDAO_interface {

	JDBCUtils jdbcUtils = new JDBCUtils();

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
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

	@Override
	public int findTotalCount(Map<String, String> condition) {
//        ("total方法開始");


		JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
		//定義模板語句
		String GET_ALL_COUNT =
				"select count(*) from ROOM where 1 = 1 ";

		StringBuilder sb = new StringBuilder(GET_ALL_COUNT);


		//迭代map
		Set<String> keySet = condition.keySet();
		//定義參數的集合
		List<Object> params = new ArrayList<Object>();

		for (String key : keySet) {

//            ("Key:"+key);
			//排除分頁條件參數
			if ("currentPage".equals(key)||
					"rows".equals(key) ||
					"funs".equals(key)||
					"delEmpId".equals(key)){
				continue;
			}

			//獲取值
			String value = condition.get(key);
//            ("value:"+value);

			//判斷是否有值
			if (value != null && !"".equals(value)) {
				//有值
				sb.append(" and " + key + " like ? ");
				params.add("%"+value+"%"); //加問號條件的值
			}
		}


//        (params);



		return Template.queryForObject(sb.toString(), Integer.class, params.toArray());

	}

	@Override
	public List<RmVO> findByPage(int start, int rows, Map<String, String> condition){

		JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
		String GET_LIMIT =
				"select * from ROOM where 1 = 1 ";

//        ("ByPage方法開始");

		StringBuilder sb = new StringBuilder(GET_LIMIT);

		//迭代map
		Set<String> keySet = condition.keySet();

		//定義參數的集合

		List<Object> params = new ArrayList<Object>();

		for (String key : keySet) {
			//排除分頁條件參數
			if ("currentPage".equals(key)||
					"rows".equals(key) ||
					"funs".equals(key)||
					"delEmpId".equals(key)){
				continue;
			}

			//獲取值
			String value = condition.get(key);

			//判斷是否有值
			if (value != null && !"".equals(value)) {
				//有值
				sb.append(" and " + key + " like ? ");
				params.add("%"+value+"%"); //加問號條件的值
			}
		}

		//添加分頁查詢
		sb.append("limit ?,?");

		//添加分頁查詢的參數

		params.add(start);
		params.add(rows);


		String sql = sb.toString();

		return Template.query(sql,new BeanPropertyRowMapper<RmVO>(RmVO.class),params.toArray());


	}
}
