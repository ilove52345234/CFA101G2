package com.rmorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.rmorder.model.BookRmoVO;
import com.rmorder.model.BookRoomRequestVO;
import com.rmorder.model.MemRoomOrderVO;
import com.rmorder.model.RmoService;
import com.rmorder.model.RoomTypeRemainVO;
import com.rmtype.model.RtService;
import com.rmtype.model.RtVO;

/**
 * servlet要做的事情很簡單 只要把使用者請求的資料給接受處理後 並返還給使用者 Servlet implementation class
 * RmorderServlet
 */
@WebServlet("/rmorder/RmCartServlet")
public class RmCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RmCartServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings({ "unchecked", "unused" }) // ????
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 從這邊先宣告bookRoomRequestVO
		BookRoomRequestVO bookRoomRequestVO = new BookRoomRequestVO();
		List<String> errorMsgs = new LinkedList<String>();
		Integer roomCategoryId = null;
		HttpSession session = req.getSession(); // 接濾器

		if ("add".equals(action)) {
			System.out.println("RmorderServlet，action為getRoomOrder");
			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 畫面上input name需要跟下面getParameter的name對應到

				// request getParameter為null的話，才從session取得
				// 若經過濾器，先redirect到login.jsp之前先把資料放在session
				String roomCategoryIdstr = req.getParameter("roomCategoryId");
				String checkInDateStr = req.getParameter("checkInDate");
				String checkOutDateStr = req.getParameter("checkOutDate");
				String memNumberStr = req.getParameter("memNumber");
				String roomNumberStr = req.getParameter("roomNumber");
				String memberDataStr = req.getParameter("getMemberData");

				System.out.println("=============== book room 加入購物車 ================");
				System.out.println("roomCategoryIdstr:" + roomCategoryIdstr);
				System.out.println("checkInDateStr:" + checkInDateStr);
				System.out.println("checkOutDateStr:" + checkOutDateStr);
				System.out.println("memNumberStr:" + memNumberStr);
				System.out.println("roomNumberStr:" + roomNumberStr);
				System.out.println("memberDataStr:" + memberDataStr);
				System.out.println("=====================================");

				// 這邊是input hidden傳送過來，一定是正確，不需要驗證  將物件的編號轉型成數字給下面的rtSvc.getOneRt(roomCategoryId);用
				roomCategoryId = Integer.parseInt(roomCategoryIdstr);

				// ajax傳送過來的資訊是否為空，格式是否正確
				errorMsgs = this.verifyParameterEmpty(checkInDateStr, checkOutDateStr, memNumberStr, roomNumberStr,
						memberDataStr, errorMsgs);
				if (errorMsgs.isEmpty() == false) {
					this.failAndPrintErrorMsg(req, res, roomCategoryId, errorMsgs);
					return;
				}

				Integer memNumber = Integer.parseInt(memNumberStr); // 人數
				Integer roomNumber = Integer.parseInt(roomNumberStr); // 房間數量
				Timestamp checkInDate = transferStringToTimestamp(checkInDateStr, "15:00:00");
				Timestamp checkOutDate = transferStringToTimestamp(checkOutDateStr, "11:00:00");
				// 這邊是接使用者傳來的訂房資訊就是點我要訂房內的key的資料資訊 ,再把資料打包成下列的型態
				bookRoomRequestVO = new BookRoomRequestVO(roomCategoryId, checkInDate, checkOutDate, memNumber,
						roomNumber, memberDataStr);

				// TODO 確認是否還有剩餘房間

				// 取得房型名稱  
				RtService rtSvc = new RtService();
				RtVO rtVO = rtSvc.getOneRt(roomCategoryId);
				bookRoomRequestVO.setRoomName(rtVO.getRoomName());
				/***************************
				 * 2.房間加入購物車
				 *****************************************/

				Object roomCarts = session.getAttribute("roomCarts");// 從session取得 先寫getAttribute是為了說 萬一購物車內已經有資料先setAttribute了會直接把資料覆蓋掉
				List<BookRoomRequestVO> roomCartList = null;         
				if (roomCarts == null) {                            //若沒取得東西就會增加資料上去
					roomCartList = new ArrayList<BookRoomRequestVO>(); 
					roomCartList.add(bookRoomRequestVO);
				} else {											//若裡面有資料 則比對裡面的資料是否和BookRoomRequestVO內一樣
					roomCartList = (List<BookRoomRequestVO>) roomCarts;
					Boolean roomDateExist = false;
					for (BookRoomRequestVO vo : roomCartList) {
						if (vo.getRoomCategoryId().equals(bookRoomRequestVO.getRoomCategoryId())
								&& vo.getCheckInDate().equals(bookRoomRequestVO.getCheckInDate())
								&& vo.getCheckOutDate().equals(bookRoomRequestVO.getCheckOutDate())) {
							Integer newMemNumber = vo.getMemNumber() + bookRoomRequestVO.getMemNumber();
							Integer newRoomNumber = vo.getRoomNumber() + bookRoomRequestVO.getRoomNumber();

							vo.setMemNumber(newMemNumber);
							vo.setRoomNumber(newRoomNumber);
							roomDateExist = true;
						}
					}

					if (!roomDateExist) {
						roomCartList.add(bookRoomRequestVO);
					}
//					
				}

				session.setAttribute("roomCarts", roomCartList); //上述條件判斷OK後 就會把資料存進去
				System.out.println("購物車 bookRoomRequestVO:" + roomCartList);

			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				errorMsgs.add(e.getMessage());
				this.failAndPrintErrorMsg(req, res, roomCategoryId, errorMsgs);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorMsgs.add(e.getMessage());
				this.failAndPrintErrorMsg(req, res, roomCategoryId, errorMsgs);
			}
		}

		if ("delete".equals(action)) { //若今天沒有加checkInDateStr checkOutDateStr這兩個條件 假設都是二號房  系統在刪除時 沒辦法辨識要刪除哪一個資訊  會依順序刪除資料
			try {
				String roomCategoryIdstr = req.getParameter("roomCategoryId");
				String checkInDateStr = req.getParameter("checkInDate");
				String checkOutDateStr = req.getParameter("checkOutDate");
				System.out.println("=============== book room 購物車 ================");
				System.out.println("roomCategoryIdstr:" + roomCategoryIdstr);
				System.out.println("checkInDateStr:" + checkInDateStr);
				System.out.println("checkOutDateStr:" + checkOutDateStr);
				System.out.println("=====================================");

				// 這邊是input hidden傳送過來，一定是正確，不需要驗證
				roomCategoryId = Integer.parseInt(roomCategoryIdstr);

				Timestamp checkInDate = transferStringToTimestamp(checkInDateStr);
				Timestamp checkOutDate = transferStringToTimestamp(checkOutDateStr);
				
				bookRoomRequestVO.setRoomCategoryId(roomCategoryId);
				bookRoomRequestVO.setCheckInDate(checkInDate);
				bookRoomRequestVO.setCheckOutDate(checkOutDate);

				Object roomCarts = session.getAttribute("roomCarts");// 從session取得
				List<BookRoomRequestVO> roomCartList = (List<BookRoomRequestVO>) roomCarts;
				for (BookRoomRequestVO vo : roomCartList) {
					if (vo.getRoomCategoryId().equals(bookRoomRequestVO.getRoomCategoryId())){
//							&& vo.getCheckInDate().equals(bookRoomRequestVO.getCheckInDate())
//							&& vo.getCheckOutDate().equals(bookRoomRequestVO.getCheckOutDate())) {
							
						roomCartList.remove(vo);
						break;
					}
				}

				session.setAttribute("roomCarts", roomCartList);
				System.out.println("購物車 bookRoomRequestVO:" + roomCartList);
				
				String url = "/front-end/rmtype/roomCarts.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				errorMsgs.add(e.getMessage());
				this.failAndPrintErrorMsg(req, res, roomCategoryId, errorMsgs);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorMsgs.add(e.getMessage());
				this.failAndPrintErrorMsg(req, res, roomCategoryId, errorMsgs);
			}
		}
	}

	private List<String> verifyParameterEmpty(String checkInDateStr, String checkOutDateStr, String memNumberStr,
			String roomNumberStr, String memberDataStr, List<String> errorMsgs) {

		if (checkInDateStr == null || (checkInDateStr.trim()).length() == 0) {
			errorMsgs.add("請輸入住房日期");
		}

		if (checkOutDateStr == null || (checkOutDateStr.trim()).length() == 0) {
			errorMsgs.add("請輸入退房日期");
		}

		if (memNumberStr == null || (memNumberStr.trim()).length() == 0) {
			errorMsgs.add("請輸入總人數");
		}

		if (memberDataStr == null) {
			errorMsgs.add("請勾選 系統將帶入您的個人資料");
		}

		try {
			new Integer(memNumberStr);
		} catch (Exception e) {
			errorMsgs.add("總人數格式不正確");
		}

		try {
			new Integer(roomNumberStr);
		} catch (Exception e) {
			errorMsgs.add("訂房間量不正確");
		}

		return errorMsgs;

	}

	private void failAndPrintErrorMsg(HttpServletRequest req, HttpServletResponse res, Integer roomCategoryId,
			List<String> errorMsgs) throws ServletException, IOException {

		StringBuilder errorMsg = new StringBuilder();
		for (String msg : errorMsgs) {
			errorMsg.append(msg + "\n");
		}

		System.out.println("errorMsg:" + errorMsg);

		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(errorMsg.toString());

	}

	private Timestamp transferStringToTimestamp(String dateStr, String timeStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		dateStr = dateStr + " " + timeStr;
		ts = Timestamp.valueOf(dateStr);
		return ts;
	}
	
	private Timestamp transferStringToTimestamp(String dateStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		ts = Timestamp.valueOf(dateStr);
		return ts;
	}

}
