package com.room.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;







	public class RmDAO implements RmDAO_interface {

		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = "INSERT INTO ROOM (ROOM_CATEGORY_ID, ROOM_CHECK_STATUS, ROOM_SALE_STATUS, ROOM_INFORMATION) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = "SELECT ROOM_ID, ROOM_CATEGORY_ID, ROOM_CHECK_STATUS, ROOM_SALE_STATUS, ROOM_INFORMATION FROM ROOM_TYPE order by ROOM_CATEGORY_ID";
		private static final String GET_ONE_STMT = "SELECT ROOM_ID, ROOM_CATEGORY_ID, ROOM_CHECK_STATUS, ROOM_SALE_STATUS, ROOM_INFORMATION FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?";
		private static final String DELETE = "DELETE FROM ROOM where ROOM_ID = ?";
		private static final String UPDATE = "UPDATE ROOM set ROOM_ID=?, ROOM_CATEGORY_ID=?, ROOM_CHECK_STATUS=?, ROOM_SALE_STATUS=?, ROOM_INFORMATION=? where ROOM_ID = ?";
		
		@Override
		public void insert(RmVO rmVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
//和第33行對照  若對照錯會發生錯誤
				pstmt.setInt(1, rmVO.getRoomCategoryId());
				pstmt.setByte(2, rmVO.getRoomCheckStatus());
				pstmt.setByte(3, rmVO.getRoomSaleStatus());
				pstmt.setString(4, rmVO.getRoomInformation());
		

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
		public void update(RmVO rmVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, rmVO.getRoomId());
				pstmt.setInt(2, rmVO.getRoomCategoryId());
				pstmt.setByte(3, rmVO.getRoomCheckStatus());
				pstmt.setByte(4, rmVO.getRoomSaleStatus());
				pstmt.setString(5, rmVO.getRoomInformation());
		

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
		public void delete(Integer roomId) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, roomId);

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
		public RmVO findByPrimaryKey(Integer roomId) {
			RmVO rmVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
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
				con = ds.getConnection();
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
