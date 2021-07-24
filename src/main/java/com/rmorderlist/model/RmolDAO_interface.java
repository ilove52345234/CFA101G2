package com.rmorderlist.model;

import com.rmorder.model.RmoVO;

import java.util.List;
import java.util.Map;


public interface RmolDAO_interface {
	public void insert(RmolVO rmolVO);
	public void update(RmolVO rmolVO);
	public void delete(Integer orderListId);
	public  RmolVO findByPrimaryKey(Integer orderListId);
	public List<RmolVO> getAll();
	public int findTotalCount(Map<String, String> condition);
	public List<RmolVO> findByPage(int start, int rows, Map<String, String> condition);

	public List<RmolVO> getAllByRoomOrderId(Integer RoomOrderId);

}
