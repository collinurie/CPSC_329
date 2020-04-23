package ruleSets;

import Pente.PenteBoard;
import captureCheckers.TraditionalCaptureChecker;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.FiveInARowWinCondition;
import winConditions.NumberCapturesWinCondition;

/**
 * @author mb5053
 *
 */
public class FiveInARowRuleSet extends RuleSet {

	/**
	 * @param board
	 */
	public FiveInARowRuleSet ( PenteBoard board ) {
		super(board);
		
		winConditions_.add(new FiveInARowWinCondition(board));

		moveCheckers_.add(new MoveIsEmptyChecker(board));

		captureCheckers_.add(new TraditionalCaptureChecker(board));
	}

}
