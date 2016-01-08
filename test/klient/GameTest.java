package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void testPlayeAdd() {
		Game game = new Game();
		game.addPlayer("Adam", 500);
		assertEquals(game.players.get(0).getName(), "Adam");
	}
	

	@Test
	public void testCardAdd() {
		Game game = new Game();
		game.addCart(11);
		assertEquals(game.cards[0].getStrenght(), 11);
	}
	
	@Test
	public void testCardRem() {
		Game game = new Game();
		game.addCart(11);
		game.addCart(12);
		game.addCart(43);
		game.clearCards();
		assertNull(game.cards[3]);
	}
}
