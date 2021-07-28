package com.rmorderlist.controller;

import com.extrabill.model.ExtraBillService;
import com.extrabill.model.ExtraBillVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmorderlist.model.RmolService;
import com.rmorderlist.model.RmolVO;
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

@WebServlet("/rmorderlist/roomUpdateServlet")
public class RoomUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("roomUpdate接收到了");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        RmService rmService = new RmService();
        RmolService rmolService = new RmolService();
        ResultInfo info = new ResultInfo();

        try {

            //取得參數
            String roomId = request.getParameter("ROOM_ID");
            String orderListId = request.getParameter("orderListId");


            //修改房間資料
            RmolVO rmolVO = rmolService.getOneRt(Integer.parseInt(orderListId));

            //登記房間
            if (rmolVO.getRoomId() == 0) {
                rmolVO.setRoomId(Integer.parseInt(roomId));
                rmolService.updateRmolVO(rmolVO);


                RmVO oneRm = rmService.getOneRm(Integer.parseInt(roomId));
                oneRm.setRoomCheckStatus((byte) 1);//修改為待入住
                oneRm.setRoomSaleStatus((byte) 1);//修改為不可用
                rmService.updateRmVO(oneRm);

                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("已有登記房間");
            }

        } catch (Exception e) {
            info.setErrorMsg("發生錯誤:" + e.getMessage());
            info.setFlag(false);
        }

        System.out.println("轉交");
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }
}
