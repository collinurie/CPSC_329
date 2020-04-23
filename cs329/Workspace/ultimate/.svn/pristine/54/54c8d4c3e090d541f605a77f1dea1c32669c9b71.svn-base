package winConditions;

import Pente.PenteBoard;
import Pente.Player;

/**
 * @author mb5053
 */
public class FiveInARowWinCondition implements WinCondition {

	PenteBoard board_;

	/**
	 * public constructor for the FiveInARowWinCondition class
	 * 
	 * @param board
	 *          Board being used for the game
	 */
	public FiveInARowWinCondition ( PenteBoard board ) {
		board_ = board;
	}

	public boolean checkCondition ( Player player ) {
		return checkForFiveInARow(player);
	}

	/**
	 * Checks if there are five pieces in a row for the correct player
	 * 
	 * @param player
	 *          - Which player might have one
	 * @return True if there is a new string of 5 or more pieces that includes the
	 *         most recently played piece.
	 */
	public boolean checkForFiveInARow ( Player player ) {
		int row = board_.getMostRecentPlayRow();
		int col = board_.getMostRecentPlayCol();

		int vCount = 0;
		int hCount = 0;
		int upRightCount = 0;
		int upLeftCount = 0;

		int[][] board = board_.getBoard();
		int[] directionCounts = { 0, 0, 0, 0, 0, 0, 0, 0 };
		for ( int i = 0 ; i < colIterateVals.length ; i++ ) {
			try {
				int placeVal = board[row + rowIterateVals[i]][col + colIterateVals[i]];
				int dist = 1;
				while ( placeVal == player.getPieceNum() ) {
					directionCounts[i]++;
					dist++;
					placeVal = board[row + rowIterateVals[i] * dist][col
					    + colIterateVals[i] * dist];
					if ( dist > 5 ) {
						break;
					}
				}
			} catch ( ArrayIndexOutOfBoundsException e ) {
				continue;
			}
		}
		vCount = directionCounts[UP] + directionCounts[DOWN] + 1;
		hCount = directionCounts[RIGHT] + directionCounts[LEFT] + 1;
		upRightCount = directionCounts[UP_RIGHT] + directionCounts[DOWN_LEFT] + 1;
		upLeftCount = directionCounts[UP_LEFT] + directionCounts[DOWN_RIGHT] + 1;

		if ( vCount >= 5 || hCount >= 5 || upRightCount >= 5 || upLeftCount >= 5 ) {
			return true;
		} else {
			return false;
		}
	}

}
