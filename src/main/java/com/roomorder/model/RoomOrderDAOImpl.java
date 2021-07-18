package com.roomorder.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomOrderDAOImpl implements RoomOrderDAO {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
    String userid = "CFA101G2";
    String passwd = "123456";


    private static final String INSERT_STMT =
            "INSERT INTO ROOM_ORDER(MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE)value (?,?,?,?)";
    private static final String GET_ALL_STMT =
            "select ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE from ROOM_ORDER order by ROOM_ORDER_ID";
    private static final String GET_ONE_STMT =
            "select ROOM_ORDER_ID, MEM_ID, ORDER_DATE, ROOM_ORDER_STATUS, TOTAL_PRICE from ROOM_ORDER where ROOM_ORDER_ID =?";
    private static final String DELETE =
            "DELETE FROM ROOM_ORDER where ROOM_ORDER_ID =?";
    private static final String UPDATE =
            "update ROOM_ORDER set MEM_ID=?,ORDER_DATE=?,ROOM_ORDER_STATUS=?,TOTAL_PRICE=? where ROOM_ORDER_ID =?";


    @Override
    public void insert(RoomOrderVO roomOrderVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, roomOrderVO.getMemId());
            pstmt.setTimestamp(2, roomOrderVO.getOrderDate());
            pstmt.setInt(3, roomOrderVO.getRoomOrderStatus());
            pstmt.setInt(4, roomOrderVO.getTotalPrice());

            int i = pstmt.executeUpdate();

            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驅動載入錯誤 "
                    + e.getMessage());
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
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }

            }
        }


    }

    @Override
    public void update(RoomOrderVO roomOrderVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            pstmt = con.prepareStatement(UPDATE);
            pstmt.setInt(1, roomOrderVO.getMemId());
            pstmt.setTimestamp(2, roomOrderVO.getOrderDate());
            pstmt.setInt(3, roomOrderVO.getRoomOrderStatus());
            pstmt.setInt(4, roomOrderVO.getTotalPrice());
            pstmt.setInt(5, roomOrderVO.getRoomOrderId());

            int i = pstmt.executeUpdate();

            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驅動載入錯誤 "
                    + e.getMessage());
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
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }

            }
        }


    }

    @Override
    public void delete(Integer roomOrderId) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, roomOrderId);

            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驅動載入錯誤 "
                    + e.getMessage());
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
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }

            }
        }

    }

    @Override
    public RoomOrderVO findByPrimaryKey(Integer roomOrderId) {
        RoomOrderVO roomOrderVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, roomOrderId);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
                roomOrderVO.setMemId(rs.getInt("MEM_ID"));
                roomOrderVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
                roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
                roomOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驅動載入錯誤 "
                    + e.getMessage());
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
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }

            }
        }
        return roomOrderVO;
    }

    @Override
    public List<RoomOrderVO> getAll() {
        ArrayList<RoomOrderVO> roomOrderList = new ArrayList<RoomOrderVO>();

        RoomOrderVO roomOrderVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
                roomOrderVO.setMemId(rs.getInt("MEM_ID"));
                roomOrderVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
                roomOrderVO.setRoomOrderStatus(rs.getInt("ROOM_ORDER_STATUS"));
                roomOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));

                roomOrderList.add(roomOrderVO);

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驅動載入錯誤 "
                    + e.getMessage());
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
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }

            }
        }
        return roomOrderList;
    }


}
