package server;

import java.util.*;

public class Deck
{
	private ArrayList<Card> deck = new ArrayList<Card>(52);
	
	public void shuffleDeck()
	{
		Collections.shuffle(deck);
	}
	
	public Card getFromTop()
	{
		return deck.remove(0);
	}
	
	public void putIntoDeck(Card card)
	{
		deck.add(card);
	}
	
	public int getSize()
	{
		return deck.size();
	}
	
	public Deck()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j =0; j < 13; j++)
			{
				deck.add(new Card(j, i));
			}
		}
	}
}
