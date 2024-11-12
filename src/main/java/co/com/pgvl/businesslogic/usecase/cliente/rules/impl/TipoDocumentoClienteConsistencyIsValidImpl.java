package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import co.com.pgvl.businesslogic.usecase.cliente.rules.TipoDocumentoClienteConsistencyIsValid;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public final class TipoDocumentoClienteConsistencyIsValidImpl implements TipoDocumentoClienteConsistencyIsValid{

	@Override
	public void execute(String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
		
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 40)) {
		var userMessage = "El tipo de documento solo puede contener maximo 40 caracteres";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if(!TextHelper.cotainsOnlyLettersAndSpaces(data)) {
		var userMessage = "El tipo de documento solo puede tener letras y espacios";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage = "El tipo de documento no puede estar vacio";
			throw BusinessLogicPGVLException.crear(userMessage);
		}	
	}

}
