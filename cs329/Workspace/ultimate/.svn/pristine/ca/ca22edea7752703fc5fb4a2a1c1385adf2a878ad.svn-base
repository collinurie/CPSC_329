package ruleSets;

import Pente.PenteBoard;
import captureCheckers.TraditionalCaptureChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.FiveInARowWinCondition;
import winConditions.NumberCapturesWinCondition;

/**
 * Class for the freestyle variation of the game. Appropriate moveCheckers and
 * win conditions are applied
 * 
 * @author ms5976
 */
public class FreestyleRuleSet extends RuleSet {

	/**
	 * Adds the appropriate move checkers and win conditions for the freestyle
	 * variation of the game
	 * 
	 * @param board
	 *          - Board for the game
	 */
	public FreestyleRuleSet ( PenteBoard board ) {
		super(board);

		moveCheckers_.add(new MoveIsEmptyChecker(board));

		winConditions_.add(new FiveInARowWinCondition(board));
		winConditions_.add(new NumberCapturesWinCondition(5));

		captureCheckers_.add(new TraditionalCaptureChecker(board));

	}

}
