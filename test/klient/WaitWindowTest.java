package klient;

import static org.junit.Assert.*;

import org.junit.Test;


public class WaitWindowTest {

	@Test
	public void testWaitWindow() {
		WaitWindow ww = new WaitWindow();
		ww.setMessage("new message");
		assertEquals(ww.message.getText(), "new message");
	}
}
