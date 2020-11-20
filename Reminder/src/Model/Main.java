package Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(model.get_UserList().get(Model_Controller.user_id));

//		for (Alarm alarm : model.get_UserList().get(Model_Controller.user_id).getAlarmListOfMed("abba")) {
//			System.out.println(alarm.getHour() + "   " + alarm.getMinute());
//		}

	}

}
