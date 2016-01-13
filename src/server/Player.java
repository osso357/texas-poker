package server;

import java.util.List;

public class Player
{
	private Hand playerHand;
	private String nick;
	private int Chips;
	private int actualBet;
	private int indexNumber;
	private int state;
	public PlayerConnector playerConnector;
	
	public void setState(int state)
	{
		this.state = state;
		playerConnector.setBiddingStatus(state, Chips);
	}
	
	public int getState()
	{
		return this.state;
	}
	
	public void setActualBet(int bet)
	{
		this.actualBet = bet;
		this.Chips = this.Chips - this.actualBet;
	}
	
	public int getActualBet()
	{
		return this.actualBet;
	}
	
	public void setIndexNumber(int index)
	{
		this.indexNumber = index;
		playerConnector.setIndexNumber(this.indexNumber);
	}
	
	public int getIndexNumber()
	{
		return this.indexNumber;
	}
	
	public int getChips()
	{
		return this.Chips;
	}
	
	public void giveChips(int chips)
	{
		setChips(Chips+chips);
		playerConnector.playerGainedChips(chips);
	}
	
	public void setChips(int chips)
	{
		this.Chips = chips;
	}
	
	public void setBiddingStatus(int maxBet)
	{
		if(actualBet < maxBet && Chips + actualBet < maxBet) state = 1;
		else if(actualBet < maxBet) state = 2;
		else if(actualBet < maxBet && Chips > maxBet) state = 3;
		else if(maxBet == actualBet) state = 4;
		else if(actualBet == 0) state = 5;
		
		playerConnector.setBiddingStatus(state, Chips);
	}
	
	public void addCards(Card card1, Card card2, List<Card> tableCardsReference)
	{
		this.playerHand = new Hand(tableCardsReference);
		playerHand.addToHand(card1);
		playerHand.addToHand(card2);
		playerConnector.sendPlayerCards(card1);
		playerConnector.sendPlayerCards(card2);
	}
	
	public String getNick()
	{
		return nick;
	}
	
	public void setNick()
	{
		this.nick = playerConnector.getNick();
	}
	
	public void changeNick(String newNick)
	{
		playerConnector.changeNick(this, newNick);
		setNick();
	}
	
	public void modifyPlayer()
	{
		playerConnector.modifyPlayer(Chips, actualBet);
	}
	
	public boolean isConnected()
	{
		return playerConnector.isConnected();
	}
	
	public Player(PlayerConnectorAdapter playerConnector)
	{
		this.playerConnector = (PlayerConnector) playerConnector;
	}
	
}

