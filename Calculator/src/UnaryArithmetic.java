
public class UnaryArithmetic implements IArithmetic{
	int a;
	String op;
	
	UnaryArithmetic(String str){
		Parse(str);
	}
	
	public void Parse(String str){
		op = str.substring(0, str.indexOf('('));
		a = Integer.parseInt(str.substring(str.indexOf('(') + 1, str.indexOf(')')));
	}
	
	public int Calc() throws NegativeNumberException{
		switch (op)
		{
			case "sqr": return a * a;
			case "sqrt": if (a < 0) throw new NegativeNumberException(); return (int) Math.sqrt(a);
		}
		return 0;
	}
}
