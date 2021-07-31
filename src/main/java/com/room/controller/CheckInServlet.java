package com.room.controller;

import com.extrabill.model.ExtraBillService;
import com.extrabill.model.ExtraBillVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.model.RmService;
import com.room.model.RmVO;
import com.utils.DateUtils;
import com.utils.ResultInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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
        String stayDays = request.getParameter("stayDays");

        System.out.println("入住天數:"+stayDays);
//        String dateStr="2021-8-01 00:00:00";
//        String dateStr="2021-7-31 ";


        //修改房間資料
        RmVO oneRm = rmService.getOneRm(Integer.parseInt(roomId));
        oneRm.setRoomInformation(roomInformation);//填入住客名字
        oneRm.setRoomCheckStatus((byte) 2);//修改為入住中
        oneRm.setRoomSaleStatus((byte) 1);//修改為不可用
        RmVO rmVO = rmService.updateRmVO(oneRm);


            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            Date expected = new DateUtils().addAndSubtractDaysByGetTime(date, Integer.parseInt(stayDays));


            //新增詳細列表
        ExtraBillVO extraBillVO = extraBillService.CheckIn(rmVO.getRoomId(), informationPhone, 0,new Timestamp(expected.getTime()) , timestamp, null);

            Timer timer ;
            timer = new Timer();
            System.out.println("排程器設定");
            TimerTask task = new TimerTask(){
                @Override
                public void run() {
                    System.out.println("開始修改為待退房");
                    oneRm.setRoomCheckStatus((byte) 4);
                    RmVO rmVO = rmService.updateRmVO(oneRm);
                    System.out.println("修改完畢:"+rmVO);
                    timer.cancel();
                }
            };
            timer.schedule(task,expected);
//            timer.schedule(task,new DateUtils().getUtilDate(dateStr));

        info.setFlag(true);
        }catch (Exception e){
            info.setErrorMsg("發生錯誤:" + e.getMessage());
            info.setFlag(false);
        }

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }
}
