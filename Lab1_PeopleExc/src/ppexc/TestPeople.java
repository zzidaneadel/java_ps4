package ppexc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class TestPeople {
	
	People testpeople = new People();
	ArrayList<Person> addcollect = new ArrayList<Person>();
	
	@Before
	public void setUp() throws Exception {
		testpeople.add(new Person("Samuel", "Eto'O", 35));
		testpeople.add(new Person("Alan", "Dzagoev", 23));
		testpeople.add(new Person("Leonid", "Kuchuk", 56));
		
		addcollect.add(new Person("Gareth", "Bale", 25));
		addcollect.add(new Person("Edwin", "van der Sar", 43));
	}

	@Test
	public void test_add() throws WrongAgeValueException {
		testpeople.add(new Person("Halil", "Altintop", 34));
		assertEquals("Samuel Eto'O, 35 years\n"
				+ "Alan Dzagoev, 23 years\n"
				+ "Leonid Kuchuk, 56 years\n"
				+ "Halil Altintop, 34 years\n", 
						testpeople.toString());
	}
	
	@Test
	public void test_add_index() throws WrongAgeValueException
	{
		testpeople.add(2, new Person("Halil", "Altintop", 34));
		assertEquals("Samuel Eto'O, 35 years\n"
				+ "Alan Dzagoev, 23 years\n"
				+ "Halil Altintop, 34 years\n"
				+ "Leonid Kuchuk, 56 years\n",
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_add_index_out_of_bounds()
	{
		testpeople.add(6, new Person());
	}
	
	@Test
	public void test_addAll() throws WrongAgeValueException
	{
		testpeople.addAll(addcollect);
		assertEquals("Samuel Eto'O, 35 years\n"
				+ "Alan Dzagoev, 23 years\n"
				+ "Leonid Kuchuk, 56 years\n"
				+ "Gareth Bale, 25 years\n"
				+ "Edwin van der Sar, 43 years\n",
						testpeople.toString());
	}
	
	@Test
	public void test_addAll_index()
	{
		testpeople.addAll(2, addcollect);
		assertEquals("Samuel Eto'O, 35 years\n"
				+ "Alan Dzagoev, 23 years\n"
				+ "Gareth Bale, 25 years\n"
				+ "Edwin van der Sar, 43 years\n"
				+ "Leonid Kuchuk, 56 years\n",
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_addAll_index_out_of_bounds()
	{
		testpeople.addAll(7, addcollect);
	}
	
	@Test
	public void test_remove()
	{
		testpeople.remove(0);
		assertEquals("Alan Dzagoev, 23 years\n"
				+ "Leonid Kuchuk, 56 years\n",
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_remove_index_out_of_bounds()
	{
		testpeople.remove(7);
	}
	
	@Test
	public void test_clear()
	{
		testpeople.clear();
		assertEquals("", testpeople.toString());
	}
}
