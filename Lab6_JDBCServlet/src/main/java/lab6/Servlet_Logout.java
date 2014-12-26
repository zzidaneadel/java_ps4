package lab6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Servlet_Logout extends HttpServlet{
	
	//static DataBase DB = new DataBase("jdbc:postgresql://localhost:5432/postgres", "postgres", "superpass");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN>");
		out.println("<HTML><HEAD><TITLE>1</TITLE></HEAD>");
		out.println("<BODY>" + "<a href=\"http://localhost:8080/main\">Main Menu</a>"
				+ "<H1><div>Usernames and User's messages</div></H1><br><br>");
		
		HttpSession session = request.getSession();
        Object loginSession = session.getValue("LOGIN");
        if (session.isNew()) {
        	loginSession = "Unknown";
        	session.putValue("LOGIN", loginSession);
        }
        
        out.println("GOOD BYE " + loginSession);
        session.invalidate();
		out.println("</BODY></HTML>");
		
		out.close();
	}
}
