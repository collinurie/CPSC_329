package moveCheckers;

import Pente.Player;

/**
 * Interface for determining whether or not a move is valid
 * 
 * @author mb5053
 */
public interface MoveChecker {

	/**
	 * Determines whether or not a move is valid. Can be different for each
	 * different variation of the game
	 * 
	 * @param player
	 *          player that is making the move
	 * @param moveNumber
	 *          number of the move that is being made
	 * @param row
	 *          row of the piece being placed
	 * @param col
	 *          column of the piece being placed
	 * @return true if the move is valid, otherwise false
	 */
	public boolean isValidMove ( Player player, int moveNumber, int row,
	                             int col );
}
