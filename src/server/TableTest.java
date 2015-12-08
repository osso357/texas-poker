package server;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class TableTest
{
	Table s;
	List<Socket> Players;
	List<BufferedReader> Inputs;
	List<PrintWriter> Outputs;
	
	@Before
	public void createPlayers() throws UnknownHostException, IOException
	{
		s = new Table();
		s.PlayersNumber = 4;
		s.initialize();
		Players = new ArrayList<Socket>();
		System.out.println("Dolaczanie graczy");
		for(int i = 0; i < 4; i++)
		{
			Socket Player = new Socket("localhost", 65023);
			BufferedReader in = new BufferedReader(new InputStreamReader(Player.getInputStream()));
			PrintWriter out = new PrintWriter(Player.getOutputStream());
			out.write("S:0:Gracz " + i);
			Players.add(Player);
			Inputs.add(in);
			Outputs.add(out);
			System.out.println("Dolaczyl gracz " + i);
		}
	}
	
	@Test
	public void startGameTest()
	{
		assertEquals(4, s.PlayersList.size());
	}
}
