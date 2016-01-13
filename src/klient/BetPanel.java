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
	JButton call;
	JButton bet;
	JButton check;
	JButton raise;
	JButton allin;
	JTextField amount;
	Game game;

	public void setState(int state)
	{
		if (state == 1)
		{
			amount.setVisible(false);
			raise.setVisible(false);
			check.setVisible(false);
			call.setVisible(false);
			bet.setVisible(false);
			allin.setVisible(true);
			fold.setVisible(true);
		}
		else if (state == 2)
		{
			amount.setVisible(false);
			raise.setVisible(false);
			check.setVisible(false);
			call.setVisible(true);
			bet.setVisible(false);
			allin.setVisible(false);
			fold.setVisible(true);
		}
		else if (state == 3)
		{
			amount.setVisible(true);
			raise.setVisible(true);
			check.setVisible(false);
			call.setVisible(true);
			bet.setVisible(false);
			allin.setVisible(false);
			fold.setVisible(true);
		}
		else if (state == 4)
		{
			amount.setVisible(false);
			raise.setVisible(false);
			check.setVisible(true);
			call.setVisible(false);
			bet.setVisible(false);
			allin.setVisible(false);
			fold.setVisible(true);
		}
		else if (state == 5)
		{
			amount.setVisible(true);
			raise.setVisible(false);
			check.setVisible(false);
			call.setVisible(false);
			bet.setVisible(true);
			allin.setVisible(false);
			fold.setVisible(true);
		}
		else if (state == 6)
		{
			amount.setVisible(false);
			raise.setVisible(false);
			check.setVisible(false);
			call.setVisible(false);
			bet.setVisible(false);
			allin.setVisible(false);
			fold.setVisible(false);
		}
	}
	
	public BetPanel(Game currentgame, WindowActions listener) {
		//Game game = currentgame;
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(700,50));
		fold = new JButton("Fold");
		fold.setActionCommand("fold");
		fold.addActionListener(listener);
		bet = new JButton("Bet");
		bet.setActionCommand("Bet");
		bet.addActionListener(listener);
		check = new JButton("Check");
		check.setActionCommand("Check");
		check.addActionListener(listener);
		call = new JButton("Call");
		call.setActionCommand("Call");
		call.addActionListener(listener);
		raise = new JButton("Raise");
		raise.setActionCommand("Raise");
		raise.addActionListener(listener);
		allin = new JButton("All-In");
		allin.setActionCommand("All-In");
		allin.addActionListener(listener);
		amount = new JTextField(20);
		
		
		add(amount);
		add(raise);
		add(check);
		add(call);
		add(bet);
		add(allin);
		add(fold);
		
		amount.setVisible(false);
		raise.setVisible(false);
		check.setVisible(false);
		call.setVisible(false);
		bet.setVisible(false);
		allin.setVisible(false);
		fold.setVisible(false);
		setVisible(true);
	}
}
