import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MapPrintStream extends PrintStream {

	public MapPrintStream(File file) throws FileNotFoundException {super(file);}
	
	public void println(Map map)
	{
		Set<Entry> entries = map.entrySet();
		for(Entry entry : entries){
			print( entry.getKey().toString() + " " + entry.getValue().toString() + "\n" );}
	}
}
