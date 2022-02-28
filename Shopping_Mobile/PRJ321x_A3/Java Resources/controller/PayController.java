package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import model.Cart;
import model.Orders;

/**
 * Servlet implementation class PayController
 */
@WebServlet("/PayController")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/*
	 * 
	 * Nhận vào thông tin từ form mua hàng và lưu đơn hàng cùng các thông tin người dùng vào database
	 * 
	 * 
	 * */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			response.getWriter().println("No cart");
		}
			
		try {
			String userMail = (String) request.getParameter("username");
			String address = (String) request.getParameter("useraddress");
			String discount = (String) request.getParameter("discountcode");

			long s = System.currentTimeMillis();
			Date date = new Date(s);
			
			Orders order = new Orders(userMail, 2, discount, address, "", date);
			
			OrdersDAO ordersAction = new OrdersDAO();
			ordersAction.inserOrders(order, cart);
			
			session.removeAttribute("cart");
			response.sendRedirect("createdOrder.jsp");
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}	
	}

}
