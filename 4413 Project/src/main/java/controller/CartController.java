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
import model.Shoe;
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
		
		System.out.println(action);
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
			}

		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
		
		
	}

	private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    Cart cart = (Cart) request.getSession(true).getAttribute("cart");
	    List<Shoe> shoeList = cart.getAll();
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

		cart.add(shoe);
		request.getSession(true).setAttribute("cart", cart);
	}
	private void removeShoe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession(true).getAttribute("cart");
	    List<Shoe> cartList = cart.getAll();
	    String[] shoeCheck = request.getParameterValues("shoeCheck");
	    
		for(int i = 0; i < shoeCheck.length; i++) {
			if(shoeCheck[i].equals("on")) {
				cartList.remove(i);
			}
		}
		cart.setAll(cartList);
		request.getSession(true).setAttribute("cart", cart);
	}
	
	
	
	
	
}
