package com.mpfunc.model;

import java.util.List;

public class MpFuncService {
    private MpFuncDAO mpFuncDAO;

    public MpFuncService() {
        mpFuncDAO = new MpFuncDAOImpl();
    }


    public MpFuncVO addMpFunc(Integer empId,Integer funcId) {
        MpFuncVO mpFuncVO =new MpFuncVO();

        mpFuncVO.setEmpId(empId);
        mpFuncVO.setFuncId(funcId);
        mpFuncDAO.insert(mpFuncVO);
        return mpFuncVO;

    }


    public MpFuncVO updateMpFunc(Integer empId,Integer funcId,Integer setFuncId) {

        MpFuncVO mpFuncVO =new MpFuncVO();
        mpFuncVO.setEmpId(empId);
        mpFuncVO.setFuncId(funcId);
        mpFuncDAO.update(mpFuncVO,setFuncId);
        mpFuncVO.setFuncId(setFuncId);
        return mpFuncVO;


    }



    public void deleteOneMpFunc(Integer empId , Integer funcId) {
        mpFuncDAO.delete(empId ,funcId);
    }


    public List<MpFuncVO> getAll() {
        return mpFuncDAO.getAll();
    }


    public List<MpFuncVO> getOneMpFunc(Integer empId) {
        return mpFuncDAO.findByPrimaryKey(empId);
    }



    public void deleteAllMpFunc(Integer empId ) {
        mpFuncDAO.deleteAllFunc(empId);
    }

}
