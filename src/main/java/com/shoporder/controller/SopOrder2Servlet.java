package com.shoporder.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoporder.model.ShopOrderService;

@WebServlet("/shoporder2.do")
public class SopOrder2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        // 取消訂單
        System.out.println(action);
        if ("cancel_shoporder".equals(action)) {
            String requestURL = req.getParameter("requestURL");
            String whichPage = req.getParameter("whichPage");
            Integer itemOrderId = new Integer(req.getParameter("itemOrderId"));

            try {
                ShopOrderService shopOrderService = new ShopOrderService();
                shopOrderService.cancelShopOrder(3, itemOrderId); // 訂單狀態1:已取消
                res.sendRedirect("/CFA101G2" + requestURL + "?whichPage=" + whichPage);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        // 前往信用卡付款頁面
        if ("pay_by_card".equals(action)) {

            try {
                req.setAttribute("requestURL", req.getParameter("requestURL"));
                req.setAttribute("whichPage", req.getParameter("whichPage"));
                req.setAttribute("itemOrderId", req.getParameter("itemOrderId"));
                req.setAttribute("itemAmounts", req.getParameter("itemAmounts"));
                RequestDispatcher view = req.getRequestDispatcher("/front-end/shop/pay.jsp");
                view.forward(req, res);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        // 進行信用卡付款
        if ("pay_finish".equals(action)) {

            String requestURL = req.getParameter("requestURL");
            String whichPage = req.getParameter("whichPage");
            System.out.println(requestURL + whichPage);
            Integer itemOrderId = new Integer(req.getParameter("itemOrderId"));
            System.out.println(itemOrderId);

            try {
                ShopOrderService ordSvc = new ShopOrderService();
                ordSvc.confirmShopOrder(1, itemOrderId); // 訂單狀態1:出貨準備中
                res.sendRedirect("/CFA101G2" + requestURL + "?whichPage=" + whichPage);
            } catch (Exception e) {
                throw new ServletException(e);
            }

        }
    }
}