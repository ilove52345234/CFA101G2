package com.rmorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmorder.model.RmoService;
import com.rmorder.model.RmoVO;
import com.utils.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/room/checkRmorderServlet")
public class CheckRmorderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("訂單接收到了");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        RmoService rmoService = new RmoService();
        ResultInfo info = new ResultInfo();

        try {

            //取得參數
            String room_order_id = request.getParameter("ROOM_ORDER_ID");
            String status = request.getParameter("status");


            //修改房間資料
            RmoVO oneRmo = rmoService.getOneRmo(Integer.parseInt(room_order_id));

            if ("true".equals(status)) {
                oneRmo.setRoomOrderStatus(1);
            }
            if ("false".equals(status)) {
                oneRmo.setRoomOrderStatus(2);
            }
            if ("end".equals(status)) {
                oneRmo.setRoomOrderStatus(3);
            }

            rmoService.updateRmoVO(oneRmo);

            //新增詳細列表

            info.setFlag(true);
        } catch (Exception e) {
            info.setErrorMsg("發生錯誤:" + e.getMessage());
            info.setFlag(false);
        }
        mapper.writeValue(response.getOutputStream(), info);
    }
}
