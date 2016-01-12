package klient;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4877891476332024807L;
	
	JLabel message;
	JButton ok;
	
	ErrorWindow(String mes, WindowActions listener)
	{
		super("Texas Hold'em Error");
		addWindowListener(listener);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
	    setLayout(new FlowLayout(FlowLayout.CENTER));
		message = new JLabel(mes);
		add(message);
		ok = new JButton("OK");
		ok.setActionCommand("errorWinOK");
		add(ok);
		setSize(400,70);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
}
