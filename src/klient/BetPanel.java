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
			amount.setEnabled(false);
			raise.setEnabled(false);
			check.setEnabled(false);
			call.setEnabled(false);
			bet.setEnabled(false);
			allin.setEnabled(true);
			fold.setEnabled(true);
		}
		else if (state == 2)
		{
			amount.setEnabled(false);
			raise.setEnabled(false);
			check.setEnabled(false);
			call.setEnabled(true);
			bet.setEnabled(false);
			allin.setEnabled(false);
			fold.setEnabled(true);
		}
		else if (state == 3)
		{
			amount.setEnabled(true);
			raise.setEnabled(true);
			check.setEnabled(false);
			call.setEnabled(true);
			bet.setEnabled(false);
			allin.setEnabled(false);
			fold.setEnabled(true);
		}
		else if (state == 4)
		{
			amount.setEnabled(false);
			raise.setEnabled(false);
			check.setEnabled(true);
			call.setEnabled(false);
			bet.setEnabled(false);
			allin.setEnabled(false);
			fold.setEnabled(true);
		}
		else if (state == 5)
		{
			amount.setEnabled(false);
			raise.setEnabled(false);
			check.setEnabled(false);
			call.setEnabled(false);
			bet.setEnabled(true);
			allin.setEnabled(false);
			fold.setEnabled(true);
		}
		else if (state == 6)
		{
			amount.setEnabled(false);
			raise.setEnabled(false);
			check.setEnabled(false);
			call.setEnabled(false);
			bet.setEnabled(false);
			allin.setEnabled(false);
			fold.setEnabled(false);
		}
	}
	
	public BetPanel(Game currentgame, WindowActions listener) {
		//Game game = currentgame;
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(700,50));
		fold = new JButton("Fold");
		fold.setActionCommand("fold");
		bet = new JButton("Bet");
		bet.setActionCommand("Bet");
		check = new JButton("Check");
		check.setActionCommand("Check");
		call = new JButton("Call");
		call.setActionCommand("Call");
		raise = new JButton("Raise");
		raise.setActionCommand("Raise");
		allin = new JButton("All-In");
		allin.setActionCommand("All-In");
		amount = new JTextField(20);
		
		
		add(amount);
		add(raise);
		add(check);
		add(call);
		add(bet);
		add(allin);
		add(fold);
		
		amount.setEnabled(false);
		raise.setEnabled(false);
		check.setEnabled(false);
		call.setEnabled(false);
		bet.setEnabled(false);
		allin.setEnabled(false);
		fold.setEnabled(false);
		setVisible(true);
	}
}
