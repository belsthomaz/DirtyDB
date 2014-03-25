package db.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JTextField birthDateField;
	private JTextField deathDateField;
	private JTextField ageField;
	private JTextField oldName;
	private JTextField newName;
	private JCheckBox marriedBox;
	private JCheckBox childrenBox;
	private JButton createDBButton;
	private JButton deleteDBButton;
	private JButton createTableButton;
	private JButton createPeopleTableButton;
	private JButton insertPersonButton;
	private SpringLayout baseLayout;
	private JLabel childrenLabel;
	private JLabel marriedLabel;
	
	public DBPanel(AppController baseController)
	{
		this.baseController = baseController;
		
		createDBButton = new JButton("Create Database");
		deleteDBButton = new JButton("Delete Database");
		createTableButton = new JButton("Create a Table");
		createPeopleTableButton = new JButton("Create a people table");
		insertPersonButton = new JButton("Put person in table");
		nameField = new JTextField("Insert Person's Name Here");
		birthDateField = new JTextField("Insert Birthdate Here");
		deathDateField = new JTextField("Insert Deathdate Here");
		ageField = new JTextField("Insert Age Here");
		oldName = new JTextField("Insert Old Name Here");
		newName = new JTextField("Insert New Name Here");
		marriedBox = new JCheckBox();
		childrenBox = new JCheckBox();
		marriedLabel = new JLabel("Is he/she married?");
		marriedLabel.setForeground(new Color(255, 255, 255));
		childrenLabel = new JLabel("Does he/she have children?");
		childrenLabel.setForeground(new Color(255, 255, 255));
		
		
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, newName, 4, SpringLayout.SOUTH, oldName);
		baseLayout.putConstraint(SpringLayout.WEST, newName, 138, SpringLayout.EAST, marriedLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, ageField, 9, SpringLayout.SOUTH, marriedBox);
		baseLayout.putConstraint(SpringLayout.NORTH, marriedLabel, 3, SpringLayout.NORTH, marriedBox);
		baseLayout.putConstraint(SpringLayout.NORTH, oldName, 91, SpringLayout.SOUTH, createPeopleTableButton);
		baseLayout.putConstraint(SpringLayout.NORTH, childrenBox, -3, SpringLayout.NORTH, childrenLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, createTableButton, 6, SpringLayout.SOUTH, insertPersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, deleteDBButton, 0, SpringLayout.NORTH, insertPersonButton);
		baseLayout.putConstraint(SpringLayout.WEST, deleteDBButton, 27, SpringLayout.EAST, insertPersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createPeopleTableButton, 0, SpringLayout.NORTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, oldName, 2, SpringLayout.WEST, newName);
		baseLayout.putConstraint(SpringLayout.NORTH, nameField, 115, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, createDBButton, -13, SpringLayout.NORTH, nameField);
		baseLayout.putConstraint(SpringLayout.NORTH, childrenLabel, 9, SpringLayout.SOUTH, deathDateField);
		baseLayout.putConstraint(SpringLayout.WEST, childrenLabel, 14, SpringLayout.EAST, childrenBox);
		baseLayout.putConstraint(SpringLayout.WEST, marriedLabel, 14, SpringLayout.EAST, marriedBox);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTableButton, 35, SpringLayout.EAST, createDBButton);
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
		this.add(birthDateField);
		this.add(deathDateField);
		this.add(oldName);
		this.add(newName);
		this.add(marriedBox);
		this.add(childrenBox);
		this.add(marriedLabel);
		this.add(childrenLabel);

	}
	
	public void setupLayout()
	{
		setBackground(new Color(123, 104, 238));
		
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


