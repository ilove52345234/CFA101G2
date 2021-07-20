package com.room.controller;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.rmtype.model.RtService;
import com.rmtype.model.RtVO;
import com.room.model.RmService;
import com.room.model.RmVO;
import com.utils.PageBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/room/roomServlet")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//設置編碼
		request.setCharacterEncoding("utf-8");


		Map<String, String> condition = new HashMap<String, String>();

		List<String> errorMsgs = new LinkedList<String>();
//        List<String> Msgs = new LinkedList<String>();

		request.setAttribute("errorMsgs", errorMsgs);
//        request.setAttribute("Msgs", Msgs);

		String requestURL = request.getParameter("requestURL");

//        ("路徑為:"+requestURL);



		try {



			//1.獲取參數
			String currentPage = request.getParameter("currentPage"); //當前頁碼
			String rows = request.getParameter("rows"); //每頁顯示的條數

			// 如果沒有直接讓他到第一頁
			if(currentPage == null || "".equals(currentPage)){
				currentPage = "1";
			}

			if(rows == null || "".equals(rows)){
				rows = "5";
			}

			//1.1獲取條件查詢參數

			String ROOM_ID = request.getParameter("ROOM_ID");
			String ROOM_INFORMATION = request.getParameter("ROOM_INFORMATION");
			String ROOM_CHECK_STATUS = request.getParameter("ROOM_CHECK_STATUS");


			condition.put("ROOM_ID",ROOM_ID);
			condition.put("ROOM_INFORMATION",ROOM_INFORMATION);
			condition.put("ROOM_CHECK_STATUS",ROOM_CHECK_STATUS);


//        (condition.toString());
			//2.調用service來查詢
			RmService rmService = new RmService();
			PageBean<RmVO> pb = rmService.findrmByPage(currentPage, rows, condition);
			RtService rtService = new RtService();

			List<RtVO> rtVOS = rtService.getAll();

			List<RmVO> list = pb.getList();

			for (RmVO rmVO : list) {
				System.out.println(rmVO);
			}


			//3.將PageBean存入request
			request.setAttribute("pb",pb);
			request.setAttribute("rtVOS",rtVOS);
			request.setAttribute("condition",condition);//將查詢條件也一起放入

//        ("執行順利");
			//4.轉交
			request.getRequestDispatcher("/back-end/room/roomlist.jsp").forward(request, response);

		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request
					.getRequestDispatcher("/back-end/room/roomlist.jsp");
			failureView.forward(request, response);
		}



	}

}
