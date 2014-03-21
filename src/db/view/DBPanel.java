package db.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import db.controller.AppController;
import db.model.Person;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBPanel extends JPanel
{
	private AppController baseController;
	private JTextField nameField;
	private JTextField deathDateField;
	private JTextField ageField;
	private JButton createDBButton;
	private JButton deleteDBButton;
	private JButton createTableButton;
	private JButton createPeopleTableButton;
	private JButton insertPersonButton;
	private SpringLayout baseLayout;
	private JLabel nameLabel;
	private JLabel birthDateLabel;
	private JLabel deathDateLabel;
	private JLabel ageLabel;
	
	public DBPanel(AppController baseController)
	{
		
		this.baseController = baseController;
		
		createDBButton = new JButton("Create Database");
		deleteDBButton = new JButton("Delete Database");
		createTableButton = new JButton("Create a Table");
		createPeopleTableButton = new JButton("Create a people table");
		insertPersonButton = new JButton("put person in table");
		nameField = new JTextField(40);
		deathDateField = new JTextField(30);
		ageField = new JTextField(10);
		nameLabel = new JLabel("Name field:");
		birthDateLabel = new JLabel("Birth Date field:");
		deathDateLabel = new JLabel("Death Date field:");
		ageLabel = new JLabel("Age field:");
		
		baseLayout = new SpringLayout();

		
		
		

		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	public void setupPanel()
	{
		this.setLayout(baseLayout);

		this.add(createDBButton);
		this.add(deleteDBButton);
		this.add(createTableButton);
		this.add(createPeopleTableButton);
		this.add(insertPersonButton);
		this.add(ageField);
		this.add(nameField);
		this.add(deathDateField);
		this.add(ageLabel);
		this.add(birthDateLabel);
		this.add(deathDateLabel);
		this.add(nameLabel);

	}
	
	public void setupLayout()
	{
		setBackground(new Color(64, 224, 208));
		baseLayout.putConstraint(SpringLayout.SOUTH, deathDateLabel, -226, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, ageField, 303, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, ageLabel, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, ageLabel, -6, SpringLayout.NORTH, ageField);
		baseLayout.putConstraint(SpringLayout.WEST, ageField, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateLabel, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, nameLabel, 37, SpringLayout.SOUTH, createTableButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, nameLabel, -11, SpringLayout.NORTH, nameField);
		baseLayout.putConstraint(SpringLayout.SOUTH, nameField, -6, SpringLayout.NORTH, birthDateLabel);
		baseLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, nameField, 63, SpringLayout.SOUTH, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, birthDateLabel, 92, SpringLayout.SOUTH, createTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, birthDateLabel, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, createDBButton, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, deleteDBButton, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, deleteDBButton, -27, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, insertPersonButton, 12, SpringLayout.SOUTH, createPeopleTableButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createTableButton, 12, SpringLayout.SOUTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, insertPersonButton, 0, SpringLayout.WEST, createPeopleTableButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createPeopleTableButton, 0, SpringLayout.NORTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTableButton, 30, SpringLayout.EAST, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, createTableButton, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createDBButton, 10, SpringLayout.NORTH, this);
	}
	
	public void setupListeners()
	{
		createDBButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createDatabase();
			}
		});
		
		deleteDBButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().deleteDatabase();
				
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
	}
	private boolean checkInteger(String current)
	{
		boolean checkInteger = false;
		
		try
		{
			Integer.parseInt(current);
			checkInteger = true;
		}
		catch(NumberFormatException currentException)
		{
			JOptionPane.showMessageDialog(this, "Make sure you typed in a number for the age :)");
		}
		
		return checkInteger;
		
	}
	
	private	Person createPersonFromInputInput()
	{
		Person deadPerson = null;
		
		return deadPerson;
	}

}


