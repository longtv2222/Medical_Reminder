package Model;
import javax.swing.*;
import java.sql.*;

public class DBManager {

	private static Connection conn = null;
	private static boolean hasData = false;

	public static Connection dbConnector() {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DBSM.sqlite");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	private void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:DBSM.sqlite");
		initialise();
	}

	public ResultSet displayUsers() {
		try {
			if (conn == null) {
				getConnection();
			}

			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery("SELECT fname, lname FROM user");
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void initialise() throws SQLException {
		if (!hasData) {
			hasData = true;
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
			if (!res.next()) {
				System.out.println("Building database");

				Statement state2 = conn.createStatement();
				state2.execute("CREATE TABLE user(id integer,fname varchar(60),lName varchar(60), primary key(id));");

				PreparedStatement prep = conn.prepareStatement("INSERT INTO user values(?,?,?);");

				prep.setString(2, "John");
				prep.setString(3, "McNeil");
				prep.execute();

				PreparedStatement prep2 = conn.prepareStatement("INSERT INTO user values(?,?,?);");
				prep2.setString(2, "Paul");
				prep2.setString(3, "Smith");
				prep2.execute();
				
				PreparedStatement prep3 = conn.prepareStatement("INSERT INTO user (id,fname,lName) VALUES (3,'LONG','TA');");
				prep3.execute();

			}
		}

	}

	public static void main(String[] args) {
		DBManager test = new DBManager();
		ResultSet rs = test.displayUsers();
		try {
			while (rs.next()) {
				System.out.println(rs.getString("fname") + rs.getString("lname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}