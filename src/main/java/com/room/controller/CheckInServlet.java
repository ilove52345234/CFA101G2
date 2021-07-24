package com.room.controller;

import com.extrabill.model.ExtraBillService;
import com.extrabill.model.ExtraBillVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.model.RmService;
import com.room.model.RmVO;
import com.utils.ResultInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

@WebServlet("/room/CheckInServlet")
public class CheckInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("CH接收到了");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        RmService rmService = new RmService();
        ExtraBillService extraBillService = new ExtraBillService();
        ResultInfo info = new ResultInfo();

        try {


            //取得參數
            String roomId = request.getParameter("roomId");
        String roomInformation = request.getParameter("roomInformation");
        String informationPhone = request.getParameter("informationPhone");




        //修改房間資料
        RmVO oneRm = rmService.getOneRm(Integer.parseInt(roomId));
        oneRm.setRoomInformation(roomInformation);//填入住客名字
        oneRm.setRoomCheckStatus((byte) 2);//修改為入住中
        oneRm.setRoomSaleStatus((byte) 1);//修改為不可用
        RmVO rmVO = rmService.updateRmVO(oneRm);

        //新增詳細列表

        ExtraBillVO extraBillVO = extraBillService.CheckIn(rmVO.getRoomId(), informationPhone, 0, "", new Timestamp(System.currentTimeMillis()), null);

        info.setFlag(true);
        }catch (Exception e){
            info.setErrorMsg("發生錯誤:" + e.getMessage());
            info.setFlag(false);
        }

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }
}
