package GUI;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BoardCustomCanvas extends Canvas {
	private int boardSize_ = 19;
	private int[][] boardArr_;

	public BoardCustomCanvas () {
		super();
		boardArr_ = new int[boardSize_][boardSize_];
		this.setWidth(760);
		this.setHeight(760);
		buildGrid();
		this.setOnMousePressed( e -> {
			int player;
			double clickX = e.getX()-20;
			double clickY = e.getY()-20;
			int boardX = (int) Math.round((clickX/40));
			int boardY = (int) Math.round((clickY/40));
						
		});
	}

	/**
	 * Draws the grid on the canvas. 
	 */
	private void buildGrid () {
		GraphicsContext g = this.getGraphicsContext2D();
		g.setFill(Color.DARKGREEN);
		g.fillRect(0,0,760,760);
		g.setFill(Color.LAWNGREEN);
		g.fillRect(20,20,720,720);
		g.setFill(Color.LIGHTGREEN);
		g.fillRect(280,280,200,200);
		g.setFill(Color.BLACK);
		g.fillOval(375,375,10,10);
		for ( int i = 20 ; i <= 740 ; i = i + 40 ) {
			g.setStroke(Color.BLACK);
			g.strokeLine(i,740,i,20);
		}
		for ( int i = 20 ; i <= 740 ; i = i + 40 ) {
			g.setStroke(Color.BLACK);
			g.strokeLine(740,i,20,i);
		}
	}

	/**
	 * Paints a board with pieces on it. 
	 * @param b - an int[][] that contains where pieces are on the board. 
	 */
	public void paintBoard ( int[][] b ) {
		System.out.println("Painting Board");
		GraphicsContext g = this.getGraphicsContext2D();
		g.setFill(Color.TRANSPARENT);
		g.fillRect(0,0,760,760);
		buildGrid();
		for ( int i = 0 ; i < b.length ; i++ ) {
			for ( int j = 0 ; j < b[i].length ; j++ ) {
				int stone = b[i][j];
				System.out.print(stone);
				if ( stone != 0 ) {
					if ( stone == 1 ) {
						g.setFill(Color.BLACK);
					} else {
						g.setFill(Color.WHITE);
					}
					g.fillOval((j * 40),(i * 40),40,40);
				}
			}
			System.out.println();
		}

	}
	/**
	 *Paints the Boardcanvas with a message telling the users who has won. 	 
	 * @param winner - the player number that won the game
	 */
	public void drawWinnerCanvas(int winner){
		
		String winText;
		if(winner == 2) {
			winText = "Black Has Won the Game";
		}
		else {
			winText = "White Has Won the Game";
		}
		
		GraphicsContext g = this.getGraphicsContext2D();
		g.setFill(Color.DARKGREEN);
		g.fillRect(0,0,760,760); 
		g.setFill(Color.ANTIQUEWHITE);
		g.setTextAlign(TextAlignment.CENTER);
    g.setTextBaseline(VPos.CENTER);
    g.fillText(
               winText, 
                Math.round(this.getWidth()  / 2), 
                Math.round(this.getHeight() / 2)
              );
	}

	/**
	 * Pops up a dialogue box telling the users that the game has been won
	 * @param winner - the player number that won the game.
	 * @throws IOException
	 */
	public void showGameOver ( int winner ) throws IOException {
		String color = "Black";
		if ( winner == 2 ) {
			color = "White";
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Game Over!");
		alert.setHeaderText(color + " has won!");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Restart");
		ButtonType buttonTypeTwo = new ButtonType("Quit to Mian Menu");

		alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if ( result.get() == buttonTypeOne ) {
			FXMLLoader loader =
			    new FXMLLoader(getClass().getResource("BoardWindow.fxml"));
			Scene boardScene = new Scene(loader.load());
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(boardScene);
			stage.setResizable(false);
			stage.show();
		} else if ( result.get() == buttonTypeTwo ) {
			Pane welcomePane =
			    (Pane) FXMLLoader.load(getClass().getResource("HomeWindow.fxml"));
			Scene welcomeScene = new Scene(welcomePane);

			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(welcomeScene);
			stage.show();

		}
	}
}
