package decorator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BreakoutMain extends Application
    implements PropertyChangeListener {

	private Canvas canvas_;
	private BreakoutGame game_;

	/**
	 * @param args
	 */
	public static void main ( String[] args ) {
		launch(args);
	}

	@Override
	public void start ( Stage stage ) throws Exception {
		game_ = new BreakoutGame(800,600,3);

		BorderPane root = new BorderPane();
		root.setStyle("-fx-border-width: 4px; -fx-border-color: #000000");

		Scene scene = new Scene(root);

		Dimension2D dim = game_.getSize();
		canvas_ = new Canvas(dim.getWidth(),dim.getHeight());
		root.setCenter(canvas_);

		canvas_.setOnMouseEntered(e -> game_.unpause());
		canvas_.setOnMouseExited(e -> game_.pause());
		canvas_.setOnMouseMoved(e -> game_.setPaddleTarget(e.getX()));
		game_.addPropertyChangeListener(this);

		stage.setScene(scene);
		stage.setTitle("breakout");
		stage.show();
		stage.setResizable(false);
	}

	public void draw () {
		GraphicsContext g = canvas_.getGraphicsContext2D();

		g.clearRect(0,0,canvas_.getWidth(),canvas_.getHeight());

		// ball
		Ball ball = game_.getBall();
		ball.draw(g);

		// paddle
		Paddle paddle = game_.getPaddle();
		paddle.draw(g);

		// walls
		for ( Wall wall : game_.getWalls() ) {
			wall.draw(g);
		}

		// status info
		g.setFill(Color.BLACK);
		g.setFont(new Font("SansSerif",12));
		g.fillText("score: " + game_.getScore(),20,30);
		g.fillText("balls left: " + game_.getBallsLeft(),20,45);

		// bricks
		for ( int row = 0 ; row < game_.getBlockRows() ; row++ ) {
			for ( int col = 0 ; col < game_.getBlockCols() ; col++ ) {
				Brick brick = game_.getBrick(row,col);
				if ( brick != null ) {
					brick.draw(g);
				}
			}
		}
	}

	@Override
	public void propertyChange ( PropertyChangeEvent event ) {
		if ( event.getPropertyName().equals("repaint") ) {
			draw();
		} else if ( event.getPropertyName().equals("stop") ) {
			game_.stop();
		}
	}

}
