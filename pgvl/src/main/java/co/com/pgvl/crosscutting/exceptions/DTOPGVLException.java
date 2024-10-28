package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;

public class DTOPGVLException extends PGVLException {

	private static final long serialVersionUID = 1L;

	public DTOPGVLException(final String userMessage, final String technicalMessage, final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.DTO);
	}

	public static final DTOPGVLException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new DTOPGVLException(userMessage, technicalMessage, rootException);
	}

	public static final DTOPGVLException crear(final String userMessage) {
		return new DTOPGVLException(userMessage, userMessage, new Exception());
	}

	public static final DTOPGVLException crear(final String userMessage, final String technicalMessage) {
		return new DTOPGVLException(userMessage, technicalMessage, new Exception());
	}

}
