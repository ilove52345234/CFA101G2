package com.rmorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmorder.model.RmoService;
import com.rmorder.model.RmoVO;
import com.rmtype.model.RtService;
import com.rmtype.model.RtVO;
import com.utils.PageBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/rmorder/romListServlet")
public class RomListServlet extends HttpServlet {

    private RmoService rmoService = new RmoService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("接收到了");

        ObjectMapper mapper = new ObjectMapper();

        request.setCharacterEncoding("UTF-8");

        Map<String, String> condition = new HashMap<String, String>();
        response.setContentType("text/html;charset=utf-8");

        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        String MEM_ID = request.getParameter("MEM_ID");
        String ROOM_ORDER_ID = request.getParameter("ROOM_ORDER_ID");

        System.out.println(ROOM_ORDER_ID);

        condition.put("MEM_ID", MEM_ID);
        condition.put("ROOM_ORDER_ID", ROOM_ORDER_ID);

        PageBean<RmoVO> rmoByPage = this.rmoService.findRmoByPage(currentPage, rows, condition);

        List<RmoVO> list = rmoByPage.getList();

        System.out.println("開始轉交");
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), rmoByPage);
    }
}
