package ruleSets;

import Pente.PenteBoard;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.FiveInARowWinCondition;

/**
 * @author mb5053
 *
 */
public class GomakuFreestyleRuleSet extends RuleSet {

	/**
	 * @param board
	 */
	public GomakuFreestyleRuleSet ( PenteBoard board ) {
		super(board);
		
		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));
		
		winConditions_.add(new FiveInARowWinCondition(board));
	}

}
