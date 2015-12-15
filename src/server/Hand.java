package server;

import java.util.ArrayList;
import java.util.List;

public class Hand
{
	private List<Card> playerCards;
	private List<Card> tableCards;
	
	private int handType;
	private int handPoints;
	
	public int compareTo(Hand hand)
	{
		
		return 0;
	}
	
	public void checkHand()
	{
		
	}
	
	public Hand(Card firstCard, Card secondCard)
	{
		playerCards = new ArrayList<Card>();
		
		playerCards.add(firstCard);
		playerCards.add(secondCard);
		
		tableCards = new ArrayList<Card>();
	}
}
