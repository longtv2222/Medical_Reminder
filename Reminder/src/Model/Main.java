package Model;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) { // Potentially has to use multithreading for this application.
		try {
			DBManager db = new DBManager();
			db.getConnection();
			ArrayList<User> user_list = new ArrayList<User>();
			db.loadUserData(user_list);
			for (User user : user_list) {
				System.out.println(user.getUserName());
				user.printAllAlarm();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		  User user = new User("Long",2); 
//		  ExecutorService executor = Executors.newSingleThreadExecutor();  //Create a thread for user to recursively check alarm ring
//		  executor.execute(user);
//
//		int option = 0;
//		Scanner scanner = new Scanner(System.in);
//		boolean stop = false;
//
//		while (!stop) {
//			System.out.println("\nPlease pick your option!");
//			option = scanner.nextInt();
//			scanner.nextLine();
//			String medName;
//			switch (option) {
//			case 1:
//				user.showAllMedicine();
//				break;
//			case 2:
//				System.out.println("Please enter the name of the med");
//				medName = scanner.nextLine();
//				user.addMedicine(medName);
//				break;
//			case 3:
//				System.out.println("Please enter the name of the med");
//				medName = scanner.nextLine();
//
//				System.out.println("Please enter the hour");
//				int hour = scanner.nextInt();
//				scanner.nextLine();
//
//				System.out.println("Please enter the minute");
//				int minute = scanner.nextInt();
//				scanner.nextLine();
//				user.addingAlarm(medName, new Alarm(hour, minute, 400, "ml"));
//				break;
//			case 4:
//				System.out.println("Please enter the name of the med");
//				medName = scanner.nextLine();
//				user.printAllAlarm(medName);
//				break;
//			case 5:
//				System.out.println("Bye");
//				stop = true;
//				user.recursiveCheckAlarm();
//				break;
//			default:
//				System.out.println("Instruction not avaialable");
//
//			}
//		}
//		scanner.close();

	}
}
