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

import dao.BookDAO;
import dao.BookDAOImpl;
import model.Book;
import model.Category;

@WebServlet({ "/books"})
public class BookController extends HttpServlet {

	BookDAO bookDao;
	public BookController() {
		super();
		
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	
		// calling DAO method to retrieve category List from Database, for left column display
		
		ServletContext context =  getServletContext();
		bookDao = new BookDAOImpl(context);
		List<Category> categoryList = bookDao.findAllCategories();
		context.setAttribute("categoryList", categoryList);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String base = "/jsp/";
		String url = base + "home.jsp";
		String action = request.getParameter("action");
		String category = request.getParameter("category");
		String keyWord = request.getParameter("keyWord");
		if (action != null) {
			switch (action) {
			case "allBooks":
				findAllBooks(request, response);
				url = base + "listOfBooks.jsp";
				break;
			case "category":
				findBooksByCategory(request, response, category);
				url = base + "category.jsp?category=" + category;
				break;
			case "search":
				searchBooks(request, response, keyWord);
				url = base + "searchResult.jsp";
				break;

			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	private void findAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// calling DAO method to retrieve a list of all books 
			List<Book> bookList = bookDao.findAllBooks();
			request.setAttribute("bookList", bookList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//search books by keyword
	private void searchBooks(HttpServletRequest request,
			HttpServletResponse response, String keyWord)
			throws ServletException, IOException {
		try {
			
			// calling DAO method to search book by keyword 
			List<Book> bookList = bookDao.searchBooksByKeyword(keyWord);

			request.setAttribute("bookList", bookList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void findBooksByCategory(HttpServletRequest request,
			HttpServletResponse response, String cate)
			throws ServletException, IOException {
		try {
			
			// calling DAO method to search book by keyword 
			List<Book> bookList = bookDao.findBooksByCategory(cate);

			request.setAttribute("bookList", bookList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
