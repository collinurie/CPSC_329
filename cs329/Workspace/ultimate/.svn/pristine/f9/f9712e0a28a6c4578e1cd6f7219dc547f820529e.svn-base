package winConditions;

import Pente.Player;

/**
 * @author mb5053
 */
public interface WinCondition {
//Variables to describe the relative positions of pieces
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
	 * Checks to see if the win condition has been completed for whichever
	 * variation of the game is being implemented
	 * 
	 * @param player
	 *          Player that is up and has potentially won the game
	 * @return true if the win condition is met, otherwise false
	 */
	public boolean checkCondition ( Player player );
}
