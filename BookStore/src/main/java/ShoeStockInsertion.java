import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class ShoeStockInsertion {

	public static void main(String[] args) {
		// Database connection parameters
		
		String url = "jdbc:sqlite:D:/github repositories/4413-Group-Project/BookStore/src/main/webapp/WEB-INF/Shoetypes.db"; // Change this to the path of your SQLite database
		String[] shoeSizes = { "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12" };

		// JDBC variables for database connection
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Connect to the SQLite database
			connection = DriverManager.getConnection(url);

			// Loop through each shoe size and insert into the SHOESTOCK table
			for (int shoeId = 1; shoeId < 38; shoeId++) {
				for (String size : shoeSizes) {

					String stockId = shoeId + "_" + size;

					Random random = new Random();
					int stock = random.nextInt(5) + 1;

					// Generate and execute the INSERT statement
					String sql = "INSERT INTO SHOESTOCK (stock_id, shoe_id, shoe_size, stock) VALUES (?, ?, ?, ?)";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, stockId);
					preparedStatement.setInt(2, shoeId);
					preparedStatement.setString(3, size);
					preparedStatement.setInt(4, stock);
					preparedStatement.executeUpdate();
				}
			}

			System.out.println("Data inserted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}