package ppexc;

import java.util.ArrayList;
import java.util.Collection;

public class People 
{
	private ArrayList<Person> people = new ArrayList<Person>(); 
	
	People() {};
	
	
	public boolean add(Person p)	{
		return people.add(p);
	}
	
	public void add(int index, Person p)	{
		people.add(index, p);
	}
	
	public boolean addAll(Collection<Person> collection)	{
		return people.addAll(collection);
	}
	
	public boolean addAll(int index, Collection<Person> collection)	{
		return people.addAll(index, collection);
	}
	
	public Person remove(int index)	{
		return people.remove(index);
	}
	
	public void clear()	{
		people.clear();
	}
	
	public String toString()
	{
		StringBuffer strbuf = new StringBuffer();
		for (Person p : people)
		{
			strbuf.append(p.toString() + "\n");
		}
		return strbuf.toString();
	}
	
}
