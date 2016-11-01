
public class BinaryArithmetic implements IArithmetic{
	int a, b;
	char op;
	String operations = "+-*/";
	
	BinaryArithmetic(String str){
		Parse(str);
	}
	
	public void Parse(String str){
		int op_pos = 0;
		for (int i = 0; i < operations.length(); i++)
		{
			op_pos = str.indexOf(operations.charAt(i));
			if (op_pos != -1)
			{
				op = operations.charAt(i);
				break;
			}
		}
		
		a = Integer.parseInt(str.substring(0, op_pos));
		b = Integer.parseInt(str.substring(op_pos+1, str.length()));
	}
	
	public int Calc(){
		switch (op)
		{
			case '+': return a + b;
			case '-': return a - b;
			case '*': return a * b;
			case '/': return a / b;
		}
		return 0;
	}
}
