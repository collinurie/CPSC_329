package ruleSets;

import Pente.PenteBoard;
import captureCheckers.ThreeInARowCaptureChecker;
import captureCheckers.TraditionalCaptureChecker;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.FiveInARowWinCondition;
import winConditions.NumberCapturesWinCondition;

/**
 * @author mb5053
 *
 */
public class KeryoRuleSet extends RuleSet {

	/**
	 * @param board
	 */
	public KeryoRuleSet ( PenteBoard board ) {
		super(board);
		
		winConditions_.add(new FiveInARowWinCondition(board));
		winConditions_.add(new NumberCapturesWinCondition(15));

		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));

		captureCheckers_.add(new TraditionalCaptureChecker(board));
		captureCheckers_.add(new ThreeInARowCaptureChecker(board));
	}

}
