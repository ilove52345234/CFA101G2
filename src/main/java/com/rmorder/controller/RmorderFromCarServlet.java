package com.rmorder.controller;

import java.io.IOException;
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
 * servlet要做的事情很簡單 只要把使用者請求的資料給接受處理後 並返還給使用者
 * Servlet implementation class RmorderServlet
 */
@WebServlet("/rmorder/RmorderFromCartServlet")
public class RmorderFromCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RmorderFromCarServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		Integer roomCategoryId=null;
		HttpSession session = req.getSession();  //接濾器
		
			System.out.println("RmorderServlet，action為getRoomOrder");
		try {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Object roomCarts = session.getAttribute("roomCarts");// 從session取得
			List<BookRoomRequestVO> roomCartList = null;
			if (roomCarts == null) {
				errorMsgs.add("購物車沒房間資料");
			} else {
				roomCartList = (List<BookRoomRequestVO>) roomCarts;
			}

			/*************************** 2.開始訂房 *****************************************/
			
			MemVO memVO=(MemVO)session.getAttribute("memVO");//從session取得
			System.out.println("memVO:" + memVO);

			List<MemRoomOrderVO> memRoomOrderVOs=null;
			RmoService rmoSvc = new RmoService();
			for(BookRoomRequestVO bookRoomRequestVO:roomCartList) {
				//bookRoomRequestVO的源頭是先從上方開始宣告  而不是直接連到bookRoomRequestVO.java可以點選VO來追蹤源頭
				 memRoomOrderVOs = rmoSvc.bookPreRoomOrder(bookRoomRequestVO, memVO);
				//bookRmoVO是 把使用者資料(bookRoomRequestVO, memVO)帶入rmoSvc內的方法bookPreRoomOrder後經過一些業務邏輯而帶出來的物件

				System.out.println("memRoomOrderVO:" + memRoomOrderVOs);
				if (memRoomOrderVOs == null) {
					errorMsgs.add("查無資料");
				}
			}
			
			if (errorMsgs.isEmpty() == false) {
				this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);
				return;
			}
			
			session.removeAttribute("roomCarts");

			// Send the use back to the form, if there were errors

/*************************** 3.查詢完成,準備轉交(Send the Success view) 這邊做的事只是將使用者req的資料送出後由servlet做回應*************/
			req.setAttribute("memRoomOrderVOs", memRoomOrderVOs); // 資料庫取出的bookRmoVO物件,存入req

			String url = "/front-end/rmtype/roomOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 roomOrderList.jsp
			successView.forward(req, res);

			/***************************
			 * 其他可能的錯誤處理 (建議可以做重導到錯誤頁面像系統忙線中)
			 *************************************/

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			errorMsgs.add(e.getMessage());
			this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorMsgs.add(e.getMessage());
			this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);
		}
	}

	
	private List<String> verifyParameterEmpty(String checkInDateStr, String checkOutDateStr,
			String memNumberStr, String roomNumberStr, Object memberDataObj, List<String> errorMsgs) {

		if (checkInDateStr == null || (checkInDateStr.trim()).length() == 0) {
			errorMsgs.add("請輸入住房日期");
		}

		if (checkOutDateStr == null || (checkOutDateStr.trim()).length() == 0) {
			errorMsgs.add("請輸入退房日期");
		}

		if (memNumberStr == null || (memNumberStr.trim()).length() == 0) {
			errorMsgs.add("請輸入總人數");
		}

		if (memberDataObj == null) {
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

	private void failAndForwardToOriginPage(HttpServletRequest req, HttpServletResponse res,
			Integer roomCategoryId , List<String> errorMsgs) throws ServletException, IOException {
		RtService rtSvc = new RtService();
		RtVO rtVO = rtSvc.getOneRt(roomCategoryId);
		req.setAttribute("rtVO", rtVO);
		req.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/roomCarts.jsp");
		failureView.forward(req, res);
	}

	private Timestamp transferStringToTimestamp(String dateStr, String timeStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		dateStr = dateStr + " " + timeStr;
		ts = Timestamp.valueOf(dateStr);
		return ts;
	}

}
