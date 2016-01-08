package klient;

import org.junit.Test;


public class ConnectorWindowTest {

	@Test
	public void testConnectorWindow() {
		Client client = new Client();
		WindowActions listener = new WindowActions(client);
		ConnectorWindow connector = new ConnectorWindow(client, listener);
	}
}
