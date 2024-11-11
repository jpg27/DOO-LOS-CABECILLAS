package co.com.pgvl.controller.response.concrete;

import java.util.List;

import co.com.pgvl.controller.response.Response;

public final class GenericResponse extends Response {
	
	public static final Response build(final List<String> messages) {
		var response = new GenericResponse();
		response.setMessages(messages);
		
		return response;
	}

}
