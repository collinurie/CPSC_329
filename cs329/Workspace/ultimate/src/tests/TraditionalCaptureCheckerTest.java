package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pente.LocalPlayer;
import Pente.PenteBoard;
import Pente.Player;
import captureCheckers.CaptureChecker;
import captureCheckers.TraditionalCaptureChecker;
import moveCheckers.MoveChecker;
import moveCheckers.SecondMoveIsWithinRangeOfCenterChecker;
import ruleSets.RuleSet;

/**
 * @author mb5053
 */
public class TraditionalCaptureCheckerTest {

	private PenteBoard board_;
	private Player player1_;
	private Player player2_;
	private CaptureChecker checker_;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		player1_ = new LocalPlayer("Eck");
		player2_ = new LocalPlayer("Vaughn (RIP)");
		board_ = PenteBoard.createNewPenteBoard(19,19);
		checker_ = new TraditionalCaptureChecker(board_);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {
		Player.resetPlayerCount();
	}

	@Test
	public void testCaptureHorizontal () {
		board_.placePiece(player2_,1,1);
		board_.placePiece(player1_,1,2);
		board_.placePiece(player1_,1,3);
		board_.placePiece(player2_,1,4);
		checker_.executeCaptures(player2_,1,4);

		assertTrue(board_.getPosition(1,1) == player2_.getPieceNum()
		    && board_.getPosition(1,4) == player2_.getPieceNum()
		    && board_.getPosition(1,2) == 0 && board_.getPosition(1,3) == 0);
	}

	@Test
	public void testCaptureVertical () {
		board_.placePiece(player2_,6,5);
		board_.placePiece(player1_,7,5);
		board_.placePiece(player1_,8,5);
		board_.placePiece(player2_,9,5);
		checker_.executeCaptures(player2_,9,5);

		assertTrue(board_.getPosition(6,5) == player2_.getPieceNum()
		    && board_.getPosition(9,5) == player2_.getPieceNum()
		    && board_.getPosition(7,5) == 0 && board_.getPosition(8,5) == 0);

	}

	@Test
	public void testCaptureDiagonal () {
		board_.placePiece(player2_,1,1);
		board_.placePiece(player1_,2,2);
		board_.placePiece(player1_,3,3);
		board_.placePiece(player2_,4,4);
		checker_.executeCaptures(player2_,4,4);

		assertTrue(board_.getPosition(1,1) == player2_.getPieceNum()
		    && board_.getPosition(4,4) == player2_.getPieceNum()
		    && board_.getPosition(2,2) == 0 && board_.getPosition(3,3) == 0);

	}

	@Test
	public void testCaptureVerticalOutOfOrder() {
		board_.placePiece(player1_,1,3);
		board_.placePiece(player1_,1,2);
		board_.placePiece(player2_,1,1);
		board_.placePiece(player2_,1,4);
		checker_.executeCaptures(player2_,1,4);
		
		assertTrue(board_.getPosition(1,1) == player2_.getPieceNum() && board_.getPosition(1,4) == player2_.getPieceNum() && board_.getPosition(1,2) == 0 && board_.getPosition(1,3) == 0);
	}

}
