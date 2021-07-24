package com.extrabill.model;

import java.util.List;

public interface ExtraBillDAO {
    public void insert(ExtraBillVO extraBillVO);
    public void update(ExtraBillVO extraBillVO);
    public ExtraBillVO getOneByRoomId(Integer roomId);
    public void delete(Integer extraBillId);
    public void deleteByRoomId(Integer roomId);
//    public ExtraBillVO findByPrimaryKey(Integer extraBillId);
//    public List<ExtraBillVO> getAll();
}
