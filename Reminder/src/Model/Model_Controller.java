package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
		// Adding an alarm to db however we still don't know the id of it, we set it
		// inside addAlarm method.
		db.addAlarm(user_list.get(Model_Controller.user_id).getId(), medName, alarm);
		user_list.get(Model_Controller.user_id).addAlarm(medName, alarm);
	}

	public ArrayList<String> getAllMedicine() {
		return user_list.get(Model_Controller.user_id).getMedList();
	}

	public ArrayList<Alarm> getAlarmListOfMed(String medName) {
		return user_list.get(Model_Controller.user_id).getAlarmListOfMed(medName);
	}

	public void removeAlarm(int alarm_id) {
		int user_id = user_list.get(Model_Controller.user_id).getId();
		db.removeAlarm(user_id, alarm_id);
		user_list.get(Model_Controller.user_id).removeAlarm(alarm_id);
	}

	public void removeMedicine(String medName) {
		db.removeMedicine(user_list.get(user_id).getId(), medName);
		user_list.get(Model_Controller.user_id).removeMedicine(medName);
	}

	public void addMedicine(String medName) {
		db.addMedicine(user_list.get(user_id).getId(), medName);
		user_list.get(user_id).addMedicine(medName);
	}

	public Ringtone checkAlarm() {
		return user_list.get(user_id).recursiveCheckAlarm();
	}

}
