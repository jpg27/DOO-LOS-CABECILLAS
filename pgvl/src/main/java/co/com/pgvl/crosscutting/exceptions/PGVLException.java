package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;

public class PGVLException extends PGVLApplicationException {

	private static final long serialVersionUID = 1L;

	public PGVLException(final String userMessage, final String technicalMessage, final Exception rootException,
			final Layer layer) {
		super(userMessage, technicalMessage, rootException, layer);
	}

	public static PGVLException crear(final String userMessage, final String technicalMessage,
			final Exception rootException, final Layer layer) {
		return new PGVLException(userMessage, technicalMessage, rootException, layer);
	}

	public static PGVLException crear(final String userMessage) {
		return new PGVLException(userMessage, userMessage, new Exception(), Layer.GENERAL);
	}

	public static PGVLException crear(final String userMessage, final String technicalMessage) {
		return new PGVLException(userMessage, technicalMessage, new Exception(), Layer.GENERAL);
	}

}
