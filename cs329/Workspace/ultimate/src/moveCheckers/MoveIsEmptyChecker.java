package moveCheckers;

import Pente.PenteBoard;
import Pente.Player;

/**
 * Ensures that a piece being placed is in an empty spot on the board
 * 
 * @author mb5053
 */
public class MoveIsEmptyChecker implements MoveChecker {
	private PenteBoard board_;

	/**
	 * public constructor for MoveIsEmptyChecker
	 * 
	 * @param board
	 *          - board being used to play
	 */
	public MoveIsEmptyChecker ( PenteBoard board ) {
		board_ = board;
	}

	public boolean isValidMove ( Player player, int moveNumber, int row,
	                             int col ) {
		if ( board_.getPosition(row,col) != 0 ) {
			return false;
		}
		return true;
	}

}
