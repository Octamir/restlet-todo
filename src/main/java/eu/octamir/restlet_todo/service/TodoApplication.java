package eu.octamir.restlet_todo.service;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class TodoApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router r = new Router();
		r.attach("/todo", TodosResource.class);
		r.attach("/todo/{todo_id}", TodoResource.class);
		return r;
	}
}
