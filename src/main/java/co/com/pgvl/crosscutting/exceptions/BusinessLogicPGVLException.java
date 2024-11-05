package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;

public class BusinessLogicPGVLException extends PGVLException {

	private static final long serialVersionUID = 1L;

	public BusinessLogicPGVLException(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.BUSINESSLOGIC);
	}

	public static final BusinessLogicPGVLException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new BusinessLogicPGVLException(userMessage, technicalMessage, rootException);
	}

	public static final BusinessLogicPGVLException crear(final String userMessage) {
		return new BusinessLogicPGVLException(userMessage, userMessage, new Exception());
	}

	public static final BusinessLogicPGVLException crear(final String userMessage, final String technicalMessage) {
		return new BusinessLogicPGVLException(userMessage, technicalMessage, new Exception());
	}

}
