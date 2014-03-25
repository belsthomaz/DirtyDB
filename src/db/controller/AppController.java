package db.controller;

import java.awt.Container;
import java.util.ArrayList;

import db.model.GraveMarker;
import db.model.Person;
import db.view.DBFrame;

public class AppController
{
	private DBController myDataController;
	private DBFrame myAppFrame;
	private ArrayList<GraveMarker> myGraveMarkers;
	private ArrayList<Person> myGraveyardPeople;
	
	public AppController()
	{
		myDataController = new DBController();
		myGraveMarkers = new ArrayList<GraveMarker>();
		myGraveyardPeople = new ArrayList<Person>();
	}
	
	public void start()
	{
		myAppFrame = new DBFrame(this);
	}
	
	public DBController getMyDataController()
	{
		return myDataController;
	}

	public ArrayList<Person> getMyGraveyardPeople()
	{
		
		return myGraveyardPeople;
	}
}
