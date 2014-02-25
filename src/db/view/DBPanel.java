package db.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import db.controller.AppController;
import java.awt.Color;

public class DBPanel extends JPanel
{
	private AppController baseController;
	private JTextField textFieldOne;
	private JTextField textFieldTwo;
	private JTextField textFieldThree;
	private JTextField textFieldFour;
	private JTextField textFieldFive;
	private JButton buttonOne;
	private JButton buttonTwo;
	private SpringLayout baseLayout;
	
	public DBPanel(AppController baseController)
	{
		setBackground(new Color(70, 130, 180));
		this.baseController = baseController;
		
		buttonOne = new JButton("BUTTON");
		buttonTwo = new JButton("WOOHOO");
		textFieldOne = new JTextField(10);
		textFieldTwo = new JTextField(20);
		textFieldThree = new JTextField(30);
		textFieldFour = new JTextField(40);
		textFieldFive = new JTextField(50);
		
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, buttonTwo, 0, SpringLayout.NORTH, buttonOne);
		baseLayout.putConstraint(SpringLayout.WEST, buttonTwo, 66, SpringLayout.EAST, buttonOne);
		baseLayout.putConstraint(SpringLayout.WEST, textFieldTwo, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, textFieldThree, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, textFieldOne, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, textFieldFour, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, textFieldFive, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, buttonOne, 6, SpringLayout.SOUTH, textFieldFive);
		baseLayout.putConstraint(SpringLayout.WEST, buttonOne, 0, SpringLayout.WEST, textFieldFive);
		baseLayout.putConstraint(SpringLayout.NORTH, textFieldFive, 6, SpringLayout.SOUTH, textFieldFour);
		baseLayout.putConstraint(SpringLayout.NORTH, textFieldFour, 6, SpringLayout.SOUTH, textFieldThree);
		baseLayout.putConstraint(SpringLayout.NORTH, textFieldThree, 6, SpringLayout.SOUTH, textFieldTwo);
		baseLayout.putConstraint(SpringLayout.NORTH, textFieldTwo, 6, SpringLayout.SOUTH, textFieldOne);
		baseLayout.putConstraint(SpringLayout.NORTH, textFieldOne, 10, SpringLayout.NORTH, this);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	public void setupPanel()
	{
		this.setLayout(baseLayout);

		this.add(buttonOne);
		this.add(buttonTwo);
		this.add(textFieldFive);
		this.add(textFieldFour);
		this.add(textFieldOne);
		this.add(textFieldThree);
		this.add(textFieldTwo);
	}
	
	public void setupLayout()
	{
		
	}
	
	public void setupListeners()
	{
		
	}
}
