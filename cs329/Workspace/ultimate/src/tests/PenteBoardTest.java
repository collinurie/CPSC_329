package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;

/**
 * Test cases for PenteBoard class
 * @author mb5053
 */
public class PenteBoardTest {
	int[][] emptyTestBoard = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

	int[][] sampleTestBoard = { { 1, 0, 1 }, { 0, 2, 0 }, { 0, 0, 2 } };

	Player player1_;
	Player player2_;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		Player.resetPlayerCount();
		player1_ = new LocalPlayer("Eck");
		player2_ = new LocalPlayer("Vaughn (RIP)");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {
		Player.resetPlayerCount();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateEmptyBoard () {
		int[][] test = PenteBoard.createNewPenteBoard(3,3).getBoard();
		assertEquals(test,emptyTestBoard);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testPlacePiece () {
		PenteBoard board = PenteBoard.createNewPenteBoard(3,3);
		board.placePiece(player1_,0,0);
		board.placePiece(player1_,0,2);
		board.placePiece(player2_,1,1);
		board.placePiece(player2_,2,2);
		PenteBoard target =
		    PenteBoard.createNewPenteBoardFromArray(sampleTestBoard);
		assertEquals(board.getBoard(),target.getBoard());
	}

}
