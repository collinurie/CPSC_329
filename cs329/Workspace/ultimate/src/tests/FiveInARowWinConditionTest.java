package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;
import winConditions.FiveInARowWinCondition;
import winConditions.WinCondition;

/**
 * Test cases for checking if there are 5 pieces in a row.
 * 
 * @author mb5053
 */
public class FiveInARowWinConditionTest {

	Player player_;
	PenteBoard board_;
	WinCondition condition_;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		player_ = new LocalPlayer("Vaughn (RIP)");
		board_ = PenteBoard.createNewPenteBoard(19,19);
		condition_ = new FiveInARowWinCondition(board_);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {
		Player.resetPlayerCount();
	}

	/**
	 * Test to make sure five in a row diagonally is detected
	 */
	@Test
	public void testFiveInARowWinsDiag () {
		board_.placePiece(player_,0,0);
		board_.placePiece(player_,1,1);
		board_.placePiece(player_,2,2);
		board_.placePiece(player_,3,3);
		board_.placePiece(player_,4,4);
		assertTrue(condition_.checkCondition(player_));
	}

	/**
	 * Test to make sure five in a row in a row is detected
	 */
	@Test
	public void testFiveInARowWinsRow () {
		board_.placePiece(player_,0,0);
		board_.placePiece(player_,1,0);
		board_.placePiece(player_,2,0);
		board_.placePiece(player_,3,0);
		board_.placePiece(player_,4,0);
		assertTrue(condition_.checkCondition(player_));
	}

	/**
	 * Test to make sure five in a row in a column is detected
	 */
	@Test
	public void testFiveInARowWinsCol () {
		board_.placePiece(player_,14,14);
		board_.placePiece(player_,15,15);
		board_.placePiece(player_,16,16);
		board_.placePiece(player_,17,17);
		board_.placePiece(player_,18,18);
		assertTrue(condition_.checkCondition(player_));
	}

	/**
	 * Test to make sure five in a row on the edge of the board is detected
	 */
	@Test
	public void testFiveInARowWinsTowardsEdgeOfBoard () {
		board_.placePiece(player_,0,0);
		board_.placePiece(player_,1,0);
		board_.placePiece(player_,2,0);
		board_.placePiece(player_,3,0);
		board_.placePiece(player_,4,0);
		assertTrue(condition_.checkCondition(player_));
	}

	/**
	 * Test to make sure 4 in a row does not count as a win
	 */
	@Test
	public void testFourInARowIsNotAWin () {
		board_.placePiece(player_,0,0);
		board_.placePiece(player_,1,0);
		board_.placePiece(player_,2,0);
		board_.placePiece(player_,3,0);
		assertFalse(condition_.checkCondition(player_));
	}

	/**
	 * Test to make sure method can count 5 in a row if the winning piece is
	 * placed in the middle of the 5
	 */
	@Test
	public void testPieceInTheMiddleOfFiveWins () {
		board_.placePiece(player_,0,0);
		board_.placePiece(player_,1,0);
		board_.placePiece(player_,4,0);
		board_.placePiece(player_,2,0);
		board_.placePiece(player_,3,0);
		assertTrue(condition_.checkCondition(player_));
	}

	/**
	 * Test to make sure 5 pieces in a row from different players does not
	 * register as a win
	 */
	@Test
	public void testMixedPlayersDoesNotWin () {
		Player player2 = new LocalPlayer("Lasseter");
		board_.placePiece(player_,0,0);
		board_.placePiece(player2,1,0);
		board_.placePiece(player_,2,0);
		board_.placePiece(player_,3,0);
		board_.placePiece(player_,4,0);
		assertFalse(condition_.checkCondition(player_));
	}

}