package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ByNow
 */
@WebServlet("/ByNow")
public class ByNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ByNow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 
		 * Nhận dữ liệu id và thêm sản phẩm vào giỏ hàng tương ứng khi user chọn mua ngay
		 * 
		 * */
		HttpSession session = request.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		PrintWriter out = response.getWriter();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ListProductDAO productAction = new ListProductDAO();
			Product product = productAction.getProduct(id);
			Product productToAdd = new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSrc(), product.getType(), product.getBrand(), 1);
			cart.add(productToAdd);
		} catch (Exception e) {
			out.println(e.toString());
		}
		response.sendRedirect("cart?action=showcart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
