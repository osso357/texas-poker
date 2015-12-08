package klient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;

@SuppressWarnings("serial")
public class CardPanel extends JPanel{

	Border blackline;
	TitledBorder border;
	
	public CardPanel() {
		setSize(700, 250);
		blackline = BorderFactory.createLineBorder(Color.black);
		border = BorderFactory.createTitledBorder("KARTY");
		setBorder(border);
		border.setTitleJustification(TitledBorder.LEFT);
	}
	
	public void addCart(int cardnumber) {
		//implement
	}
	
	public void removeCards() {
		//implement
	}
	
	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(10, 20, 120, 160, 20, 20);
        g.fillRoundRect(160, 20, 120, 160, 20, 20);
    }
}
