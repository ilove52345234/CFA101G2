package com.emp.controller;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/emp/EmpListServlet")
public class EmpListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        //設置編碼
        request.setCharacterEncoding("utf-8");


        //調用Service物件
        EmpService empService = new EmpService();

        //查詢全部
        List<EmpVO> emps = empService.getAll();

//        for (EmpVO emp : emps) {
//
//            System.out.println(emp);
//        }

        //存到request
        request.setAttribute("emps",emps);


        request.getRequestDispatcher("/back-end/list.jsp").forward(request, response);

    }

}
