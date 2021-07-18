package com.emp.controller;

import com.emp.model.EmpService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/emp/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<String> errorMsgs = new LinkedList<String>();

        request.setAttribute("errorMsgs", errorMsgs);

        //設置編碼
        request.setCharacterEncoding("utf-8");
        String requestURL = request.getParameter("requestURL");


        try{
            //獲取所有的id
            String[] delEmpIds = request.getParameterValues("delEmpId");

            //調用刪除

            EmpService empService = new EmpService();
            empService.delSelectedEmp(delEmpIds);



            RequestDispatcher successView = request.getRequestDispatcher("/emp/findEmpByPageServlet");
            successView.forward(request, response);

        }catch (Exception e) {
            errorMsgs.add("刪除資料失敗:" + e.getMessage());

            RequestDispatcher failureView = request
                    .getRequestDispatcher("/emp/findEmpByPageServlet");
            failureView.forward(request, response);
        }


    }
}
