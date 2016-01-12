package klient;

public class Player {

	private String name;
	private int credits;
	private int bet;
	
	public Player(String sentname, int sentcredits) {
		name = sentname;
		credits = sentcredits;
	}

	
	public void changeCredits(int change)
	{
		credits = change;
	}
	
	public void changeBet(int change)
	{
		bet = change;
	}
	
	public int getCredits()
	{
		return credits;
	}
	
	public int getBet()
	{
		return bet;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void changeName(String newName)
	{
		name = newName;
	}
}
