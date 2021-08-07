import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter2 implements Filter{

	private FilterConfig config;
	public void init (FilterConfig config) {
		this.config = config;
	}
	public void destory() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter暴動"); //測試filter有無執行
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(); //取得session
		Object account = session.getAttribute("memVO"); //判斷是否登入過
		if(account == null) {
			//進入getRoomOrder的頁面
			if((req.getContextPath().toString()+"/rmorder/RmorderServlet").equals(req.getRequestURI())) {
				session.setAttribute("location", req.getRequestURI()+"?action=getRoomOrder");
				System.out.println("location:"+req.getRequestURI()+"?action=getRoomOrder");

				session.setAttribute("roomCategoryId", req.getParameter("roomCategoryId"));
				session.setAttribute("checkInDate", req.getParameter("checkInDate"));
				session.setAttribute("checkOutDate", req.getParameter("checkOutDate"));
				session.setAttribute("memNumber", req.getParameter("memNumber"));
				session.setAttribute("roomNumber", req.getParameter("roomNumber"));
				session.setAttribute("getMemberData", req.getParameter("getMemberData"));
			}else {
				session.setAttribute("location", req.getRequestURI()); //其他路徑進去的點
				System.out.println("location:"+req.getRequestURI());
			}

			res.sendRedirect(req.getContextPath() + "/front-end/mem/index.jsp");
			System.out.println("location:"+req.getRequestURI()+"/front-end/mem/index.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}
