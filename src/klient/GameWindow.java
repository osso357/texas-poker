package klient;

import javax.swing.*;

public final class GameWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950203353318858088L;
	
	private static GameWindow instance = null;
	
	static CardPanel cards;
	static PlayersPanel players;
	static MessagePanel messages;
	static BetPanel bet;
	Game game;
	
	public static GameWindow getInstance(Game currentgame) {
        if (instance == null) {
            instance = new GameWindow(currentgame);
        }
        else
        {
        	players.update();
        	cards.update();
        }
        return instance;
    }
	
	private GameWindow(Game currentGame) {
		setTitle("Texas Hold'em");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		players = new PlayersPanel(currentGame);
		add(players);
		cards = new CardPanel(currentGame);
		add(cards);
		bet = new BetPanel(currentGame);
		add(bet);
		messages = new MessagePanel();
		add(messages);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
