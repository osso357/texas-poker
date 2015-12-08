package klient;

public class Player {

	private String name;
	private int credits;
	
	public Player(String sentname, int sentcredits) {
		name = sentname;
		credits = sentcredits;
	}

	
	public void changeCredits(int change)
	{
		credits = change;
	}
	
	public int getCredits()
	{
		return credits;
	}
	
	public String getName()
	{
		return name;
	}
}
