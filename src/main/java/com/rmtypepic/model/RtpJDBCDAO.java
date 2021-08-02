
package com.rmtypepic.model;



import com.utils.JDBCUtils;

import java.io.FileInputStream;
 import java.io.IOException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;


 public class RtpJDBCDAO
         implements RtpDAO_interface
         {
//    /*  16 */ String driver = "com.mysql.cj.jdbc.Driver";
//    /*  17 */ String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
//    /*  18 */ String userid = "CFA101G2";
//    /*  19 */ String passwd = "A123456";

    JDBCUtils jdbcUtils = new JDBCUtils();

    
       private static final String INSERT_STMT = "INSERT INTO ROOM_TYPE_PIC (ROOM_CATEGORY_ID, ROOM_PHOTO) VALUES (?,?)";
    
       private static final String GET_ALL_STMT = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC order by ROOM_PHOTO_ID";
    
       private static final String GET_ONE_STMT = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?";
       private static final String DELETE = "DELETE FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?";
       private static final String UPDATE = "UPDATE ROOM_TYPE_PIC set ROOM_PHOTO_ID=?, ROOM_CATEGORY_ID=? where ROOM_PHOTO_ID = ?";
       private static final String GET_ONE_ALL_STMT = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_CATEGORY_ID = ?";
       private static final String GET_ONE_CATEGORY_ID_STMT = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_CATEGORY_ID = ? limit 1";

    
    
    public void insert(RtpVO rtpVO) {
        /*  32 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
        try {
//			/*  37 */       Class.forName(this.driver);
//			/*  38 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
            /*  39 */
            con = jdbcUtils.getConnection();

            pstmt = con.prepareStatement("INSERT INTO ROOM_TYPE_PIC (ROOM_CATEGORY_ID, ROOM_PHOTO) VALUES (?,?)");
            
            /*  41 */
            pstmt.setInt(1, rtpVO.getRoomCategoryId().intValue());
            /*  42 */
            pstmt.setBytes(2, rtpVO.getRoomPhoto());
            /*  43 */
            pstmt.executeUpdate();
            
            /*  45 */
            /*  46 */
            
        }
        /*  48 */ catch (SQLException se) {
            /*  49 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /*  51 */
            if (pstmt != null) {
                
                try {
                    /*  53 */
                    pstmt.close();
                    /*  54 */
                } catch (SQLException se) {
                    /*  55 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /*  58 */
            if (con != null) {
                
                try {
                    /*  60 */
                    con.close();
                    /*  61 */
                } catch (Exception e) {
                    /*  62 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    public void update(RtpVO rtpVO) {
        /*  70 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
        try {
            /*  75 */
//            Class.forName(this.driver);
//            /*  76 */
//            con = DriverManager.getConnection(this.url, this.userid, this.passwd);
//            /*  77 */

            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement("UPDATE ROOM_TYPE_PIC set ROOM_PHOTO_ID=?, ROOM_CATEGORY_ID=? where ROOM_PHOTO_ID = ?");
            
            /*  79 */
            pstmt.setInt(1, rtpVO.getRoomPhotoId().intValue());
            /*  80 */
            pstmt.setInt(2, rtpVO.getRoomCategoryId().intValue());
            /*  81 */
            pstmt.setBytes(3, rtpVO.getRoomPhoto());
            /*  82 */
            pstmt.executeUpdate();
            

            /*  84 */
            /*  85 */
            
        }
        /*  87 */ catch (
                SQLException se) {
            /*  88 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /*  90 */
            if (pstmt != null) {
                
                try {
                    /*  92 */
                    pstmt.close();
                    /*  93 */
                } catch (SQLException se) {
                    /*  94 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /*  97 */
            if (con != null) {
                
                try {
                    /*  99 */
                    con.close();
                    /* 100 */
                } catch (Exception e) {
                    /* 101 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    public void delete(Integer roomPhotoId) {
        /* 110 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        try {
            /* 114 */
//            Class.forName(this.driver);
//            /* 115 */
//            con = DriverManager.getConnection(this.url, this.userid, this.passwd);

            con = jdbcUtils.getConnection();
            /* 116 */
            pstmt = con.prepareStatement("DELETE FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?");
            
            /* 118 */
            pstmt.setInt(1, roomPhotoId.intValue());
            
            /* 120 */
            pstmt.executeUpdate();
            

            /* 122 */
            /* 123 */
            /* 124 */
        } catch (SQLException se) {
            /* 125 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 127 */
            if (pstmt != null) {
                
                try {
                    /* 129 */
                    pstmt.close();
                    /* 130 */
                } catch (SQLException se) {
                    /* 131 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 134 */
            if (con != null) {
                
                try {
                    /* 136 */
                    con.close();
                    /* 137 */
                } catch (Exception e) {
                    /* 138 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    public RtpVO findByPrimaryKey(Integer roomPhotoId) {
        /* 147 */
        RtpVO rtpVO = null;
        /* 148 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
//        /* 153 */       Class.forName(this.driver);
//        /* 154 */       con=DriverManager.getConnection(this.url,this.userid,this.passwd);
            /* 155 */
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement("SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?");
            
            /* 157 */
            pstmt.setInt(1, roomPhotoId.intValue());
            
            /* 159 */
            rs = pstmt.executeQuery();
            
            /* 161 */
            while (rs.next())
                 {
                /* 163 */
                rtpVO = new RtpVO();
                /* 164 */
                rtpVO.setRoomPhotoId(Integer.valueOf(rs.getInt("ROOM_PHOTO_ID")));
                /* 165 */
                rtpVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 166 */
                rtpVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
                
            }
            
            
            /* 170 */
            /* 171 */
            
        }
        /* 173 */ catch (SQLException se) {
            /* 174 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 176 */
            if (rs != null) {
                
                try {
                    /* 178 */
                    rs.close();
                    /* 179 */
                } catch (SQLException se) {
                    /* 180 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 183 */
            if (pstmt != null) {
                
                try {
                    /* 185 */
                    pstmt.close();
                    /* 186 */
                } catch (SQLException se) {
                    /* 187 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 190 */
            if (con != null) {
                
                try {
                    /* 192 */
                    con.close();
                    /* 193 */
                } catch (Exception e) {
                    /* 194 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 198 */
        return rtpVO;
        
    }

    
    
    
    public List<RtpVO> getAll() {
        /* 203 */
        List<RtpVO> list = new ArrayList<RtpVO>();
        /* 204 */
        RtpVO rtpVO = null;
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        
        try {
//        /* 212 */       Class.forName(this.driver);
//        /* 213 */       con=DriverManager.getConnection(this.url,this.userid,this.passwd);
            /* 214 */
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement("SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC order by ROOM_PHOTO_ID");
            /* 215 */
            rs = pstmt.executeQuery();
            
            /* 217 */
            while (rs.next())
                 {
                /* 219 */
                rtpVO = new RtpVO();
                /* 220 */
                rtpVO.setRoomPhotoId(Integer.valueOf(rs.getInt("ROOM_PHOTO_ID")));
                /* 221 */
                rtpVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 222 */
                rtpVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
                
                
                /* 225 */
                list.add(rtpVO);
                
            }
            
            /* 228 */
            /* 229 */
            
        }
        /* 231 */ catch (SQLException se) {
            /* 232 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 234 */
            if (rs != null) {
                
                try {
                    /* 236 */
                    rs.close();
                    /* 237 */
                } catch (SQLException se) {
                    /* 238 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 241 */
            if (pstmt != null) {
                
                try {
                    /* 243 */
                    pstmt.close();
                    /* 244 */
                } catch (SQLException se) {
                    /* 245 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 248 */
            if (con != null) {
                
                try {
                    /* 250 */
                    con.close();
                    /* 251 */
                } catch (Exception e) {
                    /* 252 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 256 */
        return list;
        
    }

    
    
    
    public void insert2(RtpVO rtpVO, Connection con) {
        /* 261 */
        Connection conn = null;

        PreparedStatement pstmt = null;
        
        
        try {
            /* 266 */
            conn = con;
            /* 267 */
            pstmt = conn.prepareStatement("INSERT INTO ROOM_TYPE_PIC (ROOM_CATEGORY_ID, ROOM_PHOTO) VALUES (?,?)");
            
            /* 269 */
            pstmt.setInt(1, rtpVO.getRoomCategoryId().intValue());
            /* 270 */
            pstmt.setBytes(2, rtpVO.getRoomPhoto());
            /* 271 */
            pstmt.executeUpdate();
            
        }
        /* 273 */ catch (SQLException se) {
            /* 274 */
            if (con != null) {
                
                
                try {
                    /* 277 */
                    System.err.print("交易中失敗 ");
                    /* 278 */
                    System.err.println("rolled back-由-Rtp");
                    /* 279 */
                    con.rollback();
                    /* 280 */
                } catch (SQLException excep) {
                    /* 281 */
                    throw new RuntimeException("rollback 失敗. " + excep
                            /* 282 */.getMessage());
                    
                }
                
            }
            /* 285 */
            throw new RuntimeException("發生錯誤. " + se
                    /* 286 */.getMessage());
            
        } finally {
            
            /* 289 */
            if (pstmt != null) {
                
                try {
                    /* 291 */
                    pstmt.close();
                    /* 292 */
                } catch (SQLException se) {
                    /* 293 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    public static byte[] getPictureByteArray(String path) throws IOException {
        /* 301 */
        FileInputStream fis = new FileInputStream(path);
        /* 302 */
        byte[] buffer = new byte[fis.available()];
        /* 303 */
        fis.read(buffer);
        /* 304 */
        fis.close();
        /* 305 */
        return buffer;
        
    }

    
    
    
    
    public List<RtpVO> getAllByRcId(Integer roomCategoryId) {
        /* 311 */
        List<RtpVO> list = new ArrayList<RtpVO>();
        /* 312 */
        RtpVO rtpVO = null;
        
        /* 314 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        try {
//        /* 320 */       Class.forName(this.driver);
//        /* 321 */       con=DriverManager.getConnection(this.url,this.userid,this.passwd);
            /* 322 */
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement("SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_CATEGORY_ID = ?");
            /* 323 */
            pstmt.setInt(1, roomCategoryId.intValue());
            /* 324 */
            rs = pstmt.executeQuery();
            
            /* 326 */
            while (rs.next()) {
                /* 327 */
                rtpVO = new RtpVO();
                /* 328 */
                rtpVO.setRoomPhotoId(Integer.valueOf(rs.getInt("ROOM_PHOTO_ID")));
                /* 329 */
                rtpVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 330 */
                rtpVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
                
                
                /* 333 */
                list.add(rtpVO);
                
            }
            
            /* 336 */
            /* 337 */
            
        }
        /* 339 */ catch (SQLException se) {
            /* 340 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 342 */
            if (rs != null) {
                
                try {
                    /* 344 */
                    rs.close();
                    /* 345 */
                } catch (SQLException se) {
                    /* 346 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 349 */
            if (pstmt != null) {
                
                try {
                    /* 351 */
                    pstmt.close();
                    /* 352 */
                } catch (SQLException se) {
                    /* 353 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 356 */
            if (con != null) {
                
                try {
                    /* 358 */
                    con.close();
                    /* 359 */
                } catch (Exception e) {
                    /* 360 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 364 */
        return list;
        
    }

    
    
    
    
    public RtpVO findByRoomCategoryId(Integer roomCategoryId) {
        /* 370 */
        RtpVO rtpVO = null;
        
        /* 372 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
//        /* 376 */       Class.forName(this.driver);
//        /* 377 */       con=DriverManager.getConnection(this.url,this.userid,this.passwd);
            /* 378 */
            con = jdbcUtils.getConnection();

            pstmt = con.prepareStatement("SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_CATEGORY_ID = ? limit 1");
            /* 379 */
            pstmt.setInt(1, roomCategoryId.intValue());
            /* 380 */
            rs = pstmt.executeQuery();
            /* 381 */
            while (rs.next()) {
                /* 382 */
                rtpVO = new RtpVO();
                /* 383 */
                rtpVO.setRoomPhotoId(Integer.valueOf(rs.getInt("ROOM_PHOTO_ID")));
                /* 384 */
                rtpVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 385 */
                rtpVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
                
            }
            
            /* 388 */
            /* 389 */
            
        }
        /* 391 */ catch (SQLException se) {
            /* 392 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 394 */
            if (rs != null) {
                
                try {
                    /* 396 */
                    rs.close();
                    /* 397 */
                } catch (SQLException se) {
                    /* 398 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 401 */
            if (pstmt != null) {
                
                try {
                    /* 403 */
                    pstmt.close();
                    /* 404 */
                } catch (SQLException se) {
                    /* 405 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 408 */
            if (con != null) {
                
                try {
                    /* 410 */
                    con.close();
                    /* 411 */
                } catch (Exception e) {
                    /* 412 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 416 */
        return rtpVO;
        
    }
    
    
    
    
    
    
    
    
}


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtypepic/model/RtpJDBCDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */