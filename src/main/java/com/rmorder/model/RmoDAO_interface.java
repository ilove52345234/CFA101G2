package com.rmorder.model;

import com.emp.model.EmpVO;
import com.rmorderlist.model.RmolVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface RmoDAO_interface {
	public void insert(RmoVO rmoVO);
	public void update(RmoVO rmoVO);
	public void delete(Integer roomOrderId);
	public  RmoVO findByPrimaryKey(Integer roomOrderId);
	public List<RmoVO> getAll();
	public int findTotalCount(Map<String, String> condition);
	public List<RmoVO> findByPage(int start, int rows, Map<String, String> condition);
	//進行預定房間訂單
	public Integer bookPreOrder(RmoVO rmoVO, List<RmolVO> rmolVOs);
	//找到預定過後的訂單
	public BookRmoVO findByBookOrder(Integer memberId,Integer orderId);
	//訂房後進入預定的訂單
	public List<MemRoomOrderVO> roomOrder(Integer memberId);


}
