
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class KeryoPente extends Rules {
	
	private int turn = 0;

	/**
	 * 
	 */
	public KeryoPente () {
		boardSize_ = 19;
		gameBoard_ = new Board(boardSize_);
		capturedStonesMin_ = 2; //this is wrong however, since it can be 2 OR 3
		capturedStonesMax_ = 3;
	}
	
	/**
	 * used for testing purposes 
	 */
	public KeryoPente (Board board) {
		boardSize_ = 19;
		gameBoard_ = board;
		capturedStonesMin_ = 2; //this is wrong however, since it can be 2 OR 3
		capturedStonesMax_ = 3;
	}

	@Override
	public boolean validMove ( int x, int y, int color ) {
		if (turn == 0 && color == 1) { //first move (BLACK) must be in the centre of the board
			//check if given x and y are in the centre of the board
			if (x == gameBoard_.getCenter() && y == gameBoard_.getCenter()) {
				turn++;
				return true;
			}
			else {
				return false; // move is not in the centre, so we return false
			}
		}
		else { //if it is not the first turn, we just need to check if the given position is inside the board
			if (x >= 0 && x < gameBoard_.BOARD_SIZE &&
					y >= 0 && y < gameBoard_.BOARD_SIZE
					&& gameBoard_.check(0,x,y)) {
				turn++;
				return true;
			}
			else { return false;} //if we reach this else, the move is invalid. 
		}
	}

	@Override
	public boolean winConditionConsec ( int consecStones ) {
		if (consecStones >= 15) { //in keryo-pente we win by having captured 15 or more stones
			return true;
		}
		return false;
	}

	@Override
	public boolean winConditionCapture ( Player currentPlayer ) {
		if (currentPlayer.getScore() >= 10 ) {
			return true;
		}
		return false;
	}

}
