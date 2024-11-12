package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import co.com.pgvl.businesslogic.usecase.cliente.rules.NumeroCelularClienteConsistencyIsValid;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;

public final class NumeroCelularClienteConsistencyIsValidImpl implements NumeroCelularClienteConsistencyIsValid {

	@Override
	public void execute(String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
		
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 20)) {
		var userMessage = "El numero celular del cliente solo puede contener maximo  20 caracteres";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if(!TextHelper.containsOnlyNumbers(data)) {
		var userMessage = "El numero de celular del cliente solo puede tener numeros";
		throw BusinessLogicPGVLException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if(TextHelper.isEmpty(data)) {
			var userMessage = "El numero de celular del cliente no puede estar vacio";
			throw BusinessLogicPGVLException.crear(userMessage);
		}
		
	}

}
