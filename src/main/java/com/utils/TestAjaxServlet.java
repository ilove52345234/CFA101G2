package com.utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TestAjaxServlet")
public class TestAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Ajax請求");


        String room_order_id = request.getParameter("ROOM_ORDER_ID");

        System.out.println(room_order_id);
        PrintWriter writer = response.getWriter();


    }
}
