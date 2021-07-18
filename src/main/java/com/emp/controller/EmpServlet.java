package com.emp.controller;

import com.emp.model.EmpVO;
import com.emp.model.EmpService;
import com.utils.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@WebServlet("/emp/empServlet/*")
public class EmpServlet extends BaseServlet {
    //Service物件
    private EmpService service = new EmpService();

    //Vo物件
    private EmpVO empVO = new EmpVO();

    //錯誤物件
    private ResultInfo info = new ResultInfo();

    //Jackson物件
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 1.產生隨機驗證碼的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkCodeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服務器通知瀏覽器不要緩存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

//在內存中創建一個長80，寬30的圖片，默認黑色背景
//參數一：長
//參數二：寬
//參數三：顏色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//獲取畫筆
        Graphics g = image.getGraphics();
//設置畫筆顏色為灰色
        g.setColor(Color.GRAY);
//填充圖片
        g.fillRect(0, 0, width, height);

//產生4個隨機驗證碼，12Ey
        String checkCode = getCheckCode();
//將驗證碼放入HttpSession中
        request.getSession().setAttribute("checkCode", checkCode);

//設置畫筆顏色為黃色
        g.setColor(Color.YELLOW);
//設置字體的小大
        g.setFont(new Font("黑體", Font.BOLD, 24));
//向圖片上寫入驗證碼
        g.drawString(checkCode, 15, 25);

//將內存中的圖片輸出到瀏覽器
//參數一：圖片對象
//參數二：圖片的格式，如PNG,JPG,GIF
//參數三：圖片輸出到哪裡去
        ImageIO.write(image, "PNG", response.getOutputStream());
    }


    /**
     * 1.1產生4位隨機字串給驗證碼用
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
//產生0到size-1的隨機值
            int index = r.nextInt(size);
//在base字符串中獲取下標為index的字符
            char c = base.charAt(index);
//將c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 2.登入的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void empLoginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        String requestURI = request.getRequestURI();


        //1.獲取帳號跟密碼
        String empAccount = request.getParameter("empAccount");
        String empPassword = request.getParameter("empPassword");


//        2.封裝Emp物件
        EmpVO empVO = new EmpVO();
        empVO.setEmpAccount(empAccount);
        empVO.setEmpPassword(empPassword);


//        Map<String, String[]> map = request.getParameterMap();
//        try {
//            BeanUtils.populate(empVO, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        //3.調用Service查詢
//        EmpService service = new EmpServiceImpl();

        EmpVO emp = service.login(empVO);

//        System.out.println(emp);


        //驗證物件
//        ResultInfo info = new ResultInfo();


        String check = request.getParameter("verifycode");

        HttpSession session = request.getSession();
        String checkCodeServer = (String) session.getAttribute("checkCode");
        //保證驗證碼只能使用一次
        session.removeAttribute("checkCode");
        //驗證碼不相等
        if (checkCodeServer == null || !checkCodeServer.equals(check)) {
            //對用戶輸入驗證碼進行判斷
            if ("".equals(check)) {
                info.setErrorMsg("驗證碼不能為空");
            } else {
                info.setErrorMsg("驗證碼錯誤");
            }
            info.setFlag(false);
        } else {
            //4.判斷帳號是不是null
            if (emp == null) {
                //帳號密碼錯誤
                info.setFlag(false);
                info.setErrorMsg("帳號或密碼錯誤!");
            }

            Integer i = 1;

            //5.判斷管理員狀態
            if (emp != null && !i.equals(emp.getEmpStatus())) {
                //管理員權限未開啟或停權中
                info.setFlag(false);
                info.setErrorMsg("帳號未啟用,請聯絡其他管理員");
            }

            //6.判斷登入成功
            if (emp != null && i.equals(emp.getEmpStatus())) {


                //如果使用者勾選了自動登入
                if ("true".equals(request.getParameter("autoLogin"))) {
                    //創立一個Cookie,
                    Cookie cookie = new Cookie("autoLogin", emp.getEmpAccount() + "#" + emp.getEmpPassword());

                    //設置存活時間
                    cookie.setMaxAge(60 * 60 * 24);

                    //傳送cookie到客戶端
                    response.addCookie(cookie);
                }


                //將Emp物件存放在session中
                session.setAttribute("Emp", emp);
                //登入成功
                info.setFlag(true);


            }

        }

        //Jackson
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(info);
        //設置回傳格式
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }


    /**
     * 3.取得登入者名字的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //從session中獲取
        Object emp = request.getSession().getAttribute("Emp");

        //Jackson
//        ObjectMapper mapper = new ObjectMapper();

        //設置回傳格式
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), emp);
//        ("這邊跑了"+emp);

    }


    /**
     * 4.登出的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exitServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Session自動銷毀的方法
        request.getSession().invalidate();

        //重設Cookie
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

//        (request.getContextPath());
        //跳轉回登入
        response.sendRedirect(request.getContextPath() + "/login.html");
    }


    /**
     * 5.自動登入的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void autoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取得所有cookie
        Cookie[] cookies = request.getCookies();

        //判斷所有cookie是否有登入的cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("autoLogin".equals(cookie.getName())) {

                    //取得值
                    String value = cookie.getValue();

//                    (value);

                    //用#切掉拿第一個跟第二個存成帳號密碼
                    String EmpAccount = value.split("#")[0];
                    String EmpPassword = value.split("#")[1];

                    //設置
                    empVO.setEmpPassword(EmpPassword);
                    empVO.setEmpAccount(EmpAccount);


                    //核對是否正確
                    EmpVO emp = service.login(empVO);

                    if (emp != null) {
//                        System.out.println("自動登入成功");
                        //登入成功標記
                        request.getSession().setAttribute("Emp", emp);
                        //登入成功
                        info.setFlag(true);
                    } else {
//                        System.out.println("自動登入失敗");
                        info.setErrorMsg("自動登入過期");
                        info.setFlag(false);

                    }

                    response.setContentType("application/json;charset=utf-8");
                    mapper.writeValue(response.getOutputStream(), info);
                }
            }

        }

    }




}

