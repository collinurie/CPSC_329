package Pente;

import ruleSets.FiveInARowRuleSet;
import ruleSets.FreestyleRuleSet;
import ruleSets.GomakuCaroRuleSet;
import ruleSets.GomakuFreestyleRuleSet;
import ruleSets.GomakuOmokRuleSet;
import ruleSets.GomakuTraditionalRuleSet;
import ruleSets.KeryoRuleSet;
import ruleSets.NoCapturesRuleSet;
import ruleSets.RuleSet;
import ruleSets.TournamentRuleSet;
import ruleSets.TraditionalRuleSet;

/**
 * @author mb5053 Class to represent an actual game of pente. takes players and
 *         a ruleset, and starts a while loop to represent the game. Sends
 *         requests for user input periodically
 */
public class PenteGame {

	/*
	 * Pente: standard, tournament, Keryo-Pente, freestyle, five-in-a-row, no
	 * captures Gomoku: standard, freestyle, Caro, Omok
	 */
	public static final int STANDARD = 0;
	public static final int TOURNAMENT = 1;
	public static final int KERYO = 2;
	public static final int FREESTYLE = 3;
	public static final int FIVE_IN_A_ROW = 4;
	public static final int NO_CAPTURES = 5;
	public static final int GOM_STANDARD = 6;
	public static final int GOM_FREESTYLE = 7;
	public static final int GOM_CARO = 8;

	private PenteBoard board_;
	public Player p1_;
	public Player p2_;
	private RuleSet rules_;
	public int ruleType_;

	private Player[] players;
	
	/**
	 * @return the players
	 */
	public Player[] getPlayers () {
		return players;
	}
	
	public void setNumMoves(int n) {
		rules_.setNumMoves(n);
	}
	
	public int getNumMoves() {
		return rules_.getNumMoves();
	}

	public Player winner;

	/**
	 * Initializes a PenteGame with two players and a ruleset.
	 * @param p1 - Player one
	 * @param p2 - Player two
	 * @param ruleType - Type of rule to be implemented, as outlined by the final ints at the top of this class
	 */
	public PenteGame ( Player p1, Player p2, int ruleType ) {
		ruleType_ = ruleType;
		board_ = PenteBoard.createNewPenteBoard(19,19);
		p1_ = p1;
		p2_ = p2;

		players = new Player[2];
		players[0] = p1_;
		players[1] = p2_;
		
		if ( ruleType == TOURNAMENT ) {
			rules_ = new TournamentRuleSet(board_);
		} else if ( ruleType == KERYO ) {
			rules_ = new KeryoRuleSet(board_);
		} else if ( ruleType == FREESTYLE ) {
			rules_ = new FreestyleRuleSet(board_);
		} else if ( ruleType == FIVE_IN_A_ROW ) {
			rules_ = new FiveInARowRuleSet(board_);
		} else if ( ruleType == NO_CAPTURES ) {
			rules_ = new NoCapturesRuleSet(board_);
		} else if ( ruleType == GOM_STANDARD ) {
			rules_ = new GomakuTraditionalRuleSet(board_);
		} else if ( ruleType == GOM_FREESTYLE ) {
			rules_ = new GomakuFreestyleRuleSet(board_);
		} else if ( ruleType == GOM_CARO ) {
			rules_ = new GomakuCaroRuleSet(board_);
		} else {
			rules_ = new TraditionalRuleSet(board_);
		}
	}

	/**
	 * Determines if the game has been won yet
	 * @return - True if the game is won, false otherwise
	 */
	public boolean isWon () {
		return rules_.isWon();
	}

	/**
	 * Attempts to make the given move for the current player
	 * @param move - Move to be made
	 * @return True if the move was valid, false otherwise
	 */
	public boolean makePlayerMove ( Move move) {
		// Make sure that the submitted move is being made by the correct player
		if (move.getPlayer().getPieceNum() != getCurrentPlayer().getPieceNum()) {
			System.out.println("Player mismatch");
			return false;
		}
		
		// Attempt to make the move. If the move fails, return false
		boolean ret = rules_.makeMove(move.getPlayer(),move.getRow(),move.getCol());
		if (ret == false) {
			return false;
		}
		
		// Check for a winner
		if (rules_.isWon()) {
			winner = players[(1+rules_.getNumMoves()) % 2];
			return true;
		}
//		System.out.println("HERERERERERERERERERER");

		// If the opponent is a computer, then automatically request the next move
		if (getCurrentPlayer().isComputer()) {
			System.out.println("HERERERERERERERERERER");
			boolean success = false;
			while (!success) {
			Move nextMove = ((ComputerPlayer) getCurrentPlayer()).getNextMove(board_, this, getCurrentPlayer(), players[(rules_.getNumMoves()+1) % 2], 1);
			success = makePlayerMove(nextMove);
			}
		}
		return true;
	}
	
	/**
	 * Gets the player who needs to make a move
	 * @return
	 */
	public Player getCurrentPlayer() {
		return players[rules_.getNumMoves() % 2];
	}
	
	/**
	 * Gets the PenteBoard being worked on
	 * @return - PenteBoard used in this game
	 */
	public PenteBoard getPenteBoard() {
		return board_;
	}
	
	/**
	 * @return the rules
	 */
	public RuleSet getRules () {
		return rules_;
	}

	/**
	 * Sets the PenteBoard for this game. Extremely volatile if used in the middle of a game
	 * @param b - PenteBoard to be set
	 */
	public void setBoard(PenteBoard b) {
		board_ = b;
	}
}
