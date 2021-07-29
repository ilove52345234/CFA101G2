package com.recentnews.model;

import java.sql.Timestamp;
import java.util.List;

public class RecentNewsService {
	
	private RecentNewsJDBCDAO_interface jdbcdao;
	
	public RecentNewsService() {
		jdbcdao = new RecentNewsJDBCDAO();
	}
	
	//新增
	public RecentNewsVO addNews(String newsContent, Timestamp newsTime, Integer newsStatus) {
		
		RecentNewsVO recentNewsVO = new RecentNewsVO();
		recentNewsVO.setNewsContent(newsContent);
		recentNewsVO.setNewsTime(newsTime);
		recentNewsVO.setNewsStatus(newsStatus);
		jdbcdao.insert(recentNewsVO);
		
		return recentNewsVO;
	}
	
	//更新
	public RecentNewsVO updateNews(Integer newsId, String newsContent, Integer newsStatus) {  

		RecentNewsVO recentNewsVO = new RecentNewsVO();
		recentNewsVO.setNewsId(newsId);
		recentNewsVO.setNewsContent(newsContent);
		recentNewsVO.setNewsStatus(newsStatus);
		jdbcdao.update(recentNewsVO);

		return recentNewsVO;
	}
	
	public RecentNewsVO getOneNews(Integer newsId) {
		return jdbcdao.findByPrimaryKey(newsId);
	}
	
	public List<RecentNewsVO> getAll() {
		return jdbcdao.getAll();
	}
	
	public void deleteNews(Integer newsId) {
		jdbcdao.delete(newsId);
	}
}
