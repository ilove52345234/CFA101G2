package com.shoporderdetail.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.shoporderdetail.model.*;

@WebServlet("/back-end/shoporderdetail/shoporderdetail.do")
public class ShopOrderDetailServlet extends HttpServlet{

	private static final long serialVersionUID = 8542437415001564432L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("ShopServlet");

		//
		if ("getShop_Join_Detail".equals(action)) { // 來自listAllShopOrderDetail.jsp的請求
//			System.out.println("ShopOrderDetailServlet-getShop_Join_Detail");
			List<String> errorMsgs = new LinkedList<String>();
			// 將此集合存儲在請求範圍中，以防我們需要
			// 發送錯誤頁面視圖
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer itemOrderId = new Integer(req.getParameter("itemOrderId"));
//				System.out.println("itemOrderId: "+itemOrderId);

				/*************************** 2.開始查詢資料 ****************************************/
				ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
				List<ShopOrderDetailVO> shopOrderDetailVO = shopOrderDetailSvc.shopJoinDetail(itemOrderId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shopOrderDetailVO", shopOrderDetailVO); // 資料庫取出的shopVO物件,存入req
				// 修改資料，跳到updateShop.jsp頁面
				String url = "/back-end/shoporderdetail/listAllShopOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateShop.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shoporder/searchOrder.jsp");
				failureView.forward(req, res);
			}
		}
	}
}