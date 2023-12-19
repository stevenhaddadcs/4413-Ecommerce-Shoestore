package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import model.User;
import dao.LoginDAO;
import dao.LoginDAOImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String base = "/jsp/";
		String url = base + "home.jsp";
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usernameReg = request.getParameter("usernameReg");
		String passwordReg = request.getParameter("passwordReg");

		if (action != null) {
			switch (action) {
			case "loginRegister":
				url = base + "Login.jsp";
				break;
			case "login":
				LoginDAO check = new LoginDAOImpl(context);
				boolean validLogin = check.isValid(username, password);

				if (validLogin) {
					request.getSession(true).setAttribute("loginStatus", "true");
					// checks if admin so that they can access the admin view
					request.getSession(true).setAttribute("adminStatus", check.isAdmin(username));
					User user = check.getUser(username);
					request.getSession(true).setAttribute("user", user);

					// get shoes so that it loads when home.jsp is called
					ShoeDAO shoeDao = new ShoeDAOImpl(context);
					List<Shoe> shoeTypes = shoeDao.findAllShoes();
					request.setAttribute("shoeTypes", shoeTypes);
				} else {
					request.setAttribute("loginFail", "true");
					url = base + "Login.jsp";
				}
				break;
			case "register":
				url = base + "register.jsp";
				break;
			case "registerSubmit":
				check = new LoginDAOImpl(context);
				boolean available = check.userAvailable(usernameReg);

				Pattern ccReg = Pattern.compile("^[a-zA-Z0-9]{1,}$");
				Matcher matcher = ccReg.matcher(usernameReg);
				boolean matchFlag = matcher.matches();

				if (available && matchFlag && passwordReg != "") {
					check.register(usernameReg, passwordReg);
					request.getSession(true).setAttribute("loginStatus", "true");
					User user = new User();
					user.setUsername(usernameReg);
					user.setPassword(passwordReg);
					request.getSession(true).setAttribute("user", user);
				} else if (!available) {
					request.setAttribute("nameTaken", "true");
					url = base + "register.jsp";
				} else if (passwordReg == "") {
					request.setAttribute("passwordEmpty", "true");
					url = base + "register.jsp";
				} else if (usernameReg == "" || !matchFlag) {
					request.setAttribute("nameEmpty", "true");
					url = base + "register.jsp";
				}
				break;
			case "logout":
				// get shoes so that it loads when home.jsp is called
				request.getSession(true).setAttribute("loginStatus", "false");
				break;
			case "profile":
				url = base + "profile.jsp";
				break;
			}
		} else {
			// get shoes so that it loads when home.jsp is called
			ShoeDAO shoeDao = new ShoeDAOImpl(context);
			List<Shoe> shoeTypes = shoeDao.findAllShoes();
			request.setAttribute("shoeTypes", shoeTypes);
		}

		// get shoes so that it loads when home.jsp is called
		if (url == (base + "home.jsp")) {
			ShoeDAO shoeDao = new ShoeDAOImpl(context);
			List<Shoe> shoeTypes = shoeDao.findAllShoes();
			request.setAttribute("shoeTypes", shoeTypes);
		}

		
		ShoeDAO shoeDao = new ShoeDAOImpl(context);
		List<Shoe> shoeTypes = shoeDao.findAllShoes();
		request.setAttribute("shoeTypes", shoeTypes);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);

	}

}
