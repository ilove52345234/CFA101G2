package com.actphoto.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.actphoto.model.ActPhotoService;
import com.actphoto.model.ActPhotoVO;

//@WebServlet("/ActPhotoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActPhotoServlet extends HttpServlet {
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if("getOne_For_Display".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("actCategoryId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/actphoto/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer actCategoryId = null;
				try {
					actCategoryId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/actphoto/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************2.開始查詢資料****************************************/
				ActPhotoService actPhotoSvc = new ActPhotoService();
				ActPhotoVO actPhotoVO = actPhotoSvc.getOneById(actCategoryId);
				if (actPhotoVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/actphoto/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				
				/***************************3.查詢完成,準備轉交***********************************/
				req.setAttribute("actPhotoVO", actPhotoVO);
				String url = "/front-end/actphoto/listOneActPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/carte/talkSection.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
