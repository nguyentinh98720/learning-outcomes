package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import dao.ListProductDAO;

/**
 * Servlet implementation class InformationProductController
 */
@WebServlet("/InformationProductController")
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationProductController() {
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
	 * Nhận giá trị id và lấy dữ liệu sản phẩm từ database với id tương ứng, đưa user vào trang xem chi tiết sản phẩm
	 * 
	 * */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ListProductDAO productAction = new ListProductDAO();
		Product product = new Product();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			product = productAction.getProduct(id);
			request.setAttribute("product", product);
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
