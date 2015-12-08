package klient;

import java.io.*;
import java.net.*;

class ServerListener extends Thread{
	
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	int stop = 0;
	String mes;
	String[] mtable;
	int changed;

	ServerListener(String host, int port) throws IOException, UnknownHostException{
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		changed = 0;
	}
	
	public void run()
	{
	try
		{
		while (stop == 0)
			{
			mes = in.readLine();
			System.out.println(mes);
			
			/*mtable = mes.split(":");
			
			if (mtable[0].equals("S"))
				{
				System.out.println("START!");

				}
			else if (mtable[0].equals("AED"))
			{
			//ignore
			}
			else if (mtable[0].equals("AC"))
			{
			//ignore
			}
			else if (mtable[0].equals("INC"))
				{
				//System.out.println("Listener - New cards!");
				String cardsTable[] = new String[4];
				for (int i = 0; i < 4; i++)
					{
						cardsTable[i] = mtable[i+1];
					}
					if (changed == 1)
					{
						changed = 0;
					}
					else
					{
						changed = 1;
					}
				}
			else if (mtable[0].equals("AM"))
				{
				if (mtable[4].equals("Bet"))
					{
					}
				}
			else
				{
					System.out.println("error");
				}*/
			}
		}
		catch  (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
	}
	
	public void write(String message){
		out.println(message);
	}


}

