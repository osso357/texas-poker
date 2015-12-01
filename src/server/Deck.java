package server;

import java.util.*;

public class Deck
{
	public ArrayList<Card> deck = new ArrayList<Card>(52);
	
	public void shuffleDeck()
	{
		Collections.shuffle(deck);
	}
	
	public Card getFromTop() throws CardsException
	{
		if(deck.isEmpty())
		{
			throw new CardsException("Brak kart w talii");
		}
		return deck.remove(0);
	}
	
	public void putIntoDeck(final Card card)
	{
		deck.add(deck.size(), card);
	}
	
	public int getSize()
	{
		return deck.size();
	}
	
	public Deck()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 13; j++)
			{
				deck.add(new Card(j ,i));
			}
		}
	}
}
