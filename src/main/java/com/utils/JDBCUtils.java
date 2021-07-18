package com.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Druid的連接持工具類別
 * 先看完DruidDemo
 */

public class JDBCUtils {

    //1.定義成員變數 DataSource
    private static DataSource ds = null;

//    static {
//
//
//        try {
//            //1.載入配置文件
//            Properties pro = new Properties();
//            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
//
//            //2.獲取DataSource
//            ds = DruidDataSourceFactory.createDataSource(pro);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




    static {
        //僅僅在類別被載入時系統建立一個連線池，自動識別配置檔案
        ds = new ComboPooledDataSource();
    }



    //1. 獲取連線的方法:通過資料庫連線池獲取連線
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    //2.釋放資源的方法

    public static void close(Statement stmt, Connection conn) {
        /*
        if (stmt != null) {
            try {
                stmt.close(); //釋放
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close(); //歸還
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        */

        close(null, stmt, conn);

    }


    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close(); //釋放
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close(); //釋放
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }

        if (conn != null) {
            try {
                conn.close(); //歸還
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }

    }


    //3. 獲取連線池的方法
    public static DataSource getDataSource() {
        return ds;
    }


}
