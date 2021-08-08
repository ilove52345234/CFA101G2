package com.mem.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MemService {
	
	private MemJDBCDAO_interface jdbcdao;
	
	public MemService() {
		jdbcdao = new MemJDBCDAO();
	}


	public MemVO updateMem(String memAccount) {

		MemVO memVO = new MemVO();
		memVO.setMemAccount(memAccount);
		jdbcdao.updateStatus(memVO);
		return memVO;
	}


	//註冊會員
	public MemVO addMem(String memAccount, String memName, String memPassword, String memAddress, 
			String memPhone, String memUid, String memEmail, String memSex, Date memDob, Integer memStatus, Timestamp memUpdate) {
		MemVO memVO = new MemVO();
		memVO.setMemAccount(memAccount);
		memVO.setMemName(memName);
		memVO.setMemPassword(memPassword);
		memVO.setMemAddress(memAddress);
		memVO.setMemPhone(memPhone);
		memVO.setMemUid(memUid);
		memVO.setMemEmail(memEmail);
		memVO.setMemSex(memSex);
		memVO.setMemDob(memDob);
		memVO.setMemStatus(memStatus);
		memVO.setMemUpdate(memUpdate);
		jdbcdao.insert(memVO);
		
		return memVO;
	}
	
	//更新
	public MemVO updateMem(Integer memId, String memAccount, String memName, String memPassword, String memAddress,
			String memPhone, String memUid, String memEmail, String memSex, Date memDob, Integer memStatus, Timestamp memUpdate) {
		MemVO memVO = new MemVO();
		memVO.setMemId(memId);
		memVO.setMemAccount(memAccount);
		memVO.setMemName(memName);
		memVO.setMemPassword(memPassword);
		memVO.setMemAddress(memAddress);
		memVO.setMemPhone(memPhone);
		memVO.setMemUid(memUid);
		memVO.setMemEmail(memEmail);
		memVO.setMemSex(memSex);
		memVO.setMemDob(memDob);
		memVO.setMemStatus(memStatus);
		memVO.setMemUpdate(memUpdate);
		jdbcdao.update(memVO);

		return memVO;
	}
	
	//查單一
	public MemVO getOneMem(Integer memId) {
		return jdbcdao.findByPrimaryKey(memId);
	}
	
	//查全部
	public List<MemVO> getAll() {
		return jdbcdao.getAll();
	}
	
	//登入
	public MemVO getOneMemByMemAcc(String memAccount) {
		return jdbcdao.findByPrimaryKeyByMemAcc(memAccount);
	}
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "";
	     final String myGmail_password = "";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
	
}
