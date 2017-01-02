package eu.octamir.restlet_todo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonHelper {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static String convertObjectToJsonString(Object obj) throws JsonProcessingException {
		return MAPPER.writeValueAsString(obj);
	}
	
	public static <T> T convertJsonStringToObject(String str, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return MAPPER.readValue(str, type);
	}
}
