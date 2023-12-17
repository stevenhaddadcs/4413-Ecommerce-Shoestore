package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

import javax.servlet.ServletContext;

public class LoginDAOImpl implements LoginDAO {
    ServletContext sc;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
        }
    }

    public LoginDAOImpl(ServletContext servletContext) {
        sc = servletContext;
    }

    private Connection getConnection() throws SQLException {
        String path = sc.getRealPath("/dbFile/Shoestore.db");
        return DriverManager.getConnection("jdbc:sqlite:" + path);

    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }



    // get
}