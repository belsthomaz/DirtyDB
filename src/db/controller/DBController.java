package db.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBController
{
	private String connectionString;
	private Connection dataConnection;
	
	public DBController()
	{
		connectionString = "jdbc:mysql://localhost/am_project?user=root";
		
		checkDriver();
		setupConnection();
	}
	
	public String getConnectionString()
	{
		return connectionString;
	}
	
	public void setConnectionString(String connectionString)
	{
		this.connectionString = connectionString;
	}
	
	/**
	 * Checks that the MySql database driver is loaded with the project.
	 * Shuts the program down if it fails.
	 */
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception currentException)
		{
			System.err.println("Unable to load the driver");
			System.err.println("Check that the Connector] .jar file is loaded as an external JAR in the build path.");
			System.err.println("The original .jar should be in the ~/MySQL/ foler");
			System.exit(1);
		}
	}
	
	/**
	 *Make the connection to the databases using the connection string. Catches 
	 *SQL Errors and displays via the displaySQLErrors method.
	 */
	public void setupConnection()
	{
		try
		{
			dataConnection =  DriverManager.getConnection(connectionString);
		}
		catch(SQLException currentException)
		{
			displaySQLErrors(currentException);
		}
	}
	
	/**
	 * Displays the associated SQL and Java errors attached to this database 
	 * project
	 * 
	 * @param current The Supplied SQLException
	 */
	public void displaySQLErrors(SQLException current)
	{
		JOptionPane.showMessageDialog(null, "SQL message is: " + current.getMessage());
		JOptionPane.showMessageDialog(null, "SQL state is: " + current.getSQLState());
		JOptionPane.showMessageDialog(null, "Java error code is: " + current.getErrorCode());
	}
}
