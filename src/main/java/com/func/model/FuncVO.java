package com.func.model;

public class FuncVO implements java.io.Serializable {

    private Integer funcId;
    private String funcName;
    private String funcDesc;

    public FuncVO() {
    }

    public FuncVO(Integer funcId, String funcName, String funcDesc) {
        this.funcId = funcId;
        this.funcName = funcName;
        this.funcDesc = funcDesc;
    }

    @Override
    public String toString() {
        return "FuncVO{" +
                "funcId=" + funcId +
                ", funcName='" + funcName + '\'' +
                ", funcDesc='" + funcDesc + '\'' +
                '}';
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }
}
