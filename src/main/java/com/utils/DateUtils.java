package com.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //格式化自行修改
    SimpleDateFormat dateformatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定義返回的日期格式


    //java.sql.Date.valueOf(String s)的s格式必須為YYYY-MM-DD格式

    //字串轉成sql.Date,格式化自行修改
    public java.sql.Date getDate(String dateString) {

        //自動更新時間設為false
        dateformatAll.setLenient(false);

        //先轉成util
        Date timeDate = null;//util類型
        try {
            timeDate = dateformatAll.parse(dateString);
            System.out.println(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //轉成sql
        java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql類型

        //返回
        return dateTime;

    }


    //字串轉成Timestamp
    public Timestamp getTimestamp(String dateString) {

        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date du = null;
        try {
            du = sp.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp st = new Timestamp(du.getTime());
        return st;
    }


    //Timestamp格式化回傳字串
    public String getFormatTimestamp(Timestamp ts) {
        if (ts == null) {//如果時間為空返回當前時間
            return dateformatAll.format(getCurrentTimestamp());
        }

        return dateformatAll.format(ts);//格式化傳過來的時間就可以去掉毫秒數
    }

    //獲取當前時間字串類型,其他類型請直接用System.currentTimeMillis()
    public String getCurrentTimestamp() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }

    public java.sql.Date getSqlCurrentTime() {
        return new java.sql.Date(new Date().getTime());
    }

    public Date getUtilDate(String dateString) {
        Date timeDate = null;//util類型
        try {
            timeDate = dateformatAll.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeDate;
    }

    public  Date addAndSubtractDaysByGetTime(Date dateTime/*待處理的日期*/, int n/*加減天數*/) {
//日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dd.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
//System.out.println(dd.format(new Date(dateTime.getTime()   n * 24 * 60 * 60 * 1000L)));
//注意這裡一定要轉換成Long型別，要不n超過25時會出現範圍溢位，從而得不到想要的日期值
        return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L);
    }


}
