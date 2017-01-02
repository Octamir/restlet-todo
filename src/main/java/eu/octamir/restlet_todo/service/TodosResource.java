package eu.octamir.restlet_todo.service;

import java.io.IOException;
import java.sql.SQLException;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.fasterxml.jackson.core.JsonProcessingException;

import eu.octamir.restlet_todo.ErrorModel;
import eu.octamir.restlet_todo.JsonHelper;
import eu.octamir.restlet_todo.Todo;
import eu.octamir.restlet_todo.dal.DALTodo;
import eu.octamir.restlet_todo.dal.TodoNotFoundException;

public class TodosResource extends ServerResource {

	@Get("json")
	public String getTodos() throws JsonProcessingException, ClassNotFoundException, SQLException {
		return JsonHelper.convertObjectToJsonString(DALTodo.getInstance().getTodos());
	}

	@Post("json")
	public String addTodo(String data) throws JsonProcessingException {
		try {
			// If the posted data cannot be parsed to the Todo model, it fails and sends an error
			Todo parsedTodo = JsonHelper.convertJsonStringToObject(data, Todo.class);
			
			return JsonHelper.convertObjectToJsonString(DALTodo.getInstance().insertTodo(parsedTodo));
		} catch (IOException | ClassNotFoundException | SQLException | TodoNotFoundException e) {
			e.printStackTrace();
			return JsonHelper.convertObjectToJsonString(new ErrorModel("Could not post this data"));
		}
	}
}
