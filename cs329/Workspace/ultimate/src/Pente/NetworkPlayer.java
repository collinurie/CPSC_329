package Pente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class for communicating with a central PenteServer. Used to pass potential
 * moves into game actions.
 * 
 * @author mb5053
 */
public class NetworkPlayer extends Player {

	private PrintWriter out_; // to write moves to server
	private BufferedReader in_; // to read moves from server
	private String IP_; // IP address for the server

	public int gameMode_;

	private int port_; // Port to connect to the server
	private Socket connection_;
	private int playerOrderNum_;

	private boolean isWon_;

	private int[][] recentBoard_;

	/**
	 * Constructor for the Network player which sets up communications with the
	 * server.
	 * 
	 * @param name
	 *          of player
	 * @param IP
	 *          address for the server
	 * @param port
	 *          to connect to the server
	 * @throws IOException
	 */
	public NetworkPlayer ( String name ) throws IOException {
		super(name);
		isComputer_ = false;
		isNetwork_ = true;
		recentBoard_ = new int[19][19];
		for ( int i = 0 ; i < recentBoard_.length ; i++ ) {
			for ( int j = 0 ; j < recentBoard_[i].length ; j++ ) {
				recentBoard_[i][j] = 0;
			}
		}
	}

	/**
	 * Initiates the connection procedure for this player.
	 * 
	 * @param IP
	 *          - IP address to connect to
	 * @param port
	 *          - Port to connect to
	 * @throws IOException
	 */
	public void connect ( String IP, int port ) throws IOException {
		// Sets up reader/writer
		initializeConnection(IP,port);

		// Attempt handshake
		boolean connected = handshake();

		// If connection is successful
		if ( connected ) {
			System.out.println("Connected to PenteServer");
			String inp = in_.readLine();
			playerOrderNum_ = Integer.parseInt(inp);

			// If we are player 0, then send back what type of game should be created
			if ( playerOrderNum_ == 0 ) {
				out_.println(gameMode_);
				out_.flush();
			}
		}
	}

	/**
	 * @return True if the handshake is succesful, false otherwise
	 * @throws IOException
	 */
	private boolean handshake () throws IOException {
		String inp = in_.readLine();
		if ( inp.equals("PENTE") ) {
			out_.println("PENTE_PLAYER");
			out_.flush();
			return true;
		}
		return false;
	}

	/**
	 * Gets the board array
	 * 
	 * @return - 2D Integer array representing the state of the game's board
	 */
	public int[][] getBoardArray () {
		return recentBoard_;
	}

	/**
	 * @return True if the game is won, false otherwise
	 */
	public boolean gameIsWon () {
		return isWon_;
	}

	/**
	 * Initializes the readers and writers used to communicate with the server
	 * 
	 * @param IP
	 *          - IP to reach out to
	 * @param port
	 *          - Port the server resides at
	 * @throws IOException
	 */
	public void initializeConnection ( String IP, int port ) throws IOException {
		IP_ = IP;
		port_ = port;
		connection_ = new Socket(IP_,port_);
		out_ =
		    new PrintWriter(new OutputStreamWriter(connection_.getOutputStream()));
		in_ =
		    new BufferedReader(new InputStreamReader(connection_.getInputStream()));
		System.out.println("NETWORK PLAYER CONNECTED");
	}

	/**
	 * Converts inputs from a GUI into instructions for the server
	 * 
	 * @return True if the move was valid, false otherwise
	 */
	public boolean handleUserInput ( Move move ) {
		try {
			out_.println(move.getRow() + ";" + move.getCol() + ";"
			    + move.getPlayer().getPieceNum());
			out_.flush();
			String response = in_.readLine();
			if ( response.equals("Valid Move") ) {
				int[][] newBoard = readBoard();
				setRecentBoard(newBoard);
				return true;
			} else {
				return false;
			}
		} catch ( IOException e ) {
			// If there's an error, just try again (by returning false)
			return false;
		}
	}

	/**
	 * @return a 2D integer array read from the server
	 * @throws IOException
	 */
	private int[][] readBoard () throws IOException {
		int[][] newBoard = new int[19][19];
		for ( int i = 0 ; i < newBoard.length ; i++ ) {
			String inp = in_.readLine();
			System.out.println(inp);
			String[] line = inp.split(" ");
			for ( int j = 0 ; j < line.length ; j++ ) {
				newBoard[i][j] = Integer.parseInt(line[j]);
			}
		}
		return newBoard;
	}

	/**
	 * @return the most Recent board from the server
	 * @throws IOException
	 */
	public int[][] awaitResponse () throws IOException {
		recentBoard_ = readBoard();
		return recentBoard_;
	}

	/**
	 * Tell the server to shut down
	 */
	public void shutdown () {
		out_.println("shutdown");
		out_.flush();
	}

	/**
	 * @return the iP
	 */
	public String getIP () {
		return IP_;
	}

	/**
	 * @return the playerOrderNum
	 */
	public int getPlayerOrderNum () {
		return playerOrderNum_;
	}

	/**
	 * @return the recentBoard
	 */
	public int[][] getRecentBoard () {
		return recentBoard_;
	}

	/**
	 * @param recentBoard
	 *          the recentBoard to set
	 */
	public void setRecentBoard ( int[][] recentBoard ) {
		recentBoard_ = recentBoard;
	}

}
