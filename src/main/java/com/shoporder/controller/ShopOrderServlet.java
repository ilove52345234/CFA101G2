package com.shoporder.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.shop.model.ShopService;
import com.shop.model.ShopVO;
import com.shoporder.model.*;

@WebServlet(urlPatterns = { "/back-end/shoporder/shoporder.do" })
public class ShopOrderServlet extends HttpServlet {

	private static final long serialVersionUID = -8038864886930081378L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增
//		if ("insert".equals(action)) { // 來自addShop.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// 將此集合存儲在請求範圍中，以防我們需要
//			// 發送錯誤頁面視圖
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//
//				Integer itemOrderId = null;
//				try {
//					itemOrderId = new Integer(req.getParameter("itemOrderId").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("訂單編號：請勿空白");
//				}
//
//				Integer memId = null;
//				try {
//					memId = new Integer(req.getParameter("memId").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("會員編號：請勿空白");
//				}
//
//				String itemOrderDate = req.getParameter("itemOrderDate").trim();
//				if (itemOrderDate == null || itemOrderDate.trim().length() == 0) {
//					errorMsgs.add("訂單成立時間：請勿空白");
//				}
//
//				Integer itemAmounts = null;
//				try {
//					itemAmounts = new Integer(req.getParameter("itemAmounts").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("訂單總金額：請勿空白");
//				}
//
//				Byte paymentMethod = null;
//				try {
//					paymentMethod = new Byte(req.getParameter("paymentMethod").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("付款方式：請勿空白");
//				}
//
//				Byte shippingMethod = null;
//				try {
//					shippingMethod = new Byte(req.getParameter("shippingMethod").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("運送方式：請勿空白");
//				}
//
//				Byte shippingStatus = null;
//				try {
//					shippingStatus = new Byte(req.getParameter("shippingStatus").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("訂單狀態：請勿空白");
//				}
//
//				ShopOrderVO shopOrderVO = new ShopOrderVO();
//
//				shopOrderVO.setItemOrderId(itemOrderId);
//				shopOrderVO.setMemId(memId);
//				shopOrderVO.setItemOrderDate(itemOrderDate);
//				shopOrderVO.setItemAmounts(itemAmounts);
//				shopOrderVO.setPaymentMethod(paymentMethod);
//				shopOrderVO.setShippingMethod(shippingMethod);
//				shopOrderVO.setShippingStatus(shippingStatus);
//
//				// 如果有錯誤，請將使用發送回表單
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("shopOrderVO", shopOrderVO); // 含有輸入格式錯誤的ShopVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/addShopOrder.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				ShopOrderService shopOrderSvc = new ShopOrderService();
//				shopOrderVO = shopOrderSvc.addShopOrder(itemOrderId, memId, itemOrderDate, itemAmounts, paymentMethod,
//						shippingMethod, shippingStatus);
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/shoporder/listAllShopOrder.jsp"; // 新增完訂單後，畫面轉到查看所有訂單的頁面
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllShop.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				e.printStackTrace();
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/addShopOrder.jsp");
//				failureView.forward(req, res);
//			}
//		}

		// 修改
		if ("update".equals(action)) { // 來自changeOrderStatus.jsp的請求

			System.out.println("ShopOrderServlet-update");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer itemOrderId = new Integer(req.getParameter("itemOrderId"));

				Integer memId = new Integer(req.getParameter("memId"));

				String itemOrderDate = new String(req.getParameter("itemOrderDate"));

				Integer itemAmounts = new Integer(req.getParameter("itemAmounts"));

				Byte paymentMethod = null;
				paymentMethod = new Byte(req.getParameter("paymentMethod"));
				System.out.println("paymentMethod: " + paymentMethod);

				Byte shippingMethod = null;
				shippingMethod = new Byte(req.getParameter("shippingMethod"));
				System.out.println("shippingMethod: " + shippingMethod);

				Byte shippingStatus = null;
				shippingStatus = new Byte(req.getParameter("shippingStatus"));
				System.out.println("shippingStatus: " + shippingStatus);

				ShopOrderVO shopOrderVO = new ShopOrderVO();

				shopOrderVO.setMemId(memId);
				shopOrderVO.setItemOrderDate(itemOrderDate);
				shopOrderVO.setItemAmounts(itemAmounts);
				shopOrderVO.setPaymentMethod(paymentMethod);
				shopOrderVO.setShippingMethod(shippingMethod);
				shopOrderVO.setShippingStatus(shippingStatus);
				shopOrderVO.setItemOrderId(itemOrderId);
				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopOrderVO", shopOrderVO); // 含有輸入格式錯誤的shopOrderVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shoporder/changeOrderStatus.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				shopOrderVO = shopOrderSvc.updateShopOrder(itemOrderId, memId, itemOrderDate, itemAmounts,
						paymentMethod, shippingMethod, shippingStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopOrderVO", shopOrderVO); // 資料庫update成功後,正確的的ShopVO物件,存入req
				String url = "/back-end/shoporder/listOneShopOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneShop.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shoporder/changeOrderStatus.jsp");
				failureView.forward(req, res);
			}
		}

		// 拿取一個來 修改 or 刪除
		if ("getOne_For_Update".equals(action)) { // 來自listAllShopOrder.jsp的請求
			System.out.println("ShopOrderServlet-getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// 將此集合存儲在請求範圍中，以防我們需要
			// 發送錯誤頁面視圖
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer itemOrderId = new Integer(req.getParameter("itemOrderId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				ShopOrderVO shopOrderVO = shopOrderSvc.getOneShopOrder(itemOrderId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shopOrderVO", shopOrderVO); // 資料庫取出的shopVO物件,存入req
				// 修改資料，跳到updateShopOrderStatus.jsp頁面
				String url = "/back-end/shoporder/changeOrderStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateShopOrderStatus.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/listAllShopOrder.jsp");
				failureView.forward(req, res);
			}
		}

		// 所有商品資料
		if ("getOne_For_Display".equals(action)) { // 來自selectPage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("itemOrderId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/selectOrderPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer itemOrderId = null;
				try {
					itemOrderId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/selectOrderPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				ShopOrderVO shopOrderVO = shopOrderSvc.getOneShopOrder(itemOrderId);
				if (shopOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/selectOrderPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopOrderVO", shopOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/shop/backEndPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/selectOrderPage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("searchOrder".equals(action)) {
			String startDate = req.getParameter("startDate");
			System.out.println("startDate= " + startDate);
			String endDate = req.getParameter("endDate");
			System.out.println("endDate= " + endDate);
			
//			Integer pageNumber = Integer.valueOf(req.getParameter("pageNumber"));
//			Integer pageSize = Integer.valueOf(req.getParameter("pageSize"));
			
			
			ShopOrderService shopOrderSvc = new ShopOrderService();
			List<ShopOrderVO> shopOrderVOList = shopOrderSvc.getIntervalOrder(startDate,endDate);
			// [1,2,3,4,5] [6,7]
			int shopListCount = shopOrderVOList.size();
//			shopOrderVOList = shopOrderVOList.subList((pageNumber-1) * pageSize,pageNumber * pageSize > shopListCount ? shopListCount : pageNumber * pageSize);
			
			
			req.setAttribute("shopOrderVOList", shopOrderVOList); // 資料庫取出的shopVO物件,存入req
			req.setAttribute("shopListCount", shopListCount);
//			req.setAttribute("pageNumber", pageNumber);
//			req.setAttribute("pageSize", pageSize);
			req.setAttribute("startDate", startDate);
			req.setAttribute("endDate", endDate);
			
			
			// 修改資料，跳到updateShopOrderStatus.jsp頁面
			String url = "/back-end/shoporder/listAllShopOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateShopOrderStatus.jsp
			successView.forward(req, res);
			
		}

	}
}