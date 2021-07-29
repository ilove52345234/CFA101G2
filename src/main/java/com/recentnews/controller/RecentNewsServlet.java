package com.recentnews.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;
import com.recentnews.model.RecentNewsService;
import com.recentnews.model.RecentNewsVO;
//@WebServlet("/news/news.do")
public class RecentNewsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//新增消息
		if("addNews".equals(action)) { //來自addNews.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
						
		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/	
			//消息內容
			String newsContent = req.getParameter("newsContent");
			if (newsContent == null || newsContent.trim().length() == 0) {
				errorMsgs.put("newsContent", "內文請勿空白");
			} 
			
			//消息時間
			Timestamp newsTime = new Timestamp(System.currentTimeMillis());
			
			//消息狀態
			Integer newsStatus = null;
			if (req.getParameter("newsStatus") != null && !"".equals(req.getParameter("newsStatus"))) {
				newsStatus = new Integer(req.getParameter("newsStatus").trim());
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/addNews.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ****************************************/
			RecentNewsService recentNewsService = new RecentNewsService();
			recentNewsService.addNews(newsContent, newsTime, newsStatus);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/news/showNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交showNews.jsp
			successView.forward(req, res);
			/*************************** 其他可能的錯誤處理 ***********************************/
			} catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/news/showNews.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		// 更新消息
		if ("getNewsUpdate".equals(action)) { // 來自showNews.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer newsId = new Integer(req.getParameter("newsId"));
				
				/***************************2.開始查詢資料****************************************/
				RecentNewsService recentNewsSvc = new RecentNewsService();
				RecentNewsVO recentNewsVO = recentNewsSvc.getOneNews(newsId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("recentNewsVO", recentNewsVO);
				String url = "/news/updateNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/showNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		//上傳更新
		if ("update".equals(action)) { // 來自updateNews.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer newsId = new Integer(req.getParameter("newsId").trim());
				
				String newsContent = req.getParameter("newsContent");
				if (newsContent == null || newsContent.trim().length() == 0) {
					errorMsgs.add("消息內容請勿空白");
				}
				
				Integer newsStatus = null;
				try {
					newsStatus = new Integer(req.getParameter("newsStatus").trim());
				} catch (NumberFormatException e) {
					newsStatus = null;
					errorMsgs.add("消息狀態請填數字");
				}
				
				RecentNewsVO recentNewsVO = new RecentNewsVO();
				recentNewsVO.setNewsId(newsId);
				recentNewsVO.setNewsContent(newsContent);
				recentNewsVO.setNewsStatus(newsStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recentNewsVO", recentNewsVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/news/updateNews.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RecentNewsService recentNewsSvc = new RecentNewsService();
				recentNewsVO = recentNewsSvc.updateNews(newsId, newsContent, newsStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recentNewsVO", recentNewsVO);
				String url = "/news/showNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/updateNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 刪除消息
		if ("delete".equals(action)) { // 來自showNewsEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer newsId = new Integer(req.getParameter("newsId"));
				
				/***************************2.開始刪除資料***************************************/
				RecentNewsService recentNewsSvc = new RecentNewsService();
				recentNewsSvc.deleteNews(newsId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/news/showNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/showNews.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
