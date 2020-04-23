package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import Pente.NetworkPlayer;

public class NetworkSelectionWindowController {

  @FXML
  private MenuItem mainMenuOption;
	@FXML
	private TextField ipText;

	@FXML
	private Button connectButon;

	@FXML
	public void connectToServer ( ActionEvent event ) {

		String inputText = ipText.getText();

		if ( inputText != null ) {
			try {
			((NetworkPlayer) HomeWindowController.getPlayerOne())
			    .connect(inputText,14457);
			System.out.println("MADE IT HERE");
			FXMLLoader loader =
			    new FXMLLoader(getClass().getResource("BoardWindow.fxml"));
			Scene waitScene = new Scene(loader.load());
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(waitScene);
			stage.setResizable(false);
			stage.show();
				//NetworkPlayer player = ((NetworkPlayer) HomeWindowController.getPlayerOne());
				//player.initializeConnection(inputText,14457);
				//player.initialHandling(0); //TODO: figure out where to get the game-type
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("WaitingScreen.fxml"));
//				Scene boardScene = new Scene(loader.load());
//				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//				stage.setScene(boardScene);
//				stage.setResizable(false);
//				stage.show();
			} catch (IOException e) {
				ipText.setText("Please enter a valid IP");
				System.out.println("Error: Please enter a valid IP");
				e.printStackTrace();
			}
		}
	}

	@FXML
	void showAbout ( ActionEvent event ) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Types");
		alert.setHeaderText(null);
		alert.setContentText("Expand page for rule details.");

		Label label = new Label("Rule Variations are:");
		String ruleText =
		    "Standard Rules\n\tFirst player’s first move must be on center point\n"
		        + "\tPlayers then take turns placing their stone, one at a time, on an empty intersection \n"
		        + "\tPlayer wins by placing 5 stones in a row or by capturing 5 pairs\n"
		        + "Tournament\n\tFirst player’s first move must be on center point\n"
		        + "\tFirst players second move must be three or more intersections away from the center of board\n"
		        + "\tThe rest of standard Pente rules apply\n"
		        + "Freestyle\n\tFirst player’s first move can be anywhere on board\n"
		        + "\tThe rest of standard Pente rules apply\n" + "";
		TextArea textArea = new TextArea(ruleText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea,Priority.ALWAYS);
		GridPane.setHgrow(textArea,Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label,0,0);
		expContent.add(textArea,0,1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
  @FXML
  void goToMainMenu(ActionEvent event) throws IOException {
  	Pane welcomePane =
		    (Pane) FXMLLoader.load(getClass().getResource("HomeWindow.fxml"));
		Scene welcomeScene = new Scene(welcomePane);

		Stage stage = (Stage) ipText.getScene().getWindow();
		stage.setScene(welcomeScene);
		stage.show();
  }

}
