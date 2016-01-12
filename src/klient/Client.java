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
	ErrorWindow error;
	
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
		gamewin = GameWindow.getInstance(game, listener);
	}
	
	protected void refreshMessage()
	{
		GameWindow.messages.changeMessage(game.message);
	}
	
	protected void refreshPlayers()
	{
		GameWindow.players.update();
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
		catch (NumberFormatException e)
		{
			System.out.println("Port not a number");
			ww.setVisible(false);
			error = new ErrorWindow("Niepoprawne dane - port nie jest liczbą ", listener);
		}
		catch (UnknownHostException e) {
			System.out.println("Unknown host");
			ww.setVisible(false);
			error = new ErrorWindow("Nieznany host ", listener);
			//System.exit(1);
		}
		catch  (IOException e) {
			System.out.println("No I/O");
			ww.setVisible(false);
			error = new ErrorWindow("Brak połączenia ", listener);
			//System.exit(1);
		}
	}
}
