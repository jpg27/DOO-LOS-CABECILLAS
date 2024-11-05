package co.com.pgvl.crosscutting.exceptions;

import co.com.pgvl.crosscutting.exceptions.enums.Layer;

public class EntityPGVLException extends PGVLException {

	private static final long serialVersionUID = 1L;

	public EntityPGVLException(final String userMessage, final String technicalMessage, final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.ENTITY);
	}

	public static final EntityPGVLException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new EntityPGVLException(userMessage, technicalMessage, rootException);
	}

	public static final EntityPGVLException crear(final String userMessage) {
		return new EntityPGVLException(userMessage, userMessage, new Exception());
	}

	public static final EntityPGVLException crear(final String userMessage, final String technicalMessage) {
		return new EntityPGVLException(userMessage, technicalMessage, new Exception());
	}

}
