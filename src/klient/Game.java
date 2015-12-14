package klient;

import java.util.ArrayList;


public class Game {
	
	ArrayList<Player> Players;
	Card[] Cards;
	int cardscounter;
	
	
	public Game() {
		Players = new ArrayList<Player>();
		Cards = new Card[7];
		cardscounter = 0;
	}
	
	
	public void addPlayer(String name, int credits) {
		Players.add(new Player(name, credits));
	}
	
	public void remPlayer() {
		//TODO: implement!!!
	}
	
	public void addCart(int card) {
		Cards[cardscounter] = new Card(card);
	}
	
	public int countPlayers() {
		return Players.size();
	}

}
