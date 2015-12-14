package klient;


public class Card {
	
	private int value;

	public Card(int value) {
	this.value = value;
	}
	
	public int getSuite()
	{
		return value / 13 ;
	}
	
	public int getStrenght()
	{
		return value % 13;
	}
}
