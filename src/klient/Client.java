package klient;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.*;

public class Client{
	
	ConnectorWindow connector;
	WindowActions listener;
	WaitWindow ww;
	GameWindow game;
	ServerListener slistener;
	
	public static void main(String[] args){
		new Client();
	}
	
	public Client()	{
		listener = new WindowActions(this);
		connector = new ConnectorWindow(this, listener);
	}
	
	protected void connect()
	{
		connector.setVisible(false);
		ww = new WaitWindow();
		ww.addWindowListener(listener);
		//game = new GameWindow();
		try
		{
		slistener = new ServerListener(connector.adres.getText(), Integer.parseInt(connector.port.getText()));
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
