package com.mpfunc.model;

import java.util.List;

public interface MpFuncDAO {
    public void insert(MpFuncVO mpFuncVO);

    public void update(MpFuncVO mpFuncVO , Integer funcId);

    public void delete(Integer empId , Integer funcId);

    public void deleteAllFunc(Integer empId);

    public List<MpFuncVO> findByPrimaryKey(Integer empId);

    public List<MpFuncVO> getAll();
}
