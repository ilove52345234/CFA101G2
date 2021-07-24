package com.rschedule.model;

import com.rmorderlist.model.RmolVO;

import java.util.List;


public interface RsDAO_interface {
	public void insert(RsVO rsVO);
    public void update(RsVO rsVO);
    public void delete(Integer rsVO);
    public RsVO findByPrimaryKey(Integer roomScheduleId);
    public List<RsVO> getAll();
    public List<RsVO> getInterval(RmolVO rmolVO);

}
