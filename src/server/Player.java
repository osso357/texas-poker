package server;

import java.util.List;

public class Player
{
	private Hand playerHand;
	private String nick;
	private int Chips;
	private int actualBid;
	private int indexNumber;
	public PlayerConnector playerConnector;
	
	public void setActualBid(int bid)
	{
		this.actualBid = bid;
	}
	
	public int getActualBid()
	{
		return this.actualBid;
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
	
	public void addCards(Card card1, Card card2, List<Card> tableCardsReference)
	{
		this.playerHand = new Hand(tableCardsReference);
		playerHand.addToHand(card1);
		playerHand.addToHand(card2);
		playerConnector.sendPlayerCards(card1, card2);
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
		playerConnector.modifyPlayer(Chips, actualBid);
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

