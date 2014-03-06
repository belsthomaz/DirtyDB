package db.model;

import java.util.ArrayList;
public class GraveMarker
{
	private String typeOfGrave;
	//picture
	private String location;
	private ArrayList<Person> gravePersonList;
	private String graveInformation;
	private boolean isLegible;
	
	public GraveMarker()
	{
		gravePersonList = new ArrayList<Person>();
		isLegible = false;
		graveInformation = "blank";
		location = "null";
		typeOfGrave = "n/a";
	}
	
	public GraveMarker(boolean isLegible, String typeOfGrave)
	{
		this.isLegible = isLegible;
		this.typeOfGrave = typeOfGrave;
		gravePersonList = new ArrayList<Person>();
		location = "null";
		graveInformation = "blank";
		typeOfGrave = "n/a";
	}
	
 	public String getTypeOfGrave()
	{
		return typeOfGrave;
	}

	public void setTypeOfGrave(String typeOfGrave)
	{
		this.typeOfGrave = typeOfGrave;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public ArrayList<Person> getGravePersonList()
	{
		return gravePersonList;
	}

	public void setGravePersonList(ArrayList<Person> gravePersonList)
	{
		this.gravePersonList = gravePersonList;
	}

	public String getGraveInformation()
	{
		return graveInformation;
	}

	public void setGraveInformation(String graveInformation)
	{
		this.graveInformation = graveInformation;
	}

	public boolean isLegible()
	{
		return isLegible;
	}

	public void setLegible(boolean isLegible)
	{
		this.isLegible = isLegible;
	}

	public String toString()
	{
		String graveInfo = "";
		
		for(Person current : gravePersonList)
		{
			graveInfo += current + " is buried here.\n";
		}
		
		if(isLegible)
		{
			graveInfo += "This grave is legible";
		}
		else
		{
			graveInfo += "This grave is NOT legible";
		}
		return graveInfo;
	}
}
