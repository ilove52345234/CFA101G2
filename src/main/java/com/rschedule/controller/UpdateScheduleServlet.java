package com.rschedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmorderlist.model.RmolService;
import com.room.model.RmService;
import com.rschedule.model.RsService;
import com.rschedule.model.RsVO;
import com.utils.ResultInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/rschedule/updateScheduleServlet")
public class UpdateScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("房況接收到了");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List> rschedule = new HashMap<String, List>();
        RsService rsService =  new RsService();

        try {

            String roomCategoryId = request.getParameter("RoomCategoryId");

            List<RsVO> list = rsService.getAllByRoomCategoryId(Integer.parseInt(roomCategoryId));



           rschedule.put("list",list);

        } catch (Exception e) {
             e.printStackTrace();
        }

        System.out.println("轉交");
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), rschedule);
    }
}
