package lab6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet_NumberofUsers extends HttpServlet{
	
	static DataBase DB = new DataBase("jdbc:postgresql://localhost:5432/postgres", "postgres", "superpass");
	
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
		out.println("<HTML><HEAD><TITLE>2</TITLE></HEAD>");
		out.println("<BODY>" + "<a href=\"http://localhost:8080/main\">Main Menu</a>"
				+ "<H1><div>Numbers of users</div></H1><br><br>");
		
		int fromdb = DB.userCount();
		
		out.println("There are "  + fromdb + " users" + "<br>");
		
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
