package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIClient extends Application {

	public static void main ( String[] args ) {
		launch(args);		
	}

	public void start ( Stage stage ) throws Exception {
		try {
			// get commandline parameters
			String host = getParameters().getUnnamed().get(0);
			//System.out.println(host);
			int port = Integer.parseInt(getParameters().getUnnamed().get(1));

			// load the FXML file defining the UI
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jokeclient.fxml"));
		  Parent root = fxmlLoader.load();
		  // get the UI controller
		  GUIController controller = (GUIController)fxmlLoader.getController();
		  SystemController sysControll  = new SystemController(controller,host,port);
		  controller.setSystemController(sysControll);
		  // set up the UI
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch ( Exception e ) {
			e.printStackTrace();
		}

	}


}
