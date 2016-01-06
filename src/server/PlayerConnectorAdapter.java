package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public interface PlayerConnectorAdapter
{

	Socket initializeSocket(ServerSocket serverSocket) throws IOException;
	boolean isConnected();
	
	//public void 
}
