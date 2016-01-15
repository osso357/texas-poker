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
		if(!PlayersList.get(actualPlayer).inGame && playersLeft() > 1) actualPlayer = getNextPlayer(); 
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
			player.setChips(Chips);
			System.out.println("Dolaczyl gracz, nick: " + player.getNick());
			
			
		}
		
		return true;
	}
	
	public boolean checkIfBetsTheSame()
	{
		int playerBet = 0;
		for(Player player : PlayersList)
		{
			if(player.folded || player.allIn) continue;
			playerBet = player.getActualBet();
			break;
		}
		
		for(Player player : PlayersList)
		{
			if(player.folded || player.allIn || player.getChips() == 0) continue;
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
	
	public boolean allPlayersMoved()
	{
		int howManyPlayersLeft = PlayersList.size();
		int foldedPlayers = 0;
		for(Player player : PlayersList)
		{
			if(player.folded || player.allIn) foldedPlayers++;
			//if(!player.didMove) return false;
			if(player.didMove) howManyPlayersLeft--;
		}
		if(foldedPlayers >= PlayersList.size() - 1) return true;
		if(howManyPlayersLeft >= 1) return false;
		return true;
	}
	
	public int playersLeft()
	{
		int inGame = 0;
		for(Player player : PlayersList)
		{
			if(player.inGame) inGame++;
		}
		return inGame;
	}
	
	public void startGame()
	{
		System.out.println("Startuje gre");
		
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
		int dealerButtonIndex = (int)(Math.floor(Math.random()*PlayersList.size()));
		
		
		
		

		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		while(playersLeft() > 1)
		{
			dealerButtonIndex = (dealerButtonIndex + 1 >= PlayersList.size()) ? 0 : dealerButtonIndex + 1;
			Player dealerButtonPlayer = PlayersList.get(dealerButtonIndex);
			
			setActualPlayer(dealerButtonIndex);
			
			deck = new Deck();
			deck.shuffleDeck();
			
			tableCards = new ArrayList<Card>();
			for(Player player : PlayersList)
			{
				if(player == dealerButtonPlayer)
				{
					player.playerConnector.sendMessage("M:Otrzymujesz Dealer Button");
					
				}
				else if(player.inGame) player.playerConnector.sendMessage("M:Gracz " + dealerButtonPlayer.getNick() + " otrzymuje Dealer Button");

				for(Player player2 : PlayersList)
				{
					if(player2.inGame) player.playerConnector.changeNick(player2, player2.getNick());
					else player.playerConnector.changeNick(player2, "odpadł");
				}
				player.playerConnector.changeNick(dealerButtonPlayer, "@" + dealerButtonPlayer.getNick());
				
				
				Card card1, card2;
				card1 = deck.getFromTop();
				card2 = deck.getFromTop();
				player.addCards(card1, card2, tableCards);
			}
			
			if(PlayersList.get(getNextPlayer()).getChips() < SmallBlind)
			{
				removePlayer(PlayersList.get(getActualPlayer()));
				continue;
			}
			
			PlayersList.get(getActualPlayer()).addToBet(SmallBlind);
			PlayersList.get(getActualPlayer()).modifyPlayer();
			PlayersList.get(getActualPlayer()).didMove = true;
			
			if(PlayersList.get(getNextPlayer()).getChips() < BigBlind)
			{
				removePlayer(PlayersList.get(getActualPlayer()));
				continue;
			}
			
			PlayersList.get(getActualPlayer()).addToBet(BigBlind);
			PlayersList.get(getActualPlayer()).modifyPlayer();
			PlayersList.get(getActualPlayer()).didMove = true;
			
			maxBet = BigBlind;
			
			
			
			//Licytacja
			
			int turn = 1;
			while(turn < 5)
			{
				int startingPlayerIndex = getActualPlayer();
				//System.out.println("starting=" + startingPlayerIndex + ", actual=" + getActualPlayer() + "\n");
	
				System.out.println("checkBets = " + checkIfBetsTheSame() + ", allPM = " + allPlayersMoved());
				while(!(checkIfBetsTheSame() && allPlayersMoved()))
				{
					boolean error = false;
					
					Player actualPlayer = PlayersList.get(getNextPlayer());
					
					if(actualPlayer.folded || actualPlayer.allIn || !actualPlayer.inGame) continue;

					for(Player player : PlayersList)
					{
						String dbchar = "";
						String apchar = "";
						if(dealerButtonPlayer == player) dbchar = "@";
						if(actualPlayer == player) apchar = "> ";
						for(Player player2 : PlayersList)
						{
							if(player.inGame) player2.playerConnector.changeNick(player, apchar + dbchar + player.getNick());
							else player2.playerConnector.changeNick(player, "odpadł");
						}
						player.enableButton(7);
					}
					
					do
					//Ustawianie guzikow
					{
						error = false;
						//check
						if(checkIfBetsZero()) actualPlayer.enableButton(1);
						//Bet
						if(checkIfBetsZero() && actualPlayer.getChips() > 0) actualPlayer.enableButton(2);
						//Raise
						if(maxBet > actualPlayer.getActualBet() && actualPlayer.getChips() > maxBet - actualPlayer.getActualBet()) actualPlayer.enableButton(3);
						//Call
						if(maxBet > actualPlayer.getActualBet() && actualPlayer.getChips() >= maxBet - actualPlayer.getActualBet()) actualPlayer.enableButton(4);
						//fold
						actualPlayer.enableButton(5);
						//AllIn
						if(maxBet - actualPlayer.getActualBet() > actualPlayer.getChips()) actualPlayer.enableButton(6);
						
						
						//actualPlayer.setBiddingStatus(maxBet);
						//if(actualPlayer.folded) continue;
						String messageReceived;
						try
						{ 
							messageReceived = actualPlayer.playerConnector.receiveMessage();
						}
						catch(IOException e)
						{
							removePlayer(actualPlayer);
							
							continue;
						}

						actualPlayer.enableButton(7);
						
						if(messageReceived.equals("FOLD")){
							actualPlayer.folded = true;
						}
						//else if(messageReceived.equals("CHECK")) continue;
						else if(messageReceived.equals("CALL"))
						{
							actualPlayer.addToBet(maxBet - actualPlayer.getActualBet());
							actualPlayer.modifyPlayer();
						}
						else if(messageReceived.contains("BET"))
						{
							int actualPlayerBet;
							try
							{
								actualPlayerBet = Integer.parseInt(messageReceived.split(":")[1]);
								if(actualPlayerBet < 0) throw new NumberFormatException();
							}
							catch(NumberFormatException e)
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Nie poprawne dane!");
								continue;
							}
							catch(ArrayIndexOutOfBoundsException e)
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Nie poprawne dane!");
								continue;
							}
							if(actualPlayerBet > actualPlayer.getChips())
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Nie masz tyle żetonów!");
								continue;
							}
							actualPlayer.addToBet(actualPlayerBet);
							actualPlayer.modifyPlayer();
							maxBet = actualPlayer.getActualBet();
						}
						else if(messageReceived.contains("RAISE"))
						{
							int actualPlayerBet;
							try
							{
								actualPlayerBet = Integer.parseInt(messageReceived.split(":")[1]);
								if(actualPlayerBet < 0) throw new NumberFormatException();
							}
							catch(NumberFormatException e)
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Nie poprawne dane!");
								continue;
							}
							catch(ArrayIndexOutOfBoundsException e)
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Nie poprawne dane!");
								continue;
							}
							
							if(actualPlayerBet > actualPlayer.getChips())
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Nie masz tyle żetonów!");
								continue;
							}
							else if(actualPlayerBet < maxBet)
							{
								error = true;
								actualPlayer.playerConnector.sendMessage("M:Musisz przebić maksymalny zakład!");
								continue;
							}
							actualPlayer.addToBet(actualPlayerBet);
							actualPlayer.modifyPlayer();
							maxBet = actualPlayer.getActualBet();
						}
						else if(messageReceived.equals("ALLIN"))
						{
							actualPlayer.allInBet = actualPlayer.getChips();
							actualPlayer.addToBet(actualPlayer.getChips());
							actualPlayer.allIn = true;
							actualPlayer.modifyPlayer();
						}
						else if(messageReceived.equals("CHECK"))
						{
							actualPlayer.modifyPlayer();
						}
					}
					while(error);
					actualPlayer.didMove = true;
					actualPlayer.modifyPlayer();
				}
				
				
				for(Player player : PlayersList)
				{
					pot += player.getActualBet();
					player.setActualBet(0);
					maxBet = 0;
					player.didMove = (player.allIn || player.folded) ? true : false;
				}
				
				for(Player player : PlayersList)
				{
					player.modifyPlayer();
					if(player.inGame) player.playerConnector.sendMessage("M:Pula wynosi " + pot + " żetonów");
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
				if(!player.folded) player.evaluateHand();
				else player.getHand().handValue = new int[]{0,0,0,0,0,0};
			}
			
			Player winningPlayer = PlayersList.get(0);
			boolean tie = false;
			List<Player> tiePlayers = new ArrayList<Player>();
			
			for(int i = 1; i < PlayersList.size(); i++)
			{
				Player comparingPlayer = PlayersList.get(i); 
				if(comparingPlayer == winningPlayer) continue;
				if(winningPlayer.getHand().compareTo(comparingPlayer.getHand()) < 0) winningPlayer = comparingPlayer;
			}
			
			for(Player player : PlayersList)
			{
				if(player == winningPlayer) continue;
				if(winningPlayer.getHand().compareTo(player.getHand()) == 0)
				{
					tie = true;
					tiePlayers.add(player);
				}
			}
			
			if(!winningPlayer.allIn)
			{
				if(tie)
				{
					int gain = (pot / (tiePlayers.size() + 1));
					winningPlayer.setChips(winningPlayer.getChips() + gain);
					for(Player player : tiePlayers) player.setChips(winningPlayer.getChips() + gain);
				}
				else winningPlayer.setChips(winningPlayer.getChips() + pot);
				pot = 0;
			}
			else
			{
				winningPlayer.setChips(pot - ((playersLeft() - 1) * winningPlayer.getActualBet()));
				pot -= ((playersLeft() - 1) * winningPlayer.getActualBet());
				
				winningPlayer.setActualBet(0);
			}
			winningPlayer.modifyPlayer();
			
			for(Player player : PlayersList)
			{
				player.playerConnector.sendWinner(winningPlayer);
				player.folded = false;
				player.allIn = false;
				player.didMove = false;
				player.setActualBet(0);
			}
			
			for(Player player : PlayersList)
			{
				if(player.getChips() <= 0)
				{
					removePlayer(player);
					player.playerConnector.sendMessage("M:Nie masz już żetonów. Odpadasz!");
					break;
				}
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for(Player player : PlayersList) player.playerConnector.clearCards();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		for(Player player : PlayersList)
		{
			if(player.getChips() > 0) player.playerConnector.sendMessage("M:Zwycięzca! Gra zakończy sie za 10 sekund");
			else player.playerConnector.sendMessage("M:Przegrałeś! Gra zakończy sie za 10 sekund");
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void removePlayer(Player player)
	{
		for(Player player2 : PlayersList) player2.playerConnector.changeNick(player, "odpadł");
		player.setChips(0);
		player.setActualBet(0);
		player.modifyPlayer();
		player.inGame = false;
		//PlayersList.remove(player);
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
			s.PlayersNumber = 3;
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
