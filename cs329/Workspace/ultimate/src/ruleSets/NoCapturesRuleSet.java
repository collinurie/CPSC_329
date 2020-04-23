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
public class NoCapturesRuleSet extends RuleSet{

	/**
	 * @param board
	 */
	public NoCapturesRuleSet ( PenteBoard board ) {
		super(board);
		winConditions_.add(new FiveInARowWinCondition(board));

		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));
		
	}

}
