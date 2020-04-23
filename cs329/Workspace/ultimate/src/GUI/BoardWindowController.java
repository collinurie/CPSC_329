package GUI;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import GUI.BoardCustomCanvas;
import Pente.ComputerPlayer;
import Pente.Move;
import Pente.NetworkPlayer;
import Pente.PenteGame;
import Pente.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class BoardWindowController implements Initializable {

	private SpectateListener listener_;

	private int moveNum_ = 0;
	private boolean gameOver = false;
	
	private boolean isSpectator_ = false;

	@FXML
	private BorderPane borderPane;

	@FXML
	private Label player1Score;

	@FXML
	private Polygon leftArrow;

	@FXML
	private Polygon rightArrow;

	@FXML
	private Label player2Score;

	@FXML
	private BoardCustomCanvas boardCanvas;
	
	private int p1RecentScore_ = 0;
	private int p2RecentScore_ = 0; 

//  public PenteGame game_;
//  
//  public void setGame(PenteGame g) {
//  	game_ = g;
//  }


	@FXML
	void boxedClicked ( MouseEvent event ) throws IOException {
		System.out.println("SPECTATOR STATUS: "+isSpectator_);
		if (isSpectator_) {
			return;
		}
		if ( !gameOver ) {

			// These need to be implemented
			double clickX = event.getX() - 20;
			double clickY = event.getY() - 20;
			int boardX = (int) Math.round((clickX / 40));
			int boardY = (int) Math.round((clickY / 40));
			int row = boardY;
			int col = boardX;
			Player currentPlayer =
			    moveNum_ % 2 == 0 ? HomeWindowController.getPlayerOne()
			        : HomeWindowController.getPlayerTwo();
			boolean success =
			    currentPlayer.handleUserInput(new Move(row,col,currentPlayer));

			if ( success ) { // successful move is made 
				moveNum_++;
				if ( currentPlayer.isNetwork() ) {
					moveNum_--;
					NetworkPlayer np = (NetworkPlayer) currentPlayer;
					boardCanvas.paintBoard(np.getRecentBoard());
					System.out.println("This is recent board");
					for ( int i = 0 ; i < np.getRecentBoard().length ; i++ ) {
						for ( int j = 0 ; j < np.getRecentBoard()[i].length ; j++ ) {
							System.out.print(np.getRecentBoard()[i][j]);
						}
						System.out.println();
					}
				} else {
					currentPlayer =
					    moveNum_ % 2 == 0 ? HomeWindowController.getPlayerOne()
					        : HomeWindowController.getPlayerTwo();
					if ( currentPlayer.isComputer() ) {
						moveNum_++;
					} else {
						changeArrow(currentPlayer.getPieceNum());
					}
					int[][] board = currentPlayer.getBoardArray();
					boardCanvas.paintBoard(board);
									
				}
				if ( currentPlayer.gameIsWon() ) {
					try {
						showGameOver(currentPlayer.getPieceNum());
					} catch ( IOException e ) {
						e.printStackTrace();
					}

				}
				
				if ( HomeWindowController.getPlayerOne() instanceof NetworkPlayer ) {
					System.out.println("p1RecentScore: " + p1RecentScore_);
					System.out.println("p1RecentScore: " + p2RecentScore_);
					player1Score.setText(p1RecentScore_ + "");
					player2Score.setText(p2RecentScore_ + "");
				}
				else {
					System.out.println("I SHOULD NOT BE HERE!");
					updateScores(HomeWindowController.getPlayerOne().getCaptures(),
          HomeWindowController.getPlayerTwo().getCaptures());
				}

			}
			if ( isWinner() ) {
				showGameOverWindow();
			}
		}

	}

	@FXML
	void quit ( ActionEvent event ) throws IOException {

		if ( HomeWindowController.getPlayerOne() instanceof NetworkPlayer ) {
			((NetworkPlayer) HomeWindowController.getPlayerOne()).shutdown();
			listener_.shutdown();
		}
		Pane welcomePane =
		    (Pane) FXMLLoader.load(getClass().getResource("HomeWindow.fxml"));
		Scene welcomeScene = new Scene(welcomePane);

		Stage stage = (Stage) boardCanvas.getScene().getWindow();
		stage.setScene(welcomeScene);
		stage.show();
	}

	@FXML
	void restart ( ActionEvent event ) throws IOException, URISyntaxException {

		String url = "https://www.pente.net/instructions.html";
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("/usr/bin/firefox -new-window " + url);
	}

	/**
	 * updates scores of the two players in the UI.
	 * @param p1
	 * @param p2
	 */
	public void updateScores ( int p1, int p2 ) {
		player1Score.setText(p1 + "");
		player2Score.setText(p2 + "");
	}
	
	public void setp1RecentScore(int s) {
		p1RecentScore_ = s;
	}
	public void setp2RecentScore(int s) {
		p2RecentScore_ = s;

	}

	/**
	 * changes the arrow of the player whos turn it is. 
	 * @param playerTurn
	 */
	public void changeArrow ( int playerTurn ) {

		if ( playerTurn != 1 ) { // player 2 turn
			rightArrow.setStyle("-fx-fill : #1dd105");
			leftArrow.setStyle("-fx-fill : #959696");
		} else { // player 1 turn
			leftArrow.setStyle("-fx-fill : #1dd105");
			rightArrow.setStyle("-fx-fill : #959696");
		}
	}
 /**
  * Shows a dialogue box when the game is over so the users can quit to main menu. 
  * @param winner
  * @throws IOException
  */
	public void showGameOver ( int winner ) throws IOException {
		String color = "Black";
		if ( winner == 1 ) {
			color = "White";
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Game Over!");
		alert.setHeaderText(color + " has won!");
		// alert.setContentText("Choose your option.");

		ButtonType quitButton = new ButtonType("Quit to Main Menu");

		alert.getButtonTypes().setAll(quitButton);

		Optional<ButtonType> result = alert.showAndWait();
		if ( result.get() == quitButton ) {
			Pane welcomePane =
			    (Pane) FXMLLoader.load(getClass().getResource("HomeWindow.fxml"));
			Scene welcomeScene = new Scene(welcomePane);

			Stage stage = (Stage) boardCanvas.getScene().getWindow();
			stage.setScene(welcomeScene);
			stage.show();
		}
	}


	/**
	 * displays the fact that the game is over to the users. 
	 * @throws IOException
	 */
	public void showGameOverWindow () throws IOException {
		gameOver = true;
		boardCanvas.drawWinnerCanvas(SpectateListener.winner_);
	}
	
	/**
	 * returns if there is a winner.
	 * @return boolean
	 */
	public boolean isWinner () {
		if ( SpectateListener.winner_ != 0 ) {
			return true;
		}
		return false;

	}

	/**
	 * Necessary code that needs to be done before the window is displayed.
	 */
	@Override
	public void initialize ( URL arg0, ResourceBundle arg1 ) {
		// If we are player two, call awaitBoard();
		try {
			if ( HomeWindowController.getPlayerOne() instanceof NetworkPlayer ) {
				NetworkPlayer np = (NetworkPlayer) HomeWindowController.getPlayerOne();
				listener_ = new SpectateListener(np.getIP(),boardCanvas,this);
				Thread t = new Thread(listener_);
				t.setDaemon(true);
				t.start();
				System.out.println(np.getPlayerOrderNum());
				if ( np.getPlayerOrderNum() == 1 ) {

					System.out
					    .println("I am locking the board and waiting for the server");
					changeArrow(moveNum_);
					int[][] r = np.awaitResponse();
					boardCanvas.paintBoard(r);

				} else if (np.getPlayerOrderNum() > 1) {
					isSpectator_ = true;
				}
			}
		} catch ( IOException e ) {
			System.out.println("Error in setting up board w networking");
			e.printStackTrace();
		}
	}
}