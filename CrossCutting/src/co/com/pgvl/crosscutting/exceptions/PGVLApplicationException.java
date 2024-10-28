package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public class PGVLApplicationException extends RuntimeException {

	private static final long serialVersionUID = -7682900863325465078L;

	private String userMessage;
	private Layer layer;

	public PGVLApplicationException(final String userMessage, final String technicalMessage,
			final Exception rootException, final Layer layer) {

		super(TextHelper.applyTrim(technicalMessage), ObjectHelper.getDefault(rootException, new Exception()));

		setUserMessage(userMessage);
		setLayer(layer);
	}

	private void setUserMessage(final String userMessage) {
		this.userMessage = TextHelper.applyTrim(userMessage);
	}

	private void setLayer(final Layer layer) {
		this.layer = ObjectHelper.getDefault(layer, Layer.GENERAL);
	}

	public String getUserMessage() {
		return userMessage;
	}

	public Layer getLayer() {
		return layer;
	}
}
