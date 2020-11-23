package Model;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Model_Controller model = new Model_Controller();
		Controller controller = new Controller(model);
		controller.createGUI();
	}

}
