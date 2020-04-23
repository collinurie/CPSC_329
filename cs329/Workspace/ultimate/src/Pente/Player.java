package Pente;

import java.util.ArrayList;

/**
 * Class to handle the players for the game. Any change to the players capture
 * counts, or the addition of new players is done in this class
 * 
 * @author mb5053
 */
public abstract class Player {
	private static ArrayList<String> names = new ArrayList<String>();
	private static int playernums = 1;
	private String name_;
	private int captures_;
	protected boolean isComputer_;
	protected boolean isNetwork_;
	protected PenteGame game_;

	// Integer for use in the PenteBoard. Assumes that future implementations
	// might require more than 2 players.
	private int pieceNum_;

	/**
	 * Private player constructor
	 * 
	 * @param name
	 */
	protected Player ( String name ) {
		name_ = name;
		captures_ = 0;
		pieceNum_ = playernums++;
	}
	
	public abstract int[][] getBoardArray();
	
	public abstract boolean gameIsWon();

	/**
	 * Gets the player's name
	 * 
	 * @return Player's name
	 */
	public String getName () {
		return name_;
	}

	/**
	 * Adds one to the current capture count
	 * 
	 * @return the current capture count
	 */
	public int addCapture () {
		captures_++;
		return captures_;
	}

	/**
	 * Gets the current number of captures
	 * 
	 * @return the current capture count
	 */
	public int getCaptures () {
		return captures_;
	}

	/**
	 * @return current piece number
	 */
	public int getPieceNum () {
		return pieceNum_;
	}

	/**
	 * Resets the player counters. needed for testing teardown.
	 */
	public static void resetPlayerCount () {
		playernums = 1;
	}
	
	/**
	 * Takes in a move, and tries to implement it using whatever protocol is appropriate
	 * @param move - Move to be made
	 * @return
	 */
	public abstract boolean handleUserInput(Move move);
	
	public boolean isComputer() {
		return isComputer_;
	}
	
	public boolean isNetwork() {
		return isNetwork_;
	}
	
//	public void setGame(PenteGame game) {
//		game_ = game;
//	}
//	
//	public PenteGame getGame() {
//		return game_;
//	}
//	
	public void setCaptures(int caps) {
		captures_ = caps;
	}
}
