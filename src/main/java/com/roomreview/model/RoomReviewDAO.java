package com.roomreview.model;

import java.util.List;

public interface RoomReviewDAO {

    public void insert(RoomReviewVO roomReviewVO);
    public void update(RoomReviewVO roomReviewVO);
    public void delete(Integer roomReviewId);
    public RoomReviewVO findByPrimaryKey(Integer roomReviewId);
    public List<RoomReviewVO> getAll();
    
}
