package com.extrabill.model;

import com.ron.model.RonVO;
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
            "insert into EXTRA_BILL (ROOM_ID, EXTRA_PRICE, INFORMATION_PHONE, EXPECTED_CHECK_OUT_DATE, CHECK_IN_DATE, CHECK_OUT_DATE) value (?,?,?,?,?,?)";
    private static final String GET_ONE_STMT =
            "select * from EXTRA_BILL where ROOM_ID=?";
    private static final String GET_ALL_STMT =
            "select EXTRA_BILL_ID, ORDER_LIST_ID, EXTRA_PRICE, AMOUNT, EXPECTED_CHECK_OUT_DATE, CONSUMPTION_DATE from EXTRA_BILL order by EXTRA_BILL_ID";
    private static final String DELETE =
            "delete from EXTRA_BILL where EXTRA_BILL_ID=?";

    private static final String DELETE_ROOM =
            "delete from EXTRA_BILL where ROOM_ID=?";
    private static final String UPDATE =
            "update EXTRA_BILL set ROOM_ID=?,EXTRA_PRICE=?, INFORMATION_PHONE=? ,EXPECTED_CHECK_OUT_DATE=? ,CHECK_IN_DATE=? ,CHECK_OUT_DATE=? where EXTRA_BILL_ID=?";

    @Override
    public void insert(ExtraBillVO extraBillVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, extraBillVO.getRoomId());
            pstmt.setInt(2, extraBillVO.getExtraPrice());
            pstmt.setString(3, extraBillVO.getInformationPhone());
            pstmt.setTimestamp(4, extraBillVO.getExpectedCheckOutDate());
            pstmt.setTimestamp(5, extraBillVO.getCheckInDate());
            pstmt.setTimestamp(6, extraBillVO.getCheckOutDate());

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

            pstmt.setInt(1, extraBillVO.getRoomId());
            pstmt.setInt(2, extraBillVO.getExtraPrice());
            pstmt.setString(3, extraBillVO.getInformationPhone());
            pstmt.setTimestamp(4, extraBillVO.getExpectedCheckOutDate());
            pstmt.setTimestamp(5, extraBillVO.getCheckInDate());
            pstmt.setTimestamp(6, extraBillVO.getCheckOutDate());
            pstmt.setInt(7, extraBillVO.getExtraBillId());

            int i = pstmt.executeUpdate();


            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public ExtraBillVO getOneByRoomId(Integer roomId) {

        ExtraBillVO extraBillVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, roomId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                extraBillVO = new ExtraBillVO();
                extraBillVO.setExtraBillId(rs.getInt("EXTRA_BILL_ID"));
                extraBillVO.setRoomId(rs.getInt("ROOM_ID"));
                extraBillVO.setExtraPrice(rs.getInt("EXTRA_PRICE"));
                extraBillVO.setInformationPhone(rs.getString("INFORMATION_PHONE"));
                extraBillVO.setExpectedCheckOutDate(rs.getTimestamp("EXPECTED_CHECK_OUT_DATE"));
                extraBillVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
                extraBillVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));

            }
            ;

        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs, pstmt, con);
        }
        return extraBillVO;
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
    public void deleteByRoomId(Integer roomId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE_ROOM);

            pstmt.setInt(1, roomId);

            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }


}
