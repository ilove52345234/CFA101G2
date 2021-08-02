 package com.rmtype.model;

 import java.io.Serializable;

 public class RtVO
		   implements Serializable
		 {
	   private Integer roomCategoryId;
	   private Integer roomTypeAmount;
	   private String roomTypeContent;
	   private Byte roomSaleStatus;
	   private Integer roomTotalPerson;
	   private Integer roomTotalScore;
	   private String roomName;
	   private Integer roomPrice;
	   private String roomOnePhoto;
	
	   public RtVO() {}
	
	/*  20 */   public String toString() { return "RtVO{roomCategoryId=" + this.roomCategoryId + ", roomTypeAmount=" + this.roomTypeAmount + ", roomTypeContent='" + this.roomTypeContent + '\'' + ", roomSaleStatus=" + this.roomSaleStatus + ", roomTotalPerson=" + this.roomTotalPerson + ", roomTotalScore=" + this.roomTotalScore + ", roomName='" + this.roomName + '\'' + ", roomPrice=" + this.roomPrice + ", roomOnePhoto='" + this.roomOnePhoto + '\'' + '}'; }
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*  34 */   public String getRoomOnePhoto() { return this.roomOnePhoto; }
	
	
	
	/*  38 */   public void setRoomOnePhoto(String roomOnePhoto) { this.roomOnePhoto = roomOnePhoto; }
	
	
	   public RtVO(Integer roomCategoryId, Integer roomTypeAmount, String roomTypeContent, Byte roomSaleStatus, Integer roomTotalPerson, Integer roomTotalScore, String roomName, Integer roomPrice, String roomOnePhoto) {
		/*  42 */     this.roomCategoryId = roomCategoryId;
		/*  43 */     this.roomTypeAmount = roomTypeAmount;
		/*  44 */     this.roomTypeContent = roomTypeContent;
		/*  45 */     this.roomSaleStatus = roomSaleStatus;
		/*  46 */     this.roomTotalPerson = roomTotalPerson;
		/*  47 */     this.roomTotalScore = roomTotalScore;
		/*  48 */     this.roomName = roomName;
		/*  49 */     this.roomPrice = roomPrice;
		/*  50 */     this.roomOnePhoto = roomOnePhoto;
		   }
	
	
	/*  54 */   public Integer getRoomCategoryId() { return this.roomCategoryId; }
	
	
	
	/*  58 */   public void setRoomCategoryId(Integer roomCategoryId) { this.roomCategoryId = roomCategoryId; }
	
	
	
	/*  62 */   public Integer getRoomTypeAmount() { return this.roomTypeAmount; }
	
	
	
	/*  66 */   public void setRoomTypeAmount(Integer roomTypeAmount) { this.roomTypeAmount = roomTypeAmount; }
	
	
	
	/*  70 */   public String getRoomTypeContent() { return this.roomTypeContent; }
	
	
	
	/*  74 */   public void setRoomTypeContent(String roomTypeContent) { this.roomTypeContent = roomTypeContent; }
	
	
	
	
	
	/*  80 */   public Byte getRoomSaleStatus() { return this.roomSaleStatus; }
	
	
	
	/*  84 */   public void setRoomSaleStatus(Byte roomSaleStatus) { this.roomSaleStatus = roomSaleStatus; }
	
	
	
	/*  88 */   public Integer getRoomTotalPerson() { return this.roomTotalPerson; }
	
	
	
	/*  92 */   public void setRoomTotalPerson(Integer roomTotalPerson) { this.roomTotalPerson = roomTotalPerson; }
	
	
	
	/*  96 */   public Integer getRoomTotalScore() { return this.roomTotalScore; }
	
	
	
	/* 100 */   public void setRoomTotalScore(Integer roomTotalScore) { this.roomTotalScore = roomTotalScore; }
	
	
	
	/* 104 */   public String getRoomName() { return this.roomName; }
	
	
	
	/* 108 */   public void setRoomName(String roomName) { this.roomName = roomName; }
	
	
	
	/* 112 */   public Integer getRoomPrice() { return this.roomPrice; }
	
	
	
	/* 116 */   public void setRoomPrice(Integer roomPrice) { this.roomPrice = roomPrice; }
	 }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/model/RtVO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */