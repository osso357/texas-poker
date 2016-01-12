package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameWindowTest {

	@Test
	public void testView() {
		Client client = new Client();
		WindowActions listener = new WindowActions(client);
		Game game = new Game();
		game.addPlayer("Adam", 500);
		game.addPlayer("Ewa", 500);
		GameWindow window = GameWindow.getInstance(game, listener);
		window = GameWindow.getInstance(game, listener);
		assertEquals(game.players.get(0).getName(), "Adam");
	}
	
}
