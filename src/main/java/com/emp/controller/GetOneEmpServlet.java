package com.emp.controller;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.func.model.FuncVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@WebServlet("/emp/GetOneEmpServlet")
public class GetOneEmpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> errorMsgs = new LinkedList<String>();

        EmpService empService = new EmpService();


        //設置編碼
        request.setCharacterEncoding("utf-8");

        try{
            //獲取id
            Integer empId = new Integer(request.getParameter("empId"));

            //調用方法查詢到對應的VO物件與權限列表
            EmpVO emp = empService.getOneEmp(empId);
            Set<FuncVO> func = empService.getFuncByEmpId(empId);


            //存入request中
            request.setAttribute("emp",emp);
            request.setAttribute("func",func);

//            ("準備轉交");
            //4.轉交
            RequestDispatcher successView = request.getRequestDispatcher("/back-end/update.jsp");
            successView.forward(request, response);


        }catch (Exception e) {
            errorMsgs.add(e.getMessage());
            RequestDispatcher failureView = request
                    .getRequestDispatcher(request.getContextPath()+"/back-end/list.jsp");
            failureView.forward(request, response);
        }


    }
}
