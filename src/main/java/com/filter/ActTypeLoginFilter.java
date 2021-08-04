package com.filter;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.func.model.FuncVO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

/**
 * 活動類別管理
 */

public class ActTypeLoginFilter implements Filter {
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
        System.out.println("ActTypeLoginFilter被執行了");
        HttpServletRequest req = (HttpServletRequest) request;
        boolean flag = false;
        //1.獲取資源請求路徑
        String requestURI = req.getRequestURI();

//        System.out.println(requestURI);
        //2.判斷是否包含登錄相關資源路徑

        //contains :判斷字串中是否包含相關文字
        if (requestURI.contains("/login.html") ||
                requestURI.contains("/css/") ||
                requestURI.contains("/images/") ||
                requestURI.contains("/js/") ||
                requestURI.contains("/index.html") ||
                requestURI.contains("/AuthorityError.html") ||
                requestURI.contains("/header.jsp")) {
            //如果包含這些,代表用戶想登入,
            // 必須放行,否則會陷入我要登入就必須先登入的情況

            System.out.println(requestURI+"包含,放行");

            chain.doFilter(request, response);
        } else {
            System.out.println("不包含,開始驗證");
//            request.setCharacterEncoding("UTF-8");
//            response.setContentType("text/html;charset=UTF-8");
            //不包含,就判斷是否登入過

            //3.從session獲取用戶資訊
            EmpVO emp = (EmpVO)req.getSession().getAttribute("Emp");


            Set<FuncVO> funcByEmpId = new EmpService().getFuncByEmpId(emp.getEmpId());

            for (FuncVO funcVO : funcByEmpId) {
               if(funcVO.getFuncId() == 26){
                   flag = true;
               }
            }

            if (flag) {
                //代表登入過了,放行
                System.out.println("權限足夠,放行");
                chain.doFilter(request, response);
            } else {
                System.out.println("權限不足");
                //空的,沒登入過,叫他去登入
                req.getRequestDispatcher("/back-end/AuthorityError.html").forward(request, response);
            }
        }
//                chain.doFilter(request, response);
    }
}
