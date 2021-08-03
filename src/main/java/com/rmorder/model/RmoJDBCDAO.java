package com.rmorder.model;

import com.emp.model.EmpVO;
import com.rmorderlist.model.RmolVO;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RmoJDBCDAO implements RmoDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "CFA101G2";
//	String passwd = "A123456";

	JDBCUtils jdbcUtils = new JDBCUtils();


	private static final String INSERT_STMT = "INSERT INTO ROOM_ORDER (MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE FROM ROOM_ORDER order by ROOM_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE FROM ROOM_ORDER where ROOM_ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_ORDER where ROOM_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE ROOM_ORDER set MEM_ID=?, ORDER_DATE=?, ROOM_ORDER_STATUS=?, TOTAL_PRICE=? where ROOM_ORDER_ID = ?";

	private static final String INSERT_RS_STMT = "INSERT INTO ROOM_SCHEDULE (ROOM_CATEGORY_ID, ROOM_SCHEDULE_DATE, ROOM_AMOUNT, ROOM_RSV_BOOKED) VALUES (?,?,?,?)";

	private static final String INSERT_LIST_STMT = "INSERT INTO ROOM_ORDER_LIST (ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE) VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String GET_PRE_ROOM_ORDER = "SELECT ro.ROOM_ORDER_ID , m.MEM_NAME ,m.MEM_EMAIL ,m.MEM_PHONE , rt.ROOM_NAME , rol.CHECK_IN_DATE ,"
			+ " rol.CHECK_OUT_DATE , rol.MEM_NUMBER ,DATEDIFF(rol.CHECK_OUT_DATE, rol.CHECK_IN_DATE) , rt.ROOM_PRICE ,ro.TOTAL_PRICE , COUNT(*) "
			+ " FROM MEM m left join ROOM_ORDER ro on m.MEM_ID =ro.MEM_ID left join ROOM_ORDER_LIST rol on ro.ROOM_ORDER_ID =rol.ROOM_ORDER_ID left join ROOM_TYPE rt on rol.ROOM_CATEGORY_ID =rt.ROOM_CATEGORY_ID WHERE m.MEM_ID =? and ro.ROOM_ORDER_ID =? "
			+" GROUP BY ro.ROOM_ORDER_ID, m.MEM_NAME, m.MEM_EMAIL ,m.MEM_PHONE, rt.ROOM_NAME, rol.CHECK_IN_DATE, rol.CHECK_OUT_DATE, DATEDIFF(rol.CHECK_OUT_DATE , rol.CHECK_IN_DATE), rt.ROOM_PRICE,rol.MEM_NUMBER , ro.TOTAL_PRICE ";


	private static final String UPDATE_ROOM = "UPDATE ROOM set ROOM_CHECK_STATUS=? where ROOM_ID = ?";
	private static final String GET_MEM_ROOM_ORDER ="SELECT DISTINCT " + "ro.ROOM_ORDER_ID ," + "ro.MEM_ID ," + "ro.ORDER_DATE ," + "ro.TOTAL_PRICE ," + "rol.CHECK_IN_DATE ,"
			+ "rt.ROOM_NAME " + " FROM ROOM_ORDER ro left join ROOM_ORDER_LIST rol on ro.ROOM_ORDER_ID=rol.ROOM_ORDER_ID "
			+ " left join ROOM_TYPE rt on rol.ROOM_CATEGORY_ID =rt.ROOM_CATEGORY_ID" + " WHERE ro.MEM_ID = ? " + "order by ro.ROOM_ORDER_ID DESC " ;


	@Override
	public void insert(RmoVO rmoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rmoVO.getMemId());
			pstmt.setTimestamp(2, rmoVO.getOrderDate());
			pstmt.setInt(3, rmoVO.getRoomOrderStatus());
			pstmt.setInt(4, rmoVO.getTotalPrice());

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
	public void update(RmoVO rmoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rmoVO.getMemId());
			pstmt.setTimestamp(2, rmoVO.getOrderDate());
			pstmt.setInt(3, rmoVO.getRoomOrderStatus());
			pstmt.setInt(4, rmoVO.getTotalPrice());
			pstmt.setInt(5, rmoVO.getRoomOrderId());


			pstmt.executeUpdate();

		}catch (SQLException se) {
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomOrderId);

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
	public RmoVO findByPrimaryKey(Integer roomOrderId) {
		RmoVO rmoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
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



	/**
	 * 新增訂單 及 多筆訂單明細
	 */
	@Override
	public Integer bookPreOrder(RmoVO rmoVO, List<RmolVO> rmolVOs) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("=============== book room dao ================");

			con = jdbcUtils.getConnection();
			con.setAutoCommit(false);

//			// 新增 預定明細
//			for (RsVO rsVO : rsVos) {
//				pstmt = con.prepareStatement(INSERT_RS_STMT);
//
//				pstmt.setInt(1, rsVO.getRoomCategoryId());
//				pstmt.setDate(2, rsVO.getRoomScheduleDate());
//				pstmt.setInt(3, rsVO.getRoomAmount());
//				pstmt.setInt(4, rsVO.getRoomRsvBooked());
//
//				pstmt.executeUpdate();
//				System.out.println("room schedule insert , rsVO:" + rsVO);
//			}
			//此方法為結合住房訂單及住房訂單明細
			//1.新增 住房訂單
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, rmoVO.getMemId());
			pstmt.setTimestamp(2, rmoVO.getOrderDate());
			pstmt.setInt(3, rmoVO.getRoomOrderStatus());
			pstmt.setInt(4, rmoVO.getTotalPrice());

			pstmt.executeUpdate();
			System.out.println("room order insert , rmoVO:" + rmoVO);

			rs = pstmt.getGeneratedKeys();       // 以此方式可以取得資料庫自增主鍵的物件
			Integer newOrderId = null;

			if (rs.next()) {
				newOrderId = rs.getInt(1);    //rs指向PK並且取得PK
			} else {  //沒取得主鍵則報失敗訊息
				throw new RuntimeException("A database getGeneratedKeys error occured. ");
			}
			System.out.println("住房訂單 newOrderId :" + newOrderId);

			//2.新增 住房訂單明細
			for (RmolVO rmolVO : rmolVOs) {
				pstmt = con.prepareStatement(INSERT_LIST_STMT);

				pstmt.setInt(1, newOrderId);   //這邊的FK是ROOM_ORDER_ID 從上面新增完住房訂單資料後直接用newOrderId帶下來 這樣才會一起同步新增兩個訂單
				pstmt.setInt(2, rmolVO.getRoomCategoryId());
				pstmt.setObject(3, rmolVO.getRoomPromotionId()); //不能用Int因為會自動拆箱成Int型別而不是物件型別
				pstmt.setObject(4, rmolVO.getRoomId());
				pstmt.setString(5, rmolVO.getMemName());
				pstmt.setInt(6, rmolVO.getMemNumber());
				pstmt.setTimestamp(7, rmolVO.getCheckInDate());
				pstmt.setTimestamp(8, rmolVO.getCheckOutDate());
				pstmt.setInt(9, rmolVO.getRoomTotalPrice());
				pstmt.executeUpdate();
				System.out.println("room order list insert , rmolVO:" + rmolVO);
			}

			con.commit();

			return newOrderId;

		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

	/**
	 * 查詢 預定訂單明細
	 */
	@Override
	public BookRmoVO findByBookOrder(Integer memberId, Integer orderId) {
		BookRmoVO bookRmoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_PRE_ROOM_ORDER);
			System.out.println("GET_PRE_ROOM_ORDER= "+GET_PRE_ROOM_ORDER);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, orderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookRmoVO = new BookRmoVO();
				bookRmoVO.setRoomOrderId(rs.getInt(1));
				bookRmoVO.setMemName(rs.getString(2));
				bookRmoVO.setMemEmail(rs.getString(3));
				bookRmoVO.setMemPhone(rs.getString(4));
				bookRmoVO.setRoomName(rs.getString(5));
				bookRmoVO.setCheckInDate(rs.getTimestamp(6));
				bookRmoVO.setCheckOutDate(rs.getTimestamp(7));
				bookRmoVO.setMemNumber(rs.getInt(8));
				bookRmoVO.setDays(rs.getInt(9));
				bookRmoVO.setRoomPrice(rs.getInt(10));
				bookRmoVO.setRoomTotalPrice(rs.getInt(11));
				bookRmoVO.setRoomCount(rs.getInt(12));
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
		return bookRmoVO;
	}

	//查詢會員訂單
	@Override
	public List<MemRoomOrderVO> roomOrder(Integer memId) {
		List<MemRoomOrderVO> list = new ArrayList<MemRoomOrderVO>();
		MemRoomOrderVO memRoomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println("GET_MEM_ROOM_ORDER= "+GET_MEM_ROOM_ORDER);
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ROOM_ORDER);

			pstmt.setInt(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memRoomOrderVO = new MemRoomOrderVO();
				memRoomOrderVO.setRoomOrderId(rs.getInt(1));
				memRoomOrderVO.setMemId(rs.getInt(2));
				memRoomOrderVO.setOrderDate(rs.getTimestamp(3));
				memRoomOrderVO.setTotalPrice(rs.getInt(4));
				memRoomOrderVO.setCheckInDate(rs.getTimestamp(5));
				memRoomOrderVO.setRoomName(rs.getString(6));
				list.add(memRoomOrderVO);
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
