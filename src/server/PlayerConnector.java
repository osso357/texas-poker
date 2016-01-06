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
	private String nick;
	
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
	
	public void sendInitialMessage(List<Player> playerList)
	{
		
	}
	
	@Override
	public boolean isConnected()
	{
		return playerSocket.isConnected();
	}
	
	public PlayerConnector()
	{
		
	}

}
