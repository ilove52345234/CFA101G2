 package com.rmtype.model;

 import com.rmtypepic.model.RtpService;
 import com.rmtypepic.model.RtpVO;
 import com.utils.Base64VO;
 import com.utils.PageBean;

 import java.sql.Connection;
 import java.util.Base64;
 import java.util.List;
 import java.util.Map;







 public class RtService
		 {
	/*  19 */   private RtDAO_interface dao = new RtJDBCDAO();



			 public void updateRschedule(Integer inint,
										 String date,
										 Integer rtpid,
										 Integer loop,
										 Integer amount, Connection con){
			 	dao.updateCount(inint,date,rtpid,loop,amount,con);
//				 System.out.println("更新完成");

			 }

	
	   public RtVO addRtVO(Integer roomTypeAmount, String roomTypeContent, Byte roomSaleStatus, Integer roomTotalPerson, Integer roomTotalScore, String roomName, Integer roomPrice) {
		/*  25 */     RtVO rtVO = new RtVO();
		
		/*  27 */     rtVO.setRoomTypeAmount(roomTypeAmount);
		/*  28 */     rtVO.setRoomTypeContent(roomTypeContent);
		/*  29 */     rtVO.setRoomSaleStatus(roomSaleStatus);
		/*  30 */     rtVO.setRoomTotalPerson(roomTotalPerson);
		/*  31 */     rtVO.setRoomTotalScore(roomTotalScore);
		/*  32 */     rtVO.setRoomName(roomName);
		/*  33 */     rtVO.setRoomPrice(roomPrice);
		/*  34 */     this.dao.insert(rtVO);
		
		/*  36 */     return rtVO;
		   }
	
	
	   public RtVO updateRtVO(Integer roomCategoryId, Integer roomTypeAmount, String roomTypeContent, Byte roomSaleStatus, Integer roomTotalPerson, Integer roomTotalScore, String roomName, Integer roomPrice) {
		/*  41 */     RtVO rtVO = new RtVO();
		
		/*  43 */     rtVO.setRoomCategoryId(roomCategoryId);
		/*  44 */     rtVO.setRoomTypeAmount(roomTypeAmount);
		/*  45 */     rtVO.setRoomTypeContent(roomTypeContent);
		/*  46 */     rtVO.setRoomSaleStatus(roomSaleStatus);
		/*  47 */     rtVO.setRoomTotalPerson(roomTotalPerson);
		/*  48 */     rtVO.setRoomTotalScore(roomTotalScore);
		/*  49 */     rtVO.setRoomName(roomName);
		/*  50 */     rtVO.setRoomPrice(roomPrice);
		/*  51 */     this.dao.update(rtVO);
		
		/*  53 */     return rtVO;
		   }
	
	/*  56 */   public void deleteRt(Integer roomCategoryId) { this.dao.delete(roomCategoryId); }
	
	
	
	/*  60 */   public RtVO getOneRt(Integer roomCategoryId) { return this.dao.findByPrimaryKey(roomCategoryId); }
	
	
	
	/*  64 */   public List<RtVO> getAll() { return this.dao.getAll(); }
	
	
	
	/*  68 */   public RtVO getRoomName(String roomName) { return this.dao.findRoom(roomName); }
	
	
	
	/*  72 */   public void updateNormal(RtVO rtVO) { this.dao.updateByNormal(rtVO); }

	
	
	   public RtVO addRoomTypeAndPic(RtVO rtVO, List<Base64VO> list) {
		/*  77 */     this.dao.insertWithRtAndPic(rtVO, list);

		/*  79 */     return rtVO;
		   }
	
	
	   public PageBean<RtVO> findRtByPage(String _currentPage, String _rows, Map<String, String> condition) {
		/*  84 */     PageBean<RtVO> rtVOPageBean = new PageBean<RtVO>();
		
		/*  86 */     RtpService rtpService = new RtpService();
		
		/*  88 */     int currentPage = Integer.parseInt(_currentPage);
		/*  89 */     int rows = Integer.parseInt(_rows);
		
		
		/*  92 */     rtVOPageBean.setCurrentPage(currentPage);
		/*  93 */     rtVOPageBean.setRows(rows);
		
		
		/*  96 */     int totalCount = this.dao.findTotalCount(condition);
		
		/*  98 */     rtVOPageBean.setTotalCount(totalCount);
		
		
		
		
		/* 103 */     int start = (currentPage - 1) * rows;
		
		/* 105 */     List<RtVO> list = this.dao.findByPage(start, rows, condition);
		
		
		/* 108 */     for (RtVO rtVO : list) {
			/* 109 */       StringBuilder sb = new StringBuilder("data:image/jpeg;base64,");
			/* 110 */       RtpVO rtpVO = rtpService.getOneByCategoryId(rtVO.getRoomCategoryId());
			/* 111 */       if (rtpVO != null) {
				/* 112 */         sb.append(Base64.getEncoder().encodeToString(rtpVO.getRoomPhoto()));
				/* 113 */         rtVO.setRoomOnePhoto(sb.toString()); continue;
				       }
			/* 115 */       rtVO.setRoomOnePhoto("/CFA101G2/back-end/images/Coming_soon.png");
			     }
		
		
		
		
		
		/* 122 */     rtVOPageBean.setList(list);
		
		
		
		/* 126 */     int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows + 1);
		
		/* 128 */     rtVOPageBean.setTotalPage(totalPage);
		
		/* 130 */     return rtVOPageBean;
		   }
	 }


