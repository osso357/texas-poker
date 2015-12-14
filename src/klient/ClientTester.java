package klient;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientTester extends Client {

	WindowActions listener;
	GameWindow gamewin;
	Game game; 
	
	public ClientTester() {
		listener = new WindowActions(this);
		game = new Game();
		game.addPlayer("Adam", 500);
		game.addPlayer("Ewa", 500);
		game.addCart(23);
		game.addCart(48);
		game.addCart(10);
		game.addCart(23);
		game.addCart(48);
		game.addCart(10);
		game.addCart(10);
		gamewin = new GameWindow(game);
	}

	public static void main(String[] args) {
		new ClientTester();

	}

}
