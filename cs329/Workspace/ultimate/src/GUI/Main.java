package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author cu5988
 *
 */
public class Main extends Application {


	@Override
	public void start ( Stage primaryStage ) {
		try {
			Pane welcomePane =
			    (Pane) FXMLLoader.load(getClass().getResource("HomeWindow.fxml"));
			Scene welcomeScene = new Scene(welcomePane);

			primaryStage.setScene(welcomeScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
		

	public static void main ( String[] args ) {
		launch(args);		
	}
	
}