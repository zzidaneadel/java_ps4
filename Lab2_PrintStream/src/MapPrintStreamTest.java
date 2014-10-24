import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapPrintStreamTest {

	@Test
	public void test_map_string_string() throws IOException {
		File filemap = new File("map.txt");
		MapPrintStream stream_map = new MapPrintStream(filemap);
		Map<String, String> map1 = new TreeMap<String, String>();
		map1.put("1", "one");
		map1.put("2", "two");
		map1.put("3", "three");
		map1.put("4", "four");
		map1.put("5", "five");
		map1.put("6", "six");
		stream_map.println(map1);
				
		assertEquals("1 one\n2 two\n3 three\n4 four\n5 five\n6 six\n", file_to_string(filemap));
		stream_map.close();
	}
	
	@Test
	public void test_map_integer_string() throws IOException {
		File filemap = new File("map.txt");
		MapPrintStream stream_map = new MapPrintStream(filemap);
		Map<Integer, String> map1 = new TreeMap<Integer, String>();
		map1.put(1, "one");
		map1.put(2, "two");
		map1.put(3, "three");
		map1.put(4, "four");
		map1.put(5, "five");
		map1.put(7, "seven");
		map1.put(99, "ninety_nine");
		stream_map.println(map1);
				
		assertEquals("1 one\n2 two\n3 three\n4 four\n5 five\n7 seven\n99 ninety_nine\n", file_to_string(filemap));
		stream_map.close();
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
