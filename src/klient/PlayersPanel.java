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
	JLabel nick;
	JLabel zetony;
	JLabel wPuli;
	
	public PlayersPanel(bot.Game currentGame) {
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("GRACZE");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		bot.Game game = currentGame;
		nick = new JLabel(" NICK:", SwingConstants.LEFT);
		zetony = new JLabel("DOSTĘPNE ŻETONY:", SwingConstants.RIGHT);
		wPuli = new JLabel("ŻETONY W ZAKŁADZIE: ", SwingConstants.RIGHT);
		add(nick);
		add(zetony);
		add(wPuli);
		imiona = new JLabel[game.countPlayers()];
		kredyty = new JLabel[game.countPlayers()];
		zaklad = new JLabel[game.countPlayers()];
		setLayout(new GridLayout(game.countPlayers() + 1,3));
		for (int i = 0; i < game.countPlayers(); i++) {
			imiona[i] = new JLabel(" " + game.players.get(i).getName(), SwingConstants.LEFT);
			add(imiona[i]);
			kredyty[i] = new JLabel(Integer.toString(game.players.get(i).getCredits()), SwingConstants.RIGHT);
			add(kredyty[i]);
			zaklad[i] = new JLabel(Integer.toString(game.players.get(i).getBet()) + " ", SwingConstants.RIGHT);
			add(zaklad[i]);	
		}
		setVisible(true);
	}
	
	public PlayersPanel(Game currentGame) {
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("GRACZE");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		game = currentGame;
		nick = new JLabel(" NICK:", SwingConstants.LEFT);
		zetony = new JLabel("DOSTĘPNE ŻETONY:", SwingConstants.RIGHT);
		wPuli = new JLabel("ŻETONY W ZAKŁADZIE: ", SwingConstants.RIGHT);
		add(nick);
		add(zetony);
		add(wPuli);
		imiona = new JLabel[game.countPlayers()];
		kredyty = new JLabel[game.countPlayers()];
		zaklad = new JLabel[game.countPlayers()];
		setLayout(new GridLayout(game.countPlayers() + 1,3));
		for (int i = 0; i < game.countPlayers(); i++) {
			imiona[i] = new JLabel(" " + game.players.get(i).getName(), SwingConstants.LEFT);
			add(imiona[i]);
			kredyty[i] = new JLabel(Integer.toString(game.players.get(i).getCredits()), SwingConstants.RIGHT);
			add(kredyty[i]);
			zaklad[i] = new JLabel(Integer.toString(game.players.get(i).getBet()) + " ", SwingConstants.RIGHT);
			add(zaklad[i]);	
		}
		setVisible(true);
	}
	
	public void update() {
		for (int i = 0; i < game.countPlayers(); i++) {
			imiona[i].setText((game.players.get(i).getName()));
			kredyty[i].setText(Integer.toString(game.players.get(i).getCredits()));
			zaklad[i].setText(Integer.toString(game.players.get(i).getBet()) + " ");
		}
	}
    
}
