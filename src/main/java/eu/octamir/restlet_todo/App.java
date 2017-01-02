package eu.octamir.restlet_todo;

import org.restlet.Component;
import org.restlet.data.Protocol;

import eu.octamir.restlet_todo.service.TodoApplication;

public final class App {

	// Just let the server crash if no server can be created
	public static void main(String[] args) throws Exception {
		Component c = new Component();
		c.getServers().add(Protocol.HTTP, 8000);
		c.getDefaultHost().attach(new TodoApplication());
		c.start();
	}
}
