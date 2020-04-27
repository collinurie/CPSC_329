
/**
 * @author Famke Nouwens
 *
 */
public class Game {

	private Board gameBoard;

	public Player whitePlayer;
	public Player blackPlayer;
	private boolean moveIsMade;
	public boolean tournamentRules_ = false;
	private int turn;
	private int currentPlayer;
	private int lastMoveX, lastMoveY; //x and y-coordinate of the last made move (so the last turn)
	private int testX, testY;
	public boolean winnerWhite, winnerBlack;
	private boolean gameOverCheckUpDirection; //to keep track of which direction we checked for gameOver

	/**
	 * Constructor that sets up the game
	 */
	public Game(int board_size, boolean tournamentRules)	{
		//creates the board and the players
		gameBoard = new Board(board_size);
		whitePlayer = new Player();
		blackPlayer = new Player();
		tournamentRules_ = tournamentRules;
		start(); //start the game
	}

	/**
	 * Getter that returns the gameboard
	 * @return the gameboard
	 */
	public Board getBoard() {
		return gameBoard;
	}

	/**
	 * Method that sets the board for a game
	 */
	public void setBoard(Board board) {
		gameBoard.setBoard(board);
	}

	/**
	 * Method that can reset the game
	 */
	public void resetGame() {
		gameBoard.initialiseBoard();
		winnerWhite = false;
		winnerBlack = false;
		turn = 0;
		currentPlayer = 1;
		start();
	}

	//Method that should call the graphics
	public void start() {

	}

	/**
	 * Method that checks if the clicked spot for a player is a valid move
	 * @param x the x-coordinate of the clicked spot
	 * @param y the y-coordinate of the clicked spot
	 */
	public void makeMove(int x, int y) {
		if (validMove(x,y)) { //we need to check if the x and y are valid, if so we can "continue"

			//System.out.println("Current player: " + currentPlayer);

			if (turn == 0) //first move made by black
			{
				if (x == gameBoard.getCenter() && y == gameBoard.getCenter() && gameBoard.check(0, x, y)) //center of the board is at position 9,9 in the array
				{
					gameBoard.placeStone(currentPlayer, x, y);
					moveIsMade = true;
				}
			}
			else if (tournamentRules_ && turn == 2) {
				//the second move made by black must be at least 3 or more intersections away
				if (x <  gameBoard.getCenter()-2 || x >  gameBoard.getCenter()+2) {
					if (gameBoard.check(0, x, y)) {
						//if the clicked spot is empty, we can place the stone
						moveIsMade = true;
						gameBoard.placeStone(currentPlayer, x, y);
					}
				}
				//x is now between 7 and 11, so our y must be at least three intersections away from the center
				else if (y <  gameBoard.getCenter()-2 || y >  gameBoard.getCenter()+2) {
					if (gameBoard.check(0, x, y)) {
						//if the clicked spot is empty, we can place the stone
						moveIsMade = true;
						gameBoard.placeStone(currentPlayer, x, y);
					}
				}
				else {
					throw new IllegalArgumentException ("Stone is too close to the centre!"); //this stops the program
					//for the game better to just not do anything else
				}
			}
			else {
				if (gameBoard.check(0, x, y)) {
					//if the clicked spot is empty, we can place the stone
					moveIsMade = true;
					gameBoard.placeStone(currentPlayer, x, y);
				}
			}
			assert moveIsMade == true : "No move is made";
			//assumption: we have made a move when we reach here

			//updating the lastMove coordinates before we go on to the next turn
			lastMoveX = x;
			lastMoveY = y;

			//after the stone is placed on the board, we check if it captures stones from the opponent
			captureStoneCheck();

			//check if the game is finished after making the currentMove
			if (gameOverCheckMove() || gameOverCheckCapturedStones())
			{

				System.out.println("Game is finished");
				if (currentPlayer == 1)
				{
					winnerBlack = true;
					System.out.println("Black has won");
				}
				else {
					winnerWhite = true;
					System.out.println("White has won");
				}
			}

			//switch the currentPlayer
			if (currentPlayer == 1) {
				currentPlayer = 2;
			}
			else {
				currentPlayer = 1;
			}
			turn++;
			moveIsMade = false;
		}
	}

	/**
	 * Method that checks if a move is inside the board
	 * @param x x-Coordinate of move
	 * @param y y-coordinate of move
	 * @return true if the move is valid
	 */
	public boolean validMove(int x, int y) {
		if (x >= 0 && x < gameBoard.BOARD_SIZE && y >= 0 && y < gameBoard.BOARD_SIZE) {
			return true;
		}
		System.out.println("Move is not valid");
		return false;
	}

	/**
	 * Method that checks if the number of captured stones by a player is enough to win
	 * @return true if a player has won the game
	 */
	public boolean gameOverCheckCapturedStones() {
		if (whitePlayer.getCapturedStones() >= 10 || blackPlayer.getCapturedStones() >= 10)
		{
			return true;
		}
		return false;
	}

	/**
	 * Method that checks if the game is won by having 5 or more consecutive stones in a row/column/diagonal
	 * @return true if the current player won the game
	 */
	public boolean gameOverCheckMove() {
		//we only need to look from the position of the last placed stone
		//System.out.println("GameOverCheckMove after move: " + lastMoveX + "," + lastMoveY);

		int consecStones = 0; //the number of consecutive stones in one direction

		//diagonal top-left to bottom-right
		//System.out.println("Diagonal top-left to bottom-right");
		consecStones += checkGameOverBothDirections(-1, -1);
		if (consecStones >= 5) {	return true; }

		//check the vertical direction
		//System.out.println("vertical direction");
		consecStones = 0;
		consecStones += checkGameOverBothDirections(-1, 0);
		if (consecStones >= 5) {	return true; }

		//check the horizontal direction
		//System.out.println("horizontal direction");
		consecStones = 0;
		consecStones += checkGameOverBothDirections(0, -1);
		if (consecStones >= 5) {	return true; }

		//diagonal bottom-left to top-right
		//System.out.println("diagonal bottom-left to top-right ");
		consecStones = 0;
		consecStones += checkGameOverBothDirections(1, -1);
		if (consecStones >= 5) {	return true; }

		//if all previous tests fail, we didn't have 5 consecutive stones in a row, so there is no game over
		//System.out.println("No game over, return false");
		return false;
	}

	/**
	 * Method that counts the consecutive stones in a direction (both ways)
	 * @param dirX the x-coordinate of the direction (typically -1,0,1)
	 * @param dirY the y-coordinate of the direction (typically -1,0,1)
	 * @return the number of consecutive stones in that direction (both ways)
	 */
	public int checkGameOverBothDirections(int dirX, int dirY) {
		int consecStones = 1; //the first one is the one that was placed in the turn

		//creating temporary variables to hold the position on the board we check for stones
		int currentX = lastMoveX + dirX;
		int currentY = lastMoveY + dirY;

		//prefer while loop in this case because it will always finish and
		//because we don't know when there are no more stones in a certain direction
		while (gameBoard.check(currentPlayer, currentX, currentY)
				&& currentX >= 0 && currentX <gameBoard.BOARD_SIZE
				&& currentY >= 0 && currentY < gameBoard.BOARD_SIZE ){
			consecStones++;
			currentX += dirX;
			currentY += dirY;
		}

		//checking the opposite direction
		currentX = lastMoveX - dirX; //"reset" to where the stone was last placed and then go in the opposite direction
		currentY = lastMoveY - dirY;
		while (gameBoard.check(currentPlayer, currentX, currentY)
				&& currentX >= 0 && currentX <gameBoard.BOARD_SIZE
				&& currentY >= 0 && currentY < gameBoard.BOARD_SIZE ){
			consecStones++;
			currentX -= dirX;
			currentY -= dirY;
		}
		//System.out.println("Total number of consecutive stones for player " + currentPlayer + " in direction X:" + dirX+ ", Y:" + dirY + " is: " + consecStones);
		return consecStones;
	}
	//I found this website online that helped me figure out how to check for consecutive stones (only realised later on that is from this school):
	// http://math.hws.edu/eck/cs124/javanotes3/c8/ex-8-6-answer.html


	/**
	 * Method that checks if any stones can be captured
	 */
	public void captureStoneCheck() {
		//we check in each direction for two stones of the opposite colour and then for one of its own
		//since we can capture multiple stones with one move we must check all directions

		//check diagonal left up
		checkCapturedStonesOneDirection(-1, -1);

		//check diagonal left down
		checkCapturedStonesOneDirection(-1, 1);

		//check diagonal right up
		checkCapturedStonesOneDirection(1, 1);

		//check diagonal right down
		checkCapturedStonesOneDirection(1, -1);

		//check vertical up
		checkCapturedStonesOneDirection(-1, 0);

		//check vertical down
		checkCapturedStonesOneDirection(1, 0);

		//check horizontal right
		checkCapturedStonesOneDirection(0, 1);

		//check horizontal left
		checkCapturedStonesOneDirection(0, -1);
	}

	/**
	 * Method that checks if stones can be captured (in one direction) and then removes them from the board
	 * @param dirX the x-coordinate of the direction (typically -1,0,1)
	 * @param dirY the y-coordinate of the direction (typically -1,0,1)
	 */
	public void checkCapturedStonesOneDirection( int dirX, int dirY) {
		int consecStones = 0;
		int currentX = lastMoveX + dirX; //creating temporary variables to hold the position on the board we check for stones
		int currentY = lastMoveY + dirY;

		int[][] capturedStoneLocations = new int[8][]; //max number of captured stones can be 8

		int otherPlayer;
		if (currentPlayer == 1)
		{
			otherPlayer = 2;
		}
		else {
			otherPlayer = 1;
		}
		assert otherPlayer != currentPlayer : "Opponent is same as current!";

		while (gameBoard.check(otherPlayer, currentX, currentY) &&
				currentX >= 0 && currentX <gameBoard.BOARD_SIZE  &&
				currentY >= 0 && currentY < gameBoard.BOARD_SIZE ){
			int[] toBeCaptStone = {currentX, currentY};
			capturedStoneLocations[consecStones] = toBeCaptStone; //we only remove these opponent stones if they're followed by own stone
			consecStones++;
			currentX += dirX;
			currentY += dirY;
		}

		if (gameBoard.check(currentPlayer, currentX, currentY) && consecStones == 2) { //two stones of the opposite player cornered by two stones of current player
			System.out.println("capturing stones");
			if (currentPlayer == 1)
			{
				blackPlayer.addCapturedStones();
			}
			else {
				whitePlayer.addCapturedStones();
			}
			//removing the stones from the board
			for (int i = 0; i<capturedStoneLocations.length; i++) {
				if (capturedStoneLocations[i] != null) {
					gameBoard.removeFromBoard(capturedStoneLocations[i][0], capturedStoneLocations[i][1]);
				}
			}
		}
	}
}
