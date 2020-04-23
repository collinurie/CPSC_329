package captureCheckers;

import Pente.PenteBoard;
import Pente.Player;

/**
 * Class to handle the captures in a variation of the game
 * 
 * @author mb5053
 */
public class TraditionalCaptureChecker implements CaptureChecker {

	private PenteBoard board_;

	/**
	 * Public constructor for TraditionalCaptureChecker method
	 * 
	 * @param board
	 */
	public TraditionalCaptureChecker ( PenteBoard board ) {
		board_ = board;
	}

	public boolean executeCaptures ( Player player, int row, int col ) {

		return captureDirection(player,row,col);
	}

	/**
	 * Determines if placing a piece on (row, col) captures other pieces.
	 * 
	 * @param player
	 *          - Player placing the piece
	 * @param penteBoard
	 *          - PenteBoard the piece is being placed on
	 * @param row
	 *          - Row the piece is being placed on
	 * @param col
	 *          - Column the piece is being placed on
	 * @return A positive integer corresponding to the direction of a capture, and
	 *         -1 if there isn't a capture
	 */
	public boolean captureDirection ( Player player, int row, int col ) {
		boolean res = false;
		int[][] board = board_.getBoard();

		for ( int i = 0 ; i < colIterateVals.length ; i++ ) {
			try {
				int placeVal = board[row + rowIterateVals[i]][col + colIterateVals[i]];
				if ( placeVal != player.getPieceNum() && placeVal != 0 ) {
					int nextPlace = board[row + rowIterateVals[i] * 2][col + colIterateVals[i] * 2];
					if ( nextPlace != player.getPieceNum() && nextPlace != 0 ) {
						if ( board[row + rowIterateVals[i] * 3][col
						    + colIterateVals[i] * 3] == player.getPieceNum() ) {
							System.out.println("Found a Capture");
							executeCaptureFromDirection(player, row, col, i);
							res = true;
						}
					}
				}
			} catch ( ArrayIndexOutOfBoundsException e ) {
				continue;
			}
		}
		return res;
	}

	/**
	 * Starting at the specified location, converts the value of the two adjacent
	 * pieces in the direction of the direction argument to the given player's
	 * value
	 * 
	 * @param player
	 *          - Player who placed the piece that completes the capture. The two
	 *          adjacent pieces will be transfered to this player's control.
	 * @param row
	 *          - Row where the player put their piece
	 * @param col
	 *          - Column where the player put their piece
	 * @param direction
	 *          - Direction (referring to final integers in RuleSet) that the
	 *          capture should be executed in
	 */
	public void executeCaptureFromDirection ( Player player, int row, int col,
	                                          int direction ) {
		board_.clearSpace(row + rowIterateVals[direction],
		                  col + colIterateVals[direction]);
		board_.clearSpace(row + rowIterateVals[direction] * 2,
		                  col + colIterateVals[direction] * 2);
		System.out.println("I cleared some spaces!");
		player.addCapture();
		player.addCapture();
	}

}
