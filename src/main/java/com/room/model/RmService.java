package com.room.model;

import java.util.List;

public class RmService {

		private RmDAO_interface dao;
		
		public RmService() {
			dao = new RmDAO();
		}
		
		public RmVO addRmVO(Integer roomCategoryId, Byte roomCheckStatus, Byte roomSaleStatus, String roomInformation) {
			RmVO rmVO = new RmVO();
			rmVO.setRoomCategoryId(roomCategoryId);
			rmVO.setRoomCheckStatus(roomCheckStatus);
			rmVO.setRoomSaleStatus(roomSaleStatus);
			rmVO.setRoomInformation(roomInformation);
			dao.insert(rmVO);
			return rmVO;
			
		}
		public RmVO updateRmVO(Integer roomId, Integer roomCategoryId, Byte roomCheckStatus, Byte roomSaleStatus, String roomInformation) {
			RmVO rmVO = new RmVO();
			rmVO.setRoomId(roomId);
			rmVO.setRoomCategoryId(roomCategoryId);
			rmVO.setRoomCheckStatus(roomCheckStatus);
			rmVO.setRoomSaleStatus(roomSaleStatus);
			rmVO.setRoomInformation(roomInformation);
			dao.update(rmVO);
			return rmVO;
		}
		
		public void deleteRm(Integer roomId) {
			dao.delete(roomId);
		}
		
		public RmVO getOneRm(Integer roomId) {
			return dao.findByPrimaryKey(roomId);
		}
		
		public List<RmVO> getAll(){
			return dao.getAll();
		}
		
}
