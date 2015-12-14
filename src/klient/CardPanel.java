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
		setPreferredSize(new Dimension(700,160));
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("KARTY");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
		game = currentGame;
		setFont(new Font("Serif", Font.PLAIN, 28));
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
		//implement
	}
	
	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < 7; i++) {
			if(game.Cards[i] != null) {
				g.setColor(Color.WHITE);
		        g.fillRoundRect(10 + 100*i, 20, 80, 120, 20, 20);
		        int suite = game.Cards[i].getSuite();
		        if (suite == 0){
		        	g.drawImage(spade, 27 + 100*i, 30, null);
		        }
		        else if (suite == 1) {
		        	g.drawImage(heart, 27 + 100*i, 30, null);
		        }
		        else if (suite == 2) {
		        	g.drawImage(diamond, 27 + 100*i, 30, null);
		        }
		        else if (suite == 3) {
		        	g.drawImage(club, 27 + 100*i, 30, null);
		        }
		        else {
		        	//error, KURWA!
		        }
		        g.setColor(Color.BLACK);
		        g.drawString(Strenght2String(game.Cards[i].getStrenght()), 33 + 100*i, 110);
			}
		}
    }
	
	private String Strenght2String(int strenght) {
		System.out.println("int: " + strenght);
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
		System.out.println("str: " + strstrenght);
		return strstrenght;
	}
}
