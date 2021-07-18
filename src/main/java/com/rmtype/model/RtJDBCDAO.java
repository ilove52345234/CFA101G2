/*     */ package com.rmtype.model;
/*     */ import com.rmtypepic.model.RtpService;
/*     */ import com.utils.Base64VO;
/*     */ import com.utils.JDBCUtils;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Base64;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.jdbc.core.BeanPropertyRowMapper;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */
/*     */ public class RtJDBCDAO implements RtDAO_interface {
	/*  19 */   JDBCUtils jdbcUtils = new JDBCUtils();
	/*     */
	/*  21 */   String driver = "com.mysql.cj.jdbc.Driver";
	/*  22 */   String url = "jdbc:mysql://35.221.136.103:3306/CFA101G2?serverTimezone=Asia/Taipei";
	/*  23 */   String userid = "CFA101G2";
	/*  24 */   String passwd = "123456";
	/*     */
	/*     */
	/*     */   private static final String INSERT_STMT = "INSERT INTO ROOM_TYPE (ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE) VALUES (?,?,?,?,?,?,?)";
	/*     */
	/*     */
	/*     */   private static final String GET_ALL_STMT = "SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE FROM ROOM_TYPE order by ROOM_CATEGORY_ID";
	/*     */
	/*     */
	/*     */   private static final String GET_ONE_STMT = "SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?";
	/*     */
	/*     */
	/*     */   private static final String DELETE = "DELETE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?";
	/*     */
	/*     */
	/*     */   private static final String UPDATE = "UPDATE ROOM_TYPE set ROOM_TYPE_AMOUNT=?, ROOM_TYPE_CONTENT=?, ROOM_SALE_STATUS=?, ROOM_TOTAL_PERSON=?, ROOM_TOTAL_SCORE=?, ROOM_NAME=?, ROOM_PRICE=?  where ROOM_CATEGORY_ID = ?";
	/*     */
	/*     */
	/*     */   private static final String FIND_ROOM = "SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE WHERE ROOM_NAME= ?";
	/*     */
	/*     */
	/*     */   private static final String UPDATE_NORMAL = "UPDATE ROOM_TYPE set ROOM_TYPE_AMOUNT=?, ROOM_TYPE_CONTENT=?, ROOM_SALE_STATUS=?, ROOM_NAME=?, ROOM_PRICE=?  where ROOM_CATEGORY_ID = ?";
	/*     */
	/*     */
	/*     */   public void insert(RtVO rtVO) {
		/*  49 */   Connection con = null;
		PreparedStatement pstmt = null;
		/*     */
		/*     */
		/*     */     try {
			/*  54 */       Class.forName(this.driver);
			/*  55 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/*  56 */       pstmt = con.prepareStatement("INSERT INTO ROOM_TYPE (ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE) VALUES (?,?,?,?,?,?,?)");
			/*     */
			/*     */
			/*  59 */       pstmt.setInt(1, rtVO.getRoomTypeAmount().intValue());
			/*  60 */       pstmt.setString(2, rtVO.getRoomTypeContent());
			/*  61 */       pstmt.setByte(3, rtVO.getRoomSaleStatus().byteValue());
			/*  62 */       pstmt.setInt(4, rtVO.getRoomTotalPerson().intValue());
			/*  63 */       pstmt.setInt(5, rtVO.getRoomTotalScore().intValue());
			/*  64 */       pstmt.setString(6, rtVO.getRoomName());
			/*  65 */       pstmt.setInt(7, rtVO.getRoomPrice().intValue());
			/*     */
			/*  67 */       pstmt.executeUpdate();
			/*     */     }
		/*  69 */     catch (ClassNotFoundException e) {
			/*  70 */       throw new RuntimeException("Couldn't load database driver. " + e
/*  71 */           .getMessage());
			/*     */     }
		/*  73 */     catch (SQLException se) {
			/*  74 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/*  76 */       if (pstmt != null) {
				/*     */         try {
					/*  78 */           pstmt.close();
					/*  79 */         } catch (SQLException se) {
					/*  80 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*  83 */       if (con != null) {
				/*     */         try {
					/*  85 */           con.close();
					/*  86 */         } catch (Exception e) {
					/*  87 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */   public void update(RtVO rtVO) {
		/*  96 */   Connection con = null;
		PreparedStatement pstmt = null;
		/*     */
		/*     */     try {
			/* 100 */       Class.forName(this.driver);
			/* 101 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/* 102 */       pstmt = con.prepareStatement("UPDATE ROOM_TYPE set ROOM_TYPE_AMOUNT=?, ROOM_TYPE_CONTENT=?, ROOM_SALE_STATUS=?, ROOM_TOTAL_PERSON=?, ROOM_TOTAL_SCORE=?, ROOM_NAME=?, ROOM_PRICE=?  where ROOM_CATEGORY_ID = ?");
			/*     */
			/*     */
			/* 105 */       pstmt.setInt(1, rtVO.getRoomTypeAmount().intValue());
			/* 106 */       pstmt.setString(2, rtVO.getRoomTypeContent());
			/* 107 */       pstmt.setByte(3, rtVO.getRoomSaleStatus().byteValue());
			/* 108 */       pstmt.setInt(4, rtVO.getRoomTotalPerson().intValue());
			/* 109 */       pstmt.setInt(5, rtVO.getRoomTotalScore().intValue());
			/* 110 */       pstmt.setString(6, rtVO.getRoomName());
			/* 111 */       pstmt.setInt(7, rtVO.getRoomPrice().intValue());
			/* 112 */       pstmt.setInt(8, rtVO.getRoomCategoryId().intValue());
			/*     */
			/* 114 */       pstmt.executeUpdate();
			/*     */     }
		/* 116 */     catch (ClassNotFoundException e) {
			/* 117 */       throw new RuntimeException("Couldn't load database driver. " + e
/* 118 */           .getMessage());
			/*     */     }
		/* 120 */     catch (SQLException se) {
			/* 121 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/* 123 */       if (pstmt != null) {
				/*     */         try {
					/* 125 */           pstmt.close();
					/* 126 */         } catch (SQLException se) {
					/* 127 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 130 */       if (con != null) {
				/*     */         try {
					/* 132 */           con.close();
					/* 133 */         } catch (Exception e) {
					/* 134 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */   public void delete(Integer roomCategoryId) {
		/* 143 */  Connection con = null;
		PreparedStatement pstmt = null;
		/*     */
		/*     */     try {
			/* 147 */       Class.forName(this.driver);
			/* 148 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/* 149 */       pstmt = con.prepareStatement("DELETE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?");
			/*     */
			/* 151 */       pstmt.setInt(1, roomCategoryId.intValue());
			/*     */
			/* 153 */       pstmt.executeUpdate();
			/*     */     }
		/* 155 */     catch (ClassNotFoundException e) {
			/* 156 */       throw new RuntimeException("Couldn't load database driver. " + e
/* 157 */           .getMessage());
			/* 158 */     } catch (SQLException se) {
			/* 159 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/* 161 */       if (pstmt != null) {
				/*     */         try {
					/* 163 */           pstmt.close();
					/* 164 */         } catch (SQLException se) {
					/* 165 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 168 */       if (con != null) {
				/*     */         try {
					/* 170 */           con.close();
					/* 171 */         } catch (Exception e) {
					/* 172 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */   public RtVO findByPrimaryKey(Integer roomCategoryId) {
		/* 180 */     RtVO rtVO = null;
		/* 181 */   Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		/*     */
		/*     */     try {
			/* 186 */       Class.forName(this.driver);
			/* 187 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/* 188 */       pstmt = con.prepareStatement("SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE FROM ROOM_TYPE where ROOM_CATEGORY_ID = ?");
			/*     */
			/* 190 */       pstmt.setInt(1, roomCategoryId.intValue());
			/*     */
			/* 192 */       rs = pstmt.executeQuery();
			/*     */
			/* 194 */       while (rs.next()) {
				/* 195 */         rtVO = new RtVO();
				/* 196 */         rtVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
				/* 197 */         rtVO.setRoomTypeAmount(Integer.valueOf(rs.getInt("ROOM_TYPE_AMOUNT")));
				/* 198 */         rtVO.setRoomTypeContent(rs.getString("ROOM_TYPE_CONTENT"));
				/* 199 */         rtVO.setRoomSaleStatus(Byte.valueOf(rs.getByte("ROOM_SALE_STATUS")));
				/* 200 */         rtVO.setRoomTotalPerson(Integer.valueOf(rs.getInt("ROOM_TOTAL_PERSON")));
				/* 201 */         rtVO.setRoomTotalScore(Integer.valueOf(rs.getInt("ROOM_TOTAL_SCORE")));
				/* 202 */         rtVO.setRoomName(rs.getString("ROOM_NAME"));
				/* 203 */         rtVO.setRoomPrice(Integer.valueOf(rs.getInt("ROOM_PRICE")));
				/*     */       }
			/* 205 */     } catch (ClassNotFoundException e) {
			/* 206 */       throw new RuntimeException("Couldn't load database driver. " + e
/* 207 */           .getMessage());
			/*     */     }
		/* 209 */     catch (SQLException se) {
			/* 210 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/* 212 */       if (rs != null) {
				/*     */         try {
					/* 214 */           rs.close();
					/* 215 */         } catch (SQLException se) {
					/* 216 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 219 */       if (pstmt != null) {
				/*     */         try {
					/* 221 */           pstmt.close();
					/* 222 */         } catch (SQLException se) {
					/* 223 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 226 */       if (con != null) {
				/*     */         try {
					/* 228 */           con.close();
					/* 229 */         } catch (Exception e) {
					/* 230 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/* 234 */     return rtVO;
		/*     */   }
	/*     */
	/*     */
	/*     */   public List<RtVO> getAll() {
		/* 239 */     List<RtVO> list = new ArrayList<RtVO>();
		/* 240 */     RtVO rtVO = null;
		/*     */
		/* 242 */     Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*     */
		/*     */
		/*     */     try {
			/* 248 */       Class.forName(this.driver);
			/* 249 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/* 250 */       pstmt = con.prepareStatement("SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE FROM ROOM_TYPE order by ROOM_CATEGORY_ID");
			/* 251 */       rs = pstmt.executeQuery();
			/*     */
			/* 253 */       while (rs.next())
				/*     */       {
				/* 255 */         rtVO = new RtVO();
				/* 256 */         rtVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
				/* 257 */         rtVO.setRoomTypeAmount(Integer.valueOf(rs.getInt("ROOM_TYPE_AMOUNT")));
				/* 258 */         rtVO.setRoomTypeContent(rs.getString("ROOM_TYPE_CONTENT"));
				/* 259 */         rtVO.setRoomSaleStatus(Byte.valueOf(rs.getByte("ROOM_SALE_STATUS")));
				/* 260 */         rtVO.setRoomTotalPerson(Integer.valueOf(rs.getInt("ROOM_TOTAL_PERSON")));
				/* 261 */         rtVO.setRoomTotalScore(Integer.valueOf(rs.getInt("ROOM_TOTAL_SCORE")));
				/* 262 */         rtVO.setRoomName(rs.getString("ROOM_NAME"));
				/* 263 */         rtVO.setRoomPrice(Integer.valueOf(rs.getInt("ROOM_PRICE")));
				/* 264 */         list.add(rtVO);
				/*     */       }
			/*     */
			/* 267 */     } catch (ClassNotFoundException e) {
			/* 268 */       throw new RuntimeException("Couldn't load database driver. " + e
/* 269 */           .getMessage());
			/*     */     }
		/* 271 */     catch (SQLException se) {
			/* 272 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/* 274 */       if (rs != null) {
				/*     */         try {
					/* 276 */           rs.close();
					/* 277 */         } catch (SQLException se) {
					/* 278 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 281 */       if (pstmt != null) {
				/*     */         try {
					/* 283 */           pstmt.close();
					/* 284 */         } catch (SQLException se) {
					/* 285 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 288 */       if (con != null) {
				/*     */         try {
					/* 290 */           con.close();
					/* 291 */         } catch (Exception e) {
					/* 292 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/* 296 */     return list;
		/*     */   }
	/*     */
	/*     */
	/*     */   public RtVO findRoom(String roomName) {
		/* 301 */     RtVO rtVO = null;
		/* 302 */    Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*     */
		/*     */     try {
			/* 307 */       Class.forName(this.driver);
			/* 308 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/* 309 */       pstmt = con.prepareStatement("SELECT ROOM_CATEGORY_ID, ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME,ROOM_PRICE FROM ROOM_TYPE WHERE ROOM_NAME= ?");
			/*     */
			/* 311 */       pstmt.setString(1, roomName);
			/*     */
			/* 313 */       rs = pstmt.executeQuery();
			/*     */
			/* 315 */       while (rs.next()) {
				/* 316 */         rtVO = new RtVO();
				/* 317 */         rtVO.setRoomCategoryId(Integer.valueOf(rs.getInt("ROOM_CATEGORY_ID")));
				/* 318 */         rtVO.setRoomTypeAmount(Integer.valueOf(rs.getInt("ROOM_TYPE_AMOUNT")));
				/* 319 */         rtVO.setRoomTypeContent(rs.getString("ROOM_TYPE_CONTENT"));
				/* 320 */         rtVO.setRoomSaleStatus(Byte.valueOf(rs.getByte("ROOM_SALE_STATUS")));
				/* 321 */         rtVO.setRoomTotalPerson(Integer.valueOf(rs.getInt("ROOM_TOTAL_PERSON")));
				/* 322 */         rtVO.setRoomTotalScore(Integer.valueOf(rs.getInt("ROOM_TOTAL_SCORE")));
				/* 323 */         rtVO.setRoomName(rs.getString("ROOM_NAME"));
				/*     */       }
			/* 325 */     } catch (ClassNotFoundException e) {
			/* 326 */       throw new RuntimeException("Couldn't load database driver. " + e
/* 327 */           .getMessage());
			/*     */     }
		/* 329 */     catch (SQLException se) {
			/* 330 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/* 332 */       if (rs != null) {
				/*     */         try {
					/* 334 */           rs.close();
					/* 335 */         } catch (SQLException se) {
					/* 336 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 339 */       if (pstmt != null) {
				/*     */         try {
					/* 341 */           pstmt.close();
					/* 342 */         } catch (SQLException se) {
					/* 343 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 346 */       if (con != null) {
				/*     */         try {
					/* 348 */           con.close();
					/* 349 */         } catch (Exception e) {
					/* 350 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/* 354 */     return rtVO;
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */   public void insertWithRtAndPic(RtVO rtVO, List<Base64VO> list) {
		/* 361 */    Connection con = null;
		PreparedStatement pstmt = null;
		/*     */
		/*     */
		/*     */     try {
			/* 366 */       Class.forName(this.driver);
			/* 367 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/*     */
			/*     */
			/* 370 */       con.setAutoCommit(false);
			/*     */
			/*     */
			/* 373 */     String[]  cols = new String[] { "ROOM_CATEGORY_ID" };
			/*     */
			/* 375 */       pstmt = con.prepareStatement("INSERT INTO ROOM_TYPE (ROOM_TYPE_AMOUNT, ROOM_TYPE_CONTENT, ROOM_SALE_STATUS, ROOM_TOTAL_PERSON, ROOM_TOTAL_SCORE,ROOM_NAME, ROOM_PRICE) VALUES (?,?,?,?,?,?,?)", cols);
			/*     */
			/*     */
			/* 378 */       pstmt.setInt(1, rtVO.getRoomTypeAmount().intValue());
			/* 379 */       pstmt.setString(2, rtVO.getRoomTypeContent());
			/* 380 */       pstmt.setByte(3, rtVO.getRoomSaleStatus().byteValue());
			/* 381 */       pstmt.setInt(4, rtVO.getRoomTotalPerson().intValue());
			/* 382 */       pstmt.setInt(5, rtVO.getRoomTotalScore().intValue());
			/* 383 */       pstmt.setString(6, rtVO.getRoomName());
			/* 384 */       pstmt.setInt(7, rtVO.getRoomPrice().intValue());
			/*     */
			/* 386 */       int i = pstmt.executeUpdate();
			/*     */
			/*     */
			/* 389 */       System.out.println("新增筆" + i + "成功");
			/*     */
			/*     */
			/* 392 */       String nextRoomCategoryId = null;
			/*     */
			/* 394 */       ResultSet rs = pstmt.getGeneratedKeys();
			/*     */
			/*     */
			/*     */
			/* 398 */       if (rs.next()) {
				/*     */
				/* 400 */         nextRoomCategoryId = rs.getString(1);
				/*     */
				/* 402 */         System.out.println("自增主鍵值= " + nextRoomCategoryId + "(剛新增成功的房型編號)");
				/*     */       } else {
				/* 404 */         System.out.println("未取得自增主鍵值");
				/*     */       }
			/* 406 */       rs.close();
			/*     */
			/*     */
			/* 409 */       System.out.println("list.size()-A=" + list.size());
			/*     */
			/* 411 */       int nrcid = Integer.parseInt(nextRoomCategoryId);
			/*     */
			/* 413 */       RtpService rtpService = new RtpService();
			/*     */
			/* 415 */       Base64.Decoder decoder = Base64.getDecoder();
			/*     */
			/* 417 */       for (Base64VO base64VO : list) {
				/*     */
				/* 419 */         String base64 = base64VO.getBase64();
				/*     */
				/*     */
				/*     */
				/* 423 */         base64 = base64.split(",")[1];
				/*     */
				/* 425 */         System.out.println("轉換開始");
				/*     */
				/* 427 */         byte[] decode = decoder.decode(base64);
				/*     */
				/* 429 */         int len = decode.length;
				/*     */
				/* 431 */         rtpService.addRtpAndPic(con, Integer.valueOf(nrcid), decode);
				/*     */
				/* 433 */         System.out.println(len);
				/*     */       }
			/*     */
			/*     */
			/*     */
			/* 438 */       con.commit();
			/*     */
			/* 440 */       con.setAutoCommit(true);
			/*     */
			/* 442 */       System.out.println("list.size()-B=" + list.size());
			/* 443 */       System.out.println("新增房型編號" + nextRoomCategoryId + "時,共有圖片" + list.size() + "張同時被新增");
			/*     */
			/*     */
			/*     */     }
		/* 447 */     catch (ClassNotFoundException e) {
			/* 448 */       throw new RuntimeException("驅動載入錯誤" + e
/* 449 */           .getMessage());
			/*     */     }
		/* 451 */     catch (SQLException se) {
			/* 452 */       if (con != null) {
				/*     */
				/*     */         try {
					/* 455 */           System.err.print("交易中失敗 ");
					/* 456 */           System.err.println("rolled back由-Rt");
					/* 457 */           con.rollback();
					/* 458 */         } catch (SQLException excep) {
					/* 459 */           throw new RuntimeException("rollback 失敗. " + excep
/* 460 */               .getMessage());
					/*     */         }
				/*     */       }
			/* 463 */       throw new RuntimeException("發生錯誤. " + se
/* 464 */           .getMessage());
			/*     */     } finally {
			/*     */
			/* 467 */       if (pstmt != null) {
				/*     */         try {
					/* 469 */           pstmt.close();
					/* 470 */         } catch (SQLException se) {
					/* 471 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 474 */       if (con != null) {
				/*     */         try {
					/* 476 */           con.close();
					/* 477 */         } catch (Exception e) {
					/* 478 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */   public void updateByNormal(RtVO rtVO) {
		/* 490 */   Connection con = null;
		PreparedStatement pstmt = null;
		/*     */
		/*     */     try {
			/* 494 */       Class.forName(this.driver);
			/* 495 */       con = DriverManager.getConnection(this.url, this.userid, this.passwd);
			/* 496 */       pstmt = con.prepareStatement("UPDATE ROOM_TYPE set ROOM_TYPE_AMOUNT=?, ROOM_TYPE_CONTENT=?, ROOM_SALE_STATUS=?, ROOM_NAME=?, ROOM_PRICE=?  where ROOM_CATEGORY_ID = ?");
			/*     */
			/*     */
			/* 499 */       pstmt.setInt(1, rtVO.getRoomTypeAmount().intValue());
			/* 500 */       pstmt.setString(2, rtVO.getRoomTypeContent());
			/* 501 */       pstmt.setByte(3, rtVO.getRoomSaleStatus().byteValue());
			/* 502 */       pstmt.setString(4, rtVO.getRoomName());
			/* 503 */       pstmt.setInt(5, rtVO.getRoomPrice().intValue());
			/* 504 */       pstmt.setInt(6, rtVO.getRoomCategoryId().intValue());
			/*     */
			/* 506 */
			int i = pstmt.executeUpdate();
			/* 507 */       System.out.println("更新" + i + "筆完成");
			/*     */     }
		/* 509 */     catch (ClassNotFoundException e) {
			/* 510 */       throw new RuntimeException("Couldn't load database driver. " + e
/* 511 */           .getMessage());
			/*     */     }
		/* 513 */     catch (SQLException se) {
			/* 514 */       throw new RuntimeException("A database error occured. " + se.getMessage());
			/*     */     } finally {
			/* 516 */       if (pstmt != null) {
				/*     */         try {
					/* 518 */           pstmt.close();
					/* 519 */         } catch (SQLException se) {
					/* 520 */           se.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/* 523 */       if (con != null) {
				/*     */         try {
					/* 525 */           con.close();
					/* 526 */         } catch (Exception e) {
					/* 527 */           e.printStackTrace(System.err);
					/*     */         }
				/*     */       }
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */   public int findTotalCount(Map<String, String> condition) {
		/* 539 */     JdbcTemplate Template = new JdbcTemplate(this.jdbcUtils.getDataSource());
		/*     */
		/* 541 */     String GET_ALL_COUNT = "select count(*) from ROOM_TYPE where 1 = 1 ";
		/*     */
		/*     */
		/* 544 */     StringBuilder sb = new StringBuilder(GET_ALL_COUNT);
		/*     */
		/*     */
		/*     */
		/* 548 */     Set<String> keySet = condition.keySet();
		/*     */
		/* 550 */     List<Object> params = new ArrayList<Object>();
		/*     */
		/* 552 */     for (String key : keySet) {
			/*     */
			/*     */
			/*     */
			/* 556 */       if ("currentPage".equals(key) || "rows"
/* 557 */         .equals(key) || "funs"
/* 558 */         .equals(key) || "delEmpId"
/* 559 */         .equals(key)) {
				/*     */         continue;
				/*     */       }
			/*     */
			/*     */
			/* 564 */       String value = (String)condition.get(key);
			/*     */
			/*     */
			/*     */
			/* 568 */       if (value != null && !"".equals(value)) {
				/*     */
				/* 570 */         sb.append(" and " + key + " like ? ");
				/* 571 */         params.add("%" + value + "%");
				/*     */       }
			/*     */     }
		/*     */
		/*     */
		/*     */
		/*     */
		/* 578 */     return ((Integer)Template.queryForObject(sb.toString(), Integer.class, params.toArray())).intValue();
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */   public List<RtVO> findByPage(int start, int rows, Map<String, String> condition) {
		/* 586 */     JdbcTemplate Template = new JdbcTemplate(this.jdbcUtils.getDataSource());
		/* 587 */     String GET_LIMIT = "select * from ROOM_TYPE where 1 = 1 ";
		/*     */
		/*     */
		/*     */
		/*     */
		/* 592 */     StringBuilder sb = new StringBuilder(GET_LIMIT);
		/*     */
		/*     */
		/* 595 */     Set<String> keySet = condition.keySet();
		/*     */
		/*     */
		/*     */
		/* 599 */     List<Object> params = new ArrayList<Object>();
		/*     */
		/* 601 */     for (String key : keySet) {
			/*     */
			/* 603 */       if ("currentPage".equals(key) || "rows"
/* 604 */         .equals(key) || "funs"
/* 605 */         .equals(key) || "delEmpId"
/* 606 */         .equals(key)) {
				/*     */         continue;
				/*     */       }
			/*     */
			/*     */
			/* 611 */       String value = (String)condition.get(key);
			/*     */
			/*     */
			/*     */
			/* 615 */       if (value != null && !"".equals(value)) {
				/*     */
				/* 617 */         sb.append(" and " + key + " like ? ");
				/* 618 */         params.add("%" + value + "%");
				/*     */       }
			/*     */     }
		/*     */
		/*     */
		/* 623 */     sb.append("limit ?,?");
		/*     */
		/*     */
		/*     */
		/* 627 */     params.add(Integer.valueOf(start));
		/* 628 */     params.add(Integer.valueOf(rows));
		/*     */
		/*     */
		/* 631 */     String sql = sb.toString();
		/*     */
		/*     */
		/* 634 */     return Template.query(sql, new BeanPropertyRowMapper(RtVO.class), params.toArray());
		/*     */   }
	/*     */ }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/model/RtJDBCDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */