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

    private RmolService rmolService = new RmolService();

    private RmService rmService = new RmService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("接收到了");
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

//        Map<String, String> condition = new HashMap<String, String>();//參數

        Map<String, List> rmorder = new HashMap<String, List>();


//        String currentPage = request.getParameter("currentPage");
//        String rows = request.getParameter("rows");
//
//        if (currentPage == null || "".equals(currentPage)) {
//            currentPage = "1";
//        }
//
//        if (rows == null || "".equals(rows)) {
//            rows = "5";
//        }

        String ROOM_ORDER_ID = request.getParameter("ROOM_ORDER_ID");

//        System.out.println("參數:"+ROOM_ORDER_ID);
//        condition.put("ROOM_ORDER_ID", ROOM_ORDER_ID);

        List<RmolVO> list = this.rmolService.getAllByROOM_ORDER_ID(Integer.parseInt(ROOM_ORDER_ID));
        List<RmVO> all = this.rmService.getAll();


        rmorder.put("list", list);
        rmorder.put("all", all);

        System.out.println("開始轉交");
        mapper.writeValue(response.getOutputStream(), rmorder);
    }
}
