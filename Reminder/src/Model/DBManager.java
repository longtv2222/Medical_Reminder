package Model;
import java.sql.*;
import java.util.ArrayList;


//This class is responsible for creating tables if tables haven't been created.
public class DBManager {

	private static Connection conn = null;

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
	
	//If table hasn't existed, create it
	private void createMedicineTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists Medicine(med_id integer, user_id integer, med_name varchar(60), primary key(med_id), FOREIGN KEY(user_id) REFERENCES User(id));");
	}
	
	//If table hasn't existed, create it
	private void createAlarmTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists Alarm(id integer,med_id integer, alarm_name varchar(60),val INTEGER, unit varchar(5), primary key(id),FOREIGN KEY(med_id) REFERENCES Medicine(med_id));");
	}
	
	//If table hasn't existed, create it
	private void createUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists User(id integer,fname varchar(60),lName varchar(60), primary key(id));");
	}
	
	public void populateData() {
		
	}

	public static void main(String[] args) {
		try {
			DBManager test = new DBManager();
			test.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	private void loadUserMedicineData(int user_id) {
		
	}
	
	private void loadAlarmMedicineData(int user_id,int med_id) {
		
	}
	
	public void loadUserData(ArrayList<User> user_list) {

		
	}

}