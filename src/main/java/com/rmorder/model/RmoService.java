package com.rmorder.model;

import com.mem.model.MemVO;
import com.rmorderlist.model.RmolVO;
import com.rmtype.model.RtService;
import com.rmtype.model.RtVO;
import com.rschedule.model.RsDAO_interface;
import com.rschedule.model.RsJDBCDAO;
import com.rschedule.model.RsService;
import com.utils.PageBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RmoService {
	
	private RmoDAO_interface dao;
	private RsDAO_interface rsDao;
	private RtService rtService;
	
	public RmoService() {
		dao = new RmoJDBCDAO();
		rsDao = new RsJDBCDAO();
		rtService = new RtService();
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

	public List<MemRoomOrderVO> bookPreRoomOrder(BookRoomRequestVO bookRoomRequestVO, MemVO memVO) {

		// 計算入住天數,對應此頁的private Integer diffDays方法
		Integer diffDays = diffDays(bookRoomRequestVO.getCheckInDate(), bookRoomRequestVO.getCheckOutDate());
		// 因為入住時間是15:00，退房時間是11:00，相減會未滿一天，轉成integer會無條件捨去，所以這邊要+1
		Integer liveDays = diffDays + 1;

		// 查詢該房型資料  從rtService取得該主鍵ID
		RtVO rtVO = rtService.getOneRt(bookRoomRequestVO.getRoomCategoryId());

		// 計算入住天數 及人數 總金額, 對應此頁的private Integer calulateMoney方法
		// 總金額=liveDays天數*rtVO.getRoomPrice()的價格*bookRoomRequestVO.getRoomNumber()的房間數量
		Integer totalMoney = calulateMoney(liveDays, rtVO.getRoomPrice(), bookRoomRequestVO.getRoomNumber());

		// 組成 住房訂單 VO 住房訂單內有 ROOM_ORDER_ID MEM_ID ORDER_DATE ROOM_ORDER_STATUS TOTAL_PRICE
		//而getRoomOrder 是寫活的資料 把MEM_ID存入memVO  totalMoney存入TOTAL_PRICE內並帶到private RmoVO getRoomOrder內
		RmoVO rmoVO = getRoomOrder(memVO, totalMoney);

		// 組成 多筆住房訂單明細 VO 住房訂單明細內有ORDER_LIST_ID,ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, ROOM_ID,MEM_NAME, MEM_NUMBER, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_TOTAL_PRICE
		//而getRoomOrderLists只取bookRoomRequestVO的資料  totalMoney算出來的總金額  memVO內MEM_NAME和MEM_NUMBER 並帶到private List<RmolVO> getRoomOrderLists
		List<RmolVO> rmolVOs = getRoomOrderLists(bookRoomRequestVO, rtVO, memVO);

//		// 依照 該房型及日期 房型預訂表 最新一筆資料取得 累計數量 ，再組成 房型預訂表 VO
//		List<RsVO> rsVos = getRoomSchedule(bookRoomRequestVO.getCheckInDate(), bookRoomRequestVO.getCheckOutDate(),
//				diffDays, bookRoomRequestVO.getRoomCategoryId(), bookRoomRequestVO.getRoomNumber());

		// (從DAO依據指令共新增兩張表)新增住房訂單和新增住房明細

		Integer orderId = dao.bookPreOrder(rmoVO, rmolVOs);
		RsService rsService = new RsService();

		for (RmolVO rmolVO : rmolVOs) {
			rsService.updateByRmOrderList(rmolVO);
		}

		// (從DAO依據指令)查詢該會員預定房型訂單相關資訊 join四張table的資料才能完全顯現  以前寫的方法
//		BookRmoVO bookRmoVO = rmoDao.findByBookOrder(memVO.getMemId(), orderId);

		// (從DAO依據指令)join三張表的資料
		List<MemRoomOrderVO> memRoomOrderVOs = dao.roomOrder(memVO.getMemId());


		return memRoomOrderVOs;

	}

	//取得會員訂房訂單明細的方法
	public BookRmoVO findByBookOrder(Integer memberId, Integer orderId) {
		BookRmoVO bookRmoVO = dao.findByBookOrder(memberId, orderId);
		return bookRmoVO;

	}
	//取得會員訂單的方法
	public List<MemRoomOrderVO> memRoomorder(MemVO memVO){
		List<MemRoomOrderVO> memRoomOrderVOs = dao.roomOrder(memVO.getMemId());
		return memRoomOrderVOs;
	}

	private RmoVO getRoomOrder(MemVO memVO, Integer totalMoney) {
		RmoVO rmoVO = new RmoVO();
		rmoVO.setMemId(memVO.getMemId());
		rmoVO.setOrderDate(new Timestamp(System.currentTimeMillis()));
		rmoVO.setRoomOrderStatus(0);// 未確認
		rmoVO.setTotalPrice(totalMoney);
		return rmoVO;
	}
	//從BookRoomRequestVO拿到的資料存入RoomCategoryId,CheckInDate,CheckOutDate totalMoney算出來的資料存入TotalPrice  memVO存入NUMBER和NAME
	private List<RmolVO> getRoomOrderLists(BookRoomRequestVO bookRoomRequestVO, RtVO rtVO, MemVO memVO) {
		//數量的部分則是使用下面的迴圈訂幾間就增加幾筆資料 增加完的資料帶進rmolVOs  再帶回上方的rmolVOs內
		List<RmolVO> rmolVOs = new ArrayList<>();
		for (int i = 0; i < bookRoomRequestVO.getRoomNumber(); i++) {
			RmolVO rmolVO = new RmolVO();
			rmolVO.setRoomCategoryId(bookRoomRequestVO.getRoomCategoryId());
			rmolVO.setMemNumber(bookRoomRequestVO.getMemNumber());
			rmolVO.setCheckInDate(bookRoomRequestVO.getCheckInDate());
			rmolVO.setCheckOutDate(bookRoomRequestVO.getCheckOutDate());
			rmolVO.setRoomTotalPrice(rtVO.getRoomPrice());
			rmolVO.setMemName(memVO.getMemName());
			rmolVOs.add(rmolVO);
		}
		return rmolVOs;
	}

	private Integer diffDays(Timestamp checkInDate, Timestamp CheckOutDate) { // 返還天數方法
		Long times = CheckOutDate.getTime() - checkInDate.getTime(); // 單位為毫秒
		Long days = times / (1000 * 3600 * 24);// 天 = 毫秒 / 一天的毫秒

		return Integer.parseInt(days.toString());
	}

	// 總金額=liveDays天數*rtVO.getRoomPrice()的價格*bookRoomRequestVO.getRoomNumber()的房間數量
	private Integer calulateMoney(Integer days, Integer priceUnique, Integer roomNumber) {
		return days * priceUnique * roomNumber;
	}


}
