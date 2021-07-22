package com.rmorder.model;

import com.emp.model.EmpVO;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RmoJDBCDAO implements RmoDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	String userid = "CFA101G2";
	String passwd = "123456";

	JDBCUtils jdbcUtils = new JDBCUtils();


	private static final String INSERT_STMT = "INSERT INTO ROOM_ORDER (MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE FROM ROOM_ORDER order by ROOM_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE FROM ROOM_ORDER where ROOM_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_ORDER where ROOM_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_ORDER set MEM_ID=?, ORDER_DATE=?, ROOM_ORDER_STATUS=?, TOTAL_PRICE=? where ROOM_ORDER_ID = ?";


	@Override
	public void insert(RmoVO rmoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmoVO.getMemId());
			pstmt.setTimestamp(2, rmoVO.getOrderDate());
			pstmt.setInt(3, rmoVO.getRoomOrderStatus());
			pstmt.setInt(4, rmoVO.getTotalPrice());

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
	public void update(RmoVO rmoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmoVO.getRoomOrderId());
			pstmt.setInt(2, rmoVO.getMemId());
			pstmt.setTimestamp(3, rmoVO.getOrderDate());
			pstmt.setInt(4, rmoVO.getRoomOrderStatus());
			pstmt.setInt(5, rmoVO.getTotalPrice());

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
	public void delete(Integer roomOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomOrderId);

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
	public RmoVO findByPrimaryKey(Integer roomOrderId) {
		RmoVO rmoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomOrderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmoVO = new RmoVO();
				rmoVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmoVO.setMemId(rs.getInt("MEM_ID"));
				rmoVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				rmoVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				rmoVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));

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
		return rmoVO;
	}

	@Override
	public List<RmoVO> getAll() {
		List<RmoVO> list = new ArrayList<RmoVO>();
		RmoVO rmoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmoVO = new RmoVO();
				rmoVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmoVO.setMemId(rs.getInt("MEM_ID"));
				rmoVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				rmoVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
				rmoVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
				
				list.add(rmoVO);

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

	@Override
	public int findTotalCount(Map<String, String> condition){
//        ("total方法開始");


		JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
		//定義模板語句
		String GET_ALL_COUNT =
				"select count(*) from ROOM_ORDER where 1 = 1 ";

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
	public List<RmoVO> findByPage(int start, int rows, Map<String, String> condition) {

		JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
		String GET_LIMIT =
				"select * from ROOM_ORDER where 1 = 1 ";

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

//            (value);
			//判斷是否有值
			if (value != null && !"".equals(value)) {
				//有值
				sb.append(" and " + key + " like ? ");
				params.add("%"+value+"%"); //加問號條件的值
			}
		}

		//按照新增時間排序
		sb.append(" order by ORDER_DATE desc ");
		//添加分頁查詢
		sb.append(" limit ?,? ");

		//添加分頁查詢的參數

		params.add(start);
		params.add(rows);


		String sql = sb.toString();

		System.out.println(sql);
		return Template.query(sql,new BeanPropertyRowMapper<RmoVO>(RmoVO.class),params.toArray());


	}



}
