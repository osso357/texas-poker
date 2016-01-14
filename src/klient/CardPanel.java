package klient;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class CardPanel extends JPanel{

	Border blackline;
	TitledBorder border;
	Game game;
	BufferedImage spade, heart, diamond, club;
	
	public CardPanel(Game currentGame) {
		setPreferredSize(new Dimension(700,180));
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("KARTY");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		game = currentGame;
		//setFont(new Font("Serif", Font.PLAIN, 28)); //Przeniesione w inne miejsce
		try {
		    spade = ImageIO.read(new File("img/spade.png"));
		    heart = ImageIO.read(new File("img/heart.png"));
		    diamond = ImageIO.read(new File("img/diamond.png"));
		    club = ImageIO.read(new File("img/club.png"));
		} catch (IOException e) {
			System.out.println("Image loading error!");
		}
	}
	
	public void update() {
		repaint();
	}
	
	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.GRAY);
        g.drawLine(200, 15, 200, 170);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("TWOJA RĘKA", 55, 30);
        g.drawString("WSPÓLNE", 405, 30);
        
        for (int i = 0; i < 7; i++) {
			if(game.cards[i] != null) {
				g.setColor(Color.WHITE);
		        g.fillRoundRect(10 + 100*i, 40, 80, 120, 20, 20);
		        int suite = game.cards[i].getSuite();
		        if (suite == 0){
		        	g.drawImage(spade, 27 + 100*i, 50, null);
		        }
		        else if (suite == 1) {
		        	g.drawImage(heart, 27 + 100*i, 50, null);
		        }
		        else if (suite == 2) {
		        	g.drawImage(diamond, 27 + 100*i, 50, null);
		        }
		        else if (suite == 3) {
		        	g.drawImage(club, 27 + 100*i, 50, null);
		        }
		        
		        g.setColor(Color.BLACK);
		        g.setFont(new Font("Serif", Font.PLAIN, 28));
		        
		        int offset = 41;
		        
		        if(game.cards[i].getStrenght() == 8)
		        {
		        	offset = 37;
		        }
		        
		        g.drawString(strenght2String(game.cards[i].getStrenght()), offset + 100*i, 135);
			}
		}
    }
	
	private String strenght2String(final int strenght) { // NOPMD by fryzjer on 1/5/16 5:38 PM
		String strstrenght = "";
		if (strenght <= 8)
		{
			strstrenght = Integer.toString(strenght+2);
		}
		else if (strenght == 9) {
			strstrenght = "J";
		}
		else if (strenght == 10) {
			strstrenght = "Q";
		}
		else if (strenght == 11) {
			strstrenght = "K";
		}
		else if (strenght == 12) {
			strstrenght = "A";
		}
		return strstrenght;
	}
}
