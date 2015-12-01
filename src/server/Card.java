package server;

public class Card
{
	public int value;
	public int color;
	private final String[] colorNames = {"Spades", "Clubs", "Hearths", "Diamonds"};
	private final String[] cardNames = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	public String getCardDescription()
	{
		return cardNames[value] + " of " + colorNames[color];
	}
	
	public Card(final int value, final int color)
	{
		this.value = value;
		this.color = color;
	}
}
