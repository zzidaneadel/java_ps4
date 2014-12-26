package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	private Statement statement;
	private Connection connection;
	
	public DataBase(String host, String login, String password) {
		try{
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Error: connector driver is missing");
		}
		connection = null;
		try {
			connection = DriverManager.getConnection(host, login, password);
		} catch (SQLException e) {		
			System.out.println("Error: Get connection to DataBase");
		}
		
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Error: Can't create statement");
		}
	}
	
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error: Problem with closing database");
		}
	}
	
	public List<String> showUsers() {
		if (connection == null) {
			System.out.println("Error: you did not connect to database");
			return null;
		}
		
		ResultSet request;
		List<String> answer = new ArrayList<String>();
		try {
			request = statement.executeQuery("select \"login\", \"message\" from \"userlist\"");
			while (request.next())
			{
			  answer.add("USER: " + request.getString(1) + " - " + request.getString(2));
			}			
			request.close();
		} catch (SQLException e) {
			System.out.println("Error: Can't do showuserlist() query");
		}
		return answer;
	}
	
	public int userCount() {
		if (connection == null) {
			System.out.println("Error: you did not connect to database");
			return -1;
		}
		
		ResultSet request;
		int count = 0;
		try {
			request = statement.executeQuery("select \"login\" " + "from \"userlist\"");		
			while (request.next())
			{
			  count++;
			}		
			request.close();
		} catch (SQLException e) {
			System.out.println("Error: Can't do userCount() query");
			return -1;
		}		
		return count; //1 - Unknown
	}
	
	public List<String> topTen(String order) {
		if (connection == null) {
			System.out.println("Error: you did not connect to database");
			return null;
		}
		
		ResultSet request;
		int count = 0;
		List<String> answer = new ArrayList<String>();
		try {
			request = statement.executeQuery("select \"login\",\"last_entry\" from \"userlist\" order by \"last_entry\" "+order);
			while (request.next() && count < 10)
			{
				answer.add((count + 1) + ". USER: " + request.getString(1) + " - " + request.getString(2));
				count++;
			}			
			request.close();
		} catch (SQLException e) {
			System.out.println("Error: Can't do show TOP10 latest query");
		}	
		return answer;
	}
	
	
	
	public boolean loginUser(String login, String password) {
		if (connection == null) {
			System.out.println("Error: you did not connect to database");
			return false;
		}
		boolean answer = false;
		ResultSet request;
		try {
			request = statement.executeQuery("select \"login\" from \"userlist\" where \"login\"='"
					+ login + "' and \"password\"=" + password.hashCode() + "");
			if (request.next()) {
				long currentTime = System.currentTimeMillis();  //'2004-10-19 10:23:54'
				String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
				
				String query = "UPDATE "
						+ "\"userlist\" SET"
						+ "\"last_entry\" = "
						+ "'" + updateTime + "' "
						+ "where \"login\"='" + login + "'";
				//System.out.println(query);
				statement.executeUpdate(query);
				//System.out.println("Loggined USER: " + login);
				answer = true;
			}
			request.close();
		} catch (SQLException e) {
			//System.out.println("Error: no combination with this login " + login + " and password");
			answer = false;
		}
		return answer;
	}
	
	public String ShowUserMessage(String user) {
		String answer = "";
		if (connection == null) {
			System.out.println("Error: you did not connect to database");
			return answer;
		}
		
		ResultSet request;
		try {
			request = statement.executeQuery("select \"message\" from \"userlist\" where \"login\"='" + user + "'");
			
			if (request.next())
			{
				answer = request.getString(1);
			}		
			else {
				return answer;
			}
			request.close();
		} catch (SQLException e) {
			//System.out.println("Error: there is no user " + user + " in database");
			return answer;
		}	
		return answer;
	}
	
	public boolean changeMessage(String login, String message) {
		if (connection == null) {
			//System.out.println("Error: you did not connect to database");
			return false;
		}
		
		ResultSet request;
		try {
			request = statement.executeQuery("select \"login\" from \"userlist\" where \"login\"='"
					+ login + "'");	
			if (request.next()) {
				
				String query = "UPDATE "
						+ "\"userlist\" SET"
						+ "\"message\" = "
						+ "'" + message + "' "
						+ "where \"login\"='" + login + "'";
				//System.out.println(query);
				statement.executeUpdate(query);
			}
			request.close();
		} catch (SQLException e) {
			//System.out.println("Error: no combination with this login " + login + " and password");	
			return false;
		}
		return true;
	}
	
	public boolean RegisterNewUser(String login, String password, String name, String surname, String message) {
		if (connection == null) {
			System.out.println("Error: you did not connect to database");
			return false;
		}
		
		long currentTime = System.currentTimeMillis();  //'2004-10-19 10:23:54'
		String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		try {		
			String query = "insert into "
					+ "\"userlist\" "
					+ "(\"login\",\"password\",\"username\",\"surname\","
					+ "\"message\",\"last_entry\") "
					+ "VALUES "
					+ "('" + login + "', " + password.hashCode() + ", '" + name + "', '"+ surname + "', '"
					+ message + "', '" + updateTime + "')";
			//System.out.println(query);
			statement.executeUpdate(query);
			//System.out.println("Registered new USER: " + login);
		} catch (SQLException e) {
			//System.out.println("ERROR: user " + login + " is already exists!");
			//System.out.println(e);
			return false;
		}
		return true;
	}
}
