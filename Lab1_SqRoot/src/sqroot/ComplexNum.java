package sqroot;

public class ComplexNum 
{
	public Double re;
	public Double im;
	private static double delta = 0.000001;
	ComplexNum()
	{
		re = 0.0; im = 0.0;
	}
	ComplexNum(double re, double im)
	{
		this.re = re; this.im = im;
	};
	public String toString()
	{
		if (im == 0)
			return re.toString();
		else 
		{
			return (re + ((im > 0) ? "+" : "") + im + "i");
		}
	}
	@Override
	public boolean equals(Object obj)
	{
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
		 return ((Math.abs(this.re - ((ComplexNum)obj).re) < delta) && (Math.abs(this.im - ((ComplexNum)obj).im) < delta));
	}
}
