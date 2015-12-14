package klient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PlayersPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4386654832069104896L;
	Border blackline;
	TitledBorder border;
	Game game;
	JLabel[] imiona;
	JLabel[] kredyty;
	
	public PlayersPanel(Game currentGame) {
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("GRACZE");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		game = currentGame;
		imiona = new JLabel[game.countPlayers()];
		kredyty = new JLabel[game.countPlayers()];
		setLayout(new GridLayout(game.countPlayers(),2));
		for (int i = 0; i < game.countPlayers(); i++) {
			imiona[i] = new JLabel(game.Players.get(i).getName());
			add(imiona[i]);
			kredyty[i] = new JLabel(Integer.toString(game.Players.get(i).getCredits()));
			add(kredyty[i]);
		}
		setVisible(true);
	}
	
	public void update() {
		//TODO: implement
	}
    
}
