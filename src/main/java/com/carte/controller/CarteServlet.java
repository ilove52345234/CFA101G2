package com.carte.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import com.carte.model.CarteService;
import com.carte.model.CarteVO;

import com.mem.model.MemService;
import com.mem.model.MemVO;
@WebServlet("/carte.do")
@MultipartConfig
public class CarteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		//新增名片
		if("addCarte".equals(action)) { //來自addcarte.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
				
				String userName = req.getParameter("userName");
				if(userName == null || userName.trim().length() == 0) {
					errorMsgs.put("userName", "暱稱請勿空白");
				}
				
				Integer userStatus = null;
				if (req.getParameter("userStatus") != null && !"".equals(req.getParameter("userStatus"))) {
					userStatus = new Integer(req.getParameter("userStatus").trim());
				}
				
				Timestamp userUpdate = new Timestamp(System.currentTimeMillis());
				
				//上傳圖片
				Part part = req.getPart("userPic");
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte b [] = new byte [is.available()]; // 動態陣列
				
				while (is.read(b) != -1) {
				    baos.write(b);
				}

				Blob userPic = new SerialBlob(baos.toByteArray ());
				baos.close();
				is.close();
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/carte/addCarte2.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				CarteService carteService = new CarteService();
				carteService.addCarte(memId, userName, userPic, userStatus, userUpdate);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/mem/HomePage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交showCarte.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/carte/addCarte2.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		//查單一
		if("showCarte".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
				
				/***************************2.開始查詢資料****************************************/
				CarteService carteSvc = new CarteService();
				CarteVO carteVO = carteSvc.getOneCarte(memId);
				if (carteVO == null) {
					errorMsgs.add("尚未新增名片");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/carte/addCarte2.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交***********************************/
				req.setAttribute("carteVO", carteVO);
				String url = "/carte/showCarte2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/carte/addCarte2.jsp");
				failureView.forward(req, res);
			}
		}
		
		//取單一顯示並移交更新
		if ("getOneCarte".equals(action)) { // 來自showCarte.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
				
				/***************************2.開始查詢資料****************************************/
				CarteService carteSvc = new CarteService();
				CarteVO carteVO = carteSvc.getOneCarte(memId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("carteVO", carteVO);
				String url = "/carte/updateCarte.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/carte/showCarte.jsp");
				failureView.forward(req, res);
			}
		}
		
		//修改
		if("updateCarte".equals(action)) { //updateCarte.jsp送出請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
				
				String userName = req.getParameter("userName");
				if(userName == null || userName.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}
				
				
				//上傳圖片
				Part part = req.getPart("userPic");
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte b [] = new byte [is.available()]; // 動態陣列
				
				while (is.read(b) != -1) {
				    baos.write(b);
				}

				Blob userPic = new SerialBlob(baos.toByteArray ());
				baos.close();
				is.close();
				
				Timestamp userUpdate = new Timestamp(System.currentTimeMillis());
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/carte/updateCarte.jsp");
					failureView.forward(req, res);
					return;
				}
				
				CarteVO carteVO = new CarteVO();
				carteVO.setMemId(memId);
				carteVO.setUserName(userName);
				carteVO.setUserPic(userPic);
				carteVO.setUserUpdate(userUpdate);
				
				/***************************2.開始修改資料*****************************************/
				CarteService carteSvc = new CarteService();
				carteVO = carteSvc.updateCarte(memId, userName, userPic, userUpdate);// userStatus
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("carteVO", carteVO);
				String url = "/carte/showCarte2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/	
			} catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/carte/showCarte2.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
