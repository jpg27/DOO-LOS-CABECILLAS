package co.com.pgvl.controller.response.concrete;

import java.util.List;

import co.com.pgvl.controller.response.Response;
import co.com.pgvl.controller.response.ResponseWithData;
import co.com.pgvl.dto.ClienteDTO;

public final class ClienteResponse extends ResponseWithData<ClienteDTO> {
	
	public static final Response build(final List<String> messages, final List<ClienteDTO> data) {
		var response = new ClienteResponse();
		response.setMessages(messages);
		response.setData(data);
		
		return response;
	}
}
