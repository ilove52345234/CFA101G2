package com.func.model;

import com.mpfunc.model.MpFuncService;
import com.mpfunc.model.MpFuncVO;

import java.util.ArrayList;
import java.util.List;

public class FuncService {
    private FuncDAO funcDAO;

    public FuncService() {
        funcDAO = new FuncDAOImpl();
    }


    public FuncVO addFunc(String funcName, String funcDesc) {
        FuncVO funcVO = new FuncVO();
        funcVO.setFuncName(funcName);
        funcVO.setFuncDesc(funcDesc);

        funcDAO.insert(funcVO);


        return funcVO;

    }

    public FuncVO updateFunc(Integer funcId, String funcName, String funcDesc) {


        FuncVO funcVO = new FuncVO();
        funcVO.setFuncId(funcId);
        funcVO.setFuncName(funcName);
        funcVO.setFuncDesc(funcDesc);

        funcDAO.update(funcVO);


        return funcVO;

    }


    public void deleteFunc(Integer funcId) {
        funcDAO.delete(funcId);
    }


    public List<FuncVO> getAll() {
        return funcDAO.getAll();
    }


    public FuncVO getOneFunc(Integer funcId) {
        return funcDAO.findByPrimaryKey(funcId);
    }



}
