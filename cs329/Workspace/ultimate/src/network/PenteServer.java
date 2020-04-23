package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Pente.LocalPlayer;
import Pente.PenteGame;

/**
 * @author mb5053 Server for hosting a game of Pente. Supports two players who
 *         are assigned roles on a first-come-first-serve basis. Any users
 *         connecting after this are designated as spectators. PenteServer
 *         should be run before any users attempt to connect.
 */
public class PenteServer {

	private static final int PORT = 14457;

	volatile private static boolean shutdown_ = false;

	volatile public static PenteGame game_;
	private static LocalPlayer p1_;
	private static LocalPlayer p2_;

	private static int playerNum_;

	private static ArrayList<Thread> threads_;
	
	// Stores all of the players that can submit moves to this game
	private static PlayerTask[] playerTasks_;
	// Stores all of the users who want board updates (both players and
	// spectators)
	private static ArrayList<SpectatorTask> spectators_;

	public static void main ( String[] args ) throws IOException {

		playerNum_ = 0;

		threads_ = new ArrayList<Thread>();

		spectators_ = new ArrayList<SpectatorTask>();
		playerTasks_ = new PlayerTask[2];

		// Start listening on the given port
		ServerSocket server = new ServerSocket(PORT);

		// The two player objects who will controlled over the network
		p1_ = new LocalPlayer("p1");
		p2_ = new LocalPlayer("p2");

		// Next player to be assigned
		LocalPlayer nextPlayer = p1_;

		while ( !shutdown_ ) {
			try {
				Socket connection = server.accept();
				System.out.println("Established Connection.");

				// If there are already two threads running, then every successive
				// connection should be treated as a spectator
				if ( threads_.size() > 2 ) {

					// Create the spectator task, assign it to a thread, and save it to a
					// list for later use
					SpectatorTask spectator = new SpectatorTask(connection,game_);
					Thread t = new Thread(spectator);
					spectators_.add(spectator);
					threads_.add(t);
					t.setDaemon(true);
					t.start();

				} else {

					// Start a thread to manage input from the network user
					PlayerTask task =
					    new PlayerTask(connection,game_,nextPlayer,playerNum_);
					playerTasks_[playerNum_] = task;
					Thread t = new Thread(task);

					// Increment playerNum so that the next user is assigned to be player
					// 2
					threads_.add(t);
					t.setDaemon(true);
					t.start();

					// Must also create a spectator thread for this player, so they can
					// receive board updates
					Socket connection2 = server.accept();
					SpectatorTask spectator = new SpectatorTask(connection2,game_);
					spectators_.add(spectator);
					t = new Thread(spectator);
					threads_.add(t);
					t.setDaemon(true);
					t.start();

					nextPlayer = p2_;

				}
				playerNum_++;
			} catch ( IOException e ) {
				System.out.println("I/O error: " + e.getMessage());
				shutdown();
				try {
					server.close();
				} catch ( IOException e1 ) {
					System.out.println("I/O error: " + e.getMessage());
				}
			}
		}
		server.close();
	} // main

	/**
	 * Closes all active server connections and makes the server stop listening
	 * for new ones
	 */
	public static void shutdown () {
		shutdown_ = true;
		for (PlayerTask t : playerTasks_) {
			t.shutdown();
		}
		for (SpectatorTask s : spectators_) {
			s.shutdown();
		}
	}

	/**
	 * Sets the game that is being played. This should be called by the first
	 * player who connects
	 * 
	 * @param gameMode
	 */
	public static void setGame ( int gameMode ) {
		game_ = new PenteGame(p1_,p2_,gameMode);
		p1_.setGame(game_);
		p2_.setGame(game_);

	}

	/**
	 * Gets a list of all the threads that have been started to send board updates
	 * to their network user
	 * 
	 * @return the spectators_
	 */
	public static ArrayList<SpectatorTask> getSpectators_ () {
		return spectators_;
	}

}
