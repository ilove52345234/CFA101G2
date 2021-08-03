package com.actphoto.controller;

import com.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet("/actphoto/GetFirstActPhotoServlet")
public class GetFirstActPhotoServlet extends HttpServlet {
       

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 		doPost(req, res);	}
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
			//輸出圖檔的Stream
		ServletOutputStream out = res.getOutputStream();
			//JDBC
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String url = "jdbc:mysql://127.0.0.1:3306/CFA101G2?serverTimezone=Asia/Taipei";
//		String userid = "David";
//		String passwd = "123456";

		JDBCUtils jdbcUtils = new JDBCUtils();


		Connection con = null;

		//System.out.println();

		//listAllActType.jsp 來的 request 
		try {
			con = jdbcUtils.getConnection();
			Statement stmt = con.createStatement();
			Integer actTypeId = Integer.valueOf(req.getParameter("actCategoryId"));

			System.out.println(actTypeId);

			String sql = "SELECT ACT_PHOTO_FILE FROM ACT_PHOTO where ACT_CATEGORY_ID = "+actTypeId+" order by ACT_PHOTO_ID limit 1";
			ResultSet rs = stmt.executeQuery(sql);


			if (rs.next()) {
				BufferedInputStream in = 
				  new BufferedInputStream(rs.getBinaryStream("ACT_PHOTO_FILE"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}

			  in.close();
			} else {
				InputStream in = new FileInputStream(getServletContext().getRealPath("/back-end/acttype/images/NoData/nopic.jpg"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}

				in.close();
				  //res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

            rs.close();
            stmt.close();
		} catch (Exception e) {
			InputStream in = new FileInputStream(getServletContext().getRealPath("/back-end/acttype/images/NoData/nopic2Catch.jpg"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}

			in.close();
			   e.printStackTrace();
		}
	}//end of if
	
}
