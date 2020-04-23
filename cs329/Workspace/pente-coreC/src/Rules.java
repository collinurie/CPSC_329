
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public abstract class Rules {
	protected Board gameBoard_;
	protected int capturedStonesMin_; //this value must be specified in the constructor
	protected int capturedStonesMax_; //this value must be specified in the constructor
	protected int boardSize_;

//	private boolean allowedWinCaptures = true;
	
//	public abstract void setAllowedCapturedStones();
//	
//	public void setCapturedStones(int number) {
//		capturedStones_ = number;
//	}
	
	/**
	 * Method that counts the consecutive stones in a direction (both ways)
	 * @param dirX the x-coordinate of the direction (typically -1,0,1)
	 * @param dirY the y-coordinate of the direction (typically -1,0,1)
	 * @return the number of consecutive stones in that direction (both ways)
	 */
	public int checkGameOverBothDirections(int currentPlayer, int dirX, int dirY, int lastMoveX, int lastMoveY) {
		int consecStones = 1; //the first one is the one that was placed in the turn

		//creating temporary variables to hold the position on the board we check for stones
		int currentX = lastMoveX + dirX;
		int currentY = lastMoveY + dirY;

		//prefer while loop in this case because it will always finish and 
		//because we don't know when there are no more stones in a certain direction
		while (gameBoard_.check(currentPlayer, currentX, currentY) 
				&& currentX >= 0 && currentX <gameBoard_.BOARD_SIZE  
				&& currentY >= 0 && currentY < gameBoard_.BOARD_SIZE ){
			consecStones++;
			currentX += dirX;
			currentY += dirY;
		}

		//checking the opposite direction
		currentX = lastMoveX - dirX; //"reset" to where the stone was last placed and then go in the opposite direction
		currentY = lastMoveY - dirY; 
		while (gameBoard_.check(currentPlayer, currentX, currentY) 
				&& currentX >= 0 && currentX <gameBoard_.BOARD_SIZE 
				&& currentY >= 0 && currentY < gameBoard_.BOARD_SIZE ){
			consecStones++;
			currentX -= dirX;
			currentY -= dirY;
		}
		//System.out.println("Total number of consecutive stones for player " + currentPlayer + " in direction X:" + dirX+ ", Y:" + dirY + " is: " + consecStones);
		return consecStones;
	}
	
	/**
	 * Method that checks if stones can be captured (in one direction) and then removes them from the board
	 * @param currentPlayer - int that shows for which player we are checking (1 = black, 2 = white)
	 * @param dirX - the x-coordinate of the direction (typically -1,0,1)
	 * @param dirY - the y-coordinate of the direction (typically -1,0,1)
	 * @param lastMoveX - the x-coordinate of the last made move
	 * @param lastMoveY - the y-coordinate of the last made move
	 * @param captStones - the number of allowed stones to be captured (2 or 3)
	 * @param blackPlayer - the black player
	 * @param whitePlayer - the white player
	 */
	public void checkCapturedStonesOneDirection( int currentPlayer, int dirX, int dirY, 
	    	                                       int lastMoveX, int lastMoveY, int captStones, Player blackPlayer, Player whitePlayer) {
		
		int consecStones = 0;
		int currentX = lastMoveX + dirX; //creating temporary variables to hold the position on the board we check for stones
		int currentY = lastMoveY + dirY;

		//TODO: now we can hold max 8 stones, but if we can capture 3 at a time, this number can be bigger
		int[][] capturedStoneLocations = new int[8][]; //max number of captured stones can be 8

		int otherPlayer;
		if (currentPlayer == 1){
			otherPlayer = 2;
		}
		else{
			otherPlayer = 1;
		}
		assert otherPlayer != currentPlayer : "Opponent is same as current!";
		
		while (gameBoard_.check(otherPlayer, currentX, currentY) && 
				currentX >= 0 && currentX <gameBoard_.BOARD_SIZE  && 
				currentY >= 0 && currentY < gameBoard_.BOARD_SIZE ){
			int[] toBeCaptStone = {currentX, currentY};
			capturedStoneLocations[consecStones] = toBeCaptStone; //we only remove these opponent stones if they're followed by own stone
			consecStones++;
			currentX += dirX;
			currentY += dirY;
		}

		if (gameBoard_.check(currentPlayer, currentX, currentY) && consecStones == captStones) { //two stones of the opposite player cornered by two stones of current player
			System.out.println("capturing stones");
			if (currentPlayer == 1){
				blackPlayer.addToScore(captStones);
			}
			else {
				whitePlayer.addToScore(captStones);
			}
			//removing the stones from the board
			for (int i = 0; i<capturedStoneLocations.length; i++) {
				if (capturedStoneLocations[i] != null) {
					gameBoard_.removeFromBoard(capturedStoneLocations[i][0], capturedStoneLocations[i][1]);
				}
			}
		}
	}
	
	/**
	 * Checks to see if the proposed move is valid based on the rules of the game.
	 * @param x - x location of proposed move 
	 * @param y - y location of proposed move
	 * @param color - color of the player proposing the move. 
	 * @return
	 */
	public abstract boolean validMove(int x, int y, int color);
	
	/**
	 * Check all directions for a win based on the number of stones in a row. 
	 * @param player - the player for which we're checking
	 * @param lastMoveX - the x-coordinate of the last move made
	 * @param lastMoveY - the y-coordinate of the last move made
	 * @return true if gameOver, false otherwise
	 */
	public boolean gameOverCheckMove (int player, int lastMoveX, int lastMoveY) {
		//in standard pente we can win by 5 or more (>=) in a row
		
		int consecStones = 0; //the number of consecutive stones in one direction 

		//diagonal top-left to bottom-right
		consecStones += checkGameOverBothDirections(player, -1, -1, lastMoveX, lastMoveY);
		if (winConditionConsec(consecStones)) {	return true; }

		//check the vertical direction
		consecStones = 0;
		consecStones += checkGameOverBothDirections(player, -1,0, lastMoveX, lastMoveY);
		if (winConditionConsec(consecStones)) {	return true; }

		//check the horizontal direction
		consecStones = 0;
		consecStones += checkGameOverBothDirections(player, 0,-1, lastMoveX, lastMoveY);
		if (winConditionConsec(consecStones)) {	return true; }

		//diagonal bottom-left to top-right
		consecStones = 0;
		consecStones += checkGameOverBothDirections(player, -1,1, lastMoveX, lastMoveY);
		if (winConditionConsec(consecStones)) {	return true; }

		//if all previous tests fail, we didn't have 5 consecutive stones in a row, so there is no game over
		return false;
	}
	
	/**
	 * Method that checks if the game is over based on how many stones are in a row
	 * @param consecStones - the number of stones in one direction
	 * @return true if the value is enough for a win, false otherwise
	 */
	public abstract boolean winConditionConsec(int consecStones);
	
	/**
	 * Method that checks if the game is over based on how many captures a player has
	 * @param currentPlayer - the player who's score is being checked 
	 * @return true if the value is enough for a win, false otherwise
	 */
	public abstract boolean winConditionCapture(Player currentPlayer);
	
	/**
	 * checks for both win by capture and win by 
	 * @param player
	 * @param lastMoveX
	 * @param lastMoveY
	 * @param blackPlayer
	 * @param whitePlayer
	 * @return
	 */
	public boolean checkWin( int player, int lastMoveX, int lastMoveY, Player blackPlayer, Player whitePlayer){
		if(gameOverCheckMove(player,lastMoveX,lastMoveY) ||
				checkWinByCapture(player,lastMoveX,lastMoveY,blackPlayer,whitePlayer)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check all directions for a win based on the number of stones captured.
	 * @param player - the player for which we're checking
	 * @param lastMoveX - the x-coordinate of the last move made
	 * @param lastMoveY - the y-coordinate of the last move made
	 * @return true if gameOver, false otherwise
	 */
	public boolean checkWinByCapture (int player, int lastMoveX, int lastMoveY, Player blackPlayer, Player whitePlayer) {
			
		if(capturedStonesMin_ == 0) { // when there are no captures allowed in the specific set of rules. 
			return false;
		}
		//we check in each direction for a specified number of stones of the opposite colour and then for one of its own
			//since we can capture multiple stones with one move we must check all directions
			for(int i = capturedStonesMin_; i <= capturedStonesMax_; i++) {
				//System.out.println(i);
			//check diagonal left up
			checkCapturedStonesOneDirection(player, -1, -1, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check diagonal left down
			checkCapturedStonesOneDirection(player, -1, 1, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check diagonal right up
			checkCapturedStonesOneDirection(player, 1, 1, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check diagonal right down
			checkCapturedStonesOneDirection(player, 1, -1, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check vertical up
			checkCapturedStonesOneDirection(player, -1, 0, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check vertical down
			checkCapturedStonesOneDirection(player, 1, 0, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check horizontal right
			checkCapturedStonesOneDirection(player, 0, 1, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);

			//check horizontal left
			checkCapturedStonesOneDirection(player, 0, -1, lastMoveX, lastMoveY, i, blackPlayer, whitePlayer);
			}

			if (player == 1) {
				return winConditionCapture(blackPlayer);
			}
			else {
				return winConditionCapture(whitePlayer);
			}
	}
	

}
