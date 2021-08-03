package com.act.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.*;

/**
 * Servlet implementation class StartActServlet
 */
@WebServlet("/act/StartActServlet")
public class StartActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("startAct".equals(action)){
		
		
		try {
			// Retrieve form parameters.
			Integer actId = new Integer(req.getParameter("actId"));

			ActService actSvc = new ActService();
			
			
			actSvc.startAct(actId);
			
			ActVO actVO = actSvc.getOneById(actId);
			
			
			
			req.setAttribute("actVO", actVO); // 資料庫取出的ActVO物件,存入req
			
			
			// 取出的actVO送給listOnectAct.jsp
			RequestDispatcher successView = req
					.getRequestDispatcher("/back-end/act/listOneAct.jsp");
			successView.forward(req, res);
			return;

			// Handle any unusual exceptions
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of if
		
		
	
		if("startActFake".equals(action)){
			
			try {
				
			
			Integer actId = new Integer(req.getParameter("actId"));

			ActDAO dao = new ActDAO();
			
			
			ActVO actVO1 = dao.findByPrimaryKey(actId);
			actVO1.setActStatus("成團");
			dao.updateStatus(actVO1);
			ActVO actVO= dao.findByPrimaryKey(actId);
			System.out.println("目前活動編號： "+actVO.getActCategoryId()+" 的活動狀態為："+actVO.getActStatus());
			
			req.setAttribute("actVO", actVO);
			
			// 取出的actVO送給listOnectAct.jsp
			RequestDispatcher successView = req
					.getRequestDispatcher("/back-end/act/listOneAct.jsp");
			successView.forward(req, res);
			return;

			// Handle any unusual exceptions
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }//end of if
   }	
}
	



