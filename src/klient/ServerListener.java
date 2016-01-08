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
	Game game;
	Client client;
	
	ServerListener(String host, int port, Game texas, Client parent) throws IOException, UnknownHostException{
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		changed = 0;
		game = texas;
		client = parent;
	}
	
	public void run()
	{
	try
		{
		while (stop == 0)
			{
			write("Test");
			mes = in.readLine();
			System.out.println(mes);
			
			mtable = mes.split(":");
			
			if (mtable[0].equals("S")) { //S:0A:0B:1A:1B:2A:2B... nA - nazwa gracza, nB - ilość kredytów
				System.out.println("START!");
				int i = 0;
				while (i < ((mtable.length - 1) / 2)) {
					game.addPlayer(mtable[2*i+1], Integer.parseInt(mtable[2*i+2]));
				i++;
				}
				game.clearCards();
				client.newGame();
			}
			else if (mtable[0].equals("M")) {
				game.message = mtable[1]; //Pokaż wiadomość
				client.refreshMessage();
			}
			else if (mtable[0].equals("C")) {
				game.addCart(Integer.parseInt(mtable[1])); //Dodaj kartę
			}
			else if (mtable[0].equals("CLR")) {
				game.clearCards(); //wyzeruj karty
			}
			else if (mtable[0].equals("L")) { //licytacja
				//ignore
			}
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

