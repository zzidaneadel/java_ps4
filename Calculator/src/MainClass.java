
public class MainClass {

	public static void main(String[] args) {
		
		Parser parser = new Parser();
		IArithmetic ar1 = parser.Parse("8-2");
		
		try {
			System.out.println(ar1.Calc());
		}
		catch (ArithmeticException e1){			
			System.out.println("Деление на ноль");
		}
		catch (NegativeNumberException e2){
			System.out.println("Квадратный корень из отрицательного числа");
		}
		catch (NullPointerException e3){
			System.out.println("Неверный ввод");
		}
	}

}
