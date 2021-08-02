package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登入驗證的過濾器
 */

public class EmpLoginFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //0.強制轉型,因為方法在http裡面
//        ("我被執行了");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        //1.獲取資源請求路徑
        String requestURI = req.getRequestURI();
//        (requestURI);


//        (requestURI);
        //2.判斷是否包含登錄相關資源路徑
        //contains :判斷字串中是否包含相關文字

        if (requestURI.contains("/login.html") ||
                requestURI.contains("/empServlet/") ||
                requestURI.contains(".css") ||
                requestURI.contains("/fonts/") ||
                requestURI.contains(".png") ||
                requestURI.contains(".jpg") ||
                requestURI.contains("/js/")||
                requestURI.contains(".jpeg")) {
            //如果包含這些,代表用戶想登入,
            // 必須放行,否則會陷入我要登入就必須先登入的情況

//            System.out.println("包含,放行");
//            request.setCharacterEncoding("UTF-8");
//            response.setCharacterEncoding("charset=UTF-8");

            chain.doFilter(request, response);
        } else {

//            request.setCharacterEncoding("UTF-8");
//            response.setContentType("text/html;charset=UTF-8");
            //不包含,就判斷是否登入過
            //3.從session獲取用戶資訊
            Object emp = req.getSession().getAttribute("Emp");
//            (emp);

            if (emp != null) {
                //代表登入過了,放行
//                System.out.println("登入過了,放行");
                chain.doFilter(request, response);
            } else {
//                System.out.println("不包含,登入");
                //空的,沒登入過,叫他去登入
//                request.setAttribute("requestURI", requestURI);
                req.getRequestDispatcher("/login.html").forward(request, response);

            }
        }
        //        chain.doFilter(request, response);
    }
}
