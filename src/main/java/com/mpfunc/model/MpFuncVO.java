package com.mpfunc.model;

public class MpFuncVO {

    private Integer empId;
    private Integer funcId;

    public MpFuncVO() {
    }

    public MpFuncVO(Integer empId, Integer funcId) {
        this.empId = empId;
        this.funcId = funcId;
    }

    @Override
    public String toString() {
        return "MpFuncVO{" +
                "empId=" + empId +
                ", funcId=" + funcId +
                '}';
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }
}
