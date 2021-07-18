package com.roomorder.model;


import java.util.List;

public interface RoomOrderDAO {
    public void insert(RoomOrderVO roomOrderVO);
    public void update(RoomOrderVO roomOrderVO);
    public void delete(Integer roomOrderId);
    public RoomOrderVO findByPrimaryKey(Integer roomOrderId);
    public List<RoomOrderVO> getAll();



}
