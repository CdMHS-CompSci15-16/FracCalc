//By Ryan Chan
//CDM AP Computer Science
public class Frac { // Class for holding fraction data
	private int coefficient, numerator, denominator; // Class variables for
														// numerator,
														// denominator,
														// and coefficient

	public Frac(int coef, int num, int den) { // Constructor sets class
												// variables to the constructors
		numerator = num;
		denominator = den;
		coefficient = coef;
	}

	int getNumerator() { // Returns numerator
		return numerator;
	}

	int getDenominator() { // Returns Denominator
		return denominator;
	}

	int getCoefficient() { // Returns Coefficient
		return coefficient;
	}

	String getString() { // Returns numerator and denominator in String format
		if (coefficient == 0 && numerator == 0) {
			return "0";
		} else if (coefficient == 0) {
			return numerator + "/" + denominator;
		} else if (numerator == 0) {
			return Integer.toString(coefficient);
		} else {
			return coefficient + "_" + numerator + "/" + denominator;
		}
	}

	void setNumerator(int num) { // Accepts input by argument and changes
									// numerator
		numerator = num;
	}

	void setDenominator(int den) { // Accepts input by argument and changes
									// denominator
		denominator = den;
	}

	void setCoefficient(int coef) { // Accepts input by argument and changes
									// coefficient
		coefficient = coef;
	}

	void convertToImproper() { // Converts Fraction to improper form
		if (coefficient != 0) { // Only works if fraction is a mixed number
			numerator = numerator + (coefficient * denominator);
			coefficient = 0;
		}
	}

	void convertToMixed() { // Converts Fraction to a mixed number
		if (coefficient == 0) { // Only works if the fraction is improper
			coefficient = numerator / denominator;
			numerator = numerator % denominator;
		}
	}
}