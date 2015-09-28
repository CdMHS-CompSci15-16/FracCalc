//By Ryan Chan
//CDM AP Computer Science
public class Frac{ 						//Class for holding fraction data
	private int numerator, denominator; //Class variables for numerator and denominator
	public Frac(int num,int den){		//Constructor sets class variables to the constructors
		numerator = num;
		denominator = den;
	}
	int getNumerator(){					//Returns numerator
		return numerator;
	}
	int getDenominator(){				//Returns Denominator
		return denominator;
	}
	String getString(){					//Returns numerator and denominator in String format
		return numerator+"/"+denominator;
	}
	void setNumerator(int num){			//Accepts input by argument and changes numerator
		numerator=num;
	}
	void setDenominator(int den){		//Accepts input by argument and changes denominator
		denominator=den;
	}
}