package com.shop.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.shop.model.*;
@WebServlet("/front-end/shop/shop.do")
public class ShopFrontEndServlet extends HttpServlet {

	private static final long serialVersionUID = 8523979247180883296L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("testPIC".equals(action)) {
			String str = req.getParameter("itemId");
			ShopService shopSvc = new ShopService();
			ShopVO shopVO = shopSvc.getOneShop(new Integer(str));
			req.setAttribute("shopVO", shopVO);
			RequestDispatcher OKView = req
					.getRequestDispatcher("/front-end/shop/addShopPic.jsp");
			OKView.forward(req, res);
			
		}
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("itemId");	
				if (str == null || (str.trim()).length() ==0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failuerViem = req
							.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
					failuerViem.forward(req, res);
					return;
				}
				
				Integer itemId = null;
				try {
					itemId = new Integer(str);
				}catch(Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				//Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failuerViem = req
							.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
					failuerViem.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(itemId);
				if (shopVO == null) {
					errorMsgs.add("查無商品資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { //來自listAllShop/jsp的請求
	
			List<String> errorMsgs = new LinkedList<String>();
			//以防我們需要寄送錯誤畫面
			//儲存這個集合在請求scope裡面
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer itemId = new Integer(req.getParameter("itemId"));
				
				/***************************2.開始查詢資料****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(itemId);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shopVO",shopVO);
				String url = "/front-end/shop/update_shop_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/listAllShop.jsp");
				failureView.forward(req, res);
			}			
		}
		
		
		if("update".equals(action)) { // 來自update_shop_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			//以防我們需要寄送錯誤畫面
			//儲存這個集合在請求scope裡面
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer itemId = new Integer(req.getParameter("itemId").trim());
			    
				String itemName = req.getParameter("itemName");
				String itemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{4,100}$";
				if (itemName == null || itemName.trim().length()== 0) {
					errorMsgs.add("商品名稱:請勿空白");
					} else if(!itemName.trim().matches(itemNameReg)){
						errorMsgs.add("商品名稱: 只能是中文或英文或數字或,而且長度要4到10個字");
					}
				
				Integer itemFee = null;
				try {
					itemFee = new Integer(req.getParameter("itemFee").trim());
				} catch (NumberFormatException e) {
					itemFee = 0;
					errorMsgs.add("價格請填數字");
				}
				
				String itemDescribtion = req.getParameter("itemDescribtion").trim();
				String itemDescribtionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{4,100}$";
				if (itemDescribtion == null || itemDescribtion.trim().length() == 0) {
				} else if(!itemDescribtion.trim().matches(itemDescribtionReg)){
					errorMsgs.add("商品敘述:只能是中文或者英文、數字,且長度要4到100之間");
				}
				
				Integer itemQuantity = null;
				try {
					itemQuantity = new Integer(req.getParameter("itemQuantity").trim());
				} catch (NumberFormatException e) {
					itemQuantity = 0;
					errorMsgs.add("數量請填數字");
				}
				
				Byte itemStatus = null;
				try {
					itemStatus = new Byte(req.getParameter("itemStatus").trim());
				} catch(NumberFormatException e) {
					itemStatus = 0;
					errorMsgs.add("商品狀態請填:0為上架;1為下架");
				}
				
				Integer commentNumber = null;
				try {
					commentNumber = new Integer(req.getParameter("commentNumber").trim());
				} catch(NumberFormatException e) {
					commentNumber = 0;
					errorMsgs.add("評價總人數請填數字");
				} 
				
				Integer commentTotalScore = null;
				try {
					commentTotalScore = new Integer(req.getParameter("commentTotalScore").trim());
				}catch (NumberFormatException e) {
					commentTotalScore = 0;
					errorMsgs.add("評價總分請填數字");
				}
				
				Integer itemCategoryId =  new Integer(req.getParameter("itemCategoryId").trim());
				
				ShopVO shopVO = new ShopVO();
				shopVO.setItemId(itemId);
				shopVO.setItemName(itemName);
				shopVO.setItemFee(itemFee);
				shopVO.setItemDescribtion(itemDescribtion);
				shopVO.setItemQuantity(itemQuantity);
				shopVO.setItemStatus(itemStatus);
				shopVO.setCommentNumber(commentNumber);
				shopVO.setCommentTotalScore(commentTotalScore);
				shopVO.setItemCategoryId(itemCategoryId);
				
				
				//如果發生錯誤就送使用者跳轉回主頁
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopVO",shopVO);// 含有輸入格式錯誤的shopVO物件,也存入req
					RequestDispatcher failureView = req.
							getRequestDispatcher("/front-end/shop/update_shop_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.updateShop2(itemCategoryId,itemId,itemDescribtion,itemFee,
						itemName,itemQuantity,itemStatus,commentNumber,commentTotalScore);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫update成功後,正確的的shopVO物件,存入req
				String url = "/front-end/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneShop.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/update_shop_input.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("insert".equals(action)) { // 來自addShop.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			//以防我們需要寄送錯誤畫面
			//儲存這個集合在請求scope裡面
			req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
//				String itemDescribtion = req.getParameter("itemDescribtion").trim();
//				if (itemDescribtion == null || itemDescribtion.trim().length() == 0) {
//					errorMsgs.put("itemDescribtion", "商品描述請勿空白");
//				}
				
				//正規表示法 練習
				String itemDescribtion = req.getParameter("itemDescribtion").trim();
				String itemDescribtionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,100}$";
				if (itemDescribtion == null || itemDescribtion.trim().length() == 0) {
					errorMsgs.put("itemDescribtion","商品敘述不要啦不要空白啦不然怎知道說啥啦");
				} else if(!itemDescribtion.trim().matches(itemDescribtionReg)) {
					errorMsgs.put("itemDescribtion","商品敘述只能是中文或者英文、數字,且長度要3到100之間");
				}
				
				Integer itemFee = null;
				try {
					itemFee = new Integer(req.getParameter("itemFee").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("itemFee","商品價格請勿空白");
				}

				String itemName = req.getParameter("itemName").trim();
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.put("itemName","商品名稱請勿空白");
				}

				Integer itemQuantity = null;
				try {
					itemQuantity = new Integer(req.getParameter("itemQuantity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("itemQuantity","商品數量請勿空白");
				}
				
				Byte itemStatus = null;
				try {
					itemStatus = new Byte(req.getParameter("itemStatus").trim());
				} catch(NumberFormatException e) {
					errorMsgs.put("itemStatus","商品數量請勿空白:0為上架;1為下架");
				}
				
				Integer commentNumber = null;
				try {
					commentNumber = new Integer(req.getParameter("commentNumber").trim());
				} catch(NumberFormatException e) {
					errorMsgs.put("commentNumber","評價總人數請勿空白");
				} 
				
				Integer commentTotalScore = null;
				try {
					commentTotalScore = new Integer(req.getParameter("commentTotalScore").trim());
				}catch (NumberFormatException e) {
					errorMsgs.put("commentTotalScore","評價總分請勿空白");
				}
				
				Integer itemCategoryId =  new Integer(req.getParameter("itemCategoryId").trim());
				
				ShopVO shopVO = new ShopVO();
				shopVO.setItemName(itemName);
				shopVO.setItemFee(itemFee);
				shopVO.setItemDescribtion(itemDescribtion);
				shopVO.setItemQuantity(itemQuantity);
				shopVO.setItemStatus(itemStatus);
				shopVO.setCommentNumber(commentNumber);
				shopVO.setCommentTotalScore(commentTotalScore);
				shopVO.setItemCategoryId(itemCategoryId);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/addShop.jsp");
					failureView.forward(req, res);
					return;
				}
				//V 1.random  -->	randoomPassword	
				//V 2.randoomPassword -->email -->employee
				//X 3.randoomPassword -->password2
				
				/*************************** 2.開始新增資料 ***************************************/
				
				ShopService shopSvc = new ShopService();
				shopSvc.addShop2(itemCategoryId, itemDescribtion, itemFee, itemName, itemQuantity, itemStatus,
						commentNumber, commentTotalScore);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/shop/listAllShop.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);
				sucessView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/addShop.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {//來自listAllShop.jsp
		
			List<String> errorMsgs = new LinkedList<String>();
			//以防我們需要寄送錯誤畫面
			//儲存這個集合在請求scope裡面
			req.setAttribute("errorMsga", errorMsgs);
				
			try {
				/***************************1.接收請求參數***************************************/
				Integer itemId = new Integer(req.getParameter("itemId"));
					
				/***************************2.開始刪除資料***************************************/
				ShopService shopSvc = new ShopService();
				shopSvc.deleteShop(itemId);
					
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/shop/listAllShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
					
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/listAllShop.jsp");
				failureView.forward(req, res);
			}
	
		}

		if ("getItemName_For_Display".equals(action)) {//會員用這個使用名稱找東西
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("itemName");
				if (str == null || (str.trim()).length() ==0) {
					errorMsgs.add("請輸入商品名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failuerViem = req
							.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
					failuerViem.forward(req, res);
					return;
				}
				
				String itemName = req.getParameter("itemName").trim();
				String itemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("itemName:請輸入");
				} else if(!itemName.trim().matches(itemNameReg)) {
					errorMsgs.add("含非法字元");
				}
				
				//Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failuerViem = req
							.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
					failuerViem.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
			
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop2(itemName);
				if (shopVO == null) {
					errorMsgs.add("商品未上架");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的shopVO物件,存入req
				String url = "/front-end/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneShop.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/shopHomePage.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
	
}