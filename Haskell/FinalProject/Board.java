

/**
 * @author Famke Nouwens
 *
 */
public class Board {

	public int BOARD_SIZE = 19;
	private int[][] board_ = new int[BOARD_SIZE][BOARD_SIZE]; //specified boardsize
	private boolean turnBlack; //boolean that stores whose turn it is

	/**
	 * Empty constructor
	 */
	public Board()
	{
		//set-up the board
		initialiseBoard();
	}

	/**
	 * Constructor that creates an empty board with pre-specified boardsize
	 * @param board_size desired board_size
	 */
	public Board(int board_size)
	{
		BOARD_SIZE = board_size;
		//set-up the board
		initialiseBoard();
	}

	/**
	 * Constructor that creates a pre-specified board
	 * @param board
	 */
	public Board(int[][] board) {
		BOARD_SIZE = board.length;
		setBoard(board);
	}

	/**
	 * Method that sets every position on the board to an empty spot
	 */
	public void initialiseBoard() {
		for (int i = 0; i<BOARD_SIZE;i++) {
			for (int j = 0; j<BOARD_SIZE; j++)	{
				board_[i][j] = 0; //0 means empty spot
			}
		}
		turnBlack = true; //black always starts
	}

	/**
	 * Method that places a stone for a player on the board
	 * @param player of current turn (can be white or black)
	 * @param x coordinate of the xPosition on the board
	 * @param y coordinate of the yPosition on the board
	 */
	public void placeStone(int player, int x, int y)
	{	//check if turnBlack is true and if the player is PlayerBlack
		//(or false and PlayerWhite) before a move can be made

		if (turnBlack && player == 1) //if true, it's black's turn
		{
			board_[x][y] = 1;
			turnBlack = false;
		}
		else if (!turnBlack && player == 2) //it's white's turn
		{
			board_[x][y] = 2;
			turnBlack = true;
		}
		else {
			throw new IllegalArgumentException("It is not your turn!");
		}
	}

	/**
	 * Method that removes a stone from the board
	 * @param x the x-coordinate of the position
	 * @param y the x-coordinate of the position
	 */
	public void removeFromBoard(int x, int y) {
		if (x < 0 || x > BOARD_SIZE || y < 0 || y > BOARD_SIZE) {
			throw new IllegalArgumentException("X or Y coordinate is not a valid spot on the board");
		}
		board_[x][y] = 0;
	}

	/**
	 * Method that checks if the specified spot is filled with whatever we want to check for
	 * @param checkFor what we're looking for on the board (0 = empty, 1 = black, 2 = white)
	 * @param x x-coordinate of the spot
	 * @param y y-coordinate of the spot
	 * @return true if the spot is free
	 */
	public boolean check(int checkFor, int x, int y)
	{
		if (board_[x][y] == checkFor)
		{
			return true;
		}
		return false;
	}

	/**
	 * Getter for the board
	 * @return the current game board
	 */
	public int[][] getBoard()
	{
		return board_;
	}

	/**
	 * Setter for the board
	 * @param newBoard the updated board we want to change our current board to
	 */
	public void setBoard(int[][] newBoard)
	{
		for (int i = 0; i<BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++)	{
				board_[i][j] = newBoard[i][j]; //have to do it item by item because else the references stays
			}
		}
	}

	/**
	 * Setter for the board
	 * @param newBoard the updated board we want to change our current board to
	 */
	public void setBoard(Board newBoard)
	{
		for (int i = 0; i<BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++)	{
				board_[i][j] = newBoard.getBoard()[i][j]; //have to do it item by item because else the references stays
			}
		}
	}


	/**
	 * Method that prints the board
	 */
	public void printBoard() {
		for (int i=0; i< BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++) {
				System.out.print("[" + board_[i][j] + "] ");
			}
			System.out.println();
		}
	}

	/**
	 * Method that returns the center point of the board
	 * @return the center value
	 */
	public int getCenter() {
		return (BOARD_SIZE )/2; //in case the number is not divisible by 2, it rounds down
	}

	/**
	 * Method that checks if two boards are equal
	 * @param board Board board
	 * @return true if they are equal, false if not
	 */
	public boolean equals(Board board) {
		for (int i = 0; i<BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++)	{
				if (board_[i][j] != board.getBoard()[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method that checks if two boards are equal
	 * @param board int[][] board
	 * @return true if they are equal, false if not
	 */
	public boolean equals(int[][] board) {
		for (int i = 0; i<BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++)	{
				if (board_[i][j] != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
