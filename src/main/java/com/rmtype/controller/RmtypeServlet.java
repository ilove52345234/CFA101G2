package com.rmtype.controller;

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

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.rmorder.model.BookRmoVO;
import com.rmorder.model.RmoService;
import com.rmtype.model.RtService;
import com.rmtype.model.RtVO;
import com.rmtypepic.model.RtpService;
import com.rmtypepic.model.RtpVO;

/**
 * Servlet implementation class RmtypeServlet
 */

@WebServlet("/rmtype/RmtypeServlet")
public class RmtypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RmtypeServlet() {
		super();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");



		//下拉式選單功能的瀏覽功能
		if ("find_roomname".equals(action)) {
			System.out.println("RmtypeServlet，action為find_roomname");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

//				======================= 1.使用ROOMNAME查詢===============
				String rmname = req.getParameter("roomName");
				System.out.println("RtService request getParameter name is roomName, value is "+rmname);
				String rmName = new String(rmname.getBytes("ISO-8859-1"), "UTF-8");
				List<String> listRn = new ArrayList<String>();
				if (!"".equals(rmName.trim())) {
					listRn.add(rmName);
				}
				/*************************** 2.開始查詢資料 *****************************************/
				RtService rtSvc = new RtService(); // 建立一個RtService物件取得裡面的rtVO的值
				RtVO rtVO = rtSvc.getRoomName(rmname);
				System.out.println("RtService getRoomName rtVO:"+rtVO);
				List<RtVO> roomTypeResultList=new ArrayList<>();
				roomTypeResultList.add(rtVO);

				List<RtVO> roomTypeAllList = rtSvc.getAll();   //對應138行

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				// 資料庫取出的rtVO物件,存入req
				req.setAttribute("list", roomTypeResultList);
				req.setAttribute("roomTypeAllList", roomTypeAllList);
				String url = "/front-end/rmtype/listAllRmtype.jsp";
				System.out.println("forward url:"+url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/listAllRmtype.jsp");
				failureView.forward(req, res);
			}

		}

		//進入網頁listAllRmtype的總覽
		if ("getAllRoomType".equals(action)) {
			System.out.println("RmtypeServlet，action為getAllRoomType");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				//	======================= 1.使用ROOMNAME查詢===============

				/*************************** 2.開始查詢資料 *****************************************/
				RtService rtSvc = new RtService();
				List<RtVO> roomTypeAllList = rtSvc.getAll();
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", roomTypeAllList);            //進入listAllRmtype到第１２行
				req.setAttribute("roomTypeAllList", roomTypeAllList); // 資料庫取出的rtVO物件,存入req 若兩個名稱都用list會出錯
				String url = "/front-end/rmtype/listAllRmtype.jsp";
				System.out.println("forward url:"+url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/listAllRmtype.jsp");
				failureView.forward(req, res);
			}

		}

		//訂房首頁點擊詳細內容會轉交至訂房細節總覽
		if ("getRoomDetail".equals(action)) { // 來自listAllRmtype.jsp的請求 153行

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String roomCategoryIdstr = req.getParameter("roomCategoryId"); //建立一個可以輸入號碼的物件
				if (roomCategoryIdstr == null || (roomCategoryIdstr.trim()).length() == 0) {
					errorMsgs.add("請輸入房型編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/listAllRmtype.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer roomCategoryId = null;
				try {
					roomCategoryId = new Integer(roomCategoryIdstr);
				} catch (Exception e) {
					errorMsgs.add("房型編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/listAllRmtype.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RtService rtSvc = new RtService();  //建立一個RtService物件取得裡面的rtVO的值
				RtVO rtVO = rtSvc.getOneRt(roomCategoryId);
				RtpService rtpSvc =new RtpService();
				List<RtpVO> rtpVOs = rtpSvc.getRmCategoryId(roomCategoryId);
				System.out.println("getRoomDetail的rtVO:"+rtVO);
				System.out.println("rtpVOs:"+rtpVOs);
				if (rtVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/listAllRmtype.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rtVO", rtVO); // 資料庫取出的rtVO物件,存入req
				req.setAttribute("rtpVOs", rtpVOs);
				String url = "/front-end/rmtype/reserveRmType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 reserveRmType.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 (建議可以做重導到錯誤頁面像系統忙線中)*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rmtype/reserveRmType.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
