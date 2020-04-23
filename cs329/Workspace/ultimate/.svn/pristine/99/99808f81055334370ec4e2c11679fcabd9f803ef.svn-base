package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;
import moveCheckers.MoveChecker;
import moveCheckers.MoveIsEmptyChecker;

/**
 * Testing the isValidMove Method from the MoveIsEmptyChecker class
 * 
 * @author ms5976
 */
public class MoveIsEmptyCheckerTest {

	private Player player_;
	private Player player2_;
	private PenteBoard board_;
	MoveChecker checker_;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		player_ = new LocalPlayer("Eck");
		player2_ = new LocalPlayer("Spring");
		board_ = PenteBoard.createNewPenteBoard(19,19);
		checker_ = new MoveIsEmptyChecker(board_);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {
		Player.resetPlayerCount();
	}

	/**
	 * Placing a piece onto a piece that has already been placed
	 */
	@Test
	public void testPlayerMovesOntoPiece () {
		board_.placePiece(player_,15,15);
		assertFalse(checker_.isValidMove(player2_,3,15,15));

	}

	/**
	 * Placing a piece on an empty spot
	 */
	@Test
	public void testPlayerMovesOntoEmpty () {
		board_.placePiece(player_,15,15);
		assertTrue(checker_.isValidMove(player2_,3,16,15));

	}

}
