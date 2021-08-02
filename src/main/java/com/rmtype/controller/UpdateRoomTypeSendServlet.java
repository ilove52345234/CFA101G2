 package com.rmtype.controller;

 import com.fasterxml.jackson.core.type.TypeReference;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.rmtype.model.RtService;
 import com.rmtype.model.RtVO;
 import com.rmtypepic.model.RtpService;
 import com.rmtypepic.model.RtpVO;
 import com.utils.Base64VO;
 import com.utils.ResultInfo;
 import java.io.IOException;
 import java.util.Base64;
 import java.util.List;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 @WebServlet({"/rmtype/updateRoomTypeSendServlet"})
 public class UpdateRoomTypeSendServlet
           extends HttpServlet {
    /* 23 */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doPost(request, response); }
    
    
    
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 28 */     System.out.println("接收到了");
        
        
        /* 31 */     RtpService rtpService = new RtpService();
        /* 32 */     RtService rtService = new RtService();
        /* 33 */     ResultInfo info = new ResultInfo();
        /* 34 */     ObjectMapper mapper = new ObjectMapper();
        
        
             try {
            /* 38 */       String json = request.getParameter("submitArr");
            /* 39 */       String date = request.getParameter("date");
            /* 40 */       String deleteArr = request.getParameter("deleteArr");
            

                            //需實作
            /* 43 */       TypeReference<List<Base64VO>> typeReference = new TypeReference<List<Base64VO>>() {};
            /* 46 */       TypeReference<List<RtpVO>> RtpVOtype = new TypeReference<List<RtpVO>>() {};
            /* 50 */       RtVO rtVO = (RtVO)mapper.readValue(date, RtVO.class);
            /* 51 */       rtService.updateNormal(rtVO);
            
            
            /* 54 */       List<Base64VO> base64list = (List)mapper.readValue(json, typeReference);
            
            /* 56 */       List<RtpVO> deletelist = (List)mapper.readValue(deleteArr, RtpVOtype);
            
            
            /* 59 */       for (RtpVO rtpVO : deletelist) {
                /* 60 */         rtpService.deleteRtp(rtpVO.getRoomPhotoId());
                       }
            
            
            /* 64 */       Integer roomCategoryId = rtVO.getRoomCategoryId();
            
            /* 66 */       Base64.Decoder decoder = Base64.getDecoder();
            
            /* 68 */       for (Base64VO base64VO : base64list) {
                
                /* 70 */         String base64 = base64VO.getBase64();
                
                /* 72 */         base64 = base64.split(",")[1];
                
                /* 74 */         byte[] decode = decoder.decode(base64);
                
                /* 76 */         int len = decode.length;
                
                /* 78 */         rtpService.addRtp(roomCategoryId, decode);
                       }
            
            /* 81 */       System.out.println("執行完畢");
            /* 82 */       info.flag = true;
            /* 83 */     } catch (Exception e) {
            /* 84 */       e.printStackTrace();
            /* 85 */       String message = e.getMessage();
            /* 86 */       info.flag = false;
            /* 87 */       info.errorMsg = message;
                 }
        
        /* 90 */     System.out.println(info);
        
        /* 92 */     response.setContentType("application/json;charset=utf-8");
        /* 93 */     mapper.writeValue(response.getOutputStream(), info);
           }
     }


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/controller/UpdateRoomTypeSendServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */