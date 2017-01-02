package eu.octamir.restlet_todo;

/*
 * This class represents objects to send back in json format
 */
public class TodoWithId extends Todo {

	private int id;
	
	/* Needed to generate objects from json using Jackson */
	public TodoWithId() {}
	
	public TodoWithId(int id, String description, int priority, boolean completed) {
		super(description, priority, completed);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
