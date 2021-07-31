package com.mem.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemJDBCDAO implements MemJDBCDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/GROUP2?serverTimezone=Asia/Taipei"; //35.221.136.103
//	String userid = "root"; //CFA101G2
//	String passwd = "sao124154"; //123456

	JDBCUtils jdbcUtils = new JDBCUtils();


	private static final String INSERT_STMT = 
			"INSERT INTO MEM (MEM_ACCOUNT, MEM_NAME, MEM_PASSWORD, MEM_ADDRESS, MEM_PHONE, MEM_UID, MEM_EMAIL, MEM_SEX, MEM_DOB, MEM_STATUS, MEM_UPDATE) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEM_ID, MEM_ACCOUNT, MEM_NAME, MEM_PASSWORD, MEM_ADDRESS, MEM_PHONE, MEM_UID, MEM_EMAIL, MEM_SEX, MEM_DOB, MEM_STATUS, MEM_UPDATE FROM MEM order by MEM_ID";
	private static final String GET_ONE_STMT = 
			"SELECT MEM_ID, MEM_ACCOUNT, MEM_NAME, MEM_PASSWORD, MEM_ADDRESS, MEM_PHONE, MEM_UID, MEM_EMAIL, MEM_SEX, MEM_DOB, MEM_STATUS, MEM_UPDATE FROM MEM where MEM_ID = ?";
	private static final String UPDATE = 
			"UPDATE MEM set MEM_ACCOUNT=?, MEM_NAME=?, MEM_PASSWORD=?, MEM_ADDRESS=?, MEM_PHONE=?, MEM_UID=?, MEM_EMAIL=?, MEM_SEX=?, MEM_DOB=?, MEM_STATUS=?, MEM_UPDATE=? where MEM_ID = ?";
	private static final String LOGIN = 
			"SELECT MEM_ID, MEM_ACCOUNT, MEM_NAME, MEM_PASSWORD, MEM_ADDRESS, MEM_PHONE, MEM_UID, MEM_EMAIL, MEM_SEX, MEM_DOB, MEM_STATUS, MEM_UPDATE FROM MEM where MEM_ACCOUNT = ?";



	@Override //註冊
	public void insert(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver); //載入驅動
//			con = DriverManager.getConnection(url, userid, passwd); //取得連結
			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT); //建立連線
			
			pstmt.setString(1, memVO.getMemAccount());
			pstmt.setString(2, memVO.getMemName());
			pstmt.setString(3, memVO.getMemPassword());
			pstmt.setString(4, memVO.getMemAddress());
			pstmt.setString(5, memVO.getMemPhone());
			pstmt.setString(6, memVO.getMemUid());
			pstmt.setString(7, memVO.getMemEmail());
			pstmt.setString(8, memVO.getMemSex());
			pstmt.setDate(9, memVO.getMemDob());
			pstmt.setInt(10, memVO.getMemStatus());
			pstmt.setTimestamp(11, memVO.getMemUpdate());
			
			pstmt.executeUpdate(); //更新並上傳

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
	
	@Override //查全部
	public List<MemVO> getAll() {
		
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

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
				memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAccount(rs.getString("MEM_ACCOUNT"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemUid(rs.getString("MEM_UID"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemSex(rs.getString("MEM_SEX"));
				memVO.setMemDob(rs.getDate("MEM_DOB"));
				memVO.setMemStatus(rs.getInt("MEM_STATUS"));
				memVO.setMemUpdate(rs.getTimestamp("MEM_UPDATE"));
				list.add(memVO);
			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	
	@Override //查單一
	public MemVO findByPrimaryKey(Integer id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_id"));
				memVO.setMemAccount(rs.getString("MEM_ACCOUNT"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemUid(rs.getString("MEM_UID"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemSex(rs.getString("MEM_SEX"));
				memVO.setMemDob(rs.getDate("MEM_DOB"));
				memVO.setMemStatus(rs.getInt("MEM_STATUS"));
				memVO.setMemUpdate(rs.getTimestamp("MEM_UPDATE"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return memVO;
	}
	
	@Override //更新
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMemAccount());
			pstmt.setString(2, memVO.getMemName());
			pstmt.setString(3, memVO.getMemPassword());
			pstmt.setString(4, memVO.getMemAddress());
			pstmt.setString(5, memVO.getMemPhone());
			pstmt.setString(6, memVO.getMemUid());
			pstmt.setString(7, memVO.getMemEmail());
			pstmt.setString(8, memVO.getMemSex());
			pstmt.setDate(9, memVO.getMemDob());
			pstmt.setInt(10, memVO.getMemStatus());
			pstmt.setTimestamp(11, memVO.getMemUpdate());
			pstmt.setInt(12, memVO.getMemId());
			
			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
	
	@Override //登入
	public MemVO findByPrimaryKeyByMemAcc(String memAccount) {
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, memAccount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAccount(rs.getString("MEM_ACCOUNT"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemUid(rs.getString("MEM_UID"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemSex(rs.getString("MEM_SEX"));
				memVO.setMemDob(rs.getDate("MEM_DOB"));
				memVO.setMemStatus(rs.getInt("MEM_STATUS"));
				memVO.setMemUpdate(rs.getTimestamp("MEM_UPDATE"));
			}

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return memVO;
	}
}
