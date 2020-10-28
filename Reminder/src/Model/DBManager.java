package Model;
import javax.swing.*;
import java.sql.*;

public class DBManager {

	private static Connection conn = null;

	public static Connection dbConnector() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DBSM.sqlite");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	public void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = dbConnector();
			initialise();
	}

	private void initialise() throws SQLException {
		createAlarmTable();
		System.out.println("Create");
		createMedicineTable();
		createUserTable();
	}
	
	
	private void createMedicineTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists Medicine(med_id integer,med_name varchar(60), primary key(med_id));");
	}
	
	private void createAlarmTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE if not exists Alarm(id integer,fname varchar(60), primary key(id));");
	}
	
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