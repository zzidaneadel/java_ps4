import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PrintStreamTest {
	File file1;
	PrintStream stream1;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		file1 = new File("file1.txt");
		stream1 = new PrintStream(file1);
	}
	
	@After
	public void stream_closing()
	{
		stream1.close();
	}
	
	@Test
	public void test_print() throws IOException {
		stream1.print(true); stream1.print(false); stream1.print(109);
		stream1.print(-8.348); stream1.print(" STRING "); stream1.print(file1);
		
		assertEquals("truefalse109-8.348 STRING file1.txt\n", file_to_string(file1));
	}
	
	@Test
	public void test_println() throws IOException
	{
		stream1.println(true); stream1.println(false); stream1.println(109);
		stream1.println(-8.348); stream1.println(" STRING "); stream1.println(file1);
		
		assertEquals("true\nfalse\n109\n-8.348\n STRING \nfile1.txt\n", file_to_string(file1));
	}
	
	@Test
	public void test_append() throws IOException
	{
		stream1.append('y'); stream1.append("sequence"); stream1.append("abcdefghijklmnop", 5, 12);
		assertEquals("ysequencefghijkl\n", file_to_string(file1));
	}
	
	@Test
	public void test_write() throws IOException
	{
		stream1.write(88); stream1.write(89); stream1.write(13); stream1.write(90);
		assertEquals("XY\nZ\n" ,file_to_string(file1));
	}
	
	@Test (expected = FileNotFoundException.class)
	public void test_file_not_found() throws FileNotFoundException
	{
		file1 = new File(":\\incorrectpath");
		stream1 = new PrintStream(file1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_append_index_out_of_bounds() throws FileNotFoundException
	{
		stream1.append("abcde", 2, 7);
	}
	
	public String file_to_string(File file) throws IOException
	{
		String s; 
		StringBuffer s2 = new StringBuffer();
		BufferedReader inp = new BufferedReader(new FileReader(file)); 
		while ((s = inp.readLine()) != null) {
            s2.append(s + "\n");
		}
		inp.close();
		return s2.toString();
	}

}
