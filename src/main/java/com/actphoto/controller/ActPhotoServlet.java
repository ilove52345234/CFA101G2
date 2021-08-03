package com.actphoto.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import com.actphoto.model.ActPhotoService;
import com.actphoto.model.ActPhotoVO;


/**
 * Servlet implementation class ActPhotoServlet
 */
@WebServlet("/actphoto/ActPhotoServlet")
@MultipartConfig
public class ActPhotoServlet extends HttpServlet {
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		//建立actType時,同頁面的另一個form兼上傳照片
//		//來自/acttype/addActType.jsp的request
//		if("uploadActPhoto".equals(action)){
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				
//				Integer actCategoryId = null;
//				try {
//					actCategoryId = new Integer(req.getParameter("actCategoryId").trim());
//				} catch (NumberFormatException e) {
//					actCategoryId = 1;
//					errorMsgs.add("活動類別請填數字.");
//				}
//
//				
//				//上傳圖片
//				Blob actPhotoFile = null;
//				
//				try {
//					Part part = req.getPart("actPhoto");
//					InputStream is = part.getInputStream();
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();
//					byte b [] = new byte [is.available()]; // 動態陣列
//					
//					while (is.read(b) != -1) {
//					    baos.write(b);
//					}
//
//					actPhotoFile = new SerialBlob(baos.toByteArray ());
//					baos.close();
//					is.close();
//					
//				} catch (IOException ie) {
//					actPhotoFile = null;
//					errorMsgs.add("活動圖片上傳失敗.");
//				}
//				
//				ActPhotoVO actPhotoVO = new ActPhotoVO();
//				actPhotoVO.setActCategoryId(actCategoryId);
//				actPhotoVO.setActPhotoFile(actPhotoFile);
//				
//			
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("actPhotoVO", actPhotoVO);  
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/acttype/addActType.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//			
//				
//				/*************************** 2.開始新增資料 ***************************************/
//				ActPhotoService actPhotoSvc = new ActPhotoService();
//				actPhotoSvc.addActTypeAndPhoto(actCategoryId, actPhotoFile);
//				
//			
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/acttype/listAllActType.jsp";
//				req.setAttribute("actPhotoVO", actPhotoVO);  
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllActPhoto.jsp
//				successView.forward(req, res);
//				
//			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch(Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/acttype/addActType.jsp");
//				failureView.forward(req, res);
//			}
//		}//end of if
//			
		
		
		//Req的源頭要再定義清楚 有點不確定要用哪個jsp作為entry
		//一律先用 /back-end/acttype/listOneActtype.jsp 當placeholder;
		//來自listOneActtype.jsp的request
		if("getOneActPhoto".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/				
				String str = req.getParameter("actPhotoId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動圖片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/listOneActtype.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actPhotoId = null;
				try {
					actPhotoId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動圖片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/listOneActtype.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料****************************************/
				
				ActPhotoService  actPhotoSvc = new ActPhotoService();
				ActPhotoVO actPhotoVO = actPhotoSvc.getOneById(actPhotoId);
				
				
				if (actPhotoVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/listOneActtype.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交***********************************/
				req.setAttribute("actPhotoVO", actPhotoVO);
				String url = "/back-end/acttype/listOneActtype.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/acttype/listOneActtype.jsp");
				failureView.forward(req, res);
			}
		}//end of if
		
		//getOneforUpdate
		//從update_acttype_input頁面來的request
		if ("getOneActPhoto".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer actPhotoId = new Integer(req.getParameter("actPhotoId"));

				/***************************2.開始查詢資料****************************************/
				ActPhotoService  actPhotoSvc = new ActPhotoService();
				ActPhotoVO actPhotoVO = actPhotoSvc.getOneById(actPhotoId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actPhotoVO", actPhotoVO);
				String url = "/back-end/acttype/update_acttype_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/acttype/listAllActType.jsp");
				failureView.forward(req, res);
			}
		}
		
		//上傳更新照片Only
		if("updateActPhoto".equals(action)) { //update_acttype_input.jsp送出請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actCategoryId = Integer.valueOf(req.getParameter("actCategoryId"));
				
				List<ActPhotoVO> photoList =  new ArrayList<ActPhotoVO>();
				Blob actPhotoFile = null;

				//上傳圖片
				
				try {
					List<Part> parts = (List<Part>) req.getParts();
					
					for (Part part : parts) {
						//濾掉size = 0的檔案
						 if(part.getSize() != 0) {
							  //濾掉非input accept="image/*" 的檔案  
							 if( part.getContentType() != null) {
										
										//從jsp input進來
									InputStream is = part.getInputStream();
										//往DB output出去
									ByteArrayOutputStream baos = new ByteArrayOutputStream();
										// 動態Byte陣列
									byte[] b = new byte [is.available()]; 
									
									while (is.read(b) != -1) {
									    baos.write(b);
									}
									System.out.println("baos");
									actPhotoFile = new SerialBlob(baos.toByteArray ());
									
									ActPhotoVO actPhotoVO = new ActPhotoVO();
					
									actPhotoVO.setActCategoryId(actCategoryId);
									actPhotoVO.setActPhotoFile(actPhotoFile);
									photoList.add(actPhotoVO);
									
									baos.close();
									is.close();
									
									}  
							}
						}// end of for each 
				} catch (IOException ie) {
//					actPhotoFile = null;
					errorMsgs.add("活動圖片獨自更新上傳失敗.");
				}
				
				
			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("photoList", photoList);  
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/update_acttype_input.jsp");
					failureView.forward(req, res);
					return;
				}
			
				
				/*************************** 2.開始新增資料 ***************************************/
				ActPhotoService actPhotoSvc = new ActPhotoService();
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("actPhotoVO", actPhotoVO);
				String url = "/back-end/acttype/listAllActType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/	
			} catch(Exception e) {
				errorMsgs.add("獨立修改活動照片失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/acttype/update_acttype_input.jsp");
				failureView.forward(req, res);
			}
		}
		
	 
		
		
		
   }
}
