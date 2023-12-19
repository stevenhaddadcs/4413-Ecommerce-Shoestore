package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAOImpl;
import model.Purchase;
import model.Shoe;
import model.User;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context;

	private AdminDAOImpl adminDAO;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		context = config.getServletContext();
		adminDAO = new AdminDAOImpl(context);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String base = "/jsp/";
		String url = base + "adminHome.jsp"; // home page for admin
		String notAdminPage = base + "notAdmin.jsp";
		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		// if not null and is admin == true
		boolean adminCheck = session.getAttribute("adminStatus") != null
				&& (boolean) session.getAttribute("adminStatus");

		if (!adminCheck) {
			// Redirect or take appropriate action for non-admin users
			url = notAdminPage;
		} else if (action != null) {
			switch (action) {
			case "manageUsers":
				// Call the DAO method to get all users
				ArrayList<User> users = adminDAO.getAllUsers();
				request.setAttribute("users", users);
				url = base + "manageUsers.jsp";
				break;
			case "manageShoeTypes":
				// takes you to the page to manage shoes inventory
				ArrayList<Shoe> shoetypes = adminDAO.getAllShoeTypes();
				request.setAttribute("shoetypes", shoetypes);
				url = base + "manageShoeTypes.jsp";
				break;
			case "manageShoeStock":
				// takes you to the page to manage shoes inventory
				ArrayList<Shoe> shoestocks = adminDAO.getAllShoeStocks();
				request.setAttribute("shoestocks", shoestocks);
				url = base + "manageShoeStock.jsp";
				break;
			case "managePurchaseOrders":
				// takes you to the page to look at all purchase orders
				ArrayList<Purchase> purchaseOrders = adminDAO.getAllPurchases();
				request.setAttribute("purchaseOrders", purchaseOrders);
				url = base + "managePurchaseOrders.jsp";
				break;

			// Action cases for updating and deleting
			case "updateAdminStatus":
				String username = request.getParameter("username");
				int isAdmin = request.getParameter("isAdmin") != null ? 1 : 0;
				adminDAO.updateAdminStatus(username, isAdmin);
				// Call the DAO method to get all users
				ArrayList<User> users1 = adminDAO.getAllUsers();
				request.setAttribute("users", users1);
				url = base + "manageUsers.jsp";
				break;
			case "deleteUser":
				String username1 = request.getParameter("username");
				System.out.println(username1);
				adminDAO.deleteUser(username1);
				// Call the DAO method to get all users
				ArrayList<User> users2 = adminDAO.getAllUsers();
				request.setAttribute("users", users2);
				url = base + "manageUsers.jsp";
				break;
			case "deleteShoe":
				int shoe_id = Integer.parseInt(request.getParameter("shoe_id"));
				adminDAO.deleteShoe(shoe_id);
				// takes you to the page to manage shoes inventory
				ArrayList<Shoe> shoetypes1 = adminDAO.getAllShoeTypes();
				request.setAttribute("shoetypes", shoetypes1);
				url = base + "manageShoeTypes.jsp";
				break;

			case "updateShoePrice":
				int shoe_id2 = Integer.parseInt(request.getParameter("shoe_id"));
				float price = Float.parseFloat(request.getParameter("price"));
				adminDAO.updateShoePrice(shoe_id2, price);
				// takes you to the page to manage shoes inventory
				ArrayList<Shoe> shoetypes2 = adminDAO.getAllShoeTypes();
				request.setAttribute("shoetypes", shoetypes2);
				url = base + "manageShoeTypes.jsp";
				break;
			case "updateShoeStock":
				String stock_id = request.getParameter("stock_id");
				int stock = Integer.parseInt(request.getParameter("stock"));
				adminDAO.updateShoeStock(stock_id, stock);
				// takes you to the page to manage shoes inventory
				ArrayList<Shoe> shoestocks1 = adminDAO.getAllShoeStocks();
				request.setAttribute("shoestocks", shoestocks1);
				url = base + "manageShoeStock.jsp";
				break;
			case "manageUsernameOrders":
				// takes you to the page to look at all purchase orders
				String username2 = request.getParameter("username");
				ArrayList<Purchase> userOrders = adminDAO.getUserPurchases(username2);
				request.setAttribute("purchaseOrders", userOrders);
				url = base + "managePurchaseOrders.jsp";
				break;
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
