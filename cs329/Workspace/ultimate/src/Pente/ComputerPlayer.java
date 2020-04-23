package Pente;

import java.util.ArrayList;

import Pente.PenteBoard;
import Pente.Player;

/**
 * Computer controlled Pente Player.
 * 
 * @author mb5053
 */
public class ComputerPlayer extends Player {

	/**
	 * Creates a new computer controlled Pente Player
	 * @param name - Name of this player
	 */
	public ComputerPlayer ( String name ) {
		super(name);
		isComputer_ = true;
		isNetwork_ = false;
	}

	/**
	 * Sets the game that moves will be submitted to
	 * 
	 * @param game
	 */
	public void setGame ( PenteGame game ) {
		game_ = game;
	}

	/**
	 * Gets the integer array representing the board of the game this player is
	 * participating in
	 */
	public int[][] getBoardArray () {
		return game_.getPenteBoard().getBoard();
	}

	/**
	 * Abstract method call from Player. This method does not serve any purpose
	 * for computer player, as no external user ever accesses it.
	 */
	public boolean handleUserInput ( Move move ) {
		// This should never get called
		System.out
		    .println("You called the 'handleUserInput' method on a ComputerPlayer.\n This won't crash, but it also won't do anything either!");
		return true;
	}

	/**
	 * Returns true if the game has been won, false otherwise
	 */
	public boolean gameIsWon () {
		return game_.isWon();
	}

	/**
	 * Gets the next move for the player, looking depth moves ahead
	 * 
	 * @param board
	 *          - Board being examined
	 * @param game
	 *          - game being played right not (needed to get rule variant)
	 * @param player
	 *          - Player making the move
	 * @param opponent
	 *          - Opponent player is playing against
	 * @param depth
	 *          - number of moves to look ahead
	 * @return - The optimal move to make
	 */
	public Move getNextMove ( PenteBoard board, PenteGame game, Player player,
	                          Player opponent, int depth ) {

		// Creates a copy of this and the opponent for testing
		Player opp = new LocalPlayer("opponent");
		opp.setCaptures(opponent.getCaptures());
		Player me = new LocalPlayer("me");
		me.setCaptures(this.getCaptures());

		// Creates a copy of this game (or close enough) to test with, so we don't
		// mutate the actual game

		PenteGame tempGame;

		// Selects the cells that are worth looking at (those within 3 cells of an
		// existing piece)
		boolean[][] mask = generateMoveMask(board.getBoard(),3);

		// Create an empty array to store the grades for each potential move
		int[][] grades = new int[mask.length][mask[0].length];

		// For each possible move in grades
		for ( int row = 0 ; row < board.getRows() ; row++ ) {
			for ( int col = 0 ; col < board.getCols() ; col++ ) {
				// If this move is within the prescribed mask
				if ( mask[row][col] ) {

					tempGame = new PenteGame(me,opp,game.ruleType_);

					// Set the temporary board with the current board state
					tempGame.setBoard(board);
					tempGame.setNumMoves(game.getNumMoves() + 1);

					// Attempt to make this possible move
					boolean moved = tempGame.makePlayerMove(new Move(row,col,this));

					if ( !moved ) {
						// If it wasn't a valid move, make the score negative so it won't be
						// chosen
						grades[row][col] = -100;

					} else if ( tempGame.isWon() ) {
						// If this move wins the game, then pick it
						grades[row][col] = 100;
					} else {
						// If this move is valid, set the value to less than a win, but
						// still pickable
						grades[row][col] = 10;
					}
				} else {
					// This move is outside the mask, so make it un-pickable
					grades[row][col] = -100;
				}
			}
		}

		return selectBestMoveFromGrades(grades,this);

	}

	/**
	 * Traverses a 2D array of ints, and looks for the highest valued cell inside.
	 * 
	 * @param grades
	 *          - Array of ints to be traversed
	 * @param player
	 *          - Player who should make the returned move
	 * @return - A move represeting the optimal move
	 */
	private Move selectBestMoveFromGrades ( int[][] grades, Player player ) {
		int currentMaxGrade = -1000;

		// List to store all of the valid moves
		ArrayList<Move> bestList = new ArrayList<Move>();

		// Loop over all the grades
		for ( int row = 0 ; row < grades.length ; row++ ) {
			for ( int col = 0 ; col < grades[0].length ; col++ ) {
				// If this move is better than any other, clear the moves list and add
				// this one
				if ( grades[row][col] > currentMaxGrade ) {
					currentMaxGrade = grades[row][col];
					bestList.clear();
					bestList.add(new Move(row,col,player));

					// If this is just as good as the last, then add it to the list
				} else if ( grades[row][col] == currentMaxGrade ) {
					bestList.add(new Move(row,col,player));
				}
				// System.out.print(grades[row][col] + " ");
			}
			// System.out.println();
		}
		// Pick a random move from the list of options
		Move result = bestList.get((int) (Math.random() * (bestList.size() - 1)));
		System.out.println("I want to move to: (" + result.getRow() + ", "
		    + result.getCol() + "). It's score is: " + currentMaxGrade);
		return result;
	}

	/**
	 * Generates a boolean mask that uncovers only cells that are within the given
	 * range of a non-empty cell
	 * 
	 * @param board
	 *          - board being looked at
	 * @return a mask where, if true, there is a non-zero value within the range
	 *         of each cell, and false if there isn't
	 */
	private boolean[][] generateMoveMask ( int[][] board, int range ) {
		boolean[][] mask = new boolean[board.length][board[0].length];
		for ( int row = 0 ; row < mask.length ; row++ ) {
			for ( int col = 0 ; col < mask[row].length ; col++ ) {
				if ( cellIsWithinRange(board,row,col,range) ) {
					mask[row][col] = true;
				}
				if ( board[row][col] != 0 ) {
					mask[row][col] = false;
				}
			}
		}
		return mask;

	}

	/**
	 * Determines if there is a non-zero value within the given range of a cell in
	 * a 2D array
	 * 
	 * @param board
	 *          - 2D int array being examined
	 * @param row
	 *          - row of interest
	 * @param col
	 *          - col of interest
	 * @param range
	 *          - number of cells to look in each direciton
	 * @return true if there is a non-zero cell within range of the input, false
	 *         otherwise
	 */
	private boolean cellIsWithinRange ( int[][] board, int row, int col,
	                                    int range ) {
		for ( int i = row - range ; i < row + range + 1 ; i++ ) {
			for ( int j = col - range ; j < col + range + 1 ; j++ ) {
				try {
					if ( board[i][j] != 0 ) {
						return true;
					}
				} catch ( ArrayIndexOutOfBoundsException e ) {
					continue;
				}
			}
		}
		return false;
	}

}
