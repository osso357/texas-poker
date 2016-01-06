package server;

import static org.junit.Assert.*;

import org.junit.*;

public class DeckTest
{
	Deck testDeck;
	
	@Before
	public void init()
	{
		testDeck = new Deck();
	}
	
	@Test
	public void testShuffleAndPutInto()
	{
		Deck unsortedDeck = new Deck(); 
		testDeck.shuffleDeck();
		
		int isShuffled = 0;
		
		for(int i = 0; i < 52; i++)
		{
			Card testCard = testDeck.getFromTop();
			Card beforeTestCard = unsortedDeck.getFromTop();
			if(testCard.getValue() != beforeTestCard.getValue() || testCard.getColor() != beforeTestCard.getColor())
			{
				isShuffled = 1;
				testDeck.putIntoDeck(testCard);
				break;
			}
			testDeck.putIntoDeck(testCard);
		}
		assertEquals(1, isShuffled);
	}
	
	@After
	public void fin()
	{
		testDeck = null;
	}
}
