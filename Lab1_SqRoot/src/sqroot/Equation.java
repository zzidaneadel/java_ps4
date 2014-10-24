package sqroot;

public class Equation 
{
    double a;
    double b;
    double c;
    Equation(double _a, double _b, double _c) 
    {
        a = _a;
        b = _b;
        c = _c;
        if (a == 0) throw new FirstCoefficientIsZeroException("first coefficient of quadratic equation is zero");
    }
    public void Calculate(ComplexNum root1, ComplexNum root2)
    {
    	double D = b*b - 4*a*c;
		if (D >= 0)
		{
			root1.re = (-b + Math.sqrt(D))/(2*a);
			root2.re = (-b - Math.sqrt(D))/(2*a);
			root1.im = 0.0; root2.im = 0.0;
		}
		else
		{
			root1.re = root2.re = -b/(2*a);
			root1.im = Math.sqrt(Math.abs(D))/(2*a);
			root2.im = -Math.sqrt(Math.abs(D))/(2*a);
		}
    return;
    }
}