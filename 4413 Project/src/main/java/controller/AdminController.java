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
        String action = request.getParameter("action");

        if (action != null) {
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

                // Action cases for updating and deleting
                case "deleteUser":
                    String username = request.getParameter("username");
                    System.out.println(username);
                    adminDAO.deleteUser(username);
                    // Call the DAO method to get all users
                    ArrayList<User> users1 = adminDAO.getAllUsers();
                    request.setAttribute("users", users1);
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
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
