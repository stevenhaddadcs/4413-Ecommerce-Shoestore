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
	        PreparedStatement statement = connection.prepareStatement(purchaseString);
	        statement.setString(1, user);
	        statement.setString(2, pass);
	        statement.executeUpdate();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection(connection);
        }
		
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

    @Override
    public void changeUser(String usernameOld, String usernameNew) {
        // TODO Auto-generated method stub
        String updateUsernameSQL = "";
        
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement updateUsernameStatement;
            
            updateUsernameSQL = "UPDATE users SET username = ? WHERE username = ?";
            updateUsernameStatement = connection.prepareStatement(updateUsernameSQL);
            updateUsernameStatement.setString(1, usernameNew);
            updateUsernameStatement.setString(2,  usernameOld);
            updateUsernameStatement.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

	@Override
	public void changePassword(String username, String password) {
		// TODO Auto-generated method stub
        Connection connection = null;
        try {
            connection = getConnection();
            String add = "UPDATE USERS SET password= ? WHERE username= ?";
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, password);
            statement.setString(2, username);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
	}

	@Override
	public void changeAddress(String username, String address) {
		// TODO Auto-generated method stub
        Connection connection = null;
        try {
            connection = getConnection();
            String add = "UPDATE USERS SET address= ? WHERE username= ?";
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, address);
            statement.setString(2, username);
            statement.executeUpdate();   
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
	}


	@Override
	public void changeCC(String username, String cc) {
		// TODO Auto-generated method stub
        Connection connection = null;
        try {
            connection = getConnection();
            String add = "UPDATE USERS SET cc_number= ? WHERE username= ?";
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, cc);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
	}
	
	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = new User();
        Connection connection = null;
        try {
            connection = getConnection();
            String add = "select * from users where username=?";
            PreparedStatement statement = connection.prepareStatement(add);
            statement.setString(1, username);
            statement.executeQuery();
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	String name = rs.getString(1);
            	String pass = rs.getString(2);
            	int isAdmin = rs.getInt(3);
            	String address = rs.getString(4);
            	String cc = rs.getString(5);
            	
            	user.setUsername(name);
            	user.setPassword(pass);
            	user.setIsAdmin(isAdmin);
            	user.setAddress(address);
            	user.setCc_number(cc);
            } 
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return user;
	}


}