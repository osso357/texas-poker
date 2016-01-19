package klient;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//import javax.swing.event.*;

public class ConnectorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5513393657278825789L;

	Client client;
	
	JTextField adres,port,imie;
	JLabel ladres;
	JLabel lport;
	JLabel limie;
	JButton wyslij;
	
	ConnectorWindow(Client client, WindowListener listener) {
	super("Texas Hold'em - connecting to server");
	this.client = client;
	addWindowListener(listener);
    setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
	setLayout(new GridLayout(4,2));
	imie = new JTextField(20);
	adres = new JTextField("localhost");
	port = new JTextField("65025");
	limie = new JLabel();
	ladres = new JLabel();
	lport = new JLabel();
	wyslij = new JButton("wyslij");
	wyslij.setActionCommand("connect");
	
	lport.setText(" port:");
	ladres.setText(" adres:");
	limie.setText(" imię:");
	
	add(limie);
	add(imie);
	add(ladres);
	add(adres);
	add(lport);
	add(port);
	add(wyslij);
	
	wyslij.addActionListener(client.listener);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
	}
	
}
