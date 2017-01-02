package eu.octamir.restlet_todo.service;

import java.sql.SQLException;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.fasterxml.jackson.core.JsonProcessingException;

import eu.octamir.restlet_todo.ErrorModel;
import eu.octamir.restlet_todo.JsonHelper;
import eu.octamir.restlet_todo.dal.DALTodo;
import eu.octamir.restlet_todo.dal.TodoNotFoundException;

public class TodoResource extends ServerResource {

	@Get("json")
	public String getTodo() throws JsonProcessingException, ClassNotFoundException, SQLException {
		int id = Integer.parseInt(getAttribute("todo_id"));
		
		try {
			return JsonHelper.convertObjectToJsonString(DALTodo.getInstance().getTodo(id));
		} catch (TodoNotFoundException e) {
			return JsonHelper.convertObjectToJsonString(new ErrorModel("Could not find todo with id " + id));
		}
	}
}
