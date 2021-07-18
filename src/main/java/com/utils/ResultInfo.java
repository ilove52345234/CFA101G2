package com.utils;

import java.io.Serializable;

/**
 * @author Summerday
 * 用於封裝後端返回前端數據物件
 */
public class ResultInfo implements Serializable {
    /**
     * 後端返回結果正常為true，發生異常返回false
     */
    public boolean flag;

    /**
     * 後端返回結果數據對象
     */
    public Object data;

    /**
     * 發生異常的錯誤消息
     */
    public String errorMsg;


    /**
     * 無參建構子
     */
    public ResultInfo() {
    }


    public ResultInfo(boolean flag) {
        this.flag = flag;
    }


    /**o
     * 有參建構方法
     * @param flag
     * @param errorMsg
     */
    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }


    /**
     * 有參建構子
     * @param flag
     * @param data
     * @param errorMsg
     */
    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }




    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}