package klient;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1470374201074727889L;
	
	JButton fold;
	JButton bet;
	JButton check;
	JTextField amount;
	Game game;

	public BetPanel(Game currentgame) {
		Game game = currentgame;
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(700,50));
		fold = new JButton("Fold");
		fold.setActionCommand("fold");
		bet = new JButton("Bet");
		bet.setActionCommand("Bet");
		check = new JButton("Check");
		check.setActionCommand("Check");
		amount = new JTextField(20);
		
		
		add(amount);
		add(check);
		add(bet);
		add(fold);
		setVisible(true);
	}
}
