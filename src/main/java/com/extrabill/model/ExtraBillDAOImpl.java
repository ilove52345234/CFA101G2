package com.extrabill.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtraBillDAOImpl implements ExtraBillDAO {


    JDBCUtils jdbcUtils = new JDBCUtils();


    private static final String INSERT_STMT =
            "insert into EXTRA_BILL(ORDER_LIST_ID, EXTRA_PRICE, AMOUNT, SERVICE_ITEM, CONSUMPTION_DATE) value (?,?,?,?,?)";
    private static final String GET_ONE_STMT =
            "select EXTRA_BILL_ID, ORDER_LIST_ID, EXTRA_PRICE, AMOUNT, SERVICE_ITEM, CONSUMPTION_DATE from EXTRA_BILL where EXTRA_BILL_ID=?";
    private static final String GET_ALL_STMT =
            "select EXTRA_BILL_ID, ORDER_LIST_ID, EXTRA_PRICE, AMOUNT, SERVICE_ITEM, CONSUMPTION_DATE from EXTRA_BILL order by EXTRA_BILL_ID";
    private static final String DELETE =
            "delete from EXTRA_BILL where EXTRA_BILL_ID=?";
    private static final String UPDATE =
            "update EXTRA_BILL set ORDER_LIST_ID =?, EXTRA_PRICE =?, AMOUNT =?, SERVICE_ITEM =?, CONSUMPTION_DATE =? where EXTRA_BILL_ID=?";


    @Override
    public void insert(ExtraBillVO extraBillVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, extraBillVO.getOrderListId());
            pstmt.setInt(2, extraBillVO.getExtraPrice());
            pstmt.setInt(3, extraBillVO.getAmount());
            pstmt.setString(4, extraBillVO.getServiceItem());
            pstmt.setTimestamp(5, extraBillVO.getConsumptionDate());

            int i = pstmt.executeUpdate();


            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void update(ExtraBillVO extraBillVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, extraBillVO.getOrderListId());
            pstmt.setInt(2, extraBillVO.getExtraPrice());
            pstmt.setInt(3, extraBillVO.getAmount());
            pstmt.setString(4, extraBillVO.getServiceItem());
            pstmt.setTimestamp(5, extraBillVO.getConsumptionDate());
            pstmt.setInt(6, extraBillVO.getExtraBillId());

            int i = pstmt.executeUpdate();


            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void delete(Integer extraBillId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, extraBillId);

            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public ExtraBillVO findByPrimaryKey(Integer extraBillId) {
        ExtraBillVO extraBillVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1,extraBillId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                extraBillVO = new ExtraBillVO();

                extraBillVO.setExtraBillId(rs.getInt("EXTRA_BILL_ID"));
                extraBillVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
                extraBillVO.setExtraPrice(rs.getInt("EXTRA_PRICE"));
                extraBillVO.setAmount(rs.getInt("AMOUNT"));
                extraBillVO.setServiceItem(rs.getString("SERVICE_ITEM"));
                extraBillVO.setConsumptionDate(rs.getTimestamp("CONSUMPTION_DATE"));

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }
        return extraBillVO;
    }

    @Override
    public List<ExtraBillVO> getAll() {
        ArrayList<ExtraBillVO> extraBillVOList = new ArrayList<ExtraBillVO>();

        ExtraBillVO extraBillVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                extraBillVO = new ExtraBillVO();

                extraBillVO.setExtraBillId(rs.getInt("EXTRA_BILL_ID"));
                extraBillVO.setOrderListId(rs.getInt("ORDER_LIST_ID"));
                extraBillVO.setExtraPrice(rs.getInt("EXTRA_PRICE"));
                extraBillVO.setAmount(rs.getInt("AMOUNT"));
                extraBillVO.setServiceItem(rs.getString("SERVICE_ITEM"));
                extraBillVO.setConsumptionDate(rs.getTimestamp("CONSUMPTION_DATE"));
                extraBillVOList.add(extraBillVO);
            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }
        return extraBillVOList;
    }
}
