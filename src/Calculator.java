public class Calculator {
	Frac add(Frac f1, Frac f2){//NOT YET FINISHED
		int[] info=new int[3];
		System.arraycopy(matchDenominator(f1,f2),0,info,0,3);
		int finalNumerator=info[1]+info[2];
		Frac finalFrac = new Frac(finalNumerator,info[0]);
		return finalFrac;
	}
	Frac subtract(Frac f1, Frac f2){
		int[] info=new int[3];
		System.arraycopy(matchDenominator(f1,f2),0,info,0,3);
		int finalNumerator=info[1]-info[2];
		Frac finalFrac = new Frac(finalNumerator,info[0]);
		return finalFrac;
	}

	Frac multiply(Frac f1, Frac f2){
		Frac finalFrac = new Frac(f1.getNumerator()*f2.getNumerator(),f1.getDenominator()*f2.getDenominator());
		return finalFrac;
	}
	Frac divide(Frac f1, Frac f2){
		Frac finalFrac = new Frac(f1.getNumerator()*f2.getDenominator(),f1.getDenominator()*f2.getNumerator());
		return finalFrac;
	}
	private int gcd(int n1, int n2){
		while (n2>0){
			int placeholder=n2;
			n2=n1%n2;
			n1=placeholder;
		}
		return n1;
	}
	private int lcm(int n1, int n2){
		int lcmresult= n1*(n2/gcd(n1,n2));
		return lcmresult;
	}
	int[] matchDenominator(Frac f1, Frac f2){
		int[] infoArray=new int[3];
		infoArray[0]=lcm(f1.getDenominator(),f2.getDenominator());
		int multiple1=infoArray[0]/f1.getDenominator();
		int multiple2=infoArray[0]/f2.getDenominator();
		infoArray[1]=multiple1*f1.getNumerator();
		infoArray[2]=multiple2*f2.getNumerator();
		return infoArray;
	}
	Frac simplify(Frac frac){
		int multiple=gcd(frac.getNumerator(),frac.getDenominator());
		int finalNumerator=frac.getNumerator()/multiple;
		int finalDenominator=frac.getDenominator()/multiple;
		Frac finalFrac=new Frac(finalNumerator,finalDenominator);
		return finalFrac;
	}
}
