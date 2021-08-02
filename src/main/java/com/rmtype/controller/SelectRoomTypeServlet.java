 package com.rmtype.controller;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.rmtype.model.RtService;
 import com.rmtype.model.RtVO;
 import com.utils.PageBean;
 import java.io.IOException;
 import java.util.HashMap;
 import java.util.Map;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 @WebServlet({"/rmtype/selectRoomTypeServlet"})
 public class SelectRoomTypeServlet
           extends HttpServlet
         {
    /* 20 */   private RtService rtService = new RtService();
    
    
    
    /* 24 */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response); }
    
    
    
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 29 */     System.out.println("接收到了");
        /* 30 */     ObjectMapper mapper = new ObjectMapper();
        
        /* 32 */     request.setCharacterEncoding("UTF-8");
        
        /* 34 */     Map<String, String> condition = new HashMap<String, String>();
        /* 35 */     response.setContentType("text/html;charset=utf-8");
        
        
        
        /* 39 */     String currentPage = request.getParameter("currentPage");
        /* 40 */     String rows = request.getParameter("rows");
        
        
        /* 43 */     if (currentPage == null || "".equals(currentPage)) {
            /* 44 */       currentPage = "1";
                 }
        
        /* 47 */     if (rows == null || "".equals(rows)) {
            /* 48 */       rows = "5";
                 }
        
        
        /* 52 */     String ROOM_PRICE = request.getParameter("ROOM_PRICE");
        /* 53 */     String ROOM_NAME = request.getParameter("ROOM_NAME");
        
        
        /* 56 */     condition.put("ROOM_PRICE", ROOM_PRICE);
        /* 57 */     condition.put("ROOM_NAME", ROOM_NAME);
        
        /* 59 */     PageBean<RtVO> rtVOPageBean = this.rtService.findRtByPage(currentPage, rows, condition);
        
        
        
        
        /* 64 */     System.out.println("開始轉交");
        /* 65 */     response.setContentType("application/json;charset=utf-8");
        /* 66 */     mapper.writeValue(response.getOutputStream(), rtVOPageBean);
           }
     }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/controller/SelectRoomTypeServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */