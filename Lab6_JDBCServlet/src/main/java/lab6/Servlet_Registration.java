package lab6;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet_Registration extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		HttpSession session = request.getSession();
        Object loginSession = session.getValue("LOGIN");
        if (session.isNew()) {
        	loginSession = "Unknown";
        	session.putValue("LOGIN", loginSession);
        }
		          
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN>");
		out.println("<HTML><HEAD><TITLE>Registration</TITLE></HEAD>");
		out.println("<BODY>" + "<a href=\"http://localhost:8080/main\">Main Menu</a>"
				+ "<H1><div>Registration</div></H1><br><br>");
		
		
		out.println("Hello, check all fields and go");
		if (loginSession == "Unknown") {
			if (loginSession == "Unknown") {	
				out.println(
				"<form name=\"input\" action=\"/fin\" method=\"get\">"
				+ "Login: <input type=\"text\" name=\"Login\" value=\"\" /><br />"
				+ "Password: <input type=\"password\" name=\"Password\" value=\"\" /><br />"
				+ "Name: <input type=\"text\" name=\"Name\" value=\"\" /><br />"
				+ "Surname: <input type=\"text\" name=\"surname\" value=\"\" /><br />"
				+ "Message: <input type=\"text\" name=\"Message\" value=\"\" /><br />"
				+ "<input type=\"submit\" value=\"register\" />"
				+ "</form>"); 
	        }
		} else {
			out.println("You already have registration!!!");
		}
		
		out.println("<br><br> You is: <p style=\"font-family:arial;color:blue;font-size:20px;\">" + loginSession + "</p>");
		if (loginSession == "Unknown") {
			out.println("<a href=\"http://localhost:8080/register\">Registration</a>");
		} else {
			out.println("<a href=\"http://localhost:8080/logout\">LogOut</a>");
		}
		out.println("</BODY></HTML>");
		
		out.close();
	}
}
