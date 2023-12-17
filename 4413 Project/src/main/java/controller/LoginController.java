package controller;


import java.io.IOException;
import java.io.PrintWriter;
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

import dao.ShoeDAO;
import dao.ShoeDAOImpl;
import dao.CartDAO;
import dao.CartDAOImpl;
import model.Shoe;
import model.Cart;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServletContext context;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		context = config.getServletContext();

	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String base = "/jsp/";
		String url = base + "home.jsp";
		String action = request.getParameter("action");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		if (action != null) {
			switch (action) {
			case "loginRegister":
				url = base + "Login.jsp";
				break;
			case "login":
				boolean loggedIn = login(request, response, name, password);
				
				if(loggedIn) {
					request.getSession(true).setAttribute("loginStatus", "true");
				}else {
					request.setAttribute("loginFail", "true");
					url = base + "Login.jsp";
				}
				break;
			case "register":
				
				break;
			case "logout":
				request.getSession(true).setAttribute("loginStatus", "false");
				break;
			}

		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
		
		
	}

	private boolean login(HttpServletRequest request, HttpServletResponse response, String name, String password) {
		
		
		
		return false;
	}
	
}
