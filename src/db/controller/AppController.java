package db.controller;

import db.view.DBFrame;

public class AppController
{
	private DBController myDataController;
	private DBFrame myAppFrame;
	
	public AppController()
	{
		myDataController = new DBController();
	}
	
	public void start()
	{
		myAppFrame = new DBFrame(this);
	}
}
