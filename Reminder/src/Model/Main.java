package Model;

import java.sql.SQLException;
import java.util.*;
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
		try {
			DBManager db = new DBManager();
			db.getConnection();
			ArrayList<User> user_list = new ArrayList<User>();
			db.loadUserData(user_list);
			for (User user : user_list) {
				System.out.println(user.getUserName());
				user.printAllAlarm();
			}
			ExecutorService executor = Executors.newSingleThreadExecutor(); // Create a thread for user to recursively
																			// check
																			// alarm ring
			Controller controller = new Controller(db, user_list);

			executor.execute(user_list.get(0));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
