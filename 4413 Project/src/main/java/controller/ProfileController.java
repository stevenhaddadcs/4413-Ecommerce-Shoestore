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
import model.Purchase;
import model.User;
import dao.LoginDAO;
import dao.LoginDAOImpl;
import dao.PurchaseDAO;
import dao.PurchaseDAOImpl;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServletContext context;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
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
		String url = base + "profile.jsp";
		User user;
		LoginDAO check = new LoginDAOImpl(context);
		
		if(request.getParameter("addAddress") != null) {
			String address = request.getParameter("addressP");
			if(address == "") {
				request.setAttribute("addressEmpty", "true");
			}else {
				user = (User) request.getSession(true).getAttribute("user");
				user.setAddress(address);
				check.changeAddress(user.getUsername(), address);
				request.getSession(true).setAttribute("user", user);
			}
			url = base + "profile.jsp";
			
		}else if(request.getParameter("changeAddress") != null) {
			request.setAttribute("changeA", "true");
			url = base + "changeProfile.jsp";
			
		}else if(request.getParameter("addCC") != null) {
			String creditCard = request.getParameter("creditCard");
			
			Pattern ccReg = Pattern.compile("^[0-9]{15,16}$");
			Matcher matcher = ccReg.matcher(creditCard);
			boolean matchFlag = matcher.matches();
			
			if(creditCard == "" || !matchFlag) {
				request.setAttribute("ccEmpty", "true");
			}else {
				user = (User) request.getSession(true).getAttribute("user");
				user.setCc_number(creditCard);
				check.changeCC(user.getUsername(), creditCard);
				request.getSession(true).setAttribute("user", user);
			}
			url = base + "profile.jsp";
			
		}else if(request.getParameter("changeCC") != null) {
			request.setAttribute("changeC", "true");
			url = base + "changeProfile.jsp";
			
		}else if(request.getParameter("changeUser") != null) {
			request.setAttribute("changeU", "true");
			url = base + "changeProfile.jsp";
			
		}else if(request.getParameter("changePass") != null) {
			request.setAttribute("changeP", "true");
			url = base + "changeProfile.jsp";

		}else if(request.getParameter("changeNewAddress") != null) {
			String newAddress = request.getParameter("newAddress");
			String newAddressCheck = request.getParameter("newAddressCheck");
			
			if(newAddress.equals(newAddressCheck)) {
				user = (User) request.getSession(true).getAttribute("user");
				user.setAddress(newAddress);
				check.changeAddress(user.getUsername(), newAddress);
				request.getSession(true).setAttribute("user", user);
				url = base + "profile.jsp";
			}else {
				request.setAttribute("changeA", "true");
				request.setAttribute("addressInvalid", "true");
				url = base + "changeProfile.jsp";
			}
	
		}else if(request.getParameter("changeNewCC") != null) {
			String newCC = request.getParameter("newCC");
			String newCCCheck = request.getParameter("newCCCheck");
			
			Pattern ccReg = Pattern.compile("^[0-9]{15,16}$");
			Matcher matcher = ccReg.matcher(newCC);
			boolean matchFlag = matcher.matches();
			
			if(newCC.equals(newCCCheck) && matchFlag) {
				user = (User) request.getSession(true).getAttribute("user");
				user.setCc_number(newCC);
				check.changeCC(user.getUsername(), newCC);
				request.getSession(true).setAttribute("user", user);
				url = base + "profile.jsp";
			}else if(!newCC.equals(newCCCheck)){
				request.setAttribute("changeC", "true");
				request.setAttribute("ccInvalid", "true");
				url = base + "changeProfile.jsp";
			}
			else if(!matchFlag){
				request.setAttribute("changeC", "true");
				request.setAttribute("ccInvalid2", "true");
				url = base + "changeProfile.jsp";
			}

			
		}else if(request.getParameter("changeNewUsername") != null) {
			String newUsername = request.getParameter("newUsername");
			String newUsernameCheck = request.getParameter("newUsernameCheck");
			
			Pattern ccReg = Pattern.compile("^[a-zA-Z0-9]{1,}$");
			Matcher matcher = ccReg.matcher(newUsername);
			boolean matchFlag = matcher.matches();
			
			if(newUsername.equals(newUsernameCheck) && matchFlag) {
				user = (User) request.getSession(true).getAttribute("user");
				check.changeUser(user.getUsername(), newUsername);
				user.setUsername(newUsername);
				request.getSession(true).setAttribute("user", user);
				url = base + "profile.jsp";
			}else if(!newUsername.equals(newUsernameCheck)){
				request.setAttribute("changeU", "true");
				request.setAttribute("usernameInvalid", "true");
				url = base + "changeProfile.jsp";
			}else if(!matchFlag) {
				request.setAttribute("changeU", "true");
				request.setAttribute("usernameInvalid2", "true");
				url = base + "changeProfile.jsp";
			}
			
		}else if(request.getParameter("changeNewPassword") != null) {
			String newPassword = request.getParameter("newPassword");
			String newPasswordCheck = request.getParameter("newPasswordCheck");
			
			if(newPassword.equals(newPasswordCheck)) {
				user = (User) request.getSession(true).getAttribute("user");
				user.setPassword(newPassword);
				check.changePassword(user.getUsername(), newPassword);
				request.getSession(true).setAttribute("user", user);
				url = base + "profile.jsp";
			}else {
				request.setAttribute("changeP", "true");
				request.setAttribute("passwordInvalid", "true");
				url = base + "changeProfile.jsp";
			}
			
		}else if(request.getParameter("viewHistory") != null) {
			user = (User) request.getSession(true).getAttribute("user");
			PurchaseDAO get = new PurchaseDAOImpl(context);
			ShoeDAO find = new ShoeDAOImpl(context);
			List<Purchase> purchases = get.getUserPurchases(user.getUsername());
			
			for(int i = 0; i < purchases.size(); i++) {
				String fullItems = "";
				String proper = "";
				String [] commaSplit = purchases.get(i).getItems_ids().split(",");
				for(int j = 0; j < commaSplit.length; j++) {
					String [] dashSplit = commaSplit[j].split("_");
					String model = find.getShoeById(dashSplit[0]);
					String size = dashSplit[1];
					proper = model + " Size:" + size;
					fullItems = fullItems + proper + " AND ";
				}
				fullItems = fullItems.substring(0, fullItems.length() - 5);
				purchases.get(i).setItems_ids(fullItems);
			}
			request.setAttribute("purchaseHistory", purchases);
			url = base + "purchases.jsp";
		}
		
		ShoeDAO shoeDao = new ShoeDAOImpl(context);
		List<Shoe> shoeTypes = shoeDao.findAllShoes();
		request.setAttribute("shoeTypes", shoeTypes);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
		
	}
	
}
