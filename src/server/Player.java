package server;


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
	
	public void setChips(int chips)
	{
		this.Chips = chips;
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

