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
	private int actualPlayer;
	private int maxBet;
	private int pot;

	ServerSocket serverSocket = null;
	
	private int getActualPlayer()
	{
		return actualPlayer;
	}
	private void setActualPlayer(int indexOf)
	{
		actualPlayer = indexOf;
	}
	private int getNextPlayer()
	{
		actualPlayer++;
		if(actualPlayer >= PlayersList.size()) actualPlayer = 0;
		return actualPlayer;
	}
	
	public boolean initialize()
	{
		PlayersList = new ArrayList<Player>();
		
		try
		{
			serverSocket = new ServerSocket(65025);
		}
		catch (IOException e)
		{
			System.out.println("Port zajety!");
			return false;
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
	
	public boolean checkBets()
	{
		int playerBet = PlayersList.get(0).getActualBet();
		for(Player player : PlayersList)
		{
			if(player.folded || player.allIn) continue;
			if(playerBet != player.getActualBet()) return false;
		}
		return true;
	}
	
	public boolean checkIfBetsZero()
	{
		for(Player player : PlayersList)
		{
			if(player.getActualBet() != 0) return false;
		}
		return true;
	}
	
	public boolean checkTurnBets()
	{
		for(Player player : PlayersList)
		{
			if(player.getTurnBet() != 0) return false;
		}
		return true;
	}
	
	public boolean allPlayersMoved()
	{
		for(Player player : PlayersList)
		{
			if(!player.didMove) return false;
		}
		return true;
	}
	
	public void startGame()
	{
		System.out.println("Startuje gre");
		
		deck = new Deck();
		deck.shuffleDeck();
		
		tableCards = new ArrayList<Card>();

		int dealerButtonIndex = (int)(Math.floor(Math.random()*PlayersList.size()));
		Player dealerButtonPlayer = PlayersList.get(dealerButtonIndex);
		
		
		setActualPlayer(dealerButtonIndex);
		
		for(Player player : PlayersList) player.setChips(Chips);
		
		for(Player player : PlayersList)
		{
			/*if(player.playerConnector.receiveMessage() == "fail")
			{
				removePlayer(player);
				continue;
			}*/
			player.playerConnector.sendInitialMessage();
			System.out.println("SendInitialMessage");
			
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
		
		PlayersList.get(getNextPlayer()).addToBet(SmallBlind);
		PlayersList.get(getActualPlayer()).modifyPlayer();
		PlayersList.get(getNextPlayer()).addToBet(BigBlind);
		PlayersList.get(getActualPlayer()).modifyPlayer();
		
		maxBet = BigBlind;
		
		for(Player player : PlayersList)
		{
			player.playerConnector.changeNick(dealerButtonPlayer, "@" + dealerButtonPlayer.getNick());
			player.enableButton(7);
		}
		
		//Licytacja
		
		int turn = 1;
		while(turn < 5)
		{
			int startingPlayerIndex = getActualPlayer();
			//System.out.println("starting=" + startingPlayerIndex + ", actual=" + getActualPlayer() + "\n");
			do
			{
				Player actualPlayer = PlayersList.get(getNextPlayer());
				
				if(actualPlayer.folded || actualPlayer.allIn) continue;
				//Ustawianie guzikow
				//if(actualPlayer.folded) continue;
				
				//check
				if(checkIfBetsZero()) actualPlayer.enableButton(1);
				//Bet
				if(checkIfBetsZero()) actualPlayer.enableButton(2);
				//Raise
				if(maxBet > actualPlayer.getActualBet() && actualPlayer.getChips() + actualPlayer.getActualBet() > maxBet) actualPlayer.enableButton(3);
				//Call
				if(maxBet > actualPlayer.getActualBet() && actualPlayer.getChips() + actualPlayer.getActualBet() > maxBet) actualPlayer.enableButton(4);
				//fold
				actualPlayer.enableButton(5);
				//AllIn
				if(maxBet > actualPlayer.getActualBet() + actualPlayer.getChips()) actualPlayer.enableButton(6);
				
				
				//actualPlayer.setBiddingStatus(maxBet);
				//if(actualPlayer.folded) continue;
				String messageReceived = actualPlayer.playerConnector.receiveMessage();
				System.out.println("otrzymano: " + messageReceived);
				actualPlayer.enableButton(7);
				
				if(messageReceived.equals("FOLD")){
					actualPlayer.folded = true;
					System.out.println(actualPlayer.getNick() + " zfoldowal");
				}
				else if(messageReceived.equals("CHECK")) continue;
				else if(messageReceived.equals("CALL"))
				{
					actualPlayer.addToBet(maxBet - actualPlayer.getActualBet());
					actualPlayer.modifyPlayer();
				}
				else if(messageReceived.contains("BET"))
				{
					int actualPlayerBet = Integer.parseInt(messageReceived.split(":")[1]);
					actualPlayer.addToBet(actualPlayerBet);
					actualPlayer.modifyPlayer();
					maxBet = actualPlayerBet;
				}
				else if(messageReceived.contains("RAISE"))
				{
					int actualPlayerBet = Integer.parseInt(messageReceived.split(":")[1]);
					actualPlayer.addToBet(actualPlayerBet);
					actualPlayer.modifyPlayer();
					maxBet = actualPlayerBet;
				}
				else if(messageReceived.equals("ALLIN"))
				{
					actualPlayer.addToBet(actualPlayer.getChips());
					actualPlayer.modifyPlayer();
				}
				actualPlayer.didMove = true;
				actualPlayer.modifyPlayer();
				
			}
			while(!checkBets() && !allPlayersMoved());
			
			for(Player player : PlayersList)
			{
				pot += player.getActualBet();
				player.setActualBet(0);
				maxBet = 0;
				player.didMove = false;
			}
			
			for(Player player : PlayersList)
			{
				player.modifyPlayer();
				player.playerConnector.sendMessage("M:Pula wynosi " + pot + " ¿etonów");
			}
			
			if(turn == 1)
			{
				tableCards.add(deck.getFromTop());
				tableCards.add(deck.getFromTop());
			}
			if(turn < 4)
			{
				tableCards.add(deck.getFromTop());
				for(Player player : PlayersList)
				{
					if(turn == 1)
					{
						player.playerConnector.sendPlayerCards(tableCards.get(tableCards.size() - 3));
						player.playerConnector.sendPlayerCards(tableCards.get(tableCards.size() - 2));;
					}
					player.playerConnector.sendPlayerCards(tableCards.get(tableCards.size() - 1));
				}
			}
			turn++;
		}
		//dealerButtonPlayer.playerConnector.
		
		for(Player player : PlayersList)
		{
			player.evaluateHand();
		}
		
		Player winningPlayer = PlayersList.get(0);
		
		for(int i = 1; i < PlayersList.size(); i++)
		{
			Player comparingPlayer = PlayersList.get(i); 
			if(winningPlayer.getHand().compareTo(comparingPlayer.getHand()) < 0) winningPlayer = comparingPlayer;
		}
		
		for(Player player : PlayersList)
		{
			player.playerConnector.sendWinner(winningPlayer);
		}
		winningPlayer.setChips(winningPlayer.getChips() + pot);
		winningPlayer.modifyPlayer();
		pot = 0;
		
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
		player.changeNick("odszed³");
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
			s.BigBlind = 555;
		}
		
		if(s.initialize()) s.startGame();
		
		try {
			if(!s.serverSocket.isClosed()) s.serverSocket.close();
		} catch (Exception e) {
			return;
		}
	}
}
