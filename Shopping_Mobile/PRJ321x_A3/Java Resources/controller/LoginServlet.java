package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			response.sendRedirect("list");
		} else if (action.equals("login")) {
			response.sendRedirect("login.jsp");
		} else if (action.equals("logout")) {
			response.sendRedirect("list");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//xoá bỏ hết tất cả các session đã cài đặt trước đó
		request.getSession().invalidate();
		//bắt đầu lại từ đầu khi đăng nhập
		
		try {
			//nhận dữ liệu từ form login
			String email = request.getParameter("username");
			String password = request.getParameter("userpass");
			
			//đối tượng bean user để validate dữ liệu.
			User user = new User(email, password);
			
			//kiểm tra xem mật khẩu và email gửi từ form có hợp lệ không
			if (!user.isValid()) {
				//nếu không hợp lệ set lỗi vào request và đưa trở về trang login
				request.setAttribute("error", user.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				//lấy thông tin user và password từ web.xml
				String userbase = getServletContext().getInitParameter("username");
				String passwordbase = getServletContext().getInitParameter("password");
				
				//lấy thông tin người đăng nhập có lựa chọn lưu mật khẩu không
				String rememberlogin = request.getParameter("remuser");
				
				//kiểm tra thông tin từ form và web.xml
				if (user != null && user.getPassword().equals(passwordbase) && user.getEmail().equalsIgnoreCase(userbase)) {
					//nếu user có lựa chọn lưu mật khẩu thì tạo cookie và lưu lại dữ liệu
					if(rememberlogin != null) {
						Cookie unamecook = new Cookie("email", email);
						Cookie upasscook = new Cookie("passw", password);
						unamecook.setMaxAge(300);
						upasscook.setMaxAge(300);
						response.addCookie(unamecook);
						response.addCookie(upasscook);
					}
					
					HttpSession session = request.getSession(true);
					//đặt giá trị tên cho request
					session.setAttribute("email", email);
					//đưa đến trang index dành cho admin
					response.sendRedirect("admin/index.jsp");
//					request.getRequestDispatcher("admin/index.jsp").forward(request, response);
				} else {
					//nếu thông tin nhận vào không trùng khớp thì đặt lỗi cho request và đưa về trang đăng nhập
					request.setAttribute("error", "Email hoặc mật khẩu nhập vào không chính xác");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		} catch (NullPointerException e) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}
}
