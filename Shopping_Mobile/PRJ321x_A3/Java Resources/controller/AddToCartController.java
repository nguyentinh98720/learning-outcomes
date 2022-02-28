package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
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
	 * xử lý dữ liệu id nhận vào từ request để thêm hoặc xóa sản phẩm khỏi giỏ hàng với id tương ứng
	 * 
	 * */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		String action = request.getParameter("action");
		if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", new Cart());
		}
		Cart cart = (Cart) session.getAttribute("cart");
		if(action != null && action.equals("showcart")) {
			request.setAttribute("cart", "made by vati corp");
			request.getRequestDispatcher("list").forward(request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				if(action != null && action.equals("add")) {				
					ListProductDAO actionProduct = new ListProductDAO();
					Product product = actionProduct.getProduct(id);
					Product productToAdd = new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSrc(), product.getType(), product.getBrand(), 1);
					cart.add(productToAdd);
				} else if(action != null && action.equals("delete")) {
					cart.remove(id);
				}
			} catch (Exception e) {
				response.getWriter().println(e.getMessage());
			}
			response.sendRedirect(request.getHeader("referer"));
		}
	}

}
