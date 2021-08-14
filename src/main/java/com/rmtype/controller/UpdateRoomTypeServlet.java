 package com.rmtype.controller;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.rmtype.model.RtService;
 import com.rmtype.model.RtVO;
 import com.rmtypepic.model.RtpService;
 import com.rmtypepic.model.RtpVO;
 import com.utils.Base64VO;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Base64;
 import java.util.List;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;



 @WebServlet({"/rmtype/updateRoomTypeServlet"})
 public class UpdateRoomTypeServlet
           extends HttpServlet
         {
    /* 25 */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response); }
    
    
    
    
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            System.out.println("接收請求");
        
        /* 33 */     RtpService rtpService = new RtpService();
        /* 34 */     RtService rtService = new RtService();
        /* 35 */     ObjectMapper mapper = new ObjectMapper();
        
        /* 37 */     List data = new ArrayList();
        
        /* 39 */     String roomCategoryId = request.getParameter("roomCategoryId");
        
        /* 41 */     int roomId = Integer.parseInt(roomCategoryId);
        
        
        /* 44 */     RtVO oneRt = rtService.getOneRt(Integer.valueOf(roomId));
//          System.out.println(oneRt);
        /* 46 */     List<RtpVO> allByCategoryId = rtpService.getAllByCategoryId(Integer.valueOf(roomId));
        
        /* 48 */     List<Base64VO> base64VOS = new ArrayList<Base64VO>();
        
        /* 50 */     for (RtpVO rtpVO : allByCategoryId) {
            /* 51 */       StringBuilder sb = new StringBuilder("data:image/jpeg;base64,");
            /* 52 */       Base64VO base64VO = new Base64VO();
            /* 53 */       base64VO.setName(rtpVO.getRoomPhotoId().toString());
            /* 54 */       sb.append(Base64.getEncoder().encodeToString(rtpVO.getRoomPhoto()));
            /* 55 */       base64VO.setBase64(sb.toString());
            /* 56 */       base64VOS.add(base64VO);
                 }
        
        
        /* 60 */     data.add(base64VOS);
        
        /* 62 */     data.add(oneRt);
        
        /* 64 */     response.setContentType("application/json;charset=utf-8");
        /* 65 */     mapper.writeValue(response.getOutputStream(), data);
           }
     }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/controller/UpdateRoomTypeServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */