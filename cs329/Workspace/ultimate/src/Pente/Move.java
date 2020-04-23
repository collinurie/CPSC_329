package Pente;

/**
 * @author mb5053
 *
 */
public class Move {
	private int row_;
	private int col_;
	private Player player_;
	

	public Move(int row, int col, Player id) {
		row_ = row;
		col_ = col;
		player_ = id;
	}

	/**
	 * @return the row
	 */
	public int getRow () {
		return row_;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow ( int row ) {
		row_ = row;
	}

	/**
	 * @return the col
	 */
	public int getCol () {
		return col_;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol ( int col ) {
		col_ = col;
	}
	
	/**
	 * @return the playerID
	 */
	public Player getPlayer () {
		return player_;
	}

	/**
	 * @param playerID the playerID to set
	 */
	public void setPlayer ( Player player ) {
		player_ = player;
	}
}
