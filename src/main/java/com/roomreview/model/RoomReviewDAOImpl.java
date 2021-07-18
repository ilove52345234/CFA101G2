package com.roomreview.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReviewDAOImpl implements RoomReviewDAO {
    JDBCUtils jdbcUtils = new JDBCUtils();


    private static final String INSERT_STMT =
            "insert into ROOM_REVIEW (ROOM_CATEGORY_ID, MEM_ID, COMMENT_CONTENT, COMMENT_DATE, SATISFACTION)value (?,?,?,?,?)";
    private static final String GET_ONE_STMT =
            "select ROOM_REVIEW_ID, ROOM_CATEGORY_ID, MEM_ID, COMMENT_CONTENT, COMMENT_DATE, SATISFACTION from ROOM_REVIEW where ROOM_REVIEW_ID=?";
    private static final String GET_ALL_STMT =
            "select ROOM_REVIEW_ID, ROOM_CATEGORY_ID, MEM_ID, COMMENT_CONTENT, COMMENT_DATE, SATISFACTION from ROOM_REVIEW order by ROOM_REVIEW_ID";
    private static final String DELETE =
            "delete from ROOM_REVIEW where ROOM_REVIEW_ID=?";
    private static final String UPDATE =
            "update ROOM_REVIEW set ROOM_CATEGORY_ID = ?, MEM_ID =?, COMMENT_CONTENT =?, COMMENT_DATE =?, SATISFACTION =? where ROOM_REVIEW_ID=?";


    @Override
    public void insert(RoomReviewVO roomReviewVO) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, roomReviewVO.getRoomCategoryId());
            pstmt.setInt(2, roomReviewVO.getMemId());
            pstmt.setString(3, roomReviewVO.getCommentContent());
            pstmt.setTimestamp(4, roomReviewVO.getCommentDate());
            pstmt.setString(5, roomReviewVO.getSatisfaction());

            int i = pstmt.executeUpdate();

            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }


    }

    @Override
    public void update(RoomReviewVO roomReviewVO) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();

            pstmt = con.prepareStatement(UPDATE);


            pstmt.setInt(1, roomReviewVO.getRoomCategoryId());
            pstmt.setInt(2, roomReviewVO.getMemId());
            pstmt.setString(3, roomReviewVO.getCommentContent());
            pstmt.setTimestamp(4, roomReviewVO.getCommentDate());
            pstmt.setString(5, roomReviewVO.getSatisfaction());
            pstmt.setInt(6, roomReviewVO.getRoomReviewId());
            int i = pstmt.executeUpdate();

            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }


    }

    @Override
    public void delete(Integer roomReviewId) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, roomReviewId);

            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }


    }

    @Override
    public RoomReviewVO findByPrimaryKey(Integer roomReviewId) {

        RoomReviewVO roomReviewVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, roomReviewId);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                roomReviewVO = new RoomReviewVO();

                roomReviewVO.setRoomReviewId(rs.getInt("ROOM_REVIEW_ID"));
                roomReviewVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
                roomReviewVO.setMemId(rs.getInt("MEM_ID"));
                roomReviewVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
                roomReviewVO.setCommentDate(rs.getTimestamp("COMMENT_DATE"));
                roomReviewVO.setSatisfaction(rs.getString("SATISFACTION"));

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }


        return roomReviewVO;
    }

    @Override
    public List<RoomReviewVO> getAll() {
        ArrayList<RoomReviewVO> roomReviewList = new ArrayList<RoomReviewVO>();
        RoomReviewVO roomReviewVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomReviewVO = new RoomReviewVO();

                roomReviewVO.setRoomReviewId(rs.getInt("ROOM_REVIEW_ID"));
                roomReviewVO.setRoomCategoryId(rs.getInt("ROOM_CATEGORY_ID"));
                roomReviewVO.setMemId(rs.getInt("MEM_ID"));
                roomReviewVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
                roomReviewVO.setCommentDate(rs.getTimestamp("COMMENT_DATE"));
                roomReviewVO.setSatisfaction(rs.getString("SATISFACTION"));

                roomReviewList.add(roomReviewVO);
            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }


        return roomReviewList;
    }
}
