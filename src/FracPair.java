//By Ryan Chan

//This class exists for the sole purpose of holding two Frac objects and an operation.
//This is used to return input data to the main method
public class FracPair{
	public final Frac f1;
	public final Frac f2;
	public final int operation;
	
	public FracPair(Frac frac1, Frac frac2, int op){
		this.f1 = frac1;
		this.f2 = frac2;
		this.operation = op;
	}
}
