
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class FiveInARowPente extends Rules {

	/**
	 * Constructor for FiveInARow pente
	 */
	public FiveInARowPente () {
		//we still check for stones, but they don't count towards a win: how to disable this in the abstract class?
		boardSize_ = 19;
		gameBoard_ = new Board(boardSize_);
		capturedStonesMin_ = 2;
		capturedStonesMax_ = 2;
	}

	@Override
	public boolean validMove ( int x, int y, int color ) {
		//we only need to check if the move is inside the board (no restriction to any move)
		if (x >= 0 && x < gameBoard_.BOARD_SIZE &&
				y >= 0 && y < gameBoard_.BOARD_SIZE
				&& gameBoard_.check(0,x,y)) {
			return true;
		}
		else { return false;} //if we reach this else, the move is invalid. 
	}

	@Override
	public boolean winConditionConsec ( int consecStones ) {
		if (consecStones >= 5) { //we win by placing 5 or more stones in a row
			return true;
		}
		return false;
	}

	@Override
	public boolean winConditionCapture ( Player currentPlayer ) {
		return false;
	}

}
