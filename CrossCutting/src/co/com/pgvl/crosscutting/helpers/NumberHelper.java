package co.com.pgvl.crosscutting.helpers;

public class NumberHelper {
	
	private NumberHelper() {
		
	}
	
	public static <N extends Number> boolean isNull(final N number) {
	    return number == null;
	}
	
	public static <N extends Number> N getDefault(final N number, final N defaultNumber) {
	    return isNull(number) ? defaultNumber : number;
	}
	
	public static <N extends Comparable<N>> boolean isLessOrEqual(final N number1, final N number2) {
	    return number1.compareTo(number2) <= 0;
	}
	
	public static <N extends Comparable<N>> boolean isGreaterOrEqual(final N number1, final N number2) {
	    return number1.compareTo(number2) >= 0;
	}
	
	public static <N extends Comparable<N>> boolean isDifferent(final N number1, final N number2) {
	    return number1.compareTo(number2) != 0;
	}
	
	public static <N extends Comparable<N>> boolean isInRangeWithLeftLimit(final N number, final N lowerLimit, final N upperLimit) {
	    return number.compareTo(lowerLimit) >= 0 && number.compareTo(upperLimit) < 0;
	}
	
	public static <N extends Comparable<N>> boolean isInRangeWithRightLimit(final N number, final N lowerLimit, final N upperLimit) {
	    return number.compareTo(lowerLimit) > 0 && number.compareTo(upperLimit) <= 0;
	}
	
	public static <N extends Number> boolean isPositive(final N number) {
	    return number.doubleValue() > 0;
	}
	
	public static <N extends Number> boolean isNegative(final N number) {
	    return number.doubleValue() < 0;
	}	
	
	

}
