public class Frac{
	private int numerator, denominator;
	public Frac(int num,int den){
		numerator = num;
		denominator = den;
	}
	int getNumerator(){
		return numerator;
	}
	int getDenominator(){
		return denominator;
	}
	String getString(){
		return numerator+"/"+denominator;
	}
	void setNumerator(int num){
		numerator=num;
	}
	void setDenominator(int den){
		denominator=den;
	}
}