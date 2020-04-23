package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;
import winConditions.NumberCapturesWinCondition;
import winConditions.WinCondition;

/**
 * Testing the methods in the NumberCapturesWinCondition class
 * 
 * @author ms5976
 */
public class NumberCapturesWinConditionTest {

	Player player_;
	PenteBoard board_;
	WinCondition conditon_;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		player_ = new LocalPlayer("Eck");
		board_ = PenteBoard.createNewPenteBoard(19,19);
		conditon_ = new NumberCapturesWinCondition(5);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {}


	/**
	 * Testing to see if there is a win after 5 captures are found
	 */
	@Test
	public void testFind5Caps () {
		player_.addCapture();
		player_.addCapture();
		player_.addCapture();
		player_.addCapture();
		player_.addCapture();

		assertTrue(conditon_.checkCondition(player_));

	}

	/**
	 * Testing to see if there is a win after less than 5 captures are found
	 */
	@Test
	public void testFindLess5Caps () {
		player_.addCapture();
		player_.addCapture();
		player_.addCapture();
		player_.addCapture();

		assertFalse(conditon_.checkCondition(player_));

	}

}
