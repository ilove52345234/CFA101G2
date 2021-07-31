package com.emp.controller;

import com.emp.model.EmpService;
import com.func.model.FuncService;
import com.func.model.FuncVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/emp/EmpFuncServlet")
public class EmpFuncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        try {
            Integer empId = new Integer(request.getParameter("empId"));
            String empName = (request.getParameter("empName"));
//            System.out.println(empId);
            Set<FuncVO> funcByEmpId = new EmpService().getFuncByEmpId(empId);

            request.setAttribute("funcByEmpId", funcByEmpId);
            request.setAttribute("empName", empName);
            RequestDispatcher successView = request
                    .getRequestDispatcher("/back-end/emp/listOneEmpFunc.jsp");

            successView.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }
}
