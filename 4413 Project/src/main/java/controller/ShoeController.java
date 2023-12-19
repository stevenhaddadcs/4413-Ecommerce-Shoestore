package controller;

import java.io.IOException;
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
import model.Cart;
import model.Shoe;


@WebServlet({ "/shoes"})
public class ShoeController extends HttpServlet {
	
	ServletContext context;
	
	public ShoeController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		context = config.getServletContext();
		ShoeDAO shoeDAO = new ShoeDAOImpl(context);
		// calling DAO method to retrieve category List from Database, for left column display
		List<String> brandList =  shoeDAO.findAllBrands();
		context.setAttribute("brandList", brandList);
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String loginStatus;
		synchronized (session) {
			loginStatus = (String) session.getAttribute("loginStatus");
			if(loginStatus == null) {
				request.getSession(true).setAttribute("loginStatus", "false");
			}
			
		}
		
		String base = "/jsp/";
		String url = base + "home.jsp";
		String action = request.getParameter("action");
		String brand = request.getParameter("brand");
		String keyWord = request.getParameter("keyWord");
		String model = request.getParameter("model");
		String colour = request.getParameter("colour");
		if (action != null) {
			switch (action) {
			case "allShoes":
				findAllShoes(request, response);
				url = base + "listOfShoes.jsp";
				break;
			case "brand":
				findShoesByBrand(request, response, brand);
				url = base + "brand.jsp?brand=" + brand;
				break;
			case "search":
				searchShoes(request, response, keyWord);
				url = base + "searchResult.jsp";
				break;
			case "stock":
				findStockByShoe(request, response,model,colour);
				url = base + "shoe.jsp?model=" + model+"&colour=" + colour;
				break;
				
			}
		}
		ShoeDAO shoeDao = new ShoeDAOImpl(context);
		List<Shoe> shoeTypes = shoeDao.findAllShoes();
		request.setAttribute("shoeTypes", shoeTypes);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	private void findAllShoes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ShoeDAO shoeDao = new ShoeDAOImpl(context);
			List<Shoe> shoeList = shoeDao.findAllShoes();
			request.setAttribute("shoeList", shoeList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//search books by keyword
	private void searchShoes(HttpServletRequest request,
			HttpServletResponse response, String keyWord)
			throws ServletException, IOException {
		try {
			
			ShoeDAO shoeDao = new ShoeDAOImpl(context);
			List<Shoe> shoeList = shoeDao.searchShoesByKeyword(keyWord);

			request.setAttribute("shoeList", shoeList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void findShoesByBrand(HttpServletRequest request,
			HttpServletResponse response, String brand)
			throws ServletException, IOException {
		try {

			ShoeDAO shoeDao = new ShoeDAOImpl(context);
			List<Shoe> shoeList = shoeDao.findShoesByBrand(brand);

			request.setAttribute("shoeList", shoeList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void findStockByShoe(HttpServletRequest request,
			HttpServletResponse response, String model, String colour)
			throws ServletException, IOException {
		try {

			ShoeDAO shoeDao = new ShoeDAOImpl(context);
			List<Shoe> shoeList = shoeDao.searchShoesByModel(model, colour);
			
			for(int i = 0; i < shoeList.size(); i++) {
				
				float temp = shoeList.get(i).getSize();
				if(temp == Math.floor(temp)) {
					int tempInt = Math.round(temp);
					shoeList.get(i).setStock(shoeDao.getShoeStocks(shoeList.get(i).getId()+"_"+tempInt));
				}else {
					shoeList.get(i).setStock(shoeDao.getShoeStocks(shoeList.get(i).getId()+"_"+temp));
				}

			}

			request.setAttribute("shoeStockList", shoeList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
