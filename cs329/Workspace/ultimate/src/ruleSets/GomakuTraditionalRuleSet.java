package ruleSets;

import Pente.PenteBoard;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.ExactlyFiveInARowWinCondition;

/**
 * @author mb5053
 *
 */
public class GomakuTraditionalRuleSet extends RuleSet {

	/**
	 * @param board
	 */
	public GomakuTraditionalRuleSet ( PenteBoard board ) {
		super(board);
		
		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));
		
		winConditions_.add(new ExactlyFiveInARowWinCondition(board));
	}

}
