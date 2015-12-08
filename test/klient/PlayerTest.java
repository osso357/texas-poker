package klient;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testName() {
		String name = "Adrian";
		Player player = new Player(name, 1000);
		assertEquals(player.getName(), name);
	}
	
	@Test
	public void testCredits() {
		int credits = 1000;
		Player player = new Player("Adam", credits);
		assertEquals(player.getCredits(), credits);
	}
	
	@Test
	public void testCretitsChange() {
		int credits = 1000;
		Player player = new Player("Adam", 500);
		player.changeCredits(credits);
		assertEquals(player.getCredits(), credits);
	}
}
