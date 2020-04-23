package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveChecker;

/**
 * Test cases for FirstMoveIsCenterChecker. Ensures that the first move is placed in the center of the board.
 * @author mb5053
 *
 */
public class FirstMoveIsCenterCheckerTest {
	
	private PenteBoard board_;
	private Player player_;
	private MoveChecker checker_;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		player_ = new LocalPlayer("Eck");
		board_ = PenteBoard.createNewPenteBoard(19,19);
		checker_ = new FirstMoveIsCenterChecker(board_);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {}

	/**
	 * Tests that the checker allows a valid first move
	 */
	@Test
	public void testValidFirstMove () {
		assertTrue(checker_.isValidMove(player_,0,board_.getCenterRow(),board_.getCenterCol()));
	}
	
	/**
	 * Tests that the checker disallows invalid first moves
	 */
	@Test
	public void testInvalidFirstMove () {
		assertFalse(checker_.isValidMove(player_,0,board_.getCenterRow()+1,board_.getCenterCol()));
	}

	
}
