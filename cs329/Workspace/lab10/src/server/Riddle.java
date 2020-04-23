package server;

/**
 * A riddle consists of the riddle itself and the answer.
 */
public class Riddle {

	private String riddle_, answer_;

	/**
	 * Create a new riddle.
	 * 
	 * @param riddle
	 *            the riddle
	 * @param answer
	 *            the answer to the riddle
	 */
	public Riddle(String riddle, String answer) {
		riddle_ = riddle;
		answer_ = answer;
	}

	/**
	 * Get the riddle.
	 * 
	 * @return the riddle
	 */
	public String getRiddle() {
		return riddle_;
	}

	/**
	 * Get the answer to the riddle.
	 * 
	 * @return the answer
	 */
	public String getAnswer() {
		return answer_;
	}

	/**
	 * Get a string version of the riddle.
	 */
	public String toString() {
		return riddle_ + " / " + answer_;
	}

}
