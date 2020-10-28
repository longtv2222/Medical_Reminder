package Model;
import java.sql.*;


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
		state.execute("CREATE TABLE if not exists Medicine(med_id integer,med_name varchar(60),val INTEGER, unit varchar(5), primary key(med_id));");
	}
	
	//If table hasn't existed, create it
	private void createAlarmTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists Alarm(id integer,med_id integer, alarm_name varchar(60), primary key(id),FOREIGN KEY(med_id) REFERENCES Medicine(med_id));");
	}
	
	//If table hasn't existed, create it
	private void createUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists user(id integer,fname varchar(60),lName varchar(60), primary key(id));");
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

}