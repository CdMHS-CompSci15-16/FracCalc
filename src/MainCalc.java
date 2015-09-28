//By Ryan Chan
//Fraction Calculator
//CDM AP Computer Science, 2015
import java.util.Scanner; 								//Imports the Scanner class, used for user input

public class MainCalc {
	public static void main(String[] args){ 			//Main method
		Calculator calc = new Calculator(); 			//Creates new Calculator object
		
		System.out.println("Welcome to Frac Calc, by Ryan Chan. Type /help for info on usage");		
		System.out.println("Please enter your statement: ");
		FracPair pair=inputFrac(); 						//Executes the inputFrac method, which grabs user input and filters it. Data is returned in the form of a FracPair object, which holds two fractions and an operation
		Frac resultFrac = new Frac(0,0);				//Initializes a Frac object, to hold the resulting fraction.
		
		if (pair.operation==1){ 						//Adds the two Frac objects returned by inputFrac()
			resultFrac=calc.add(pair.f1,pair.f2);
		}
		else if (pair.operation==2){					//Finds the difference between the two Frac objects returned by inputFrac()
			resultFrac=calc.subtract(pair.f1,pair.f2);
		}
		else if (pair.operation==3){					//Multiplies the two Frac objects returned by inputFrac()
			resultFrac=calc.multiply(pair.f1,pair.f2);
		}
		else if (pair.operation==4){					//Divides the two Frac objects returned by inputFrac()
			resultFrac=calc.divide(pair.f1,pair.f2);
		}
		/*else if (pair.operation==5){
			System.out.println("a");
			break;
		*/
		Frac finalFrac = calc.simplify(resultFrac); 	//Simplifies the resulting Fraction
		System.out.println("Your answer is: "+finalFrac.getString());	//Prints out final answer
	}
	static FracPair inputFrac(){
		Scanner sc = new Scanner(System.in);			//Initializes new Scanner
		String input = sc.nextLine();					//Grabs user input
		sc.close();										//Closes scanner object
		int operation = 0; 								//Represents which operation will be used. 1=addition, 2=subtraction, 3=multiplication, 4=division
		
			
		//Searches for the number of '/' in the input string, in order to determine if divison will be used
		int indexCount=0; 								//Initializes variable for counting the number of "/"
		int index = input.indexOf("/"); 				//.indexOf will return -1 if no "/" is found. If not, it will return the placement of the next "/
		while (index != -1){
			index = input.indexOf("/",index); 			//Searches for the next instance of "/", starting at the place of the previous "/"
			indexCount ++; 								//Increases the index count by 1
			if(index!=-1){
				index +=1;								//Increases the index by 1, so that .indexOf will return the next "/"
			}
		}
		
		if (input.indexOf("+")!=-1){ 					//If the input string contains a "+", set operation to addition
			operation=1;
		}
		else if (input.indexOf("-")!=-1){				//If the input string contains a "-", set operation to subtraction
			operation=2;
		}
		else if (input.indexOf("*")!=-1){				//If the input string contains a "*", set operation to multiplication
			operation=3;
		}
		else if (indexCount>=3){						//If number of "/" in the String is greater than or equal to 3, then operation is set to division
			operation=4;
		}
		else if (input.indexOf("/help")!=-1){ 			//If input contains /help, print out this
			System.out.println("To use, type two fractions seperated by an operator");
			System.out.println("Fractions are represented as [numerator]/[denominator]");
			System.out.println("Valid operators are +,-,*,/");
			System.out.println("--------EXAMPLE--------");
			System.out.println("\t1/2 + 1/3");
			System.out.println("Will result in an answer of 5/6");
			FracPair pair = new FracPair(null,null,5);
			return pair; 								//Returns an empty FracPair with operation 5
		}
		else { 											//If no operator is found, print error message and exit program
			System.out.println("Invalid statement, operation not specified");
			System.exit(0);
		}
		
		input = input.replaceAll("\\s+", ""); 			//Removes all spaces in the input
		
		Scanner fracFilter = new Scanner(input);		//Initialize new Scanner with the edited input string
		fracFilter.useDelimiter("/|\\+|\\-|\\*"); 		//Sets "+","-","*",and "/" as delimiters, which serve to seperate sections of a string.
		int numerator1,numerator2,denominator1,denominator2; 	//Initializes integers for holding data
		
		numerator1=fracFilter.nextInt(); 				//Takes next integer as numerator 1
		denominator1=fracFilter.nextInt(); 				//Takes next integer as denominator 1
		numerator2=fracFilter.nextInt(); 				//Takes next integer as numerator 2
		denominator2=fracFilter.nextInt(); 				//Takes next integer as denominator 2
		fracFilter.close();								//Closes scanner object

		Frac frac1 = new Frac(numerator1,denominator1);	//Creates two Frac objects with the numerators and denominators
		Frac frac2 = new Frac(numerator2,denominator2);
		FracPair pair = new FracPair(frac1,frac2,operation);	//Creates a FracPair object that holds the two Frac objects and an integer to signal operation
		return pair; 									//Returns the FracPair 'pair'
	}
}