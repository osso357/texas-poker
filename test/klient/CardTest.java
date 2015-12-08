package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testSuite() {
		Card card = new Card(51);
		assertEquals(card.getSuite(), 3);
	}
	
	@Test
	public void testStrenght() {
		Card card = new Card(51);
		assertEquals(card.getStrenght(), 12);
	}
}
