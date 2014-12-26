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

public class Servlet_ChangeMessage extends HttpServlet{
	
	static DataBase DB = new DataBase("jdbc:postgresql://localhost:5432/postgres", "postgres", "superpass");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		HttpSession session = request.getSession();
        Object loginSession = session.getValue("LOGIN");
        if (session.isNew()) {
        	loginSession = "Unknown";
        	session.putValue("LOGIN", loginSession);
        }
		
		Enumeration en = request.getParameterNames();
		int count = 0;
		String mes = "";
		while (en.hasMoreElements()) {
			String name = (String)en.nextElement();
			String[] paramValues = request.getParameterValues(name); 
			if (count == 0) {
				mes = paramValues[0];
			}
		}
        
		boolean fromdb = DB.changeMessage(loginSession.toString(), mes);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN>");
		out.println("<HTML><HEAD><TITLE>Log in</TITLE></HEAD>");
		out.println("<BODY>" + "<a href=\"http://localhost:8080/main\">Main Menu</a>"
				+ "<H1><div>Log in</div></H1><br><br>");
		
		if (fromdb == true) {
			out.print(loginSession + ", your's new HELLO_MESSAGE: " + DB.ShowUserMessage(loginSession.toString()));
		} else {		
			out.print("Oh no, some problem with change your HELLO_MESSAGE");
			out.print("<br><a href=\"http://localhost:8080/7\">Try again</a>");
			
			return;
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
