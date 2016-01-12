package klient;

import static org.junit.Assert.*;

import org.junit.Test;


public class CardPanelTest {

	@Test
	public void testPanel() {
		GameWindow gamewin;
		Game game;
		Client client = new Client();
		
			game = new Game();
			WindowActions listener = new WindowActions(client);
			game.addPlayer("Adam", 500);
			game.addPlayer("Ewa", 500);
			game.addPlayer("Kamil", 500);
			game.addCart(2);
			game.addCart(43);
			game.addCart(11);
			game.addCart(23);
			game.addCart(48);
			game.addCart(21);
			game.addCart(36);
			gamewin = GameWindow.getInstance(game, listener);
		assertNotNull(gamewin.cards);
	}
}
