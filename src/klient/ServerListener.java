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
				client.gamewin.cards.update();
			}
			else if (mtable[0].equals("CLR")) {
				game.clearCards(); //wyzeruj karty
			}
			else if (mtable[0].equals("L")) { //licytacja
				client.gamewin.bet.setState(Integer.parseInt(mtable[1]));
			}
			else if (mtable[0].equals("E")) { //Błąd wymuszający konie gry E:[komunikat]
				if(client.gamewin != null)
				{
					client.gamewin.setVisible(false);
				}
				if(client.ww != null)
				{
					client.ww.setVisible(false);
				}
				if(client.connector != null)
				{
					client.connector.setVisible(false);
				}
				client.error = new ErrorWindow(mtable[1], client.listener);
			}
			else if (mtable[0].equals("P")) { //P:x:y:z  x - nr gracza, y - dostępne kredyty, z - aktualny zakład
				game.players.get(Integer.parseInt(mtable[1])).changeCredits(Integer.parseInt(mtable[2]));
				game.players.get(Integer.parseInt(mtable[1])).changeBet(Integer.parseInt(mtable[3]));
				client.refreshPlayers();
			}
			else if (mtable[0].equals("N")) { //N:x:abc  x - nr gracza, abc - nowy nick
				game.players.get(Integer.parseInt(mtable[1])).changeName((mtable[2]));
				client.refreshPlayers();
				System.out.println("zmiana nicka");
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

