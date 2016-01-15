package klient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1470374201074727889L;
	
	Border blackline;
	TitledBorder border;
	
	JButton[] buttons;
	JTextField amount;
	JLabel notPossible;
	Game game;

	
	public void turnButtonOn(int buttonIndex)
	{
		notPossible.setVisible(false);
		buttons[buttonIndex-1].setVisible(true);
		if (buttonIndex == 2 || buttonIndex == 3)
		{
			amount.setVisible(true);
		}
	}
	
	public void turnButtonsOff()
	{
		amount.setVisible(false);
		for (int i = 0; i < 6; i++)
		{
			buttons[i].setVisible(false);;
		}
		notPossible.setVisible(true);
	}
	
	/*public void setState(int state) //stary tryb zmiany stanów przycisków
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
			notPossible.setVisible(false);
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
			notPossible.setVisible(false);
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
			notPossible.setVisible(false);
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
			notPossible.setVisible(false);
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
			notPossible.setVisible(false);
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
			notPossible.setVisible(true);
		}
	}*/
	
	public BetPanel(Game currentgame, WindowActions listener) {
		//Game game = currentgame;
		setLayout(new FlowLayout(FlowLayout.CENTER));
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("OPCJE LICYTACJI");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		buttons = new JButton[6];
		
		setPreferredSize(new Dimension(700,60));
		buttons[4] = new JButton("Fold");
		buttons[4].setActionCommand("Fold");
		buttons[4].addActionListener(listener);
		buttons[1] = new JButton("Bet");
		buttons[1].setActionCommand("Bet");
		buttons[1].addActionListener(listener);
		buttons[0] = new JButton("Check");
		buttons[0].setActionCommand("Check");
		buttons[0].addActionListener(listener);
		buttons[3] = new JButton("Call");
		buttons[3].setActionCommand("Call");
		buttons[3].addActionListener(listener);
		buttons[2] = new JButton("Raise");
		buttons[2].setActionCommand("Raise");
		buttons[2].addActionListener(listener);
		buttons[5] = new JButton("All-In");
		buttons[5].setActionCommand("All-In");
		buttons[5].addActionListener(listener);
		amount = new JTextField(20);
		notPossible = new JLabel("Nie możesz licytować w tej chwili");
		
		add(amount);
		for (int i = 0; i < 6; i++)
		{
			add(buttons[i]);
		}
		add(notPossible);
		
		amount.setVisible(false);
		for (int i = 0; i < 6; i++)
		{
			buttons[i].setVisible(false);;
		}
		notPossible.setVisible(true);
		setVisible(true);
	}
}
