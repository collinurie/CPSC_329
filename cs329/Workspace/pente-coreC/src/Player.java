
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class Player {
	private int playerScore_;
	private int playerColor_;
	
	/**
	 * creates a Player object
	 * @param color- 1 = black 2 = white
	 */
	public Player(int color){
		playerColor_ = color;
		playerScore_ = 0;
	}
	
	/**
	 * returns the color value of the object.
	 * @return the color 
	 */
	public int getColor() {
		return playerColor_;
	}
	
	/**
	 * returns the score value of the object.
	 * @return the score
	 */
	public int getScore() {
		return playerScore_;
	}

	/**
	 * Method that adds two to the score
	 */
	public void addToScore (int stones) {
		playerScore_ += stones;
	}
	
	
	
}// end class
