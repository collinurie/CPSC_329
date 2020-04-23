package tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.Player;

/**
 * Test cases for Player class
 * @author mb5053
 *
 */
public class PlayerTest {
	Player player1_;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		player1_ = new LocalPlayer("Lasseter");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {
		Player.resetPlayerCount();
	}

	@Test
	public void testNewPlayerCaptures () {
		 assertTrue(player1_.getCaptures() == 0);
	}
	
	@Test
	public void testPlayerPieceNumber() {
		Player player2 = new LocalPlayer("Eck");
		Player player3 = new LocalPlayer("Bridgeman");
		assertTrue(player1_.getPieceNum() == 1 && player2.getPieceNum() == 2 && player3.getPieceNum() == 3 );
	}
	
	@Test
	public void testAddCapture() {
		int before = player1_.getCaptures();
		player1_.addCapture();
		assertTrue(player1_.getCaptures() == before + 1);
	}
	

}