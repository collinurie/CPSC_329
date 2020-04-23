import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author cu5988
 *
 */
class KeryoPenteTest {

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
		KeryoPente rules = new KeryoPente(board);
		
		boolean result = rules.validMove(10,10,1);
		
		assertTrue(result);
	}
	
	@Test
	void testFirstMoveNotCenter () {
		Board board = new Board(19);
		KeryoPente rules = new KeryoPente(board);
		
		boolean result = rules.validMove(10,11,1);
		
		assertTrue(!result);
	}
	
	@Test
	void testCapture3stones() {
		Board board = new Board(19);
		Player bp = new Player(1);
		Player wp = new Player(2);
		
		board.placeStone(1,1,1);
		board.placeStone(2,1,2);
		board.placeStone(2,1,3);
		board.placeStone(2,1,4);
		board.placeStone(1,1,5);
		board.printBoard();
		KeryoPente rules = new KeryoPente(board);

		// function call will increase player score if there is a capture 
		boolean result = rules.checkWinByCapture(1,1,5,bp,wp);
		
		 assertTrue(bp.getScore() == 3);
	}
	
	@Test
	void testCapture3and2Stones() {
		Board board = new Board(19);
		Player bp = new Player(1);
		Player wp = new Player(2);
		
		board.placeStone(1,1,1);
		board.placeStone(2,1,2);
		board.placeStone(2,1,3);
		board.placeStone(2,1,4);
		board.placeStone(2,1,6);
		board.placeStone(2,1,7);
		board.placeStone(1,1,8);
		board.placeStone(1,1,5);

		KeryoPente rules = new KeryoPente(board);

		// function call will increase player score if there is a capture 
		boolean result = rules.checkWinByCapture(1,1,5,bp,wp);
		
		 assertTrue(bp.getScore() == 5);
	}
	

}
