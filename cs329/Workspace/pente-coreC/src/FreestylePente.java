
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class FreestylePente extends Rules {

	/**
	 * Constructor for freestyle Pente rules
	 */
	public FreestylePente () {
		boardSize_ = 19;
		gameBoard_ = new Board(boardSize_);
		capturedStonesMin_ = 2;
		capturedStonesMax_ = 2;
	}

	@Override
	public boolean validMove ( int x, int y, int color ) {
		//we need to know which move it is
	 // we just need to check if the given position is inside the board
			if (x >= 0 && x < gameBoard_.BOARD_SIZE &&
					y >= 0 && y < gameBoard_.BOARD_SIZE
					&& gameBoard_.check(0,x,y)) {
				return true;
			}
			else { return false;} //if we reach this else, the move is invalid. 
		}
	

	@Override
	public boolean winConditionConsec ( int consecStones ) {
		if (consecStones >= 5) { //in freestyle pente, getting 5 or more in a row is enough for a win
			return true;
		}
		return false;
	}

	@Override
	public boolean winConditionCapture ( Player currentPlayer ) {
		if(currentPlayer.getScore() >= 10) {
			return true;
		}
		return false;
	}
}
