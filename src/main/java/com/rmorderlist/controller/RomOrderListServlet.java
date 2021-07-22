package com.rmorderlist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmorderlist.model.RmolService;
import com.rmorderlist.model.RmolVO;
import com.room.model.RmService;
import com.room.model.RmVO;
import com.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/rmorderlist/romOrderListServlet")
public class RomOrderListServlet extends HttpServlet {

    private RmolService rmolService  = new RmolService();
    private RmService  rmService = new RmService();


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
        Map<String, PageBean> rmorder = new HashMap<String, PageBean>();
        response.setContentType("text/html;charset=utf-8");

        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "99999";
        }

        String ROOM_ORDER_ID = request.getParameter("ROOM_ORDER_ID");

        System.out.println(ROOM_ORDER_ID);

        condition.put("ROOM_ORDER_ID", ROOM_ORDER_ID);


        PageBean<RmolVO> rmolByPage = this.rmolService.findRmolByPage(currentPage, currentPage, condition);

        PageBean<RmVO> validRoom = this.rmService.getValidRoom(currentPage, currentPage);

        List<RmVO> list = validRoom.getList();

        for (RmVO rmVO : list) {
            System.out.println(rmVO);
        }

        rmorder.put("rmolByPage", rmolByPage);
        rmorder.put("validRoom", validRoom);

        System.out.println("開始轉交");
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), rmorder);
    }
}
