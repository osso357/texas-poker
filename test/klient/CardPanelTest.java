package klient;

import static org.junit.Assert.*;

import org.junit.Test;


public class CardPanelTest {

	@Test
	public void testPanel() {
		GameWindow gamewin;
		Game game; 
		
			game = new Game();
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
			gamewin = GameWindow.getInstance(game);
		assertNotNull(gamewin.cards);
	}
}
