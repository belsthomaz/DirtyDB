package db.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import db.controller.AppController;
import db.model.Person;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.JScrollBar;

public class DBPanel extends JPanel
{
	private AppController baseController;
	private JTextField nameField;
	private JTextField birthDateField;
	private JTextField deathDateField;
	private JTextField ageField;
	private JCheckBox marriedBox;
	private JCheckBox childrenBox;
	private JButton createDBButton;
	private JButton deleteDBButton;
	private JButton createTableButton;
	private JButton createPeopleTableButton;
	private JButton insertPersonButton;
	private JButton connectToServer;
	private SpringLayout baseLayout;
	private JLabel childrenLabel;
	private JLabel marriedLabel;
	private JTextArea resultsArea;
	
	public DBPanel(AppController baseController)
	{
		this.baseController = baseController;
		connectToServer = new JButton("Connect to Server");
		createDBButton = new JButton("Create Database");
		deleteDBButton = new JButton("Delete Database");
		createTableButton = new JButton("Create a Table");
		createPeopleTableButton = new JButton("Create a people table");
		insertPersonButton = new JButton("Put person in table");
		nameField = new JTextField("Insert Person's Name Here");
		birthDateField = new JTextField("Insert Birthdate Here");
		deathDateField = new JTextField("Insert Deathdate Here");
		ageField = new JTextField("Insert Age Here");
		marriedBox = new JCheckBox();
		childrenBox = new JCheckBox();
		marriedLabel = new JLabel("Is he/she married?");
		childrenLabel = new JLabel("Does he/she have children?");
		resultsArea = new JTextArea(10,20);
		
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, resultsArea, 26, SpringLayout.SOUTH, connectToServer);
		baseLayout.putConstraint(SpringLayout.WEST, resultsArea, 23, SpringLayout.EAST, childrenLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, resultsArea, -63, SpringLayout.SOUTH, deleteDBButton);
		baseLayout.putConstraint(SpringLayout.EAST, resultsArea, 0, SpringLayout.EAST, connectToServer);
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	public void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(connectToServer);
		this.add(createDBButton);
		this.add(deleteDBButton);
		this.add(createTableButton);
		this.add(createPeopleTableButton);
		this.add(insertPersonButton);
		this.add(ageField);
		this.add(nameField);
		this.add(birthDateField);
		this.add(deathDateField);
		this.add(marriedBox);
		this.add(childrenBox);
		this.add(marriedLabel);
		this.add(childrenLabel);
		this.add(resultsArea);

	}
	
	public void setupLayout()
	{
		setBackground(new Color(123, 104, 238));
		childrenLabel.setForeground(new Color(255, 255, 255));
		marriedLabel.setForeground(new Color(255, 255, 255));
		baseLayout.putConstraint(SpringLayout.NORTH, connectToServer, 0, SpringLayout.NORTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createPeopleTableButton, 0, SpringLayout.NORTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, connectToServer, 6, SpringLayout.EAST, createPeopleTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTableButton, 6, SpringLayout.EAST, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, ageField, 9, SpringLayout.SOUTH, marriedBox);
		baseLayout.putConstraint(SpringLayout.NORTH, marriedLabel, 3, SpringLayout.NORTH, marriedBox);
		baseLayout.putConstraint(SpringLayout.NORTH, childrenBox, -3, SpringLayout.NORTH, childrenLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, createTableButton, 6, SpringLayout.SOUTH, insertPersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, deleteDBButton, 0, SpringLayout.NORTH, insertPersonButton);
		baseLayout.putConstraint(SpringLayout.WEST, deleteDBButton, 27, SpringLayout.EAST, insertPersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, nameField, 115, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, createDBButton, -13, SpringLayout.NORTH, nameField);
		baseLayout.putConstraint(SpringLayout.NORTH, childrenLabel, 9, SpringLayout.SOUTH, deathDateField);
		baseLayout.putConstraint(SpringLayout.WEST, childrenLabel, 14, SpringLayout.EAST, childrenBox);
		baseLayout.putConstraint(SpringLayout.WEST, marriedLabel, 14, SpringLayout.EAST, marriedBox);
		baseLayout.putConstraint(SpringLayout.WEST, createDBButton, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, birthDateField, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, childrenBox, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, marriedBox, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, ageField, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, insertPersonButton, 0, SpringLayout.WEST, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, createTableButton, 36, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, insertPersonButton, 24, SpringLayout.SOUTH, ageField);
		baseLayout.putConstraint(SpringLayout.NORTH, marriedBox, 217, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, deathDateField, 6, SpringLayout.SOUTH, birthDateField);
		baseLayout.putConstraint(SpringLayout.NORTH, birthDateField, 6, SpringLayout.SOUTH, nameField);
	}
	
	private void fillTextArea(Vector<Person> people)
	{
		resultsArea.setText("");
		for(Person currentPerson : people)
		{
			resultsArea.append(currentPerson.toString() + "\n");
		}
	}
	
	private void setupListeners()
	{
		createDBButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createDatabase("graveyard");
			}
		});
		
		deleteDBButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().deleteDatabase("graveyard");
				
			}
		});
		
		createTableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createTable("graveyard", "graves");
			}
		});
		
		createPeopleTableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createPersonTable("graveyard");
			}
		});
		
		insertPersonButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Person currentPerson = new Person();
				currentPerson.setName(nameField.getText());
				currentPerson.setDeathDate(deathDateField.getText());
				currentPerson.setAge(Integer.parseInt(ageField.getText()));
				
				baseController.getMyGraveyardPeople().add(currentPerson);
				baseController.getMyDataController().insertPersonIntoDatabase(currentPerson);
			}
		});
		
/**		connectToServer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().connectToExternalServer();
			}
		});*/
	}

	private boolean checkParseInteger(String current)
	{
		boolean isParseble = false;
		
		try
		{
			Integer.parseInt(current);
			isParseble = true;
		}
		catch(NumberFormatException currentNumber)
		{
			JOptionPane.showMessageDialog(this, "Try typing an integer for the age :)");
		}
		return isParseble;
	}
}


