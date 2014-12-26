package lab5;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
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
	    myservlet.addServletWithMapping(MyServlet.class, "/time"); //http://localhost:8080/time
	    
	    server.setHandler(myservlet);
	    
	    server.start();
	    server.join();
	}

}
