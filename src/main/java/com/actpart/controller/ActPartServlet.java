package com.actpart.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actpart.model.ActPartService;
import com.actpart.model.ActPartVO;

//@WebServlet("/ActPartServlet.do")
public class ActPartServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer memId = null;
				try {
					memId = new Integer(req.getParameter("memId").trim());
				} catch (NumberFormatException e) {
					memId = 1;
					errorMsgs.add("活動類別請填數字.");
				}

				
				java.sql.Timestamp actApplyDate = null;
				try {
					actApplyDate = java.sql.Timestamp.valueOf(req.getParameter("actApplyDate").trim());
				} catch (IllegalArgumentException e) {
					actApplyDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}


				Integer actStar = null;
				try {
					actStar = new Integer(req.getParameter("actStar").trim());
				} catch (NumberFormatException e) {
					actStar = 0;
					errorMsgs.add("活動報名人數請填數字.");
				}

				
			
				ActPartVO actPartVO = new ActPartVO();
				actPartVO.setMemId(memId);
				actPartVO.setActApplyDate(actApplyDate);
				actPartVO.setActStar(actStar);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actPartVO", actPartVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/addActPart.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ActPartService actPartSvc = new ActPartService();
				actPartVO = actPartSvc.addActPart(memId, actApplyDate, actStar);


				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "front-end/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/addAct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
