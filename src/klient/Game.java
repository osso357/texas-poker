package klient;

import java.util.ArrayList;


public class Game {
	
	ArrayList<Player> players;
	Card[] cards;
	int cardscounter;
	String message;
	
	
	public Game() {
		players = new ArrayList<Player>();
		cards = new Card[7];
		cardscounter = 0;
		message = "No message yet...";
	}
	
	
	public void addPlayer(String name, int credits) {
		players.add(new Player(name, credits));
	}
	
	/*public void remPlayer() {
		//TODO: implement!!!
	}*/
	
	public void addCart(int card) {
		cards[cardscounter] = new Card(card);
		cardscounter++;
	}
	
	public void clearCards() {
		for (int i = 0; i < 7; i++) {
			cards[i] = null;
		}
		cardscounter = 0;
	}
	
	public int countPlayers() {
		return players.size();
	}

}
