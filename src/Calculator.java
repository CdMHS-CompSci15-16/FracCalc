//By Ryan Chan
//CDM AP Computer Science 2015
public class Calculator {							//Class for performing mathematical operations to fractions
	Frac add(Frac f1, Frac f2){						//Addition class
		int[] info=new int[3];						//Creates an array in order to receive data from the matchDenominator method
		System.arraycopy(matchDenominator(f1,f2),0,info,0,3);	//Gets new fraction data with matching denominator from the matchDenominator method
		int finalNumerator=info[1]+info[2];			//Adds the two numerators
		Frac finalFrac = new Frac(0,finalNumerator,info[0]);		//Creates a Frac object containing the sum of the two fractions
		return finalFrac;							//Returns final Frac object
	}
	Frac subtract(Frac f1, Frac f2){				//Subtraction class
		int[] info=new int[3];						//Creates an array in order to receive data from the matchDenominator method
		System.arraycopy(matchDenominator(f1,f2),0,info,0,3);	//Gets new fraction data with matching denominator from the matchDenominator method
		int finalNumerator=info[1]-info[2];			//Subtracts the two numerators
		Frac finalFrac = new Frac(0,finalNumerator,info[0]);		//Creates a Frac object containing the difference of the two fractions
		return finalFrac;							//Returns final Frac object
	}

	Frac multiply(Frac f1, Frac f2){				//Multiplication class
		Frac finalFrac = new Frac(0,f1.getNumerator()*f2.getNumerator(),f1.getDenominator()*f2.getDenominator());	//Multiplies the numerators and denominators of the two fractions
		return finalFrac;							//Returns final Frac object
	}
	Frac divide(Frac f1, Frac f2){					//Division class
		Frac finalFrac = new Frac(0,f1.getNumerator()*f2.getDenominator(),f1.getDenominator()*f2.getNumerator());	//Divides the two fractions
		return finalFrac;							//Returns the final Frac object
	}
	private int gcd(int n1, int n2){				//Class for finding greatest common denominator of two ints
		while (n2>0){								//Loop for finding GCD
			int placeholder=n2;
			n2=n1%n2;
			n1=placeholder;
		}
		return n1;									//Returns GCD as integer
	}
	private int lcm(int n1, int n2){				//Class for finding LCM of two integers
		int lcmresult= n1*(n2/gcd(n1,n2));			//Finds LCM using the GCD method
		return lcmresult;							//Returns LCM as integer
	}
	int[] matchDenominator(Frac f1, Frac f2){		//Class for setting the denominators of two fracions equal
		int[] infoArray=new int[3];					//Array for holding shared denominator and the new numerators for each fraction
		infoArray[0]=lcm(f1.getDenominator(),f2.getDenominator());	//Finds common denominator by finding LCM between the two original denominators
		int multiple1=infoArray[0]/f1.getDenominator();	//Finds the multiple needed for converting the numerator of the first fraction
		int multiple2=infoArray[0]/f2.getDenominator();	//Finds the multiple needed for converting the numerator of the second fraction
		infoArray[1]=multiple1*f1.getNumerator();		//Multiplies original numerator of fraction 1 by the multiple in order to find the new numerator
		infoArray[2]=multiple2*f2.getNumerator();		//Multiplies original numerator of fraction 2 by the multiple in order to find the new numerator
		return infoArray;							//Returns new denominator and two new numerators in an array
	}
	Frac simplify(Frac frac){						//Class for simplifying fractions
		int multiple=gcd(frac.getNumerator(),frac.getDenominator());	//Finds gcd between numerator and denominator
		int finalNumerator=frac.getNumerator()/multiple;				//Divides both numerator by GCD
		int finalDenominator=frac.getDenominator()/multiple;			//Divides denominator by GCD
		Frac finalFrac=new Frac(0,finalNumerator,finalDenominator);		//Creates new Frac object with new numerator and denominator
		return finalFrac;							//Returns the simplified fraction
	}
}
