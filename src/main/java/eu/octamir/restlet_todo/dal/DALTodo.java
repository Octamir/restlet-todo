package eu.octamir.restlet_todo.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eu.octamir.restlet_todo.Todo;
import eu.octamir.restlet_todo.TodoWithId;

public final class DALTodo {

	private static DALTodo instance;

	public static DALTodo getInstance() {
		if (instance == null) {
			instance = new DALTodo();
		}

		return instance;
	}

	private DALTodo() {
	}

	public TodoWithId[] getTodos() throws ClassNotFoundException, SQLException {
		Statement stmt = JDBCConnector.getInstance().getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM todo");
		ArrayList<TodoWithId> todos = new ArrayList<>();
		while (rs.next()) {
			todos.add(new TodoWithId(rs.getInt("id"), rs.getString("description"), rs.getInt("priority"),
					rs.getBoolean("completed")));
		}
		return todos.toArray(new TodoWithId[todos.size()]);
	}

	public TodoWithId getTodo(int id) throws ClassNotFoundException, SQLException, TodoNotFoundException {
		Statement stmt = JDBCConnector.getInstance().getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM todo WHERE id = " + id);
		if (rs.next()) {
			return new TodoWithId(rs.getInt("id"), rs.getString("description"), rs.getInt("priority"),
					rs.getBoolean("completed"));
		} else {
			throw new TodoNotFoundException();
		}
	}

	// Based on
	// http://stackoverflow.com/questions/1915166/how-to-get-the-insert-id-in-jdbc
	public TodoWithId insertTodo(Todo todo) throws ClassNotFoundException, SQLException, TodoNotFoundException {
		PreparedStatement statement = JDBCConnector.getInstance().getConnection()
				.prepareStatement("INSERT INTO todo VALUES (NULL, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, todo.getDescription());
		statement.setInt(2, todo.getPriority());
		statement.setBoolean(3, todo.isCompleted());

		int nrOfAffectedRows = statement.executeUpdate();
		if (nrOfAffectedRows == 0) {
			throw new SQLException("Creating todo failed, no rows affected.");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();

		// No matter what, we're talking about creating one row at max
		if (generatedKeys.next()) {
			return this.getTodo(generatedKeys.getInt(1));
		} else {
			throw new SQLException("Creating todo failed, no ID obtained.");
		}
	}
}
