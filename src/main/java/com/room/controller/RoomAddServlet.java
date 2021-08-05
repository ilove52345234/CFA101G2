package com.room.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmtype.model.RtService;
import com.rmtype.model.RtVO;
import com.room.model.RmService;
import com.room.model.RmVO;
import com.utils.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/room/roomAddServlet")
public class RoomAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("新增房間接收到了");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        RmService rmService = new RmService();
        ResultInfo info = new ResultInfo();
        RtService rtService = new RtService();
        try {

        //取得參數
        String amount = request.getParameter("amount");
        String RoomCategoryId = request.getParameter("RoomCategoryId");
        int CategoryId = Integer.parseInt(RoomCategoryId);
            int anInt = Integer.parseInt(amount);
            //取得房型資料
        RtVO oneRt = rtService.getOneRt(CategoryId);
        //取得所有房間數量
            int rm = rmService.getOneRmByRoomCategoryId(CategoryId);
            int roomTypeAmount = oneRt.getRoomTypeAmount();
            if((rm+anInt)>roomTypeAmount ){
                info.setFlag(false);
                info.setErrorMsg("超出最大房間數,房間最大數量為:"+roomTypeAmount);
            }else{

                for (int j = 0; j <= anInt; j++) {
                    RmVO rmVO = rmService.addRmVO2(CategoryId);
                    System.out.println(rmVO);
                }

                info.setFlag(true);
            }

        }catch (Exception e){
            info.setErrorMsg("發生錯誤:" + e.getMessage());
            info.setFlag(false);
        }

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }
}
