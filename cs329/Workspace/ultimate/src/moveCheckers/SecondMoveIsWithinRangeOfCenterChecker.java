package moveCheckers;

import Pente.PenteBoard;
import Pente.Player;

/**
 * Ensures that the second piece placed on the board is within a certain range
 * of the center
 * 
 * @author mb5053
 */
public class SecondMoveIsWithinRangeOfCenterChecker implements MoveChecker {
	private PenteBoard board_;
	private int initialMoveRange_;

	/**
	 * Constructor for SecondMoveIsWithinRangeOfCenterChecker
	 * 
	 * @param board
	 *          board being used for the game
	 * @param initialMoveRange
	 *          range that the second move cannot be placed in
	 */
	public SecondMoveIsWithinRangeOfCenterChecker ( PenteBoard board,
	                                                int initialMoveRange ) {
		board_ = board;
		initialMoveRange_ = initialMoveRange;
	}

	public boolean isValidMove ( Player player, int moveNumber, int row,
	                             int col ) {
		if ( moveNumber == 1 ) {
			int upperRowRange = board_.getCenterRow() + initialMoveRange_;
			int upperColRange = board_.getCenterCol() + initialMoveRange_;
			int lowerRowRange = board_.getCenterRow() - initialMoveRange_;
			int lowerColRange = board_.getCenterCol() - initialMoveRange_;
			if ( row > lowerRowRange && row < upperRowRange && col > lowerColRange
			    && col < upperColRange ) {
				return false;
			}
			return true;
		} else {			
			return true;
		}
	}
}
