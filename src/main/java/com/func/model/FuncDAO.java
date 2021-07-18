package com.func.model;

import com.chatroom.model.ChatroomVO;

import java.util.List;

public interface FuncDAO {

    public void insert(FuncVO funcVO);

    public void update(FuncVO funcVO);

    public void delete(Integer funcId);

    public FuncVO findByPrimaryKey(Integer funcId);

    public List<FuncVO> getAll();
}
