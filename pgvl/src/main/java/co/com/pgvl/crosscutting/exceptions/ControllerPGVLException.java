package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;

public class ControllerPGVLException extends PGVLException {

	private static final long serialVersionUID = 1L;

	public ControllerPGVLException(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.CONTROLLER);
	}

	public static final ControllerPGVLException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new ControllerPGVLException(userMessage, technicalMessage, rootException);
	}

	public static final ControllerPGVLException crear(final String userMessage) {
		return new ControllerPGVLException(userMessage, userMessage, new Exception());
	}

	public static final ControllerPGVLException crear(final String userMessage, final String technicalMessage) {
		return new ControllerPGVLException(userMessage, technicalMessage, new Exception());
	}

}
