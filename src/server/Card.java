package server;


public class Card
{
	private final int value;
	private final int color;
	
	public int getValue()
	{
		return value;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public int CompareTo(Card card)
	{
		if(this.value > card.getValue()) return 1;
		else if(this.value == card.getValue()) return 0;
		else return -1;
	}
	
	public Card(final int value, final int color)
	{
		this.value = value;
		this.color = color;
	}
}
