package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void testPlayeAdd() {
		Game game = new Game();
		game.addPlayer("Adam", 500);
		assertEquals(game.Players.get(0).getName(), "Adam");
	}
	
}
