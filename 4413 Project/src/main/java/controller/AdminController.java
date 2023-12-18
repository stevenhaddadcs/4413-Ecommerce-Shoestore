package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAOImpl;
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
                    url = base + "manageShoeTypes.jsp";
                    break;
                case "manageShoeStock":
                    // takes you to the page to manage shoes inventory
                    url = base + "manageShoeStock.jsp";
                    break;
                // Add more cases for other admin actions as needed
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
