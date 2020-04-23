package Pente;

/**
 * Class to handle the board for the game "Pente." Any changes to the board are
 * updated in this class
 * 
 * @author mb5053
 */
public class PenteBoard {

	private int rows_;
	private int cols_;

	private int mostRecentPlayRow_ = -1;
	private int mostRecentPlayCol_ = -1;

	// Array to store the board. 0 indicates an empty space, and each player has a
	// unique number that represents one of their pieces
	private int[][] board_;

	/**
	 * Creates a pente board of the given dimensions, and initializes it to be
	 * empty
	 * 
	 * @param rows
	 *          number of rows on the board
	 * @param cols
	 *          number of columns on the board
	 */
	private PenteBoard ( int rows, int cols ) {
		rows_ = rows;
		cols_ = cols;

		// Fill the board with zeros
		board_ = new int[rows][cols];
		for ( int i = 0 ; i < board_.length ; i++ ) {
			for ( int j = 0 ; j < board_[i].length ; j++ ) {
				board_[i][j] = 0;
			}
		}
	}

	/**
	 * Create a pente board from an existing 2D array. Intended for use in tests
	 * 
	 * @param board
	 *          - 2D array that represents an existing board state
	 */
	private PenteBoard ( int[][] board ) {
		rows_ = board[0].length;
		cols_ = board.length;
		board_ = board;
	}

	/**
	 * Public method for creating a pente board.
	 * 
	 * @param rows
	 *          - number of rows for the board
	 * @param cols
	 *          - number of cols for the board
	 * @return new PenteBoard
	 */
	public static PenteBoard createNewPenteBoard ( int rows, int cols ) {
		return new PenteBoard(rows,cols);
	}

	/**
	 * Method for creating a PenteBoard from a pre-defined array. For testing
	 * only.
	 * 
	 * @param board
	 * @return
	 */
	public static PenteBoard createNewPenteBoardFromArray ( int[][] board ) {
		return new PenteBoard(board);
	}

	/**
	 * Places a player's piece at a given spot on the board.
	 * 
	 * @param player
	 *          - Player who's piece is being placed
	 * @param row
	 *          - row for piece to be placed on
	 * @param col
	 *          - column for piece to be placed on
	 */
	public void placePiece ( Player player, int row, int col ) {
		board_[row][col] = player.getPieceNum();
		mostRecentPlayRow_ = row;
		mostRecentPlayCol_ = col;
	}

	/**
	 * Clears a space (sets it to 0)
	 * 
	 * @param row
	 *          - Row index to be cleared
	 * @param col
	 *          - Column index to be cleared
	 */
	public void clearSpace ( int row, int col ) {
		board_[row][col] = 0;
	}

	/**
	 * @return rows on the board
	 */
	public int getRows () {
		return rows_;
	}

	/**
	 * @return columns on the board
	 */
	public int getCols () {
		return cols_;
	}

	/**
	 * @return 2D array for the board
	 */
	public int[][] getBoard () {
		return board_;
	}

	/**
	 * @param row
	 *          row of desired position
	 * @param col
	 *          column of desired position
	 * @return current position on the board
	 */
	public int getPosition ( int row, int col ) {
		return board_[row][col];			
	}

	/**
	 * @return the mostRecentPlayRow
	 */
	public int getMostRecentPlayRow () {
		return mostRecentPlayRow_;
	}

	/**
	 * @param mostRecentPlayRow
	 *          the mostRecentPlayRow to set
	 */
	public void setMostRecentPlayRow ( int mostRecentPlayRow ) {
		mostRecentPlayRow_ = mostRecentPlayRow;
	}

	/**
	 * @return the mostRecentPlayCol
	 */
	public int getMostRecentPlayCol () {
		return mostRecentPlayCol_;
	}

	/**
	 * @param mostRecentPlayCol
	 *          the mostRecentPlayCol to set
	 */
	public void setMostRecentPlayCol ( int mostRecentPlayCol ) {
		mostRecentPlayCol_ = mostRecentPlayCol;
	}

	/**
	 * Determines if the given location is empty (no player has put a piece there)
	 * 
	 * @param row
	 *          - Row index to be checked
	 * @param col
	 *          - Column index to be checked
	 * @return True if there is no piece there, false if there is a piece there
	 */
	public boolean locationIsEmpty ( int row, int col ) {
		return board_[row][col] == 0;
	}

	/**
	 * Determines if a given set of indices is valid for a given 2D array
	 * 
	 * @param arr
	 *          - 2D int array to be checked
	 * @param row
	 *          - Row to be checked
	 * @param col
	 *          - Column to be checked
	 * @return True if this index exists in the array, False if it lies outside
	 *         the bounds
	 */
	public boolean isInBounds ( int row, int col ) {
		return (row < rows_ && row >= 0) && (col < cols_ && col >= 0);
	}

	/**
	 * Gets the row in the center
	 * 
	 * @return - The row number that contains the center place
	 */
	public int getCenterRow () {
		return rows_ / 2;
	}

	/**
	 * Gets the column in the center
	 * 
	 * @return - The column number that contains the center place
	 */
	public int getCenterCol () {
		return cols_ / 2;
	}

}
