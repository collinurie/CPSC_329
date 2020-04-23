
/**
 * A knock knock joke has the thing (the response to "who's there?") and the
 * punchline.
 */
public class KnockKnockJoke {

	private String thing_, punchline_;

	/**
	 * Create a new knock knock joke.
	 * 
	 * @param thing
	 *            the response to "who's there?"
	 * @param punchline
	 *            the punchline
	 */
	public KnockKnockJoke(String thing, String punchline) {
		thing_ = thing;
		punchline_ = punchline;
	}

	/**
	 * Get the joke's punchline.
	 * 
	 * @return the punchline
	 */
	public String getPunchline() {
		return punchline_;
	}

	/**
	 * Get the response to "who's there?"
	 * 
	 * @return the thing
	 */
	public String getThing() {
		return thing_;
	}

	/**
	 * Get a string version of the joke.
	 */
	public String toString() {
		return thing_ + " / " + punchline_;
	}
}
