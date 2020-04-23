package winConditions;

import Pente.Player;

/**
 * @author mb5053
 */
public class NumberCapturesWinCondition implements WinCondition {

	private int numMovesToWin_;
	
	public NumberCapturesWinCondition(int numMovesToWin) {
		numMovesToWin_ = numMovesToWin;
	}

	public boolean checkCondition (Player player) {
		return player.getCaptures() >= numMovesToWin_;
	}

}
