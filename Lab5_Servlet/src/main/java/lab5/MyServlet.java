package lab5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			List<String> template = Files.readAllLines(Paths.get("src\\main\\java\\web\\time.html"));
			
			for(String s:template) {
				out.println(s.replaceAll("%MESSAGE%", getDateTime()));
			}			
	}
	
	public String getDateTime() { 
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(System.currentTimeMillis());
	}
}
