
package com.rmtypepic.model;



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


 public class RtpDAO
         implements RtpDAO_interface
         {
    /*  17 */   private static DataSource ds = null;

       static {
        
        try {
            /*  20 */
            Context ctx = new InitialContext();
            /*  21 */
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G2");
            /*  22 */
        } catch (NamingException e) {
            /*  23 */
            e.printStackTrace();
            
        }
        
    }

    
    
       private static final String INSERT_STMT = "INSERT INTO ROOM_TYPE_PIC (ROOM_CATEGORY_ID, ROOM_PHOTO) VALUES (?,?)";
    
       private static final String GET_ALL_STMT = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC order by ROOM_PHOTO_ID";
       private static final String GET_ONE_STMT = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?";
       private static final String DELETE = "DELETE FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?";
       private static final String UPDATE = "UPDATE ROOM_TYPE_PIC set ROOM_PHOTO_ID=?, ROOM_CATEGORY_ID=? where ROOM_PHOTO_ID = ?";

    
    
    public void insert(RtpVO rtpVO) {
        /*  36 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
        try {
            /*  41 */
            con = ds.getConnection();
            /*  42 */
            pstmt = con.prepareStatement("INSERT INTO ROOM_TYPE_PIC (ROOM_CATEGORY_ID, ROOM_PHOTO) VALUES (?,?)");
            
            /*  44 */
            pstmt.setInt(1, rtpVO.getRoomCategoryId().intValue());
            /*  45 */
            pstmt.setBytes(2, rtpVO.getRoomPhoto());
            /*  46 */
            pstmt.executeUpdate();
            
            
            
        }
        /*  50 */ catch (SQLException se) {
            /*  51 */
            throw new RuntimeException("發生錯誤: " + se.getMessage());
            
        } finally {
            /*  53 */
            if (pstmt != null) {
                
                try {
                    /*  55 */
                    pstmt.close();
                    /*  56 */
                } catch (SQLException se) {
                    /*  57 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /*  60 */
            if (con != null) {
                
                try {
                    /*  62 */
                    con.close();
                    /*  63 */
                } catch (Exception e) {
                    /*  64 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    public void update(RtpVO rtpVO) {
        /*  72 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
        try {
            /*  77 */
            con = ds.getConnection();
            /*  78 */
            pstmt = con.prepareStatement("UPDATE ROOM_TYPE_PIC set ROOM_PHOTO_ID=?, ROOM_CATEGORY_ID=? where ROOM_PHOTO_ID = ?");
            
            /*  80 */
            pstmt.setInt(1, rtpVO.getRoomPhotoId().intValue());
            /*  81 */
            pstmt.setInt(2, rtpVO.getRoomCategoryId().intValue());
            /*  82 */
            pstmt.setBytes(3, rtpVO.getRoomPhoto());
            /*  83 */
            pstmt.executeUpdate();
            
            
        }
        /*  86 */ catch (SQLException se) {
            /*  87 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /*  89 */
            if (pstmt != null) {
                
                try {
                    /*  91 */
                    pstmt.close();
                    /*  92 */
                } catch (SQLException se) {
                    /*  93 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /*  96 */
            if (con != null) {
                
                try {
                    /*  98 */
                    con.close();
                    /*  99 */
                } catch (Exception e) {
                    /* 100 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    public void delete(Integer roomPhotoId) {
        /* 109 */
        Connection con = null;
        PreparedStatement pstmt = null;
        
        
        try {
            /* 113 */
            con = ds.getConnection();
            /* 114 */
            pstmt = con.prepareStatement("DELETE FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?");
            
            /* 116 */
            pstmt.setInt(1, roomPhotoId.intValue());
            
            /* 118 */
            pstmt.executeUpdate();
            
            
        }
        /* 121 */ catch (SQLException se) {
            /* 122 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 124 */
            if (pstmt != null) {
                
                try {
                    /* 126 */
                    pstmt.close();
                    /* 127 */
                } catch (SQLException se) {
                    /* 128 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 131 */
            if (con != null) {
                
                try {
                    /* 133 */
                    con.close();
                    /* 134 */
                } catch (Exception e) {
                    /* 135 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    
    public RtpVO findByPrimaryKey(Integer roomPhotoId) {
        /* 145 */
        RtpVO rtpVO = null;
        /* 146 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        try {
            /* 151 */
            con = ds.getConnection();
            /* 152 */
            pstmt = con.prepareStatement("SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC where ROOM_PHOTO_ID = ?");
            
            /* 154 */
            pstmt.setInt(1, roomPhotoId.intValue());
            
            /* 156 */
            rs = pstmt.executeQuery();
            
            /* 158 */
            while (rs.next())
                 {
                /* 160 */
                rtpVO = new RtpVO();
                /* 161 */
                rtpVO.setRoomPhotoId(Integer.valueOf(rs.getInt("ROOM_PHOTO_ID")));
                /* 162 */
                rtpVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 163 */
                rtpVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
                
            }
            
            /* 166 */
        } catch (SQLException se) {
            /* 167 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 169 */
            if (rs != null) {
                
                try {
                    /* 171 */
                    rs.close();
                    /* 172 */
                } catch (SQLException se) {
                    /* 173 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 176 */
            if (pstmt != null) {
                
                try {
                    /* 178 */
                    pstmt.close();
                    /* 179 */
                } catch (SQLException se) {
                    /* 180 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 183 */
            if (con != null) {
                
                try {
                    /* 185 */
                    con.close();
                    /* 186 */
                } catch (Exception e) {
                    /* 187 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 191 */
        return rtpVO;
        
    }

    
    
    
    public List<RtpVO> getAll() {
        /* 196 */
        List<RtpVO> list = new ArrayList<RtpVO>();
        /* 197 */
        RtpVO rtpVO = null;
        
        /* 199 */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        try {
            /* 204 */
            con = ds.getConnection();
            /* 205 */
            pstmt = con.prepareStatement("SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, ROOM_PHOTO FROM ROOM_TYPE_PIC order by ROOM_PHOTO_ID");
            /* 206 */
            rs = pstmt.executeQuery();
            
            /* 208 */
            while (rs.next())
                 {
                /* 210 */
                rtpVO = new RtpVO();
                /* 211 */
                rtpVO.setRoomPhotoId(Integer.valueOf(rs.getInt("ROOM_PHOTO_ID")));
                /* 212 */
                rtpVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
                /* 213 */
                rtpVO.setRoomPhoto(rs.getBytes("ROOM_PHOTO"));
                
                
                /* 216 */
                list.add(rtpVO);
                
            }
            
            
        }
        /* 220 */ catch (SQLException se) {
            /* 221 */
            throw new RuntimeException("A database error occured. " + se.getMessage());
            
        } finally {
            /* 223 */
            if (rs != null) {
                
                try {
                    /* 225 */
                    rs.close();
                    /* 226 */
                } catch (SQLException se) {
                    /* 227 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 230 */
            if (pstmt != null) {
                
                try {
                    /* 232 */
                    pstmt.close();
                    /* 233 */
                } catch (SQLException se) {
                    /* 234 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            /* 237 */
            if (con != null) {
                
                try {
                    /* 239 */
                    con.close();
                    /* 240 */
                } catch (Exception e) {
                    /* 241 */
                    e.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        /* 245 */
        return list;
        
    }

    
    
    
    
    
    public void insert2(RtpVO rtpVO, Connection con) {
        /* 252 */
        Connection conn = null;
        /* 253 */
        PreparedStatement pstmt = null;
        
        
        
        try {
            /* 257 */
            conn = con;
            /* 258 */
            pstmt = conn.prepareStatement("INSERT INTO ROOM_TYPE_PIC (ROOM_CATEGORY_ID, ROOM_PHOTO) VALUES (?,?)");
            
            /* 260 */
            pstmt.setInt(1, rtpVO.getRoomCategoryId().intValue());
            /* 261 */
            pstmt.setBytes(2, rtpVO.getRoomPhoto());
            /* 262 */
            pstmt.executeUpdate();
            
        }
        /* 264 */ catch (SQLException se) {
            /* 265 */
            if (con != null) {
                
                
                try {
                    /* 268 */
                    System.err.print("交易中失敗 ");
                    /* 269 */
                    System.err.println("rolled back-由-Rtp");
                    /* 270 */
                    con.rollback();
                    /* 271 */
                } catch (SQLException excep) {
                    /* 272 */
                    throw new RuntimeException("rollback 失敗. " + excep
/* 273 */.getMessage());
                    
                }
                
            }
            /* 276 */
            throw new RuntimeException("發生錯誤. " + se
/* 277 */.getMessage());
            
        } finally {
            
            /* 280 */
            if (pstmt != null) {
                
                try {
                    /* 282 */
                    pstmt.close();
                    /* 283 */
                } catch (SQLException se) {
                    /* 284 */
                    se.printStackTrace(System.err);
                    
                }
                
            }
            
        }
        
    }

    
    
    
    
    /* 293 */
    public List<RtpVO> getAllByRcId(Integer roomCategoryId) {
        return null;
    }

    
    
    
    
    /* 298 */
    public RtpVO findByRoomCategoryId(Integer roomCategoryId) {
        return null;
    }
    
}


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtypepic/model/RtpDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */