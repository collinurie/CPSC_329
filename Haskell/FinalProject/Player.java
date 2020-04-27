

/**
 * @author Famke Nouwens
 *
 */
public class Player {

	private int player; //value is 1 if it's a black player and 2 if it's white
	private int capturedStones_;


	/**
	 * @return the capturedStones_
	 */
	public int getCapturedStones() {
		return capturedStones_;
	}


	public void addCapturedStones() {
		capturedStones_ = capturedStones_ + 2;
	}





}
