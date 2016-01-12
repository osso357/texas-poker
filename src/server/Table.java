package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Table
{
	public int PlayersNumber, Chips, SmallBlind, BigBlind;
	public List<Player> PlayersList;
	private Deck deck;
	private List<Card> tableCards;

	ServerSocket serverSocket = null;
	
	public boolean initialize()
	{
		PlayersList = new ArrayList<Player>()
				{
					int actualPlayer = 0;
					int getNextPlayer()
					{
						actualPlayer++;
						if(actualPlayer >= size()) actualPlayer = 0;
						return actualPlayer;
					}
					void setActualPlayer(int indexOf)
					{
						if(indexOf > size())actualPlayer = 0;
						else actualPlayer = indexOf;
					}
				};
		
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
			PlayerConnector playerConnector = new PlayerConnector(this.PlayersList);
			try
			{
				playerConnector.initializeSocket(serverSocket);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				continue;
			}
			
			Player player = new Player(playerConnector);
			
			PlayersList.add(player);
			player.setNick();
			player.setIndexNumber(PlayersList.indexOf(player));
			System.out.println("Dolaczyl gracz, nick: " + player.getNick());
			
			/*for(Player connectedPlayer : PlayersList)
			{
				if(!connectedPlayer.isConnected())
				{
					System.out.println("Gracz " + connectedPlayer.getNick() + " odszedl");
					PlayersList.remove(connectedPlayer);
				}
			}*/
		}
		
		return true;
	}
	
	public void startGame()
	{
		deck = new Deck();
		deck.shuffleDeck();
		int turn = 1;
		
		tableCards = new ArrayList<Card>();

		int dealerButtonIndex = (int)(Math.floor(Math.random()*PlayersList.size()));
		Player dealerButtonPlayer = PlayersList.get(dealerButtonIndex);
		
		//dealerButtonPlayer.dealerButton = true;
		
		for(Player player : PlayersList) player.setChips(Chips);
		
		for(Player player : PlayersList)
		{
			if(player.playerConnector.receiveMessage() == "fail")
			{
				removePlayer(player);
				continue;
			}
			player.playerConnector.sendInitialMessage();
			
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		for(Player player : PlayersList)
		{
			if(player == dealerButtonPlayer)
			{
				player.playerConnector.sendMessage("M:Otrzymujesz Dealer Button");
				
			}
			else player.playerConnector.sendMessage("M:Gracz " + dealerButtonPlayer.getNick() + " otrzymuje Dealer Button");
			
			player.playerConnector.changeNick(dealerButtonPlayer, "@" + dealerButtonPlayer.getNick());
			
			Card card1, card2;
			card1 = deck.getFromTop();
			card2 = deck.getFromTop();
			player.addCards(card1, card2, tableCards);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dealerButtonPlayer.playerConnector.
		
		for(Player player : PlayersList)
		{
			
		}
		
		while(true);
		
	}
	
	private void removePlayer(Player player)
	{
		int chipsToGiveaway = player.getChips() / (PlayersList.size() - 1);
		for(Player players : PlayersList)
		{
			if(players == player) continue;
			player.giveChips(chipsToGiveaway);
		}
		// TODO Auto-generated method stub
		
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
			s.PlayersNumber = 2;
			s.Chips = 5000;
			s.SmallBlind = 200;
			s.BigBlind = 400;
		}
		
		if(s.initialize()) s.startGame();
		
		try {
			if(!s.serverSocket.isClosed()) s.serverSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
