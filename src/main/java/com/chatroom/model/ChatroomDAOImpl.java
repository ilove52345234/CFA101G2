package com.chatroom.model;

import com.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatroomDAOImpl implements ChatroomDAO {
    JDBCUtils jdbcUtils = new JDBCUtils();


    private static final String INSERT_STMT =
            "insert into CHATROOM(CHAT_NAME, CHAT_MAX, CHAT_STATUS)value (?,?,?)";
    private static final String GET_ONE_STMT =
            "select CHAT_PLACE_ID, CHAT_NAME, CHAT_MAX, CHAT_STATUS from CHATROOM where CHAT_PLACE_ID=?";
    private static final String GET_ALL_STMT =
            "select CHAT_PLACE_ID, CHAT_NAME, CHAT_MAX, CHAT_STATUS from CHATROOM order by CHAT_PLACE_ID";
    private static final String DELETE =
            "delete from CHATROOM where CHAT_PLACE_ID=?";
    private static final String UPDATE =
            "update CHATROOM set CHAT_NAME=?, CHAT_MAX=?, CHAT_STATUS=? where CHAT_PLACE_ID=?";


    @Override
    public void insert(ChatroomVO chatroomVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1,chatroomVO.getChatName());
            pstmt.setInt(2,chatroomVO.getChatMax());
            pstmt.setInt(3,chatroomVO.getChatStatus());


            int i = pstmt.executeUpdate();

            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public void update(ChatroomVO chatroomVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1,chatroomVO.getChatName());
            pstmt.setInt(2,chatroomVO.getChatMax());
            pstmt.setInt(3,chatroomVO.getChatStatus());
            pstmt.setInt(4,chatroomVO.getChatPlaceId());

            int i = pstmt.executeUpdate();

            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void delete(Integer chatPlaceId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, chatPlaceId);

            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public ChatroomVO findByPrimaryKey(Integer chatPlaceId) {
        ChatroomVO chatroomVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1,chatPlaceId);
            rs = pstmt.executeQuery();

            while (rs.next()){
                chatroomVO = new ChatroomVO();

                chatroomVO.setChatPlaceId(rs.getInt("CHAT_PLACE_ID"));
                chatroomVO.setChatName(rs.getString("CHAT_NAME"));
                chatroomVO.setChatMax(rs.getInt("CHAT_MAX"));
                chatroomVO.setChatStatus(rs.getInt("CHAT_STATUS"));


            }




        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }

        return chatroomVO;
    }

    @Override
    public List<ChatroomVO> getAll() {
        ArrayList<ChatroomVO> chatroomVOList = new ArrayList<ChatroomVO>();

        ChatroomVO chatroomVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chatroomVO = new ChatroomVO();

                chatroomVO.setChatPlaceId(rs.getInt("CHAT_PLACE_ID"));
                chatroomVO.setChatName(rs.getString("CHAT_NAME"));
                chatroomVO.setChatMax(rs.getInt("CHAT_MAX"));
                chatroomVO.setChatStatus(rs.getInt("CHAT_STATUS"));

                chatroomVOList.add(chatroomVO);
            }

        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }

        return chatroomVOList;
    }
}
