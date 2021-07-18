package com.emp.controller;

import com.emp.model.EmpService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/emp/DelEmpServlet")
public class DelEmpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> errorMsgs = new LinkedList<String>();
//        List<String> Msgs = new LinkedList<String>();

        request.setAttribute("errorMsgs", errorMsgs);
//        request.setAttribute("Msgs", Msgs);

        //設置編碼
        request.setCharacterEncoding("utf-8");
        String requestURL = request.getParameter("requestURL");
//        System.out.println(requestURL);

        try {


            //1.獲取id
            Integer empId = new Integer(request.getParameter("empId"));

           ;
            //2.獲取請求路徑


            //3.調用service刪除
            EmpService empService = new EmpService();
            empService.deleteEmp(empId);


//            Msgs.add("管理員編號:"+empId+",刪除成功");
            //4.轉交
            RequestDispatcher successView = request.getRequestDispatcher("/emp/findEmpByPageServlet"); // 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(request, response);
        } catch (Exception e) {
            errorMsgs.add("刪除資料失敗:" + e.getMessage());

            RequestDispatcher failureView = request
                    .getRequestDispatcher(requestURL);
            failureView.forward(request, response);
        }

    }
}
