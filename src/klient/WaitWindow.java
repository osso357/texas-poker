package klient;

import javax.swing.*;

import java.awt.*;

public class WaitWindow extends JFrame {
    JLabel message;
  
	WaitWindow() {
    
	super("Texas Hold'em");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
    setLayout(new FlowLayout(FlowLayout.CENTER));
	message = new JLabel("Oczekiwanie na połączenie...");
	add(message);
	setSize(400,70);
	setLocationRelativeTo(null);
	setVisible(true);
	}
	
	public void setMessage(String text) {
		message.setText(text);
	}
}
