package bot;

import javax.swing.*;

import klient.MessagePanel;
import klient.PlayersPanel;

public final class GameWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950203353318858088L;
	
	private static GameWindow instance = null;
	
	static CardPanel cards;
	static PlayersPanel players;
	static MessagePanel messages;
	Game game;
	
	public static GameWindow getInstance(Game currentgame, WindowActions listener) {
        if (instance == null) {
            instance = new GameWindow(currentgame, listener);
        }
        else
        {
        	players.update();
        	cards.update();
        }
        return instance;
    }
	
	private GameWindow(Game currentGame, WindowActions listener) {
		setTitle("Texas Hold'em");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		players = new PlayersPanel(currentGame);
		add(players);
		cards = new CardPanel(currentGame);
		add(cards);
		messages = new MessagePanel();
		add(messages);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
