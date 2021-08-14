package com.rmorder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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
import com.rschedule.model.RsService;
import com.rschedule.model.RsVO;

/**
 * servlet要做的事情很簡單 只要把使用者請求的資料給接受處理後 並返還給使用者
 * Servlet implementation class RmorderServlet
 */
@WebServlet("/rmorder/RmorderServlet")
public class RmorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RmorderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//從這邊先宣告bookRoomRequestVO
		BookRoomRequestVO bookRoomRequestVO = new BookRoomRequestVO();
		List<String> errorMsgs = new LinkedList<String>();
		Integer roomCategoryId=null;
		HttpSession session = req.getSession();  //接濾器
		
		if ("getRoomOrder".equals(action)) {
			System.out.println("RmorderServlet，action為getRoomOrder");
		try {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 畫面上input name需要跟下面getParameter的name對應到
			
			// request getParameter為null的話，才從session取得
			// 若經過濾器，先redirect到login.jsp之前先把資料放在session
			String roomCategoryIdstr = req.getParameter("roomCategoryId")==null ? session.getAttribute("roomCategoryId").toString() :req.getParameter("roomCategoryId");
			String checkInDateStr = req.getParameter("checkInDate")==null ? session.getAttribute("checkInDate").toString() :req.getParameter("checkInDate");
			String checkOutDateStr = req.getParameter("checkOutDate")==null ? session.getAttribute("checkOutDate").toString() :req.getParameter("checkOutDate");
			String memNumberStr = req.getParameter("memNumber")==null ? session.getAttribute("memNumber").toString() :req.getParameter("memNumber");
			String roomNumberStr = req.getParameter("roomNumber")==null ? session.getAttribute("roomNumber").toString() :req.getParameter("roomNumber");
			Object memberDataObj = req.getParameter("getMemberData")==null ? session.getAttribute("getMemberData") :req.getParameter("getMemberData");
//  		String memberDataStr = req.getParameter("getMemberData")==null ? session.getAttribute("getMemberData").toString() :req.getParameter("getMemberData");
			
			
			
			// session.getAttribute(...) 回傳型別為object, 呼叫.toString()轉成String
			// req.getParameter(...) 回傳型別為String
			
			// form summit input為text時，若為空傳來的是" "(空字串)，但是input為checkbox時，若沒有打勾，傳來的是null
			// 如果session.getAttribute(...)為null, 呼叫.toString()會出現nullPointException
			
			
			System.out.println("=============== book room request ================");
			System.out.println("roomCategoryIdstr:" + roomCategoryIdstr);
			System.out.println("checkInDateStr:" + checkInDateStr);
			System.out.println("checkOutDateStr:" + checkOutDateStr);
			System.out.println("memNumberStr:" + memNumberStr);
			System.out.println("roomNumberStr:" + roomNumberStr);
			System.out.println("memberDataObj:" + memberDataObj);
			System.out.println("=====================================");
			
			// 這邊是input hidden傳送過來，一定是正確，不需要驗證
			roomCategoryId = Integer.parseInt(roomCategoryIdstr);

			// form傳送過來的資訊是否為空，格式是否正確
			errorMsgs = this.verifyParameterEmpty(checkInDateStr, checkOutDateStr, memNumberStr,
					roomNumberStr, memberDataObj, errorMsgs);
			if (errorMsgs.isEmpty() == false) {
				this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);
				return;
			}

			Integer memNumber = Integer.parseInt(memNumberStr);     //人數
			Integer roomNumber = Integer.parseInt(roomNumberStr);   //房間數量
			Timestamp checkInDate = transferStringToTimestamp(checkInDateStr, "15:00:00");
			Timestamp checkOutDate = transferStringToTimestamp(checkOutDateStr, "11:00:00");
			//這邊是接使用者傳來的訂房資訊就是點我要訂房內的key的資料資訊 ,再把資料打包成下列的型態
			bookRoomRequestVO = new BookRoomRequestVO(roomCategoryId, checkInDate, checkOutDate, memNumber, roomNumber,memberDataObj);

//			// TODO 確認是否還有剩餘房間

			RsService rsService =  new RsService();

			Date date = new Date(checkInDate.getTime());
//
			List<RsVO> oneValidAmount = rsService.getOneValidAmount(roomCategoryId,date,date);

			for (RsVO rsVO : oneValidAmount) {
				System.out.println("預定表:"+rsVO);
				if (bookRoomRequestVO.getRoomNumber() > rsVO.getRoomValidAmount()) {
					errorMsgs.add("日期：" + rsVO.getRoomScheduleDate() + "，房型：" + rsVO.getRoomCategoryId()
							+ "，已無剩餘房間");
				}
			}
//
			if (errorMsgs.isEmpty() == false) {
				String s = errorMsgs.toString();
				System.out.println(s);
				this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);
				return;
			}

			/*************************** 2.開始訂房 *****************************************/
			
			MemVO memVO=(MemVO)session.getAttribute("memVO");//從session取得
			System.out.println("memVO:" + memVO);

			RmoService rmoSvc = new RmoService();

			//bookRoomRequestVO的源頭是先從上方開始宣告  而不是直接連到bookRoomRequestVO.java可以點選VO來追蹤源頭
			List<MemRoomOrderVO> memRoomOrderVOs = rmoSvc.bookPreRoomOrder(bookRoomRequestVO, memVO);
			//bookRmoVO是 把使用者資料(bookRoomRequestVO, memVO)帶入rmoSvc內的方法bookPreRoomOrder後經過一些業務邏輯而帶出來的物件

			System.out.println("memRoomOrderVO:" + memRoomOrderVOs);
			if (memRoomOrderVOs == null) {
				errorMsgs.add("查無資料");
			}

			if (errorMsgs.isEmpty() == false) {
				this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);
				return;
			}

			// Send the use back to the form, if there were errors

/*************************** 3.查詢完成,準備轉交(Send the Success view) 這邊做的事只是將使用者req的資料送出後由servlet做回應*************/
			req.setAttribute("memRoomOrderVOs", memRoomOrderVOs); // 資料庫取出的bookRmoVO物件,存入req
			//req.setAttribute("roomNumber", bookRoomRequestVO.getRoomNumber()); //第二種取值方式
			req.setAttribute("bookRoomRequestVO", bookRoomRequestVO);
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
		
		//===================1.查詢訂單明細==========================	
		if ("getRoomOrderList".equals(action)) {
			System.out.println("RmorderServlet，action為getRoomOrderList");

//		try {
			MemVO memVO=(MemVO)session.getAttribute("memVO");//從session取得
			Integer memberId = memVO.getMemId();
			String orderIdstr = req.getParameter("orderId");
			Integer orderId = null;
			orderId = new Integer(orderIdstr);
//		} catch (Exception e) {
//			errorMsgs.add("編號格式不正確");
//		}
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/roomOrder.jsp");
//			failureView.forward(req, res);
//			return;// 程式中斷
//		}
		//===============2.開始查詢訂單資料=========================
		RmoService rmoSvc = new RmoService();
		BookRmoVO bookRmoVO = rmoSvc.findByBookOrder(memberId, orderId);
		
		if (bookRmoVO == null) {
			errorMsgs.add("查無資料");
		}

		if (errorMsgs.isEmpty() == false) {
			this.failAndForwardToOriginPage(req, res, roomCategoryId, errorMsgs);
			return;
		}
		//==================3.查詢完成,準備轉交================
		req.setAttribute("bookRmoVO", bookRmoVO);
		String url = "/front-end/rmtype/roomOrderList.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 roomOrderList.jsp
		successView.forward(req, res);	
		}
		
		//從會員專區進入的入口
		if ("getMemRoomOrder".equals(action)) {
			System.out.println("RmorderServlet，action為getRoomOrder");
		try {
		

			/*************************** 1.開始查詢 *****************************************/
			
			MemVO memVO=(MemVO)session.getAttribute("memVO");//從session取得
			System.out.println("memVO:" + memVO);

			RmoService rmoSvc = new RmoService();
			//bookRoomRequestVO的源頭是先從上方開始宣告  而不是直接連到bookRoomRequestVO.java可以點選VO來追蹤源頭
			List<MemRoomOrderVO> memRoomOrderVOs = rmoSvc.memRoomorder(memVO);
			//bookRmoVO是 把使用者資料(bookRoomRequestVO, memVO)帶入rmoSvc內的方法bookPreRoomOrder後經過一些業務邏輯而帶出來的物件

			System.out.println("memRoomOrderVO:" + memRoomOrderVOs);
			

			// Send the use back to the form, if there were errors

/*************************** 2.查詢完成,準備轉交(Send the Success view) 這邊做的事只是將使用者req的資料送出後由servlet做回應*************/
			req.setAttribute("memRoomOrderVOs", memRoomOrderVOs); // 資料庫取出的bookRmoVO物件,存入req
			//req.setAttribute("roomNumber", bookRoomRequestVO.getRoomNumber()); //第二種取值方式
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
		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/reserveRmType.jsp");
		failureView.forward(req, res);
	}

	private Timestamp transferStringToTimestamp(String dateStr, String timeStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		dateStr = dateStr + " " + timeStr;
		ts = Timestamp.valueOf(dateStr);
		return ts;
	}

}
