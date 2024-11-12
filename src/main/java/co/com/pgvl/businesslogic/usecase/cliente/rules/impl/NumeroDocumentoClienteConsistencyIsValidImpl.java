package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import co.com.pgvl.businesslogic.usecase.cliente.rules.NumeroDocumentoClienteConsistencyIsValid;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public final class NumeroDocumentoClienteConsistencyIsValidImpl implements NumeroDocumentoClienteConsistencyIsValid{

	@Override
	public void execute(String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
		
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 32)) {
		var userMessage = "El numero de documento solo puede contener maximo 32 numeros";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if(!TextHelper.containsOnlyNumbers(data)) {
		var userMessage = "El numero de documento del cliente solo puede tener numeros";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage = "El numero de documento cliente no puede estar vacio";
			throw BusinessLogicPGVLException.crear(userMessage);
		}	
	}

}
