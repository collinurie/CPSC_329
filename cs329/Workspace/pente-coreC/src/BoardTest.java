import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Famke Nouwens, Collin Urie 
 *
 */
public class BoardTest {
	
	private Board board_;
	private Board partlyFilledBoard_;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		board_ = new Board(9); //we test with a board of size 9 by 9 since that's easier to create ourselves
		int[][] partlyFilled_ = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 2, 0, 0},
								{0, 0, 0, 0, 0, 1, 0, 0, 0},
								{0, 0, 0, 0, 1, 0, 0, 0, 0},
								{0, 0, 2, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0},
								};
		partlyFilledBoard_ = new Board(partlyFilled_);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	

	
	@Test
	public void testCheckMethodWhiteStone() {
		//starting state is already created
		
		//result
		boolean result = partlyFilledBoard_.check(2, 2, 6);
		
		//compare result to expected result
		assertTrue(result);
	}
	
	@Test
	public void testCheckMethodEmptySpace() {
		//starting state is already created
		
		//result
		boolean result = partlyFilledBoard_.check(0, 2, 6);
		
		//compare result to expected result
		assertFalse(result);
	}
	
	@Test
	public void testCheckMethodEmptySpaceOutsideBoard() {
		//starting state is already created
		
		try {
			partlyFilledBoard_.check(0, -1, -1);
			  fail("expected exception is not thrown");
			} catch ( IndexOutOfBoundsException e ) {}
	}
	
	@Test
	public void testPlaceStoneTakeTurns() {
		//setting up start state
		board_.placeStone(1, 4, 4);
		board_.placeStone(2, 2, 6);
		board_.placeStone(1, 3, 5);
		board_.placeStone(2, 5, 2);
		
//		board_.printBoard();
		//input is already created
		
		//result
		boolean result = board_.equals(partlyFilledBoard_);
		
		//compare result to expected result (should be the same)
		assertTrue(result);
	}
	
	@Test
	public void testPlaceStoneNotWhiteTurn() {
		//starting state is initialized
		
		//test
		try {
				board_.placeStone(2, 3, 1);
				fail("expected exception is not thrown");
			} catch ( IllegalArgumentException e ) {}
		//expected output throw IllegalArgumentException
	}
	

}