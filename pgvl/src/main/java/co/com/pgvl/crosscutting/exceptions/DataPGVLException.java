package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;

public class DataPGVLException extends PGVLException {

	private static final long serialVersionUID = 1L;

	public DataPGVLException(final String userMessage, final String technicalMessage, final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.DATA);
	}

	public static final DataPGVLException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new DataPGVLException(userMessage, technicalMessage, rootException);
	}

	public static final DataPGVLException crear(final String userMessage) {
		return new DataPGVLException(userMessage, userMessage, new Exception());
	}

	public static final DataPGVLException crear(final String userMessage, final String technicalMessage) {
		return new DataPGVLException(userMessage, technicalMessage, new Exception());
	}

}
