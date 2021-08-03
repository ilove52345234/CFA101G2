package com.rmtypepic.controller;

import com.utils.JDBCUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/rmtypepic/RtpServlet")
public class RtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RtpServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		res.getWriter().append("Served at: ").append(req.getContextPath());
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doGet(req, res);   使用/rmtypepic/RtpServlet?roomCategoryId=${rtVO.roomCategoryId}傳遞方式 所以不能用

		req.setCharacterEncoding("UTF-8");
		res.setContentType("img/gif"); // p129
		ServletOutputStream out = res.getOutputStream();
		String action = req.getParameter("action");
		Connection con = null; // 設一個區域變數來做連接

		if ("show_pics".equals(action)) {
			try {

				JDBCUtils jdbcUtils = new JDBCUtils();;
				con = jdbcUtils.getConnection();
				Statement stmt = con.createStatement(); // 連接stmt內的ROOM_PHOTO_ID
				String roomPhotoId = req.getParameter("roomCategoryId");
				Integer myPhoto = Integer.parseInt(roomPhotoId); // 從string轉型為int
				ResultSet rs = stmt.executeQuery(
						"SELECT ROOM_PHOTO FROM ROOM_TYPE_PIC WHERE ROOM_CATEGORY_ID = " + myPhoto + " limit 4");
				System.out.println("show picture room_photo_id:" + myPhoto);

//			Statement stmt = con.createStatement();
//			String roomPhotoId = req.getParameter("roomCategoryId");
//			Integer myPhoto= Integer.parseInt(roomPhotoId);
//			ResultSet rs = stmt.executeQuery("SELECT ROOM_PHOTO FROM ROOM_TYPE_PIC WHERE ROOM_CATEGORY_ID = "+ myPhoto +"");
				// ("SELECT ROOM_PHOTO FROM ROOM_TYPE_PIC WHERE ROOM_CATEGORY_ID = '"+ String
				// +"'") 若內含字串則加上'';
				// 若資料庫photo只有一張圖片就可以這樣做 但一對多的狀態下可能會有多張圖片 所以要用limit限制一張圖片
//            System.out.println("show picture room_photo_id:"+myPhoto);
//            錯誤方式詳見listAllRmtype 168行

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ROOM_PHOTO"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					InputStream in = getServletContext().getResourceAsStream("/front-end/images/nopic.png");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				}
				rs.close();
				stmt.close();

			} catch (Exception e) {

				InputStream in = getServletContext().getResourceAsStream("/front-end/images/nopic.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if ("show_many_pics".equals(action)) {
			try {

				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
				con = ds.getConnection();

				Statement stmt = con.createStatement(); // 連接stmt內的ROOM_PHOTO_ID
				String roomPhotoId = req.getParameter("roomPhotoId");
				Integer myPhoto = Integer.parseInt(roomPhotoId); // 從string轉型為int
				ResultSet rs = stmt.executeQuery(
						"SELECT ROOM_PHOTO FROM ROOM_TYPE_PIC WHERE ROOM_PHOTO_ID = " + myPhoto);
				System.out.println("show picture room_photo_id:" + myPhoto);
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ROOM_PHOTO"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					InputStream in = getServletContext().getResourceAsStream("/front-end/images/nopic.png");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				}
				rs.close();
				stmt.close();

			} catch (Exception e) {

				InputStream in = getServletContext().getResourceAsStream("/front-end/images/nopic.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}


		}
	}

}
