package GUI;

import java.io.IOException;

import Pente.ComputerPlayer;
import Pente.LocalPlayer;
import Pente.NetworkPlayer;
import Pente.PenteGame;
import Pente.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HomeWindowController {
	
	private static Player p1_;
	private static Player p2_;
	
	private static int gameType_ = 0;
  @FXML
  private Button playButton;

  @FXML
  private MenuButton playerOptionsButton;

  @FXML
  private MenuItem onePlayerOption;

  @FXML
  private MenuItem onePlayerOnlineOption;

  @FXML
  private MenuItem twoPlayerOption1;

  @FXML
  private MenuItem spectateOption;

  @FXML
  private MenuButton rulesOptionButton;

  @FXML
  private MenuItem standardRuleOption;

  @FXML
  private MenuItem tournametRuleOption;

  @FXML
  private MenuItem freestyleRuleOption;

  @FXML
  private MenuItem fiveInARoweRuleOption;

  @FXML
  private MenuItem gomakuCaroRuleOption;

  @FXML
  private MenuItem gomakuFreestyleRuleOption;

  @FXML
  private MenuItem gomakuStandardRuleOption;

  @FXML
  private MenuItem KeryoRuleOption;


  	public static Player getPlayerOne() {
  		return p1_;
  	}
  	
  	public static Player getPlayerTwo() {
  		return p2_;
  	}
  
    @FXML
    void setOnePlayer(ActionEvent event) {
    	playerOptionsButton.setText("1 Player Local");
    }

    @FXML
    void setOnePlayerOnline(ActionEvent event) {
    	playerOptionsButton.setText("1 Player Online");
 
    }

    @FXML
    void setRulesFreestyle(ActionEvent event) {
    	rulesOptionButton.setText("Freestyle");
    	gameType_ = 3;
    }

    @FXML
    void setRulesStandard(ActionEvent event) {
    	rulesOptionButton.setText("Standard");
    	gameType_ = 0;
    }

    @FXML
    void setRulesTournament(ActionEvent event) {
    	rulesOptionButton.setText("Tournament");
    	gameType_ = 1;
    }
    @FXML
    void setRulesFiveInARow(ActionEvent event) {
    	rulesOptionButton.setText("Five In A Roe");
    	gameType_ = 4;
    }

    @FXML
    void setRulesGomakuCaro(ActionEvent event) {
    	rulesOptionButton.setText("Gomaku Caro");
    	gameType_ = 8;
    }

    @FXML
    void setRulesGomakuFreestyle(ActionEvent event) {
    	rulesOptionButton.setText("Gomaku Freestyle");
    	gameType_ = 7;
    }


    @FXML
    void setRulesGomakuStandard(ActionEvent event) {
    	rulesOptionButton.setText("Gomaku Standard");
    	gameType_ = 6;
    }

    @FXML
    void setRulesKeryo(ActionEvent event) {
    	rulesOptionButton.setText("Keryo");
    	gameType_ = 2;
    }

    @FXML
    void setSpectate(ActionEvent event) {
    	playerOptionsButton.setText("Spectate");
    }

    @FXML
    void setTwoPlayer(ActionEvent event) {
    	playerOptionsButton.setText("2 Player Local");
    }

    @FXML
    void showAbout(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
 		 alert.setTitle("Game Types");
 		 alert.setHeaderText(null);
 		 alert.setContentText("Expand page for rule details.");

 		 Label label = new Label("Rule Variations are:");
 		 String ruleText = "Standard Rules\n\tFirst player’s first move must be on center point\n" + 
 		 		"\tPlayers then take turns placing their stone, one at a time, on an empty intersection \n" + 
 		 		"\tPlayer wins by placing 5 stones in a row or by capturing 5 pairs\n" + 
 		 		"Tournament\n\tFirst player’s first move must be on center point\n" + 
 		 		"\tFirst players second move must be three or more intersections away from the center of board\n" + 
 		 		"\tThe rest of standard Pente rules apply\n" + 
 		 		"Freestyle\n\tFirst player’s first move can be anywhere on board\n" + 
 		 		"\tThe rest of standard Pente rules apply\n" + 
 		 		"";
 		 TextArea textArea = new TextArea(ruleText);
 		 textArea.setEditable(false);
 		 textArea.setWrapText(true);

 		 textArea.setMaxWidth(Double.MAX_VALUE);
 		 textArea.setMaxHeight(Double.MAX_VALUE);
 		 GridPane.setVgrow(textArea, Priority.ALWAYS);
 		 GridPane.setHgrow(textArea, Priority.ALWAYS);

 		 GridPane expContent = new GridPane();
 		 expContent.setMaxWidth(Double.MAX_VALUE);
 		 expContent.add(label, 0, 0);
 		 expContent.add(textArea, 0, 1);

 		 // Set expandable Exception into the dialog pane.
 		 alert.getDialogPane().setExpandableContent(expContent);

 		 alert.showAndWait();
    }

    @FXML
    void startGame(ActionEvent event) throws IOException {
    	Player.resetPlayerCount();
    	if(playerOptionsButton.getText().contains("Local")) { // if it is a local game 
    		if (playerOptionsButton.getText().equals("1 Player Local")) {
    			p1_ = new LocalPlayer("Player One");
    			p2_ = new ComputerPlayer("Player Two");
    			PenteGame g = new PenteGame(p1_,p2_, gameType_);
    			((LocalPlayer) p1_).setGame(g);
    			((ComputerPlayer) p2_).setGame(g);
    		} else if (playerOptionsButton.getText().equals("2 Player Local")) {
    			p1_ = new LocalPlayer("Player One");
    			p2_ = new LocalPlayer("Player Two");
    			PenteGame g = new PenteGame(p1_,p2_, gameType_);
    			((LocalPlayer) p1_).setGame(g);
    			((LocalPlayer) p2_).setGame(g);
    		}
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardWindow.fxml"));
    		Scene boardScene = new Scene(loader.load());
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		stage.setScene(boardScene);
    		stage.setResizable(false);
    		stage.show();
    	}
    	else { // if it is not a local game you must connect to the server before playing. 
    		p1_ = new NetworkPlayer("p1");
    		((NetworkPlayer) p1_).gameMode_ = gameType_;
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("NetworkSelectionWindow.fxml"));
    		Scene boardScene = new Scene(loader.load());
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		stage.setScene(boardScene);
    		stage.setResizable(false);
    		stage.show(); 
    	}    	

    }
    
    @FXML
    void updateRuleOption(ActionEvent event) {
    	

    }

		/**
		 * @return
		 */
		public static int getGameType () {
			return gameType_;
		}

}
