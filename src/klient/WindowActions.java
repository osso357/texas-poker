package klient;

import java.awt.event.*;

public class WindowActions extends WindowAdapter implements ActionListener{
	
	Client client;
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		client.connect();
		
	}
	
	public WindowActions(Client client) {
		this.client = client;
	}
}
