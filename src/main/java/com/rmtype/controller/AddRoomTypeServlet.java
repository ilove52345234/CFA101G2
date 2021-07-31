/*    */ package com.rmtype.controller;
/*    */
/*    */ import com.fasterxml.jackson.core.type.TypeReference;
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import com.rmtype.model.RtService;
/*    */ import com.rmtype.model.RtVO;
/*    */ import com.utils.Base64VO;
/*    */ import com.utils.ResultInfo;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */
/*    */
/*    */ @WebServlet({"/rmtype/addRoomTypeServlet"})
/*    */ public class AddRoomTypeServlet
        /*    */   extends HttpServlet
        /*    */ {
    /* 22 */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response); }
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 28 */     System.out.println("接收到了");
        /*    */
        /* 30 */     ResultInfo info = new ResultInfo();
        /*    */
        /* 32 */     ObjectMapper mapper = new ObjectMapper();
        /*    */
        /*    */
        /*    */
        /*    */
        /*    */
        /*    */
        /*    */     try {



            /* 40 */       String date = request.getParameter("date");
            /* 41 */       String json = request.getParameter("submitArr");
            /*    */
            /*    */
            /* 44 */       RtVO rtVO = (RtVO)mapper.readValue(date, RtVO.class);
            /* 45 */       rtVO.setRoomTotalPerson(Integer.valueOf(0));
            /* 46 */       rtVO.setRoomTotalScore(Integer.valueOf(0));
            /* 47 */       String roomTypeContent = rtVO.getRoomTypeContent();
            /* 48 */       if (roomTypeContent == null || "".equals(roomTypeContent)) {
                /* 49 */         rtVO.setRoomTypeContent("NONE");
                /*    */       }
            /*    */
            /*    */
            /*    */
            /* 54 */       TypeReference<List<Base64VO>> typeReference = new TypeReference<List<Base64VO>>() {  }
                    /*    */         ;
            /* 56 */       List<Base64VO> list = (List)mapper.readValue(json, typeReference);
            /*    */
            /*    */
            /* 59 */       RtService rtService = new RtService();
            /*    */
            /*    */
            /* 62 */       rtService.addRoomTypeAndPic(rtVO, list);
            /*    */
            /*    */
            /* 65 */       System.out.println("順利執行完畢");
            /* 66 */       info.flag = true;
            /*    */     }
        /* 68 */     catch (Exception e) {
            /* 69 */       e.printStackTrace();
            /* 70 */       String message = e.getMessage();
            /* 71 */       info.flag = false;
            /* 72 */       info.errorMsg = message;
            /*    */     }
        /*    */
        /* 75 */     System.out.println(info);
        /*    */
        /* 77 */     response.setContentType("application/json;charset=utf-8");
        /* 78 */     mapper.writeValue(response.getOutputStream(), info);
        /*    */   }
    /*    */ }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/controller/AddRoomTypeServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */