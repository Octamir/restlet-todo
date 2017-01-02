package eu.octamir.restlet_todo.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Package local so we don't use the raw connection in the main code!
 */
final class JDBCConnector {

	private static JDBCConnector instance;

	private Connection connection;

	public static JDBCConnector getInstance() {
		if (instance == null) {
			instance = new JDBCConnector();
		}

		return instance;
	}

	private JDBCConnector() {
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if (this.connection == null) {
			// Apperantly became obsolete
			//Class.forName("com.mysql.jdbc.Driver");
			
			this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/todo", "octamir", "");

			// Add a shutdown hook to close the connection on program shutdown
			// Gets added once only
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.out.println("Closing connection...");

					try {
						JDBCConnector.getInstance().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}
		return this.connection;
	}

	public void close() throws SQLException {
		if (this.connection != null) {
			this.connection.close();
			this.connection = null;
		}
	}
}
