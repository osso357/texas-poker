package klient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;

@SuppressWarnings("serial")
public class CardPanel extends JPanel{

	Border blackline;
	TitledBorder border;
	Game game;
	
	public CardPanel(Game currentGame) {
		setSize(700, 250);
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("KARTY");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		game = currentGame;
	}
	
	public void update() {
		//implement
	}
	
	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (int i = 0; i < 7; i++) {
			if(game.Cards[i] == null) {
		        g.fillRoundRect(10 + 100*i, 20, 90+100*i, 120, 20, 20);

			}
		}
//        g.fillRoundRect(160, 20, 120, 160, 20, 20);
    }
}
