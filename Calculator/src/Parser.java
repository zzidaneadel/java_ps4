
public class Parser {
	public IArithmetic Parse(String str){
		if (str.matches("\\-?[0-9]+[+|\\-|*|/][0-9]+")){
			BinaryArithmetic ba = new BinaryArithmetic(str);
			return ba;
		}
		else
			if (str.matches("(sqr|sqrt)\\(\\-?[0-9]+\\)")){
				UnaryArithmetic ua = new UnaryArithmetic(str);
				return ua;
			}
			else
				return null;
	}
	
}
