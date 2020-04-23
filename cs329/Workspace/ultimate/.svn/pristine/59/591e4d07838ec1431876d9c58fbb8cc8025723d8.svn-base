package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Pente.LocalPlayer;
import Pente.Move;
import Pente.PenteGame;

/**
 * Task for handling network user moves for a game of Pente.
 * 
 * @author mb5053
 */

public class PlayerTask implements Runnable {

	private Socket connection_;
	private PrintWriter out_;
	private BufferedReader in_;

	private int playerNum_;

	private PenteGame game_;
	private LocalPlayer player_;

	private boolean shutdown_;

	/**
	 * Creates a new PlayerTask
	 * 
	 * @param connection
	 *          - connection to be communicated of
	 * @param game
	 *          - game stored in PenteServer that should be mutated
	 * @param player
	 *          - player object from PenteServer who is submitting moves
	 * @param num
	 *          - What number player this is (the first or second)
	 */
	public PlayerTask ( Socket connection, PenteGame game, LocalPlayer player,
	                    int num ) {
		connection_ = connection;
		player_ = player;
		shutdown_ = false;
		game_ = game;
		playerNum_ = num;
	}

	/**
	 * Creates a sentinel thread that listens for input from it's network user,
	 * and converts those messages into game actions in PenteServer
	 */
	public void run () {
		try {
			// Initialize communication
			out_ = new PrintWriter(new OutputStreamWriter(connection_
			    .getOutputStream()));
			in_ = new BufferedReader(new InputStreamReader(connection_
			    .getInputStream()));

			// Attempt to handshake with user
			boolean connected = handshake();

			System.out.println("PlayerTask " + playerNum_
			    + " connected to a network player.");

			// If the handshake was successful, proceed
			if ( connected ) {

				// Tell the network player which player they are
				informPlayerNumber();

				// If this is the second player, send a board immediately so that the
				// GUI updates
				if ( playerNum_ == 1 ) {
					sendBoard(game_.getPenteBoard().getBoard());
				}

				// As long as the game is still going
				while ( !shutdown_ ) {

					String inp = in_.readLine();
					if ( inp.equals("shutdown") ) {
						shutdown_ = true;
					} else {
						// If the last message wasn't shutdown, then parse it as a move
						Move nextMove = receiveMove(inp);

						System.out.println(" Player " + nextMove.getPlayer().getPieceNum()
						    + " wants to move to " + nextMove.getRow() + ", "
						    + nextMove.getCol());

						// Attempt to make the given move
						boolean madeMove = game_.makePlayerMove(nextMove);

						if ( madeMove ) {
							// If the move was successful, then send a success message and the
							// board back to the user
							int[][] board = game_.getPenteBoard().getBoard();
							sendSuccess(board);

							// Tell every spectator task to wake up and send their user a new
							// board
							for ( SpectatorTask s : PenteServer.getSpectators_() ) {
								synchronized ( s ) {
									s.notify();
								}
							}
						} else {
							// If this wasn't a valid move, then tell the user
							sendFailure();
						}
						if ( game_.isWon() ) {
							shutdown_ = true;
						}
					}

				}
			} else {
				System.out.println("Failed Handshake. Ending...");
			}

		} catch ( IOException e ) {
			System.out
			    .println("Failed to initialize connection to player. Ending...");
		}

	}

	/**
	 * Sends out a message saying the most recent move was a failure.
	 */
	private void sendFailure () {
		out_.println("Invalid Move");
		out_.flush();
	}

	/**
	 * Sends a success message, followed by a string representation of the board
	 * 
	 * @param board
	 *          - the board that should be sent to the user
	 */
	private void sendSuccess ( int[][] board ) {
		out_.println("Valid Move");
		out_.flush();
		sendBoard(board);
	}

	/**
	 * Sends a string representation of a PenteBoard to a connection
	 * 
	 * @param board
	 *          - board to be encoded as a string and sent
	 */
	private void sendBoard ( int[][] board ) {
		for ( int i = 0 ; i < board.length ; i++ ) {
			for ( int j = 0 ; j < board[i].length ; j++ ) {
				out_.print(board[i][j] + " ");
			}
			out_.println();
		}
		out_.flush();
	}

	/**
	 * Accepts a move as a string and parses it into a Move Object
	 * 
	 * @return
	 * @throws IOException
	 */
	private Move receiveMove ( String in ) throws IOException {
		String[] elements = in.split(";");

		int row = Integer.parseInt(elements[0]);
		int col = Integer.parseInt(elements[1]);
		int player = Integer.parseInt(elements[2]);

		return new Move(row,col,player_);
	}

	/**
	 * Initiates the handshake between a PlayerTask and a NetworkPlayer
	 * 
	 * @return True if the handshake was successful, false otherwise
	 * @throws IOException
	 */
	private boolean handshake () throws IOException {
		out_.println("PENTE");
		out_.flush();
		return in_.readLine().equals("PENTE_PLAYER");
	}

	/**
	 * Sends the NetworkPlayer their player number. If this is the first player,
	 * it also asks for a game mode and sets PenteServer's game accordingly
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private void informPlayerNumber () throws NumberFormatException, IOException {
		System.out.println("Sending " + playerNum_);
		out_.println(playerNum_ + "");
		out_.flush();
		if ( playerNum_ == 0 ) {
			int mode = Integer.parseInt(in_.readLine());
			System.out.println("setting gamemode to: " + mode);
			PenteServer.setGame(mode);
			game_ = PenteServer.game_;
		}
	}
	
	/**
	 * Shuts this thread down
	 */
	public void shutdown() {
		shutdown_ = true;
	}
}
