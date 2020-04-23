package ruleSets;

import Pente.PenteBoard;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.ExactlyFiveInARowWinCondition;;

/**
 * Class for the Gomoku game. Appropriate moveCheckers and win conditions are
 * applied
 * 
 * @author mb5053
 */
public class Gomoku extends RuleSet {

	/**
	 * Adds the appropriate move checkers and win conditions for the gomoku game
	 * 
	 * @param board
	 *          - Board for the game
	 */
	public Gomoku ( PenteBoard board ) {
		super(board);

		winConditions_.add(new ExactlyFiveInARowWinCondition(board));

		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));

	}
}
