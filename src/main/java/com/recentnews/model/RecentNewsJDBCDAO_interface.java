package com.recentnews.model;

import java.util.*;

public interface RecentNewsJDBCDAO_interface {
	public void insert(RecentNewsVO recentNewsVO);
	public void update(RecentNewsVO recentNewsVO);
	public List<RecentNewsVO> getAll();
	public RecentNewsVO findByPrimaryKey(Integer newsId);
	public void delete(Integer newsId);

}
