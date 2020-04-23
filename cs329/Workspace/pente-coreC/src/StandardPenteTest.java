import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Collin Urie, Famke Nouwens
 *
 */
class StandardPenteTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp () throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown () throws Exception {}

	@Test
	void testFirstMoveCenter () {
		Board board = new Board(19);
		StandardPente rules = new StandardPente(board);
		
		boolean result = rules.validMove(10,10,1);
		
		assertTrue(result);
	}
	
	@Test
	void testFirstMoveNotCenter () {
		Board board = new Board(19);
		StandardPente rules = new StandardPente(board);
		
		boolean result = rules.validMove(1,10,1);
		
		assertTrue(!result);
	}
	
	@Test
	void testWin5ConsecVertical() {
		Board board = new Board(19);
		board.placeStone(1,1,1);
		board.placeStone(2,2,1);
		board.placeStone(1,1,2);
		board.placeStone(2,2,4);
		board.placeStone(1,1,3);
		board.placeStone(2,2,6);
		board.placeStone(1,1,4);
		board.placeStone(2,2,7);
		board.placeStone(1,1,5);
		
		StandardPente rules = new StandardPente(board);

		boolean result = rules.gameOverCheckMove(1,1,5);
		
		 assertTrue(result);
	}
	
	@Test
	void testWin5ConsecDiagonal() {
		Board board = new Board(19);
		board.placeStone(1,1,1);
		board.placeStone(2,2,1);
		board.placeStone(1,2,2);
		board.placeStone(2,2,4);
		board.placeStone(1,3,3);
		board.placeStone(2,2,6);
		board.placeStone(1,4,4);
		board.placeStone(2,2,7);
		board.placeStone(1,5,5);
		
		StandardPente rules = new StandardPente(board);

		boolean result = rules.gameOverCheckMove(1,5,5);
		
		 assertTrue(result);
	}
	
	@Test
	void testWin5ConsecHorizontal() {
		Board board = new Board(19);
		board.placeStone(1,1,1);
		board.placeStone(2,2,2);
		board.placeStone(1,2,1);
		board.placeStone(2,2,4);
		board.placeStone(1,3,1);
		board.placeStone(2,2,6);
		board.placeStone(1,4,1);
		board.placeStone(2,2,7);
		board.placeStone(1,5,1);
		
		StandardPente rules = new StandardPente(board);

		boolean result = rules.gameOverCheckMove(1,5,1);
		
		 assertTrue(result);
	}
	
	@Test
	void testCaptureVertical() {
		Board board = new Board(19);
		Player bp = new Player(1);
		Player wp = new Player(2);
		
		board.placeStone(1,1,2);
		board.placeStone(2,1,1);
		board.placeStone(1,1,3);
		board.placeStone(2,1,4);
		
		StandardPente rules = new StandardPente(board);

		// function call will increase player score if there is a capture 
		boolean result = rules.checkWinByCapture(2,1,4,bp,wp);
		
		 assertTrue(wp.getScore() == 2);
	}
	
	@Test
	void testCaptureHorizontal() {
		Board board = new Board(19);
		Player bp = new Player(1);
		Player wp = new Player(2);
		
		board.placeStone(1,2,1);
		board.placeStone(2,1,1);
		board.placeStone(1,3,1);
		board.placeStone(2,4,1);
		
		StandardPente rules = new StandardPente(board);

		// function call will increase player score if there is a capture 
		boolean result = rules.checkWinByCapture(2,4,1,bp,wp);
		
		 assertTrue(wp.getScore() == 2);
	}
	
	@Test
	void testCaptureDiagonal() {
		Board board = new Board(19);
		Player bp = new Player(1);
		Player wp = new Player(2);
		
		board.placeStone(1,2,2);
		board.placeStone(2,1,1);
		board.placeStone(1,3,3);
		board.placeStone(2,4,4);
		
		StandardPente rules = new StandardPente(board);

		// function call will increase player score if there is a capture 
		boolean result = rules.checkWinByCapture(2,4,4,bp,wp);
		
		 assertTrue(wp.getScore() == 2);
	}
	

}
