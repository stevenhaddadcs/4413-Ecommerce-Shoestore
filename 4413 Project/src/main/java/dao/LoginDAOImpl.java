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

	public boolean isValid(String user, String pass) {
		int count = 0;
		boolean valid = false;
		String sql = "select * from users where username='" + user + "' and password='" + pass + "'";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count++;
				System.out.println(resultSet.getString(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	//check if the user is an admin
	@Override
	public boolean isAdmin(String user) {
	    int isAdmin = 0;
	    String sql = "SELECT is_admin FROM users WHERE username = ?";

	    Connection connection = null;
	    try {
	        connection = getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, user);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            isAdmin = resultSet.getInt("is_admin");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        closeConnection(connection);
	    }

	    // returns true if admin
	    return isAdmin == 1;
	}
}