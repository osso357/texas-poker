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
	JLabel[] zaklad;
	
	public PlayersPanel(Game currentGame) {
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("GRACZE");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		game = currentGame;
		imiona = new JLabel[game.countPlayers()];
		kredyty = new JLabel[game.countPlayers()];
		zaklad = new JLabel[game.countPlayers()];
		setLayout(new GridLayout(game.countPlayers(),3));
		for (int i = 0; i < game.countPlayers(); i++) {
			imiona[i] = new JLabel(game.players.get(i).getName());
			add(imiona[i]);
			kredyty[i] = new JLabel(Integer.toString(game.players.get(i).getCredits()));
			add(kredyty[i]);
			zaklad[i] = new JLabel(Integer.toString(game.players.get(i).getBet()));
			add(zaklad[i]);	
		}
		setVisible(true);
	}
	
	public void update() {
		for (int i = 0; i < game.countPlayers(); i++) {
			imiona[i].setText((game.players.get(i).getName()));
			kredyty[i].setText(Integer.toString(game.players.get(i).getCredits()));
			zaklad[i].setText(Integer.toString(game.players.get(i).getBet()));
		}
	}
    
}
