package klient;

import java.io.IOException;
import java.net.UnknownHostException;


public class Client{
	
	ConnectorWindow connector;
	WindowActions listener;
	WaitWindow ww;
	GameWindow gamewin;
	ServerListener slistener;
	Game game; 
	
	public static void main(String[] args){
		new Client();
	}
	
	public Client()	{
		listener = new WindowActions(this);
		connector = new ConnectorWindow(this, listener);
		game = new Game();
		System.out.println("client start...");
		}
	
	protected void newGame()
	{
		ww.setVisible(false);
		gamewin = GameWindow.getInstance(game);
	}
	
	protected void refreshMessage()
	{
		GameWindow.messages.changeMessage(game.message);
	}
	
	protected void connect()
	{
		connector.setVisible(false);
		ww = new WaitWindow();
		ww.addWindowListener(listener);
		try
		{
		slistener = new ServerListener(connector.adres.getText(), Integer.parseInt(connector.port.getText()), game, this);
		slistener.start();
		slistener.write("S:0:" + connector.imie.getText());
		}
		catch (UnknownHostException e) {
			System.out.println("Unknown host");
			//System.exit(1);
		}
		catch  (IOException e) {
			System.out.println("No I/O");
			//System.exit(1);
		}
	}
}
