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
	
	public void initialize()
	{
		ServerSocket serverSocket = null;
		PlayersList = new ArrayList<Player>();
		
		try
		{
			serverSocket = new ServerSocket(65023);
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
			} 
		}
		
		
	}
	
	public static void main(String[] args)
	{
		Table s = null;
		if(args.length == 4)
		{ 
			s = new Table();
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
			System.out.println("Niepoprawa ilosc argumentow");
			return;
		}
		
		s.initialize();
	}

}
