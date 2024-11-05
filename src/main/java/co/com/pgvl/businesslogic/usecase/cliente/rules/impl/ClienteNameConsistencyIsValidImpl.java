package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import co.com.pgvl.businesslogic.usecase.cliente.rules.ClienteNameConsistencyIsValid;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public final class ClienteNameConsistencyIsValidImpl implements ClienteNameConsistencyIsValid{

	@Override
	public void execute(final String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
		
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 50)) {
		var userMessage = "El nombre de la ciudad solo puede contener maximo 50 caracteres";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if(!TextHelper.cotainsOnlyLettersAndSpaces(data)) {
		var userMessage = "El nombre de la ciudad solo puede tener letras y espacios";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage = "El nombre de la ciudad no puede estar vacio";
			throw BusinessLogicPGVLException.crear(userMessage);
		}
	}

}
