package ruleSets;

import Pente.PenteBoard;
import Pente.Player;
import captureCheckers.TraditionalCaptureChecker;
import moveCheckers.FirstMoveIsCenterChecker;
import moveCheckers.MoveIsEmptyChecker;
import moveCheckers.SecondMoveIsWithinRangeOfCenterChecker;
import winConditions.FiveInARowWinCondition;
import winConditions.NumberCapturesWinCondition;

/**
 * Class for the TournamentRuleSet variation of the game. Appropriate
 * moveCheckers and win conditions are applied
 * 
 * @author mb5053
 */
public class TournamentRuleSet extends RuleSet {

	/**
	 * Adds the appropriate move checkers and win conditions for the tournament
	 * variation of the game
	 * 
	 * @param board
	 *          - Board for the game
	 */
	public TournamentRuleSet ( PenteBoard board ) {
		super(board);

		winConditions_.add(new FiveInARowWinCondition(board));
		winConditions_.add(new NumberCapturesWinCondition(10));

		moveCheckers_.add(new MoveIsEmptyChecker(board));
		moveCheckers_.add(new FirstMoveIsCenterChecker(board));
		moveCheckers_.add(new SecondMoveIsWithinRangeOfCenterChecker(board,2));

		captureCheckers_.add(new TraditionalCaptureChecker(board));

	}

}
