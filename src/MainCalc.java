
//By Ryan Chan
//CDM AP Computer Science 2015
import java.util.*;

public class MainCalc {
	final static boolean SHOW_PROCESS = true; // Toggle this to display each
												// step of the solving process.
												// This will have no effect on
												// the resulting answer.

	public static void main(String[] args) {
		System.out.println("Welcome to Fraction Calculator!");
		System.out.println("Written by Ryan Chan, CDM AP Computer Science 2015");
		Scanner sc = new Scanner(System.in);
		Frac finalFrac = new Frac(0, 0, 1);
		String input;
		boolean continueLoop = true;
		// input = "2_1/3 / 6_2/5 + 3_1/3 - 5 * 3/2";
		while (continueLoop) {
			System.out.println("Please enter an expression, or enter \"quit\" to quit: ");
			input = sc.nextLine();
			if (input.equals("quit") || input.equals("Quit")) {
				continueLoop = false;
				System.out.println("Thank you for using Fraction Calculator, have a nice day!");
			} else {
				finalFrac = parse(input);
				finalFrac.convertToMixed();
				System.out.println("Your result is: " + finalFrac.getString());
			}
		}
		sc.close();
	}

	public static Frac operate(String input) {
		Calculator calc = new Calculator();
		Frac finalFrac = new Frac(0, 0, 1);
		Frac frac1 = new Frac(0, 0, 1);
		Frac frac2 = new Frac(0, 0, 1);
		String temp;
		int operatorPos = 0;
		int underscorePos = 0;
		int slashPos = 0;

		if (input.indexOf("+") != -1) {
			operatorPos = input.indexOf("+");
		} else if (input.indexOf("- ") != -1) {
			operatorPos = input.indexOf("- ");
		} else if (input.indexOf("*") != -1) {
			operatorPos = input.indexOf("*");
		} else {
			operatorPos = input.indexOf("/ ");
		}

		if (input.lastIndexOf("_", operatorPos) != -1) {
			underscorePos = input.lastIndexOf("_", operatorPos);
			temp = input.substring(0, underscorePos);
			frac1.setCoefficient(Integer.parseInt(temp));
		}
		if (input.lastIndexOf("/", operatorPos-1) != -1) {
			slashPos = input.lastIndexOf("/", operatorPos - 1);
			if (input.lastIndexOf("_", operatorPos) != -1) {
				temp = input.substring(underscorePos + 1, slashPos);
			} else {
				temp = input.substring(0, slashPos);
			}
			frac1.setNumerator(Integer.parseInt(temp));
			temp = input.substring(slashPos + 1, operatorPos - 1);
			frac1.setDenominator(Integer.parseInt(temp));
		} else {
			temp = input.substring(0, operatorPos - 1);
			frac1.setCoefficient(Integer.parseInt(temp));
		}

		if (input.indexOf("_", operatorPos) != -1) {
			underscorePos = input.indexOf("_", operatorPos);
			temp = input.substring(operatorPos + 2, underscorePos);
			frac2.setCoefficient(Integer.parseInt(temp));
		}
		if (input.indexOf("/", operatorPos + 1) != -1) {
			slashPos = input.indexOf("/", operatorPos + 1);
			if (input.indexOf("_", operatorPos) != -1) {
				temp = input.substring(underscorePos + 1, slashPos);
			} else {
				temp = input.substring(operatorPos + 2, slashPos);
			}
			frac2.setNumerator(Integer.parseInt(temp));
			temp = input.substring(slashPos + 1);
			frac2.setDenominator(Integer.parseInt(temp));
		} else {
			temp = input.substring(operatorPos + 2);
			frac2.setCoefficient(Integer.parseInt(temp));
		}
		frac1.convertToImproper();
		frac2.convertToImproper();
		if (input.indexOf("+") != -1) {
			finalFrac = calc.add(frac1, frac2);
		} else if (input.indexOf("- ") != -1) {
			finalFrac = calc.subtract(frac1, frac2);
		} else if (input.indexOf("*") != -1) {
			finalFrac = calc.multiply(frac1, frac2);
		} else if (input.indexOf("/") != -1) {
			finalFrac = calc.divide(frac1, frac2);
		} else {
			System.out.println("Error! Operator not present");
		}
		return finalFrac;
	}

	public static Frac parse(String input) {
		int operatorPos = 0;
		int startPos = 0;
		int endPos = 0;
		String excerpt, firstHalf, secondHalf;
		Frac combinedFrac = new Frac(0, 0, 1);

		while (input.indexOf(" ") != -1) {
			if ((input.indexOf("*") != -1)||(input.indexOf("/ ") != -1)) {
				if ((input.indexOf("*")!=-1)&&input.indexOf("/ ")!=-1){
					operatorPos = Math.min(input.indexOf("*"),input.indexOf("/ "));
				} else if ((input.indexOf("*")!=-1)&&input.indexOf("/ ")==-1){
					operatorPos = input.indexOf("*");
				} else if ((input.indexOf("*")==-1)&&input.indexOf("/ ")!=-1){
					operatorPos=input.indexOf("/ ");
				}
			} else if ((input.indexOf("+") != -1)||(input.indexOf("- ") != -1)) {
				if ((input.indexOf("+")!=-1)&&input.indexOf("- ")!=-1){
					operatorPos = Math.min(input.indexOf("+"),input.indexOf("- "));
				} else if ((input.indexOf("+")!=-1)&&input.indexOf("- ")==-1){
					operatorPos = input.indexOf("+");
				} else if ((input.indexOf("+")==-1)&&input.indexOf("- ")!=-1){
					operatorPos=input.indexOf("- ");
				}
			}

			if (input.lastIndexOf(" ", operatorPos - 2) != -1) {
				startPos = input.lastIndexOf(" ", operatorPos - 2) + 1;
			} else {
				startPos = 0;
			}
			if (input.indexOf(" ", operatorPos + 2) != -1) {
				endPos = input.indexOf(" ", operatorPos + 2);
			} else {
				endPos = input.length();
			}

			if (SHOW_PROCESS) {
				System.out.println("Now Operating: \t|" + input.substring(startPos, endPos) + "|");
			}

			firstHalf = input.substring(0, startPos);
			secondHalf = input.substring(endPos);
			excerpt = input.substring(startPos, endPos);

			combinedFrac = operate(excerpt);

			input = firstHalf + combinedFrac.getString() + secondHalf;
			if (SHOW_PROCESS) {
				System.out.println("New String: \t" + input);
			}
		}
		return combinedFrac;
	}

}
