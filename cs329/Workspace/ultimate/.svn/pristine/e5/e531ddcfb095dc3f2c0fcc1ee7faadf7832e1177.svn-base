package ruleSets;

import Pente.PenteBoard;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import winConditions.CaroWinCondition;

/**
 * @author mb5053
 *
 */
public class GomakuCaroRuleSet extends RuleSet {

	/**
	 * @param board
	 */
	public GomakuCaroRuleSet ( PenteBoard board ) {
		super(board);
		
		winConditions_.add(new CaroWinCondition(board));
		
		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));
	}

}
