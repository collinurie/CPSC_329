package Pente;

/**
 * Class for local players. Interacts directly with a game being run on the same thread.
 * @author mb5053
 */
public class LocalPlayer extends Player {

	/**
	 * @param name:
	 *          name of the player
	 */
	public LocalPlayer ( String name ) {
		super(name);
		isComputer_ = false; // not a computer player
		isNetwork_ = false;
	}

	/**
	 * Sets the game that this player will submit moves to
	 * 
	 * @param game
	 *          - PenteGame that this player should participate in
	 */
	public void setGame ( PenteGame game ) {
		game_ = game;
	}

	/**
	 * Gets a 2D integer array representing the PenteBoard
	 * @return - integer array defining the PenteBoard
	 */
	public int[][] getBoardArray () {
		return game_.getPenteBoard().getBoard();
	}

	/**
	 * Returns true if the game this player is participating in has been won, and
	 * false otherwise
	 * @return - True if the game is won, false otherwise
	 */
	public boolean gameIsWon () {
		return game_.isWon();
	}

	/**
	 * Takes in a move, and submits it the game. 
	 * @return - True if the move was valid, and false if is not valid
	 */
	public boolean handleUserInput ( Move move ) {
		return game_.makePlayerMove(move);
	}

}
