package klient;

import javax.swing.*;

public class GameWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950203353318858088L;
	CardPanel cards;
	PlayersPanel players;
	MessagePanel messages;
	Game game;
	
	public GameWindow(Game currentGame) {
		setTitle("Texas Hold'em");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		players = new PlayersPanel(currentGame);
		add(players);
		cards = new CardPanel(currentGame);
		add(cards);
		messages = new MessagePanel();
		add(messages);
		pack();
		setVisible(true);
	}
}
