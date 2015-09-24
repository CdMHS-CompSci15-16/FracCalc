import java.util.Scanner;

public class MainCalc {
	public static void main(String[] args){
		Calculator calc = new Calculator();
		System.out.println("Welcome to Frac Calc, by Ryan Chan. Type /help for info on usage");
		
		System.out.println("Please enter your statement: ");
		FracPair pair=inputFrac();
		Frac resultFrac = new Frac(0,0);
		if (pair.operation==1){
			resultFrac=calc.add(pair.f1,pair.f2);
		}
		else if (pair.operation==2){
			resultFrac=calc.subtract(pair.f1,pair.f2);
		}
		else if (pair.operation==3){
			resultFrac=calc.multiply(pair.f1,pair.f2);
		}
		else if (pair.operation==4){
			resultFrac=calc.divide(pair.f1,pair.f2);
		}
		/*else if (pair.operation==5){
			System.out.println("a");
			break;
		*/
		Frac finalFrac = calc.simplify(resultFrac);
		System.out.println("Your answer is: "+finalFrac.getString());
		}
	static FracPair inputFrac(){
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		int operation = 0; //1=addition, 2=subtraction, 3=multiplication, 4=division
		
		int indexCount=0;							//Searches for the number of '/' in the input string
		int index = input.indexOf("/");
		while (index != -1){
			index = input.indexOf("/",index);
			indexCount ++;
			
			if(index!=-1){
				index +=1;
			}
		}
		
		if (input.indexOf("+")!=-1){
			operation=1;
		}
		else if (input.indexOf("-")!=-1){
			operation=2;
		}
		else if (input.indexOf("*")!=-1){
			operation=3;
		}
		else if (indexCount>3){
			operation=4;
		}
		else if (input.indexOf("/help")!=-1){
			System.out.println("To use, type two fractions seperated by an operator");
			System.out.println("Fractions are represented as [numerator]/[denominator]");
			System.out.println("Valid operators are +,-,*,/");
			System.out.println("--------EXAMPLE--------");
			System.out.println("\t1/2 + 1/3");
			System.out.println("Will result in an answer of 5/6");
			FracPair pair = new FracPair(null,null,5);
			return pair;
		}
		else {
			System.out.println("Invalid statement, operation not specified");
			System.exit(0);
		}
		
		input = input.replaceAll("\\s+", "");		
		
		Scanner fracFilter = new Scanner(input);
		fracFilter.useDelimiter("/|\\+|\\-|\\*");
		int numerator1,numerator2,denominator1,denominator2;
		
		numerator1=fracFilter.nextInt();
		denominator1=fracFilter.nextInt();
		numerator2=fracFilter.nextInt();
		denominator2=fracFilter.nextInt();
		fracFilter.close();

		Frac frac1 = new Frac(numerator1,denominator1);
		Frac frac2 = new Frac(numerator2,denominator2);
		FracPair pair = new FracPair(frac1,frac2,operation);
		return pair;
	}
}