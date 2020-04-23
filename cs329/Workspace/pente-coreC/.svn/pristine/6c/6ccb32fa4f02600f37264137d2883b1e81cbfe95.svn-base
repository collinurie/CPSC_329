
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class TournamentPente extends Rules {

	private int turn = 0;
	
	/**
	 * 
	 */
	public TournamentPente () {
		boardSize_ = 19;
		gameBoard_ = new Board(boardSize_);
		capturedStonesMin_ = 2;	
		capturedStonesMax_ = 2;

	}
	/**
	 * used for testing purposes 
	 * @param board
	 */
	public TournamentPente (Board board) {
		boardSize_ = 19;
		gameBoard_ = board;
		capturedStonesMin_ = 2;	
		capturedStonesMax_ = 2;

	}

	@Override
	public boolean validMove ( int x, int y, int color ) {
		//we need to know which move it is
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
		else if (turn == 2 && color == 1
							&& gameBoard_.check(0,x,y)) { //this is the second turn for black
		//the second move made by black must be at least 3 or more intersections away
			if (x <  (gameBoard_.getCenter()-2) || x >  (gameBoard_.getCenter()+2)) {
				turn++;
					return true;
			} 
			//x is now between 7 and 11, so our y must be at least three intersections away from the center
			else if (y <  gameBoard_.getCenter()-2 || y >  gameBoard_.getCenter()+2) {
				turn++;
				return true;
			}
		}
		else { //if it is not the first or the third turn, we just need to check if the given position is inside the board
			if (x >= 0 && x < gameBoard_.BOARD_SIZE &&
					y >= 0 && y < gameBoard_.BOARD_SIZE
					&& gameBoard_.check(0,x,y)) {
				turn++;
				return true;
			}
		}
		return false;//if we reach this else, the move is invalid. 
	}

	@Override
	public boolean winConditionConsec ( int consecStones ) {
		if (consecStones >= 5) { //in tournament pente, getting 5 or more in a row wins the game
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
