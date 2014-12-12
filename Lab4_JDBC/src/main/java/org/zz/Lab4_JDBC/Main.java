package org.zz.Lab4_JDBC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static String logged_user="";
    public static void main(String[] args) throws IOException {
    	
    DataBase db = new DataBase();
/*    try
    {
    db.dropTable();
    db.createTable();
    db.addSomeRows();
    db.printTable();
    }
    catch (SQLException e)
    {
    	System.out.println(e);
    }
        */
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        Scanner scanner = new Scanner(br);

        while (true) {
            System.out.println("Press 1 to display users and messages ");
            System.out.println("Press 2 to display the number of users");
            System.out.println("Press 3 to display top 10 of users recently logged in");
            System.out.println("Press 4 to display top 10 of users logged in a long time ago");
            if(logged_user == "")System.out.println("Press 5 to log in");
            if(logged_user != "")System.out.println("Press 6 to print new message");
            if(logged_user != "")System.out.println("Press 7 to log out");
            System.out.println("Press 0 to exit");
            int s = -1;
            try{
            s = scanner.nextInt();}
            catch (InputMismatchException e){
            	scanner.nextLine();
            }
                       	
            switch (s) {
                case 1:
                    try {
                        db.printUsersMessages();
                    } catch (SQLException e) {
                        System.out.println("Some SQL error occured");
                    }
                    break;
                case 2:try {
                    System.out.println(db.countUsers());
                } catch (SQLException e) {
                    System.out.println("Some SQL error occured");
                }
                    break;
                case 3:try {
                    System.out.println(db.topTen("Desc"));
                } catch (SQLException e) {
                    System.out.println("Some SQL error occured");
                }
                    break;
                case 4:try {
                    System.out.println(db.topTen("Asc"));
                } catch (SQLException e) {
                    System.out.println("Some SQL error occured");
                }
                    break;
                case 5:try {
                    System.out.println("Enter login:");
                    String login = br.readLine();
                    System.out.println("Enter password:");
                    String pass = br.readLine();
                    if (db.login(login, pass)!="")
                    	System.out.println(db.login(login, pass));
                    else
                        System.out.println("Invalid login-password pair");
                } catch (SQLException e) {
                    System.out.println(e);
                }
                    break;
                case 6:try {
                    if(logged_user!=""){
                        System.out.println("Enter new message:");
                        
                        String message = br.readLine();
                        System.out.println(logged_user + "\t" + message);
                        db.newMessage(logged_user,message);
                    }
                } catch (SQLException e) {
                    System.out.println("Some SQL error occured");
                }
                    break;
                case 7:
                    if(logged_user!=""){
                        System.out.println("User "+logged_user+" logged out");
                        logged_user="";
                    }
                    break;
                case 0:
                    return;
            }
    }
}
}