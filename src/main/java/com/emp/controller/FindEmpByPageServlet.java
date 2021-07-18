package com.emp.controller;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.utils.PageBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/emp/findEmpByPageServlet")
public class FindEmpByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 //設置編碼
        request.setCharacterEncoding("utf-8");
    	
    	
        Map<String, String> condition = new HashMap<String, String>();

        List<String> errorMsgs = new LinkedList<String>();
//        List<String> Msgs = new LinkedList<String>();

        request.setAttribute("errorMsgs", errorMsgs);
//        request.setAttribute("Msgs", Msgs);

        String requestURL = request.getParameter("requestURL");

//        ("路徑為:"+requestURL);

       

        try {



        //1.獲取參數
        String currentPage = request.getParameter("currentPage"); //當前頁碼
        String rows = request.getParameter("rows"); //每頁顯示的條數

        // 如果沒有直接讓他到第一頁
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }

        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        //1.1獲取條件查詢參數

            String EMP_ID = request.getParameter("EMP_ID");
            String EMP_NAME = request.getParameter("EMP_NAME");
            String EMP_ADD_DATE = request.getParameter("EMP_ADD_DATE");

            condition.put("EMP_ID",EMP_ID);
            condition.put("EMP_NAME",EMP_NAME);
            condition.put("EMP_ADD_DATE",EMP_ADD_DATE);


//        (condition.toString());
        //2.調用service來查詢
        EmpService empService = new EmpService();

        PageBean<EmpVO> pb = empService.findEmpByPage(currentPage,rows,condition);



        //3.將PageBean存入request
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//將查詢條件也一起放入

//        ("執行順利");
        //4.轉交
        request.getRequestDispatcher("/back-end/list.jsp").forward(request, response);

        } catch (Exception e) {
        errorMsgs.add(e.getMessage());
        RequestDispatcher failureView = request
                .getRequestDispatcher("/back-end/list.jsp");
        failureView.forward(request, response);
    }



    }
}
