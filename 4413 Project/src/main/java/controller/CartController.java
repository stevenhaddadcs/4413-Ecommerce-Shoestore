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
import dao.LoginDAO;
import dao.LoginDAOImpl;
import model.Shoe;
import model.User;
import model.Cart;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServletContext context;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
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
	      HttpSession session = request.getSession(true);
	      Cart cart;
	      synchronized (session) {  // synchronized to prevent concurrent updates
	         // Retrieve the shopping cart for this session, if any. Otherwise, create one.
	         cart = (Cart) session.getAttribute("cart");
	         if (cart == null) {  // No cart, create one.
	            cart = new Cart();
	            session.setAttribute("cart", cart);  // Save it into session
	         }
	      }
		String base = "/jsp/";
		String url = base + "home.jsp";
		String action = request.getParameter("action");
		String model = request.getParameter("model");
		String colour = request.getParameter("colour");
		String size = request.getParameter("sizeSelect");
		String ccard = request.getParameter("ccard");
		String address = request.getParameter("address");

		if (action != null) {
			switch (action) {
			case "add":
				addShoe(request, response, model, colour, size);
				break;
			case "viewCart":
				viewCart(request, response);
				url = base + "cart.jsp";
				break;			
			case "removeShoe":
				removeShoe(request, response);
				break;			
			case "checkout":
				String loginStatus = (String) request.getSession(true).getAttribute("loginStatus");
				if(loginStatus.equals("true")) {
					url = base + "checkout.jsp"; //placeholder for checking out if logged in
				}else {
					request.setAttribute("notLogged", "true");
					url = base + "Login.jsp"; //placeholder for logging in/registering if not logged in.
				}
				viewCart(request, response);
				break;
			case "checkedout":
				cart = (Cart) request.getSession(true).getAttribute("cart");
				CartDAO cartDao = new CartDAOImpl(context);
				User user = (User) request.getSession(true).getAttribute("user");		
				
				if(ccard != "") {
					request.setAttribute("ccard", ccard);
				}
				if(address != "") {
					request.setAttribute("address", address);
				}
				
				Pattern ccReg = Pattern.compile("^[0-9]{15,16}$");
				Matcher matcher = ccReg.matcher(ccard);
				boolean matchFlag = matcher.matches();
				
				
				if(matchFlag && address != "") {
					LoginDAO check = new LoginDAOImpl(context);
					if(user.getAddress().equals(null)) {
						user.setAddress(address);
						check.changeAddress(user.getUsername(), address);
					}
					if(user.getCc_number().equals(null)) {
						user.setCc_number(ccard);
						check.changeCC(user.getUsername(), ccard);
					}
					
					request.getSession(true).setAttribute("user",user);
					
					cartDao.checkout(cart, user.getUsername(), ccard, address);
					System.out.println("You have been checked out");
					Cart newCart = new Cart();
					request.getSession(true).setAttribute("cart", newCart);
				}else if(ccard == "" || !matchFlag) {
					request.setAttribute("noCC", "true");
					viewCart(request, response);
					url = base + "checkout.jsp";
				}else if(address == "") {
					request.setAttribute("noAddress", "true");
					viewCart(request, response);
					url = base + "checkout.jsp";
				}
				break;
			}

		}
		
		ShoeDAO shoeDao = new ShoeDAOImpl(context);
		List<Shoe> shoeTypes = shoeDao.findAllShoes();
		request.setAttribute("shoeTypes", shoeTypes);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
		
		
	}

	private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    Cart cart = (Cart) request.getSession(true).getAttribute("cart");
	    List<Shoe> shoeList = cart.getAll();
	    float total = 0;
	    for(int i = 0; i <shoeList.size(); i++) {
	    	float temp = shoeList.get(i).getPrice();
	    	total = total + temp;
	    }
	    
	    request.setAttribute("total", total);
	    request.setAttribute("shoeList", shoeList);
	    
	}

	private void addShoe(HttpServletRequest request, HttpServletResponse response, String model, String colour, String size) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession(true).getAttribute("cart");
		
		ShoeDAO shoeDao = new ShoeDAOImpl(context);
		Shoe shoe = new Shoe();
		
		float temp = Float.valueOf(size);
		if(temp == Math.floor(temp)) {
			int tempInt = Math.round(temp);
			shoe = shoeDao.searchShoesByMCS(model, colour,"" +tempInt);
		}else {
			shoe = shoeDao.searchShoesByMCS(model, colour,"" +temp);
		}
		
		if(shoe.getStock() > 0) {
			cart.add(shoe);
		}

		request.getSession(true).setAttribute("cart", cart);
	}
	private void removeShoe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession(true).getAttribute("cart");
	    List<Shoe> cartList = cart.getAll();
	    String[] shoeCheck = request.getParameterValues("shoeCheck");
	    if(shoeCheck != null) {
			for(int i = 0; i < shoeCheck.length; i++) {
				for(int j = 0; j < cartList.size(); j++) {
					String temp = cartList.get(j).getModel()+","+cartList.get(j).getColourway()+","+cartList.get(j).getSize();
					if(shoeCheck[i].equals(temp)) {
						cartList.remove(j);
					}
				}	
			}
			cart.setAll(cartList);
			request.getSession(true).setAttribute("cart", cart);
	    }
	}
	
}
