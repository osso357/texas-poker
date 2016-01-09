package server;

import java.util.List;

public class Player
{
	private Hand playerHand;
	private String nick;
	private int Chips;
	public PlayerConnector playerConnector;
	
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
	
	public boolean isConnected()
	{
		return playerConnector.isConnected();
	}
	
	public Player(PlayerConnectorAdapter playerConnector)
	{
		this.playerConnector = (PlayerConnector) playerConnector;
	}
	
}

