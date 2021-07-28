package com.rmorder.model;

import com.utils.PageBean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RmoService {
	
	private RmoDAO_interface dao;
	
	public RmoService() {
		dao = new RmoJDBCDAO();
	}
	
	public RmoVO addRmoVO(Integer memId, Timestamp orderDate, Integer roomOrderStatus,  Integer totalPrice) {
		
		RmoVO rmoVO = new RmoVO();
		rmoVO.setMemId(memId);
		rmoVO.setOrderDate(orderDate);
		rmoVO.setRoomOrderStatus(roomOrderStatus);
		rmoVO.setTotalPrice(totalPrice);
		dao.insert(rmoVO);
		return rmoVO;
		
	}
	public RmoVO updateRmoVO(Integer roomOrderId, Integer memId, Timestamp orderDate, Integer roomOrderStatus,  Integer totalPrice) {
		RmoVO rmoVO = new RmoVO();
		rmoVO.setRoomOrderId(roomOrderId);
		rmoVO.setMemId(memId);
		rmoVO.setOrderDate(orderDate);
		rmoVO.setRoomOrderStatus(roomOrderStatus);
		rmoVO.setTotalPrice(totalPrice);

		dao.update(rmoVO);
		return rmoVO;

		
	}

	public void updateRmoVO(RmoVO rmoVO) {
		dao.update(rmoVO);
	}


		public void deleteRmo(Integer roomOrderId) {
		dao.delete(roomOrderId);
	}
	
	public RmoVO getOneRmo(Integer roomOrderId) {
		return dao.findByPrimaryKey(roomOrderId);
	}
	
	public List<RmoVO> getAll(){
		return dao.getAll();
	}


	public PageBean<RmoVO> findRmoByPage(String _currentPage, String _rows, Map<String, String> condition) {
		//1.創建一個空的PageBean物件
		PageBean<RmoVO> RmoVOPageBean = new PageBean<RmoVO>();

		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);



		//2.設置參數
		RmoVOPageBean.setCurrentPage(currentPage);
		RmoVOPageBean.setRows(rows);

		//3.調用dao查詢總記錄數
		int totalCount= dao.findTotalCount(condition);

		RmoVOPageBean.setTotalCount(totalCount);

		//4.調用dao查詢List數據集合

		//計算開始的索引值
		int start = (currentPage - 1) * rows;

		List list = dao.findByPage(start,rows,condition);

		RmoVOPageBean.setList(list);

		//5.計算總頁碼

		int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

		RmoVOPageBean.setTotalPage(totalPage);

		return RmoVOPageBean;

	}



}
