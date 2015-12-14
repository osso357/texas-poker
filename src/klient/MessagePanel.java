package klient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class MessagePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 969683185406396614L;
	Border blackline;
	TitledBorder border;
	JLabel message;

	public MessagePanel() {
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("WIADOMOŚĆ");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		message = new JLabel("No message yet. Waiting for something probably...");
		add(message);
		setVisible(true);
	}
	
	public void changeMessage(String newMessage) {
		message.setText(newMessage);
	}

}
