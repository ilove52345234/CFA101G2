package com.actpart.model;

import java.util.List;

import com.acttype.model.ActTypeVO;

public interface ActPartDAOInterface {
	public void insert(ActPartVO actPartVO);
	public void update(ActPartVO actPartVO);
	public void delete (Integer actId, Integer memId);
	public ActPartVO findByPrimaryKey(Integer actId, Integer memId);
	public List<ActPartVO> getAll();
	public List<ActPartVO> getAllByActId(Integer actId);//用actId查找該活動所有的參加者資料

}
