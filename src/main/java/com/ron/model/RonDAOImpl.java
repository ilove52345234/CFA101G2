package com.ron.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RonDAOImpl implements RonDAO {

    JDBCUtils jdbcUtils = new JDBCUtils();

    private static final String INSERT_STMT =
            "insert into ROOM_ORDER_NOTIFY(ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, NOTIFY_TEXT, NOTIFY_DATE, NOTIFY_STATUS)value (?,?,?,?,?,?,?)";
    private static final String GET_ONE_STMT =
            "select ROOM_ORDER_NOTIFY_ID, ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, NOTIFY_TEXT, NOTIFY_DATE, NOTIFY_STATUS from ROOM_ORDER_NOTIFY where ROOM_ORDER_NOTIFY_ID=?";
    private static final String GET_ALL_STMT =
            "select ROOM_ORDER_NOTIFY_ID, ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, NOTIFY_TEXT, NOTIFY_DATE, NOTIFY_STATUS from ROOM_ORDER_NOTIFY order by ROOM_ORDER_NOTIFY_ID";
    private static final String DELETE =
            "delete from ROOM_ORDER_NOTIFY where ROOM_ORDER_NOTIFY_ID=?";
    private static final String UPDATE =
            "update ROOM_ORDER_NOTIFY set ROOM_ORDER_ID=?, MEM_ID=?,CHECK_IN_DATE=?,CHECK_OUT_DATE=?,NOTIFY_TEXT=?,NOTIFY_DATE=?,NOTIFY_STATUS=? where ROOM_ORDER_NOTIFY_ID=?";


    @Override
    public void insert(RonVO ronVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, ronVO.getRoomOrderId());
            pstmt.setInt(2, ronVO.getMemId());
            pstmt.setTimestamp(3, ronVO.getCheckInDate());
            pstmt.setTimestamp(4, ronVO.getCheckOutDate());
            pstmt.setString(5, ronVO.getNotifyText());
            pstmt.setTimestamp(6, ronVO.getNotifyDate());
            pstmt.setInt(7, ronVO.getNotifyStatus());

            int i = pstmt.executeUpdate();

//            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public void update(RonVO ronVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setInt(1, ronVO.getRoomOrderId());
            pstmt.setInt(2, ronVO.getMemId());
            pstmt.setTimestamp(3, ronVO.getCheckInDate());
            pstmt.setTimestamp(4, ronVO.getCheckOutDate());
            pstmt.setString(5, ronVO.getNotifyText());
            pstmt.setTimestamp(6, ronVO.getNotifyDate());
            pstmt.setInt(7, ronVO.getNotifyStatus());
            pstmt.setInt(8, ronVO.getRoomOrderNotifyId());



            int i = pstmt.executeUpdate();

//            System.out.println("更新" + i + "筆完成");

        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public void delete(Integer roomOrderNotifyId) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();

            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, roomOrderNotifyId);

            int i = pstmt.executeUpdate();

//            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public RonVO findByPrimaryKey(Integer roomOrderNotifyId) {
        RonVO ronVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, roomOrderNotifyId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ronVO = new RonVO();
                ronVO.setRoomOrderNotifyId(rs.getInt("ROOM_ORDER_NOTIFY_ID"));
                ronVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
                ronVO.setMemId(rs.getInt("MEM_ID"));
                ronVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
                ronVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
                ronVO.setNotifyText(rs.getString("NOTIFY_TEXT"));
                ronVO.setNotifyDate(rs.getTimestamp("NOTIFY_DATE"));
                ronVO.setNotifyStatus(rs.getInt("NOTIFY_STATUS"));

            };

        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }


        return ronVO;
    }

    @Override
    public List<RonVO> getAll() {
        ArrayList<RonVO> ronList = new ArrayList<RonVO>();
        RonVO ronVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ronVO = new RonVO();
                ronVO.setRoomOrderNotifyId(rs.getInt("ROOM_ORDER_NOTIFY_ID"));
                ronVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
                ronVO.setMemId(rs.getInt("MEM_ID"));
                ronVO.setCheckInDate(rs.getTimestamp("CHECK_IN_DATE"));
                ronVO.setCheckOutDate(rs.getTimestamp("CHECK_OUT_DATE"));
                ronVO.setNotifyText(rs.getString("NOTIFY_TEXT"));
                ronVO.setNotifyDate(rs.getTimestamp("NOTIFY_DATE"));
                ronVO.setNotifyStatus(rs.getInt("NOTIFY_STATUS"));
                ronList.add(ronVO);
            };



        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }

        return ronList;
    }
}
