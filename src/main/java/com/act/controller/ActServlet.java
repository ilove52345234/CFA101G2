package com.act.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;

@WebServlet("/ActServlet.do")
public class ActServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actId = null;
				try {
					actId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneById(actId);
				if (actVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/act/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer actId = new Integer(req.getParameter("actId"));
				
				/***************************2.開始查詢資料****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneById(actId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actVO", actVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/act/update_act_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/act/listAllAct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actId = new Integer(req.getParameter("actId").trim());
				
				Integer actCategoryId = null;
				try {
					actCategoryId = new Integer(req.getParameter("actCategoryId").trim());
				} catch (NumberFormatException e) {
					actCategoryId = 1;
					errorMsgs.add("活動類別請填數字.");
				}
				
				Integer actPromotionId = null;
				try {
					actPromotionId = new Integer(req.getParameter("actPromotionId").trim());
				} catch (NumberFormatException e) {
					actPromotionId = 1;
					errorMsgs.add("活動優惠編號請填數字.");
				}
				
				String actDescription = req.getParameter("actDescription").trim();
				if (actDescription == null || actDescription.trim().length() == 0) {
					errorMsgs.add("活動內容請勿空白");
				}	
				
				java.sql.Timestamp actStart = null;
				try {
					actStart = java.sql.Timestamp.valueOf(req.getParameter("actStart").trim());
				} catch (IllegalArgumentException e) {
					actStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp actEnd = null;
				try {
					actEnd = java.sql.Timestamp.valueOf(req.getParameter("actEnd").trim());
				} catch (IllegalArgumentException e) {
					actEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String actStatus = req.getParameter("actStatus").trim();
				if (actStatus == null || actStatus.trim().length() == 0) {
					errorMsgs.add("活動狀態請勿空白");
				}	
				
				Integer actFee = null;
				try {
					actFee = new Integer(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					actFee = 0;
					errorMsgs.add("活動價格請填數字.");
				}

				Integer applicants = null;
				try {
					applicants = new Integer(req.getParameter("applicants").trim());
				} catch (NumberFormatException e) {
					applicants = 1;
					errorMsgs.add("活動報名人數請填數字.");
				}
				
				java.sql.Timestamp partStart = null;
				try {
					partStart = java.sql.Timestamp.valueOf(req.getParameter("partStart").trim());
				} catch (IllegalArgumentException e) {
					partStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp partEnd = null;
				try {
					partEnd = java.sql.Timestamp.valueOf(req.getParameter("partEnd").trim());
				} catch (IllegalArgumentException e) {
					partEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer actMaxPart = null;
				try {
					actMaxPart = new Integer(req.getParameter("actMaxPart").trim());
				} catch (NumberFormatException e) {
					actMaxPart = 50;
					errorMsgs.add("活動報名人數上限請填數字.");
				}
				
				Integer actMinPart = null;
				try {
					actMinPart = new Integer(req.getParameter("actMinPart").trim());
				} catch (NumberFormatException e) {
					actMinPart = 1;
					errorMsgs.add("活動報名人數下限請填數字.");
				}

				ActVO actVO = new ActVO();
				actVO.setActId(actId);
				actVO.setActCategoryId(actCategoryId);
				actVO.setActPromotionId(actPromotionId);
				actVO.setActDescription(actDescription);		
				actVO.setActStart(actStart);
				actVO.setActEnd(actEnd);
				actVO.setActStatus(actStatus);
				actVO.setActFee(actFee);
				actVO.setApplicants(applicants);
				actVO.setPartStart(partStart);
				actVO.setPartEnd(partEnd);
				actVO.setActMaxPart(actMaxPart);
				actVO.setActMinPart(actMinPart);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActService actSvc = new ActService();
				actVO = actSvc.updateAct(actId, actCategoryId, actPromotionId, actDescription, actStart, actEnd, actStatus,
										 actFee, applicants, partStart, partEnd, actMaxPart, actMinPart);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/act/update_act_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer actCategoryId = null;
				try {
					actCategoryId = new Integer(req.getParameter("actCategoryId").trim());
				} catch (NumberFormatException e) {
					actCategoryId = 1;
					errorMsgs.add("活動類別請填數字.");
				}
				
				Integer actPromotionId = null;
				try {
					actPromotionId = new Integer(req.getParameter("actPromotionId").trim());
				} catch (NumberFormatException e) {
					actPromotionId = 1;
					errorMsgs.add("活動優惠編號請填數字.");
				}
				
				String actDescription = req.getParameter("actDescription").trim();
				if (actDescription == null || actDescription.trim().length() == 0) {
					errorMsgs.add("活動內容請勿空白");
				}	
				
				java.sql.Timestamp actStart = null;
				try {
					actStart = java.sql.Timestamp.valueOf(req.getParameter("actStart").trim());
				} catch (IllegalArgumentException e) {
					actStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp actEnd = null;
				try {
					actEnd = java.sql.Timestamp.valueOf(req.getParameter("actEnd").trim());
				} catch (IllegalArgumentException e) {
					actEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String actStatus = req.getParameter("actStatus").trim();
				if (actStatus == null || actStatus.trim().length() == 0) {
					errorMsgs.add("活動狀態請勿空白");
				}	
				
				Integer actFee = null;
				try {
					actFee = new Integer(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					actFee = 0;
					errorMsgs.add("活動價格請填數字.");
				}

				Integer applicants = null;
				try {
					applicants = new Integer(req.getParameter("applicants").trim());
				} catch (NumberFormatException e) {
					applicants = 0;
					errorMsgs.add("活動報名人數請填數字.");
				}
				
				java.sql.Timestamp partStart = null;
				try {
					partStart = java.sql.Timestamp.valueOf(req.getParameter("partStart").trim());
				} catch (IllegalArgumentException e) {
					partStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp partEnd = null;
				try {
					partEnd = java.sql.Timestamp.valueOf(req.getParameter("partEnd").trim());
				} catch (IllegalArgumentException e) {
					partEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer actMaxPart = null;
				try {
					actMaxPart = new Integer(req.getParameter("actMaxPart").trim());
				} catch (NumberFormatException e) {
					actMaxPart = 0;
					errorMsgs.add("活動報名人數上限請填數字.");
				}
				
				Integer actMinPart = null;
				try {
					actMinPart = new Integer(req.getParameter("actMinPart").trim());
				} catch (NumberFormatException e) {
					actMinPart = 0;
					errorMsgs.add("活動報名人數下限請填數字.");
				}

				ActVO actVO = new ActVO();
				actVO.setActCategoryId(actCategoryId);
				actVO.setActPromotionId(actPromotionId);
				actVO.setActDescription(actDescription);		
				actVO.setActStart(actStart);
				actVO.setActEnd(actEnd);
				actVO.setActStatus(actStatus);
				actVO.setActFee(actFee);
				actVO.setApplicants(applicants);
				actVO.setPartStart(partStart);
				actVO.setPartEnd(partEnd);
				actVO.setActMaxPart(actMaxPart);
				actVO.setActMinPart(actMinPart);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActService actSvc = new ActService();
				actVO = actSvc.addAct(actCategoryId, actPromotionId, actDescription, actStart, actEnd, actStatus, 
									  actFee, applicants, partStart, partEnd, actMaxPart, actMinPart);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "front-end/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/act/addAct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
