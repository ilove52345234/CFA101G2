package com.rmtypepic.model;

import java.sql.Connection;
import java.util.List;

public interface RtpDAO_interface {
	void insert(RtpVO paramRtpVO);

	void update(RtpVO paramRtpVO);

	void delete(Integer paramInteger);

	RtpVO findByPrimaryKey(Integer paramInteger);

	List<RtpVO> getAll();

	void insert2(RtpVO paramRtpVO, Connection paramConnection);

	List<RtpVO> getAllByRcId(Integer paramInteger);

	RtpVO findByRoomCategoryId(Integer paramInteger);
	public List<RtpVO> findByRoomCategoryId2(Integer roomCategoryId);

}


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtypepic/model/RtpDAO_interface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */