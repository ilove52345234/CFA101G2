package com.room.controller;

import com.extrabill.model.ExtraBillService;
import com.extrabill.model.ExtraBillVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.model.RmService;
import com.room.model.RmVO;
import com.utils.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/room/checkOutServlet")
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Out接收到了");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        RmService rmService = new RmService();
        ExtraBillService extraBillService = new ExtraBillService();
        ResultInfo info = new ResultInfo();
        try {


            //取得參數
             String roomId = request.getParameter("roomId");
        //修改房間資料
        RmVO oneRm = rmService.getOneRm(Integer.parseInt(roomId));
        oneRm.setRoomCheckStatus((byte) 3);//修改為已退房
        RmVO rmVO = rmService.updateRmVO(oneRm);

        //修改詳細列表
            ExtraBillVO extraBillVO = extraBillService.GetOne(rmVO.getRoomId());

            extraBillVO.setCheckOutDate( new Timestamp(System.currentTimeMillis()));

            extraBillService.CheckOut(extraBillVO);

            info.setFlag(true);
        }catch (Exception e){
            info.setErrorMsg("發生錯誤:" + e.getMessage());
            info.setFlag(false);
        }

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }
}
