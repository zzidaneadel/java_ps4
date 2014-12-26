package lab6;

//import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletHandler;


public class main {

	
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		
		ServerConnector connector = new ServerConnector(server);
	    connector.setHost("127.0.0.1");
	    connector.setPort(8080);
	    connector.setName("main");
	    server.addConnector(connector); 
	    
	    ServletHandler myservlet = new ServletHandler();
	    myservlet.addServletWithMapping(MenuServlet.class, "/main"); 
	    myservlet.addServletWithMapping(Servlet_UsersMessages.class, "/1"); 
	    myservlet.addServletWithMapping(Servlet_NumberofUsers.class, "/2"); 
	    myservlet.addServletWithMapping(Servlet_TopTenRecently.class, "/3"); 
	    myservlet.addServletWithMapping(Servlet_TopTenALongTimeAgo.class, "/4"); 
	    myservlet.addServletWithMapping(Servlet_LoginForm.class, "/5"); 
	    myservlet.addServletWithMapping(Servlet_Message.class, "/6"); 
	    myservlet.addServletWithMapping(Servlet_NewMessage.class, "/7"); 
	    myservlet.addServletWithMapping(Servlet_Login.class, "/login"); 
	    myservlet.addServletWithMapping(Servlet_Logout.class, "/logout"); 
	    myservlet.addServletWithMapping(Servlet_Registration.class, "/register"); 
	    myservlet.addServletWithMapping(Servlet_FinishRegistration.class, "/fin"); 
	    myservlet.addServletWithMapping(Servlet_ChangeMessage.class, "/changemessage");
	   
        HashSessionIdManager idmanager = new HashSessionIdManager();
        idmanager.start();
        server.setSessionIdManager(idmanager);
    
        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        
        myservlet.setHandler(sessions);
        
        server.setHandler(myservlet);       
	    

	    server.start();
	    server.join();
	}

}
