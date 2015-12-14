package klient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;

@SuppressWarnings("serial")
public class PlayersPanel extends JPanel{

	Border blackline;
	TitledBorder border;
	Game game;
	JLabel[] imiona;
	JLabel[] kredyty;
	
	public PlayersPanel(Game currentGame) {
		//setSize(700, 250);
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("Gracze");
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
		//implement
	}
    
}
