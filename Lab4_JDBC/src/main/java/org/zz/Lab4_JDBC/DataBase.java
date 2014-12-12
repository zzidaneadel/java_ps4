package org.zz.Lab4_JDBC;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataBase {
    private Connection dbConnection = null;
    private Statement statement = null;
    public static  Connection getDBConnection() throws SQLException {
        Connection dbConnection = null;
        dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "superpass");
        return dbConnection;
    }
    public void add(String username, String surname, String message, String login, 
    		String password, String lastEntry) throws SQLException
    {
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        String insertTableSQL = "INSERT INTO userlist"
                + "(username, surname, message, login, password, last_entry) " + "VALUES"
                + "('" + username + "', '" + surname + "', '" + message + "', '" + login + "', '" 
                + password + "', '" + lastEntry + "')";
        statement.executeUpdate(insertTableSQL);

    }
    public void printTable() throws SQLException{
        String selectTableSQL = "SELECT username, surname, message, login, password, last_entry from userlist";
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);

        System.out.println("username     surname     message     login     password     last_entry");
        while (rs.next()) {
            String username = rs.getString("username");
            String surname = rs.getString("surname");
            String message = rs.getString("message");
            String login = rs.getString("login");
            String password = rs.getString("password");
            String last_entry = rs.getString("last_entry");

            System.out.println(username+"\t"+surname+"\t"+message+"\t"+login+"\t"+password+"\t"+last_entry);
            }
    }
    public void printUsersMessages() throws SQLException{
        String selectTableSQL = "SELECT message,login from userlist";
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);

        System.out.println("login\tmessage");
        while (rs.next()) {
            String message = rs.getString("message");
            String login = rs.getString("login");
            System.out.println(login+"\t"+message);
        }
        
    }
    public int countUsers() throws SQLException{
        String selectTableSQL = "SELECT COUNT(*) as total FROM  userlist";
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        int total=0;
        while(rs.next()){
            total = rs.getInt("total");
        }
        return total;
    }
    public String topTen(String order) throws SQLException{
        String selectTableSQL = "SELECT * FROM userlist  ORDER BY  last_entry "+order+" LIMIT 10 ";
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        String username="";
        String last_entry="";
        String res="";
        while(rs.next()){
            username = rs.getString("username");
            last_entry = rs.getString("last_entry");
            res += username+"\t"+last_entry+"\n";
        }
        return res;
    }
    public String login(String login, String password) throws SQLException{
        String selectTableSQL = "SELECT * FROM userlist WHERE login='"+login+"' AND password='"+password+"'";
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        String username="";
        String message="";
        if(rs.next()){
            username = rs.getString("username");
            message = rs.getString("message");

            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            selectTableSQL="UPDATE userlist SET last_entry = '" + dateFormat.format(cal.getTime())
            		+ "' WHERE login = '"+login+"'";
            statement.execute(selectTableSQL);
            Main.logged_user=login;
            return username+" has logged in. Message: "+message;
        }
        return "";
    }
    public void newMessage(String login, String message) throws SQLException{

        String updateTableSQL = "UPDATE userlist SET message = '" + message + "' WHERE login = '" + login + "'";
        dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        statement.execute(updateTableSQL);
    }
    public void dropTable() throws SQLException
    {
    	String dropTableSQL = "DROP TABLE userlist;";
    	dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        statement.execute(dropTableSQL);
    }
    public void createTable() throws SQLException
    {
    	String createTableSQL = "CREATE TABLE userlist (\n"
    			+ "	username varchar(80),\n	surname varchar(80),\n"
    			+ "	message varchar(80),\n"
    			+ "	login varchar(80),\n"
    			+ "	password varchar(80),\n"
    			+ "	last_entry timestamp\n"
    			+ ");";
    	dbConnection = this.getDBConnection();
        statement = dbConnection.createStatement();
        statement.execute(createTableSQL);
    }
    public void addSomeRows() throws SQLException
    {
    	this.add("James", "Cameron", "Terminator", "jcameron", "avatartenoutoften", "2014-10-19 10:23:54");
    	this.add("Leonid", "Kuchuk", "Kuban", "lkuchuk", "nepuskat", "2014-09-08 04:09:33");
    	this.add("Adam", "Savage", "Own reality", "adammyth", "ytre906", "2014-10-08 09:13:44");
    }
    
}