
package com.rmtype.model;



import com.utils.Base64VO;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import javax.naming.Context;
 import javax.naming.InitialContext;
 import javax.naming.NamingException;
 import javax.sql.DataSource;





 public class RtDAO implements RtDAO_interface {
    /*  22 */   private static DataSource ds = null;

       static {
        
        try {
            /*  25 */
            Context ctx = new InitialContext();
            /*  26 */
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
            /*  27 */
        } catch (NamingException e) {
            /*  28 */
            e.printStackTrace();
            
        }
        
    }

    
    
    
       private static final String INSERT_STMT = "INSERT INTO ROOM_TYPE (ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE) VALUES (?,?,?,?,?,?,?)";
    
    
       private static final String GET_ALL_STMT = "SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE order by ROOM_CATEGORY_ID";
    
       private static final String GET_ONE_STMT = "SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?";
    
       private static final String DELETE = "DELETE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?";
    
       private static final String UPDATE = "UPDATE ROOM_TYPE set ROOM_TYPE_AMOUNT=?, ROOM_TYPE_CONTENT=?, ROOM_SALE_STATUS=?, ROOM_TOTAL_PERSON=?, ROOM_TOTAL_SCORE=?, ROOM_NAME=?, ROOM_PRICE=? where ROOM_CATEGORY_ID = ?";
    
       private static final String FIND_ROOM = "SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE WHERE ROOM_NAME= ? ";
       private static final String CALL_BatchInsert= "CALL BatchInsert(?, ?, ?, ?, ?)";

    
    
    
    public void insert(RtVO rtVO) {
        /*  49 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        try {
            /*  53 */
            con = ds.getConnection();
            /*  54 */
            pstmt = con.prepareStatement("INSERT INTO ROOM_TYPE (ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE) VALUES (?,?,?,?,?,?,?)");
            
            /*  56 */
            pstmt.setInt(1, rtVO.getRoomTypeAmount().intValue());
            /*  57 */
            pstmt.setString(2, rtVO.getRoomTypeContent());
            /*  58 */
            pstmt.setByte(3, rtVO.getRoomSaleStatus().byteValue());
            /*  59 */
            pstmt.setInt(4, rtVO.getRoomTotalPerson().intValue());
            /*  60 */
            pstmt.setInt(5, rtVO.getRoomTotalScore().intValue());
            /*  61 */
            pstmt.setString(6, rtVO.getRoomName());
            /*  62 */
            pstmt.setInt(7, rtVO.getRoomPrice().intValue());
            
            /*  64 */
            pstmt.executeUpdate();
            /*  65 */
        } catch (SQLException se) {
            /*  66 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /*  68 */
            if (pstmt != null) {
                
                try {
                    /*  70 */
                    pstmt.close();
                    /*  71 */
                } catch (SQLException se) {
                    /*  72 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /*  75 */
            if (con != null) {
                
                try {
                    /*  77 */
                    con.close();
                    /*  78 */
                } catch (Exception e) {
                    /*  79 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    public void update(RtVO rtVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        try {
            /*  92 */
            con = ds.getConnection();
            /*  93 */
            pstmt = con.prepareStatement("UPDATE ROOM_TYPE set ROOM_TYPE_AMOUNT=?, ROOM_TYPE_CONTENT=?, ROOM_SALE_STATUS=?, ROOM_TOTAL_PERSON=?, ROOM_TOTAL_SCORE=?, ROOM_NAME=?, ROOM_PRICE=? where ROOM_CATEGORY_ID = ?");
            
            /*  95 */
            pstmt.setInt(1, rtVO.getRoomTypeAmount().intValue());
            /*  96 */
            pstmt.setString(2, rtVO.getRoomTypeContent());
            /*  97 */
            pstmt.setByte(3, rtVO.getRoomSaleStatus().byteValue());
            /*  98 */
            pstmt.setInt(4, rtVO.getRoomTotalPerson().intValue());
            /*  99 */
            pstmt.setInt(5, rtVO.getRoomTotalScore().intValue());
            /* 100 */
            pstmt.setString(6, rtVO.getRoomName());
            /* 101 */
            pstmt.setInt(7, rtVO.getRoomPrice().intValue());
            /* 102 */
            pstmt.setInt(8, rtVO.getRoomCategoryId().intValue());
            
            
            /* 105 */
            pstmt.executeUpdate();
            /* 106 */
        } catch (SQLException se) {
            /* 107 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 109 */
            if (pstmt != null) {
                
                try {
                    /* 111 */
                    pstmt.close();
                    /* 112 */
                } catch (SQLException se) {
                    /* 113 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 116 */
            if (con != null) {
                
                try {
                    /* 118 */
                    con.close();
                    /* 119 */
                } catch (Exception e) {
                    /* 120 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    public void delete(Integer roomCategoryId) {
        /* 129 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        try {
            /* 133 */
            con = ds.getConnection();
            /* 134 */
            pstmt = con.prepareStatement("DELETE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?");
            
            /* 136 */
            pstmt.setInt(1, roomCategoryId.intValue());
            
            /* 138 */
            pstmt.executeUpdate();
            /* 139 */
        } catch (SQLException se) {
            /* 140 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 142 */
            if (pstmt != null) {
                
                try {
                    /* 144 */
                    pstmt.close();
                    /* 145 */
                } catch (SQLException se) {
                    /* 146 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 149 */
            if (con != null) {
                
                try {
                    /* 151 */
                    con.close();
                    /* 152 */
                } catch (Exception e) {
                    /* 153 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    public RtVO findByPrimaryKey(Integer roomCategoryId) {
        /* 162 */
        RtVO rtVO = null;
        /* 163 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        try {
            /* 168 */
            con = ds.getConnection();
            /* 169 */
            pstmt = con.prepareStatement("SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?");
            
            /* 171 */
            pstmt.setInt(1, roomCategoryId.intValue());
            
            /* 173 */
            rs = pstmt.executeQuery();
            
            /* 175 */
            while (rs.next()) {
                /* 176 */
                rtVO = new RtVO();
                /* 177 */
                rtVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 178 */
                rtVO.setRoomTypeAmount(Integer.valueOf(rs.getInt("ROOM_TYPE_AMOUNT")));
                /* 179 */
                rtVO.setRoomTypeContent(rs.getString("ROOM_TYPE_CONTENT"));
                /* 180 */
                rtVO.setRoomSaleStatus(Byte.valueOf(rs.getByte("ROOM_SALE_STATUS")));
                /* 181 */
                rtVO.setRoomTotalPerson(Integer.valueOf(rs.getInt("ROOM_TOTAL_PERSON")));
                /* 182 */
                rtVO.setRoomTotalScore(Integer.valueOf(rs.getInt("ROOM_TOTAL_SCORE")));
                /* 183 */
                rtVO.setRoomName(rs.getString("ROOM_NAME"));
                /* 184 */
                rtVO.setRoomPrice(Integer.valueOf(rs.getInt("ROOM_PRICE")));
                
            }
            
            /* 187 */
        } catch (SQLException se) {
            /* 188 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 190 */
            if (rs != null) {
                
                try {
                    /* 192 */
                    rs.close();
                    /* 193 */
                } catch (SQLException se) {
                    /* 194 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 197 */
            if (pstmt != null) {
                
                try {
                    /* 199 */
                    pstmt.close();
                    /* 200 */
                } catch (SQLException se) {
                    /* 201 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 204 */
            if (con != null) {
                
                try {
                    /* 206 */
                    con.close();
                    /* 207 */
                } catch (Exception e) {
                    /* 208 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 212 */
        return rtVO;
        
    }

    
    
    
    public List<RtVO> getAll() {
        /* 217 */
        List<RtVO> list = new ArrayList<RtVO>();
        /* 218 */
        RtVO rtVO = null;
        
        /* 220 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        
        try {
            /* 226 */
            con = ds.getConnection();
            /* 227 */
            pstmt = con.prepareStatement("SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE order by ROOM_CATEGORY_ID");
            /* 228 */
            rs = pstmt.executeQuery();
            
            /* 230 */
            while (rs.next())
                 {
                /* 232 */
                rtVO = new RtVO();
                /* 233 */
                rtVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 234 */
                rtVO.setRoomTypeAmount(Integer.valueOf(rs.getInt("ROOM_TYPE_AMOUNT")));
                /* 235 */
                rtVO.setRoomTypeContent(rs.getString("ROOM_TYPE_CONTENT"));
                /* 236 */
                rtVO.setRoomSaleStatus(Byte.valueOf(rs.getByte("ROOM_SALE_STATUS")));
                /* 237 */
                rtVO.setRoomTotalPerson(Integer.valueOf(rs.getInt("ROOM_TOTAL_PERSON")));
                /* 238 */
                rtVO.setRoomTotalScore(Integer.valueOf(rs.getInt("ROOM_TOTAL_SCORE")));
                /* 239 */
                rtVO.setRoomName(rs.getString("ROOM_NAME"));
                /* 240 */
                rtVO.setRoomPrice(Integer.valueOf(rs.getInt("ROOM_PRICE")));
                /* 241 */
                list.add(rtVO);
                
            }
            
            /* 244 */
        } catch (SQLException se) {
            /* 245 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 247 */
            if (rs != null) {
                
                try {
                    /* 249 */
                    rs.close();
                    /* 250 */
                } catch (SQLException se) {
                    /* 251 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 254 */
            if (pstmt != null) {
                
                try {
                    /* 256 */
                    pstmt.close();
                    /* 257 */
                } catch (SQLException se) {
                    /* 258 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 261 */
            if (con != null) {
                
                try {
                    /* 263 */
                    con.close();
                    /* 264 */
                } catch (Exception e) {
                    /* 265 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 269 */
        return list;
        
    }

    
    
    
    public RtVO findRoom(String roomName) {
        /* 274 */
        RtVO rtVO = null;
        /* 275 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        try {
            /* 280 */
            con = ds.getConnection();
            /* 281 */
            pstmt = con.prepareStatement("SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE WHERE ROOM_NAME= ? ");
            
            /* 283 */
            pstmt.setString(1, roomName);
            
            /* 285 */
            rs = pstmt.executeQuery();
            
            /* 287 */
            while (rs.next()) {
                /* 288 */
                rtVO = new RtVO();
                /* 289 */
                rtVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 290 */
                rtVO.setRoomTypeAmount(Integer.valueOf(rs.getInt("ROOM_TYPE_AMOUNT")));
                /* 291 */
                rtVO.setRoomTypeContent(rs.getString("ROOM_TYPE_CONTENT"));
                /* 292 */
                rtVO.setRoomSaleStatus(Byte.valueOf(rs.getByte("ROOM_SALE_STATUS")));
                /* 293 */
                rtVO.setRoomTotalPerson(Integer.valueOf(rs.getInt("ROOM_TOTAL_PERSON")));
                /* 294 */
                rtVO.setRoomTotalScore(Integer.valueOf(rs.getInt("ROOM_TOTAL_SCORE")));
                /* 295 */
                rtVO.setRoomName(rs.getString("ROOM_NAME"));
                /* 296 */
                rtVO.setRoomPrice(Integer.valueOf(rs.getInt("ROOM_PRICE")));
                
            }
            
            /* 299 */
        } catch (SQLException se) {
            /* 300 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 302 */
            if (rs != null) {
                
                try {
                    /* 304 */
                    rs.close();
                    /* 305 */
                } catch (SQLException se) {
                    /* 306 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 309 */
            if (pstmt != null) {
                
                try {
                    /* 311 */
                    pstmt.close();
                    /* 312 */
                } catch (SQLException se) {
                    /* 313 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 316 */
            if (con != null) {
                
                try {
                    /* 318 */
                    con.close();
                    /* 319 */
                } catch (Exception e) {
                    /* 320 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 324 */
        return rtVO;
        
    }

    
    
    
    
    
    public void insertWithRtAndPic(RtVO rtVO, List<Base64VO> list) {
    }

    
    
    
    
    
    public void updateByNormal(RtVO rtVO) {
    }


    
    
    
    
    /* 340 */
    public int findTotalCount(Map<String, String> condition) {
        return 0;
    }

    
    
    
    
    /* 345 */
    public List<RtVO> findByPage(int start, int rows, Map<String, String> condition) {
        return null;
    }

     @Override
     public void updateCount(Integer inint, String date, Integer rtpid, Integer loop, Integer amount,Connection con) {

     }

 }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/model/RtDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */