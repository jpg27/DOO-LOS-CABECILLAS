package co.com.pgvl.crosscutting.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.com.pgvl.crosscutting.helpers.ObjectHelper;

class HelpersUnitTestCase {

	@Test
	void givenWantValidateIfObjectIsNullWhenObjectIsNullThenResultIsTrue() {
		//arrange:initial context
		String object = null;
		
		
		//act
		boolean result = ObjectHelper.isNull(object);
		
		
		//assert
		assertTrue(result);
	}
	
	@Test
	void givenWantValidateIfObjectIsNotNullWhenObjectIsNotNullThenResultIsFalse() {
		//arrange:initial context
		var object = "";
		
		
		//act: execute action
		boolean result = ObjectHelper.isNull(object);
		
		
		//assert: validate result
		assertFalse(result);
	}
	
	@Test
	void givenWantGetOriginalValueWhenObjectIsNotNullThenResultIsTheSameObject() {
		//arrange:initial context
		var object = "Casa";
		var defaultValue = "Default";
		
		
		//act: execute action
		var result = ObjectHelper.getDefault(object, defaultValue);
		
		
		//assert: validate result
		assertEquals(object, result);
	}
	
	@Test
	void givenWantGetDefaultlValueWhenObjectIsNullThenResultIsDefaultObject() {
		//arrange:initial context
		String object = null;
		var defaultValue = "Default";
		
		
		//act: execute action
		var result = ObjectHelper.getDefault(object, defaultValue);
		
		
		//assert: validate result
		assertEquals(defaultValue, result);
	}

}
