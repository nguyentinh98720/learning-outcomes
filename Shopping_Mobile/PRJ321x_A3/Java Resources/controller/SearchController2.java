package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import model.Product;

/**
 * Servlet implementation class SearchController2
 */
@WebServlet("/SearchController2")
public class SearchController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController2() {
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
	 * 
	 * Chức năng tìm kiếm dành cho người dùng
	 * Có thể tìm sản phẩm dựa vào mục brand product ở header hoặc nhập tên từ thanh tìm kiếm
	 * 
	 * 
	 * */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//khi request gửi thông tin tìm kiếm từ thanh tìm kiếm
		if(request.getParameter("brand") == null) {
			try {
				int from = 1;
				if(request.getParameter("from") != null) {
					from = Integer.parseInt(request.getParameter("from"));
				}
				int to = from + 8;
				if(request.getParameter("to") != null) {
					to = Integer.parseInt(request.getParameter("to"));
				}
				String string = request.getParameter("userSearch");
//				System.err.println(string);
				ListProductDAO productAction = new ListProductDAO();
				ArrayList<Product> list = productAction.search(string, from, to);
				int numOfProduct = productAction.countResultSearch(string);
				request.setAttribute("listProduct", list);
				request.setAttribute("from", from);
				request.setAttribute("userSearch", string);
				request.setAttribute("numOfProduct", numOfProduct);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} catch (Exception e) {
				response.getWriter().println(e.getMessage());
			}
		//khi người dùng chọn mục Sản Phẩm để phân loại sản phẩm dựa vào Hãng sản xuất
		} else {
			try {
				int from = 1;
				if(request.getParameter("from") != null) {
					from = Integer.parseInt(request.getParameter("from"));
				}
				int to = from + 8;
				if(request.getParameter("to") != null) {
					to = Integer.parseInt(request.getParameter("to"));
				}
				String string = request.getParameter("brand");
				ListProductDAO productAction = new ListProductDAO();
				ArrayList<Product> list = productAction.findBrand(string, from, to);
				int numOfProduct = productAction.countResultSearchBrand(string);
				request.setAttribute("listProduct", list);
				request.setAttribute("from", from);
				request.setAttribute("brand", string);
				request.setAttribute("numOfProduct", numOfProduct);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} catch (Exception e) {
				response.getWriter().println(e.getMessage());
			}
		}
	}

}
