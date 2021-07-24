package com.rschedule.model;

import com.rmorderlist.model.RmolVO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class RsService {
	
	private RsDAO_interface dao;

	public RsService() {
		dao = new RsJDBCDAO();
	}
	
	public RsVO addRsVO(Integer roomCategoryId, Date roomScheduleDate, Integer roomAmount, Integer roomRsvBooked ,Integer roomCheckOut , Integer roomCheckIn) {
		RsVO rsVO = new RsVO();
		rsVO.setRoomCategoryId(roomCategoryId);
		rsVO.setRoomScheduleDate(roomScheduleDate);
		rsVO.setRoomAmount(roomAmount);
		rsVO.setRoomRsvBooked(roomRsvBooked);
		rsVO.setRoomCheckOut(roomCheckOut);
		rsVO.setRoomCheckIn(roomCheckIn);
		dao.insert(rsVO);
		return rsVO;
	}
	
	public RsVO updateRsVO(Integer roomScheduleId, Integer roomCategoryId, Date roomScheduleDate, Integer roomAmount, Integer roomRsvBooked,Integer roomCheckOut , Integer roomCheckIn) {
		RsVO rsVO = new RsVO();
		rsVO.setRoomScheduleId(roomScheduleId);
		rsVO.setRoomCategoryId(roomCategoryId);
		rsVO.setRoomScheduleDate(roomScheduleDate);
		rsVO.setRoomAmount(roomAmount);
		rsVO.setRoomRsvBooked(roomRsvBooked);
		rsVO.setRoomCheckOut(roomCheckOut);
		rsVO.setRoomCheckIn(roomCheckIn);

		dao.update(rsVO);
		return rsVO;
	}


	public RsVO updateRsVO(RsVO rsVO) {
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

	public void updateByRmOrderList(RmolVO rmolVO) {
		List<RsVO> interval = dao.getInterval(rmolVO);
		int size = interval.size();




		for (int i = 0; i < size; i++) {
			RsVO rsVO = interval.get(i);
			if (i==0){
				rsVO.setRoomCheckIn(rsVO.getRoomCheckIn()+1);
				System.out.println("入住的:"+rsVO);
			}
			else if (i==(interval.size()-1)){
				rsVO.setRoomCheckOut(rsVO.getRoomCheckOut()+1);
				System.out.println("退房的:"+rsVO);
			}
			else {
				rsVO.setRoomRsvBooked(rsVO.getRoomRsvBooked()+1);
				System.out.println("期間的:"+rsVO);

			}
			dao.update(rsVO);
		}
	}
}
