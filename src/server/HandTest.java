package server;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class HandTest
{
	Hand hand1, hand2;
	List<Card> tableCards;
	@Before
	public void init()
	{
		tableCards = new ArrayList<Card>();
		tableCards.add(new Card(12, 1));
		tableCards.add(new Card(11, 2));
		tableCards.add(new Card(10, 1));
		tableCards.add(new Card(8, 2));
		tableCards.add(new Card(7, 1));
		hand1 = new Hand(tableCards);
		hand2 = new Hand(tableCards);

		hand1.addToHand(new Card(3, 1));
		hand1.addToHand(new Card(1, 1));
		hand2.addToHand(new Card(1, 3));
		hand2.addToHand(new Card(11, 3));
		
		hand1.evaluateHand();
		hand2.evaluateHand();
	}
	
	@Test
	public void testCompareTo()
	{
		for(int i = 0; i < 6; i++) System.out.println("1: " + hand1.handValue[i] + ", 2: " + hand2.handValue[i]);
		assertEquals(-1, hand2.compareTo(hand1));
	}
}
