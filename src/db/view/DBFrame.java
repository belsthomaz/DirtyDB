package db.view;

import javax.swing.JFrame;

import db.controller.AppController;

public class DBFrame extends JFrame
{
	private DBPanel myDBPanel;
	
	public DBFrame(AppController baseController)
	{
		myDBPanel = new DBPanel(baseController);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(myDBPanel);
		this.setVisible(true);
		this.setSize(500,500);
	}
}
