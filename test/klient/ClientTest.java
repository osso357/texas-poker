package klient;

import server.*;
import static org.junit.Assert.*;

import org.junit.Test;

import server.Table;

public class ClientTest {
	
	@Test
	public void testMain() {
		Client.main();
		//assertEquals(client.getName(), name);
	}

	@Test
	public void testNewGame() {
		Client client = new Client();
		client.ww = new WaitWindow();
		client.newGame();
		assertNotNull(client.gamewin);
	}
	
	@Test
	public void testRefreshMessages() {
		Client client = new Client();
		client.ww = new WaitWindow();
		client.newGame();
		client.game.message = "test";
		client.refreshMessage();
		assertEquals(client.gamewin.messages.message.getText(), "test");
	}
	
	/*@Test
	public void testConnect() {
		Client client = new Client();
		client.connector.adres.setText("localhost");
		client.connector.port.setText("65025");
		client.connector.imie.setText("65025");
		String args[] = new String[2];
		Table.main(args);
		client.connect();
	}*/
}
