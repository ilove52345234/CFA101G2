package com.rmtypetracklist.model;

import java.util.List;

public class RmttlService {
		
	private RmttlDAO_interface dao;
	
	public RmttlService() {
		dao = new RmttlDAO();
	}
	
	public RmttlVO addRmttlVO(Integer roomCategoryId, Integer memId) {
		
		RmttlVO rmttlVO = new RmttlVO();
		rmttlVO.setRoomCategoryId(roomCategoryId);
		rmttlVO.setMemId(memId);
		dao.insert(rmttlVO);
		
		return rmttlVO;
		
	}
	
	public RmttlVO updateRmttlVO(Integer roomCategoryId, Integer memId) {
		
		RmttlVO rmttlVO = new RmttlVO();
		rmttlVO.setRoomCategoryId(roomCategoryId);
		rmttlVO.setMemId(memId);
		dao.update(rmttlVO);
		
		return rmttlVO;
	}
	
	public void deletRmttl(Integer roomCategoryId, Integer memId) {
		dao.delete(roomCategoryId, memId);
	}
	
	public RmttlVO getOneRt(Integer roomCategoryId, Integer memId) {
		return dao.findByPrimaryKey(roomCategoryId, memId);
	}
	
	public List<RmttlVO> getAll(){
		return dao.getAll();
	}
	
}


