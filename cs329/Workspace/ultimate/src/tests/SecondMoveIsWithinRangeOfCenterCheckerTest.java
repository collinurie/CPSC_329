package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;
import moveCheckers.MoveChecker;
import moveCheckers.SecondMoveIsWithinRangeOfCenterChecker;

/**
 * Tests for the SecondMoveIsWithinRangeOfCenterChecker. Ensures that for the second move of the game, pieces can only be placed within the given range.
 * @author mb5053
 *
 */
public class SecondMoveIsWithinRangeOfCenterCheckerTest {

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
		checker_ = new SecondMoveIsWithinRangeOfCenterChecker(board_, 3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {}

	/**
	 * Tests to make sure that the checker allows valid moves
	 */
	@Test
	public void testSecondMoveIsInRange () {
		assertTrue(checker_.isValidMove(player_,1,board_.getCenterRow()+3,board_.getCenterCol() + 1));
	}
	
	/**
	 * Tests to make sure that the checker does not allow invalid moves
	 */
	@Test
	public void testSecondMoveIsNotInRange () {
		assertFalse(checker_.isValidMove(player_,1,board_.getCenterRow()-2,board_.getCenterCol() + 1));
	}

}
