package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player
{
	public Socket playerSocket;
	public PrintWriter out;
	public BufferedReader in;
	public Table server;
	public String nick;
	public int Chips;
	public boolean dealerButton;
	public Hand hand;
	public int bid;
	
	public void drawCards(Deck deck)
	{
		try
		{
			hand = new Hand(deck.getFromTop(), deck.getFromTop());
		}
		catch(CardsException e)
		{
			e.printStackTrace();
		}
	}
	
	public Player(Table server, Socket socket) throws PlayerException
	{
		this.playerSocket = socket;
		
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String msg = in.readLine();
			//System.out.println(msg);
			if(msg != null && msg.contains("S:0:"))
			{
				this.nick = msg.split(":")[2];
				out = new PrintWriter(socket.getOutputStream());
				out.println("W lobby znajduje sie " + (server.PlayersList.size() + 1) + " na " + server.PlayersNumber + " graczy");
				System.out.println("W lobby znajduje sie " + (server.PlayersList.size() + 1) + " na " + server.PlayersNumber + " graczy");
			}
			else throw new PlayerException("Fake connection");
			
		}
		catch (IOException e)
		{
			throw new PlayerException("hehe");
		}
		
	}
}
