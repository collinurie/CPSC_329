package ruleSets;

import Pente.PenteBoard;
import captureCheckers.TraditionalCaptureChecker;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.FiveInARowWinCondition;
import winConditions.NumberCapturesWinCondition;

/**
 * Class for the TraditionalRuleSet variation of the game. Appropriate
 * moveCheckers and win conditions are applied
 * 
 * @author mb5053
 */
public class TraditionalRuleSet extends RuleSet {

	/**
	 * Adds the appropriate move checkers and win conditions for the traditional
	 * pente game
	 * 
	 * @param board
	 *          - Board for the game
	 */
	public TraditionalRuleSet ( PenteBoard board ) {
		super(board);

		winConditions_.add(new FiveInARowWinCondition(board));
		winConditions_.add(new NumberCapturesWinCondition(10));

		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));
		
		captureCheckers_.add(new TraditionalCaptureChecker(board));
		
	}
}
