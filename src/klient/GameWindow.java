package klient;

import java.awt.Color;

import javax.swing.*;

public class GameWindow extends JFrame{

	CardPanel cards;
	
	public GameWindow() {
		super("Texas Hold'em");
		cards = new CardPanel();
		add(cards);
		setSize(600, 300);
		setVisible(true);
	}
}
