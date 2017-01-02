package eu.octamir.restlet_todo;

public final class ErrorModel {

	private String error;
	
	public ErrorModel(String error) {
		this.error = error;
	}
	
	public String getError() {
		return this.error;
	}
}
