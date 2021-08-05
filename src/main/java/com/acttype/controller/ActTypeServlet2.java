package com.acttype.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import com.act.model.ActVO;
import com.actphoto.model.ActPhotoService;
import com.actphoto.model.ActPhotoVO;
import com.acttype.model.ActTypeService;
import com.acttype.model.ActTypeVO;

/**
 * Servlet implementation class ActTypeServlet
 */
@WebServlet("/acttype/ActTypeServlet2")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActTypeServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res); // both GET and POST HTTP requests will treat as POST in this controller.
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//if ("getOne_For_actCategoryId".equals(action)) { // 來自listAllEmp.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer actCategoryId = new Integer(req.getParameter("actCategoryId"));
//				
//				/***************************2.開始查詢資料****************************************/
//				ActTypeService actTypeSvc = new ActTypeService();
//				ActTypeVO actTypeVO = actTypeSvc.getOneById(actCategoryId);
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("actTypeVO", actTypeVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/front-end/act/listOneAct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/act/listAllActType2.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format
				 **********************/
				String str = req.getParameter("actCategoryId");
				// 1. getParameter for pk;
				// 2. check if input pk is empty;
				if (str == null || str.trim().length() == 0) {
					// str == null此處為了避免NullPointerException造成難debug，不加也可以run，但加了出錯比較好debug.
					// (str.trim()).length() == 0) aka 輸入為empty String.

					errorMsgs.add("請輸入活動類別編號");
				}
				// Send the request back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Integer actCategoryId = null;
				try {
					// 3. check the input pk format is correct or throw NumberFormatException
					actCategoryId = new Integer(str);
					// Use wrapper classes default constructor which prmt is String (ex:
					// Float(String str), force transfer str to number,
					// if transfer failed, throw "NumberFormatException".
				} catch (Exception e) {
					errorMsgs.add("活動類別編號格式不正確");
				}
				// Send the req back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************
				 * 2.開始查詢資料----Start query by calling DAO Service.
				 *****************************************/
				ActTypeService actTypeSvc = new ActTypeService(); // New a facade(ActTypeService) to call ActTypeDAO
																	// methods.
				ActTypeVO actTypeVO = actTypeSvc.getOneById(actCategoryId); // Call ActTypeDAO method
																			// "getOneById(actCategoryId)" and save
																			// result as actTypeVO.
				if (actTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.
				 *************/
				req.setAttribute("actTypeVO", actTypeVO); // 資料庫取出data由resultset轉存empVO物件,存入req
				String url = "/back-end/acttype/listOneActType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneActType.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				// 1. if前端顯示看得懂的Exception Msg: 從 Controller後面的 DAO throw出來的Runtime Exception，
				// 會從這?catch，並setAttribute到request
				// 再forward request回去原先發出request的View(JSP);

				// 2. if前端顯示空白的Exception Msg: 如果View（JSP）的input name有錯誤（ex: job多了一個space >>>
				// “job_” 而不是“job”)，
				// 也會在這?被catch，但不會有errorMsg.
				// 此時暫時把try-catch block註解掉，直接跑這段code，看哪一行發生NullPointerException再回去check
				// View（JSP).

				errorMsgs.add("無法取得資料:" + e.getMessage());
				// 這?把errorMsg 加入list之後，
				// 在try-catch block之外預先註冊的 req.setAttribute("errorMsgs", errorMsgs);
				// 就會同步新增msg進去request。
				// 之後再forward request去前端的View(JSP)

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/select_page.jsp");
				failureView.forward(req, res);

			}
		} // end of if

		if ("getOne_For_Update".equals(action)) { // 來自listAllActType.jsp的請求
			// 1. 先執行getOne 再執行Update

			List<String> errorMsgs = new LinkedList<String>();
			// Store this LinkedList in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 2.getOne
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format
				 **********************/
				Integer actCategoryId = new Integer(req.getParameter("actCategoryId"));

				/***************************
				 * 2.開始查詢資料----Start query by calling DAO Service.
				 *****************************************/
				ActTypeService actTypeService = new ActTypeService();
				ActTypeVO actTypeVO = actTypeService.getOneById(actCategoryId);

				ActPhotoService actPhotoSvcs = new ActPhotoService();
				ActPhotoVO actPhotoVO = actPhotoSvcs.getOneByTypeId(actCategoryId);
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)---Finish query, Send the Success view.
				 *************/
				req.setAttribute("actTypeVO", actTypeVO);
				req.setAttribute("actPhotoVO", actPhotoVO);
				String url = "/back-end/acttype/update_acttype_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功forward update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/listAllActType.jsp");
				failureView.forward(req, res);
			}

		} // end of if

		if ("update".equals(action)) { // 來自update_acttype_input.jsp的請求 <input type="hidden" name="action"
										// value="update">

			List<String> errorMsgs = new LinkedList<String>();
			// Store this LinkedList in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 3. 執行Update
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format
				 **********************/
				Integer actCategoryId = new Integer(req.getParameter("actCategoryId").trim());

				String actCategoryName = req.getParameter("actCategoryName"); // Not Null

				String actCategoryNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,11}$"; // (中文unicode)(英文大小寫)(數字0-9) ;[any
																						// reg matched];
				if (actCategoryName == null || actCategoryName.trim().length() == 0) {
					errorMsgs.add("活動類別名稱：請勿空白");
				} else if (!actCategoryName.trim().matches(actCategoryNameReg)) {
					errorMsgs.add("活動類別名稱：只能是 中、英文字母、數字和_，且長度介於2到11之間");
				}

				String actCategoryDesc = req.getParameter("actCategoryDesc").trim(); // Nullable;

				Integer actMaxPart = null;
				try {
					actMaxPart = new Integer(req.getParameter("actMaxPart").trim());
				} catch (NumberFormatException e) {
					actMaxPart = 0;
					errorMsgs.add("最高參加人數上限請填數字.");
				}

				Integer actMinPart = null;
				try {
					actMinPart = new Integer(req.getParameter("actMinPart").trim());
				} catch (NumberFormatException e) {
					actMinPart = 0;
					errorMsgs.add("最低參加人數下限請填數字.");
				}

				// 要驗證input一定要大於actMinPart
				if (actMaxPart < actMinPart) {
					errorMsgs.add("最高參加人數上限必須大於最低參加人數下限.");
				}

				Integer actFee = null;
				try {
					actFee = new Integer(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					actFee = 0;
					errorMsgs.add("活動費用請填數字.");
				}

				ActTypeVO actTypeVO = new ActTypeVO();
				actTypeVO.setActCategoryId(actCategoryId);
				actTypeVO.setActCategoryName(actCategoryName);
				actTypeVO.setActCategoryDesc(actCategoryDesc);
				actTypeVO.setActMaxPart(actMaxPart);
				actTypeVO.setActMinPart(actMinPart);
				actTypeVO.setActFee(actFee);

				List<Part> parts = (List<Part>) req.getParts();

				List<ActPhotoVO> photoList = new ArrayList<ActPhotoVO>();

				for (Part part : parts) {
					// 從jsp input進來
					InputStream is = part.getInputStream();
					// 往DB output出去
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					// 動態Byte陣列
					byte[] b = new byte[is.available()];

					while (is.read(b) != -1) {
						baos.write(b);
					}

					Blob actPhotoFile = new SerialBlob(baos.toByteArray());
					ActPhotoVO actPhotoVO = new ActPhotoVO();

					actPhotoVO.setActPhotoFile(actPhotoFile);
					photoList.add(actPhotoVO);

					baos.close();
					is.close();

				} // end of for each

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actTypeVO", actTypeVO); // update出錯 原因在這裏沒set到VO,所以JSP沒辦法成功驗證input data 的format.
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/update_acttype_input.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 2.開始更新資料----Start update by calling DAO Service.
				 *****************************************/
				ActTypeService actTypeService = new ActTypeService();
				actTypeService.updateActType(actCategoryId, actCategoryName, actCategoryDesc, actMaxPart, actMinPart,
						actFee);

				/***************************
				 * 3.更新完成,準備轉交(Send the Success view)---Finish Update, Send the Success view.
				 *************/
				req.setAttribute("actTypeVO", actTypeVO); // 資料庫update成功後,正確的的actTypeVO物件,存入req
				String url = "/back-end/acttype/listOneActType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneActType.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/update_acttype_input.jsp");
				failureView.forward(req, res);
			}
		} // end of if

		// 同時新增ActTypeVO跟List<actPhotoVO> list
		if ("insert".equals(action)) { // 來自addActType.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理---check Input parameters' empty and format
				 **********************/
				String actCategoryName = req.getParameter("actCategoryName"); // Not Null

				String actCategoryNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,11}$"; // (中文unicode)(英文大小寫)(數字0-9) ;[any
																						// reg matched];
				if (actCategoryName == null || actCategoryName.trim().length() == 0) {
					errorMsgs.add("活動類別名稱：請勿空白");
				} else if (!actCategoryName.trim().matches(actCategoryNameReg)) {
					errorMsgs.add("活動類別名稱：只能是 中、英文字母、數字和_，且長度介於2到11之間");
				}

				String actCategoryDesc = req.getParameter("actCategoryDesc").trim(); // Nullable;

				Integer actMaxPart = null;
				try {
					actMaxPart = new Integer(req.getParameter("actMaxPart").trim());
				} catch (NumberFormatException e) {
					actMaxPart = 0;
					errorMsgs.add("最高參加人數上限請填數字.");
				}

				Integer actMinPart = null;
				try {
					actMinPart = new Integer(req.getParameter("actMinPart").trim());
				} catch (NumberFormatException e) {
					actMinPart = 0;
					errorMsgs.add("最低參加人數下限請填數字.");
				}

				// 要驗證input一定要大於actMinPart
				if (actMaxPart < actMinPart) {
					errorMsgs.add("最高參加人數上限必須大於最低參加人數下限.");
				}

				Integer actFee = null;
				try {
					actFee = new Integer(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					actFee = 0;
					errorMsgs.add("活動費用請填數字.");
				}

				ActTypeVO actTypeVO = new ActTypeVO();
				actTypeVO.setActCategoryName(actCategoryName);
				actTypeVO.setActCategoryDesc(actCategoryDesc);
				actTypeVO.setActMaxPart(actMaxPart);
				actTypeVO.setActMinPart(actMinPart);
				actTypeVO.setActFee(actFee);

				List<Part> parts = (List<Part>) req.getParts();

				List<ActPhotoVO> photoList = new ArrayList<ActPhotoVO>();

				for (Part part : parts) {
					// 濾掉size = 0的檔案
					if (part.getSize() != 0) {
						// 濾掉非input accept="image/*" 的檔案
						if (part.getContentType() != null) {

							// 從jsp input進來
							InputStream is = part.getInputStream();
							// 往DB output出去
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							// 動態Byte陣列
							byte[] b = new byte[is.available()];

							while (is.read(b) != -1) {
								baos.write(b);
							}
							System.out.println("baos");
							Blob actPhotoFile = new SerialBlob(baos.toByteArray());
							ActPhotoVO actPhotoVO = new ActPhotoVO();

							actPhotoVO.setActPhotoFile(actPhotoFile);
							photoList.add(actPhotoVO);

							baos.close();
							is.close();

						}
					}
				} // end of for each
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actTypeVO", actTypeVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/addActType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************
				 * 2.開始新增資料----Start add by calling DAO Service.
				 *****************************************/
				ActTypeService actTypeService = new ActTypeService();
				actTypeService.insertWithActPhotos(actTypeVO, photoList);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)---Finish Insert, Send the Success view.
				 *************/
				req.setAttribute("actTypeVO", actTypeVO);
				String url = "/back-end/acttype/listAllActType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllActType.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/addActType.jsp");
				failureView.forward(req, res);
			}
		} // end of if
	
		if ("listAllAct_ByActCategoryId".equals(action))  {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer actCategoryId = new Integer(req.getParameter("actCategoryId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ActTypeService actTypeSvc = new ActTypeService();
				Set<ActVO> set = actTypeSvc.getActByActCategoryId(actCategoryId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllAct_ByActCategoryId", set);    // 資料庫取出的set物件,存入request

				String url = "/front-end/act/listAllAct_ByActCategoryId.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
