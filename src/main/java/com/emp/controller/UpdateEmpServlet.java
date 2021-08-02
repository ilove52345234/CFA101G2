package com.emp.controller;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.mpfunc.model.MpFuncService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/emp/updateEmpServlet")
public class UpdateEmpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");


        List<String> errorMsgs = new LinkedList<String>();
        List<String> Msgs = new LinkedList<String>();

        request.setAttribute("errorMsgs", errorMsgs);
        request.setAttribute("Msgs", Msgs);
        String requestURL = request.getParameter("requestURL");

        //設置編碼
        request.setCharacterEncoding("utf-8");

        try {

            Integer empId = new Integer(request.getParameter("empId").trim());


            //姓名
            String empName = request.getParameter("empName");
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

//            System.out.println(empName);

            //如果輸入為空的話
            if (empName == null || empName.trim().length() == 0) {
                errorMsgs.add("姓名: 請勿空白");
            } else if (!empName.trim().matches(enameReg)) {

                //如果trim後不符合正規表達式的話,matches為false反轉回true進到這裡
                errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }


            //帳號
            String accountReg = "^[0-9A-Za-z]{3,16}$";
            String empAccount = request.getParameter("empAccount");
            //如果輸入為空的話
            if (empAccount == null || empAccount.trim().length() == 0) {
                errorMsgs.add("帳號: 請勿空白");
            } else if (!empAccount.trim().matches(accountReg)) {

                //如果trim後不符合正規表達式的話,matches為false反轉回true進到這裡
                errorMsgs.add("帳號: 只能是英文字母、數字, 且長度必需在3到16之間");
            }


            //密碼
            String passwordReg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
            String empPassword = request.getParameter("empPassword");

            //如果輸入為空的話
            if (empPassword == null || empPassword.trim().length() == 0) {
                errorMsgs.add("密碼: 請勿空白");
            } else if (!empPassword.trim().matches(passwordReg)) {

                //如果trim後不符合正規表達式的話,matches為false反轉回true進到這裡
                errorMsgs.add("密碼: 必須是數字與英文混合, 且長度必需在8到16之間");
            }



            //狀態
            Integer empStatus = null;

            empStatus = new Integer(request.getParameter("empStatus").trim());

            //日期
            java.sql.Date empAddDate = null;
            try {
                empAddDate = java.sql.Date.valueOf(request.getParameter("empAddDate").trim());
            } catch (IllegalArgumentException e) {
                empAddDate=new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }



            //封裝VO
            EmpVO empVO = new EmpVO();

            empVO.setEmpId(empId);
            empVO.setEmpName(empName);
            empVO.setEmpAccount(empAccount);
            empVO.setEmpPassword(empPassword);
            empVO.setEmpStatus(empStatus);
            empVO.setEmpAddDate(empAddDate);

//            System.out.println(empVO);

            if (!errorMsgs.isEmpty()) {
                request.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/emp/GetOneEmpServlet");
                failureView.forward(request, response);
                return;
            }


            //調用Service保存
            EmpService empService = new EmpService();

            empService.updateEmp(empId,empName,empAccount,empPassword,empStatus,empAddDate);


            MpFuncService mpFuncService = new MpFuncService();


            mpFuncService.deleteAllMpFunc(empId);

            String[] funs  = null;
             funs = request.getParameterValues("funs");

            if(funs != null) {
                for (String fun : funs) {
                    mpFuncService.addMpFunc(empId, Integer.parseInt(fun));
                }
            }
            Msgs.add("管理員:"+empAccount+"修改成功");


            RequestDispatcher successView = request.getRequestDispatcher("/emp/findEmpByPageServlet"); // 新增成功後轉交listAllEmp.jsp
//            ("路徑完成");
            successView.forward(request, response);
//            ("完成轉交");

        }catch (Exception e) {
            errorMsgs.add(e.getMessage());
            RequestDispatcher failureView = request
                    .getRequestDispatcher(requestURL);
            failureView.forward(request, response);
        }


    }
}
