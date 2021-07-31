package com.rmorderlist.model;

import com.rmorder.model.RmoVO;
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


public class RmolJDBCDAO implements RmolDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "CFA101G2";
//	String passwd = "A123456";
	JDBCUtils jdbcUtils = new JDBCUtils();

	private static final String INSERT_STMT = "INSERT INTO ROOM_ORDER_LIST (ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_LIST_ID,ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE FROM ROOM_ORDER_LIST order by ORDER_LIST_ID";
	private static final String GET_ONE_STMT = "SELECT ORDER_LIST_ID,ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE FROM ROOM_ORDER_LIST where ORDER_LIST_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_ORDER_LIST where ORDER_LIST_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_ORDER_LIST set ROOM_ORDER_ID=?, ROOM_CATEGORY_ID=?, ROOM_PROMOTION_ID=?, ROOM_ID=?,MEM_NAME=?, MEM_NUMBER=?, CHECK_IN_DATE=?,CHECK_OUT_DATE=?,ROOM_TOTAL_PRICE=? where ORDER_LIST_ID = ?";
	private static final String GET_ONE_ORDER_ID_STMT = "select * from ROOM_ORDER_LIST where ROOM_ORDER_ID =?";


	@Override
	public void insert(RmolVO rmolVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmolVO.getRoomOrderId());
			pstmt.setInt(2, rmolVO.getRoomCategoryId());
			pstmt.setInt(3, rmolVO.getRoomPromotionId());
			pstmt.setInt(4, rmolVO.getRoomId());
			pstmt.setString(5, rmolVO.getMemName());
			pstmt.setInt(6, rmolVO.getMemNumber());
			pstmt.setTimestamp(7, rmolVO.getCheckInDate());
			pstmt.setTimestamp(8, rmolVO.getCheckOutDate());
			pstmt.setInt(9, rmolVO.getRoomTotalPrice());

			pstmt.executeUpdate();

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
	public void update(RmolVO rmolVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmolVO.getRoomOrderId());
			pstmt.setInt(2, rmolVO.getRoomCategoryId());
			pstmt.setInt(3, rmolVO.getRoomPromotionId());
			pstmt.setInt(4, rmolVO.getRoomId());
			pstmt.setString(5, rmolVO.getMemName());
			pstmt.setInt(6, rmolVO.getMemNumber());
			pstmt.setTimestamp(7, rmolVO.getCheckInDate());
			pstmt.setTimestamp(8, rmolVO.getCheckOutDate());
			pstmt.setInt(9, rmolVO.getRoomTotalPrice());
			pstmt.setInt(10, rmolVO.getOrderListId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
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
	public void delete(Integer orderListId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderListId);

			pstmt.executeUpdate();

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
	public RmolVO findByPrimaryKey(Integer orderListId) {
		RmolVO rmolVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderListId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmolVO = new RmolVO();
				rmolVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
				rmolVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmolVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmolVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
				rmolVO.setRoomId(rs.getInt("ROOM_ID"));
				rmolVO.setMemName(rs.getString("MEM_NAME"));
				rmolVO.setMemNumber(rs.getInt("MEM_NUMBER"));
				rmolVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
				rmolVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
				rmolVO.setRoomTotalPrice(rs.getInt("ROOM_TOTAL_PRICE"));
				
			}
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
		return rmolVO;
	}

	@Override
	public List<RmolVO> getAll() {
		List<RmolVO> list = new ArrayList<RmolVO>();
		RmolVO rmolVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rmolVO = new RmolVO();
				rmolVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
				rmolVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				rmolVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
				rmolVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
				rmolVO.setRoomId(rs.getInt("ROOM_ID"));
				rmolVO.setMemName(rs.getString("MEM_NAME"));
				rmolVO.setMemNumber(rs.getInt("MEM_NUMBER"));
				rmolVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
				rmolVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
				rmolVO.setRoomTotalPrice(rs.getInt("ROOM_TOTAL_PRICE"));
				
				list.add(rmolVO);

			}
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
				"select count(*) from ROOM_ORDER_LIST where 1 = 1 ";

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
	public List<RmolVO> findByPage(int start, int rows, Map<String, String> condition){

		JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
		String GET_LIMIT =
				"select * from ROOM_ORDER_LIST where 1 = 1 ";

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
			System.out.println("Value:"+value);

//            (value);
			//判斷是否有值
			if (value != null && !"".equals(value)) {
				//有值
				sb.append(" and " + key + " like ? ");
				params.add("%"+value+"%"); //加問號條件的值
			}
		}

		//添加分頁查詢
		sb.append(" limit ?,? ");

		//添加分頁查詢的參數

		params.add(start);
		params.add(rows);


		String sql = sb.toString();

		System.out.println(sql);
		return Template.query(sql, new BeanPropertyRowMapper<RmolVO>(RmolVO.class), params.toArray());


	}

	@Override
	public List<RmolVO> getAllByRoomOrderId(Integer RoomOrderId) {

			List<RmolVO> list = new ArrayList<RmolVO>();
			RmolVO rmolVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);

				con = jdbcUtils.getConnection();
				pstmt = con.prepareStatement(GET_ONE_ORDER_ID_STMT);

				pstmt.setInt(1,RoomOrderId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					rmolVO = new RmolVO();
					rmolVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
					rmolVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
					rmolVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
					rmolVO.setRoomPromotionId(rs.getInt("ROOM_PROMOTION_ID"));
					rmolVO.setRoomId(rs.getInt("ROOM_ID"));
					rmolVO.setMemName(rs.getString("MEM_NAME"));
					rmolVO.setMemNumber(rs.getInt("MEM_NUMBER"));
					rmolVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
					rmolVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
					rmolVO.setRoomTotalPrice(rs.getInt("ROOM_TOTAL_PRICE"));

					list.add(rmolVO);

				}
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
}
