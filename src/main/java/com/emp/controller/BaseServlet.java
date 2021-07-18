package com.emp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        throws ServletException, IOException

        //1.獲取請求路徑
        String uri = req.getRequestURI();
//        System.out.println("路徑"+uri);

        //2.獲取方法名稱

        //從最後一個'/'後的第一個字開始算到結尾
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

//                System.out.println("方法"+methodName);
//        System.out.println(this); //this代表用service方法的物件,就是empServlet

        //3.利用反射獲取所有方法
        try {
            //參考反射筆記,獲取class檔並放入參數跟方法名稱

            //getDeclaredMethod 忽略存取修飾
//            Method method = this.getClass().getDeclaredMethod(methodNeme, HttpServletRequest.class, HttpServletResponse.class);


            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //暴力反射
//            method.setAccessible(true);
            //執行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
