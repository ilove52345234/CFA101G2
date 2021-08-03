package com.actpromo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actpromo.model.ActPromoService;
import com.actpromo.model.ActPromoVO;

/**
 * Servlet implementation class ActPromoServlet
 */
@WebServlet("/actpromo/ActPromoServlet")
public class ActPromoServlet extends HttpServlet {
       
    
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);  //both GET and POST HTTP requests will treat as POST in this controller.
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
	
			req.setAttribute("errorMsgs", errorMsgs);
		
		
			 
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
				String str = req.getParameter("actPromotionId");
				
				if (str==null||str.trim().length()==0) {
					errorMsgs.add("請輸入活動優惠編號");
				}
				
				Integer actPromotionId =null;
				try {
					actPromotionId = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("活動優惠編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料----Start query by calling DAO Service.*****************************************/
				ActPromoService actPromoSvc = new ActPromoService();
				ActPromoVO actPromoVO = actPromoSvc.getOneById(actPromotionId);
				if (actPromoVO== null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.*************/
				req.setAttribute("actPromoVO", actPromoVO);
				String url = "/back-end/actpromo/listOneActPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneActPromo.jsp
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				//1. if前端顯示看得懂的Exception Msg: 從 Controller後面的 DAO throw出來的Runtime Exception，
				//會從這?catch，並setAttribute到request
				//再forward request回去原先發出request的View(JSP);

				//2. if前端顯示空白的Exception Msg: 如果View（JSP）的input name有錯誤（ex: job多了一個space >>> “job_” 而不是“job”)，
				//也會在這?被catch，但不會有errorMsg.
				//此時暫時把try-catch block註解掉，直接跑這段code，看哪一行發生NullPointerException再回去check View（JSP).
				
				errorMsgs.add("無法取得資料:" + e.getMessage());
				//這?把errorMsg 加入list之後，
				//在try-catch block之外預先註冊的 req.setAttribute("errorMsgs", errorMsgs); 就會同步新增msg進去request。
				//之後再forward request去前端的View(JSP)
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpromo/select_page.jsp");
				failureView.forward(req, res);

			}
	   }//end of if
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllActPromo.jsp的請求
			//1. 先執行getOne 再執行Update
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this LinkedList in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//2.getOne
				/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
				Integer actPromotionId = new Integer(req.getParameter("actPromotionId"));
				
				/***************************2.開始查詢資料----Start query by calling DAO Service.*****************************************/
				ActPromoService actPromoSvc = new ActPromoService();
				ActPromoVO actPromoVO = actPromoSvc.getOneById(actPromotionId);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.*************/
				req.setAttribute("actPromoVO", actPromoVO);
				String url = "/back-end/actpromo/update_actpromo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneActPromo.jsp
				successView.forward(req, res);
			
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpromo/listAllActPromo.jsp");
				failureView.forward(req, res);
			}
	     }// end of if
		if ("update".equals(action)) { // 來自update_actpromo_input.jsp的請求 <input type="hidden" name="action" value="update">
			List<String> errorMsgs = new LinkedList<String>();
			// Store this LinkedList in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//3. 執行Update
				/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
				Integer actPromotionId = new Integer(req.getParameter("actPromotionId").trim());
				
				String actPromotionName = req.getParameter("actPromotionName"); // Not Null
				
				String actPromotionNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,11}$";
				if(actPromotionName==null||actPromotionName.trim().length()==0) {
					errorMsgs.add("活動優惠名稱：請勿空白");
				} else if (!actPromotionName.trim().matches(actPromotionNameReg)){
					errorMsgs.add("活動優惠名稱只能是 中、英文字母、數字和_，且長度介於3到11之間");
				}
				
				
				Double actPromotionDiscount = null;
				try {
					actPromotionDiscount =new Double(req.getParameter("actPromotionDiscount").trim()); // Not Null
				} catch (NumberFormatException e) {
					actPromotionDiscount = 0.0;
					errorMsgs.add("活動優惠折數請填小數. ex: 0.88");
				}
				
				ActPromoVO actPromoVO = new ActPromoVO();
				actPromoVO.setActPromotionId(actPromotionId);
				actPromoVO.setActPromotionName(actPromotionName);
				actPromoVO.setActPromotionDiscount(actPromotionDiscount);
				
				if (!errorMsgs.isEmpty()) {
				req.setAttribute("actPromoVO", actPromoVO);  
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/update_actpromo_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始更新資料----Start update by calling DAO Service.*****************************************/
				ActPromoService actPromoService = new ActPromoService();
				actPromoService.updateActPromo(actPromotionId, actPromotionName, actPromotionDiscount);
				
				/***************************3.更新完成,準備轉交(Send the Success view)---Finish Update, Send the Success view.*************/
				req.setAttribute("actPromoVO", actPromoVO);  
				String url = "/back-end/actpromo/listOneActPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneActPromo.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpromo/update_actpromo_input.jsp");
				failureView.forward(req, res);
			}
		}//end of if
		
	    if ("insert".equals(action)) { // 來自addActPromo.jsp的請求  
	    	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/				
				String actPromotionName = req.getParameter("actPromotionName"); // Not Null
				
				String actPromotionNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,11}$";
				if(actPromotionName==null||actPromotionName.trim().length()==0) {
					errorMsgs.add("活動優惠名稱：請勿空白");
				} else if (!actPromotionName.trim().matches(actPromotionNameReg)){
					errorMsgs.add("活動優惠名稱只能是 中、英文字母、數字和_，且長度介於3到11之間");
				}
				
				
				Double actPromotionDiscount = null;
				try {
					actPromotionDiscount =new Double(req.getParameter("actPromotionDiscount").trim()); // Not Null
				} catch (NumberFormatException e) {
					actPromotionDiscount = 0.0;
					errorMsgs.add("活動優惠折數請填小數. ex: 0.66");
				}
				
				ActPromoVO actPromoVO = new ActPromoVO();
				actPromoVO.setActPromotionName(actPromotionName);
				actPromoVO.setActPromotionDiscount(actPromotionDiscount);
				
				if (!errorMsgs.isEmpty()) {
				req.setAttribute("actPromoVO", actPromoVO);  
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/update_actpromo_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始更新資料----Start update by calling DAO Service.*****************************************/
				ActPromoService actPromoService = new ActPromoService();
				actPromoService.addActPromo(actPromotionName, actPromotionDiscount);
				
				/***************************3.更新完成,準備轉交(Send the Success view)---Finish Update, Send the Success view.*************/
				req.setAttribute("actPromoVO", actPromoVO);  
				String url = "/back-end/actpromo/listAllActPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllActPromo.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpromo/addActPromo.jsp");
				failureView.forward(req, res);
			}
			
	    }//end of if 
	    
		if ("delete".equals(action)) { // 來自listAllActPromo.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
				Integer actPromotionId = new Integer(req.getParameter("actPromotionId"));
				
				/***************************2.開始刪除資料----Start delete by calling DAO Service.*****************************************/
				ActPromoService actPromoService  = new ActPromoService();
				actPromoService.deleteActPromo(actPromotionId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)---Finish Delete, Send the Success view.*************/
				String url = "/back-end/actpromo/listAllActPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpromo/listAllActPromo.jsp");
				failureView.forward(req, res);
			}
	
		}//end of if    
  }
}
