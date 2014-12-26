package lab6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet_LoginForm extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
        Object loginSession = session.getValue("LOGIN");
        if (session.isNew()) {
        	loginSession = "Unknown";
        	session.putValue("LOGIN", loginSession);
        }
		
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN>");
		out.println("<HTML><HEAD><TITLE>Login</TITLE></HEAD>");
		out.println("<BODY>" + "<a href=\"http://localhost:8080/main\">Main Menu</a>"
				+ "<H1><div>Log in</div></H1><br><br>");
        
        if (loginSession == "Unknown") {	
			out.println(
			"<form name=\"input\" action=\"/login\" method=\"get\">"
			+ "Login: <input type=\"text\" name=\"Login\" value=\"Your's login\" /><br />"
			+ "Password: <input type=\"password\" name=\"Password\" value=\"Password\" /><br />"
			+ "<input type=\"submit\" value=\"Log in\" />"
			+ "</form>"); 
        }
		
		out.println("<br><br> You are: <p style=\"font-family:arial;color:blue;font-size:20px;\">" + loginSession + "</p>");
		if (loginSession == "Unknown") {
			out.println("<a href=\"http://localhost:8080/register\">Registration</a>");
		} else {
			out.println("<a href=\"http://localhost:8080/logout\">LogOut</a>");
		}
		out.println("</BODY></HTML>");
		
		out.close();
	}
}
