package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class PlayerConnector implements PlayerConnectorAdapter
{
	public Socket playerSocket;
	public PrintWriter playerOutputStream;
	public BufferedReader playerInputStream;
	private int PlayerIndex;
	private String nick;
	private List<Player> PlayersList;
	
	@Override
	public Socket initializeSocket(ServerSocket serverSocket) throws IOException
	{
		Socket playerSocket = serverSocket.accept();
		playerInputStream = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
		playerOutputStream = new PrintWriter(playerSocket.getOutputStream(), true);

		String msg = playerInputStream.readLine();
		//System.out.println(msg);
		if(msg != null && msg.contains("S:0:"))
		{
			this.nick = msg.split(":")[2];
		}
		
		return playerSocket;
	}
	
	public String getNick()
	{
		return nick;
	}
	
	
	public void sendInitialMessage()
	{
		String initialMessage;
		initialMessage = "S";
		for(int i = 0; i < PlayersList.size(); i++)
		{
			initialMessage += ":" + PlayersList.get(i).getNick();
			initialMessage += ":" + PlayersList.get(i).getChips();
		}
		System.out.println("step1");
		playerOutputStream.println(initialMessage);
		System.out.println("step2");
		System.out.println("Wysylam do gracza "+ nick +": " + initialMessage);
		
	/*	try {
			System.out.println(playerInputStream.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Override
	public boolean isConnected()
	{
		return playerSocket.isConnected();
	}
	
	public PlayerConnector(List<Player> PlayerList)
	{
		this.PlayersList = PlayerList;
	}

	public void setIndexNumber(int indexOf)
	{
		this.PlayerIndex = indexOf;
	}
	
	@Override
	public void sendDealerButtonInfo(Player dealerButtonPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBiddingStatus(int state, int remainingChips)
	{
		sendMessage("L:" + state + ":" + remainingChips);
	}
	
	@Override
	public String receiveMessage() throws IOException
	{
		String ret = null;
		try {
			ret = playerInputStream.readLine();
			
		} catch (IOException e) {
			throw e;
		}
		return ret;
	}


	public void sendPlayerCards(Card card1) {
		// TODO Auto-generated method stub
		playerOutputStream.println("C:" + (card1.getColor() * 13 + card1.getValue()));
		//System.out.println("Do gracza " + nick + "C:" + (card1.getColor() * 13 + card1.getValue()) + " - Karta1, color: " + card1.getColor() + "value: "+ card1.getValue());
	}

	@Override
	public void sendMessage(String message)
	{
		playerOutputStream.println(message);
	}
	
	public void modifyPlayer(int chips, int bid)
	{
		for(Player player : PlayersList) player.playerConnector.sendMessage("P:" + PlayerIndex + ":" + chips + ":" + bid);
	}
	
	@Override
	public void changeNick(Player player, String newNick) {
		sendMessage("N:" + player.getIndexNumber() + ":" + newNick);
		this.nick = newNick;
	}

	public void sendWinner(Player winningPlayer) {
		int winnerIndex = winningPlayer.getIndexNumber();
		if(winnerIndex == this.PlayerIndex) sendMessage("M:Wygra³eœ!");
		else sendMessage("M:Gracz " + winningPlayer.getNick() + " wygrywa runde!");
	}

	public void enableButton(int buttonIndex,  int remainingChips)
	{
		sendMessage("B:" + buttonIndex);
	}

	public void clearCards()
	{
		sendMessage("CLR");
	}
	

}
