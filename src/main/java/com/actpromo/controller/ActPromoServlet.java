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

@WebServlet("/actpromo/ActPromoServlet")
public class ActPromoServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			doPost(req, res);
		}

		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			
			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
					String str = req.getParameter("actPromotionId");
					if (str ==null || str.trim().length() ==0) {
					
						errorMsgs.add("請輸入活動優惠編號");
					} 
					// Send the request back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					Integer actPromotionId = null;
					try {
						//3. check the input pk format is correct or throw NumberFormatException
						actPromotionId = new Integer(str);
						//Use wrapper classes default constructor which prmt is String (ex: Float(String str), force transfer str to number,
						//if transfer failed, throw "NumberFormatException".	
					} catch (Exception e) {
						errorMsgs.add("活動優惠格式不正確");
					}
					// Send the req back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					/***************************2.開始查詢資料----Start query by calling DAO Service.*****************************************/
					ActPromoService actPromoSvc = new ActPromoService(); //New a facade(ActTypeService) to call ActTypeDAO methods.
					ActPromoVO actPromoVO = actPromoSvc.getOneById(actPromotionId);  //Call ActTypeDAO method "getOneById(actCategoryId)" and save result as actTypeVO.
					if(actPromoVO == null) {   
						errorMsgs.add("查無資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					/***************************3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.*************/
					req.setAttribute("actPromoVO",actPromoVO);  // 資料庫取出data由resultset轉存empVO物件,存入req
					String url = "/back-end/actpromo/listOneActType.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneActType.jsp
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
			
			if ("getOne_For_Update".equals(action)) { // 來自listAllActType.jsp的請求
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
					ActPromoService actPromoService = new ActPromoService();
					ActPromoVO actPromoVO = actPromoService.getOneById(actPromotionId);
				
					/***************************3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.*************/
					req.setAttribute("actPromoVO", actPromoVO);
					String url = "/back-end/actpromo/update_acttype_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功forward update_emp_input.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				}catch (Exception e) {
							errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/actpromo/listAllActType.jsp");
							failureView.forward(req, res);
				}
			
			}// end of if
				
			if ("update".equals(action)) { // 來自update_acttype_input.jsp的請求 <input type="hidden" name="action" value="update">
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this LinkedList in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					//3. 執行Update
					/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
					Integer actPromotionId = new Integer(req.getParameter("actPromotionId").trim());
					
					String actPromotionName = req.getParameter("actPromotionName"); //Not Null
					
					String actPromotionNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,11}$"; //(中文unicode)(英文大小寫)(數字0-9) ;[any reg matched];
					if (actPromotionName == null|| actPromotionName.trim().length() == 0) {
						errorMsgs.add("活動優惠名稱：請勿空白");
					} else if (!actPromotionName.trim().matches(actPromotionNameReg)) {
						errorMsgs.add("活動優惠名稱：只能是 中、英文字母、數字和_，且長度介於2到11之間");
					}
					
										
					Double actPromoDiscount = null;
					try {
						actPromoDiscount =new Double (req.getParameter("actPromoDiscount").trim());
					} catch (NumberFormatException e) {
						actPromoDiscount = 0.0;
						errorMsgs.add("活動優惠請填數字.");
					}

										
					ActPromoVO actPromoVO = new ActPromoVO();
					actPromoVO.setActPromotionId(actPromotionId);
					actPromoVO.setActPromotionName(actPromotionName);
					actPromoVO.setActPromotionDiscount(actPromoDiscount);

					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("actPromoVO", actPromoVO);  //update出錯 原因在這裏沒set到VO,所以JSP沒辦法成功驗證input data 的format.
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/update_actpromo_input.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始更新資料----Start update by calling DAO Service.*****************************************/
					ActPromoService actPromoService  = new ActPromoService();
					actPromoVO = actPromoService.updateActPromo(actPromotionId, actPromotionName, actPromoDiscount);
					
					/***************************3.更新完成,準備轉交(Send the Success view)---Finish Update, Send the Success view.*************/
					req.setAttribute("actPromoVO", actPromoVO); 
					String url = "/back-end/actpromo/listOneActType.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
		
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpromo/update_actpromo_input.jsp");
					failureView.forward(req, res);
				}
			}//end of if
			
	        if ("insert".equals(action)) {
	        	List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/				
					String actPromotionName = req.getParameter("actPromotionName"); //Not Null
					
					String actPromotionNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,11}$"; //(中文unicode)(英文大小寫)(數字0-9) ;[any reg matched];
					if (actPromotionName == null|| actPromotionName.trim().length() == 0) {
						errorMsgs.add("活動優惠名稱：請勿空白");
					} else if (!actPromotionName.trim().matches(actPromotionNameReg)) {
						errorMsgs.add("活動類別名稱：只能是 中、英文字母、數字和_，且長度介於2到11之間");
					}
					
					Double actPromoDiscount = null;
					try {
						actPromoDiscount =new Double (req.getParameter("actPromoDiscount").trim());
					} catch (NumberFormatException e) {
						actPromoDiscount = 0.0;
						errorMsgs.add("活動折扣請填數字.");
					}

					
					
					ActPromoVO actPromoVO = new ActPromoVO();
					actPromoVO.setActPromotionName(actPromotionName);
					actPromoVO.setActPromotionDiscount(actPromoDiscount);
					
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("actPromoVO", actPromoVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actpromo/addActPromo.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					/***************************2.開始新增資料----Start add by calling DAO Service.*****************************************/
					ActPromoService actPromoService  = new ActPromoService();
					actPromoVO = actPromoService.addActPromo(actPromotionName, actPromoDiscount);
					
					/***************************3.新增完成,準備轉交(Send the Success view)---Finish Insert, Send the Success view.*************/
					String url = "/back-end/actpromo/listAllActPromo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllActType.jsp
					successView.forward(req, res);				
					/***************************其他可能的錯誤處理**********************************/

					
					
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/addActType.jsp");
					failureView.forward(req, res);
				}
	        }//end of if
	        
			if ("delete".equals(action)) { // 來自listAllActType.jsp
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
					String url = "/back-end/actpromo/listAllActPromoType.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpromo/listAllActPromo.jsp");
					failureView.forward(req, res);
				}

			}// end of if
			/***************************1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format**********************/
			
			/***************************2.開始查詢資料----Start query by calling DAO Service.*****************************************/

			/***************************2.開始新增資料----Start add by calling DAO Service.*****************************************/

			/***************************3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.*************/

		}
	}

	
