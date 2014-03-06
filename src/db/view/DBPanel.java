package db.view;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import db.controller.AppController;
import db.model.Person;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBPanel extends JPanel
{
	private AppController baseController;
//	private JTextField textFieldOne;
//	private JTextField textFieldTwo;
//	private JTextField textFieldThree;
//	private JTextField textFieldFour;
//	private JTextField textFieldFive;
	private JButton testButton;
	//private JButton buttonTwo;
	private SpringLayout baseLayout;
	
	public DBPanel(AppController baseController)
	{
		
		this.baseController = baseController;
		
		testButton = new JButton("BUTTON");
//		buttonTwo = new JButton("WOOHOO");
//		textFieldOne = new JTextField(10);
//		textFieldTwo = new JTextField(20);
//		textFieldThree = new JTextField(30);
//		textFieldFour = new JTextField(40);
//		textFieldFive = new JTextField(50);
		
		baseLayout = new SpringLayout();

		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	public void setupPanel()
	{
		this.setLayout(baseLayout);

		this.add(testButton);
//		this.add(buttonTwo);
//		this.add(textFieldFive);
//		this.add(textFieldFour);
//		this.add(textFieldOne);
//		this.add(textFieldThree);
//		this.add(textFieldTwo);
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
	
	public void setupLayout()
	{
		setBackground(new Color(70, 130, 180));
		baseLayout.putConstraint(SpringLayout.NORTH, testButton, 125, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, testButton, 176, SpringLayout.WEST, this);
	}
	
	public void setupListeners()
	{
		testButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createDatabase();
			}
		});
	}

}


