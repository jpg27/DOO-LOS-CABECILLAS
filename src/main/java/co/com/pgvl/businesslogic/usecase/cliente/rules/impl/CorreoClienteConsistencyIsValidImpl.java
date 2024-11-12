package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import co.com.pgvl.businesslogic.usecase.cliente.rules.CorreoClienteConsistencyIsValid;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public final class CorreoClienteConsistencyIsValidImpl implements CorreoClienteConsistencyIsValid {

	@Override
	public void execute(String data) {
		validateNotNull(data);
		validateLen(data);
		
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 60)) {
		var userMessage = "El correo del cliente solo puede contener maximo 60 caracteres";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage = "El correo del cliente no puede estar vacio";
			throw BusinessLogicPGVLException.crear(userMessage);
		}
	}

}
