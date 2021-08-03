package com.actpart.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actpart.model.ActPartService;
import com.actpart.model.ActPartVO;


/**
 * Servlet implementation class ActPartServlet
 */
@WebServlet("/actpart/ActPartServlet")
public class ActPartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);  //both GET and POST HTTP reqs will treat as POST in this controller.

	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	     
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getAllPartsByActId".equals(action)){ // 來自/back-end/act/actBackendIndex.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
	
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
				String str = req.getParameter("actId");
				//1. getParameter for pk;
				//2. check if input pk is empty;
				if (str ==null || str.trim().length() ==0) {
					errorMsgs.add("請輸入活動編號");
				} 
				// Send the request back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/actBackendIndex.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				Integer actId = null;
				try {
					actId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/actBackendIndex.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料----Start query by calling DAO Service.*****************************************/
				ActService actService = new ActService();
				ActVO actVO = actService.getOneById(actId);
				
				
				
				if(actVO == null) {   
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/actBackendIndex.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.*************/
				HttpSession session = req.getSession();
				session.setAttribute("actVO", actVO);
//				req.setAttribute("actVO",actVO);  
				String url = "/back-end/actpart/actPartList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 actPartList.jsp
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
						.getRequestDispatcher("/back-end/act/actBackendIndex.jsp");
				failureView.forward(req, res);

			}
		}//end of if
		
		
		
	}

}
