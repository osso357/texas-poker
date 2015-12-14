package klient;

import javax.swing.*;

public class GameWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950203353318858088L;
	CardPanel cards;
	PlayersPanel players;
	Game game;
	
	public GameWindow(Game currentGame) {
		super("Texas Hold'em");
		players = new PlayersPanel(currentGame);
		add(players);
		cards = new CardPanel(currentGame);
		add(cards);
		setSize(700, 500);
		//pack();
		setVisible(true);
	}
}
