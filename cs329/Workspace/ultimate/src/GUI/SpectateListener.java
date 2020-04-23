package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Runnable class that listens for board updates from the server
 * @author mb5053
 */
public class SpectateListener implements Runnable {

	private BoardCustomCanvas boardCanvas_;
	
	private BufferedReader in_; // to read moves from server
	private PrintWriter out_;
	
	private boolean shutdown_;
	
	private BoardWindowController controller_;
	
	public static int winner_ = 0;

	/**
	 * Creates the listener
	 * @param IP - IP to connect to
	 * @param b - Canvas to draw too
	 * @param controller - controller to update scores with
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public SpectateListener ( String IP, BoardCustomCanvas b,
	                          BoardWindowController controller )
	    throws UnknownHostException, IOException {
		controller_ = controller;
		boardCanvas_ = b;
		Socket conn = new Socket(IP,14457);
		shutdown_ = false;
		in_ = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		out_ = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
	}

	/**
	 * Runs this thread. Listens for board and game updates, and intsructs the GUI to adapt accordingly
	 */
	public void run () {
		try {
			boolean success = handshake();
			if ( success ) {

				while ( !shutdown_ ) {
					String inp = in_.readLine();
					if ( inp.startsWith("Winner") ) {
						winner_ = Integer.parseInt(inp.split(";")[1]);
						System.out.println("inp: "+ inp);

						//c_.showGameOver(winner_);
						//showGameOverWindow();
						//c_.showGameOverWindow();
						boardCanvas_.drawWinnerCanvas(winner_);
					} else {
						int p1Score =Integer.parseInt(inp.split(";")[0]);
						int p2Score = Integer.parseInt(inp.split(";")[1]);
						controller_.setp1RecentScore(p1Score);
						controller_.setp2RecentScore(p2Score);
						System.out.println("Recieved a Board in SpectateListener");
						boardCanvas_.paintBoard(readBoard());
					}
				}
			}

		} catch ( IOException e ) {
			System.out.println("Network error.");
			e.printStackTrace();
		}

	}

	/**
	 * @return True if the handshake succeeded, false otherwise
	 * @throws IOException
	 */
	private boolean handshake () throws IOException {
		String inp = in_.readLine();
		if ( inp.equals("spectate") ) {
			out_.println("spectate");
			out_.flush();
			return true;
		}
		return false;
	}

	/**
	 * Reads a board from the connection
	 * @return - 2D integer Array representing the board
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
	 * Shut this thread down
	 */
	public void shutdown() {
		out_.println("shutdown");
		out_.flush();
		shutdown_ = true;
	}
	

}
