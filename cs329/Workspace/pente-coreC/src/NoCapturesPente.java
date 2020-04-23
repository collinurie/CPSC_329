
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class NoCapturesPente extends Rules {

	/**
	 * 
	 */
	public NoCapturesPente () {
		boardSize_ = 19;
		gameBoard_ = new Board(boardSize_);
		capturedStonesMin_ = 0;
	}

	@Override
	public boolean validMove ( int x, int y, int color ) {
		if (x >= 0 && x < gameBoard_.BOARD_SIZE &&
				y >= 0 && y < gameBoard_.BOARD_SIZE
				&& gameBoard_.check(0,x,y)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean winConditionConsec ( int consecStones ) {
		if (consecStones >= 5) { //we win by having 5 or more stones in a row
			return true;
		}
		return false;
	}

	@Override
	public boolean winConditionCapture ( Player currentPlayer ) {
		return false;
	}

}
