package com.mem.controller;

import java.io.IOException;
import java.sql.Date;
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
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;


public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//註冊會員
		if ("insert".equals(action)) { // 來自addMember.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);


			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memAccount = req.getParameter("memAccount");
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.put("memAccount", "會員帳號: 請勿空白");
				} 

				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.put("memName", "會員姓名: 請勿空白");
				} else if (!memName.trim().matches(memNameReg)) {
					errorMsgs.put("memName", "會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String memPassword = req.getParameter("memPassword").trim();
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.put("memPassword", "密碼請勿空白");
				}

				String memAddress = req.getParameter("memAddress").trim();
				String memAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,66}$";
				if (memAddress == null || memAddress.trim().length() == 0) {
					errorMsgs.put("memAddress", "地址: 請勿空白");
				} else if (!memAddress.trim().matches(memAddressReg)) {
					errorMsgs.put("memAddress", "地址: 只能是中文、英文字母、數字 ");
				}

				String memPhone = req.getParameter("memPhone").trim();
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.put("memPhone", "電話請勿空白");
				}

				String memUid = req.getParameter("memUid").trim();
				String memUidReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if (memUid == null || memUid.trim().length() == 0) {
					errorMsgs.put("memUid", "身分證字號: 請勿空白");
				} else if (!memUid.trim().matches(memUidReg)) {
					errorMsgs.put("memUid", "身分證字號: 請輸入正確身分證格式 ");
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.put("memEmail", "電子信箱: 請勿空白");
				} else if (!memEmail.trim().matches(memEmailReg)) {
					errorMsgs.put("memEmail", "電子信箱: 請輸入正確電子信箱格式 ");
				}

				String memSex = req.getParameter("memSex").trim();
				if (memSex == null || memSex.trim().length() == 0) {
					errorMsgs.put("memSex", "性別請勿空白");
				}

				Date memDob = null;
				try {
					if (req.getParameter("memDob") != null) {
						memDob = Date.valueOf(req.getParameter("memDob").trim());
					}
				} catch (IllegalArgumentException e) {
					errorMsgs.put("memDob", "請輸入生日");
				}

				Integer memStatus = null;
				if (req.getParameter("memStatus") != null && !"".equals(req.getParameter("memStatus"))) {
					memStatus = new Integer(req.getParameter("memStatus").trim());
				}

				Timestamp memUpdate = new Timestamp(System.currentTimeMillis());

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				MemService memServive = new MemService();
				memServive.addMem(memAccount, memName, memPassword, memAddress, memPhone, memUid, memEmail, memSex, memDob, memStatus, memUpdate);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/mem/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交index.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/addMember.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		//後台會員顯示修改資料
		if ("getOne_For_Update".equals(action)) { // 來自listAllMemEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memId = new Integer(req.getParameter("memId"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memId);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO);
				String url = "/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		//前端會員顯示修改資料
		if ("getOne_For_Update2".equals(action)) { // 來自HomePage.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
				
				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.getOneMem(memId);//MemVO
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO);
				String url = "/mem/updateMemData2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/HomePage.jsp");
				failureView.forward(req, res);
			}
		}
		//後端送出修改
		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memId = new Integer(req.getParameter("memId"));
				String memAccount = req.getParameter("memAccount");
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				}
				
				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(memNameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String memPassword = req.getParameter("memPassword").trim();
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String memAddress = req.getParameter("memAddress").trim();
				String memAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,66}$";
				if (memAddress == null || memAddress.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!memAddress.trim().matches(memAddressReg)) {
					errorMsgs.add("地址: 只能是中文、英文字母、數字 ");
				}
				
				String memPhone = req.getParameter("memPhone").trim();
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				String memUid = req.getParameter("memUid").trim();
				String memUidReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if (memUid == null || memUid.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if (!memUid.trim().matches(memUidReg)) {
					errorMsgs.add("身分證字號: 請輸入正確身分證格式 ");
				}
				
				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if (!memEmail.trim().matches(memEmailReg)) {
					errorMsgs.add("電子信箱: 請輸入正確電子信箱格式 ");
				}
				
				String memSex = req.getParameter("memSex").trim();
				if (memSex == null || memSex.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}	
				
				Date memDob = null;
				try {
					memDob = Date.valueOf(req.getParameter("memDob").trim());
				} catch (IllegalArgumentException e) {
					memDob = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日日期!");
				}

				Integer memStatus = null;
				try {
					memStatus = new Integer(req.getParameter("memStatus").trim());
				} catch (NumberFormatException e) {
					memStatus = null;
					errorMsgs.add("會員狀態請填數字");
				}
				
				Timestamp memUpdate = new Timestamp(System.currentTimeMillis());
				
				MemVO memVO = new MemVO();
				memVO.setMemId(memId);
				memVO.setMemAccount(memAccount);
				memVO.setMemName(memName);
				memVO.setMemPassword(memPassword);
				memVO.setMemAddress(memAddress);
				memVO.setMemPhone(memPhone);
				memVO.setMemUid(memUid);
				memVO.setMemEmail(memEmail);
				memVO.setMemSex(memSex);
				memVO.setMemDob(memDob);
				memVO.setMemStatus(memStatus);
				memVO.setMemUpdate(memUpdate);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(memId, memAccount, memName, memPassword, memAddress, memPhone, memUid, memEmail, memSex, memDob, memStatus, memUpdate); 
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO);
				String url = "/mem/HomePage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交HomePage.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/HomePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台查單一
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);

					return;
				}
				
				Integer id = null;
				try {
					id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(id);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO);
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		//前台查單一
		if ("getOne_For_Display2".equals(action)) { // 來自HomePage.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				Integer memId = memVO.getMemId();
				
				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.getOneMem(memId);//MemVO
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO);
				String url = "/mem/memData.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/HomePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		//登入
		if ("login".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//帳號
				String memAccount = req.getParameter("memAccount").trim();
				if (memAccount == null || memAccount.length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}
				
				//密碼
				String memPassword = req.getParameter("memPassword").trim();
				String memPasswordReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (memPassword == null || memPassword.length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if(!memPassword.matches(memPasswordReg)) { 
					errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/logIn.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMemByMemAcc(memAccount);
				if (memVO == null || !(memVO.getMemPassword().equals(memPassword))) {
					errorMsgs.add("帳號或密碼錯誤");
				}
				
				if(memVO.getMemStatus() == 0) {
					errorMsgs.add("此帳號已停用");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/index.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.處理登入********************************************/
				HttpSession session = req.getSession(); //取得session物件
			    session.setAttribute("memVO", memVO);
			    String location = (String)session.getAttribute("location");


			    if(location != null) {
			    	session.removeAttribute("location");
			    	res.sendRedirect(location);
			    	return;
			    }
				/***************************4.查詢完成,準備轉交(Send the Success view)*************/
			    req.setAttribute("memVO", memVO);
			    String url = "/mem/testLoginSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mem/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		//登出
		if("logout".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession(); //取得session物件
			session.invalidate(); //讓此次session失效
		    String url = "/mem/HomePage.jsp";
		    System.out.println("登出成功"); //測試登出
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//忘記密碼
		if ("forgotPassWord".equals(action)) { // 來自login.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				//帳號
				String memAccount = req.getParameter("memAccount").trim();
				if (memAccount == null || memAccount.length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/logIn.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMemByMemAcc(memAccount);
				if (memVO == null) {
					errorMsgs.add("帳號錯誤");
				}
				
				/*************************** 3.處理e-mail ****************************************/
			    String to = "whatever016015@gmail.com"; // memVO.getMemEmail();
			      
			    String subject = "密碼通知";
			      
			    String memname = memVO.getMemName(); // memVO.getMemName();
			    String mempassword = memVO.getMemPassword(); // memVO.getMemPassword();
			    String messageText = "您好! " + memname + " 請謹記此密碼: " + mempassword + "\n" +" (登入後請更改新密碼)"; 
			       
			    memSvc.sendMail(to, subject, messageText);
			    
				/*************************** 4.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/mem/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/HomePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}

}
