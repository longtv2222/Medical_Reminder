package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

//This class is responsible for creating tables if tables haven't been created.
public class DBManager {

	private static Connection conn = null;

	public DBManager() throws ClassNotFoundException, SQLException {
		this.getConnection();
	}

	public static Connection dbConnector() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DBSM.sqlite");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = dbConnector();
		initialise();
	}

	private void initialise() throws SQLException {
		dataBaseSetting();
		createAlarmTable();
		createMedicineTable();
		createUserTable();
	}

	private void dataBaseSetting() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("PRAGMA foreign_keys = 1");
	}

	// If table hasn't existed, create it
	private void createMedicineTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists Medicine(med_id integer, user_id integer, med_name varchar(60), primary key(med_id), FOREIGN KEY(user_id) REFERENCES User(id));");
	}

	// If table hasn't existed, create it
	private void createAlarmTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists Alarm(id integer,user_id integer, med_id integer, alarm_name varchar(60),hour INTEGER, minute Integer, val INTEGER, unit varchar(5), primary key(id),"
						+ "FOREIGN KEY(med_id) REFERENCES Medicine(med_id),FOREIGN KEY(user_id) REFERENCES User(id));");
	}

	// If table hasn't existed, create it
	private void createUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists User(id integer,fname varchar(60),lName varchar(60), primary key(id));");
	}

	// Load user medicine into hashmap of user and call loadAlarmMedicine method.
	private void loadUserMedicineData(int user_id, ConcurrentHashMap<String, ArrayList<Alarm>> concurrentHashMap)
			throws SQLException {
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM Medicine WHERE user_id = " + user_id);

		while (rs.next()) {
			concurrentHashMap.put(rs.getString("med_name"), new ArrayList<Alarm>()); // Create new key and intialize
																						// alarm at this key.
			ArrayList<Alarm> time = concurrentHashMap.get(rs.getString("med_name"));
			loadAlarmMedicineData(time, user_id, rs.getInt("med_id"));
		}
	}

	private void loadAlarmMedicineData(ArrayList<Alarm> time, int user_id, int med_id) throws SQLException {
		Statement state = conn.createStatement();
		ResultSet rs = state
				.executeQuery("SELECT * FROM Alarm WHERE user_id = " + user_id + " AND med_id = " + med_id + ";");

		while (rs.next()) {
			time.add(new Alarm(rs.getInt("hour"), rs.getInt("minute"), rs.getDouble("val"), rs.getString("unit"),
					rs.getString("alarm_name")));
		}

	}

	public void loadUserData(ArrayList<User> user_list) throws SQLException {
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM User");
		while (rs.next()) {
			user_list.add(new User(rs.getString("fname") + " " + rs.getString("lname"), rs.getInt("id")));
			loadUserMedicineData(rs.getInt("id"), user_list.get(user_list.size() - 1).getMedTime());
		}
	}

}