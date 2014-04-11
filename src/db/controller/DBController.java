package db.controller;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;

import db.model.Person;

public class DBController
{
	private String connectionString;
	private Connection databaseConnection;
	

	public DBController()
	{
		connectionString = "jdbc:mysql://localhost/?user=root";

		checkDriver();
		setupConnection();
	}
	
	/**
	 * creates a DBController
	 * @return
	 */
	public String getConnectionString()
	{
		return connectionString;
	}
	
	/**
	 * sets the class level connectionString with the supplied String object.
	 * @param connectionString
	 */
	public void setConnectionString(String connectionString)
	{
		this.connectionString = connectionString;
	}

	/**
	 * Builds a Java connectionString for a MySQL database with the supplied fields for server path.
	 * @param serverPath the path to the server.
	 * @param database the name of the database you are connecting to you.
	 * @param userName the username for the database access.
	 * @param password the password in cleartext for the connection.
	 */
	/**public void buildConnectionString(String serverPath, String database, String userName, String password)
	{
		connectionString = "jdbc:mysql://" + serverPath + "/" + database + "?user=" + userName +"&password=" + password;
	}*/
	
	/**
	 * Checks that the MySql database driver is loaded with the project. Shuts
	 * the program down if it fails.
	 */
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception currentException)
		{
			System.err.println("Unable to load the driver");
			System.err.println("Check that the Connector] .jar file is loaded as an external JAR in the build path.");
			System.err.println("The original .jar should be in the ~/MySQL/ foler");
			System.exit(1);
		}
	}
	

	/**
	 * Make the connection to the databases using the connection string. Catches
	 * SQL Errors and displays via the displaySQLErrors method.
	 */
	public void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectionString);
		}
		catch (SQLException currentException)
		{
			displaySQLErrors(currentException);
		}
	}

	
	/**
	 * Displays the associated SQL and Java errors attached to this database
	 * project
	 * 
	 * @param current
	 *            The Supplied SQLException
	 */
	public void displaySQLErrors(SQLException current)
	{
		JOptionPane.showMessageDialog(null, "SQL message is: " + current.getMessage());
		JOptionPane.showMessageDialog(null, "SQL state is: " + current.getSQLState());
		JOptionPane.showMessageDialog(null, "Java error code is: " + current.getErrorCode());
	}
	/**
	 * clears the old connection with the database and then sets up a new connection.
	 */
	private void clearConnection()
	{
		closeConnection();
		//connectionString = "jdbc:mysql://localhost/?user=root";
		setupConnection();
	}
	/**
	 * closes the connection with the server
	 */
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	/**
	 * creates a database 
	 * @param database implemented in the listeners in my DBPanel
	 */
	public void createDatabase(String database)
	{
		clearConnection();
		try
		{
			Statement createDatabaseStatement = databaseConnection.createStatement();

			int result = createDatabaseStatement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
			createDatabaseStatement.close();

		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
		JOptionPane.showMessageDialog(null, "Database Created.");
	}
	/**
	 * Deletes a database 
	 * @param database implemented in the listeners in my DBPanel
	 */
	public void deleteDatabase(String database)
	{
		clearConnection();
		try
		{
			Statement deleteDatabaseStatement = databaseConnection.createStatement();

			int result = deleteDatabaseStatement.executeUpdate("DROP DATABASE" + database + " ;");
			deleteDatabaseStatement.close();

		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	/**
	 * Creates a table
	 * @param database implemented in the listeners in my DBPanel
	 * @param tableName implemented in the listeners in my DBPanel
	 * @param parameters
	 */
	public void createTable(String database, String tableName, String [][] parameters)
	{
		clearConnection();

		int queryIndex = connectionString.indexOf("?");
		String connectionStart = connectionString.substring(0, queryIndex);
		String connectionEnd = connectionString.substring(queryIndex);
		connectionString = connectionStart + database + connectionEnd;

		try
		{
			Statement createTableStatement = databaseConnection.createStatement();
			String parameterInfo = parseParameterArray(parameters);
			
			String createString = "CREATE TABLE IF NOT EXISTS `" + database + "`.`" + tableName + "`" +
					"(" +
						parameterInfo +
					") ENGINE=INNODB;";
			
			int result = createTableStatement.executeUpdate(createString);
			createTableStatement.close();

		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	private String parseParameterArray(String[][] parameters)
	{
		String parameterInfo = "";
		
		for(int row = 0; row < parameters.length; row++)
		{
			parameterInfo += "`" + parameters[row][1] + " " + parameters [row][2] ;
			
			if(row <parameters.length - 1)
			{
				parameterInfo += ",";
			}
		}
		return parameterInfo;
	}
	
	/**
	 * inserts a Person object into the database. 
	 * currently not working.
	 * @param currentPerson
	 */
	public void insertPersonIntoDatabase(Person currentPerson)
	{
		try
		{
			Statement insertPersonStatement = databaseConnection.createStatement();
			int databaseIsMarried, databaseHasChildren;
			if(currentPerson.isMarried())
			{
				databaseIsMarried = 1;
			}
			else
			{
				databaseIsMarried = 0;
			}
			if(currentPerson.isHasChildren())
			{
				databaseHasChildren = 1;
			}
			else
			{
				databaseHasChildren = 0;
			}
			
			String insertString = "INSERT INTO `graveyard`.`people`" +
					"( `person_name`,`death_date`,`brith_date`,`person_is_married`,`person_has_children`,`person_age` ) " +
					"VALUES " +
						"('" + currentPerson.getName() + "', '" + currentPerson.getDeathDate() + "', '" + currentPerson.getBirthDate() + 
						"', '" + databaseIsMarried + "', '" + databaseHasChildren + "', '" + currentPerson.getAge() + "'" +
						");";
			
			int result = insertPersonStatement.executeUpdate(insertString);
			JOptionPane.showMessageDialog(null, "Successfully inserted " + result + " rows");
			insertPersonStatement.close();
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	/**
	 * updates the information in the database about a specific person.
	 * @param oldName
	 * @param newName
	 */
	public void updatePersonInTable(String oldName, String newName)
	{
		try
		{
			Statement updateStatement = databaseConnection.createStatement();
			String updateString = "UPDATE `graveyard`.`people` "+
									"SET `person_name` = '" + newName + "'" +
									"WHERE `person_name` = '" + oldName + "' ;";
			
			int result = updateStatement.executeUpdate(updateString);
			JOptionPane.showMessageDialog(null, "Successfully updated " + result + " row(s)");
			updateStatement.close();
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	/**
	 * creates a table to put people objects in.
	 * @param database
	 */
	public void createPersonTable(String database)
	{
		clearConnection();

		int queryIndex = connectionString.indexOf("?");
		String connectionStart = connectionString.substring(0, queryIndex);
		String connectionEnd = connectionString.substring(queryIndex);
		connectionString = connectionStart + database + connectionEnd;
		
		try
		{
			Statement createTableStatement = databaseConnection.createStatement();
			String createPersonTable = "CREATE TABLE `" + database + "`.`people`"+
					"(" +
						"`person_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
						"`person_name` VARCHAR (50) NOT NULL," +
						"`person_birth_date` VARCHAR (30)," +
						"`person_death_date` VARCHAR (30)," +
						"`person_is_married` BOOL," +
						"`person_has_children` BOOL," +
						"`person_age` INT) " +
						"ENGINE = INNODB;";
			
			int result = createTableStatement.executeUpdate(createPersonTable);
			createTableStatement.close();
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	public Vector<Person> selectDataFromTable(String tableName)
	{
		Vector<Person> personVector = new Vector<Person>();
		ResultSet seeDeadPeopleResults;
		String selectQuery = "SELECT person_age, person_name, person_has_children, person_is_married, person_birth_date, person_death_date" +
		"FROM"+ tableName + ";";
		
		try
		{
			PreparedStatement selectStatement = databaseConnection.prepareStatement(selectQuery);
			seeDeadPeopleResults = selectStatement.executeQuery();
			
			while(seeDeadPeopleResults.next())
			{
				Person tempPerson = new Person();
				
				int tempAge = seeDeadPeopleResults.getInt(1);
				String tempName = seeDeadPeopleResults.getString(2);
				boolean tempKids = seeDeadPeopleResults.getBoolean(3);
				boolean tempMarried = seeDeadPeopleResults.getBoolean(4);
				String tempBirth = seeDeadPeopleResults.getString(5);
				String tempDeath = seeDeadPeopleResults.getString(6);
				
				tempPerson.setAge(tempAge);
				tempPerson.setName(tempName);
				tempPerson.setHasChildren(tempKids);
				tempPerson.setMarried(tempMarried);
				tempPerson.setBirthDate(tempBirth);
				tempPerson.setDeathDate(tempDeath);
				
				personVector.add(tempPerson);
			}
			
			seeDeadPeopleResults.close();
			selectStatement.close();
			
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
		return personVector;
	}
	
	private Vector<Vector <String>> parsePersonInformation(Vector<Person> peopleData)
	{
		Vector<Vector <String>> parsedData = new Vector<Vector <String>>();
		int currentRowCount = 1;
		for(Person current : peopleData)
		{
			Vector<String> currentRow = new Vector<String>();
			
			currentRow.add(Integer.toString(currentRowCount));
			currentRow.add(current.getName());
			currentRow.add(current.getBirthDate());
			currentRow.add(current.getDeathDate());
			currentRow.add(Boolean.toString(current.isMarried()));
			currentRow.add(Boolean.toString(current.isHasChildren()));
			currentRow.add(Integer.toString(current.getAge()));
			
			parsedData.add(currentRow);
			currentRowCount++;
		}
		
		return parsedData;
	}
	
	/**
	 * connects to cody's external server
	 */
	/**public void connectToExternalServer()
	{
		buildConnectionString("10.228.6.204" , "", "ctec", "student");
		setupConnection();
		JOptionPane.showMessageDialog(null, "Connection Successful.");
	}*/

}
