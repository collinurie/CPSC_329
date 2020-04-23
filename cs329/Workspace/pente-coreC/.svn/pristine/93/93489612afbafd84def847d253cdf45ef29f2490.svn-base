
/**
 * @author Collin Urie, Famke Nouwens
 *
 */
public class Game {
	private Rules rules_;
	private Board board_;
	private Player blackPlayer_, whitePlayer_;
	private boolean turnBlack;

	public Game(Rules gameRules, int boardSize) {
		turnBlack = true; //black always starts the game
		rules_ = gameRules;
		board_ = new Board(boardSize);
		blackPlayer_ = new Player(1);
		whitePlayer_ = new Player(2);
	}

	/**
	 * Makes a move if it is valid based on the current rules.
	 * @param x - the x position the move is to be made in.
	 * @param y - the y position the move is to be made in.
	 * @param player - the player making the move. 
	 */
	public void makeMove(int x, int y, int player) {
		if (turnBlack && player == 1 || !turnBlack & player ==2) {
			if(rules_.validMove(x,y,player)){
				board_.placeStone(player,x,y);
				if (turnBlack) {
					turnBlack = false;
				}
				else {
					turnBlack = true;
				}

				//check if the last made move results in a win or a capture
				if (rules_.checkWin(player,x,y,blackPlayer_,whitePlayer_))
				{
					//TODO end game based on GUI
					System.out.println("game is finished");
				}
			}
			System.out.println("not a valid move");
		}
	}


}
