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
		String sql = "select * from users where username='"+ user +"' and password='" + pass + "'";

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
    
	public boolean userAvailable(String user) {
		int count = 0;
		boolean valid = false;
		String sql = "select * from users where username='"+ user + "'";

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
		if (count <= 0) {
			valid = true;
		}
		return valid;
	}

	@Override
	public void register(String user, String pass) {
		
		
		Connection connection = null;
		try {
			connection = getConnection();
	        String purchaseString = "INSERT INTO USERS (username, password) VALUES (?, ?)";
	        PreparedStatement purchaseStatement = connection.prepareStatement(purchaseString);
	        purchaseStatement.setString(1, user);
	        purchaseStatement.setString(2, pass);
	        purchaseStatement.executeUpdate();
	        
	        PreparedStatement test = connection.prepareStatement("select * from users");
	        ResultSet rs = test.executeQuery();
	        while(rs.next()) {
	        	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5));
	        }
	        
	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection(connection);
        }
		
	}
}