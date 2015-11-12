//By Ryan Chan
//AP Computer Science 2015
import java.util.Scanner;

public class mainCalc {
	final static boolean SHOW_PROCESS=true;	//Toggle this to have the calculator show its process for solving larger expressions
	public static void main(String[] args) {
		System.out.println("Welcome to Fraction Calculator!");
		System.out.println("Written by Ryan Chan, by CDM AP Computer Science 2015");
		System.out.println("Extra-Credit edition");
		Scanner sc = new Scanner(System.in);
		String input;
		boolean continueLoop = true;
		while (continueLoop) {	//Loops the program until the user enters "quit"
			System.out.println("Please enter an expression, or enter \"quit\" to quit: ");
			input = sc.nextLine();	//Takes user input
			if (input.equals("quit") || input.equals("Quit")) { //If user enters "quit", the program will exit
				continueLoop = false;
				System.out.println("Thank you for using Fraction Calculator, have a nice day!");
			} else {
				System.out.println(parse(input)); //Calls the parse method, which will use order of operations to perform all necessary operations within the string. This value is then printed
			}
		}
		sc.close();
	}
	public static String parse(String input) { //Determines order of operation and executes operations accordingly
		int operatorPos = 0;
		int startPos = 0;
		int endPos = 0;
		String excerpt, firstHalf, secondHalf, combined = null;
		while (input.indexOf(" ") != -1) { //Loops until there is only one term in the string, which will be the final answer
			if ((input.indexOf("*") != -1)||(input.indexOf("/ ") != -1)) {	//Searches for multiplication and division first
				if ((input.indexOf("*")!=-1)&&input.indexOf("/ ")!=-1){	//Finds first instance of multiplication or division, if present
					operatorPos = Math.min(input.indexOf("*"),input.indexOf("/ "));
				} else if ((input.indexOf("*")!=-1)&&input.indexOf("/ ")==-1){
					operatorPos = input.indexOf("*");
				} else if ((input.indexOf("*")==-1)&&input.indexOf("/ ")!=-1){
					operatorPos=input.indexOf("/ ");
				}
			} else if ((input.indexOf("+") != -1)||(input.indexOf("- ") != -1)) {	//Searches for addition and subtraction if no multiplication or division remain
				if ((input.indexOf("+")!=-1)&&input.indexOf("- ")!=-1){	//Finds first instance of addition or subtration, if present
					operatorPos = Math.min(input.indexOf("+"),input.indexOf("- "));
				} else if ((input.indexOf("+")!=-1)&&input.indexOf("- ")==-1){
					operatorPos = input.indexOf("+");
				} else if ((input.indexOf("+")==-1)&&input.indexOf("- ")!=-1){
					operatorPos=input.indexOf("- ");
				}
			}

			if (input.lastIndexOf(" ", operatorPos - 2) != -1) {	//Checks if the number before the operator is the first in the string and records starting position
				startPos = input.lastIndexOf(" ", operatorPos - 2) + 1;	
			} else {
				startPos = 0;
			}
			if (input.indexOf(" ", operatorPos + 2) != -1) {	//Checks if the number after the operator is the last number and records end position
				endPos = input.indexOf(" ", operatorPos + 2);
			} else {
				endPos = input.length();
			}

			if (SHOW_PROCESS) {
				System.out.println("Now Operating: \t[" + input.substring(startPos, endPos) + "]");
			}

			firstHalf = input.substring(0, startPos);	//First half of string taken up until the starting point for the operation
			secondHalf = input.substring(endPos);	//Second half of string taken from ending point of the operation to string ending
			excerpt = input.substring(startPos, endPos);	//Operation string is taken between starting point and ending point

			combined = operate(excerpt);	//Operates on the excerpt

			input = firstHalf + combined + secondHalf;
			if (SHOW_PROCESS) {
				System.out.println("New String: \t" + input);
			}
		}
		return combined;
	}
	public static String operate(String input) {
		//Initializes variables for two fractions. Each fraction has a variable for its coefficient, numerator, and denominator
		int f1c=0;	//Coefficient
		int f1n=0;	//Numerator
		int f1d=1;	//Denominator
		
		int f2c=0;
		int f2n=0;
		int f2d=1;
		String temp;
		String result = null;
		int operatorPos = 0;
		int underscorePos = 0;
		int slashPos = 0;

		if (input.indexOf("+") != -1) {		//Finds the location of the operator and stores its position.
			operatorPos = input.indexOf("+");
		} else if (input.indexOf("- ") != -1) {
			operatorPos = input.indexOf("- ");
		} else if (input.indexOf("*") != -1) {
			operatorPos = input.indexOf("*");
		} else {
			operatorPos = input.indexOf("/ ");
		}

		if (input.lastIndexOf("_",  operatorPos) != -1) {	//If the first term is a mixed number, finds the coefficient
			underscorePos = input.lastIndexOf("_",  operatorPos);
			temp = input.substring(0,  underscorePos);	//Coefficient is located based on position of "_"
			f1c=(Integer.parseInt(temp));
		}
		if (input.lastIndexOf("/",  operatorPos-1) != -1) {	//If the first term contains a "/", and is therefore a fraction, finds the numerator and denominator
			slashPos = input.lastIndexOf("/",  operatorPos - 1);	//Finds position of "/"
			if (input.lastIndexOf("_",  operatorPos) != -1) {		//If the fraction has a coefficient, numerator is taken as the string between "_" and "/"
				temp = input.substring(underscorePos + 1,  slashPos);
			} else {
				temp = input.substring(0,  slashPos);	//If there is no coefficient, string is taken from index 0 to the "/"
			}
			f1n=(Integer.parseInt(temp));
			
			temp = input.substring(slashPos + 1,  operatorPos - 1);	//Denominator is taken as the string between "/" and operator
			f1d=(Integer.parseInt(temp));
		} else {
			temp = input.substring(0,  operatorPos - 1);	//If there is no fraction, then the whole number is stored as a coefficient
			f1c=(Integer.parseInt(temp));
		}

		if (input.indexOf("_",  operatorPos) != -1) {	//If the second term is a mixed number, finds the coefficient
			underscorePos = input.indexOf("_",  operatorPos);//Coefficient is located based on position of "_"
			temp = input.substring(operatorPos + 2,  underscorePos);
			f2c=(Integer.parseInt(temp));
		}
		
		if (input.indexOf("/",  operatorPos + 1) != -1) {	//If the second term contains a "/", and is therefore a fraction, finds the numerator and denominator
			slashPos = input.indexOf("/",  operatorPos + 1);	//Finds position of "/"
			if (input.indexOf("_",  operatorPos) != -1) {	//If the fraction has a coefficient, numerator is taken as the string between "_" and "/"
				temp = input.substring(underscorePos + 1,  slashPos);
			} else {
				temp = input.substring(operatorPos + 2,  slashPos);	//If there is no coefficient, string is taken from operator index to the "/"
			}
			f2n=(Integer.parseInt(temp));		
			temp = input.substring(slashPos + 1);	//Denominator is taken as the string between "/" and operator
			f2d=(Integer.parseInt(temp));
		} else {
			temp = input.substring(operatorPos + 2);	//If there is no fraction, then the whole number is stored as a coefficient
			f2c=(Integer.parseInt(temp));
		}		
		//Converts fraction from a mixed number to an inproper fraction
		f1n = f1n + (f1c * f1d);
		f1c = 0;
		f2n = f2n + (f2c * f2d);
		f2c = 0;
		if (input.indexOf("+") != -1) {	//Calls relevant arithmetic method depending on the operator
			result = add(f1n,f1d,f2n,f2d);
		} else if (input.indexOf("- ") != -1) {
			result = subtract(f1n,f1d,f2n,f2d);
		} else if (input.indexOf("*") != -1) {
			result = multiply(f1n,f1d,f2n,f2d);
		} else if (input.indexOf("/ ") != -1) {
			result = divide(f1n,f1d,f2n,f2d);
		} else {
			System.out.println("Error! Operator not present");
		}
		if (f1d==0||f2d==0){
			result = "Error:Divided_By_Zero";
		}
		return result;
	}
	public static String add(int f1n, int f1d, int f2n, int f2d){	
		String result;
		int f3n=matchDenominator(f1n,f1d,f2n,f2d,"f1n")+matchDenominator(f1n,f1d,f2n,f2d,"f2n");	//Multiplies each fraction by a constant so that they have equal denominators, then adds the numerators
		int f3d=matchDenominator(f1n,f1d,f2n,f2d,"denominator");	//Sets the denominator of the result to the equal denominator of both fraction inputs
		result=convertToMixed(f3n,f3d);	//Converts the resulting fraction to a mixed number
		return result;
	}
	public static String subtract(int f1n, int f1d, int f2n, int f2d){
		String result;
		int f3n=matchDenominator(f1n,f1d,f2n,f2d,"f1n")-matchDenominator(f1n,f1d,f2n,f2d,"f2n");	//Multiplies each fraction by a constant so that they have equal denominators, then subtracts the numerators
		int f3d=matchDenominator(f1n,f1d,f2n,f2d,"denominator");	//Sets the denominator of the result to the equal denominator of both fraction inputs
		result=convertToMixed(f3n,f3d);	//Converts the resulting fraction to a mixed number
		return result;
	}
	public static String multiply(int f1n, int f1d, int f2n, int f2d){
		String result;
		int f3n=f1n*f2n;//Multiplies the numerators
		int f3d=f1d*f2d;//Multiplies the denominators
		result = convertToMixed(f3n,f3d);	//Converts the resulting fraction to a mixed number
		return result;
}
	public static String divide(int f1n, int f1d, int f2n, int f2d){
		String result;
		if (f2n==0||f2d==0){
			result = "Error:Divide_By_Zero";
		} else {
			int f3n=f1n*f2d;//Multiplies the first fraction by the inverse of the second
			int f3d=f1d*f2n;
			result = convertToMixed(f3n,f3d);	//Converts the resulting fraction to a mixed number
		}
		return result;
}
	public static String convertToMixed(int numPreliminary, int denPreliminary){	//Creates a mixed number from an improper fraction
		int coefficient = numPreliminary / denPreliminary;	//Finds coefficient
		int numerator = simplify(numPreliminary,denPreliminary,"numerator");	//Simplifies numerator and denominator
		int denominator = simplify(numPreliminary,denPreliminary,"denominator");
		if (numerator<0&&coefficient<0){
			numerator=numerator*-1;
		}
		numerator = numerator % denominator;	//Sets numerator as remainder 
		if (coefficient == 0 && numerator == 0) {	//If the fraction is 0, returns 0
			return "0";
		} else if (coefficient == 0) {	//If the coefficient is 0, only the fraction is returned
			return numerator + "/" + denominator;
		} else if (numerator == 0) {	//If the numerator is 0, only the coefficient is returned
			return Integer.toString(coefficient);
		} else {	//If there is both a coefficient and a fraction, returns both
			return coefficient + "_" + numerator + "/" + denominator;
		}
	}
	public static int simplify(int numerator, int denominator, String slot){
		int multiple = gcf(Math.abs(numerator),Math.abs(denominator));	//Finds gcf of numerator and denominator
		numerator = numerator/multiple;	
		denominator = denominator/multiple;
		if (slot.equals("numerator")){	//Returns numerator if requested
			return numerator;
		} else if (slot.equals("denominator")){	//Returns denominator if requested
			return denominator;	
		} else{
			return 1241412; //This is just for debugging; it should never actually happen
		}
	}
	public static int gcf(int num1, int num2){				//Class for finding greatest common denominator of two ints
		while (num2>0){								//Loop for finding GCF
			int temp=num2;
			num2=num1%num2;
			num1=temp;
		}
		return num1;									//Returns GCF as integer
	}
	public static int lcm(int n1, int n2){				//Class for finding LCM of two integers
		int lcmResult= (n2/gcf(n1,n2)*n1);			//Finds LCM using the GCF method
		return lcmResult;							//Returns LCM as integer
	}
	public static int matchDenominator(int f1n, int f1d, int f2n, int f2d, String placeToReturn){	//Matches the denominators of two fractions by multiplying by constants, then the requested number is returned
		int newDenominator= lcm(f1d,f2d);	//New denominator is the LCM of the two
		int multiple1 = newDenominator/f1d;	//Finds constant for fraction 1
		int multiple2 = newDenominator/f2d;	//Finds constant for fraction 2
		int f1NewNumerator=multiple1*f1n;	//Finds new numerator for fraction 1 by multiplying by the previously found constant
		int f2NewNumerator=multiple2*f2n;	//Finds new numerator for fraction 2 by multiplying by the previously found constant
		
		if (placeToReturn.equals("f1n")){	//Returns information based on method parameters
			return f1NewNumerator;
		} else if (placeToReturn.equals("f2n")){
			return f2NewNumerator;
		} else if (placeToReturn.equals("denominator")){
			return newDenominator;
		}
		else {
			return -1;
		}
	}
}