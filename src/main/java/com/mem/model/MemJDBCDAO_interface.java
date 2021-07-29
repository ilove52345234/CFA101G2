package com.mem.model;

import java.util.List;


public interface MemJDBCDAO_interface {
	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public List<MemVO> getAll();
    public MemVO findByPrimaryKey(Integer memVO);
    public MemVO findByPrimaryKeyByMemAcc(String memAccount);
}
