package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public interface PlayerConnectorAdapter
{

	Socket initializeSocket(ServerSocket serverSocket) throws IOException;
	boolean isConnected();
	void sendDealerButtonInfo(Player dealerButtonPlayer);
	void bidding();
	String receiveMessage();
	void sendMessage(String message);
	void playerGainedChips(int chips);
	void sendPlayerCards(Card card1, Card card2);
	void changeNick(Player player, String newNick);
	
	//public void 
}
