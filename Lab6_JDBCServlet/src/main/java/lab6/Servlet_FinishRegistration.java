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

public class Servlet_FinishRegistration extends HttpServlet{
	
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
		String login = "", password = "", name = "", surname = "", message = "";
		while (en.hasMoreElements()) {
			String Name = (String)en.nextElement();
			String[] paramValues = request.getParameterValues(Name); 
			//System.out.println(Name);
			if (Name.compareTo("Login") == 0) {
				login = paramValues[0];
			}
			if (Name.compareTo("Password") == 0) {
				password = paramValues[0];
			}	
			if (Name.compareTo("Name") == 0) {
				name = paramValues[0];
			}
			if (Name.compareTo("surname") == 0) {
				surname = paramValues[0];
			}
			if (Name.compareTo("Message") == 0) {
				message = paramValues[0];
			}
			count++;
		}
        //             
        //out.print(login + " - " + pass);
        
		boolean fromdb = DB.RegisterNewUser(login, password, name, surname, message);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN>");
		out.println("<HTML><HEAD><TITLE>Registration</TITLE></HEAD>");
		out.println("<BODY>" + "<a href=\"http://localhost:8080/main\">Main Menu</a>"
				+ "<H1><div>Registration</div></H1><br><br>");
		
		if (fromdb == true) {
			session.putValue("LOGIN", login);
			loginSession = login;
			out.print(login + ", yor are succesfuly registered");
		} else {		
			out.print("Someting wrong, maybe your login is already exists");
			out.print("<br><a href=\"http://localhost:8080/register\">Try register again</a>");
			
			return;
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
