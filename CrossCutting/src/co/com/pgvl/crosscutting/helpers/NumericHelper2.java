package co.com.pgvl.crosscutting.helpers;

public class NumericHelper2 {
	
	
	public static final <T extends Number> boolean  isGreat(T numberOne, T numberTwo) {
		return numberOne.doubleValue() > numberTwo.doubleValue();
	}
	
	public static final <T extends Number> boolean  isLess(T numberOne, T numberTwo) {
		return numberOne.doubleValue() < numberTwo.doubleValue();
	}
	
	public static final <T extends Number> boolean  isDiferent(T numberOne, T numberTwo) {
		return numberOne.doubleValue() != numberTwo.doubleValue();
	}
	
	public static final <T extends Number> boolean  isEqual(T numberOne, T numberTwo) {
		return numberOne.doubleValue() == numberTwo.doubleValue();
	}
	
	public static final <T extends Number> boolean  isGreatOrEqual(T numberOne, T numberTwo) {
		return numberOne.doubleValue() >= numberTwo.doubleValue();
	}
	
	public static final <T extends Number> boolean  isLessOrEqual(T numberOne, T numberTwo) {
		return numberOne.doubleValue() <= numberTwo.doubleValue();
	}
	
	public static final <T extends Number> boolean  isBetween(final T number, final T initialLimit, final T finalLiit, final boolean includeInitialLimit, final boolean includeFinalLimit) {
		return (includeInitialLimit?isGreatOrEqual(number, initialLimit):isGreat(number, initialLimit))&&(includeFinalLimit?isLessOrEqual(number, finalLiit):isLess(number, finalLiit));
	}


}
