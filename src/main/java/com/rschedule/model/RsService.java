package com.rschedule.model;

import java.sql.Timestamp;
import java.util.List;

public class RsService {
	
	private RsDAO_interface dao;
	
	public RsService() {
		dao = new RsDAO();
	}
	
	public RsVO addRsVO(Integer roomCategoryId, Timestamp roomScheduleDate, Integer roomAmount, Integer roomRsvBooked) {
	
		RsVO rsVO = new RsVO();
		rsVO.setRoomCategoryId(roomCategoryId);
		rsVO.setRoomScheduleDate(roomScheduleDate);
		rsVO.setRoomAmount(roomAmount);
		rsVO.setRoomRsvBooked(roomRsvBooked);
		dao.insert(rsVO);
		return rsVO;
	}
	
	public RsVO updateRsVO(Integer roomScheduleId, Integer roomCategoryId, Timestamp roomScheduleDate, Integer roomAmount, Integer roomRsvBooked) {
		RsVO rsVO = new RsVO();
		rsVO.setRoomScheduleId(roomScheduleId);
		rsVO.setRoomCategoryId(roomCategoryId);
		rsVO.setRoomScheduleDate(roomScheduleDate);
		rsVO.setRoomAmount(roomAmount);
		rsVO.setRoomRsvBooked(roomRsvBooked);

		dao.update(rsVO);
		return rsVO;
	}
	public void deleteRs(Integer roomScheduleId) {
		dao.delete(roomScheduleId);
	}
	public RsVO getOneRt(Integer roomScheduleId) {
		return dao.findByPrimaryKey(roomScheduleId);
	}
	public List<RsVO> getAll(){
		return dao.getAll();
	}
}
