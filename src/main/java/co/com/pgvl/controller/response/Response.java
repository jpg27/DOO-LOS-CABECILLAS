package co.com.pgvl.controller.response;

import java.util.ArrayList;
import java.util.List;

import co.com.pgvl.crosscutting.helpers.ObjectHelper;

public abstract class Response {
	private List<String> messages = new ArrayList<>();
	
	
	public final void setMessages(List<String> messages) {
		this.messages = ObjectHelper.getDefault(messages, this.messages);
	}

	public final List<String> getMessages() {
		return messages;
	}

}
