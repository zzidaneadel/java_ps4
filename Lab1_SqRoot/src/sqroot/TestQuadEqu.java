package sqroot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestQuadEqu {
	ComplexNum cn1 = new ComplexNum(); 
	ComplexNum cn2 = new ComplexNum();
	
	@Test 
	public void test_D_is_zero() 
	{
		Equation eq = new Equation (1, 2, 1);
		ComplexNum check = new ComplexNum(-1.0, 0);
		eq.Calculate(cn1, cn2);
		assertEquals(check, cn1); assertEquals(check, cn2); 
	}
	
	@Test
	public void test_D_is_positive()
	{
		Equation eq = new Equation(1, 2, -4);
		ComplexNum check1 = new ComplexNum(1.2360679774, 0);
		ComplexNum check2 = new ComplexNum(-3.2360679774, 0);
		eq.Calculate(cn1, cn2);
		assertEquals(check1, cn1); assertEquals(check2, cn2); 
	}
	
	@Test
	public void test_D_is_negative()
	{
		Equation eq = new Equation(1, 2, 4);
		ComplexNum check1 = new ComplexNum(-1.0, 1.732050807);
		ComplexNum check2 = new ComplexNum(-1.0, -1.732050807);
		eq.Calculate(cn1, cn2);
		assertEquals(check1, cn1); assertEquals(check2, cn2); 
	}
	
	@Test (expected = FirstCoefficientIsZeroException.class)
	public void test_first_coeff_is_zero()
	{
		Equation eq = new Equation(0, 2, 4);
	}
}
