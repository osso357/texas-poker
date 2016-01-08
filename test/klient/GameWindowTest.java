package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameWindowTest {

	@Test
	public void testView() {
		Game game = new Game();
		game.addPlayer("Adam", 500);
		game.addPlayer("Ewa", 500);
		GameWindow window = GameWindow.getInstance(game);
		window = GameWindow.getInstance(game);
		assertEquals(game.players.get(0).getName(), "Adam");
	}
	
}
