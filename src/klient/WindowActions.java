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
		else if (e.getActionCommand() == "All-In")
		{
			client.slistener.write("ALLIN");
		}
		else if (e.getActionCommand() == "Raise")
		{
			client.slistener.write("RAISE:" + client.gamewin.bet.amount.getText());
		}
		else if (e.getActionCommand() == "Call")
		{
			client.slistener.write("CALL");
		}
		else if (e.getActionCommand() == "Check")
		{
			client.slistener.write("CHECK");
		}
		else if (e.getActionCommand() == "Bet")
		{
			client.slistener.write("BET:" + client.gamewin.bet.amount.getText());
		}
		else if (e.getActionCommand() == "Fold")
		{
			client.slistener.write("FOLD");
		}
	}
	
	public WindowActions(Client client) {
		this.client = client;
	}
}
