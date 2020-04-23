import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Collin Urie, Famke Nouwens
 *
 */
class TournamentPenteTest {

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
		TournamentPente rules = new TournamentPente(board);
		
		boolean result = rules.validMove(10,10,1);
		
		assertTrue(result);
	}
	@Test
	void testSecondBlackMove () {
		Board board = new Board(19);
		TournamentPente rules = new TournamentPente(board);
		
		rules.validMove(10,10,1);
		rules.validMove(1,1,2);
		boolean result = rules.validMove(7,7,1);
		assertTrue(result);
	}
	@Test
	void testInvalidSecondBlackMove () {
		Board board = new Board(19);
		TournamentPente rules = new TournamentPente(board);
		
		rules.validMove(10,10,1);
		rules.validMove(1,1,2);
		boolean result = rules.validMove(8,8,1);
		
		assertTrue(!result);
	}

}
