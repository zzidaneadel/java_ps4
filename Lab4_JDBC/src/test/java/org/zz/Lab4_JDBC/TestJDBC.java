package org.zz.Lab4_JDBC;

import java.sql.SQLException;

import junit.framework.TestCase;

public class TestJDBC extends TestCase {
	
	DataBase db = new DataBase();

	protected void setUp() throws Exception {
		super.setUp();
		
	    db.dropTable();
	    db.createTable();
	    db.addSomeRows();
	    db.printTable();
	  
	}
	
	public void testNumberOfUsers() throws SQLException {		
		assertEquals(3, db.countUsers());
	}
	
	public void testUsersALongTimeAgo() throws SQLException{
		assertEquals("Leonid	2014-09-08 04:09:33"
				+ "\nAdam	2014-10-08 09:13:44"
				+ "\nJames	2014-10-19 10:23:54\n", db.topTen("asc"));
	}
	
	public void testLoginMessage() throws SQLException	{
		assertEquals("Leonid has logged in. Message: Kuban", db.login("lkuchuk", "nepuskat"));
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
