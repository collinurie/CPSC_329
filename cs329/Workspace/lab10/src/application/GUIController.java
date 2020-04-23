package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIController {
	
	SystemController sysControll_;

    @FXML
    private Button knockknock_;

    @FXML
    private Button riddle_;

    @FXML
    private Button shutdown_;

    @FXML
    private TextArea display_ ;  // joke/riddle display area

    @FXML
    private TextField input_;   // input for user's answer to the riddle


    public void setSystemController(SystemController sysController) {
    	sysControll_ = sysController;
    }
    
    @FXML
    void getKnockKnock(ActionEvent event) {
    		sysControll_.getKnockKnock();
//    		System.out.println(display_);
    	// get knock knock joke from server
    }

    @FXML
    void getRiddle(ActionEvent event) {
    	sysControll_.getRiddle();
    	// get riddle from server
    }

    @FXML
    void quit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void sendAnswer(ActionEvent event) {
    	sysControll_.answerRiddle(input_.getText());
    	input_.setText("");
    	// send user's answer to a riddle to the server
    }

    @FXML
    void shutdown(ActionEvent event) {
    	sysControll_.shutdownServer();
    }
    
    public void displayText(String message) {
    	//System.out.println(display_);
    	display_.setText(message);
    }
    
    

}
