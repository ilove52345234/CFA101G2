 package com.rmtypepic.model;

 import java.sql.Connection;
 import java.util.List;




 public class RtpService
		 {
	/* 11 */   private RtpDAO_interface dao = new RtpJDBCDAO();
	
	
	
	   public RtpVO addRtp(Integer roomCategoryId, byte[] roomPhoto) {
		/* 16 */     RtpVO rtpVO = new RtpVO();
		
		
		/* 19 */     rtpVO.setRoomCategoryId(roomCategoryId);
		/* 20 */     rtpVO.setRoomPhoto(roomPhoto);
		/* 21 */     this.dao.insert(rtpVO);
		
		/* 23 */     return rtpVO;
		   }
	
	
	
	
	   public RtpVO addRtpAndPic(Connection con, Integer roomCategoryId, byte[] roomPhoto) {
		/* 30 */     RtpVO rtpVO = new RtpVO();
		
		/* 32 */     rtpVO.setRoomCategoryId(roomCategoryId);
		/* 33 */     rtpVO.setRoomPhoto(roomPhoto);
		/* 34 */     this.dao.insert2(rtpVO, con);
		
		/* 36 */     return rtpVO;
		   }
	
	
	
	
	   public RtpVO updateRtp(Integer roomPhotoId, Integer roomCategoryId, byte[] roomPhoto) {
		/* 43 */     RtpVO rtpVO = new RtpVO();
		
		/* 45 */     rtpVO.setRoomPhotoId(roomPhotoId);
		/* 46 */     rtpVO.setRoomCategoryId(roomCategoryId);
		/* 47 */     rtpVO.setRoomPhoto(roomPhoto);
		/* 48 */     this.dao.update(rtpVO);
		
		/* 50 */     return rtpVO;
		   }
	
	
	
	/* 55 */   public void deleteRtp(Integer roomPhotoId) { this.dao.delete(roomPhotoId); }
	
	
	
	/* 59 */   public RtpVO getOneRtp(Integer roomPhotoId) { return this.dao.findByPrimaryKey(roomPhotoId); }
	
	
	
	/* 63 */   public List<RtpVO> getAll() { return this.dao.getAll(); }
	
	
	
	
	
	
	/* 70 */   public List<RtpVO> getAllByCategoryId(Integer roomCategoryId) { return this.dao.getAllByRcId(roomCategoryId); }
	
	
	
	
	
	
	
	
	/* 79 */   public RtpVO getOneByCategoryId(Integer roomCategoryId) { return this.dao.findByRoomCategoryId(roomCategoryId); }
	 }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtypepic/model/RtpService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */