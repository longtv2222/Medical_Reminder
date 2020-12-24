package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

//This class is responsible for creating tables if tables haven't been created.
public class DBManager {

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
		try (Connection conn = dbConnector()) {
			initialise(conn);
		}

	}

	private void initialise(Connection conn) throws SQLException {
		dataBaseSetting(conn);
		createAlarmTable(conn);
		createMedicineTable(conn);
		createUserTable(conn);
		createTableFrequency(conn);
	}

	private void dataBaseSetting(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.execute("PRAGMA foreign_keys = 1");
	}

	// If table hasn't existed, create it
	private void createMedicineTable(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists Medicine(med_id integer, user_id integer, med_name varchar(60), primary key(med_id), FOREIGN KEY(user_id) REFERENCES User(id));");
	}

	// If table hasn't existed, create it
	private void createAlarmTable(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists Alarm(id integer,user_id integer, med_id integer, alarm_name varchar(60),hour INTEGER, minute Integer, val INTEGER, unit varchar(5), primary key(id),"
						+ "FOREIGN KEY(med_id) REFERENCES Medicine(med_id),FOREIGN KEY(user_id) REFERENCES User(id));");
	}

	private void createTableFrequency(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists Alarm_Freq(id integer, monday integer, tuesday integer, wednesday integer, thursday integer, friday integer, saturday integer, sunday integer,"
						+ "FOREIGN KEY(id) REFERENCES Alarm(id));");
	}

	// If table hasn't existed, create it
	private void createUserTable(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE if not exists User(id integer,fname varchar(60),lName varchar(60), primary key(id));");
	}

	// Load user medicine into hashmap of user and call loadAlarmMedicine method.
	private void loadUserMedicineData(int user_id, ConcurrentHashMap<String, ArrayList<Alarm>> concurrentHashMap,
			Connection conn) throws SQLException {
		PreparedStatement state = conn.prepareStatement("SELECT * FROM Medicine WHERE user_id = ?");
		state.setInt(1, user_id);
		ResultSet rs = state.executeQuery();

		while (rs.next()) {
			concurrentHashMap.put(rs.getString("med_name"), new ArrayList<Alarm>()); // Create new key and intialize
																						// alarm at this key.
			ArrayList<Alarm> time = concurrentHashMap.get(rs.getString("med_name"));
			loadAlarmMedicineData(time, user_id, rs.getInt("med_id"), conn);
		}
	}

	private void loadAlarmMedicineData(ArrayList<Alarm> time, int user_id, int med_id, Connection conn)
			throws SQLException {
		PreparedStatement state = conn.prepareStatement("SELECT * FROM Alarm WHERE user_id = ? AND med_id = ?;");
		state.setInt(1, user_id);
		state.setInt(2, med_id);
		ResultSet rs = state.executeQuery();

		while (rs.next()) {
			time.add(new Alarm(rs.getInt("id"), rs.getInt("hour"), rs.getInt("minute"), rs.getDouble("val"),
					rs.getString("unit"), rs.getString("alarm_name")));
		}
	}

	public void loadUserData(ArrayList<User> user_list) throws SQLException {
		try (Connection conn = dbConnector()) {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM User");
			while (rs.next()) {
				user_list.add(new User(rs.getString("fname") + " " + rs.getString("lname"), rs.getInt("id")));
				loadUserMedicineData(rs.getInt("id"), user_list.get(user_list.size() - 1).getMedTime(), conn);
			}
		}
	}

	public void addAlarm(int user_id, String medName, Alarm alarm) {
		try (Connection conn = dbConnector()) {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(
					"SELECT * FROM Medicine WHERE med_name = '" + medName + "' AND user_id =" + user_id + ";");
			int med_id = rs.getInt("med_id");

			PreparedStatement state2 = conn.prepareStatement(
					"INSERT INTO Alarm(user_id, med_id, alarm_name, hour, minute, val , unit) VALUES (?,?,?,?,?,?,?)");
			state2.setInt(1, user_id);
			state2.setInt(2, med_id);
			state2.setString(3, alarm.getAlarmName());
			state2.setInt(4, alarm.getHour());
			state2.setInt(5, alarm.getMinute());
			state2.setDouble(6, alarm.getVal());
			state2.setString(7, alarm.getUnit());
			state2.execute();

			Statement state3 = conn.createStatement();
			ResultSet rs2 = state3.executeQuery("SELECT last_insert_rowid();");
			alarm.setId(rs2.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeAlarm(int user_id, int alarm_id) {
		try (Connection conn = dbConnector()) {
			PreparedStatement state = conn.prepareStatement("DELETE FROM Alarm WHERE id = ? AND user_id = ?");
			state.setInt(1, alarm_id);
			state.setInt(2, user_id);
			state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * When we remove medicine, we remove all alarm with that medicine.
	 */
	public void removeMedicine(int user_id, String medName) {
		try (Connection conn = dbConnector()) {
			PreparedStatement state = conn
					.prepareStatement("SELECT * FROM Medicine WHERE med_name = ? AND user_id = ?;");
			state.setString(1, medName);
			state.setInt(2, user_id);
			ResultSet rs = state.executeQuery();
			int med_id = rs.getInt("med_id");
			// Delete all key constraint first
			PreparedStatement state2 = conn.prepareStatement("DELETE From Alarm WHERE med_id = ? AND user_id = ?;");
			state2.setInt(1, med_id);
			state2.setInt(2, user_id);
			state2.execute();

			PreparedStatement state3 = conn.prepareStatement("DELETE From Medicine WHERE med_id = ? AND user_id = ?;");
			state3.setInt(1, med_id);
			state3.setInt(2, user_id);
			state3.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addMedicine(int user_id, String medName) {
		try (Connection conn = dbConnector()) {
			PreparedStatement state = conn.prepareStatement("INSERT INTO Medicine (user_id,med_name) VALUES (?, ?)");
			state.setInt(1, user_id);
			state.setString(2, medName);
			state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}