package com.shop.controller;

import java.io.*;
import java.util.*;
import java.util.Base64.Decoder;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.shop.model.*;
import com.shoppic.model.ShopPicVO;

@WebServlet(urlPatterns = { "/back-end/shop/shop.do" })
public class ShopServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("ShopServlet");
		// 新增
		if ("insert".equals(action)) { // 來自addShop.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// 將此集合存儲在請求範圍中，以防我們需要
			// 發送錯誤頁面視圖
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer itemCategoryId = null;
				try {
					itemCategoryId = new Integer(req.getParameter("itemCategoryId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品類別編號：請勿空白");
				}

				String itemDescribtion = req.getParameter("itemDescribtion");
				if (itemDescribtion == null || itemDescribtion.trim().length() == 0) {
					errorMsgs.add("商品敘述：請勿空白");
				}

				Integer itemFee = null;
				try {
					itemFee = new Integer(req.getParameter("itemFee").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格：請勿空白及負數");
				}

				String itemName = req.getParameter("itemName").trim();
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名稱：請勿空白");
				}

				Integer itemQuantity = null;
				try {
					itemQuantity = new Integer(req.getParameter("itemQuantity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量：請勿空白");
				}

				Byte itemStatus = null;
				try {
					itemStatus = new Byte(req.getParameter("itemStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品狀態：請勿空白");
				}

				// 商品圖片
				String hat_memPic = req.getParameter("pic_base64");
				String[] picArr = hat_memPic.split(",");
				String imageString = picArr[1];
				Decoder decoder = Base64.getDecoder();
				byte[] imageByte = decoder.decode(imageString);

//				Integer commentNumber = null;			
//				if(req.getParameter("commentNumber").trim().equals("")) {	// 如果commentNumber輸入空白 比對(equlas)空字串時					
//					commentNumber = 0;	// commentNumber為0					
//				} else {	// 否則					
//					commentNumber = new Integer(req.getParameter("commentNumber"));	// 如果有輸入數字，則顯示數字	
//				}
//				
//				Integer commentTotalScore = null;
//				if(req.getParameter("commentTotalScore").trim().equals("")) {	// 如果commentTotalScore輸入空白 比對(equlas)空字串時				
//					commentTotalScore = 0;	// commentTotalScore 為0				
//				} else {					
//					commentTotalScore = new Integer(req.getParameter("commentTotalScore"));	// 如果有輸入數字，則顯示數字				
//				}

				ShopPicVO shopPicVO = new ShopPicVO();
				shopPicVO.setItemPhoto(imageByte);

				ShopVO shopVO = new ShopVO();
				shopVO.setItemCategoryId(itemCategoryId);
				shopVO.setItemDescribtion(itemDescribtion);
				shopVO.setItemFee(itemFee);
				shopVO.setItemName(itemName);
				shopVO.setItemQuantity(itemQuantity);
				shopVO.setItemStatus(itemStatus);
				shopVO.setShopPicVO(shopPicVO);

				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的ShopVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/addCommodityContent.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.addShop(itemCategoryId, itemDescribtion, itemFee, itemName, itemQuantity, itemStatus,
						shopPicVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/shop/listAllShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllShop.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/addCommodityContent.jsp");
				failureView.forward(req, res);
			}
		}

		// 刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer itemId = new Integer(req.getParameter("itemId"));

				/*************************** 2.開始刪除資料 ***************************************/
				ShopService shopSvc = new ShopService();
				shopSvc.deleteShop(itemId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				// 資料刪除完後，還是到ListAllShop頁面
				String url = "/back-end/shop/listAllShop.jsp";
				// 刪除成功後,轉交回送出刪除的來源網頁
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/listAllShop.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改
		if ("update".equals(action)) { // 來自updateShop.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer itemId = new Integer(req.getParameter("itemId"));

				Integer itemCategoryId = new Integer(req.getParameter("itemCategoryId"));

				String itemDescribtion = req.getParameter("itemDescribtion");
				if (itemDescribtion == null || itemDescribtion.trim().length() == 0) {
					errorMsgs.add("商品敘述：請勿空白");
				}

				Integer itemFee = null;
				try {
					itemFee = new Integer(req.getParameter("itemFee").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格：請勿空白及負數");
				}

				String itemName = req.getParameter("itemName").trim();
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名稱：請勿空白");
				}

				Integer itemQuantity = null;
				try {
					itemQuantity = new Integer(req.getParameter("itemQuantity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量：請勿空白");
				}

				Byte itemStatus = null;
				itemStatus = new Byte(req.getParameter("itemStatus"));
				System.out.println("itemStatus: " + itemStatus);

				Integer commentNumber = new Integer(req.getParameter("commentNumber"));

				Integer commentTotalScore = new Integer(req.getParameter("commentTotalScore"));


//				Integer commentNumber = null;			
//				if(req.getParameter("commentNumber").trim().equals("")) {	// 如果commentNumber輸入空白 比對(equlas)空字串時					
//					commentNumber = 0;	// commentNumber為0					
//				} else {	// 否則					
//					commentNumber = new Integer(req.getParameter("commentNumber"));	// 如果有輸入數字，則顯示數字	
//				}
//				
//				Integer commentTotalScore = null;
//				if(req.getParameter("commentTotalScore").trim().equals("")) {	// 如果commentTotalScore輸入空白 比對(equlas)空字串時				
//					commentTotalScore = 0;	// commentTotalScore 為0				
//				} else {					
//					commentTotalScore = new Integer(req.getParameter("commentTotalScore"));	// 如果有輸入數字，則顯示數字				
//				}


				ShopVO shopVO = new ShopVO();
				shopVO.setItemId(itemId);
				shopVO.setItemCategoryId(itemCategoryId);
				shopVO.setItemDescribtion(itemDescribtion);
				shopVO.setItemFee(itemFee);
				shopVO.setItemName(itemName);
				shopVO.setItemQuantity(itemQuantity);
				shopVO.setItemStatus(itemStatus);
				shopVO.setCommentNumber(commentNumber);
				shopVO.setCommentTotalScore(commentTotalScore);
				
				// 商品圖片
				String shopPic = req.getParameter("pic_base64");
				
				ShopPicVO shopPicVO = new ShopPicVO();
				if (shopPic == null || shopPic.trim().length() == 0) {
					System.out.println("沒有選擇要修改的圖片!");
					System.out.println("shopPic: "+shopPic.toString());
				}else {
					String[] picArr = shopPic.split(",");
					String imageString = picArr[1];
					Decoder decoder = Base64.getDecoder();
					byte[] imageByte = decoder.decode(imageString);
					shopPicVO.setItemPhoto(imageByte);
					shopVO.setShopPicVO(shopPicVO);
				}
				
				
				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的shopVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/updateShop.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.updateShop(itemId, itemCategoryId, itemDescribtion, itemFee, itemName, itemQuantity,
						itemStatus, commentNumber, commentTotalScore, shopPicVO);
				shopVO.setShopPicSrc("images?queryImg=ITEM_PHOTO&tableName=SHOP_PIC&colName=ITEM_ID&queryId=" + itemId);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopVO", shopVO); // 資料庫update成功後,正確的的ShopVO物件,存入req
				String url = "/back-end/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneShop.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/updateShop.jsp");
				failureView.forward(req, res);
			}
		}

		// 拿取一個來 修改 or 刪除
		if ("getOne_For_Update".equals(action)) { // 來自listAllShop.jsp的請求
			System.out.println("ShopServlet-getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// 將此集合存儲在請求範圍中，以防我們需要
			// 發送錯誤頁面視圖
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer itemId = new Integer(req.getParameter("itemId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(itemId);
				System.out.println("getOne_For_Update__status: " + shopVO.getItemStatus());

				shopVO.setShopPicSrc("images?queryImg=ITEM_PHOTO&tableName=SHOP_PIC&colName=ITEM_ID&queryId=" + itemId);
				System.out.println("ShopPicSrc: " + shopVO.getShopPicSrc());

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的shopVO物件,存入req
				// 修改資料，跳到updateShop.jsp頁面
				String url = "/back-end/shop/updateShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateShop.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/listAllShop.jsp");
				failureView.forward(req, res);
			}
		}

		// 所有商品資料
		if ("getOne_For_Display".equals(action)) { // 來自selectPage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("itemId");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer itemId = null;
				try {
					itemId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(itemId);
				if (shopVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/backEndPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/shop/backEndPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/backEndPage.jsp");
				failureView.forward(req, res);
			}
		}

		// 搜尋關鍵字
		if ("getSearch".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("ShopServlet-getSearch");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer pageNumber = Integer.valueOf(req.getParameter("pageNumber"));
				Integer pageSize = Integer.valueOf(req.getParameter("pageSize"));
				String str = req.getParameter("itemName");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品名稱");
				}

				// 如果有錯誤，請將使用發送回表單
				if (!errorMsgs.isEmpty()) {
					System.out.println("errorMsgs.isEmpty() " + errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/searchCommodity.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ShopService shopSvc = new ShopService();
				List<ShopVO> shopVO = shopSvc.getSearch(str);
				// [1,2,3,4,5] [6,7]
				int shopListCount = shopVO.size();
				System.out.println("ShopServlet-getSearch-shopListCount: " + shopListCount);
				shopVO = shopVO.subList((pageNumber - 1) * pageSize,
						pageNumber * pageSize > shopListCount ? shopListCount : pageNumber * pageSize);
				if (shopVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的shopVO物件,存入req
				req.setAttribute("shopListCount", shopListCount);
				req.setAttribute("pageNumber", pageNumber);
				req.setAttribute("pageSize", pageSize);
				req.setAttribute("searcName", str);
				// 資料搜尋完成，跳到inquireRelatedProducts.jsp頁面
				String url = "/back-end/shop/searchCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 displayResult.jsp
				successView.forward(req, res);
				System.out.println("ShopServlet-getSearch-成功轉交 ");
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/searchCommodity.jsp");
				failureView.forward(req, res);
			}
		}
	}
}