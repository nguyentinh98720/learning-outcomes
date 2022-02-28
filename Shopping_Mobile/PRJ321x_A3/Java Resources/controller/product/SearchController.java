package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import model.Product;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ListProductDAO action = new ListProductDAO();
		try (PrintWriter out = response.getWriter()) {
			int from = 1;
			int to = from + 8;
			if(request.getParameter("from") != null) {
				from = Integer.parseInt(request.getParameter("from"));
			}
			if(request.getParameter("to") != null) {
				to = Integer.parseInt(request.getParameter("to"));
			}
			String name = request.getParameter("search");
			ArrayList<Product> list = action.search(name, from, to);
			int number = action.countResultSearch(name);
			request.setAttribute("listProduct", list);
			request.setAttribute("from", from);
			request.setAttribute("numOfProduct", number);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (SQLException e) {
			Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
