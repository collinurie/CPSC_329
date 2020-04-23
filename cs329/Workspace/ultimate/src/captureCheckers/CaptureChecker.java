package captureCheckers;

import Pente.Player;

/**
 * Interface for the captureCheckers
 * 
 * @author mb5053
 */
public interface CaptureChecker {
	public static final int UP = 0;
	public static final int UP_RIGHT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN_RIGHT = 3;
	public static final int DOWN = 4;
	public static final int DOWN_LEFT = 5;
	public static final int LEFT = 6;
	public static final int UP_LEFT = 7;

	public static final int[] colIterateVals = { 0, 1, 1, 1, 0, -1, -1, -1 };
	public static final int[] rowIterateVals = { -1, -1, 0, 1, 1, 1, 0, -1 };

	/**
	 * Adds one to the players capture count of the player that made the capture,
	 * if one is made, and clears the two spaces on the board that should be
	 * removed from the capture
	 * 
	 * @param player
	 *          player that is executing the capture
	 * @param row
	 *          row of the piece that is placed before the capture is made
	 * @param col
	 *          column of the piece that is placed before the capture is made
	 * @return true if a capture is made, otherwise false
	 */
	public boolean executeCaptures ( Player player, int row, int col );
}
