package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class Model_Controller {
	public static int user_id = 0; // Index of current user.
	private DBManager db;
	private ArrayList<User> user_list;

	public Model_Controller() {
		try {
			user_list = new ArrayList<User>();
			db = new DBManager();
			db.loadUserData(user_list);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<User> get_UserList() {
		return this.user_list;
	}

	/*
	 * Assign one thread to check alarm recursively.
	 */
	public void checkAlarm(ExecutorService executor) {
		executor.execute(user_list.get(user_id));
	}

	public DBManager get_db() {
		return this.db;
	}

	public String getUserName() {
		return user_list.get(Model_Controller.user_id).getUserName();
	}

	public ConcurrentHashMap<String, ArrayList<Alarm>> getMedTime() {
		return user_list.get(Model_Controller.user_id).getMedTime();
	}

	public void addAlarm(String medName, Alarm alarm) {
		try {
			db.addAlarm(medName, alarm);
			user_list = new ArrayList<User>();
			db.loadUserData(user_list); // Need to look at this again later.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getAllMedicine() {
		return user_list.get(Model_Controller.user_id).getMedList();
	}
}
