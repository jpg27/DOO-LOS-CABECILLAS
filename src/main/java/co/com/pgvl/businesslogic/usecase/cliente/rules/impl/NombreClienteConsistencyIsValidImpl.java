package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import co.com.pgvl.businesslogic.usecase.cliente.rules.NombreClienteConsistencyIsValid;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public final class NombreClienteConsistencyIsValidImpl implements NombreClienteConsistencyIsValid{

	@Override
	public void execute(final String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
		
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 60)) {
		var userMessage = "El nombre del cliente solo puede contener maximo  60 caracteres";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if(!TextHelper.cotainsOnlyLettersAndSpaces(data)) {
		var userMessage = "El nombre del cliente solo puede tener letras y espacios";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage = "El nombre del cliente no puede estar vacio";
			throw BusinessLogicPGVLException.crear(userMessage);
		}
	}

}
