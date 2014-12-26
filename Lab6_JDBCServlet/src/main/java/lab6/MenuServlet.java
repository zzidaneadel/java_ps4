package lab6;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
        Object loginSession = session.getValue("LOGIN");
        if (session.isNew()) {
        	loginSession = "Unknown";
        	session.putValue("LOGIN", loginSession);
        }

		
		List<String> template = Files.readAllLines(Paths.get("src//main//java//laba6//web//main.html"));
		
		for(String s:template) {
			out.println(s);
		}		
		
		out.println("<br><br> You are: <p style=\"font-family:arial;color:blue;font-size:20px;\">" + loginSession + "</p>");
		if (loginSession == "Unknown") {
			out.println("<a href=\"http://localhost:8080/register\">Registration</a>");
		} else {
			out.println("<a href=\"http://localhost:8080/logout\">LogOut</a>");
		}
		out.close();
	}
}
