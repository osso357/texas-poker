package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class ErrorWindowTest {
	
	@Test
	public void testWindow() {
		Client client = new Client();
		WindowActions listener = new WindowActions(client);
		new ErrorWindow("error", listener);
	}

}
