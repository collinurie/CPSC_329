package moveCheckers;

import Pente.PenteBoard;
import Pente.Player;

/**
 * Ensures that the first move of the game is in the center of the baord
 * 
 * @author mb5053
 */
public class FirstMoveIsCenterChecker implements MoveChecker {

	private PenteBoard board_;

	/**
	 * Public constructor for FirstMoveIsCenterChecker
	 * 
	 * @param board
	 *          - board being used to play the game
	 */
	public FirstMoveIsCenterChecker ( PenteBoard board ) {
		board_ = board;
	}

	public boolean isValidMove ( Player player, int moveNumber, int row,
	                             int col ) {
		if ( moveNumber == 0 ) {
			if ( board_.getCenterCol() != col || board_.getCenterRow() != row ) {
			  return false;
			}
		}
		return true;
	}

}
