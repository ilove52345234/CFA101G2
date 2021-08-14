package com.shoptype.model;

import java.sql.*;
import java.util.*;

import com.shop.model.ShopVO;
import com.utils.JDBCUtils;

public class ShopTypeJDBCDAO implements ShopTypeDAO_interface {

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/CFA101G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Meng25858825";

	JDBCUtils jdbcUtils = new JDBCUtils();


	private static final String INSERT_STMT = "INSERT INTO SHOP_TYPE(ITEM_CATEGORY_NAME, ITEM_CATEGORY_DESC)VALUE(?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOP_TYPE ORDER BY ITEM_CATEGORY_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOP_TYPE WHERE ITEM_CATEGORY_ID = ?";
	// 
	private static final String GET_SHOPS_BY_ITEM_CATEGORY_ID_STMT = "SELECT ITEM_ID, ITEM_CATEGORY_ID, ITEM_DESCRIBTION, ITEM_FEE, ITEM_NAME, ITEM_QUANTITY, ITEM_STATUS, COMMENT_NUMBER, COMMENT_TOTAL_SCORE FROM SHOP WHERE ITEM_CATEGORY_ID = ? ORDER BY ITEM_ID";
	// 
	private static final String DELETE_SHOPS = "DELETE FROM SHOP WHERE ITEM_CATEGORY_ID =?";
	private static final String DELETE_SHOP_TYPE = "DELETE FROM SHOP_TYPE WHERE ITEM_CATEGORY_ID =?";
	private static final String UPDATE = "UPDATE SHOP_TYPE SET ITEM_CATEGORY_NAME =?, ITEM_CATEGORY_DESC =? WHERE ITEM_CATEGORY_ID = ?";

	@Override
	public void insert(ShopTypeVO shoptypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			// ���n��driver
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shoptypeVO.getItemCategoryName());
			pstmt.setString(2, shoptypeVO.getItemCategoryDesc());
				
			int rowCount = pstmt.executeUpdate();
			//��s��Ʈw,�Ǧ^��s���\������
			System.out.println("�s�W " + rowCount + "�����");
			

			// �B�z�����X�ʪ����~
		}  catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~" + se.getMessage());
			// ����JDBC���귽
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);// System.err API�w�]��k
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
	public void update(ShopTypeVO shopTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			// ���n��driver

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, shopTypeVO.getItemCategoryName());
			pstmt.setString(2, shopTypeVO.getItemCategoryDesc());
			pstmt.setInt(3, shopTypeVO.getItemCategoryId());
			pstmt.executeUpdate();

			// �B�z�����X�ʪ����~
		}  catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~" + se.getMessage());
			// ����JDBC���귽
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
	public void delete(Integer itemCategoryId) {
		
		int updateCount_Shops = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			// ���n��driver

			con = jdbcUtils.getConnection();

			// 1. �]�w��pstm.executeUpdate()�e
			con.setAutoCommit(false);
			
			// ���R���ӫ~
			pstmt = con.prepareStatement(DELETE_SHOPS);
			pstmt.setInt(1, itemCategoryId);
			updateCount_Shops = pstmt.executeUpdate();
			
			// �A�R�����O(?)
			pstmt = con.prepareStatement(DELETE_SHOP_TYPE);
			pstmt.setInt(1, itemCategoryId);
			pstmt.execute();
			
			// 2. �]�w��pstm.executeUpdate��
			con.commit();
			con.setAutoCommit(true);
			System.out.println("�R�����O�s��" + itemCategoryId + "�ɡA�@���ӫ~" + updateCount_Shops + "�ӡA�P�ɳQ�R��");
			
			// �B�z�����X�ʪ����~
		}  catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~" + se.getMessage());
			// ����JDBC���귽
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
	public ShopTypeVO findByPK(Integer itemCategoryId) {
		
		ShopTypeVO  shopTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = jdbcUtils.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, itemCategoryId);	
			// �d�߸�Ʈw,�Ǧ^ResultSet
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {
				
				shopTypeVO = new ShopTypeVO();
				shopTypeVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopTypeVO.setItemCategoryName(rs.getString("ITEM_CATEGORY_NAME"));
				shopTypeVO.setItemCategoryDesc(rs.getString("ITEM_CATEGORY_DESC"));
				
			}
			//�B�z�����X�ʿ��~
		}catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~"+se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}if(con != null) {
			try {
				con.close();
			}catch(Exception e){
				e.printStackTrace(System.err);
			}
		}		
		return shopTypeVO;
	}

	@Override
	public List<ShopTypeVO> getAll() {
		
		List<ShopTypeVO> list = new ArrayList<ShopTypeVO>();
		ShopTypeVO shopTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				shopTypeVO = new ShopTypeVO();
				shopTypeVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopTypeVO.setItemCategoryName(rs.getString("ITEM_CATEGORY_NAME"));
				shopTypeVO.setItemCategoryDesc(rs.getString("ITEM_CATEGORY_DESC"));
				list.add(shopTypeVO);
				
			}
		}  catch (SQLException se) {
			throw new RuntimeException("��Ʈw�o�Ϳ��~" + se.getMessage());
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return list;
	}
	
	@Override
	public Set<ShopVO> getShopsByItemCategoryId(Integer itemCategoryId) {
		Set<ShopVO> set = new LinkedHashSet<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jdbcUtils.getConnection();
			pstmt = con.prepareStatement(GET_SHOPS_BY_ITEM_CATEGORY_ID_STMT);
			pstmt.setInt(1, itemCategoryId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setItemId(rs.getInt("ITEM_ID"));
				shopVO.setItemCategoryId(rs.getInt("ITEM_CATEGORY_ID"));
				shopVO.setItemDescribtion(rs.getString("ITEM_DESCRIBTION"));
				shopVO.setItemFee(rs.getInt("ITEM_FEE"));
				shopVO.setItemName(rs.getString("ITEM_NAME"));
				shopVO.setItemQuantity(rs.getInt("ITEM_QUANTITY"));
				shopVO.setItemStatus(rs.getByte("ITEM_STATUS"));
				shopVO.setCommentNumber(rs.getInt("COMMENT_NUMBER"));
				shopVO.setCommentTotalScore(rs.getInt("COMMENT_TOTAL_SCORE"));
				set.add(shopVO);
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
		return set;
	}

}
