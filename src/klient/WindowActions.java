package klient;

import java.awt.event.*;

public class WindowActions extends WindowAdapter implements ActionListener{
	
	Client client;
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "connect")
		{
			client.connect();
		}
		else if (e.getActionCommand() == "errorWinOK")
		{
			if(client.gamewin != null)
			{
				client.gamewin.setVisible(false);
			}
			if(client.ww != null)
			{
				client.ww.setVisible(false);
			}
			if(client.error != null)
			{
				client.error.setVisible(false);
			}
			client.connector.setVisible(true);
		}
	}
	
	public WindowActions(Client client) {
		this.client = client;
	}
}
