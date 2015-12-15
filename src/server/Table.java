package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Table
{

	public int PlayersNumber, Chips, SmallBlind, BigBlind;
	public List<Player> PlayersList;
	private Deck deck;
	ServerSocket serverSocket = null;
	
	public boolean initialize()
	{
		PlayersList = new ArrayList<Player>();
		
		try
		{
			serverSocket = new ServerSocket(65025);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		while(PlayersList.size() < PlayersNumber)
		{
			try
			{
				Socket newConnection = serverSocket.accept();
				Player player;
				//PrintWriter out = new PrintWriter(newConnection.getOutputStream());
				//BufferedReader in = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
				try
				{
					player = new Player(this, newConnection);
				}
				catch(PlayerException e)
				{
					System.out.println(e.getMessage());
					continue;
				}
				PlayersList.add(player);
				System.out.println("Dolaczyl gracz, nick: " + player.nick + ",IP: " + newConnection.getInetAddress());
				
				for(Player connectedPlayer : PlayersList)
				{
					if(!connectedPlayer.playerSocket.isConnected())
					{
						System.out.println("Gracz " + connectedPlayer.nick + " odszedl");
						PlayersList.remove(connectedPlayer);
					}
				}
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			} 
		}
		
		return true;
	}
	
	public void startGame()
	{
		deck = new Deck();

		int dealerButtonIndex = (int)(Math.floor(Math.random()*PlayersList.size()));
		Player dealerButtonPlayer = PlayersList.get(dealerButtonIndex);
		
		dealerButtonPlayer.dealerButton = true;
		
		for(Player player : PlayersList)
		{
			player.Chips = Chips;
			String initialMessage;
			initialMessage = "S";
			for(int i = 0; i < PlayersList.size() - 1; i++)
			{
				initialMessage += ":" + PlayersList.get((PlayersList.indexOf(player)+i)%PlayersList.size()).nick;
			}
			player.out.println(initialMessage);
			
			System.out.println("Wysylam do gracza "+ player.nick +": " + initialMessage);
		}
		
		
		while(PlayersList.size() > 1)
		{
			List<Card> tableCards = new ArrayList<Card>();
			
			PlayersList.get((dealerButtonIndex + 1 >= PlayersList.size()) ? dealerButtonIndex + 1 : 0).bid = SmallBlind;
			PlayersList.get((dealerButtonIndex + 2 >= PlayersList.size()) ? dealerButtonIndex + 2 : 0).bid = BigBlind;
			
			deck.shuffleDeck();
			
			for(Player player : PlayersList)
			{
				player.drawCards(deck);
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		Table s = new Table();
		if(args.length == 4)
		{ 
			try
			{
				s.PlayersNumber = Integer.parseInt(args[0]);
				if(s.PlayersNumber < 2 || s.PlayersNumber > 10) throw new Exception();
				
				s.Chips = Integer.parseInt(args[1]);
				if(s.Chips < 1) throw new Exception();
				
				s.SmallBlind = Integer.parseInt(args[2]);
				if(s.SmallBlind < 1 || s.SmallBlind > s.Chips) throw new Exception();
				
				s.BigBlind = Integer.parseInt(args[3]);
				if(s.BigBlind < s.SmallBlind || s.BigBlind > s.Chips) throw new Exception();
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
				return;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
		else
		{
			/* Domyslne parametry wejsciowe */
			
			s.PlayersNumber = 4;
			
			s.Chips = 5000;
			
			s.SmallBlind = 200;
			
			s.BigBlind = 400;
		}
		
		if(s.initialize()) s.startGame();
		
		try {
			s.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
