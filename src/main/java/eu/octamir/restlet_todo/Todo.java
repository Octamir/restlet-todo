package eu.octamir.restlet_todo;

/*
 * Class that represents objects to be created in the database
 */
public class Todo {

	private String description;
	private int priority;
	private boolean completed;
	
	/* Needed to generate objects from json using Jackson */
	public Todo() {}
	
	public Todo(String description, int priority, boolean completed) {
		this.description = description;
		this.priority = priority;
		this.completed = completed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
