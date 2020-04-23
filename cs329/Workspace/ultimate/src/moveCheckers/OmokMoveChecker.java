package moveCheckers;

import Pente.PenteBoard;
import Pente.Player;

/**
 * @author mb5053
 *
 */
public class OmokMoveChecker implements MoveChecker{
	
	
	PenteBoard board_;
	/**
	 * 
	 */
	public OmokMoveChecker (PenteBoard board) {
		board_ = board;
	}

	@Override
	public boolean isValidMove ( Player player, int moveNumber, int row,
	                             int col ) {
		return checkNoThreeByThree(player,row,col);
	}
	
	private boolean checkNoThreeByThree (Player p, int row, int col) {
		
		return true;
	}

}
