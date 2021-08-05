package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import com.shoporder.model.*;
import com.shoporderdetail.model.*;
import com.shoporder.model.ShopOrderService;
import com.mem.model.MemVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

@WebServlet("/ShoppingCart.do")

public class ShoppingCartServlet extends HttpServlet {

 private static final long serialVersionUID = -7822726794535774162L;

 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  this.doPost(req, res);
 }

 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  req.setCharacterEncoding("UTF-8");
  res.setContentType("text/html; charset=UTF-8");
  HttpSession session = req.getSession();

  @SuppressWarnings("unchecked")
  Vector<ShopVO> buylist = (Vector<ShopVO>) session.getAttribute("shoppingcart");
  String action = req.getParameter("action");
  System.out.println("action" + action);
  // 如果動作執行不是結帳時
  if (!action.equals("CHECKOUT")) {

   // 刪除購物車中的商品
   if (action.equals("DELETE")) {
    String del = req.getParameter("del");
    int d = Integer.parseInt(del);
    buylist.remove(d);

    session.setAttribute("shoppingcart", buylist);
    String url = "/front-end/shop/shopHomePage.jsp";// 跳轉至shopHomePage.jsp頁面
    RequestDispatcher rd = req.getRequestDispatcher(url);
    rd.forward(req, res);

   }
   // 新增商品至購物車中
   else if (action.equals("ADD")) {
    System.out.println("印起來");
    // 取得後來新增的商品
    ShopVO ashop = getShopVO(req);

    // 新增第一個商品至購物車時
    if (buylist == null) {
     buylist = new Vector<ShopVO>();
     buylist.add(ashop);
    } else {
     if (buylist.contains(ashop)) {
      ShopVO innerShopVO = buylist.get(buylist.indexOf(ashop));
      System.out.println(innerShopVO);
      innerShopVO.setOrderQuantity(innerShopVO.getOrderQuantity() + ashop.getOrderQuantity());
      // 假如新增的商品和購物車的商品一樣時
     } else {
      buylist.add(ashop);
     }
    }

   }

   if (!("add_one".equals(action) || "down_one".equals(action) || "change_orderQuantity".equals(action)||"confirm_check".equals(action)||"delete_item".equals(action))) {
    for (ShopVO i : buylist) {
     System.out.println(i);
    }

    session.setAttribute("shoppingcart", buylist);
    String url = "/front-end/shop/shoppingCart.jsp";// 跳轉至shoppingCart頁面
    RequestDispatcher rd = req.getRequestDispatcher(url);
    rd.forward(req, res);
   }
  }
  // 數量+1、-1(Ajax)
  if ("add_one".equals(action) || "down_one".equals(action) || "change_orderQuantity".equals(action)) {
   try {
    Integer itemId = new Integer(req.getParameter("itemId"));
    Integer back_itemQuantity = new Integer(req.getParameter("orderQuantity"));
    // 目前資料庫的商品庫存
    ShopService shopSvc = new ShopService();
    ShopVO shopVO = shopSvc.getOneShop(itemId);
    Integer itemQuantity = shopVO.getItemQuantity();

    JSONObject obj = new JSONObject();
    // 更改數量
    if ("change_orderQuantity".equals(action)) {
     // 從buylist裡面找要更改的項目
     for (ShopVO buyVO : buylist) {
      if (buyVO.getItemId().equals(itemId)) {
       // 如果修改的數量小於或等於庫存量=>修改數量
       if (back_itemQuantity > 0 && back_itemQuantity <= itemQuantity) {
        buyVO.setOrderQuantity(back_itemQuantity);
        obj.put("orderQuantity", buyVO.getOrderQuantity());
        break;
       } else if (back_itemQuantity == 0) {
        obj.put("status", "return");
        obj.put("orderQuantity", buyVO.getOrderQuantity());
        break;
       } else {
        obj.put("status", "failure");
        obj.put("orderQuantity", buyVO.getOrderQuantity());
        break;
       }
      }
     }
     obj.put("itemQuantity", itemQuantity);
    }
    // 數量+1
    if ("add_one".equals(action)) {
     // 從buylist中找出要+1的商品
     for (ShopVO buyVO : buylist) {
      if (buyVO.getItemId().equals(itemId))
       ;
      // 修改數量 小於或等於庫存=>選購的數量就+1
      System.out.println(back_itemQuantity);
      System.out.println(itemQuantity);
      if (buyVO.getOrderQuantity() < itemQuantity) {
       buyVO.setOrderQuantity(back_itemQuantity + 1);
       obj.put("status", "success");
      }

      // 如果購買數量大於等於庫存量=>不允許+1 且發出錯誤驗證
      obj.put("itemQuantity", itemQuantity);
      obj.put("orderQuantity", buyVO.getOrderQuantity());
     }
    }
    // 數量-1
    else if ("down_one".equals(action)) {
     for (ShopVO buyVO : buylist) {
      if (buyVO.getItemId().equals(itemId)) {
       buyVO.setOrderQuantity(back_itemQuantity - 1);
       obj.put("itemQuantity", shopVO.getItemQuantity());
       obj.put("orderQuantity", buyVO.getOrderQuantity());
       break;
      }
     }
    }
    PrintWriter out = res.getWriter();
    out.write(obj.toString());
    System.out.println(obj.toString());
    out.flush();
    out.close();
   } catch (Exception e) {
    throw new ServletException(e);
   }
  }
  // 結帳,計算購物車商品的價錢與總數
  else if (action.equals("CHECKOUT")) {
//			int total = 0;
//			for (int i = 0; i < buylist.size(); i++) {
//				ShopVO order = buylist.get(i);
//				int price = order.getItemFee();
//				int quantity = order.getOrderQuantity();
//				total += (price * quantity);
//			}
//			String amount = String.valueOf(total);
//			req.setAttribute("amount", amount);
   String url = "/front-end/shop/shoppingCheckOut.jsp";// 跳轉至checkout頁面
   RequestDispatcher rd = req.getRequestDispatcher(url);
   rd.forward(req, res);
  }
  if ("delete_item".equals(action)) {
   try {
    Integer itemId = new Integer(req.getParameter("itemId"));
    for (ShopVO buyVO: buylist) {
     if (buyVO.getItemId().equals(itemId)) {
      buylist.remove(buyVO);
      break;
     }
    }
   } catch (Exception e) {
    throw new ServletException(e);
   }
  }
  // 送出訂單
  if ("confirm_check".equals(action)) {
   System.out.println("訂單確認");
   try {
    ShopOrderVO shopOrderVO = new ShopOrderVO();

    MemVO memVO = (MemVO)session.getAttribute("memVO");
    shopOrderVO.setMemId(memVO.getMemId());
    System.out.println(memVO.getMemId());

    String shipType = req.getParameter("shipType");
    Byte ship = Byte.parseByte(shipType);
    shopOrderVO.setShippingMethod(ship);

    String name = req.getParameter("name");
    shopOrderVO.setOrderName(name);
    System.out.println(name);

    String mobile = req.getParameter("mobile");
    shopOrderVO.setOrderMobile(mobile);
    System.out.println(mobile);

    if (req.getParameter("cvs") != "") {
     String cvs = req.getParameter("cvs");
     shopOrderVO.setOrderAddress(cvs);
     System.out.println(cvs);
    } else {
     String addr = req.getParameter("addr");
     StringBuffer sb = new StringBuffer(addr);
     shopOrderVO.setOrderAddress(sb.toString());
     System.out.println(sb.toString());
    }

    Integer total = new Integer(req.getParameter("total"));
    shopOrderVO.setItemAmounts(total);
    System.out.println(" total: " + total);

    Byte paymentMethod = new Byte(req.getParameter("payType"));
    shopOrderVO.setPaymentMethod(paymentMethod);
    System.out.println("付款方式:"+paymentMethod);
    if (!paymentMethod.equals((byte) 0)) {// 2是選擇超商
     shopOrderVO.setShippingStatus((byte) 4);// 0:成立 1:出貨 2:完成 3:取消4:未付款
    } else {
     shopOrderVO.setShippingStatus((byte) 0);//
    }

    List<ShopOrderDetailVO> ordList = new ArrayList<ShopOrderDetailVO>();
    Integer count = new Integer(req.getParameter("count"));
    System.out.println(count);
    for (int i = 1; i <= count; i++) {

     ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();

     Integer itemId = new Integer(req.getParameter("item" + i));
     shopOrderDetailVO.setItemId(itemId);
     System.out.println("商品名稱"+itemId);

     Integer orderQuantity = new Integer(req.getParameter("orderQuantity"+i));
     shopOrderDetailVO.setOrderQuantity(orderQuantity);
     System.out.println("下單數量"+orderQuantity);
     shopOrderDetailVO.setItemPromotionId(0);

     Integer price = new Integer(req.getParameter("itemFee" + i));
     shopOrderDetailVO.setItemAmounts(price);
     System.out.println(price);

     shopOrderDetailVO.setItemFinalAmount(price);

     ordList.add(shopOrderDetailVO);
     System.out.println(itemId + ", " + orderQuantity + ", " + price);
    }

    ShopOrderService shopOrdSvc = new ShopOrderService();
    Integer itemOrderId = shopOrdSvc.insertWithShopOrderDetail(shopOrderVO, ordList);
    System.out.println("下單編號"+itemOrderId);
    // 清空購物車session
    session.removeAttribute("shoppingcart");
    System.out.println("進來控制器了");
    req.setAttribute("status", "訂單成立");

    req.setAttribute("itemOrderId", itemOrderId);
    System.out.println(itemOrderId);
    RequestDispatcher view = req.getRequestDispatcher("/front-end/shop/shoppingCartCheck.jsp");
    view.forward(req, res);

   } catch (Exception e) {
    e.printStackTrace();
    req.setAttribute("status", "訂購失敗，請重新操作");
    System.out.println("新增訂單失敗");
    RequestDispatcher view = req.getRequestDispatcher("/front-end/shop/shoppingCartCheck.jsp");
    view.forward(req, res);
   }
  }

 }

 private ShopVO getShopVO(HttpServletRequest req) {

  String itemId = req.getParameter("itemId");
  String itemName = req.getParameter("itemName");
  String itemFee = req.getParameter("itemFee");
  String itemDescribtion = req.getParameter("itemDescribtion");
  String orderQuantity = req.getParameter("orderQuantity");
  String commentNumber = req.getParameter("commentNumber");
  String commentTotalScore = req.getParameter("commentTotalScore");
  String itemQuantity = req.getParameter("itemQuantity");

  ShopVO shop = new ShopVO();

  shop.setItemId((Integer.parseInt(itemId)));
  shop.setItemName(itemName);
  shop.setItemFee((new Integer(itemFee)).intValue());
  shop.setItemDescribtion(itemDescribtion);
  shop.setOrderQuantity((new Integer(orderQuantity)).intValue());
  shop.setCommentNumber((new Integer(commentNumber)).intValue());
  shop.setCommentTotalScore((new Integer(commentTotalScore)).intValue());
  shop.setItemQuantity((new Integer(itemQuantity)).intValue());

  return shop;

 }

}