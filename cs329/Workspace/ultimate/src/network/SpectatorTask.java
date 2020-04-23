package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Pente.PenteGame;
import Pente.Player;

/**
 * @author mb5053
 */
public class SpectatorTask implements Runnable {

	private Socket connection_;
	private PrintWriter out_;
	private BufferedReader in_;
	private PenteGame game_;
	private boolean shutdown_;

	/**
	 * Creats a new SpectatorTask
	 * 
	 * @param connection
	 *          - connection to be communicated over
	 * @param game
	 *          - game to send out updates about
	 */
	public SpectatorTask ( Socket connection, PenteGame game ) {
		connection_ = connection;
		game_ = game;
		shutdown_ = false;
	}

	/**
	 * Sets this thread to always be sending out the most updated board from the
	 * game, or waiting to be woken up.
	 */
	public void run () {
		try {
			out_ = new PrintWriter(new OutputStreamWriter(connection_
			    .getOutputStream()));
			in_ = new BufferedReader(new InputStreamReader(connection_
			    .getInputStream()));

			// Attempt to connect to a GUI's listener
			boolean connected = handshake();

			// If the connection was successful
			if ( connected ) {
				while ( !shutdown_ ) {
					// If there is a winner, tell the listener
					if ( game_.isWon() ) {
						out_.println("Winner;" + game_.getCurrentPlayer().getPieceNum());
						out_.flush();
						
					// Otherwise, send out the player scores and then the updated board
					} else {
						Player p1 = game_.getPlayers()[0];
						Player p2 = game_.getPlayers()[1];
						out_.println(p1.getCaptures() + ";" + p2.getCaptures());
						out_.flush();
						sendBoard(game_.getPenteBoard().getBoard());
					}
					// Wait until a PlayerTask wakes this thread up with an update
					synchronized ( this ) {
						this.wait();
					}
				}
			}
		} catch ( IOException e ) {
			System.out.println("Failed to connect. Ending...");
		} catch ( InterruptedException e ) {
			System.out.println("Thread failed to wait. Ending...");
			e.printStackTrace();
		}
	}

	/**
	 * Sends a board over the connection to a GUI's listener
	 * @param board - PenteBoard array to be sent other the network
	 */
	private void sendBoard ( int[][] board ) {
		System.out.println("Sending the following board from spectator ");
		for ( int i = 0 ; i < board.length ; i++ ) {
			for ( int j = 0 ; j < board[i].length ; j++ ) {
				System.out.print(board[i][j]);
				out_.print(board[i][j] + " ");
			}
			out_.println();
			System.out.println();
		}
		out_.flush();
	}

	/**
	 * Initiate the handshake between this and a GUI's listener
	 * @return - True if the handshake was successful, false otherwise
	 * @throws IOException
	 */
	private boolean handshake () throws IOException {
		out_.println("spectate");
		out_.flush();
		String inp = in_.readLine();
		return inp.equals("spectate");
	}
	
	/**
	 * Tells this thread to shutdown
	 */
	public void shutdown() {
		shutdown_ = true;
	}

}
