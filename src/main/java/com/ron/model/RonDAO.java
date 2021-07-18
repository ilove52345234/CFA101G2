package com.ron.model;

import java.util.List;

public interface RonDAO {
    public void insert(RonVO ronVO);
    public void update(RonVO ronVO);
    public void delete(Integer roomOrderNotifyId);
    public RonVO findByPrimaryKey(Integer roomOrderNotifyId);
    public List<RonVO> getAll();
}
